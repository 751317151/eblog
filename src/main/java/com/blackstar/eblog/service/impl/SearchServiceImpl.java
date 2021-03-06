package com.blackstar.eblog.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.blackstar.eblog.entity.MPost;
import com.blackstar.eblog.search.model.PostDocment;
import com.blackstar.eblog.search.mq.PostMqIndexMessage;
import com.blackstar.eblog.search.repository.PostRepository;
import com.blackstar.eblog.service.MPostService;
import com.blackstar.eblog.service.SearchService;
import com.blackstar.eblog.vo.PostVo;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.index.query.*;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author huah
 * @since 2021年09月12日
 */
@Slf4j
@Service
public class SearchServiceImpl implements SearchService {

  @Autowired
  PostRepository postRepository;

  @Autowired
  ModelMapper modelMapper;

  @Autowired
  MPostService postService;

  @Override
  public IPage search(Page page, String keyword) {
    // 分页信息 mybatis plus的page 转成 jpa的page
    Long current = page.getCurrent() - 1;
    Long size = page.getSize();
    Pageable pageable = PageRequest.of(current.intValue(), size.intValue());

    // 搜索es得到pageData
    MultiMatchQueryBuilder multiMatchQueryBuilder = QueryBuilders.multiMatchQuery(keyword,
        "title", "authorName", "categoryName");

    QueryBuilder queryBuilder1 = QueryBuilders.wildcardQuery("title", "*"+keyword+"*");
    QueryBuilder queryBuilder2 = QueryBuilders.wildcardQuery("authorName", "*"+keyword+"*");
    QueryBuilder queryBuilder3 = QueryBuilders.wildcardQuery("categoryName", "*"+keyword+"*");
    QueryBuilder builder = QueryBuilders.boolQuery().should(queryBuilder1).should(queryBuilder2).should(queryBuilder3);
    org.springframework.data.domain.Page<PostDocment> docments = postRepository.search(builder, pageable);

    // 结果信息 jpa的pageData转成mybatis plus的pageData
    IPage pageData = new Page(page.getCurrent(), page.getSize(), docments.getTotalElements());
    pageData.setRecords(docments.getContent());
    return pageData;
  }

  @Override
  public int initEsData(List<PostVo> records) {
    if(records == null || records.isEmpty()) {
      return 0;
    }

    List<PostDocment> documents = new ArrayList<>();
    for(PostVo vo : records) {
      // 映射转换
      PostDocment postDocment = modelMapper.map(vo, PostDocment.class);
      documents.add(postDocment);
    }
    postRepository.saveAll(documents);
    return documents.size();
  }

  @Override
  public void createOrUpdateIndex(PostMqIndexMessage message) {
    Long postId = message.getPostId();
    PostVo postVo = postService.selectOnePost(new QueryWrapper<MPost>().eq("p.id", postId));

    PostDocment postDocment = modelMapper.map(postVo, PostDocment.class);

    postRepository.save(postDocment);

    log.info("es 索引更新成功！ ---> {}", postDocment.toString());
  }

  @Override
  public void removeIndex(PostMqIndexMessage message) {
    Long postId = message.getPostId();

    postRepository.deleteById(postId);
    log.info("es 索引删除成功！ ---> {}", message.toString());
  }
}

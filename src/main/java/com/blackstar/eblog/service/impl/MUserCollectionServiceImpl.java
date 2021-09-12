package com.blackstar.eblog.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.blackstar.eblog.entity.MUserCollection;
import com.blackstar.eblog.mapper.MUserCollectionMapper;
import com.blackstar.eblog.service.MUserCollectionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.blackstar.eblog.vo.CollectionVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author huah
 * @since 2021-08-28
 */
@Service
public class MUserCollectionServiceImpl extends ServiceImpl<MUserCollectionMapper, MUserCollection> implements MUserCollectionService {

  @Autowired
  MUserCollectionMapper userCollectionMapper;

  @Override
  public IPage<CollectionVo> paging(Page page, Long userid, String order) {
    return userCollectionMapper.selectCollections(page,new QueryWrapper<>()
        .eq("c.user_id",userid)
        .orderByDesc(order != null,order)
    );
  }
}

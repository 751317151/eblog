package com.blackstar.eblog.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.blackstar.eblog.search.mq.PostMqIndexMessage;
import com.blackstar.eblog.vo.PostVo;

import java.util.List;

/**
 * @author huah
 * @since 2021年09月12日
 */
public interface SearchService {

  IPage search(Page page, String keyword);

  int initEsData(List<PostVo> records);

  void createOrUpdateIndex(PostMqIndexMessage message);

  void removeIndex(PostMqIndexMessage message);
}

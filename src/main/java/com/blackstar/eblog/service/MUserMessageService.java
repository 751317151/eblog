package com.blackstar.eblog.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.blackstar.eblog.entity.MUserMessage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.blackstar.eblog.vo.UserMessageVo;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author huah
 * @since 2021-08-28
 */
public interface MUserMessageService extends IService<MUserMessage> {

  IPage<UserMessageVo> paging(Page page, QueryWrapper<MUserMessage> wrapper);

  void updateToReaded(List<Long> ids);
}

package com.blackstar.eblog.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.blackstar.eblog.entity.MUserMessage;
import com.blackstar.eblog.mapper.MUserMessageMapper;
import com.blackstar.eblog.service.MUserMessageService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.blackstar.eblog.vo.UserMessageVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author huah
 * @since 2021-08-28
 */
@Service
public class MUserMessageServiceImpl extends ServiceImpl<MUserMessageMapper, MUserMessage> implements MUserMessageService {

  @Autowired
  MUserMessageMapper messageMapper;

  @Override
  public IPage<UserMessageVo> paging(Page page, QueryWrapper<MUserMessage> wrapper) {
    return messageMapper.selectMessages(page, wrapper);
  }

  @Override
  public void updateToReaded(List<Long> ids) {
    if(ids.isEmpty()) return;

    messageMapper.updateToReaded(new QueryWrapper<MUserMessage>()
        .in("id", ids)
    );

  }
}

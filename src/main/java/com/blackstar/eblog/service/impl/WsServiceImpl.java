package com.blackstar.eblog.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.blackstar.eblog.entity.MUserMessage;
import com.blackstar.eblog.service.MUserMessageService;
import com.blackstar.eblog.service.WsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 * @author huah
 * @since 2021年09月11日
 */
@Service
public class WsServiceImpl implements WsService {

  @Autowired
  MUserMessageService messageService;

  @Autowired
  SimpMessagingTemplate messagingTemplate;

  @Async
  @Override
  public void sendMessCountToUser(Long toUserId) {
    int count = messageService.count(new QueryWrapper<MUserMessage>()
        .eq("to_user_id", toUserId)
        .eq("status", "0")
    );

    // websocket通知 (/user/20/messCount)
    messagingTemplate.convertAndSendToUser(toUserId.toString(), "/messCount", count);
  }
}

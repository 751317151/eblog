package com.blackstar.eblog.service.impl;

import cn.hutool.core.util.RandomUtil;
import com.blackstar.eblog.common.lang.Consts;
import com.blackstar.eblog.im.vo.ImMess;
import com.blackstar.eblog.im.vo.ImUser;
import com.blackstar.eblog.service.ChatService;
import com.blackstar.eblog.shiro.AccountProfile;
import com.blackstar.eblog.util.RedisUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author huah
 * @since 2021年09月20日
 */
@Slf4j
@Service("chatService")
public class ChatServiceImpl implements ChatService {

  @Autowired
  RedisUtil redisUtil;

  @Override
  public ImUser getCurrentUser() {
    AccountProfile profile = (AccountProfile) SecurityUtils.getSubject().getPrincipal();

    ImUser user = new ImUser();

    if(profile != null) {
      user.setId(profile.getId());
      user.setAvatar(profile.getAvatar());
      user.setUsername(profile.getUsername());
      user.setStatus(ImUser.ONLINE_STATUS);

    } else {
      user.setAvatar("http://tp1.sinaimg.cn/5619439268/180/40030060651/1");

      // 匿名用户处理
      Long imUserId = (Long) SecurityUtils.getSubject().getSession().getAttribute("imUserId");
      user.setId(imUserId != null ? imUserId : RandomUtil.randomLong());

      SecurityUtils.getSubject().getSession().setAttribute("imUserId", user.getId());

      user.setSign("never give up!");
      user.setUsername("匿名用户");
      user.setStatus(ImUser.ONLINE_STATUS);
    }

    return user;
  }

  @Override
  public void setGroupHistoryMsg(ImMess imMess) {
    redisUtil.lSet(Consts.IM_GROUP_HISTROY_MSG_KEY, imMess, 24 * 60 * 60);
  }

  @Override
  public List<Object> getGroupHistoryMsg(int count) {
    long length = redisUtil.lGetListSize(Consts.IM_GROUP_HISTROY_MSG_KEY);
    return redisUtil.lGet(Consts.IM_GROUP_HISTROY_MSG_KEY, length - count < 0 ? 0 : length - count, length);
  }
}
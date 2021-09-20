package com.blackstar.eblog.service;

import com.blackstar.eblog.im.vo.ImMess;
import com.blackstar.eblog.im.vo.ImUser;

import java.util.List;

/**
 * @author huah
 * @since 2021年09月20日
 */
public interface ChatService {
  ImUser getCurrentUser();

  void setGroupHistoryMsg(ImMess responseMess);

  List<Object> getGroupHistoryMsg(int count);
}

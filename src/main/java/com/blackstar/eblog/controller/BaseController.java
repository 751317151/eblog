package com.blackstar.eblog.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.blackstar.eblog.service.*;
import com.blackstar.eblog.shiro.AccountProfile;
import org.apache.shiro.SecurityUtils;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.ServletRequestUtils;

import javax.servlet.http.HttpServletRequest;

/**
 * @author huah
 * @since 2021年08月28日
 */
public class BaseController {

  @Autowired
  HttpServletRequest req;

  @Autowired
  MPostService postService;

  @Autowired
  MCommentService commentService;

  @Autowired
  MUserMessageService messageService;

  @Autowired
  MUserCollectionService userCollectionService;

  @Autowired
  MCategoryService categoryService;

  @Autowired
  MUserService userService;

  @Autowired
  WsService wsService;

  @Autowired
  SearchService searchService;

  @Autowired
  AmqpTemplate amqpTemplate;

  public Page getPage(){
    int pn = ServletRequestUtils.getIntParameter(req,"pn",1);
    int size = ServletRequestUtils.getIntParameter(req,"size",2);
    return new Page(pn,size);
  }

  protected AccountProfile getProfile() {
    return (AccountProfile) SecurityUtils.getSubject().getPrincipal();
  }

  protected Long getProfileId() {
    return getProfile().getId();
  }
}

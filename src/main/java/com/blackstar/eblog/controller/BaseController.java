package com.blackstar.eblog.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.blackstar.eblog.service.MCommentService;
import com.blackstar.eblog.service.MPostService;
import com.blackstar.eblog.service.MUserService;
import com.blackstar.eblog.shiro.AccountProfile;
import org.apache.shiro.SecurityUtils;
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
  MUserService userService;

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

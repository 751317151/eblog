package com.blackstar.eblog.config;

import com.blackstar.eblog.template.HotsTemplate;
import com.blackstar.eblog.template.PostsTemplate;
import com.blackstar.eblog.template.TimeAgoMethod;
import com.jagregory.shiro.freemarker.ShiroTags;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

/**
 * @author huah
 * @since 2021年08月29日
 */
@Configuration
public class FreemarkerConfig {

  @Autowired
  private freemarker.template.Configuration configuration;

  @Autowired
  PostsTemplate postsTemplate;

  @Autowired
  HotsTemplate hotsTemplate;

  @PostConstruct
  public void setUp() {
    configuration.setSharedVariable("timeAgo", new TimeAgoMethod());
    configuration.setSharedVariable("posts", postsTemplate);
    configuration.setSharedVariable("hots", hotsTemplate);
    configuration.setSharedVariable("shiro", new ShiroTags());
  }

}

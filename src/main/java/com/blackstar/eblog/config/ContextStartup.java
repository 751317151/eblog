package com.blackstar.eblog.config;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.blackstar.eblog.entity.MCategory;
import com.blackstar.eblog.service.MCategoryService;
import com.blackstar.eblog.service.MPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import org.springframework.web.context.ServletContextAware;

import javax.servlet.ServletContext;
import java.util.List;

/**
 * @author huah
 * @since 2021年08月28日
 */
@Component
public class ContextStartup implements ApplicationRunner, ServletContextAware {

  @Autowired
  MCategoryService mCategoryService;

  ServletContext servletContext;

  @Autowired
  MPostService mPostService;

  @Override
  public void run(ApplicationArguments args) throws Exception {
    List<MCategory> mCategories = mCategoryService.list(new QueryWrapper<MCategory>().eq("status", 0));
    servletContext.setAttribute("categories",mCategories);

    mPostService.initWeekRank();
  }

  @Override
  public void setServletContext(ServletContext servletContext) {
    this.servletContext = servletContext;
  }
}

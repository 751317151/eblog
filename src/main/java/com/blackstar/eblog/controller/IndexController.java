package com.blackstar.eblog.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author huah
 * @since 2021年08月28日
 */
@Controller
public class IndexController extends BaseController {

  @RequestMapping({"","/","index"})
  public String index(){

    // 1分页信息，2分类 3用户 4置顶 5精选 6排序
    IPage results = mPostService.paging(getPage(),null,null,null,null,"created");

    req.setAttribute("pageData",results);
    req.setAttribute("currentCategoryId",0);
    return "index";
  }
}

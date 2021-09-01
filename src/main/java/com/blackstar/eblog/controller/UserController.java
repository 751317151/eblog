package com.blackstar.eblog.controller;

import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.SecureUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.blackstar.eblog.common.lang.Result;
import com.blackstar.eblog.entity.MPost;
import com.blackstar.eblog.entity.MUser;
import com.blackstar.eblog.shiro.AccountProfile;
import com.blackstar.eblog.util.UploadUtil;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

/**
 * @author huah
 * @since 2021年09月01日
 */
@Controller
public class UserController extends BaseController {

  @Autowired
  UploadUtil uploadUtil;

  @GetMapping("/user/home")
  public String home() {

    MUser user = userService.getById(getProfileId());

    List<MPost> posts = postService.list(new QueryWrapper<MPost>()
        .eq("user_id", getProfileId())
        // 30天内
        //.gt("created", DateUtil.offsetDay(new Date(), -30))
        .orderByDesc("created")
    );

    req.setAttribute("user", user);
    req.setAttribute("posts", posts);
    return "/user/home";
  }

  @GetMapping("/user/set")
  public String set() {
    MUser user = userService.getById(getProfileId());
    req.setAttribute("user", user);

    return "/user/set";
  }

  @ResponseBody
  @PostMapping("/user/set")
  public Result doSet(MUser user) {

    if(StrUtil.isNotBlank(user.getAvatar())) {

      MUser temp = userService.getById(getProfileId());
      temp.setAvatar(user.getAvatar());
      userService.updateById(temp);

      AccountProfile profile = getProfile();
      profile.setAvatar(user.getAvatar());

      SecurityUtils.getSubject().getSession().setAttribute("profile", profile);

      return Result.success().action("/user/set#avatar");
    }

    if(StrUtil.isBlank(user.getUsername())) {
      return Result.fail("昵称不能为空");
    }
    int count = userService.count(new QueryWrapper<MUser>()
        .eq("username", getProfile().getUsername())
        .ne("id", getProfileId()));
    if(count > 0) {
      return Result.fail("该昵称已被占用");
    }

    MUser temp = userService.getById(getProfileId());
    temp.setUsername(user.getUsername());
    temp.setGender(user.getGender());
    temp.setSign(user.getSign());
    userService.updateById(temp);

    AccountProfile profile = getProfile();
    profile.setUsername(temp.getUsername());
    profile.setSign(temp.getSign());
    SecurityUtils.getSubject().getSession().setAttribute("profile", profile);

    return Result.success().action("/user/set#info");
  }

  @ResponseBody
  @PostMapping("/user/upload")
  public Result uploadAvatar(@RequestParam(value = "file") MultipartFile file) throws IOException {
    return uploadUtil.upload(UploadUtil.type_avatar, file);
  }

  @ResponseBody
  @PostMapping("/user/repass")
  public Result repass(String nowpass, String pass, String repass) {
    if(!pass.equals(repass)) {
      return Result.fail("两次密码不相同");
    }

    MUser user = userService.getById(getProfileId());

    String nowPassMd5 = SecureUtil.md5(nowpass);
    if(!nowPassMd5.equals(user.getPassword())) {
      return Result.fail("密码不正确");
    }

    user.setPassword(SecureUtil.md5(pass));
    userService.updateById(user);

    return Result.success().action("/user/set#pass");
  }
}

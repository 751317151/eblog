package com.blackstar.eblog.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.crypto.SecureUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.blackstar.eblog.common.lang.Result;
import com.blackstar.eblog.entity.MUser;
import com.blackstar.eblog.mapper.MUserMapper;
import com.blackstar.eblog.service.MUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.blackstar.eblog.shiro.AccountProfile;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author huah
 * @since 2021-08-28
 */
@Service
public class MUserServiceImpl extends ServiceImpl<MUserMapper, MUser> implements MUserService {

  @Override
  public Result register(MUser user) {
    int count = this.count(new QueryWrapper<MUser>()
        .eq("email", user.getEmail())
        .or()
        .eq("username", user.getUsername())
    );
    if(count > 0) return Result.fail("用户名或邮箱已被占用");

    // 安全注册
    MUser temp = new MUser();
    temp.setId(7l);
    temp.setUsername(user.getUsername());
    temp.setPassword(SecureUtil.md5(user.getPassword()));
    temp.setEmail(user.getEmail());
    temp.setAvatar("/res/images/avatar/default.png");

    temp.setCreated(new Date());
    temp.setPoint(0);
    temp.setVipLevel(0);
    temp.setCommentCount(0);
    temp.setPostCount(0);
    temp.setGender("0");
    this.save(temp);

    return Result.success();
  }

  @Override
  public AccountProfile login(String email, String password) {
    MUser user = this.getOne(new QueryWrapper<MUser>().eq("email", email));
    if(user == null) {
      throw new UnknownAccountException();
    }
    if(!user.getPassword().equals(password)){
      throw new IncorrectCredentialsException();
    }

    user.setLasted(new Date());
    this.updateById(user);

    AccountProfile profile = new AccountProfile();
    BeanUtil.copyProperties(user, profile);

    return profile;
  }
}

package com.blackstar.eblog.service;

import com.blackstar.eblog.common.lang.Result;
import com.blackstar.eblog.entity.MUser;
import com.baomidou.mybatisplus.extension.service.IService;
import com.blackstar.eblog.shiro.AccountProfile;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author huah
 * @since 2021-08-28
 */
public interface MUserService extends IService<MUser> {

  Result register(MUser user);

  AccountProfile login(String email, String password);
}

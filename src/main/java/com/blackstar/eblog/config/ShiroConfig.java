package com.blackstar.eblog.config;

import cn.hutool.core.map.MapUtil;
import com.blackstar.eblog.shiro.AccountRealm;
import com.blackstar.eblog.shiro.AuthFilter;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.codec.Base64;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.CookieRememberMeManager;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author huah
 * @since 2021年09月01日
 */
@Slf4j
@Configuration
public class ShiroConfig {

  @Bean
  public SimpleCookie rememberMeCookie() {
    // 这个参数是cookie的名称，对应前端的checkbox的name = rememberMe
    SimpleCookie simpleCookie = new SimpleCookie("rememberMe");
    // <!-- 记住我cookie生效时间30天 ,单位秒;-->
    simpleCookie.setMaxAge(259200);
    return simpleCookie;
  }

  /**
   * CookieRememberMeManager
   *
   * @return
   */
  @Bean
  public CookieRememberMeManager rememberMeManager() {
    System.out.println("ShiroConfiguration.rememberMeManager()");
    CookieRememberMeManager cookieRememberMeManager = new CookieRememberMeManager();
    cookieRememberMeManager.setCookie(rememberMeCookie());
    cookieRememberMeManager.setCipherKey(Base64.decode("2AvVhdsgUs0FSA3SDFAdag=="));
    return cookieRememberMeManager;
  }

  @Bean(name = "sessionManager")
  public DefaultWebSessionManager sessionManager() {
    System.out.println("ShiroConfiguration.sessionManager()");
    DefaultWebSessionManager sessionManager = new DefaultWebSessionManager();
    sessionManager.getSessionIdCookie().setName("sId");
    sessionManager.setGlobalSessionTimeout(1800000);
    sessionManager.setDeleteInvalidSessions(true);
//    sessionManager.setSessionValidationScheduler(sessionValidationScheduler());
    sessionManager.setSessionValidationSchedulerEnabled(true);
//    sessionManager.setSessionListeners(sessionListeners());
//    sessionManager.setSessionDAO(mySessionDao());
    return sessionManager;
  }

  @Bean
  public SecurityManager securityManager(AccountRealm accountRealm){

    DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
    securityManager.setRealm(accountRealm);
    securityManager.setRememberMeManager(rememberMeManager());
    securityManager.setSessionManager(sessionManager());

    log.info("------------------>securityManager注入成功");

    return securityManager;
  }

  @Bean
  public ShiroFilterFactoryBean shiroFilterFactoryBean(SecurityManager securityManager) {

    ShiroFilterFactoryBean filterFactoryBean = new ShiroFilterFactoryBean();
    filterFactoryBean.setSecurityManager(securityManager);
    // 配置登录的url和登录成功的url
    filterFactoryBean.setLoginUrl("/login");
    filterFactoryBean.setSuccessUrl("/user/center");
    // 配置未授权跳转页面
    filterFactoryBean.setUnauthorizedUrl("/error/403");

    filterFactoryBean.setFilters(MapUtil.of("auth", authFilter()));

    Map<String, String> hashMap = new LinkedHashMap<>();

    hashMap.put("/res/**", "anon");

    hashMap.put("/user/home", "auth");
    hashMap.put("/user/set", "auth");
    hashMap.put("/user/upload", "auth");
    hashMap.put("/user/index", "auth");
    hashMap.put("/user/public", "auth");
    hashMap.put("/user/collection", "auth");
    hashMap.put("/user/message", "auth");
    hashMap.put("/message/remove/", "auth");
    hashMap.put("/message/nums/", "auth");

    hashMap.put("/collection/remove/", "auth");
    hashMap.put("/collection/find/", "auth");
    hashMap.put("/collection/add/", "auth");

    hashMap.put("/post/edit", "auth");
    hashMap.put("/post/submit", "auth");
    hashMap.put("/post/delete", "auth");
    hashMap.put("/post/reply/", "auth");

//    hashMap.put("/**", "user");

    hashMap.put("/websocket", "anon");
    hashMap.put("/login", "anon");
    filterFactoryBean.setFilterChainDefinitionMap(hashMap);

    return filterFactoryBean;

  }

  @Bean
  public AuthFilter authFilter() {
    return new AuthFilter();
  }
}

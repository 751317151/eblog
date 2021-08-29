package com.blackstar.eblog.config;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author huah
 * @since 2021年08月28日
 */
@Configuration
@EnableTransactionManagement
@MapperScan("com.blackstar.eblog.mapper")
public class MyBatisPlusConfig {

  @Bean
  public PaginationInterceptor paginationInterceptor(){
    PaginationInterceptor paginationInterceptor = new PaginationInterceptor();
    return paginationInterceptor;
  }
}

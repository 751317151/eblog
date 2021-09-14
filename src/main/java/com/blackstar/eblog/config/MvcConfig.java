package com.blackstar.eblog.config;

import com.blackstar.eblog.common.lang.Consts;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author huah
 * @since 2021年09月01日
 */
@Configuration
public class MvcConfig implements WebMvcConfigurer {

  @Autowired
  Consts consts;

  @Bean
  ModelMapper modelMapper() {
    return new ModelMapper();
  }

  @Override
  public void addResourceHandlers(ResourceHandlerRegistry registry) {
    registry.addResourceHandler("/upload/avatar/**")
        .addResourceLocations("file:///" + consts.getUploadDir() + "/avatar/");
  }

}

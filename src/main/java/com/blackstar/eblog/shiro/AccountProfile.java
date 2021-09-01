package com.blackstar.eblog.shiro;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author huah
 * @since 2021年09月01日
 */
@Data
public class AccountProfile implements Serializable {

  private Long id;

  private String username;
  private String email;
  private String sign;

  private String avatar;
  private String gender;
  private Date created;

  public String getSex() {
    return "0".equals(gender) ? "女" : "男";
  }

}

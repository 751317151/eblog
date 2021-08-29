package com.blackstar.eblog.entity;

import lombok.Data;

import java.util.Date;

/**
 * @author huah
 * @since 2021年08月28日
 */
@Data
public class BaseEntity {

  private Long id;
  private Date created;
  private Date modified;

}

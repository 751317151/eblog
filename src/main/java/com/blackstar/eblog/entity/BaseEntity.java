package com.blackstar.eblog.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.util.Date;

/**
 * @author huah
 * @since 2021年08月28日
 */
@Data
public class BaseEntity {

  @TableId(value = "id",type = IdType.AUTO)
  private Long id;
  private Date created;
  private Date modified;

}

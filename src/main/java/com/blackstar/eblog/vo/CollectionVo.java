package com.blackstar.eblog.vo;

import com.blackstar.eblog.entity.MUserCollection;
import lombok.Data;

import java.util.Date;

/**
 * @author huah
 * @since 2021年09月05日
 */
@Data
public class CollectionVo extends MUserCollection {

  private String title;
  private Integer viewCount;
  private Integer commentCount;
  private Date collectDate;
}

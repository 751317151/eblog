package com.blackstar.eblog.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.blackstar.eblog.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author huah
 * @since 2021-08-28
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("m_user_collection")
public class MUserCollection extends BaseEntity {

    private static final long serialVersionUID = 1L;

    private Long userId;

    private Long postId;

    private Long postUserId;


}

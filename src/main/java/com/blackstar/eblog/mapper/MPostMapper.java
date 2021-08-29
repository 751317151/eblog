package com.blackstar.eblog.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.blackstar.eblog.entity.MPost;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.blackstar.eblog.vo.PostVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author huah
 * @since 2021-08-28
 */
@Component
public interface MPostMapper extends BaseMapper<MPost> {

  IPage<PostVo> selectPosts(Page page, @Param(Constants.WRAPPER)QueryWrapper wrapper);

  PostVo selectOnePost(@Param(Constants.WRAPPER)QueryWrapper<MPost> wrapper);
}

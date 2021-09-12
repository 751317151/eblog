package com.blackstar.eblog.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.blackstar.eblog.entity.MUserCollection;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.blackstar.eblog.vo.CollectionVo;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author huah
 * @since 2021-08-28
 */
public interface MUserCollectionMapper extends BaseMapper<MUserCollection> {

  IPage<CollectionVo> selectCollections(Page page, @Param(Constants.WRAPPER)QueryWrapper wrapper);
}

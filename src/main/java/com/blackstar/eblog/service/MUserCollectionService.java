package com.blackstar.eblog.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.blackstar.eblog.entity.MUserCollection;
import com.baomidou.mybatisplus.extension.service.IService;
import com.blackstar.eblog.vo.CollectionVo;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author huah
 * @since 2021-08-28
 */
public interface MUserCollectionService extends IService<MUserCollection> {

  IPage<CollectionVo> paging(Page page, Long userid, String order);
}

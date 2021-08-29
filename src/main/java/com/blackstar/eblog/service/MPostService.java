package com.blackstar.eblog.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.blackstar.eblog.entity.MPost;
import com.baomidou.mybatisplus.extension.service.IService;
import com.blackstar.eblog.vo.PostVo;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author huah
 * @since 2021-08-28
 */
public interface MPostService extends IService<MPost> {

  IPage paging(Page page, Long categoryId, Long userId, Integer level, Boolean recommand, String order);

  PostVo selectOnePost(QueryWrapper<MPost> wrapper);

  void initWeekRank();

  void incrCommentCountAndUnionForWeekRank(long postId, boolean isIncr);

  void putViewCount(PostVo vo);
}

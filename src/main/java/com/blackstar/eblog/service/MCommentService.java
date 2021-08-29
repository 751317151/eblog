package com.blackstar.eblog.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.blackstar.eblog.entity.MComment;
import com.baomidou.mybatisplus.extension.service.IService;
import com.blackstar.eblog.vo.CommentVo;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author huah
 * @since 2021-08-28
 */
public interface MCommentService extends IService<MComment> {

  IPage<CommentVo> paging(Page page, Long postId, Long userId, String order);
}

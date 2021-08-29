package com.blackstar.eblog.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.blackstar.eblog.entity.MComment;
import com.blackstar.eblog.mapper.MCommentMapper;
import com.blackstar.eblog.service.MCommentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.blackstar.eblog.vo.CommentVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author huah
 * @since 2021-08-28
 */
@Service
public class MCommentServiceImpl extends ServiceImpl<MCommentMapper, MComment> implements MCommentService {

  @Autowired
  MCommentMapper mCommentMapper;

  @Override
  public IPage<CommentVo> paging(Page page, Long postId, Long userId, String order) {
    return mCommentMapper.selectComments(page,new QueryWrapper<MComment>()
        .eq(postId != null, "post_id", postId)
        .eq(userId != null, "user_id", userId)
        .orderByDesc(order != null, order)
    );
  }
}

package com.blackstar.eblog.vo;

import com.blackstar.eblog.entity.MComment;
import lombok.Data;

@Data
public class CommentVo extends MComment {

    private Long authorId;
    private String authorName;
    private String authorAvatar;

}

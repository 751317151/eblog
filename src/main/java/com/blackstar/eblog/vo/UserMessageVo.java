package com.blackstar.eblog.vo;

import com.blackstar.eblog.entity.MUserMessage;
import lombok.Data;

@Data
public class UserMessageVo extends MUserMessage {

    private String toUserName;
    private String fromUserName;
    private String postTitle;
    private String commentContent;

}

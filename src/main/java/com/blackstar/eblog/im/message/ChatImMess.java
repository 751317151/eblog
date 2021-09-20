package com.blackstar.eblog.im.message;

import com.blackstar.eblog.im.vo.ImTo;
import com.blackstar.eblog.im.vo.ImUser;
import lombok.Data;

@Data
public class ChatImMess {

    private ImUser mine;
    private ImTo to;

}

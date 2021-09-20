package com.blackstar.eblog.im.message;

import com.blackstar.eblog.im.vo.ImMess;
import lombok.Data;

@Data
public class ChatOutMess {

    private String emit;
    private ImMess data;

}

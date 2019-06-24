package com.zoe.subscribe;

import lombok.extern.slf4j.Slf4j;

/**
 * @ClassName : Receiver
 * @Author : zoe
 * @Date : 2019/6/24 10:42
 *
 * 消息监听者
 */
@Slf4j
public class Receiver {

    public void receiverMessage(String message){
        log.info("监听者1收到消息:{}",message);
    }

    public void receiverMessage2(String message){
        log.info("监听者2收到消息:{}",message);
    }
}

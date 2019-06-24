package com.zoe.subscribe;

import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @ClassName : PubController
 * @Author : zoe
 * @Date : 2019/6/24 10:38
 *
 * 消息发送者
 */
@RestController
public class PubController {

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    private static AtomicInteger count = new AtomicInteger();

    @PostMapping("/message")
    public void sendMessage(){
        int i = count.incrementAndGet();
        stringRedisTemplate.convertAndSend("topic","消息" +i);
    }
}

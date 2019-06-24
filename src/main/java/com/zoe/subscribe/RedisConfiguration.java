package com.zoe.subscribe;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.listener.PatternTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;

/**
 * @ClassName : RedisConfiguration
 * @Author : zoe
 * @Date : 2019/6/24 10:45
 */
@Configuration
public class RedisConfiguration {

    /**
     * 消息适配器
     *
     * 绑定消息监听者和消息接收监听的方法,必须注入这个监听器,不然报错
     * @return MessageListenerAdapter
     */
    @Bean
    public MessageListenerAdapter listenerAdapter(){
        return new MessageListenerAdapter(new Receiver(),"receiverMessage");
    }

    @Bean
    public MessageListenerAdapter listenerAdapter1(){
        return new MessageListenerAdapter(new Receiver(),"receiverMessage2");
    }

    /**
     * 定义消息监听者容器
     * @param connectionFactory  连接工厂
     * @param listenerAdapter 消息处理器
     * @return RedisMessageListenerContainer
     */
    @Bean
    public RedisMessageListenerContainer container(RedisConnectionFactory connectionFactory,
                                                   MessageListenerAdapter listenerAdapter, MessageListenerAdapter listenerAdapter1){

        RedisMessageListenerContainer listenerContainer = new RedisMessageListenerContainer();
        listenerContainer.setConnectionFactory(connectionFactory);
        listenerContainer.addMessageListener(listenerAdapter,new PatternTopic("topic"));
        listenerContainer.addMessageListener(listenerAdapter1,new PatternTopic("topic"));
        return listenerContainer;
    }
}

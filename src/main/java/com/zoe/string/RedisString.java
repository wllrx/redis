package com.zoe.string;

import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName : RedisString
 * @Author : zoe
 * @Date : 2019/6/19 11:21
 */
@Component
public class RedisString {

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    /**
     * 新增一个字符串类型的值,key是键,value是值
     *
     * set(K key,V value)
     */
    public void set(){
        //存入永久数据
        stringRedisTemplate.opsForValue().set("test2","1");
        //存入数据设置过期时间
        stringRedisTemplate.opsForValue().set("test1","hello redis",1000, TimeUnit.SECONDS);
    }

    /**
     * 批量插入,key值存在会覆盖原值
     *
     * multiSet(Map<? extends K,? extends V> map)
     */
    public void multiSet(){
         Map<String,String> map = new HashMap<>(16);
         map.put("testMultiSet1","value1");
         map.put("testMultiSet2","value2");
         stringRedisTemplate.opsForValue().multiSet(map);
     }

    /**
     * 批量插入,如果里面的所有key都不存在,则全部插入,返回true,如果其中一个存在,全不插入,返回false
     *
     *  multiSetIfAbsent(Map<? extends K,? extends V> map)
     */
    public void multiSetIfAbsent(){
         Map<String,String> map = new HashMap<>(16);
         map.put("testMultiSet3","value3");
         map.put("testMultiSet4","value4");
         Boolean absent = stringRedisTemplate.opsForValue().multiSetIfAbsent(map);
         System.out.println(absent);
     }

    /**
     * 如果不存在插入,返回true插入成功,false失败
     *
     * setIfAbsent(K key,V value)
     */
    public void setIfAbsent(){
        Boolean absent = stringRedisTemplate.opsForValue().setIfAbsent("test","hello redis");
        System.out.println(absent);
     }


    /**
     * 获取key 不存在返回null
     *
     * get(Object key)
     */
    public void get(){
         System.out.println(stringRedisTemplate.opsForValue().get("testMultiSet1"));
     }

    /**
     * 批量获取,key不存在返回null
     *
     * multiGet(Collection<K> keys)
     */
    public void multiGet(){
         List<String> list = stringRedisTemplate.opsForValue().multiGet(Arrays.asList("test"));
     }

    /**
     * 获取指定字符串的长度
     *
     * size(K key)
     */
    public void size(){
        Long size = stringRedisTemplate.opsForValue().size("test");
        System.out.println(size);
     }

    /**
     * 在原有的值基础上新增字符串到末尾
     *
     * append(K key, String value)
     */
    public void append(){
        Integer append = stringRedisTemplate.opsForValue().append("test3","database");
         System.out.println(append);
     }

    /**
     * 获取原来的key键对应的值并重新赋值
     *
     * getAndSet(K key, V value)
     */
    public void getAndSet(){
        String set = stringRedisTemplate.opsForValue().getAndSet("test","set test");
         System.out.println(set);
     }

    /**
     *获取指定key的值进行减1,如果value不是integer类型,会抛异常,如果key不存在会创建一个,默认为0
     *
     * decrement(k key)
     */
    public void decrement(){
        stringRedisTemplate.opsForValue().decrement("test2");
        stringRedisTemplate.opsForValue().decrement("test1");
     }

    /**
     * 获取指定key的值进行加1,如果value不是integer类型,会抛异常,如果key不存在会创建一个,默认为0
     *
     * decrement(k key)
     */
    public void increment(){
        stringRedisTemplate.opsForValue().increment("test2");
        stringRedisTemplate.opsForValue().increment("test1");
     }

    /**
     * 删除指定key,成功返回true,失败返回false
     *
     * delete(K key)
     */
    public void delete(){
        Boolean delete = stringRedisTemplate.opsForValue().getOperations().delete("test1");
         System.out.println(delete);
     }

    /**
     * 删除多个key,返回删除key的个数
     *
     * delete(K ...keys)
     */
    public void deleteMulti(){
        Long delete = stringRedisTemplate.opsForValue().getOperations().delete(Arrays.asList("test1","test2"));
         System.out.println(delete);
     }
}

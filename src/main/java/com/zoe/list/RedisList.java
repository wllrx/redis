package com.zoe.list;

import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName : RedisList
 * @Author : zoe
 * @Date : 2019/6/21 16:57
 */
@Component
public class RedisList {

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    /**
     * 在变量左边添加元素值,如果key不存在会新建,添加成功返回添加后的总个数
     *
     * leftPush(K key,V value)
     */
    public void leftPush(){
        Long along = stringRedisTemplate.opsForList().leftPush("list","a");
        System.out.println(along);
    }

    /**
     * 批量向左边添加参数元素,key不存在会创建,添加成功返回添加后的总个数
     *
     * leftPushAll(K key,V... values)
     */
    public void leftPushAll(){
        Long pushAll = stringRedisTemplate.opsForList().leftPushAll("list","1","2","3","4");
        System.out.println(pushAll);
    }

    /**
     * 向集合最右边添加元素,如果key不存在新建,添加成功返回添加后的总个数
     *
     * rightPush(K key,V value)
     */
    public void rightPush(){
        Long right = stringRedisTemplate.opsForList().rightPush("list2","7");
        System.out.println(right);
    }

    /**
     * 向右边批量添加元素,返回当前集合元素的总个数
     *
     * rightPushAll(K key,V ...values)
     */
    public void rightPushAll(){
        Long along = stringRedisTemplate.opsForList().rightPushAll("list2","a","s","d");
        System.out.println(along);
    }
    /**
     * 向已存在的集合中添加元素.返回集合总元素的个数
     *
     * leftPushIfPresent(K key, V value)
     */
    public void leftPushIfPresent(){
        Long along = stringRedisTemplate.opsForList().leftPushIfPresent("list","h");
        System.out.println(along);
    }

    /**
     * 向已存在的集合中添加元素,返回总元素的个数
     *
     * rightPushIfPresent
     */
    public void rightPushIfPresent(){
        Long along = stringRedisTemplate.opsForList().rightPushIfPresent("list","f");
        System.out.println(along);
    }

    /**
     * 获取集合长度
     *
     * size(K key)
     */
    public void size(){
        Long size = stringRedisTemplate.opsForList().size("list2");
        System.out.println(size);
    }

    /**
     * 移除集合中的左边第一个元素,返回移除的元素,如果元素为空,该集合会自动删除
     *
     * leftPop(K key)
     */
    public void leftPop(){
        String pop = stringRedisTemplate.opsForList().leftPop("list2");
        System.out.println(pop);
    }

    /**
     * 在等待时间里移除集合中的左边的元素,如果超时没有元素则退出
     *
     * leftPop(K key,long timeout,TimeUnit unit)
     */
    public void leftPopWait(){
        String leftPop = stringRedisTemplate.opsForList().leftPop("list2",10, TimeUnit.SECONDS);
        System.out.println(leftPop);
    }

    /**
     * 移除集合中右边的元素。返回删除的元素，如果元素为空，该集合会自动删除
     *
     * rightPop(K key)
     */
    public void rightPop() {
        String pop = stringRedisTemplate.opsForList().rightPop("list2");
        System.out.println(pop);
    }

    /**
     * 移除集合中右边的元素在等待的时间里，如果超过等待的时间仍没有元素则退出。
     *
     * rightPop(K key, long timeout, TimeUnit unit)
     */
    public void rightPopWait() {
        String pop = stringRedisTemplate.opsForList().rightPop("list2", 10, TimeUnit.SECONDS);
        System.out.println(pop);
    }

    /**
     * 移除第一个集合右边的元素,插入第二个集合左边插入这个元素
     *
     * rightPopAndLeftPush(K sourceKey, K destinationKey)
     */
    public void rightPopAndLeftPush(){
        String s = stringRedisTemplate.opsForList().rightPopAndLeftPush("list2","list3");
    }

    /**
     * 在集合指定位置插入元素,如果指定位置已有元素,则覆盖,没有则新增,超过集合下标则会报错
     *
     * set(K key,long index,V value)
     */
    public void set(){
        stringRedisTemplate.opsForList().set("list2",2,"q");
    }

    /**
     * 从存储在键中的列表中删除等于值的元素的第一个计数事件。count> 0：删除等于从左到右移动的值的第一个元素；
     * count< 0：删除等于从右到左移动的值的第一个元素；count = 0：删除等于value的所有元素
     *
     * remove(K key, long count, Object value)
     */
    public void remove(){
        Long remove = stringRedisTemplate.opsForList().remove("list2",2,"q");
    }

    /**
     * 截取集合元素长度，保留长度内的数据。
     *
     * trim(K key, long start, long end)
     */
    public void trim() {
        stringRedisTemplate.opsForList().trim("list2", 0, 3);
    }

    /**
     * 获取集合指定位置的值。
     *
     * index(K key, long index)
     */
    public void index() {
        Object listValue = stringRedisTemplate.opsForList().index("list2", 3);
        System.out.println(listValue);
    }

    /**
     * 获取指定区间的值。
     *
     * range(K key, long start, long end)
     */
    public void range() {
        List<String> list = stringRedisTemplate.opsForList().range("list", 0, -1);
        System.out.println(list);
    }

    /**
     * 删除指定集合,返回true删除成功
     *
     * delete(K key)
     */
    public void delete() {
        Boolean delete = stringRedisTemplate.opsForList().getOperations().delete("list2");
        System.out.println(delete);
    }
}


package cn.tycoding.controller;

import cn.tycoding.Util.RedisUtil;
import cn.tycoding.service.UserService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/Redis")
public class RedisTest {
    //注入service
    @Autowired
    private UserService userService;
    @Autowired
    private RedisTemplate redisTemplate;

    //查询
    @RequestMapping(value = "/query")
    public Object query(String key) {
        return redisTemplate.opsForValue().get(key);
//        return RedisUtil.hasKey(key);
    }

    //写入
    @RequestMapping(value = "/insert")
    public void insert(String key,String value) {
        redisTemplate.opsForValue().set(key, value);
//        return RedisUtil.set(key,value);
    }

    //删除
    @RequestMapping(value = "/delete")
    public void delPattern(String key) {
        redisTemplate.delete(key);
//        return RedisUtil.delPattern(key);
    }

    @Test
    public void setValue(){
        redisTemplate.boundValueOps("time").set("2020年3月17日 20:15:29");
    }

}

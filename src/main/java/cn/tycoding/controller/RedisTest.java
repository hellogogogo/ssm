//package cn.tycoding.controller;
//
//import cn.tycoding.Util.RedisUtil;
//import cn.tycoding.service.UserService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.RequestMapping;
//
//
//@Controller
//@RequestMapping("/Redis")
//public class RedisTest {
//    //注入service
//    @Autowired
//    private UserService userService;
//
//
//
//    //查询
//    @RequestMapping(value = "/query")
//    public Object query(String key) {
//        RedisUtil.getString(key);
//
//        return null;
//    }
//
//    //写入
//    @RequestMapping(value = "/insert")
//    public Object insert(String key,String value) {
//        RedisUtil.setString(key,value);
//        return null;
//    }
//
//    //更新
//    @RequestMapping(value = "/update")
//    public Object update() {
//        return null;
//    }
//
//    //删除
//    @RequestMapping(value = "/delete")
//    public Object delPattern(String key) {
//        RedisUtil.del(key);
//        return null;
//    }
//
//}

package cn.tycoding.controller;

import cn.tycoding.pojo.User;
import cn.tycoding.service.CustomerService;
import cn.tycoding.service.UserService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;

/**
 * 用户的控制层
 *
 * @author tycoding
 * @date 18-4-7下午9:00
 */
@EnableAsync //开启异步任务支持
@Controller
public class TestAsyn {
    Logger logger = Logger.getLogger(TestAsyn.class);
    //注入service
    @Autowired
    private CustomerService customerService;

    /**
     * 测试spring多线程
     */
    @RequestMapping(value = "/Asyn")
    public String login() {
        for(int i=0;i<10;i++){
            customerService.sysn01(i);
            customerService.sysn02(i);
        }
        return "成功";
    }

}

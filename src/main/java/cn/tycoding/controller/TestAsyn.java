package cn.tycoding.controller;

import cn.tycoding.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.logging.Logger;

/**
 * 用户的控制层
 * @author tycoding
 * @date 18-4-7下午9:00
 */

@Controller
@EnableAsync //开启异步任务支持
public class TestAsyn{
    Logger logger = Logger.getLogger(String.valueOf(TestAsyn.class));
    //注入service
    @Autowired
    private CustomerService customerService;
    /**
     * 测试spring多线程
     */
    @RequestMapping(value = "/Asyn")
    public String login() {
        for(int i=0;i<1000;i++){
            customerService.sysn01(i);
            customerService.sysn02(i);
        }
        return "成功";
    }
}

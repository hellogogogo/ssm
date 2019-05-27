package cn.tycoding.task;

import cn.tycoding.pojo.Customer;
import cn.tycoding.service.CustomerService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * 测试定时任务
 */
@Component
public class CreateUserTask {
    Logger logger = Logger.getLogger(CreateUserTask.class);

    //注入service
    @Autowired
    private CustomerService customerService;

//    @Scheduled(cron = "0/30 * * * * ? ") // 间隔5秒执行
    private void createUser() throws Exception{
         logger.debug("定时任务开始...");

         Customer temp = new Customer();
         temp.setName("abc");
         temp.setTelephone("110");
         temp.setAddress("asd");
         temp.setRemark("asd");
         customerService.create(temp);

         logger.debug("定时签收任务开始...");
     }

}

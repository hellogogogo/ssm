package cn.tycoding.controller;

import cn.tycoding.pojo.User;
import cn.tycoding.service.UserService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;

/**
 * 用户的控制层
 *
 * @author tycoding
 * @date 18-4-7下午9:00
 */
@Controller
@RequestMapping("/user")
public class UserController {
    Logger logger = Logger.getLogger(UserController.class);


    //注入service
    @Autowired
    private UserService userService;
    //读取配置文件的值
    @Value("${shuaige}")
    private String shuaige;

    /**
     * 用户登录
     */
    @RequestMapping(value = "/login")
    public String login(HttpServletRequest request, @RequestParam String username, @RequestParam String password, Model model) {
        User user = userService.login(username);
        if (user != null) {
            if (user.getPassword().equals(password)) {
                logger.info("-----------开始-------------"+shuaige);
                logger.info("用户"+username+"在"+new Date()+"登录");
                logger.info("-------------结束-----------");
                HttpSession session = request.getSession();
                session.setAttribute("currentUser",user);
                //登录成功
                return "page/page";
            } else {
                model.addAttribute("message", "登录失败");
                return "page/loginInfo";
            }
        } else {
            model.addAttribute("message", "你输入的用户名或密码有误");
            return "page/loginInfo";
        }
    }

    /**
     * 回到登录页
     */
    @RequestMapping(value="/index")
    public String index(){
        return "index";
    }
}

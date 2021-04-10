package cn.tycoding.controller;

import javax.servlet.http.*;

import cn.tycoding.controller.jwt.JWTUtil;
import cn.tycoding.pojo.User;
import com.auth0.jwt.internal.org.bouncycastle.asn1.ocsp.ResponseData;
import org.apache.log4j.Logger;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class HandleInterceptor implements HandlerInterceptor {

    public static Logger log = Logger.getLogger(HandleInterceptor.class.getName());

    /**
     * 在Handle执行前 执行
     * return true 继续执行 false不继续执行
     */
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        String aaa = UserController.getIp(request);
        String bbb = request.getServerName();
//        log.info("HandleInterceptor1 ...... preHandle");
        response.setCharacterEncoding("utf-8");
        Cookie[] cookies = request.getCookies();
        Cookie cookie = cookies[0];
        String sessionId = cookie.getValue();
//        String sessionId = "EE43F235FCC5998C4DA0F62DE87087C9";
        if(null != sessionId) {
            HttpSession session = request.getSession();
            if(sessionId.equals(session.getId())){//session相等
                try {
                    User user = JWTUtil.unsign(session.getAttribute("token").toString(), User.class);
                    if(user !=null){
                        return true;
                    }else{
                        response.sendRedirect("/ssm");
                        return false;
                    }
                }catch (Exception e){//解密失败
                    response.sendRedirect("/ssm");
                    return false;
                }
            }else {
                response.sendRedirect("/ssm");
                return false;
            }
        } else {
            response.sendRedirect("/ssm");
            return false;
        }
    }

    /**
     * 在modelAndView 执行前 执行
     */
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
                           ModelAndView modelAndView) throws Exception {
        log.info("HandleInterceptor1 ...... postHandle");

    }

    /**
     * 在 Handle 执行后 执行
     */
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
            throws Exception {
        log.info("HandleInterceptor1 ...... afterCompletion");
    }

}
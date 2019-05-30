package cn.tycoding.controller;

import cn.tycoding.pojo.User;
import com.mysql.jdbc.StringUtils;
import org.springframework.http.HttpRequest;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @author:ly
 * @createDate 2019-04-2019/4/10 0:07
 */
public class FilterTest implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
//        HttpServletRequest request = (HttpServletRequest)servletRequest;
//        HttpServletResponse response = (HttpServletResponse)servletResponse;
//        HttpSession session = request.getSession();
//        User user = (User)session.getAttribute("currentUser");
//        String path=request.getServletPath();
//        if(user==null && (path.indexOf("login")<0)){
//            response.sendRedirect("/ssm");
//        }else{
//            filterChain.doFilter(servletRequest, servletResponse);
//        }
    }

    @Override
    public void destroy() {

    }
}

package cn.tycoding.service.impl;

import cn.tycoding.mapper.UserMapper;
import cn.tycoding.pojo.User;
import cn.tycoding.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.parser.Entity;
import java.util.List;

/**
 * @author tycoding1
 * @date 18-4-7下午9:09
 */
@Service
public class UserServiceImpl implements UserService {

    //注入
    @Autowired
    private UserMapper userMapper;

    /**
     * 用户登录的方法
     */
    public User login(String username) {
        return userMapper.login(username);
    }

    @Override
    public void saveUpload(String s) {
        userMapper.saveUpload(s);
    }

    @Override
    public void updateLoad(String s) {
        userMapper.updateLoad(s);
    }

    @Override
    public String getUpLoad(String s) {
        return userMapper.getUpLoad(s);
    }

    public List<User> findAll() {
        return null;
    }

    public User findById(Long id) {
        return null;
    }

    public void create(User user) {

    }

    public void delete(Long id) {

    }

    public void update(User user) {

    }

}

package cn.tycoding.mapper;

import cn.tycoding.pojo.User;

import java.util.List;

/**
 * @author tycoding
 * @date 18-4-7下午9:10
 */
public interface UserMapper {

    User login(String username);

    void saveUpload(String s);

    List<User> findAll();

    int updateForBalance(User user);
}

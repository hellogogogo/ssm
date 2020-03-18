package cn.tycoding.service;

import cn.tycoding.pojo.User;

import java.util.List;

/**
 * @author tycoding
 * @date 18-4-7下午9:09
 */
public interface UserService extends BaseService<User> {

    User login(String username);

    void saveUpload(String s);

    void updateLoad(String s);

    String getUpLoad(String s);

}

package cn.tycoding.mapper;

import cn.tycoding.pojo.User;

/**
 * @author tycoding
 * @date 18-4-7下午9:10
 */
public interface UserMapper {

    User login(String username);

    void saveUpload(String s);

    void updateLoad(String s);

    String getUpLoad(String s);
}

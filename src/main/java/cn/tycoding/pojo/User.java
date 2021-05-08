package cn.tycoding.pojo;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 这是用户登录的JavaBean
 * @author tycoding
 * @date 18-4-7下午7:28
 */
@Data
public class User implements Serializable {

    //用户id
    private Long id;
    //用户登录名
    private String username;
    //用户密码
    private String password;

    //余额
    private BigDecimal balance;
    //乐观锁版本号 默认0
    private Integer version;


}

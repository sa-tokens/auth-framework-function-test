package com.pj.mockdao;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @author click33
 * @since 2024/8/6
 */
@Component
public class SysUserDao {

    List<SysUser> userList = new ArrayList<>();
    public SysUserDao() {
        userList.add(new SysUser(10001, "zhang", "123456", 18));
        userList.add(new SysUser(10002, "li", "123456", 20));
        userList.add(new SysUser(10003, "wang", "123456", 22));
    }

    /**
     * 根据用户 id 查找用户
     * @param id 用户 id
     * @return 用户
     */
    public SysUser findById(long id) {
        return userList.stream()
                .filter(user -> user.getId() == id)
                .findFirst()
                .orElse(null);
    }

    /**
     * 根据用户名查找用户
     * @param username 用户名
     * @return 用户
     */
    public SysUser findByUsername(String username) {
        return userList.stream()
                .filter(user -> user.getUsername().equals(username))
                .findFirst()
                .orElse(null);
    }



}
package com.pj.springsecurity;

import com.pj.mockdao.SysUser;
import com.pj.mockdao.SysUserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Component;

/**
 * 自定义 SpringSecurity UserDetails 管理器
 *
 * @author click33
 * @since 2024/8/8
 */
@Component
public class CustomUserDetailsManager implements UserDetailsManager {

    @Autowired
    SysUserDao sysUserDao;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        SysUser sysUser = sysUserDao.findByUsername(username);
        if(sysUser == null){
            throw new UsernameNotFoundException("用户不存在");
        }

        // 不可以同时返回 roles 和 authorities，因为会相互覆盖，SpringSecurity 源码有bug
        return User.withUsername(sysUser.getUsername())
                .password("{noop}" + sysUser.getPassword())
//                .roles("admin", "super-admin", "ceo")
                .authorities("user:add", "user:delete", "user:update")
                .build();
    }

    @Override
    public void createUser(UserDetails user) {

    }

    @Override
    public void updateUser(UserDetails user) {

    }

    @Override
    public void deleteUser(String username) {

    }

    @Override
    public void changePassword(String oldPassword, String newPassword) {

    }

    @Override
    public boolean userExists(String username) {
        return false;
    }

}

package com.pj.shiro;

import com.pj.mockdao.SysUser;
import com.pj.mockdao.SysUserDao;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;

/**
 * 自定义 Realm
 * @author click33
 * @since 2024/8/6
 */
public class MyRealm extends AuthorizingRealm {

    @Autowired
    private SysUserDao sysUserDao;

    // 加载用户信息
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) {
        String username = (String)token.getPrincipal();
        SysUser sysUser = sysUserDao.findByUsername(username);
        if(sysUser == null){
            return null;
        }

        return new SimpleAuthenticationInfo(
                sysUser,
                sysUser.getPassword(),
//                ByteSource.Util.bytes("abc"),
                getName()
        );
    }

    // 加载权限信息
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        authorizationInfo.addRoles(Arrays.asList("admin", "super-admin", "ceo"));
        authorizationInfo.addStringPermissions(Arrays.asList("user:add", "user:delete", "user:update"));
        return authorizationInfo;
    }

}


package com.jy.shiro;

import com.jy.model.menu.Menu;
import com.jy.model.role.Role;
import com.jy.model.user.User;
import com.jy.service.menu.MenuService;
import com.jy.service.role.RoleService;
import com.jy.service.user.UserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class RealmConfig extends AuthorizingRealm {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private MenuService menuService;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        //用于授权 （把用户的权限告诉shiro）
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();

        //角色{下面为列子}
//        Set<String> roleSet = new HashSet<>();
//        roleSet.add("normal");

        //从数据库查询出当前用户对应的角色信息列表
        Role role = new Role();
        //获取当前用户信息
        User user = (User)  principalCollection.getPrimaryPrincipal();
        role.setUserID(user.getUserID());
        Set<String> roleSet = roleService.selectRoleListByUserID(role);
        authorizationInfo.setRoles(roleSet);

        //权限
//        Set<String> permSet = new HashSet<>();
//        permSet.add("fff");

        Menu menu = new Menu();
        menu.setUserID(user.getUserID());
        Set<String> permSet = menuService.selectMenuListByUserID(menu);
        authorizationInfo.setStringPermissions(permSet);

        return authorizationInfo;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        //用于验证（登陆）
        String userName = (String) authenticationToken.getPrincipal();
        String userPwd = new String((char[]) authenticationToken.getCredentials());
        User u = new User();
        u.setUserAccount(userName);
        u.setUserPwd(userPwd);
        //调用service进行登陆
        Map<String, Object> map = userService.login(u);
        if (map.get("code").toString().equals("1")) {
            User user = (User) map.get("userInfo");
            //登陆成功
            return new SimpleAuthenticationInfo(
                    // 这里传入的是user对象，比对的是用户名，直接传入用户名也没错，但是在授权部分就需要自己重新从数据库里取权限
                    user,
                    // 密码
                    user.getUserPwd(),
                    // salt = username + salt
                    ByteSource.Util.bytes(user.getUserAccount()),
                    // realm name
                    getName()
            );
        } else {
            return null;
        }
    }
}

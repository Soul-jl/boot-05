package com.jy.shiro;

import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authz.AuthorizationFilter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class CustomRolesAuthorizationFilter extends AuthorizationFilter {

    @Override
    protected boolean isAccessAllowed(ServletRequest servletRequest, ServletResponse servletResponse, Object o) throws Exception {
        Subject subject = getSubject(servletRequest, servletResponse);
        //这个数组对应的是工厂中设置的roles后面的中括号中的值
        //与我们授权方法中的Set集合一毛钱关系都没有
        String[] rolesArray = (String[]) o;
        //没有角色限制，有权限访问
        if (rolesArray == null || rolesArray.length == 0) {
            // 无指定角色时，无需检查，允许访问
            return true;
        }
        for (String roleName : rolesArray) {
            if (subject.hasRole(roleName)) {
                return true;
            }
        }
        return false;
    }
}

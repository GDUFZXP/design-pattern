package com.orig.design.proxy;

/**
 * 用户角色服务类
 */
public class UserRoleService {
    public void addUserRole(String user, String role){
        System.out.println(String.format("add user:%s role:%s success",user, role));
    }
}

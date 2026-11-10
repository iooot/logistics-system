package com.web.controller;

public interface UserInfoService {

    String showUserInfoList(String content, String page);

    //禁用用户
    String userInfodel(String id);

    //用户注册与编辑
    String updateUserinfo(String id, String logname, String username, String password, String phone, String email, String bem,
                          String sex, String homeAddress,String role);

    //禁用用户列表
    String delUserInfoList(String content, String page);

    //恢复禁用账号
    String resetUserinfodel(String id);

    String showArchivesUserInfoList(String content, String page);

    String singleUsr(String id);

    String showUserInfoList1(String content, String page);
}

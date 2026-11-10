package com.web.filter;


import com.domain.User;

import javax.servlet.http.HttpSession;

public class CommUtil {


    //判断是否开启redis以及登录账号
    public static boolean permissionAuthentication(HttpSession session) {
        boolean b = false;
        User u = (User) session.getAttribute("user");
        if (u != null) {
            b = true;
        }
        //redis

        return b;

    }
}

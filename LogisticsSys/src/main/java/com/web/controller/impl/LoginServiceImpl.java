package com.web.controller.impl;

import common.SqlHelper.DataService;
import common.usingtool.BeanUtil;
import common.usingtool.ConfigDataService;
import common.usingtool.MD5Utils;
import com.domain.User;
import com.web.controller.LoginService;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

public class LoginServiceImpl implements LoginService {

    public Boolean authentication(HttpSession session, String uname, String pwd) {
        Boolean b = false;
        User usr = new User();
        String password = MD5Utils.getMD5Str(pwd);
        String userLogStr = "SELECT * FROM sys_fuc_users where logname =? and pwd=? and rowstate = '1'";
        List<Map<String, Object>> dataTable = DataService.getDataTable(userLogStr, new String[]{uname, password});
        int size = dataTable.size();
        if (size > 0) {
            Map<String, Object> map = dataTable.get(0);
            BeanUtil.loadEntity(usr, map);
            session.setAttribute("user", usr);
            b = true;
        }
        return b;
    }
}

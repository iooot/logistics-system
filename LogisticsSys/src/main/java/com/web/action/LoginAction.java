package com.web.action;

import common.SqlHelper.DataService;
import common.usingtool.ConfigDataService;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.web.controller.LoginService;
import com.web.controller.impl.LoginServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/syslogin")
@Component
public class LoginAction {
    LoginService loginService = new LoginServiceImpl();

    @RequestMapping("/sysUserLogin")
    //系统登录
    public String sysUserLogin(HttpServletRequest request, String uname, String pwd) {
        HttpSession session = request.getSession();
        String returnStr = "0";
        Boolean b = loginService.authentication(session, uname, pwd);
        if (b) {
            returnStr = "1";
        }
        return returnStr;
    }


    @RequestMapping("/systemName")
    public String systemName(HttpServletRequest request) {
        String systemInfo = "SELECT sysname FROM systeminfo";
        String systemName = DataService.getFirstValue(systemInfo, new String[]{});
        return systemName;
    }

}

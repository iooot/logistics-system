package com.web.action;

import com.web.Application;
import common.usingtool.MD5Utils;
import com.domain.User;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.web.controller.UserInfoService;
import com.web.controller.impl.UserInfoServiceImpl;
import com.web.filter.CommUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/user")
@Component
public class UserAction {
    //系统管理→用户管理
    @RequestMapping("/userlist")
    public String sysUserLogin(HttpServletRequest request, String content, String page) {
        HttpSession session = request.getSession();
        String returnStr = "";
        boolean b = CommUtil.permissionAuthentication(session);
        if (b) {
            UserInfoService userInfoService = new UserInfoServiceImpl();
            returnStr = userInfoService.showUserInfoList(content, page);
        }
        return returnStr;
    }

    @RequestMapping("/uinfo1")//用户
    public String sysUserLogin1(HttpServletRequest request, String content, String page) {
        HttpSession session = request.getSession();
        String returnStr = "";
        boolean b = CommUtil.permissionAuthentication(session);
        if (b) {
            UserInfoService userInfoService = new UserInfoServiceImpl();
            returnStr = userInfoService.showUserInfoList1(content, page);
        }
        return returnStr;
    }


    //系统管理→用户管理→删除
    @RequestMapping("/userinfodel")
    public String userInfodel(HttpServletRequest request, String id) {
        HttpSession session = request.getSession();
        String returnStr = "0";
        boolean b = CommUtil.permissionAuthentication(session);
        if (b) {
            UserInfoService userInfoService = new UserInfoServiceImpl();
            returnStr = userInfoService.userInfodel(id);
            Application.initUserList();
        }
        return returnStr;
    }


    @RequestMapping("/updateUserinfo")//用户
    public String updateUserinfo(HttpServletRequest request, String id, String logname, String username,
                                 String pwd, String phone, String email, String bem, String sex, String homeAddress, String role) {
        UserInfoService userInfoService = new UserInfoServiceImpl();
        String password = MD5Utils.getMD5Str(pwd);
        String returnStr = userInfoService.updateUserinfo(id, logname, username, password, phone, email, bem, sex, homeAddress, role);

        Application.initUserList();
        return returnStr;
    }

    //系统管理→用户管理→禁用账号列表
    @RequestMapping("/uinfoDel")//用户
    public String uinfoDel(HttpServletRequest request, String content, String page) {
        HttpSession session = request.getSession();
        String returnStr = "";
        boolean b = CommUtil.permissionAuthentication(session);
        if (b) {
            UserInfoService userInfoService = new UserInfoServiceImpl();
            returnStr = userInfoService.delUserInfoList(content, page);
        }
        return returnStr;
    }


    @RequestMapping("/resetUserinfodel")//用户
    public String resetUserinfodel(HttpServletRequest request, String id) {
        HttpSession session = request.getSession();
        String returnStr = "0";
        boolean b = CommUtil.permissionAuthentication(session);
        if (b) {
            UserInfoService userInfoService = new UserInfoServiceImpl();
            returnStr = userInfoService.resetUserinfodel(id);
            Application.initUserList();
        }
        return returnStr;
    }


    @RequestMapping("/archivesList")//用户
    public String archivesList(HttpServletRequest request, String content, String page) {
        HttpSession session = request.getSession();
        String returnStr = "";
        boolean b = CommUtil.permissionAuthentication(session);
        if (b) {
            UserInfoService userInfoService = new UserInfoServiceImpl();
            returnStr = userInfoService.showArchivesUserInfoList(content, page);
        }
        return returnStr;
    }

    @RequestMapping("/singleUsr")
    public String singleUsr(HttpServletRequest request) {
        HttpSession session = request.getSession();
        String returnStr = "";
        User usr = (User) session.getAttribute("user");
        boolean b = CommUtil.permissionAuthentication(session);
        if (b) {
            UserInfoService userInfoService = new UserInfoServiceImpl();
            returnStr = userInfoService.singleUsr(usr.getId());
            Application.initUserList();
        }
        return returnStr;
    }
}

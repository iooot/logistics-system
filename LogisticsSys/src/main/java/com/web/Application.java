package com.web;


import common.SqlHelper.DataService;
import common.usingtool.BeanUtil;
import com.domain.User;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 引导类，springboot的入口,运行后就可以访问项目
 */
@SpringBootApplication
public class Application {
    public static List<String> saList;
    public static List<User> uList;

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
        initUserList();
    }


    //初始化用户列表
    public static void initUserList() {
        User usr;
        if (uList == null) {
            uList = new ArrayList<>();
        } else {
            uList.clear();
        }
        String userSql = "SELECT * FROM sys_fuc_users where rowstate = '1'";
        List<Map<String, Object>> maps = DataService.getDataTable(userSql, new String[]{});
        for (Map<String, Object> map : maps) {
            usr = new User();
            BeanUtil.loadEntity(usr, map);
            uList.add(usr);
        }

        if (saList == null) {
            saList = new ArrayList<>();
        } else {
            saList.clear();
        }
        saList.add("3f462bb8-cfe3-11ed-8c25-00e04cf193f7");
        String adminSql = "SELECT * FROM sys_fuc_users where rowstate = '4'";
        List<Map<String, Object>> mapss = DataService.getDataTable(adminSql, new String[]{});
        for (Map<String, Object> map : mapss) {
            usr = new User();
            BeanUtil.loadEntity(usr, map);
            saList.add(usr.getId());
        }
    }

    public static String getUid(String uname) {
        for (User u : uList) {
            if (u.getUsername().equals(uname)) {
                return u.getId();
            }
        }
        return "";
    }


    public static String getUname(String id) {
        for (User u : uList) {
            if (u.getId().equals(id)) {
                return u.getUsername();
            }
        }
        return "";
    }


    public static String getUTel(String uname) {
        for (User u : uList) {
            if (u.getUsername().equals(uname)) {
                return u.getPhone();
            }
        }
        return "";
    }
}

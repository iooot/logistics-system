package com.web.controller.impl;

import common.SqlHelper.DataService;
import common.usingtool.BeanUtil;
import common.usingtool.ConfigDataService;
import common.usingtool.Json;
import com.domain.User;
import com.domain.UserPageListInfo;
import com.web.controller.UserInfoService;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class UserInfoServiceImpl implements UserInfoService {

    @Override
    public String showUserInfoList(String content, String page) {
        //每页多少条数据
        double pageCount = Double.valueOf(ConfigDataService.getValue("pageCount"));
        User usr;
        UserPageListInfo userPageListInfo = new UserPageListInfo();

        //计划进入第几页
        userPageListInfo.setCurrentPage(page);

        //每页数据量
        userPageListInfo.setPageCount(String.valueOf((int) pageCount));
        List<User> list = new ArrayList<>();
        String userPageListInfoSql = ConfigDataService.getValue("userPageListInfoList")
                .replace("{1}", content);

        int endIndex = userPageListInfoSql.indexOf("FROM");

        //总数据量
        String countSql = "SELECT COUNT(*) " + userPageListInfoSql.substring(endIndex, userPageListInfoSql.length());

        String count = DataService.getFirstValue(countSql, new String[]{});
        userPageListInfo.setTotalCount(count);

        //总页数
        double countInt = Double.valueOf(count);
        if (countInt <= pageCount) {//总数据量小于每页数量
            userPageListInfo.setTotalPageCount("1");
        } else {
            double totalPageCountd = countInt / pageCount;
            int totalPageCounti = (int) Math.ceil(totalPageCountd);
            userPageListInfo.setTotalPageCount(String.valueOf(totalPageCounti));
        }

        //展示的数据
        int num = 0;
        if (Integer.valueOf(page) > 1) {
            int p = Integer.valueOf(page);
            num = (p - 1) * (int) pageCount;
        }
        userPageListInfoSql = userPageListInfoSql + " limit " + num + "," + ((int) pageCount);
        List<Map<String, Object>> maps = DataService.getDataTable(userPageListInfoSql, new String[]{});
        for (Map<String, Object> map : maps) {
            usr = new User();
            BeanUtil.loadEntity(usr, map);
            list.add(usr);
        }
        userPageListInfo.setList(list);
        String jsonStr = Json.getJsonStr(userPageListInfo);
        return jsonStr;
    }

    @Override
    public String userInfodel(String id) {
        String userInfodel = "update sys_fuc_users set rowstate = '2' WHERE id = ?";
        int execute = DataService.execute(userInfodel, new String[]{id});
        String restr = String.valueOf(execute);
        return restr;
    }

    @Override
    //用户注册与编辑
    public String updateUserinfo(String id, String logname, String username, String password, String phone,
                                 String email, String bem, String sex, String homeAddress, String role) {
        int execute = 0;
        String executeSql;
        if (id == null || id == "" || id.equals("")) {
            if (role == null) {
                role = "1";
            }
            executeSql = "SELECT COUNT(*) from sys_fuc_users where logname = ? and rowstate ='1'";
            int count = Integer.valueOf(DataService.getFirstValue(executeSql, new String[]{logname}));
            if (count > 0) {
                return "-999";
            }
            executeSql = "insert into sys_fuc_users(id,logname,username,pwd,phone,email,bem,sex,homeAddress," +
                    "createtime,rowstate,role) value(uuid(),?,?,?,?,?,?,?,?,now(),'1',?)";
            execute = DataService.execute(executeSql, new String[]{logname, username, password, phone, email, bem, sex,
                    homeAddress, role});
        } else {
            executeSql = "update sys_fuc_users set username=?, pwd=?, phone=?," +
                    "email=?,role=?,bem=?,sex=?,homeAddress=? where id = ?";
            if (password.equals("74be16979710d4c4e7c6647856088456")) {
                executeSql = executeSql.replace("pwd=?, ", "");
                execute = DataService.execute(executeSql, new String[]{username, phone, email, role, bem,
                        sex, homeAddress, id});
            } else {
                execute = DataService.execute(executeSql, new String[]{username, password, phone,
                        email, role, bem, sex, homeAddress, id});
            }
        }
        String restr = String.valueOf(execute);
        return restr;
    }

    @Override
    public String delUserInfoList(String content, String page) {
        //每页多少条数据
        double pageCount = Double.valueOf(ConfigDataService.getValue("pageCount"));
        User usr;
        UserPageListInfo userPageListInfo = new UserPageListInfo();

        //计划进入第几页
        userPageListInfo.setCurrentPage(page);

        //每页数据量
        userPageListInfo.setPageCount(String.valueOf((int) pageCount));
        List<User> list = new ArrayList<>();
        String userPageListInfoSql = ConfigDataService.getValue("deluserPageListInfoList")
                .replace("{1}", content);

        //总数据量
        String countSql = userPageListInfoSql.replace("*", "count(*)");
        String count = DataService.getFirstValue(countSql, new String[]{});
        userPageListInfo.setTotalCount(count);

        //总页数
        double countInt = Double.valueOf(count);
        if (countInt <= pageCount) {//总数据量小于每页数量
            userPageListInfo.setTotalPageCount("1");
        } else {
            double totalPageCountd = countInt / pageCount;
            int totalPageCounti = (int) Math.ceil(totalPageCountd);
            userPageListInfo.setTotalPageCount(String.valueOf(totalPageCounti));
        }

        //展示的数据
        int num = 0;
        if (Integer.valueOf(page) > 1) {
            int p = Integer.valueOf(page);
            num = (p - 1) * (int) pageCount;
        }
        userPageListInfoSql = userPageListInfoSql + " limit " + num + "," + ((int) pageCount);
        List<Map<String, Object>> maps = DataService.getDataTable(userPageListInfoSql, new String[]{});
        for (Map<String, Object> map : maps) {
            usr = new User();
            BeanUtil.loadEntity(usr, map);
            list.add(usr);
        }
        userPageListInfo.setList(list);
        String jsonStr = Json.getJsonStr(userPageListInfo);
        return jsonStr;
    }

    @Override
    public String resetUserinfodel(String id) {
        String userInfodel = "update sys_fuc_users set rowstate = '1' WHERE id = ?";
        int execute = DataService.execute(userInfodel, new String[]{id});
        String restr = String.valueOf(execute);
        return restr;
    }

    @Override
    public String showArchivesUserInfoList(String content, String page) {
        //每页多少条数据
        double pageCount = Double.valueOf(ConfigDataService.getValue("pageCount"));
        User usr;
        UserPageListInfo userPageListInfo = new UserPageListInfo();

        //计划进入第几页
        userPageListInfo.setCurrentPage(page);

        //每页数据量
        userPageListInfo.setPageCount(String.valueOf((int) pageCount));
        List<User> list = new ArrayList<>();
        String userPageListInfoSql = ConfigDataService.getValue("userPageListInfoList")
                .replace("{1}", content);

        int endIndex = userPageListInfoSql.indexOf("FROM");

        //总数据量
        String countSql = "SELECT COUNT(*) " + userPageListInfoSql.substring(endIndex, userPageListInfoSql.length());

        String count = DataService.getFirstValue(countSql, new String[]{});
        userPageListInfo.setTotalCount(count);

        //总页数
        double countInt = Double.valueOf(count);
        if (countInt <= pageCount) {//总数据量小于每页数量
            userPageListInfo.setTotalPageCount("1");
        } else {
            double totalPageCountd = countInt / pageCount;
            int totalPageCounti = (int) Math.ceil(totalPageCountd);
            userPageListInfo.setTotalPageCount(String.valueOf(totalPageCounti));
        }

        //展示的数据
        int num = 0;
        if (Integer.valueOf(page) > 1) {
            int p = Integer.valueOf(page);
            num = (p - 1) * (int) pageCount;
        }
        userPageListInfoSql = userPageListInfoSql + " limit " + num + "," + ((int) pageCount);
        List<Map<String, Object>> maps = DataService.getDataTable(userPageListInfoSql, new String[]{});
        for (Map<String, Object> map : maps) {
            usr = new User();
            BeanUtil.loadEntity(usr, map);
            list.add(usr);
        }
        userPageListInfo.setList(list);
        String jsonStr = Json.getJsonStr(userPageListInfo);
        return jsonStr;
    }

    @Override
    public String singleUsr(String id) {
        User usr = new User();
        String sql = "select * from sys_fuc_users where id = ?";
        List<Map<String, Object>> maps = DataService.getDataTable(sql, new String[]{id});
        Map<String, Object> map = maps.get(0);
        BeanUtil.loadEntity(usr, map);
        String jsonStr = Json.getJsonStr(usr);
        return jsonStr;
    }

    @Override
    public String showUserInfoList1(String content, String page) {
        //每页多少条数据
        double pageCount = Double.valueOf(ConfigDataService.getValue("pageCount"));
        User usr;
        UserPageListInfo userPageListInfo = new UserPageListInfo();

        //计划进入第几页
        userPageListInfo.setCurrentPage(page);

        //每页数据量
        userPageListInfo.setPageCount(String.valueOf((int) pageCount));
        List<User> list = new ArrayList<>();
        String userPageListInfoSql = ConfigDataService.getValue("userPageListInfoList")
                .replace("{1}", content);

        int endIndex = userPageListInfoSql.indexOf("FROM");

        //总数据量
        String countSql = "SELECT COUNT(*) " + userPageListInfoSql.substring(endIndex, userPageListInfoSql.length());

        String count = DataService.getFirstValue(countSql, new String[]{});
        userPageListInfo.setTotalCount(count);

        //总页数
        double countInt = Double.valueOf(count);
        if (countInt <= pageCount) {//总数据量小于每页数量
            userPageListInfo.setTotalPageCount("1");
        } else {
            double totalPageCountd = countInt / pageCount;
            int totalPageCounti = (int) Math.ceil(totalPageCountd);
            userPageListInfo.setTotalPageCount(String.valueOf(totalPageCounti));
        }

        //展示的数据
        int num = 0;
        if (Integer.valueOf(page) > 1) {
            int p = Integer.valueOf(page);
            num = (p - 1) * (int) pageCount;
        }
        userPageListInfoSql = userPageListInfoSql + " limit " + num + "," + ((int) pageCount);
        List<Map<String, Object>> maps = DataService.getDataTable(userPageListInfoSql, new String[]{});
        for (Map<String, Object> map : maps) {
            usr = new User();
            BeanUtil.loadEntity(usr, map);
            list.add(usr);
        }
        userPageListInfo.setList(list);
        String jsonStr = Json.getJsonStr(userPageListInfo);
        return jsonStr;
    }
}

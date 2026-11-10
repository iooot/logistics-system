package com.web.authorityAction.Controller.Impl;

import common.SqlHelper.DataService;
import common.usingtool.BeanUtil;
import common.usingtool.ConfigDataService;
import common.usingtool.Json;
import com.domain.Role;
import com.web.authorityAction.Controller.AuthorityService;
import com.web.authorityAction.domain.RolePageListInfo;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class AuthorityServiceImpl implements AuthorityService {
    @Override
    public String roleList(String content, String page) {
        //每页多少条数据
        double pageCount = Double.valueOf(ConfigDataService.getValue("pageCount"));
        Role role;
        RolePageListInfo rolePageListInfo = new RolePageListInfo();

        //计划进入第几页
        rolePageListInfo.setCurrentPage(page);

        //每页数据量
        rolePageListInfo.setPageCount(String.valueOf((int) pageCount));
        List<Role> list = new ArrayList<>();
        String roleListInitSql = ConfigDataService.getValue("roleListInit")
                .replace("{1}", content);

        //总数据量
        String countSql = roleListInitSql.replace("*", "count(*)");
        String count = DataService.getFirstValue(countSql, new String[]{});
        rolePageListInfo.setTotalCount(count);

        //总页数
        double countInt = Double.valueOf(count);
        if (countInt <= pageCount) {//总数据量小于每页数量
            rolePageListInfo.setTotalPageCount("1");
        } else {
            double totalPageCountd = countInt / pageCount;
            int totalPageCounti = (int) Math.ceil(totalPageCountd);
            rolePageListInfo.setTotalPageCount(String.valueOf(totalPageCounti));
        }

        //展示的数据
        int num = 0;
        if (Integer.valueOf(page) > 1) {
            int p = Integer.valueOf(page);
            num = (p - 1) * (int) pageCount;
        }
        roleListInitSql = roleListInitSql + " limit " + num + "," + ((int) pageCount);
        List<Map<String, Object>> maps = DataService.getDataTable(roleListInitSql, new String[]{});
        for (Map<String, Object> map : maps) {
            role = new Role();
            BeanUtil.loadEntity(role, map);
            list.add(role);
        }
        rolePageListInfo.setList(list);
        String jsonStr = Json.getJsonStr(rolePageListInfo);
        return jsonStr;
    }

    @Override
    public String addRole(String id, String val, String vkey, String dicName, String did, String bem) {
        String addRoleSql = "";
        String[] arr;
        if (id == "" || id == null) {
            addRoleSql = "INSERT INTO sys_role(id,val,vkey,dicName,did,createtime,bem) " +
                    "value(uuid(),?,?,?,?,NOW(),?)";
            arr = new String[]{val, vkey, dicName, did, bem};
        } else {
            addRoleSql = "update sys_role set val=?,vkey=?,dicName=?,did=?,bem=? where id=? ";
            arr = new String[]{val, vkey, dicName, did, bem, id};
        }
        int execute = DataService.execute(addRoleSql, arr);
        String result = String.valueOf(execute);
        return result;
    }

    @Override
    public String delRole(String id) {
        String delRoleSql = "delete from sys_role where id = ?";
        int execute = DataService.execute(delRoleSql, new String[]{id});
        String result = String.valueOf(execute);
        return result;
    }
}

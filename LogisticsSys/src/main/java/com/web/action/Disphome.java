package com.web.action;

import common.SqlHelper.DataService;
import common.usingtool.BeanUtil;
import common.usingtool.ConfigDataService;
import com.domain.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/disphome")
//转发需要 @Controller  不可以是RestController
public class Disphome {

    @RequestMapping("/xtInfo")
    public String xtInfo(HttpServletRequest request, HttpServletResponse response) throws Exception {
        SystemInfo systemInfo = new SystemInfo();
        String systemInfoSql = "SELECT * FROM systeminfo";
        List<Map<String, Object>> dataTable = DataService.getDataTable(systemInfoSql, new String[]{});
        if (dataTable.size() > 0) {
            Map<String, Object> map = dataTable.get(0);
            BeanUtil.loadEntity(systemInfo, map);
        }
        request.setAttribute("systemInfo", systemInfo);
        return "/WEB-INF/jsp/xtInfo.jsp";
    }

    //首页跳转注册界面 以及 用户管理注册界面
    //target=adnew,是从用户管理进入
    @RequestMapping("/userInfo")
    public String userInfo(HttpServletRequest request, String id, String target) throws Exception {
        User user = new User();
        //用户管理编辑进入，就载入用户信息
        if (id != null && target == null) {
            String usql = "select * FROM sys_fuc_users WHERE id = ?";
            List<Map<String, Object>> maps = DataService.getDataTable(usql, new String[]{id});
            if (maps.size() > 0) {
                Map<String, Object> map = maps.get(0);
                BeanUtil.loadEntity(user, map);
            }
        } else if (target != null) {//个人修改信息
            if (target.equals("personchange")) {

                String sql = "select * FROM sys_fuc_users WHERE id = ?";
                String uid = ((User) request.getSession().getAttribute("user")).getId();
                List<Map<String, Object>> maps = DataService.getDataTable(sql, new String[]{uid});
                if (maps.size() > 0) {
                    Map<String, Object> map = maps.get(0);
                    BeanUtil.loadEntity(user, map);
                }
            }
        }
        request.setAttribute("user", user);
        Role role;
        List<Role> rlist = new ArrayList<>();
        String roleList = "SELECT * FROM sys_role WHERE did = '1' ORDER BY vkey";
        List<Map<String, Object>> maps = DataService.getDataTable(roleList, new String[]{});
        for (Map<String, Object> map : maps) {
            role = new Role();
            BeanUtil.loadEntity(role, map);
            rlist.add(role);
        }
        request.setAttribute("rlist", rlist);
        if (id != null && target == null) {//编辑
            return "/function/userGN/userInfo2.jsp";
        } else if (id == null && target != null) {
            if (target.equals("adnew")) {//用户管理新增
                return "/function/userGN/userInfo2.jsp";
            }
        } else if (id == null) {//登录页新增
            return "/function/userGN/userInfo.jsp";
        } else if (target.equals("personchange")) {//个人修改
            return "/function/userGN/userInfo3.jsp";
        }
        return "/function/userGN/userInfo.jsp";
    }
}

package com.web.functionModule.forumAction;

import com.domain.SystemInfo;
import com.web.functionModule.forumAction.domain.Bbs;
import com.web.functionModule.forumAction.domain.Pl;
import common.SqlHelper.DataService;
import common.usingtool.BeanUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/dispForumAction")
public class DispForumAction {
    @RequestMapping("/bbs")
    public String bbs(HttpServletRequest request, HttpServletResponse response, String id) throws Exception {
        SystemInfo systemInfo = new SystemInfo();
        String systemInfoSql = "SELECT * FROM systeminfo";
        List<Map<String, Object>> dataTable = DataService.getDataTable(systemInfoSql, new String[]{});
        if (dataTable.size() > 0) {
            Map<String, Object> map = dataTable.get(0);
            BeanUtil.loadEntity(systemInfo, map);
        }
        request.setAttribute("systemInfo", systemInfo);
        Bbs bbs = new Bbs();
        String singleBbs = "SELECT * FROM sys_fuc_bbs WHERE id = ?";
        List<Map<String, Object>> maps = DataService.getDataTable(singleBbs, new String[]{id});
        if (maps.size() != 0) {
            Map<String, Object> map = maps.get(0);
            BeanUtil.loadEntity(bbs, map);
        }
        request.setAttribute("bbs", bbs);
        return "/function/forum/bbsInfo.jsp";
    }


    @RequestMapping("/bbspl")
    public String bbspl(HttpServletRequest request, HttpServletResponse response, String id) throws Exception {
        SystemInfo systemInfo = new SystemInfo();
        String systemInfoSql = "SELECT * FROM systeminfo";
        List<Map<String, Object>> dataTable = DataService.getDataTable(systemInfoSql, new String[]{});
        if (dataTable.size() > 0) {
            Map<String, Object> map = dataTable.get(0);
            BeanUtil.loadEntity(systemInfo, map);
        }
        request.setAttribute("systemInfo", systemInfo);
        Pl pl;
        List<Pl> list = new ArrayList<>();
        String singleBbs = "select * from sys_fuc_pl where bbsid = ? order by createtime desc";
        List<Map<String, Object>> maps = DataService.getDataTable(singleBbs, new String[]{id});
        for (Map<String, Object> map : maps) {
            pl = new Pl();
            BeanUtil.loadEntity(pl, map);
            list.add(pl);
        }
        request.setAttribute("plist", list);
        return "/function/forum/bbsplInfo.jsp";
    }


    @RequestMapping("/showTiezi")
    public String showTiezi(HttpServletRequest request, String id) throws Exception {
        Bbs bbs = new Bbs();
        String singleBbs = "SELECT * FROM sys_fuc_bbs WHERE id = ?";
        List<Map<String, Object>> maps = DataService.getDataTable(singleBbs, new String[]{id});
        if (maps.size() != 0) {
            Map<String, Object> map = maps.get(0);
            BeanUtil.loadEntity(bbs, map);
        }
        request.setAttribute("bbs", bbs);
        Pl pl;
        List<Pl> list = new ArrayList<>();
        String bbspl = "select * from sys_fuc_pl where bbsid = ? order by createtime desc";
        List<Map<String, Object>> bbsplmaps = DataService.getDataTable(bbspl, new String[]{id});
        for (Map<String, Object> map : bbsplmaps) {
            pl = new Pl();
            BeanUtil.loadEntity(pl, map);
            list.add(pl);
        }
        request.setAttribute("list", list);
        return "/function/forum/outer/show/show.jsp";
    }


}

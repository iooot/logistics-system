package com.web.action;

import common.SqlHelper.DataService;
import common.usingtool.BeanUtil;
import common.usingtool.ConfigDataService;
import com.domain.SystemInfo;
import com.domain.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.web.functionModule.cdInfoAction.domain.CdInfo;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
public class DispLoginAction {

    @RequestMapping("/login")
    public String login(HttpServletRequest request, HttpServletResponse response) throws Exception {
        lInfo(request);
        String systemName = "select sysname from systeminfo ";
        String sysname = DataService.getFirstValue(systemName, new String[]{});
        request.setAttribute("sysname", sysname);
        return "/login/index.jsp";
    }


    @RequestMapping("/index")
    public String index(HttpServletRequest request, HttpServletResponse response) throws Exception {
        User usr = ((User) request.getSession().getAttribute("user"));
        if (usr == null) {
            return "/login";
        }
        sinfo(request);
        request.setAttribute("user", usr);
        return "/WEB-INF/jsp/index.jsp";
    }

    private void lInfo(HttpServletRequest request) {
        SystemInfo systemInfo = new SystemInfo();
        String getsysteminfoSql = "SELECT * FROM systeminfo";
        List<Map<String, Object>> dataTable = DataService.getDataTable(getsysteminfoSql, new String[]{});
        if (dataTable.size() > 0) {
            Map<String, Object> map = dataTable.get(0);
            BeanUtil.loadEntity(systemInfo, map);
        }
        request.setAttribute("systeminfo", systemInfo);
    }

    private void sinfo(HttpServletRequest request) {
        String role = ((User) request.getSession().getAttribute("user")).getRole();
        lInfo(request);
        String cdinfo = "SELECT * FROM cdinfo ORDER BY fnid,indx";
        List<Map<String, Object>> cdmaps = DataService.getDataTable(cdinfo, new String[]{});
        List<CdInfo> cdList = new ArrayList<>();
        CdInfo cdInfo;
        for (Map<String, Object> map : cdmaps) {
            String nrole = map.get("nrole").toString();
            if (nrole.equals(role)|| map.get("nrole").equals("3")) {
                String fnid = map.get("fnid").toString();
                if (fnid.equals("0")) {
                    cdInfo = new CdInfo();
                    BeanUtil.loadEntity(cdInfo, map);
                    cdList.add(cdInfo);
                }
            }
        }
        for (Map<String, Object> map : cdmaps) {
            String s = map.get("cname").toString();
            if ( map.get("nrole").equals(role) || map.get("nrole").equals("3")) {
                String fnid = map.get("fnid").toString();
                if (fnid.equals("0")) {
                    continue;
                }
                for (CdInfo cd0 : cdList) {
                    if (cd0.getId().equals(fnid)) {
                        cdInfo = new CdInfo();
                        BeanUtil.loadEntity(cdInfo, map);
                        cd0.getList().add(cdInfo);
                        break;
                    }
                }
            }
        }
        request.setAttribute("items", cdList);
    }
}

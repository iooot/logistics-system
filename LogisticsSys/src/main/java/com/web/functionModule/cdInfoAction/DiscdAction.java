package com.web.functionModule.cdInfoAction;

import common.SqlHelper.DataService;
import common.usingtool.BeanUtil;
import common.usingtool.ConfigDataService;
import com.domain.Role;
import com.domain.SystemInfo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.web.functionModule.cdInfoAction.domain.CdInfo;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/discd")
public class DiscdAction {
    @RequestMapping("/addCd")
    public String addCd(HttpServletRequest request, String id) throws Exception {
        SystemInfo systemInfo = new SystemInfo();
        String systemInfoSql = "SELECT * FROM systeminfo";
        List<Map<String, Object>> dataTable = DataService.getDataTable(systemInfoSql, new String[]{});
        if (dataTable.size() > 0) {
            Map<String, Object> map = dataTable.get(0);
            BeanUtil.loadEntity(systemInfo, map);
        }
        request.setAttribute("systemInfo", systemInfo);
        CdInfo cdInfo = new CdInfo();
        String cdsingle = "SELECT * FROM cdinfo WHERE id = ?";
        List<Map<String, Object>> maps = DataService.getDataTable(cdsingle, new String[]{id});
        if (maps.size() != 0) {
            Map<String, Object> map = maps.get(0);
            BeanUtil.loadEntity(cdInfo, map);
        } else {
            //获取下一个编号
            String indxSql = "SELECT indx+1 FROM cdinfo WHERE fnid = ? and indx != 9999 ORDER BY indx desc";
            String indx = DataService.getFirstValue(indxSql, new String[]{"0"});
            cdInfo.setIndx(indx);
        }
        request.setAttribute("cdInfo", cdInfo);
        Role role;
        List<Role> roleList = new ArrayList<>();
        String roleSql = "SELECT * FROM sys_role WHERE did = '1'";
        List<Map<String, Object>> roleMaps = DataService.getDataTable(roleSql, new String[]{});
        for (Map<String, Object> map : roleMaps) {
            role = new Role();
            BeanUtil.loadEntity(role, map);
            roleList.add(role);
        }
        request.setAttribute("roleList", roleList);
        return "/function/cdInfo/cdInfo.jsp";
    }

    @RequestMapping("/sonCdList")
    public String sonCdList(HttpServletRequest request, String id) throws Exception {
        String sql = "select * from cdinfo where fnid = ? order by indx";
        List<Map<String, Object>> maps = DataService.getDataTable(sql, new String[]{id});
        CdInfo cdInfo;
        List<CdInfo> cdList = new ArrayList<>();
        request.setAttribute("fnid", id);

        //角色列表
        String roleSql = "SELECT * FROM sys_role";
        List<Map<String, Object>> roleMaps = DataService.getDataTable(roleSql, new String[]{});
        List<Role> rlist = new ArrayList<>();
        Role role;
        for (Map<String, Object> map : roleMaps) {
            role = new Role();
            BeanUtil.loadEntity(role, map);
            rlist.add(role);
        }

        for (Map<String, Object> map : maps) {
            cdInfo = new CdInfo();
            BeanUtil.loadEntity(cdInfo, map);
            for (Role r : rlist) {
                if (cdInfo.getNrole().equals(r.getVkey())) {
                    cdInfo.setNrole(r.getVal());
                    break;
                }
            }
            cdList.add(cdInfo);
        }
        request.setAttribute("cdList", cdList);
        return "/function/cdInfo/soncd-list.jsp";
    }


    @RequestMapping("/addSonCd")
    public String addSonCd(HttpServletRequest request, String id, String fnid) throws Exception {
        SystemInfo systemInfo = new SystemInfo();
        String systemInfoSql = "SELECT * FROM systeminfo";
        List<Map<String, Object>> dataTable = DataService.getDataTable(systemInfoSql, new String[]{});
        if (dataTable.size() > 0) {
            Map<String, Object> map = dataTable.get(0);
            BeanUtil.loadEntity(systemInfo, map);
        }
        request.setAttribute("systemInfo", systemInfo);

        CdInfo cdInfo = new CdInfo();
        String cdsingle = "SELECT * FROM cdinfo WHERE id = ?";
        List<Map<String, Object>> maps = DataService.getDataTable(cdsingle, new String[]{id});
        if (maps.size() != 0) {
            Map<String, Object> map = maps.get(0);
            BeanUtil.loadEntity(cdInfo, map);
        } else {
            //获取下一个编号
            String indxSql = "SELECT indx+1 FROM cdinfo WHERE fnid = ? and indx != 9999 ORDER BY indx desc";
            String indx = DataService.getFirstValue(indxSql, new String[]{fnid});
            if (indx == "" || indx == null) {
                indx = "0";
            }
            cdInfo.setIndx(indx);
        }
        //二级菜单
        request.setAttribute("cdInfo", cdInfo);
        Role role;
        List<Role> roleList = new ArrayList<>();
        String roleSql = "SELECT * FROM sys_role WHERE did = '1'";
        List<Map<String, Object>> roleMaps = DataService.getDataTable(roleSql, new String[]{});
        for (Map<String, Object> map : roleMaps) {
            role = new Role();
            BeanUtil.loadEntity(role, map);
            roleList.add(role);
        }

        List<CdInfo> cdlist = new ArrayList<>();
        String topCdSql = "SELECT * FROM cdInfo WHERE fnid = '0' ORDER BY indx";
        CdInfo cd;
        List<Map<String, Object>> cdMaps = DataService.getDataTable(topCdSql, new String[]{});
        for (Map<String, Object> map : cdMaps) {
            cd = new CdInfo();
            BeanUtil.loadEntity(cd, map);
            cdlist.add(cd);
        }
        request.setAttribute("fnid", fnid);
        request.setAttribute("cdlist", cdlist);
        request.setAttribute("roleList", roleList);
        return "/function/cdInfo/cdSonInfo.jsp";
    }


}

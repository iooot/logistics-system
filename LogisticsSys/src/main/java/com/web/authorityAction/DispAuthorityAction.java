package com.web.authorityAction;


import common.SqlHelper.DataService;
import common.usingtool.BeanUtil;
import com.domain.Role;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;


@Controller
@RequestMapping("/dispAuthority")
public class DispAuthorityAction {


    @RequestMapping("/newRole")
    public String testInfo(HttpServletRequest request, String id) throws Exception {
        Role role = new Role();
        String sql = "select * from sys_role where id = ?";
        List<Map<String, Object>> maps = DataService.getDataTable(sql, new String[]{id});
        if (maps.size() > 0) {
            BeanUtil.loadEntity(role, maps.get(0));
        } else {
            String s = "SELECT vkey+1 FROM sys_role ORDER BY vkey desc";
            String nextKey = DataService.getFirstValue(s, new String[]{});
            role.setVkey(nextKey);
        }
        request.setAttribute("role", role);
        return "/function/role/singleRole.jsp";
    }


}

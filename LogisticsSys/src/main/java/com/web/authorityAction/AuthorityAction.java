package com.web.authorityAction;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.web.authorityAction.Controller.AuthorityService;
import com.web.authorityAction.Controller.Impl.AuthorityServiceImpl;
import com.web.filter.CommUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/authorityAction")
@Component
public class AuthorityAction {

    @RequestMapping("/roleList")
    public String init(HttpServletRequest request, String content, String page) {
        HttpSession session = request.getSession();
        String returnStr = "";
        boolean b = CommUtil.permissionAuthentication(session);
        if (b) {
            AuthorityService authorityService = new AuthorityServiceImpl();
            returnStr = authorityService.roleList(content, page);
        }
        return returnStr;
    }


    @RequestMapping("/addRole")
    public String addRole(HttpServletRequest request, String id, String val, String vkey, String dicName,
                          String did, String bem) {
        HttpSession session = request.getSession();
        String returnStr = "";
        boolean b = CommUtil.permissionAuthentication(session);
        if (b) {
            AuthorityService authorityService = new AuthorityServiceImpl();
            returnStr = authorityService.addRole(id, val, vkey, dicName, did, bem);
        }
        return returnStr;
    }


    @RequestMapping("/delRole")
    public String delRole(HttpServletRequest request, String id) {
        HttpSession session = request.getSession();
        String returnStr = "";
        boolean b = CommUtil.permissionAuthentication(session);
        if (b) {
            AuthorityService authorityService = new AuthorityServiceImpl();
            returnStr = authorityService.delRole(id);
        }
        return returnStr;
    }

}

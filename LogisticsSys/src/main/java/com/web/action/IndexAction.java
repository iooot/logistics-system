package com.web.action;

import common.SqlHelper.DataService;
import common.usingtool.ConfigDataService;
import com.domain.User;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.web.controller.IndexService;
import com.web.controller.impl.IndexServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/home")
@Component
public class IndexAction {

    @RequestMapping("/index")
    public String init(HttpServletRequest request, HttpServletResponse response) {
        String reStr = "-1";
        HttpSession session = request.getSession();
        reStr = ((User) session.getAttribute("user")).getUsername();
        return reStr;
    }

    @RequestMapping("/updateSysInfo")
    public String updateSysInfo(String sysname, String leibie, String content) {
        String updateSysInfoSql = ConfigDataService.getValue("updateSysInfo");
        int execute = DataService.execute(updateSysInfoSql, new String[]{sysname, leibie, content});
        String reStr = String.valueOf(execute);
        return reStr;
    }


    @RequestMapping("/cdList")
    public String cdList(HttpServletRequest request, String content, String page) {
        String returnStr = "";
        IndexService indexService = new IndexServiceImpl();
        returnStr = indexService.cdList(content, page);
        return returnStr;
    }


    @RequestMapping("/cdDelete")
    public String cdDelete(HttpServletRequest request, String id) {
        HttpSession session = request.getSession();
        String returnStr = "";
        IndexService indexService = new IndexServiceImpl();
        returnStr = indexService.cdDelete(id);
        return returnStr;
    }



}

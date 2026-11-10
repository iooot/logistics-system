package com.web.functionModule.forumAction;

import com.domain.User;
import com.web.filter.CommUtil;
import com.web.functionModule.forumAction.Controller.ScInfoService;
import com.web.functionModule.forumAction.Controller.ScInfoServiceImpl;
import common.SqlHelper.DataService;
import common.usingtool.ConfigDataService;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/forumAction")
@Component
public class ForumAction {

    @RequestMapping("/bbsList")
    public String bbsList(HttpServletRequest request, String content, String page) {
        HttpSession session = request.getSession();
        String returnStr = "";
        User usr = (User) session.getAttribute("user");
        boolean b = CommUtil.permissionAuthentication(session);
        if (b) {
            ScInfoService scInfoService = new ScInfoServiceImpl();
            returnStr = scInfoService.bbsList(content, page, usr);
        }
        return returnStr;
    }


    @RequestMapping("/setTop")
    public String setTop(HttpServletRequest request, String id) {
        HttpSession session = request.getSession();
        String returnStr = "";
        boolean b = CommUtil.permissionAuthentication(session);
        if (b) {
            ScInfoService scInfoService = new ScInfoServiceImpl();
            returnStr = scInfoService.setTop(id);
        }
        return returnStr;
    }


    @RequestMapping("/noTop")
    public String noTop(HttpServletRequest request, String id) {
        HttpSession session = request.getSession();
        String returnStr = "";
        boolean b = CommUtil.permissionAuthentication(session);
        if (b) {
            ScInfoService scInfoService = new ScInfoServiceImpl();
            returnStr = scInfoService.noTop(id);
        }
        return returnStr;
    }

    @RequestMapping("/DelbbsPl")
    public String DelbbsPl(String id) {
        String sql = "delete from pl where id = ?";
        int execute = DataService.execute(sql, new String[]{id});
        String result = String.valueOf(execute);
        return result;
    }


    @RequestMapping("/bbsDel")
    public String bbsDel(String id) {
        String sql = "delete from sys_fuc_bbs where id = ?";
        int execute = DataService.execute(sql, new String[]{id});
        String result = String.valueOf(execute);
        return result;
    }


    @RequestMapping("/publish")
    public String publish(HttpServletRequest request, String bbsname, String bbstype, String bbscontent) {
        HttpSession session = request.getSession();
        User usr = (User) session.getAttribute("user");
        String publish =
                "INSERT INTO sys_fuc_bbs(id,bbsname,bbstype,bbscontent,bbscreator,bbscreatetime,state)" +
                        " VALUE(UUID(),?,?,?,?,NOW(),'1')";
        int execute = DataService.execute(publish, new String[]{bbsname, bbstype, bbscontent, usr.getUsername()});
        String result = String.valueOf(execute);
        return result;
    }

    @RequestMapping("/addNewPl")
    public String addNewPl(User usr, String id, String content) {
        String bbsDel = "INSERT INTO sys_fuc_pl(id,content,creator,createtime,bbsid) VALUE(UUID(),?,?,NOW(),?)";
        int execute = DataService.execute(bbsDel, new String[]{content, usr.getUsername(), id});
        String result = String.valueOf(execute);
        return result;
    }


}

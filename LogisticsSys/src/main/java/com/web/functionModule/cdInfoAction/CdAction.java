package com.web.functionModule.cdInfoAction;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.web.functionModule.cdInfoAction.Controller.CdInfoService;
import com.web.functionModule.cdInfoAction.Controller.Impl.CdInfoServiceImpl;
import com.web.filter.CommUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/cdAction")
@Component
public class CdAction {

    @RequestMapping("/addNewCd")
    public String addRole(HttpServletRequest request, String id,String cname,String cpath,String fnid,
                          String indx,String level,String nrole) {
        HttpSession session = request.getSession();
        String returnStr = "";
        boolean b = CommUtil.permissionAuthentication(session);
        if (b) {
            CdInfoService cdInfoService = new CdInfoServiceImpl();
            returnStr = cdInfoService.addNewCd(id, cname, cpath, fnid, indx, level,nrole);
        }
        return returnStr;
    }
}

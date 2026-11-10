package com.web.functionModule.cdInfoAction.Controller.Impl;

import common.SqlHelper.DataService;
import com.web.functionModule.cdInfoAction.Controller.CdInfoService;

public class CdInfoServiceImpl implements CdInfoService {
    @Override
    public String addNewCd(String id, String cname, String cpath, String fnid, String indx, String level, String nrole) {
        String sql;
        String[] arr;
        //最后一个数
        if (id == "" || id == null) {
            sql = "INSERT INTO cdinfo(id,cname,cpath,fnid,indx,level,nrole,bem) " +
                    "value(uuid(),?,?,?,?,?,?,?)";
            arr = new String[]{cname, cpath, fnid, indx, level, nrole, "&#xe723;"};
        } else {
            sql = "update cdinfo set cname=?,cpath=?,fnid=?,indx=?,level=?,nrole=? where id = ?";
            arr = new String[]{cname, cpath, fnid, indx, level, nrole, id};
        }
        int execute = DataService.execute(sql, arr);
        String result = String.valueOf(execute);
        return result;
    }
}

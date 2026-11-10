package com.web.functionModule.forumAction.Controller;

import com.domain.User;
import com.web.functionModule.forumAction.domain.Bbs;
import com.web.functionModule.forumAction.domain.BbsPageListInfo;
import common.SqlHelper.DataService;
import common.usingtool.BeanUtil;
import common.usingtool.ConfigDataService;
import common.usingtool.Json;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ScInfoServiceImpl implements ScInfoService {
    @Override
    public String bbsList(String content, String page, User usr) {
        //每页多少条数据
        double pageCount = Double.valueOf(ConfigDataService.getValue("pageCount"));
        Bbs bbs;
        BbsPageListInfo bbsPageListInfo = new BbsPageListInfo();
        bbsPageListInfo.setUsr(usr);
        //计划进入第几页
        bbsPageListInfo.setCurrentPage(page);

        //每页数据量
        bbsPageListInfo.setPageCount(String.valueOf((int) pageCount));
        List<Bbs> list = new ArrayList<>();
        String bbsGuanli = "SELECT * FROM sys_fuc_bbs WHERE 1=1 AND (bbsname LIKE '%{1}%'\n" +
                "        OR bbstype LIKE '%{1}%' OR bbscontent LIKE '%{1}%'\n" +
                "        OR bbscreator LIKE '%{1}%') ORDER BY state,bbscreatetime desc";
        bbsGuanli = bbsGuanli.replace("{1}", content);

        //总数据量
        String countSql = bbsGuanli.replace("*", "count(*)");
        String count = DataService.getFirstValue(countSql, new String[]{});
        bbsPageListInfo.setTotalCount(count);

        //总页数
        double countInt = Double.valueOf(count);
        if (countInt <= pageCount) {//总数据量小于每页数量
            bbsPageListInfo.setTotalPageCount("1");
        } else {
            double totalPageCountd = countInt / pageCount;
            int totalPageCounti = (int) Math.ceil(totalPageCountd);
            bbsPageListInfo.setTotalPageCount(String.valueOf(totalPageCounti));
        }

        //展示的数据
        int num = 0;
        if (Integer.valueOf(page) > 1) {
            int p = Integer.valueOf(page);
            num = (p - 1) * (int) pageCount;
        }
        bbsGuanli = bbsGuanli + " limit " + num + "," + ((int) pageCount);
        List<Map<String, Object>> maps = DataService.getDataTable(bbsGuanli, new String[]{});
        for (Map<String, Object> map : maps) {
            bbs = new Bbs();
            BeanUtil.loadEntity(bbs, map);
            list.add(bbs);
        }
        bbsPageListInfo.setList(list);
        String jsonStr = Json.getJsonStr(bbsPageListInfo);
        return jsonStr;
    }

    @Override
    public String setTop(String id) {
        String bbsDel = "update sys_fuc_bbs set state = '0' where id = ?";
        int execute = DataService.execute(bbsDel, new String[]{id});
        String result = String.valueOf(execute);
        return result;
    }


    @Override
    public String noTop(String id) {
        String bbsDel = "update sys_fuc_bbs set state = '1' where id = ?";
        int execute = DataService.execute(bbsDel, new String[]{id});
        String result = String.valueOf(execute);
        return result;
    }


}

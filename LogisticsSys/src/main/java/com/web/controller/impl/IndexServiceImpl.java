package com.web.controller.impl;

import common.SqlHelper.DataService;
import common.usingtool.BeanUtil;
import common.usingtool.ConfigDataService;
import common.usingtool.Json;
import com.web.functionModule.cdInfoAction.domain.CdInfo;
import com.web.functionModule.cdInfoAction.domain.CdPageListInfo;
import com.web.controller.IndexService;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class IndexServiceImpl implements IndexService {

    @Override
    public String cdList(String content, String page) {
        //每页多少条数据
        double pageCount = Double.valueOf(ConfigDataService.getValue("pageCount"));
        CdInfo cdInfo;
        CdPageListInfo cdPageListInfo = new CdPageListInfo();

        //计划进入第几页
        cdPageListInfo.setCurrentPage(page);

        //每页数据量
        cdPageListInfo.setPageCount(String.valueOf((int) pageCount));
        List<CdInfo> list = new ArrayList<>();
        String xueyuanInfoListsql = ConfigDataService.getValue("cdList")
                .replace("{1}", content);

        //总数据量
        String countSql = xueyuanInfoListsql.replace("*", "count(*)");
        String count = DataService.getFirstValue(countSql, new String[]{});
        cdPageListInfo.setTotalCount(count);

        //总页数
        double countInt = Double.valueOf(count);
        if (countInt <= pageCount) {//总数据量小于每页数量
            cdPageListInfo.setTotalPageCount("1");
        } else {
            double totalPageCountd = countInt / pageCount;
            int totalPageCounti = (int) Math.ceil(totalPageCountd);
            cdPageListInfo.setTotalPageCount(String.valueOf(totalPageCounti));
        }

        //展示的数据
        int num = 0;
        if (Integer.valueOf(page) > 1) {
            int p = Integer.valueOf(page);
            num = (p - 1) * (int) pageCount;
        }
        xueyuanInfoListsql = xueyuanInfoListsql + " limit " + num + "," + ((int) pageCount);
        List<Map<String, Object>> maps = DataService.getDataTable(xueyuanInfoListsql, new String[]{});
        for (Map<String, Object> map : maps) {
            cdInfo = new CdInfo();
            BeanUtil.loadEntity(cdInfo, map);
            list.add(cdInfo);
        }
        cdPageListInfo.setList(list);
        String jsonStr = Json.getJsonStr(cdPageListInfo);
        return jsonStr;
    }

    @Override
    public String cdDelete(String id) {
        String result = "-1";
        String cdSonCount = "select count(*) from cdinfo where fnid = ?";
        String firstValue = DataService.getFirstValue(cdSonCount, new String[]{id});
        if (firstValue.equals("0")) {
            String cdDelete = "delete from cdinfo where id = ?";
            int s = DataService.execute(cdDelete, new String[]{id});
            result = String.valueOf(s);
        }
        return result;
    }
}

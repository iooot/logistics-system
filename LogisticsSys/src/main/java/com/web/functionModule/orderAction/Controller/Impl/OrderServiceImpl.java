package com.web.functionModule.orderAction.Controller.Impl;

import com.domain.User;
import com.web.functionModule.orderAction.Controller.OrderService;
import com.web.functionModule.orderAction.domain.OrderInfo;
import com.web.functionModule.orderAction.domain.OrderPageListInfo;
import common.SqlHelper.DataService;
import common.usingtool.BeanUtil;
import common.usingtool.ConfigDataService;
import common.usingtool.Json;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class OrderServiceImpl implements OrderService {

    @Override
    //所有订单列表
    public String allOrderList(User usr, String beginordertime, String endordertime, String content, String page) {
        //每页多少条数据
        double pageCount = Double.valueOf(ConfigDataService.getValue("pageCount"));
        OrderInfo order;
        OrderPageListInfo orderPageListInfo = new OrderPageListInfo();

        //计划进入第几页
        orderPageListInfo.setCurrentPage(page);

        //每页数据量
        orderPageListInfo.setPageCount(String.valueOf((int) pageCount));
        List<OrderInfo> list = new ArrayList<>();
        String condition = "";
        String role = usr.getRole();
        if (!role.equals("2")) {
            condition = " and orderuserid = '" + usr.getId() + "' ";
        }
        String orderPageListInfoSql = "SELECT * FROM sys_fuc_order WHERE ordertime between ? and ? and " +
                "(ordername LIKE ? OR orderuser LIKE ? OR ordernum LIKE ? OR revicename LIKE ? " +
                " OR tel LIKE ? OR ordermoney LIKE ? OR sendWU LIKE ?  OR reviceWU LIKE ? ) " + condition +
                "order by createtime desc";

        int endIndex = orderPageListInfoSql.indexOf("FROM");

        //总数据量
        String countSql = "SELECT COUNT(*) " + orderPageListInfoSql.substring(endIndex, orderPageListInfoSql.length());

        String count = DataService.getFirstValue(countSql, new String[]{beginordertime, endordertime,
                "%" + content + "%", "%" + content + "%", "%" + content + "%", "%" + content + "%"
                , "%" + content + "%", "%" + content + "%", "%" + content + "%", "%" + content + "%"});
        orderPageListInfo.setTotalCount(count);

        //总页数
        double countInt = Double.valueOf(count);
        if (countInt <= pageCount) {//总数据量小于每页数量
            orderPageListInfo.setTotalPageCount("1");
        } else {
            double totalPageCountd = countInt / pageCount;
            int totalPageCounti = (int) Math.ceil(totalPageCountd);
            orderPageListInfo.setTotalPageCount(String.valueOf(totalPageCounti));
        }

        //展示的数据
        int num = 0;
        if (Integer.valueOf(page) > 1) {
            int p = Integer.valueOf(page);
            num = (p - 1) * (int) pageCount;
        }
        orderPageListInfoSql = orderPageListInfoSql + " limit " + num + "," + ((int) pageCount);
        List<Map<String, Object>> maps = DataService.getDataTable(orderPageListInfoSql, new String[]
                {beginordertime, endordertime,
                        "%" + content + "%", "%" + content + "%", "%" + content + "%", "%" + content + "%"
                        , "%" + content + "%", "%" + content + "%", "%" + content + "%", "%" + content + "%"});
        for (Map<String, Object> map : maps) {
            order = new OrderInfo();
            BeanUtil.loadEntity(order, map);
            list.add(order);
        }
        orderPageListInfo.setList(list);
        String jsonStr = Json.getJsonStr(orderPageListInfo);
        return jsonStr;
    }

    @Override
    public String personOrder(User usr, String beginordertime, String endordertime, String content, String page) {
        //每页多少条数据
        double pageCount = Double.valueOf(ConfigDataService.getValue("pageCount"));
        OrderInfo order;
        OrderPageListInfo orderPageListInfo = new OrderPageListInfo();

        //计划进入第几页
        orderPageListInfo.setCurrentPage(page);

        //每页数据量
        orderPageListInfo.setPageCount(String.valueOf((int) pageCount));
        List<OrderInfo> list = new ArrayList<>();

        String orderPageListInfoSql = "SELECT * FROM sys_fuc_order WHERE customerid = ? and ordertime between ? and ? and " +
                "(ordername LIKE ? OR orderform LIKE ? OR ordernum LIKE ? OR customername LIKE ? " +
                " OR orderstute LIKE ? OR bem LIKE ? ) order by createtime desc";

        int endIndex = orderPageListInfoSql.indexOf("FROM");

        //总数据量
        String countSql = "SELECT COUNT(*) " + orderPageListInfoSql.substring(endIndex, orderPageListInfoSql.length());

        String count = DataService.getFirstValue(countSql, new String[]{usr.getId(), beginordertime, endordertime,
                "%" + content + "%", "%" + content + "%", "%" + content + "%"
                , "%" + content + "%", "%" + content + "%", "%" + content + "%"});
        orderPageListInfo.setTotalCount(count);

        //总页数
        double countInt = Double.valueOf(count);
        if (countInt <= pageCount) {//总数据量小于每页数量
            orderPageListInfo.setTotalPageCount("1");
        } else {
            double totalPageCountd = countInt / pageCount;
            int totalPageCounti = (int) Math.ceil(totalPageCountd);
            orderPageListInfo.setTotalPageCount(String.valueOf(totalPageCounti));
        }

        //展示的数据
        int num = 0;
        if (Integer.valueOf(page) > 1) {
            int p = Integer.valueOf(page);
            num = (p - 1) * (int) pageCount;
        }
        orderPageListInfoSql = orderPageListInfoSql + " limit " + num + "," + ((int) pageCount);
        List<Map<String, Object>> maps = DataService.getDataTable(orderPageListInfoSql, new String[]
                {usr.getId(), beginordertime, endordertime,
                        "%" + content + "%", "%" + content + "%", "%" + content + "%"
                        , "%" + content + "%", "%" + content + "%", "%" + content + "%"});
        for (Map<String, Object> map : maps) {
            order = new OrderInfo();
            BeanUtil.loadEntity(order, map);
            list.add(order);
        }
        orderPageListInfo.setList(list);
        String jsonStr = Json.getJsonStr(orderPageListInfo);
        return jsonStr;
    }
}

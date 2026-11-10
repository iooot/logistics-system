package com.web.functionModule.orderAction;

import com.domain.User;
import com.web.Application;
import com.web.filter.CommUtil;
import com.web.functionModule.orderAction.domain.AddrInfo;
import com.web.functionModule.orderAction.domain.OrderInfo;
import common.SqlHelper.DataService;
import common.usingtool.BeanUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/dispOrder")
public class DispOrderAction {

    @RequestMapping("/previewOrder")
    public String previewOrder(HttpServletRequest request, HttpServletResponse response, String id) throws Exception {

        HttpSession session = request.getSession();
        boolean b = CommUtil.permissionAuthentication(session);
        if (b) {
            String sql = "SELECT * FROM sys_fuc_order where id = ?";
            List<Map<String, Object>> maps = DataService.getDataTable(sql, new String[]{id});
            OrderInfo orderInfo = new OrderInfo();
            if (maps.size() > 0) {
                Map<String, Object> map = maps.get(0);
                BeanUtil.loadEntity(orderInfo, map);
            }
            request.setAttribute("orderInfo", orderInfo);
            return "/function/order/previewOrderForm.jsp";
        }
        return "500--无访问权限";
    }

    //跳转到新增订单界面
    @RequestMapping("/addNewOrder")
    public String addNewOrder(HttpServletRequest request) throws Exception {
        HttpSession session = request.getSession();
        User usr = (User) session.getAttribute("user");
        OrderInfo orderInfo = new OrderInfo();
        LocalDate currentDate = LocalDate.now();
        String year = String.valueOf(currentDate.getYear());
        String month;
        int monthInt = currentDate.getMonthValue();
        if (monthInt <= 9) {
            month = "0" + monthInt;
        } else {
            month = "" + monthInt;
        }
        String day;
        int dayInt = currentDate.getDayOfMonth();
        if (dayInt <= 9) {
            day = "0" + dayInt;
        } else {
            day = "" + dayInt;
        }
        orderInfo.setOrdertime(year + "-" + month + "-" + day);
        String orderPreNum = "ORD_";
        String orderNum = orderPreNum + year + month + day;
        String orderNumSql = "SELECT * FROM sys_fuc_order WHERE ordernum LIKE '" + orderNum + "%' order by ordernum desc";
        List<Map<String, Object>> orderNumMaps = DataService.getDataTable(orderNumSql, new String[]{});
        if (orderNumMaps.size() == 0) {
            orderNum = orderNum + "001";
        } else {
            Map<String, Object> map = orderNumMaps.get(0);
            String ordernum;
            try {
                ordernum = "1" + map.get("ordernum").toString().replace(orderNum, "");
                String ordernumTemp = String.valueOf(Integer.valueOf(ordernum) + 1);
                orderNum = orderNum + ordernumTemp.substring(1);
            } catch (Exception e) {
                orderNum = orderNum + "001";
            }
        }
        orderInfo.setOrdernum(orderNum);
        orderInfo.setOrderuserid(usr.getId());
        orderInfo.setOrderuser(usr.getUsername());
        request.setAttribute("orderInfo", orderInfo);
        return "/function/order/addNewOrderForm.jsp";

    }


    //跳转到订单变更界面
    @RequestMapping("/changeOrder")
    public String changeOrder(HttpServletRequest request, HttpServletResponse response, String id) throws Exception {
        HttpSession session = request.getSession();
        User usr = (User) session.getAttribute("user");
        request.setAttribute("usr", usr);
        boolean b = CommUtil.permissionAuthentication(session);
        if (b) {
            String sql = "SELECT * FROM sys_fuc_order where id = ?";
            List<Map<String, Object>> maps = DataService.getDataTable(sql, new String[]{id});
            OrderInfo orderInfo = new OrderInfo();
            if (maps.size() > 0) {
                Map<String, Object> map = maps.get(0);
                BeanUtil.loadEntity(orderInfo, map);
            }
            request.setAttribute("orderInfo", orderInfo);
            return "/function/order/changeOrderForm.jsp";
        }
        return "500--无访问权限";
    }


    @RequestMapping("/checkhisotryorder")
    public String checkhisotryorder(HttpServletRequest request, HttpServletResponse response, String oriorderid) throws Exception {

        HttpSession session = request.getSession();
        boolean b = CommUtil.permissionAuthentication(session);
        if (b) {
            List<OrderInfo> list = new ArrayList<>();
            String sql = "SELECT * FROM sys_fuc_order where oriorderid = ? order by createtime desc";
            List<Map<String, Object>> maps = DataService.getDataTable(sql, new String[]{oriorderid});
            OrderInfo orderInfo;
            for (Map<String, Object> map : maps) {
                orderInfo = new OrderInfo();
                BeanUtil.loadEntity(orderInfo, map);
                list.add(orderInfo);
            }
            request.setAttribute("list", list);
            return "/function/order/checkHistoryOrder.jsp";
        }
        return "500--无访问权限";
    }


    //查看订单物流情况
    @RequestMapping("/orderWUL")
    public String orderWUL(HttpServletRequest request, HttpServletResponse response, String id) throws Exception {
        HttpSession session = request.getSession();
        User usr = (User) session.getAttribute("user");
        request.setAttribute("usr", usr);
        boolean b = CommUtil.permissionAuthentication(session);
        if (b) {
            List<AddrInfo> list = new ArrayList<>();
            String sql = "SELECT * FROM sys_fuc_addr where orderid = ? order by createtime desc";
            List<Map<String, Object>> maps = DataService.getDataTable(sql, new String[]{id});
            AddrInfo addrInfo;
            for (Map<String, Object> map : maps) {
                addrInfo = new AddrInfo();
                BeanUtil.loadEntity(addrInfo, map);
                list.add(addrInfo);
            }
            request.setAttribute("addrList", list);
            return "/function/wuliu/orderWU.jsp";
        }
        return "500--无访问权限";
    }


    //查看订单物流情况
    @RequestMapping("/addNewOrderWU")
    public String addNewOrderWU(HttpServletRequest request) throws Exception {
        String sql = "SELECT * FROM sys_fuc_order WHERE orderstute = '' OR orderstute = '正常流转' " +
                "OR orderstute = '物流退单' OR orderstute IS NULL  ORDER BY createtime desc";
        List<Map<String, Object>> maps = DataService.getDataTable(sql, new String[]{});
        List<OrderInfo> orderlist = new ArrayList<>();
        OrderInfo orderInfo;
        for (Map<String, Object> map : maps) {
            orderInfo = new OrderInfo();
            BeanUtil.loadEntity(orderInfo, map);
            orderlist.add(orderInfo);
        }
        request.setAttribute("orderlist", orderlist);
        return "/function/wuliu/addNewOrderWU.jsp";
    }


}

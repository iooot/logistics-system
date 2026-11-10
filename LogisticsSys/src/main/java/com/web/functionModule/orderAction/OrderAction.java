package com.web.functionModule.orderAction;

import com.domain.User;
import com.web.Application;
import com.web.filter.CommUtil;
import com.web.functionModule.orderAction.Controller.Impl.OrderServiceImpl;
import com.web.functionModule.orderAction.Controller.OrderService;
import common.SqlHelper.DataService;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/orderAction")
@Component
public class OrderAction {
    //所有订单列表
    @RequestMapping("/allorder")
    public String allOrder(HttpServletRequest request, String beginordertime, String endordertime, String content, String page) {
        HttpSession session = request.getSession();
        String returnStr = "";
        boolean b = CommUtil.permissionAuthentication(session);
        if (b) {
            User usr = (User) session.getAttribute("user");
            OrderService orderService = new OrderServiceImpl();
            returnStr = orderService.allOrderList(usr, beginordertime, endordertime, content, page);

        }
        return returnStr;
    }

    //所有订单列表
    @RequestMapping("/personorder")
    public String personOrder(HttpServletRequest request, String beginordertime, String endordertime, String content, String page) {
        HttpSession session = request.getSession();
        String returnStr = "";
        boolean b = CommUtil.permissionAuthentication(session);
        if (b) {
            User usr = (User) session.getAttribute("user");
            OrderService orderService = new OrderServiceImpl();
            returnStr = orderService.personOrder(usr, beginordertime, endordertime, content, page);
        }
        return returnStr;
    }


    //新增订单
    @RequestMapping("/saveOrder")
    public String saveOrder(HttpServletRequest request, String ordertime, String orderuser, String orderuserid,
                            String ordernum, String ordermoney, String sendWU, String reviceWU, String revicename
            , String tel, String protype, String proheight, String bem) {
        HttpSession session = request.getSession();
        String returnStr = "";
        boolean b = CommUtil.permissionAuthentication(session);
        if (b) {
            String sql = "INSERT INTO sys_fuc_order(id,ordertime,orderuser,orderuserid,ordernum,ordermoney,sendWU," +
                    "reviceWU,revicename,tel,protype,proheight,bem,createtime) value(uuid(),?,?,?,?,?,?," +
                    "?,?,?,?,?,?,now())";
            String[] arr = new String[]{ordertime, orderuser, orderuserid, ordernum, ordermoney, sendWU, reviceWU,
                    revicename, tel, protype, proheight, bem};
            int execute = DataService.execute(sql, arr);
            returnStr = String.valueOf(execute);
        }
        return returnStr;
    }


    @RequestMapping("/changeSaveOrder")
    public String changeSaveOrder(HttpServletRequest request, String id, String ordermoney, String sendWU,
                                  String reviceWU, String revicename, String tel, String protype,
                                  String proheight, String bem) {
        HttpSession session = request.getSession();
        String returnStr = "";
        boolean b = CommUtil.permissionAuthentication(session);
        if (b) {
            String sql = "update sys_fuc_order set ordermoney=?,sendWU=?,reviceWU=?,revicename=?," +
                    "tel=?,protype=?,proheight=?,bem=? where id=?";
            String[] arr = new String[]{ordermoney, sendWU, reviceWU, revicename, tel, protype, proheight, bem, id};
            int execute = DataService.execute(sql, arr);
            returnStr = String.valueOf(execute);
        }
        return returnStr;
    }


    //删除订单
    @RequestMapping("/deleteOrder")
    public String deleteOrder(HttpServletRequest request, String id) {
        HttpSession session = request.getSession();
        String returnStr = "";
        boolean b = CommUtil.permissionAuthentication(session);
        if (b) {
            String sql = "delete from sys_fuc_order where id = ?";
            String[] arr = new String[]{id};
            int execute = DataService.execute(sql, arr);
            returnStr = String.valueOf(execute);
        }
        return returnStr;
    }


    //添加物流信息
    @RequestMapping("/sendOrderWU")
    public String sendOrderWU(HttpServletRequest request, String ordertitle, String ordertime,
                              String orderstute, String addr) {
        HttpSession session = request.getSession();
        String returnStr = "";
        boolean b = CommUtil.permissionAuthentication(session);
        if (b) {
            String sql = "select id from sys_fuc_order where ordernum = ?";
            String id = DataService.getFirstValue(sql, new String[]{ordertitle});
            sql = "insert into sys_fuc_addr(id,orderid,cartaddress,createtime,orderstute,ctime) " +
                    "value(uuid(),?,?,?,?,now())";
            int execute = DataService.execute(sql, new String[]{id, addr, ordertime, orderstute});
            returnStr = String.valueOf(execute);
        }
        return returnStr;
    }


}

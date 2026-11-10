package com.web.functionModule.orderAction.Controller;


import com.domain.User;

public interface OrderService {
    String allOrderList(User usr, String beginordertime, String endordertime, String content, String page);

    String personOrder(User usr, String beginordertime, String endordertime, String content, String page);
}

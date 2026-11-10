package com.web.functionModule.orderAction.domain;

public class AddrInfo {
    private String id;
    private String orderid;
    private String cartaddress;
    private String createtime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOrderid() {
        return orderid;
    }

    public void setOrderid(String orderid) {
        this.orderid = orderid;
    }

    public String getCartaddress() {
        return cartaddress;
    }

    public void setCartaddress(String cartaddress) {
        this.cartaddress = cartaddress;
    }

    public String getCreatetime() {
        return createtime;
    }

    public void setCreatetime(String createtime) {
        this.createtime = createtime;
    }
}

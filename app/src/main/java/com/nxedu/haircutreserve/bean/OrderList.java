package com.nxedu.haircutreserve.bean;

import java.io.Serializable;

/**
 * <p>@description: 订单实体类</p>
 *
 * @author lizilei
 * @version 1.0.0
 * @Email lizilei_warms@163.com
 * @since 2017/4/11
 */

public class OrderList implements Serializable {
    private int order_id;
    private int order_status;
    private String project_title;
    private String order_price;
    private String cover_pic;
    private String created;
    private String business_name;
    private String user_name;
    private String phone;

    public OrderList(int order_id, int order_status, String project_title,
                         String order_price, String cover_pic, String created,
                         String business_name, String user_name, String phone) {
        super();
        this.order_id = order_id;
        this.order_status = order_status;
        this.project_title = project_title;
        this.order_price = order_price;
        this.cover_pic = cover_pic;
        this.created = created;
        this.business_name = business_name;
        this.user_name = user_name;
        this.phone = phone;
    }
    public OrderList() {
        super();
    }
    public int getOrder_id() {
        return order_id;
    }
    public void setOrder_id(int order_id) {
        this.order_id = order_id;
    }
    public int getOrder_status() {
        return order_status;
    }
    public void setOrder_status(int order_state) {
        this.order_status = order_status;
    }
    public String getProject_title() {
        return project_title;
    }
    public void setProject_title(String project_title) {
        this.project_title = project_title;
    }
    public String getOrder_price() {
        return order_price;
    }
    public void setOrder_price(String order_price) {
        this.order_price = order_price;
    }
    public String getCover_pic() {
        return cover_pic;
    }
    public void setCover_pic(String cover_pic) {
        this.cover_pic = cover_pic;
    }
    public String getCreated() {
        return created;
    }
    public void setCreated(String created) {
        this.created = created;
    }
    public String getBusiness_name() {
        return business_name;
    }
    public void setBusiness_name(String business_name) {
        this.business_name = business_name;
    }
    public String getUser_name() {
        return user_name;
    }
    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }
    public String getPhone() {
        return phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "OrderList{" +
                "order_id=" + order_id +
                ", order_status=" + order_status +
                ", project_title='" + project_title + '\'' +
                ", order_price='" + order_price + '\'' +
                ", cover_pic='" + cover_pic + '\'' +
                ", created='" + created + '\'' +
                ", business_name='" + business_name + '\'' +
                ", user_name='" + user_name + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }
}

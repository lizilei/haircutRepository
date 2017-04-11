package com.nxedu.haircutreserve.bean;

import java.io.Serializable;
import java.util.List;

/**
 * <p>@description: 订单实体类</p>
 *
 * @author lizilei
 * @version 1.0.0
 * @Email lizilei_warms@163.com
 * @since 2017/4/11
 */

public class OrderList implements Serializable {

    /**
     * "project_id": "0",//项目id
     * 'created': "2016-06-27 19:30:14",//创建时间
     * 'order_status': "1",  //订单状态1未付款2已付款
     * 'phone': "15123698527",//电话
     * 'user_name': "15123698527",//用户名
     * 'order_price': "100",//价格
     * 'business_id': "2",//项目类型id
     * 'project_title': null,  //项目标题
     * 'cover_pic': "http://localhost", //封面图
     * 'order_id': 320,//订单编号
     * data : {"country_immigrant_type_id":"1","immigrant_type_id":"1","cit_title":"EB5移民"}
     */
    private String company_id;
    private String order_id;
    private String business_id;
    private String project_id;
    private String project_title;
    private String order_status;
    private String user_name;
    private String created;
    private String country_id;
    private String country_name_cn;
    private String adviser_name;
    private String adviser_user_id;
    private String adviser_avatar;
    private String cover_pic;
    private String phone;
    private String order_price;
    private OrderData data;
    private List<IdCard> idcard;

    public List<IdCard> getIdcard() {
        return idcard;
    }

    public void setIdcard(List<IdCard> idcard) {
        this.idcard = idcard;
    }

    public String getOrder_price() {
        return order_price;
    }

    public void setOrder_price(String order_price) {
        this.order_price = order_price;
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

    public OrderData getData() {
        return data;
    }

    public void setData(OrderData data) {
        this.data = data;
    }

    public String getCompany_id() {
        return company_id;
    }

    public void setCompany_id(String company_id) {
        this.company_id = company_id;
    }

    public String getOrder_id() {
        return order_id;
    }

    public void setOrder_id(String order_id) {
        this.order_id = order_id;
    }

    public String getBusiness_id() {
        return business_id;
    }

    public void setBusiness_id(String business_id) {
        this.business_id = business_id;
    }

    public String getProject_id() {
        return project_id;
    }

    public void setProject_id(String project_id) {
        this.project_id = project_id;
    }

    public String getProject_title() {
        return project_title;
    }

    public void setProject_title(String project_title) {
        this.project_title = project_title;
    }

    public String getOrder_status() {
        return order_status;
    }

    public void setOrder_status(String order_status) {
        this.order_status = order_status;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public String getCountry_id() {
        return country_id;
    }

    public void setCountry_id(String country_id) {
        this.country_id = country_id;
    }

    public String getCountry_name_cn() {
        return country_name_cn;
    }

    public void setCountry_name_cn(String country_name_cn) {
        this.country_name_cn = country_name_cn;
    }

    public String getAdviser_name() {
        return adviser_name;
    }

    public void setAdviser_name(String adviser_name) {
        this.adviser_name = adviser_name;
    }

    public String getAdviser_user_id() {
        return adviser_user_id;
    }

    public void setAdviser_user_id(String adviser_user_id) {
        this.adviser_user_id = adviser_user_id;
    }

    public String getAdviser_avatar() {
        return adviser_avatar;
    }

    public void setAdviser_avatar(String adviser_avatar) {
        this.adviser_avatar = adviser_avatar;
    }

    public String getCover_pic() {
        return cover_pic;
    }

    public void setCover_pic(String cover_pic) {
        this.cover_pic = cover_pic;
    }

    @Override
    public String toString() {
        return "OrderList{" +
                "company_id='" + company_id + '\'' +
                ", order_id='" + order_id + '\'' +
                ", business_id='" + business_id + '\'' +
                ", project_id='" + project_id + '\'' +
                ", project_title='" + project_title + '\'' +
                ", order_status='" + order_status + '\'' +
                ", user_name='" + user_name + '\'' +
                ", created='" + created + '\'' +
                ", country_id='" + country_id + '\'' +
                ", country_name_cn='" + country_name_cn + '\'' +
                ", adviser_name='" + adviser_name + '\'' +
                ", adviser_user_id='" + adviser_user_id + '\'' +
                ", adviser_avatar='" + adviser_avatar + '\'' +
                ", cover_pic='" + cover_pic + '\'' +
                ", phone='" + phone + '\'' +
                ", order_price='" + order_price + '\'' +
                ", data=" + data +
                ", idcard=" + idcard +
                '}';
    }
}

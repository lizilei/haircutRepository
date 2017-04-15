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
     * code : 0
     * msg : success
     * body : [{"order_id":2,"order_status":2,"project_title":"靓装造型","order_price":"60","cover_pic":"https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1492744264&di=d9f7a3bdc000336d09","created":"2017-04-11 00:00:00.0","business_name":"补水护发","user_name":"lizilei","tel":"17301207022","distance":"14.3km","address":"兴庆区进宁北街66-10妇幼保健院向北50米"},{"order_id":3,"order_status":1,"project_title":"佑米造型（金凤万达店）","order_price":"80","cover_pic":"https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1492744209&di=96a46488dcdfd543d6","created":"2017-04-14 00:00:00.0","business_name":"洗剪吹","user_name":"lizilei","tel":"17301207022","distance":"11.4km","address":"金凤区正源北街南侧金凤万达广场B座1607号公寓"},{"order_id":4,"order_status":1,"project_title":"英皇沙宣国际美发沙龙（西夏万达店）","order_price":"120","cover_pic":"https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1492745259&di=23c20a2435989d7fd2","created":"2017-03-22 14:33:50.0","business_name":"美发","user_name":"lizilei","tel":"17301207022","distance":"2.4km","address":"西夏区西夏万达公寓b座1503室"}]
     */

    private int code;
    private String msg;
    private List<BodyBean> body;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<BodyBean> getBody() {
        return body;
    }

    public void setBody(List<BodyBean> body) {
        this.body = body;
    }

    public static class BodyBean implements Serializable{
        /**
         * order_id : 2
         * order_status : 2
         * project_title : 靓装造型
         * order_price : 60
         * cover_pic : https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1492744264&di=d9f7a3bdc000336d09
         * created : 2017-04-11 00:00:00.0
         * business_name : 补水护发
         * user_name : lizilei
         * tel : 17301207022
         * distance : 14.3km
         * address : 兴庆区进宁北街66-10妇幼保健院向北50米
         */

        private int order_id;
        private int order_status;
        private String project_title;
        private String order_price;
        private String cover_pic;
        private String created;
        private String business_name;
        private String user_name;
        private String tel;
        private String distance;
        private String address;

        public int getOrder_id() {
            return order_id;
        }

        public void setOrder_id(int order_id) {
            this.order_id = order_id;
        }

        public int getOrder_status() {
            return order_status;
        }

        public void setOrder_status(int order_status) {
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

        public String getTel() {
            return tel;
        }

        public void setTel(String tel) {
            this.tel = tel;
        }

        public String getDistance() {
            return distance;
        }

        public void setDistance(String distance) {
            this.distance = distance;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }
    }
}

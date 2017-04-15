package com.nxedu.haircutreserve.bean;

import java.util.List;

/**
 * /@Description
 * /r
 *
 * @author lzl
 * @version 1.0.0
 * @Email lizilei_warms@163.com
 * @since 2017/4/15
 */

public class GoodShopBean {

    /**
     * code : 0
     * msg : success
     * body : [{"id":1,"name":"佑米造型（金凤万达店）","address":"正源北街南侧金凤万达广场B座1607号公寓","imageurl":"http://p0.meituan.net/deal/7cb936a041b150010d08c31b2dc3baab103356.jpg","distance":"11.4km","evaluation_number":"1000","satisfactory_number":"655","satisfaction":"65.5%","tel":"17301207022"},{"id":2,"name":"靓装造型","address":"银川市兴庆区仁义巷54号","imageurl":"http://p0.meituan.net/deal/7cb936a041b150010d08c31b2dc3baab103356.jpg","distance":"14.3km","evaluation_number":"2351","satisfactory_number":"1764","satisfaction":"75%","tel":"18321964563"},{"id":3,"name":"英皇沙宣国际美发沙龙（西夏万达店）","address":"西夏区西夏万达公寓b座1503室","imageurl":"http://p0.meituan.net/deal/7cb936a041b150010d08c31b2dc3baab103356.jpg","distance":"11.4km","evaluation_number":"1766","satisfactory_number":"1145","satisfaction":"64.8%","tel":"15801201111"}]
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

    public static class BodyBean {
        /**
         * id : 1
         * name : 佑米造型（金凤万达店）
         * address : 正源北街南侧金凤万达广场B座1607号公寓
         * imageurl : http://p0.meituan.net/deal/7cb936a041b150010d08c31b2dc3baab103356.jpg
         * distance : 11.4km
         * evaluation_number : 1000
         * satisfactory_number : 655
         * satisfaction : 65.5%
         * tel : 17301207022
         */

        private int id;
        private String name;
        private String address;
        private String imageurl;
        private String distance;
        private String evaluation_number;
        private String satisfactory_number;
        private String satisfaction;
        private String tel;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getImageurl() {
            return imageurl;
        }

        public void setImageurl(String imageurl) {
            this.imageurl = imageurl;
        }

        public String getDistance() {
            return distance;
        }

        public void setDistance(String distance) {
            this.distance = distance;
        }

        public String getEvaluation_number() {
            return evaluation_number;
        }

        public void setEvaluation_number(String evaluation_number) {
            this.evaluation_number = evaluation_number;
        }

        public String getSatisfactory_number() {
            return satisfactory_number;
        }

        public void setSatisfactory_number(String satisfactory_number) {
            this.satisfactory_number = satisfactory_number;
        }

        public String getSatisfaction() {
            return satisfaction;
        }

        public void setSatisfaction(String satisfaction) {
            this.satisfaction = satisfaction;
        }

        public String getTel() {
            return tel;
        }

        public void setTel(String tel) {
            this.tel = tel;
        }
    }
}

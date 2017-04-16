package com.nxedu.haircutreserve.bean;

import java.io.Serializable;
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

public class HaircutList implements Serializable{


    /**
     * code : 0
     * msg : success
     * body : [{"id":1,"name":"张伟","address":"正源北街南侧金凤万达广场B座1607号公寓","imageurl":"http://img.hairbobo.com/uploadimg/16/05/27/160527100515756.jpg","distance":"11.4km","concessionalprice":"18","price":"30","shop_id":1,"shop_name":"佑米造型（金凤万达店）"},{"id":2,"name":"李伟","address":"正源北街南侧金凤万达广场B座1607号公寓","imageurl":"http://img.hairbobo.com/uploadimg/17/04/06/170406000638556.jpg","distance":"11.4km","concessionalprice":"99","price":"133","shop_id":1,"shop_name":"佑米造型（金凤万达店）"},{"id":3,"name":"马伟","address":"正源北街南侧金凤万达广场B座1607号公寓","imageurl":"http://img.hairbobo.com/uploadimg/16/05/12/160512090317650.jpg","distance":"11.4km","concessionalprice":"199","price":"300","shop_id":1,"shop_name":"佑米造型（金凤万达店）"},{"id":4,"name":"何伟","address":"正源北街南侧金凤万达广场B座1607号公寓","imageurl":"http://img.hairbobo.com/uploadimg/16/03/22/160322174703769.jpg","distance":"11.4km","concessionalprice":"299","price":"500","shop_id":1,"shop_name":"佑米造型（金凤万达店）"},{"id":5,"name":"王俊","address":"银川市兴庆区仁义巷54号","imageurl":"http://img.hairbobo.com/uploadimg/17/03/10/170310170627678.jpg","distance":"14.3km","concessionalprice":"39","price":"50","shop_id":2,"shop_name":"靓装造型"},{"id":6,"name":"李俊","address":"银川市兴庆区仁义巷54号","imageurl":"http://img.hairbobo.com/uploadimg/15/12/10/151210111403218.jpg","distance":"14.3km","concessionalprice":"99","price":"120","shop_id":2,"shop_name":"靓装造型"},{"id":7,"name":"赵俊","address":"银川市兴庆区仁义巷54号","imageurl":"http://img.hairbobo.com/uploadimg/16/10/30/161030161444365.jpg","distance":"14.3km","concessionalprice":"199","price":"300","shop_id":2,"shop_name":"靓装造型"},{"id":8,"name":"何俊","address":"银川市兴庆区仁义巷54号","imageurl":"http://img.hairbobo.com/uploadimg/16/10/14/161014135017720.jpg","distance":"14.3km","concessionalprice":"299","price":"500","shop_id":2,"shop_name":"英皇沙宣国际美发沙龙（西夏万达店）"},{"id":9,"name":"马腾","address":"西夏区西夏万达公寓b座1503室","imageurl":"http://img.hairbobo.com/uploadimg/16/05/22/160522193014231.jpg","distance":"11.4km","concessionalprice":"29","price":"50","shop_id":3,"shop_name":"英皇沙宣国际美发沙龙（西夏万达店）"},{"id":10,"name":"胡腾","address":"西夏区西夏万达公寓b座1503室","imageurl":"http://img.hairbobo.com/uploadimg/16/06/30/160630130435471.jpg","distance":"11.4km","concessionalprice":"99","price":"120","shop_id":3,"shop_name":"英皇沙宣国际美发沙龙（西夏万达店）"},{"id":11,"name":"李腾","address":"西夏区西夏万达公寓b座1503室","imageurl":"http://img.hairbobo.com/uploadimg/16/10/10/161010113825127.jpg","distance":"11.4km","concessionalprice":"199","price":"300","shop_id":3,"shop_name":"英皇沙宣国际美发沙龙（西夏万达店）"},{"id":12,"name":"吴腾","address":"西夏区西夏万达公寓b座1503室","imageurl":"http://img.hairbobo.com/uploadimg/16/08/02/160802120818082.jpg","distance":"11.4km","concessionalprice":"299","price":"500","shop_id":3,"shop_name":"英皇沙宣国际美发沙龙（西夏万达店）"}]
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
         * id : 1
         * name : 张伟
         * address : 正源北街南侧金凤万达广场B座1607号公寓
         * imageurl : http://img.hairbobo.com/uploadimg/16/05/27/160527100515756.jpg
         * distance : 11.4km
         * concessionalprice : 18
         * price : 30
         * shop_id : 1
         * shop_name : 佑米造型（金凤万达店）
         */

        private int id;
        private String name;
        private String address;
        private String imageurl;
        private String distance;
        private String concessionalprice;
        private String price;
        private int shop_id;
        private String shop_name;

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

        public String getConcessionalprice() {
            return concessionalprice;
        }

        public void setConcessionalprice(String concessionalprice) {
            this.concessionalprice = concessionalprice;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public int getShop_id() {
            return shop_id;
        }

        public void setShop_id(int shop_id) {
            this.shop_id = shop_id;
        }

        public String getShop_name() {
            return shop_name;
        }

        public void setShop_name(String shop_name) {
            this.shop_name = shop_name;
        }
    }
}

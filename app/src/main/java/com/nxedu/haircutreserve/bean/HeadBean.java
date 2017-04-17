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

public class HeadBean {

    /**
     * code : 0
     * msg : success
     * body : [{"title":"4\u201412岁小女孩编发 儿童精美编发图片","iamgeurl":"http://img.faxingzhan.com/170414/65-1F4141201014J_","contenturl":"http://m.faxingzhan.com/a/45711.html"},{"title":"花样男士寸头发型 演绎帅气硬汉气概","iamgeurl":"http://img.faxingzhan.com/170414/65-1F414145521Y2_","contenturl":"http://m.faxingzhan.com/a/45683.html"},{"title":"2017年流行\u201c齐腮短发\u201d 风靡时尚圈的短发","iamgeurl":"http://img.faxingzhan.com/170414/65-1F414120029D1_","contenturl":"http://m.faxingzhan.com/a/45714.html"},{"title":"烫发与发色搭配 你的脸型适合哪款","iamgeurl":"http://img.faxingzhan.com/170413/65-1F41313403LN_1","contenturl":"http://m.faxingzhan.com/a/45726.html"},{"title":"两颗才够有范CP魔力双丸子头","iamgeurl":"http://img.faxingzhan.com/170413/65-1F41312035A24_","contenturl":"http://m.faxingzhan.com/a/45686.html"}]
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
         * title : 4—12岁小女孩编发 儿童精美编发图片
         * iamgeurl : http://img.faxingzhan.com/170414/65-1F4141201014J_
         * contenturl : http://m.faxingzhan.com/a/45711.html
         */

        private String title;
        private String imageurl;
        private String contenturl;

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getImageurl() {
            return imageurl;
        }

        public void setImageurl(String imageurl) {
            this.imageurl = imageurl;
        }

        public String getContenturl() {
            return contenturl;
        }

        public void setContenturl(String contenturl) {
            this.contenturl = contenturl;
        }

        @Override
        public String toString() {
            return "BodyBean{" +
                    "title='" + title + '\'' +
                    ", imageurl='" + imageurl + '\'' +
                    ", contenturl='" + contenturl + '\'' +
                    '}';
        }
    }
}

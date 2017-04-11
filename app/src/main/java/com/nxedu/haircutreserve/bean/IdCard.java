package com.nxedu.haircutreserve.bean;

import java.io.Serializable;

/**
 * <p>@description:身份信息</p>
 *
 * @author lizilei
 * @version 1.0.0
 * @Email lizilei_warms@163.com
 * @since 2017/4/11
 */

public class IdCard implements Serializable {

    /**
     * idcard_name :
     * idcard_no :
     */

    private String idcard_name;
    private String order_idcard_id;
    private String idcard_no;

    public IdCard(String idcard_name, String order_idcard_id, String idcard_no) {
        this.idcard_name = idcard_name;
        this.order_idcard_id = order_idcard_id;
        this.idcard_no = idcard_no;
    }

    public IdCard() {
    }

    public String getIdcard_name() {
        return idcard_name;
    }

    public void setIdcard_name(String idcard_name) {
        this.idcard_name = idcard_name;
    }

    public String getOrder_idcard_id() {
        return order_idcard_id;
    }

    public void setOrder_idcard_id(String order_idcard_id) {
        this.order_idcard_id = order_idcard_id;
    }

    public String getIdcard_no() {
        return idcard_no;
    }

    public void setIdcard_no(String idcard_no) {
        this.idcard_no = idcard_no;
    }

    @Override
    public String toString() {
        return "IdCard{" +
                "idcard_name='" + idcard_name + '\'' +
                ", order_idcard_id='" + order_idcard_id + '\'' +
                ", idcard_no='" + idcard_no + '\'' +
                '}';
    }
}

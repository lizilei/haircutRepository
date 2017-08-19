package com.nxedu.haircutreserve.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.annotation.Generated;

/**
 * <p>@description:个人中心存储文字和图片实体类</p>
 *
 * @author lizilei
 * @version 1.0.0
 * @Email lizilei_warms@163.com
 * @since 2017/3/23
 */
@Entity
public class MeData {
    @Id
    private Long id;
    @Property(nameInDb = "TEXT")
    private String text;
    @Property(nameInDb = "IMGID")
    private int imgId;
    @Generated(hash = 658270807)
    public MeData(Long id, String text, int imgId) {
        this.id = id;
        this.text = text;
        this.imgId = imgId;
    }
    @Generated(hash = 467582106)
    public MeData() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getText() {
        return this.text;
    }
    public void setText(String text) {
        this.text = text;
    }
    public int getImgId() {
        return this.imgId;
    }
    public void setImgId(int imgId) {
        this.imgId = imgId;
    }
}

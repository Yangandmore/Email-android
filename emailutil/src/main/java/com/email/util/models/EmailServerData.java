package com.email.util.models;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.annotation.Transient;

import java.io.Serializable;

/**
 * Created by shiq_yang on 2017/8/28.
 * 邮箱服务器数据
 */

@Entity
public class EmailServerData implements Serializable, Cloneable{

    @Transient
    private static final long serialVersionUID = -1724884572685719187L;

    @Id(autoincrement = true)
    private Long id;

    /**
     * 发送服务器
     */
    @Property(nameInDb = "SENDSERVERSTRING")
    private String sendServerString = "";

    /**
     * 收取服务器
     */
    @Property(nameInDb = "RECEIVERSERVERSTRING")
    private String receiverServerString = "";

    /**
     * 邮箱
     */
    @Property(nameInDb = "EMAIL")
    private String emailNameString = "";

    /**
     * 密码
     */
    @Property(nameInDb = "PASSWORD")
    private String emailPasswordString = "";

    @Generated(hash = 1558207200)
    public EmailServerData(Long id, String sendServerString,
            String receiverServerString, String emailNameString,
            String emailPasswordString) {
        this.id = id;
        this.sendServerString = sendServerString;
        this.receiverServerString = receiverServerString;
        this.emailNameString = emailNameString;
        this.emailPasswordString = emailPasswordString;
    }

    @Generated(hash = 201852343)
    public EmailServerData() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSendServerString() {
        return sendServerString;
    }

    public void setSendServerString(String sendServerString) {
        this.sendServerString = sendServerString;
    }

    public String getReceiverServerString() {
        return receiverServerString;
    }

    public void setReceiverServerString(String receiverServerString) {
        this.receiverServerString = receiverServerString;
    }

    public String getEmailNameString() {
        return emailNameString;
    }

    public void setEmailNameString(String emailNameString) {
        this.emailNameString = emailNameString;
    }

    public String getEmailPasswordString() {
        return emailPasswordString;
    }

    public void setEmailPasswordString(String emailPasswordString) {
        this.emailPasswordString = emailPasswordString;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}

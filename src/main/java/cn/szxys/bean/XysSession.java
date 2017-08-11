package cn.szxys.bean;

import cn.szxys.security.SecurityUtil;

import javax.xml.crypto.Data;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2017/8/7.
 */
public class XysSession {
    private String  sessionID;
    private String  wxCode;
    private String  wxSessionKey;
    private String wxOpenID;
    private String wxUnionID;
    private long  expireTime;

    public XysSession(String wxCode, String wxSessionKey, String wxOpenID, String wxUnionID) {
        this.wxCode = wxCode;
        this.wxSessionKey = wxSessionKey;
        this.wxOpenID = wxOpenID;
        this.wxUnionID = wxUnionID;
        //  过期时间默认为12小时,单位是秒级long数据
        this.expireTime = Calendar.getInstance().getTimeInMillis()/1000+3600*12;
        Map<String,String> param = new HashMap<String,String>();
        param.put("wxCode",wxCode);
        param.put("timeStamp",""+Calendar.getInstance().getTimeInMillis());
        this.sessionID = SecurityUtil.generateToken(param);
    }

    public boolean isSessionExpire()
    {
        return Calendar.getInstance().getTimeInMillis()/1000 - expireTime > 0 ;
    }

    public String getSessionID() {
        return sessionID;
    }

    public void setSessionID(String sessionID) {
        this.sessionID = sessionID;
    }

    public String getWxCode() {
        return wxCode;
    }

    public void setWxCode(String wxCode) {
        this.wxCode = wxCode;
    }

    public String getWxSessionKey() {
        return wxSessionKey;
    }

    public void setWxSessionKey(String wxSessionKey) {
        this.wxSessionKey = wxSessionKey;
    }

    public String getWxOpenID() {
        return wxOpenID;
    }

    public void setWxOpenID(String wxOpenID) {
        this.wxOpenID = wxOpenID;
    }

    public String getWxUnionID() {
        return wxUnionID;
    }

    public void setWxUnionID(String wxUnionID) {
        this.wxUnionID = wxUnionID;
    }
}

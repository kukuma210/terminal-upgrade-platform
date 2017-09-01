package cn.szxys.bean;

/**
 * Created by Administrator on 2017/8/8.
 */
public class WxCode2SessionResult {

    private  String xysSessionID;

    public String getWxSessionKey() {
        return wxSessionKey;
    }

    public void setWxSessionKey(String wxSessionKey) {
        this.wxSessionKey = wxSessionKey;
    }

    private String wxSessionKey;

    public String getXysSessionID() {
        return xysSessionID;
    }

    public void setXysSessionID(String xysSessionID) {
        this.xysSessionID = xysSessionID;
    }
}

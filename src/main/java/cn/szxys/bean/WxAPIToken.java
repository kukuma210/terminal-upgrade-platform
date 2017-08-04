package cn.szxys.bean;

/**
 * Created by Administrator on 2017/8/4.
 */
public class WxAPIToken {
    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    public String getExpires_in() {
        return expires_in;
    }

    public void setExpires_in(String expires_in) {
        this.expires_in = expires_in;
    }

    private String access_token;
   private String expires_in;
}

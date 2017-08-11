package cn.szxys.bean;

/**
 * Created by Administrator on 2017/8/10.
 */
public class wxGZHToken {
    String access_token;
    String expires_in;

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
}

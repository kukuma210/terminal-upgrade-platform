package cn.szxys.bean;

/**
 * Created by Administrator on 2017/8/4.
 */
public class WxBuyTemplateMsg {
    String xysSessionID;
    String formID;
    String title;
    String content;

    public String getXysSessionID() {
        return xysSessionID;
    }

    public void setXysSessionID(String xysSessionID) {
        this.xysSessionID = xysSessionID;
    }

    public String getFormID() {
        return formID;
    }

    public void setFormID(String formID) {
        this.formID = formID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}

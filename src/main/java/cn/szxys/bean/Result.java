package cn.szxys.bean;

/**
 * Created by Administrator on 2017/7/20.
 */
public class Result<T> {
    int msgCode;
    String msg;
    T Data;

    public Result(){
        msgCode = -1;
        msg = "error";
    }

    public void  setOK(){
        msg="ok";
        msgCode = 0;
    }

    public void setError(int iCode,String sMsg)
    {
        msg =sMsg;
        msgCode = iCode;
    }
    public T getData() {
        return Data;
    }

    public void setData(T data) {
        Data = data;
    }

    public int getMsgCode() {
        return msgCode;
    }

    public void setMsgCode(int msgCode) {
        this.msgCode = msgCode;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}

package bean;

/**
 * Created by Administrator on 2017/7/20.
 */
public class Result<T> {
    int msgCode;
    String msg;
    T Data;

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

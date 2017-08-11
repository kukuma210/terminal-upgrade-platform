package cn.szxys.controller;


import cn.szxys.bean.*;
import cn.szxys.security.SessionManager;
import cn.szxys.tools.HttpClientUtil;
import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Administrator on 2017/7/20.
 */
@RestController
public class WebAPI {

    final Logger logger = LoggerFactory.getLogger(WebAPI.class);

    @Autowired
    private Environment env;

    @Autowired
    private SessionManager sessionMan;

    private   WxAPIToken token;
    private wxGZHToken wxSession;

    @RequestMapping("/login")
    public Result checkLogin(HttpServletRequest request)
    {
        // 获取用户名、密码。判断是否正确

        Result  result = new Result();

        result.setMsgCode(0);
        result.setMsg("密码错误！");

        return  result;
    }

    @RequestMapping("/getWXAccessToken")
    public Result getWXAccessToken()
    {
        logger.warn("getWXAccessToken");

        Result<WxAPIToken> res = new Result<WxAPIToken>();

        try{

            String wxUrl = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&" +
                    "appid="+env.getProperty("wx.appid")+
                    "&secret="+env.getProperty("wx.secret");

            String data = HttpClientUtil.sendHttpGet(wxUrl);

            token = (WxAPIToken) JSON.parseObject(data,WxAPIToken.class);

            res.setData(token);

            res.setOK();
        }
        catch (Exception e)
        {

        }

        return res;
    }

    @RequestMapping("/wxCode2Session")
    public Result wxCode2Session(@RequestBody WxCode code)
    {
        logger.warn("wxCode2Session");

        // 使用小程序wx.login 获取到的code 转换成wxSessionKey OpenID等
        String url = "https://api.weixin.qq.com/sns/jscode2session?appid=" +
                env.getProperty("wx.appid")+
                "&secret="+
                env.getProperty("wx.secret")+
                "&js_code="+
                code.getWxCode()+
                "&grant_type=authorization_code";

        String data = HttpClientUtil.sendHttpGet(url);

        WxSession wxSession = (WxSession) JSON.parseObject(data,WxSession.class);

        String s = sessionMan.generateSession(code.getWxCode(),wxSession.getSession_key(),wxSession.getOpenid(),
                wxSession.getExpires_in());

        // 并生成token 并返回给终端使用

        Result<WxCode2SessionResult> res = new Result<WxCode2SessionResult>();

        WxCode2SessionResult cs = new WxCode2SessionResult();
        cs.setXysSessionID(s);

        res.setData(cs);
        res.setOK();

        return res;

    }

    @RequestMapping("/sendWxTemplateMessage")
    public Result sendWxTemplateMessage(@RequestBody  WxBuyTemplateMsg tmsg)
    {
        logger.warn("sendWxTemplateMessage");

        Result res = new Result();

        String templateID = "dQp-B1EJgY-h3ZAeDkJDv5bDJBT7zGzJYMl3ucf6Htg";

        if(!sessionMan.isSessionValid(tmsg.getXysSessionID()))
        {
             return  res;
        }

        XysSession sysS = sessionMan.getXysSession(tmsg.getXysSessionID());

        String url = "https://api.weixin.qq.com/cgi-bin/message/wxopen/template/send?access_token="+
                token.getAccess_token();

        String data = "  {" +
                "            \"touser\":\""+sysS.getWxOpenID()+"\","+
                "                \"template_id\": \"" + templateID + "\","+
                "                \"page\": \"pages/index/index\"," +
                "                \"form_id\": \""+ tmsg.getFormID()+"\"," +
                "                \"data\": {" +
                "            \"keyword1\": {" +
                "                \"value\": \"钟教授\"," +
                "                        \"color\": \"#173177\"" +
                "            }," +
                "            \"keyword2\": {" +
                "                \"value\": \"胡先飞\"," +
                "                        \"color\": \"#173177\"" +
                "            }," +
                "            \"keyword3\": {" +
                "                \"value\": \"深圳市人民医院\"," +
                "                        \"color\": \"#173177\"" +
                "            } ," +
                "            \"keyword4\": {" +
                "                \"value\": \"2017年8月8日16:16:29\"," +
                "                        \"color\": \"#173177\"" +
                "            }" +
                "        }," +
                "            \"emphasis_keyword\": \"keyword1.DATA\"" +
                "        }";


        String ss = HttpClientUtil.sendHttpPostJson(url,data);

        logger.warn(ss);
        res.setOK();
        res.setMsg(ss);
        return res;
    }

    @RequestMapping("/wxGZHGetAccessToken")
    public String wxGZHGetAccessToken(HttpServletRequest request)
    {
        String appid = request.getParameter("APPID");
        String secret = request.getParameter("Secret");
        System.out.println("APPID is:"+appid);
        System.out.println("Secret is:"+secret);

        String url = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid="
        +appid+ "&secret="+ secret;

        String data = HttpClientUtil.sendHttpGet(url);

         wxSession = (wxGZHToken) JSON.parseObject(data,wxGZHToken.class);

        return data;
    }

    @RequestMapping("/sendWxMessage")
    public String sendWxMessage(HttpServletRequest request)
    {
        String xcxAppID = request.getParameter("xcxAppID");
        String xcxPage = request.getParameter("xcxPage");
        System.out.println("xcxAppID is:"+xcxAppID);
        System.out.println("xcxPage is:"+xcxPage);


        String url = "https://api.weixin.qq.com/cgi-bin/message/mass/sendall?access_token="+
                wxSession.getAccess_token();

//        String data = "{" +
//                "\"filter\":{" +
//                "\"is_to_all\":false," +
//                "\"tag_id\":1" +
//                "}," +
//                "\"text\":{" +
//                "\"content\":\"CONTENT\"" +
//                "}" +
//                ",\"msgtype\":\"text\"" +
//                "}";

        String data = "{" +
                "\"filter\":{" +
                "\"is_to_all\":false," +
                "\"tag_id\":1" +
                "}," +
                "\"text\":{" +
                "\"content\":\"<p><a data-miniprogram-appid=\'"+
                xcxAppID+
                "\' data-miniprogram-path=\'"+
                xcxPage +
                "\' href=\'\'>点击文字跳转小程序</a></p>\"" +
                "}," +
                "\"msgtype\":\"text\"" +
                "}";

        String ss = HttpClientUtil.sendHttpPostJson(url,data);

        logger.warn(data);

        logger.warn(ss);

        return ss;
    }
}

package cn.szxys.controller;


import cn.szxys.bean.Result;
import cn.szxys.bean.WxAPIToken;
import cn.szxys.bean.WxBuyTemplateMsg;
import cn.szxys.tools.HttpClientUtil;
import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Administrator on 2017/7/20.
 */
@RestController
public class WebAPI {

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
        Result<WxAPIToken> res = new Result<WxAPIToken>();

        try{

            String wxUrl = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&" +
                    "appid=wx00908e29d3e458c0&secret=5e570055a69d1af66226d9b905d7704b";

            String data = HttpClientUtil.sendHttpGet(wxUrl);

            WxAPIToken token = (WxAPIToken) JSON.parseObject(data,WxAPIToken.class);

            res.setData(token);

            res.setOK();
        }
        catch (Exception e)
        {

        }

        return res;
    }

    @RequestMapping("/sendWxTemplateMessage")
    public Result sendWxTemplateMessage(@RequestBody  WxBuyTemplateMsg tmsg)
    {
        Result res = new Result();
        res.setOK();
        return res;
    }
}

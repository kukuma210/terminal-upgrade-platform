package cn.szxys.controller;


import bean.Result;
import org.springframework.web.bind.annotation.RequestMapping;
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
}

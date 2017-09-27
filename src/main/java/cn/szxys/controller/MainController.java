package cn.szxys.controller;

import cn.szxys.tools.SCaptcha;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by Administrator on 2017/6/15.
 */

@Controller
public class MainController {

  private static final Logger log = LoggerFactory.getLogger(MainController.class);

    @RequestMapping("/")
    public ModelAndView index()
    {
        ModelAndView  mv = new ModelAndView("index");

        return  mv;
    }

    @GetMapping("/error")
    public ModelAndView error()
    {
        ModelAndView  mv = new ModelAndView("error");
        return  mv;
    }

    @RequestMapping("/main")
    public ModelAndView login()
    {

       ModelAndView v = new ModelAndView("main") ;
        return v;
    }

    @RequestMapping("/addUpgradeFile")
    public ModelAndView addUpgradeFile()
    {
        ModelAndView v = new ModelAndView("pages/AddUpgradeFile") ;
        return v;
    }

    @RequestMapping("/test")
    public ModelAndView testPage()
    {
        ModelAndView  v = new ModelAndView("pages/test");

        return v;
    }

}

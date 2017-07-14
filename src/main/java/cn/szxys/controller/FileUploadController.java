package cn.szxys.controller;


import java.io.File;
import java.io.IOException;
import java.util.Iterator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class FileUploadController {

    private static final Logger log = LoggerFactory.getLogger(FileUploadController.class);

    @Value("${web.upload-path}")
    private String uploadFilePath;

    @RequestMapping("/UploadFile")
    public void uploadFile(HttpServletRequest request)
    {
        //将当前上下文初始化给 CommonsMutipartResolver  （多部分解析器）
        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(request.getSession().getServletContext());

        //检查form中是否有enctype="multipart/form-data"
        if(multipartResolver.isMultipart(request))
        {
            //将request变成多部分request
            MultipartHttpServletRequest multiRequest=(MultipartHttpServletRequest)request;

            //获取multiRequest 中所有的文件名
             Iterator iter=multiRequest.getFileNames();
             while(iter.hasNext())
             {
                 //一次遍历所有文件
                 MultipartFile file=multiRequest.getFile(iter.next().toString());

                 if(file!=null)
                 {
                     String fileName =  file.getOriginalFilename();
                     fileName =  fileName.substring(fileName.lastIndexOf("\\")+1);
                     String path= uploadFilePath +fileName;

                     try {

                         File dest = new File(path);
                         // 检测是否存在目录
                         if (!dest.getParentFile().exists()) {
                             dest.getParentFile().mkdirs();
                         }
                         // 保存文件
                         file.transferTo(dest);

                     } catch (IOException e) {
                         e.printStackTrace();
                     }
                 }
             }
        }
    }

}
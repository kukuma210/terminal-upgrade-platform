package cn.szxys.security;

import java.util.*;

/**
 * Created by Administrator on 2017/8/8.
 */
public class SecurityUtil {

    /**
     *
     *  @Description    : 身份验证token值算法：
     *  							算法是：将特定的某几个参数一map的数据结构传入，
     *  							进行字典序排序以后进行md5加密,32位小写加密；
     *  @Method_Name    : authentication
     *  @param srcData   约定用来计算token的参数
     *  @return
     */
    public static String generateToken(Map<String , String > srcData){
        //排序，根据keyde 字典序排序
        if(null == srcData){
            return null;
        }
        List<Map.Entry<String,String>> list = new ArrayList<Map.Entry<String,String>>(srcData.entrySet());
        Collections.sort(list, new Comparator<Map.Entry<String, String>>(){
            //升序排序
            public int compare(Map.Entry<String,String> o1, Map.Entry<String,String> o2){
                return o1.getKey().compareTo(o2.getKey());
            }
        });

        StringBuffer srcSb = new StringBuffer();
        for(Map.Entry<String , String>srcAtom : list){
            srcSb.append(String.valueOf(srcAtom.getValue()));
        }
        System.out.println("身份验证加密前字符串："+srcSb.toString());
        //计算token
        String token = MD5Util.md5(srcSb.toString());
//		System.out.println(cToken);//for test
        return token;
    }


}
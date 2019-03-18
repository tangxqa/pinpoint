package com.navercorp.pinpoint.web.alarm;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;


public class SmsUtils {
    // 企业Id 17824
    public static final String ENTERPRISE_ID = "17824";
	
    //登录名 admin
    public static final String LOGIN_NAME = "admin";
	
    //密码 3441808d79397ffcc8e90137176c7425
    public static final String PASSWORD = "3441808d79397ffcc8e90137176c7425";
	
    //发送短信路径
	//http://113.108.68.228:8001/sendSMS.action
    public static final String SEND_MESSAGE_PATH = "http://113.108.68.228:8001/sendSMS.action";
	

    /**
     * 调取短信接口发送验证码用于App登录功能
     * @param mobile
     * @return 所代表远程资源的响应结果
     */
    public static boolean sendVerificationCodeForLogin(String mobile,String content) {
        StringBuffer stringBuffer  = new StringBuffer();
        try {
            stringBuffer.append("enterpriseID="+ENTERPRISE_ID+"");
            stringBuffer.append("&loginName="+LOGIN_NAME+"");
            stringBuffer.append("&password="+PASSWORD+"");
            stringBuffer.append("&mobiles="+mobile+"");
            int randomNum = (int)(Math.random() * 9000 + 1000);
            stringBuffer.append("&content="+URLEncoder.encode("【日日顺平台报警】"+content + randomNum, "UTF-8"));
            stringBuffer.append("&sendTime=");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        try {
            URL url = new URL(SEND_MESSAGE_PATH);
            URLConnection conn = url.openConnection();
            conn.setDoOutput(true);
            OutputStreamWriter writer = new OutputStreamWriter(conn.getOutputStream());
            writer.write(stringBuffer.toString());
            writer.flush();
            //获取返回
            StringBuffer answer = new StringBuffer();
            BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                answer.append(line);
            }
            writer.close();
            reader.close();
            System.out.println(answer.toString());
            String result = answer.substring(answer.indexOf("<Result>")+8, answer.lastIndexOf("</Result>"));
            if(result.equals("0")){
                return true;
            }else{
                return false;
            }
        } catch (MalformedURLException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return true;
    }


    public static void main(String args[]) {
//        sendVerificationCodeForLogin("15153239892", "失败："+"{\"status\":300,\"msg\":\"请求不允许\"}");
        sendVerificationCodeForLogin("15153239892", "223344");

    }
}

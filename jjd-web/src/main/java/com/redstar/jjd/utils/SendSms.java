/**
 * Project Name:jjd-background
 * File Name:SendSms.java
 * Package Name:com.redstar.jjd.utils
 * Date:2015年12月17日下午4:02:19
 * Copyright (c) 2015, China Link Communications LTD All Rights Reserved.
 *
 */

package com.redstar.jjd.utils;

import java.io.IOException;

/**
 * ClassName: SendSms <br/>
 * Date: 2015年12月17日 下午4:02:19 <br/>
 * Description: 发送消息工具类
 * 
 * @author huangrui
 * @version
 * @see
 */
public class SendSms {

    /**
     * 
     * @description 发送单条短信 <br/>
     * 
     * @param account
     * @param password
     * @param content
     * @param phone
     * @throws IOException
     *             void
     * @throws
     */
    public static void sendMessage(String account, String password,
            String phone, String content) throws IOException {
        com.nineorange.service.BusinessService bs = new com.nineorange.service.BusinessService();
        bs.setWebService("http://114.80.208.222:8080/NOSmsPlatform/services/BusinessService");
        int nRet = bs.validateUser(account, password);
        if (nRet != 1) {
            System.out.println("登录失败！");
            return;
        }
        System.out.println("登录成功！");
        nRet = bs.sendMessage(account, password, phone, content);
        if (nRet <= 0) {
            System.out.println("发送消息失败，返回值：" + nRet);
            return;
        }
        System.out.println("发送消息成功，任务 ID：" + nRet + "，可以根据这个 ID 查询消 息处理情况。");
    }

    /**
     * 
     * @description 批量发送短信 <br/>
     * 
     * @param account
     * @param password
     * @param content
     * @param phonelist
     * @throws IOException
     *             void
     * @throws
     */
    public static void sendBatchMessage(String account, String password,
            String phonelist, String content) throws IOException {
        com.nineorange.service.BusinessService bs = new com.nineorange.service.BusinessService();
        bs.setWebService("http://114.80.208.222:8080/NOSmsPlatform/services/BusinessService");
        int nRet = bs.validateUser(account, password);
        if (nRet != 1) {
            System.out.println("登录失败！");
            return;
        }
        System.out.println("登录成功！");
        nRet = bs.sendBatchMessage(account, password, phonelist, content);
        if (nRet <= 0) {
            System.out.println("发送消息失败，返回值：" + nRet);
            return;
        }
        System.out.println("发送消息成功，任务 ID：" + nRet + "，可以根据这个 ID 查询消 息处理情况。");
    }

    /**
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        com.nineorange.service.BusinessService bs = new com.nineorange.service.BusinessService();
        bs.setWebService("http://114.80.208.222:8080/NOSmsPlatform/services/BusinessService");
        int nRet = bs.validateUser("testmkl", "123456");
        if (nRet != 1) {
            System.out.println("登录失败！");
            return;
        }
        System.out.println("登录成功！");
        // nRet = bs.sendMessage("testmkl", "123456", "15021511793",
        // "【红星美凯龙】尊敬的用户您好，您的注册码是123456");

        // 给财务发短信
        String content2 = new String("【红星】您好，" + "HX5877016031" + "," + "黄锐"
                + "," + "北京东四环商场 " + "," + "411251198709297892" + ","
                + "15021511793" + ",请查收。");
        SendSms.sendBatchMessage("testmkl", "123456",
                "15021511793;13564964425", content2);
        if (nRet <= 0) {
            System.out.println("发送消息失败，返回值：" + nRet);
            return;
        }
        System.out.println("发送消息成功，任务 ID：" + nRet + "，可以根据这个 ID 查询消 息处理情况。");
    }
}

/**
 * Project Name:jjd-background
 * File Name:Test.java
 * Package Name:com.redstar.jjd.utils
 * Date:2015年12月16日下午1:34:30
 * Copyright (c) 2015, China Link Communications LTD All Rights Reserved.
 *
 */

package com.redstar.jjd.utils;

/**
 * ClassName: Test <br/>
 * Date: 2015年12月16日 下午1:34:30 <br/>
 * Description: TODO
 * 
 * @author huangrui
 * @version
 * @see
 */
public class Test {
    /**
     * @param args
     */
    public static void main(String[] args) {
        com.nineorange.service.BusinessService bs = new com.nineorange.service.BusinessService();
        bs.setWebService("http://114.80.208.222:8080/NOSmsPlatform/services/BusinessService");
        int nRet = bs.validateUser("testmkl", "123456");
        if (nRet != 1) {
            System.out.println("登录失败！");
            return;
        }
        System.out.println("登录成功！");
        nRet = bs.sendMessage("testmkl", "123456", "15021511793",
                "【红星美凯龙】尊敬的用户您好，您的注册码是123456");
        if (nRet <= 0) {
            System.out.println("发送消息失败，返回值：" + nRet);
            return;
        }
        System.out.println("发送消息成功，任务 ID：" + nRet + "，可以根据这个 ID 查询消 息处理情况。");
    }
}

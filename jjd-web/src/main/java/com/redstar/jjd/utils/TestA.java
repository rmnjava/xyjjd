/**
 * Project Name:jjd-background
 * File Name:TestA.java
 * Package Name:com.redstar.jjd.utils
 * Date:2016年4月21日下午5:25:36
 * Copyright (c) 2016, China Link Communications LTD All Rights Reserved.
 *
 */

package com.redstar.jjd.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

/**
 * ClassName: TestA <br/>
 * Date: 2016年4月21日 下午5:25:36 <br/>
 * Description: TODO
 * 
 * @author huangrui
 * @version
 * @see
 */
public class TestA {

    public static void send(String content, String phonelist, String taskId)
            throws IOException {
        StringBuffer sb = new StringBuffer("");

        sb.append("content=" + URLEncoder.encode(content, "UTF-8"));

        sb.append("&phonelist=" + phonelist);

        sb.append("&taskId=" + taskId);

        URL url = new URL(sb.toString());

        HttpURLConnection connection = (HttpURLConnection) url.openConnection();

        connection.setRequestMethod("POST");

        BufferedReader in = new BufferedReader(new InputStreamReader(
                url.openStream()));

        String inputline = in.readLine();

        System.out.println(inputline);
    }

    public static void main(String[] args) {
        // TODO Auto-generated method stub

    }

}

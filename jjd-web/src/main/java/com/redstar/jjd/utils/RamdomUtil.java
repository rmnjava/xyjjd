/**
 * Project Name:why-mobile
 * File Name:Ramdom.java
 * Package Name:com.wonders.why.sms.util
 * Date:2015-5-14下午3:28:39
 * Copyright (c) 2015, China Link Communications LTD All Rights Reserved.
 *
 */

package com.redstar.jjd.utils;

/**
 * 
 * ClassName: RamdomUtil <br/>
 * Date: 2015年12月17日 下午4:22:30 <br/>
 * Description: 生成随机数工具类 <br/>
 * 
 * @author huangr
 * @version
 * @see
 */
public final class RamdomUtil {
    public static String createRandom(boolean numberFlag, int length) {
        String retStr = "";
        String strTable = numberFlag ? "1234567890"
                : "1234567890abcdefghijkmnpqrstuvwxyz";
        int len = strTable.length();
        boolean bDone = true;
        do {
            retStr = "";
            int count = 0;
            for (int i = 0; i < length; i++) {
                double dblR = Math.random() * len;
                int intR = (int) Math.floor(dblR);
                char c = strTable.charAt(intR);
                if (('0' <= c) && (c <= '9')) {
                    count++;
                }
                retStr += strTable.charAt(intR);
            }
            if (count >= 2) {
                bDone = false;
            }
        } while (bDone);

        return retStr;
    }

}

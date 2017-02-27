package com.redstar.jjd.utils;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

/**
 * Created by Pengfei on 2015/9/25.
 */

public class JSONUtil {
    public static String stringify(Object object, String[] excludeFields) {
        JsonConfig jsonConfig = new JsonConfig();
//        jsonConfig.registerJsonValueProcessor(Date.class, new JSONDateValueProcessor(GeneralConstant.DATE_FORMAT));
        jsonConfig.setIgnoreDefaultExcludes(false);
        jsonConfig.setExcludes(excludeFields);
        return new JSONArray().fromObject(object, jsonConfig).toString();
    }


    public static String stringify(Object object) {
        return stringify(object, new String[]{});
    }

    public static String stringifySingle(Object object) {
        return new JSONObject().fromObject(object).toString();
    }
}

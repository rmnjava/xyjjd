package com.redstar.jjd.common;

import java.util.HashMap;
import java.util.Map;

import com.redstar.jjd.constant.GeneralConstant;

public class ControllerResult {

    private ControllerResult() {
        super();
    }

    private static Map<String, Object> result;

    public static Map<String, Object> getSuccessResult() {
        result = new HashMap<String, Object>();
        result.put(GeneralConstant.RETURN_STATUS,
                GeneralConstant.RETURN_SUCCESS);
        return result;
    }

    public static Map<String, Object> getFailResult() {
        result = new HashMap<String, Object>();
        result.put(GeneralConstant.RETURN_STATUS, GeneralConstant.RETURN_FAILED);
        return result;
    }

}

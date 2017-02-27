package com.redstar.jjd.constant;

import java.util.HashMap;
import java.util.Map;

public class GeneralConstant {
    static public final String RETURN_STATUS = "status";
    static public final String RETURN_SUCCESS = "success";
    static public final String RETURN_FAILED = "failed";
    // 参数错误
    public static final String PARAMTER_ERROR = "100";
    // 交易成功
    public static final String STATUS_SUCCESS = "0";
    // 失败
    public static final String STATUS_FAIL = "1";
    // 余额不足
    public static final String STATUS_PAY_ENOUGH_NOT = "2";
    // 密码错误
    public static final String STATUS_PASSWORD_ERROR = "3";

    // 申请用户id
    public static final String TXNCODE_USERID = "201510270007";

    // 支付码申请
    public static final String TXNCODE_PAYCODE = "201510270005";

    // 交易状态查询
    public static final String TXNCODE_PAY_RESULT = "201510270006";

    // 交易记录查询
    public static final String TXNCODE_CARD_HISTORY = "201510270011";

    // 账户信息查询
    public static final String TXNCODE_CARD_INFO = "201511090001";

    // 注册通汇卡
    public static final String TXNCODE_REGISTER_CARD = "201511040001";

    // 有效的
    public static final String STATE_VALID = "1";
    // 无效的
    public static final String STATE_INVALID = "0";

    // 图片类型（banner）
    public static final String NEWS_TYPE_BANNER = "banner";

    public static final String ATTACHMENT_PATH = "/../../jjd-background/images/";
    // banner图片地址
    public static final String IMG_PATH_BANNER = ATTACHMENT_PATH + "banner/";

    // 是否有银行快捷通道 1 有，0无
    public static final String HASBANKPAY_YES = "1";
    public static final String HASBANKPAY_NO = "0";

    // 银行支付状态（2待支付 1支付成功 0支付失败）
    public static final String BANK_PAY_RESULT_NO = "2";
    public static final String BANK_PAY_RESULT_SUCCESS = "1";
    public static final String BANK_PAY_RESULT_FAILED = "0";

    /**
     * 权限中文转换
     */
    public static final Map<String, String> operationMap = new HashMap<String, String>();
    static {
        operationMap.put("add", "新增");
        operationMap.put("update", "修改");
        operationMap.put("view", "查看");
        operationMap.put("delete", "删除");
        operationMap.put("import", "上传");
        operationMap.put("down", "下载");
        operationMap.put("export", "导出");
        operationMap.put("loan", "意向贷款标记");
        operationMap.put("into", "进件标记");
        operationMap.put("examine", "放款标记");
        operationMap.put("storeadd", "商场录入");
        operationMap.put("storeupdate", "商场补录");

    }

    /**
     * 下拉框定义请选择的Map
     */
    public static final Map<String, String> selectMap = new HashMap<String, String>();

    static {
        selectMap.put("", "-请选择-");
    }

    // 默认下拉框选项
    public static final String DEFAULT_COMBOVO = "----请选择----";

    // 角色名称
    public static final String STOREROLENAME_1 = "商场业务";
    public static final String STOREROLENAME_2 = "商场财务";
    public static final String ROLENAME_1 = "超级管理员";
    public static final String ROLENAME_2 = "星易通汇财务";

    // 申请数据来源（1 app(phone,password)，2 wechat(openId)，3 h5 4 pc 5 商场补录）
    public static final String DATA_SOURCE_WEIXIN = "2";
    public static final String DATA_SOURCE_APP = "1";
    public static final String DATA_SOURCE_H5 = "3";
    public static final String DATA_SOURCE_PC = "4";
    public static final String DATA_SOURCE_STORE = "5";

}

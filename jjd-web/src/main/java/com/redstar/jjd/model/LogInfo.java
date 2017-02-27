package com.redstar.jjd.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * 
 * ClassName: LogInfo <br/>
 * Date: 2016年7月6日 下午3:48:30 <br/>
 * Description: log信息 <br/>
 * 
 * @author huangrui
 * @version
 * @see
 */
@Entity
@Table(name = "T_LOG_INFO")
public class LogInfo implements Serializable {
    /**
     * serialVersionUID:TODO
     */
    private static final long serialVersionUID = -7700528626118900493L;

    @Id
    @SequenceGenerator(name = "logInfoSequence", sequenceName = "SEQ_LOGINFO")
    @GeneratedValue(generator = "logInfoSequence", strategy = GenerationType.SEQUENCE)
    @Column(name = "ID")
    private Long id;

    /**
     * log类型
     */
    @Column(name = "LOG_TYPE")
    private int logType;
    /**
     * 操作人Id
     */
    @Column(name = "OPERATOR_ID")
    private Long operatorId;

    /**
     * 登录用户名
     */
    @Column(name = "LOGIN_NAME")
    private String loginName;

    /**
     * log内容
     */
    @Column(name = "LOG_CONTENT")
    private String logContent;

    /**
     * log创建时间
     */
    @Column(name = "CREATE_TIME")
    private String createTime;

    /**
     * 400回访
     */
    public static final int TYPE_0 = 0;

    /**
     * 商场信息管理
     */
    public static final int TYPE_1 = 1;

    /**
     * 账号信息管理
     */
    public static final int TYPE_2 = 2;

    /**
     * 账号权限设置
     */
    public static final int TYPE_3 = 3;

    /**
     * 角色管理
     */
    public static final int TYPE_4 = 4;

    /**
     * 申请信息浏览
     */
    public static final int TYPE_5 = 5;

    /**
     * 申请信息导出
     */
    public static final int TYPE_6 = 6;

    /**
     * 审批进度更新
     */
    public static final int TYPE_7 = 7;

    /**
     * 审核通过导出
     */
    public static final int TYPE_8 = 8;

    /**
     * 财务确认到账
     */
    public static final int TYPE_9 = 9;

    /**
     * 商场财务确认
     */
    public static final int TYPE_10 = 10;

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getLogType() {
        return logType;
    }

    public void setLogType(int logType) {
        this.logType = logType;
    }

    public Long getOperatorId() {
        return operatorId;
    }

    public void setOperatorId(Long operatorId) {
        this.operatorId = operatorId;
    }

    public String getLogContent() {
        return logContent;
    }

    public void setLogContent(String logContent) {
        this.logContent = logContent;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

}

package com.redstar.jjd.vo;

import java.text.DecimalFormat;
import java.util.Date;

import com.redstar.jjd.model.UserInfo;
import com.redstar.jjd.utils.UtilDatetime;

/**
 * Created by Pengfei on 2015/12/24.
 */
public class UserApplyView {
    private String userId;

    private String phone;

    private UserInfo userInfo;

    private Date applyDate;

    // 申请日期
    private String applyDateFmt;

    private String applyTime;

    // 银行交易日期 YYYY/MM/DD HH:MM:SS
    private Date hostDate;

    private String orderTime;

    // 贷款成功日期
    private String hostDateFmt;

    private Integer applyStatus;

    private Double amount;

    private String amountFmt;

    private Integer period;

    private Double interest;

    private Double returnPerMonth;

    private Integer bankId;

    private Integer returnType;

    private Integer returnMonth;

    private Long storeId;

    private Boolean isZero;

    private String storeName;

    private String bankName;

    private String address;

    private String provinceName;

    private String cityName;

    private String orderNo;

    private String userName;

    private String openId;

    /**
     * 此字段用来区分是来源于农行还是红星（农行0 红星1）
     */
    private String sourceFrom;

    /**
     * 是否有银行支付快捷通道（1：有，0：无）
     */
    private Boolean hasBankPay;

    // 银行支付结果(2 待支付 1 支付成功，0 支付失败)
    private String bankPayResult;

    private Long applyId;
    // 银行支付请求url
    private String payUrl;

    /**
     * 每月还款取整
     */
    private String returnPerMonthFmt;
    /**
     * 身份证号
     */
    private String idNumber;

    /**
     * 数据来源（1 app目前是“”，2 wechat,3 H5,4 PC）
     */
    private String dataSource;

    /**
     * 是否有意向贷款（1是 |0否）
     */
    private Boolean isLoan;

    /**
     * 是否已进件（1是 |0否）
     */
    private Boolean isInto;

    /**
     * 是否放款已成功（1是 |0否）
     */
    private Boolean isLoanSuccess;

    /**
     * 录入时间
     */
    private Date operatorTime;
    private String operatorTimeStr;

    /**
     * 录入人
     */
    private Long operatorId;

    /**
     * 录入人姓名
     */
    private String operatorName;

    /**
     * 录入人部门
     */
    private String deptName;

    /**
     * 备注
     */
    private String loanNote;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Date getApplyDate() {
        return applyDate;
    }

    public void setApplyDate(Date applyDate) {
        this.applyDate = applyDate;
    }

    public Integer getApplyStatus() {
        return applyStatus;
    }

    public void setApplyStatus(Integer applyStatus) {
        this.applyStatus = applyStatus;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Integer getPeriod() {
        return period;
    }

    public void setPeriod(Integer period) {
        this.period = period;
    }

    public Double getInterest() {
        return interest;
    }

    public void setInterest(Double interest) {
        this.interest = interest;
    }

    public Double getReturnPerMonth() {
        return returnPerMonth;
    }

    public void setReturnPerMonth(Double returnPerMonth) {
        this.returnPerMonth = returnPerMonth;
    }

    public Integer getBankId() {
        return bankId;
    }

    public void setBankId(Integer bankId) {
        this.bankId = bankId;
    }

    public Integer getReturnType() {
        return returnType;
    }

    public void setReturnType(Integer returnType) {
        this.returnType = returnType;
    }

    public Integer getReturnMonth() {
        return returnMonth;
    }

    public void setReturnMonth(Integer returnMonth) {
        this.returnMonth = returnMonth;
    }

    public Long getStoreId() {
        return storeId;
    }

    public void setStoreId(Long storeId) {
        this.storeId = storeId;
    }

    public Boolean getIsZero() {
        return isZero;
    }

    public void setIsZero(Boolean isZero) {
        this.isZero = isZero;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Boolean getHasBankPay() {
        return hasBankPay;
    }

    public void setHasBankPay(Boolean hasBankPay) {
        this.hasBankPay = hasBankPay;
    }

    public String getBankPayResult() {
        return bankPayResult;
    }

    public void setBankPayResult(String bankPayResult) {
        this.bankPayResult = bankPayResult;
    }

    public Long getApplyId() {
        return applyId;
    }

    public void setApplyId(Long applyId) {
        this.applyId = applyId;
    }

    public String getPayUrl() {
        return payUrl;
    }

    public void setPayUrl(String payUrl) {
        this.payUrl = payUrl;
    }

    public String getAmountFmt() {
        if (amount != null && amount > 0) {
            DecimalFormat df = new DecimalFormat("###,###");
            amountFmt = df.format(amount);
            return amountFmt;
        }
        return "0";
    }

    public void setAmountFmt(String amountFmt) {
        this.amountFmt = amountFmt;
    }

    public Date getHostDate() {
        return hostDate;
    }

    public void setHostDate(Date hostDate) {
        this.hostDate = hostDate;
    }

    public String getHostDateFmt() {
        if (hostDate != null) {
            return UtilDatetime.DATE_TIME_FULL_FORMAT.format(hostDate);
        }
        return "";
    }

    public void setHostDateFmt(String hostDateFmt) {
        this.hostDateFmt = hostDateFmt;
    }

    public String getApplyDateFmt() {
        if (applyDate != null) {
            return UtilDatetime.DATE_TIME_FULL_FORMAT.format(applyDate);
        }
        return "";
    }

    public void setApplyDateFmt(String applyDateFmt) {
        this.applyDateFmt = applyDateFmt;
    }

    public String getReturnPerMonthFmt() {
        if (returnPerMonth != null && returnPerMonth > 0) {
            DecimalFormat df = new DecimalFormat("#");
            returnPerMonthFmt = df.format(returnPerMonth);
            return returnPerMonthFmt;
        }
        return "0";
    }

    public void setReturnPerMonthFmt(String returnPerMonthFmt) {
        this.returnPerMonthFmt = returnPerMonthFmt;
    }

    public UserInfo getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserInfo userInfo) {
        this.userInfo = userInfo;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getProvinceName() {
        return provinceName;
    }

    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public String getApplyTime() {
        return applyTime;
    }

    public void setApplyTime(String applyTime) {
        this.applyTime = applyTime;
    }

    public String getSourceFrom() {
        return sourceFrom;
    }

    public void setSourceFrom(String sourceFrom) {
        this.sourceFrom = sourceFrom;
    }

    public String getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(String orderTime) {
        this.orderTime = orderTime;
    }

    public String getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }

    public String getDataSource() {
        return dataSource;
    }

    public void setDataSource(String dataSource) {
        this.dataSource = dataSource;
    }

    public Boolean getIsLoan() {
        return isLoan;
    }

    public void setIsLoan(Boolean isLoan) {
        this.isLoan = isLoan;
    }

    public Boolean getIsInto() {
        return isInto;
    }

    public void setIsInto(Boolean isInto) {
        this.isInto = isInto;
    }

    public Boolean getIsLoanSuccess() {
        return isLoanSuccess;
    }

    public void setIsLoanSuccess(Boolean isLoanSuccess) {
        this.isLoanSuccess = isLoanSuccess;
    }

    public Date getOperatorTime() {
        return operatorTime;
    }

    public void setOperatorTime(Date operatorTime) {
        this.operatorTime = operatorTime;
    }

    public Long getOperatorId() {
        return operatorId;
    }

    public void setOperatorId(Long operatorId) {
        this.operatorId = operatorId;
    }

    public String getOperatorName() {
        return operatorName;
    }

    public void setOperatorName(String operatorName) {
        this.operatorName = operatorName;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public String getLoanNote() {
        return loanNote;
    }

    public void setLoanNote(String loanNote) {
        this.loanNote = loanNote;
    }

    public String getOperatorTimeStr() {
        return operatorTimeStr;
    }

    public void setOperatorTimeStr(String operatorTimeStr) {
        this.operatorTimeStr = operatorTimeStr;
    }

}

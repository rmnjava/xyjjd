/**
 * 
 */
package com.redstar.jjd.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * 
 * ClassName: OrderPayResult <br/>
 * Date: 2016年4月18日 上午10:51:53 <br/>
 * Description: 银行订单支付结果表 <br/>
 * 
 * @author huangrui
 * @version
 * @see
 */
@Entity
@Table(name = "T_ORDER_BANK_PAY")
public class OrderBankPay implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(name = "orderBankPaySequence", sequenceName = "SEQ_ORDER_BANK_PAY")
    @GeneratedValue(generator = "orderBankPaySequence", strategy = GenerationType.SEQUENCE)
    @Column(name = "ID")
    private Long id;

    @Column(name = "USER_APPLY_ID")
    private Long applyId;

    @Column(name = "USER_ID")
    private String userId;

    /**
     * 交易类型 （ImmediatePay： 直接支付，PreAuthPay： 预授权支付，DividedPay： 分期支付）
     */
    @Column(name = "PAY_TYPE_ID")
    private Long payTypeID;

    /**
     * 订单日期 YYYY/MM/DD HH:MM:SS
     */
    @Column(name = "ORDER_DATE")
    private Date orderDate;

    /**
     * 交易币种
     */
    @Column(name = "CURRENCY_CODE")
    private String currencyCode;

    /**
     * 订单编号
     */
    @Column(name = "ORDER_NO")
    private String orderNo;

    /**
     * 交易金额
     */
    @Column(name = "ORDER_AMOUNT")
    private String orderAmount;

    /**
     * 分期标识（ 1：分期； 0： 不分期）
     */
    @Column(name = "INSTALLMENT_MARK")
    private String installmentMark;

    /**
     * 商品种类
     * 
     * 充值类 0101:支付账户充值 消费类 0201:虚拟类,0202:传统类,0203:实名类 转账类 0301:本行转账,0302:他行转账
     * 缴费类 0401:水费,0402:电费,0403:煤气费,0404:有线电视费,0405:通讯费,
     * 0406:物业费,0407:保险费,0408:行政费用,0409:税费,0410:学费,0499:其他 理财类
     * 0501:基金,0502:理财产品,0599:其他
     */
    @Column(name = "COMMODITY_TYPE")
    private String commodityType;

    /**
     * 银行支付结果（成功 1，失败 0, 待支付2）
     */
    @Column(name = "BANK_PAYRESULT")
    private String bankPayResult;

    /**
     * 交易凭证号（建议使用 iRspRef 作为对账依据）
     */
    @Column(name = "VOUCHER_NO")
    private String voucherNo;

    /**
     * 交易批次号
     */
    @Column(name = "BATCH_NO")
    private String batchNo;

    /**
     * 银行交易日期 YYYY/MM/DD HH:MM:SS
     */
    @Column(name = "HOST_DATE")
    private Date hostDate;

    /**
     * 银行返回交易流水号
     */
    @Column(name = "IRSP_REF")
    private String iRspRef;

    /**
     * 消费者支付方式
     */
    @Column(name = "PAY_TYPE")
    private String payType;

    /**
     * 支付结果通知方式
     */
    @Column(name = "NOTIFY_TYPE")
    private String notifyType;

    public String getBankPayResult() {
        return bankPayResult;
    }

    public void setBankPayResult(String bankPayResult) {
        this.bankPayResult = bankPayResult;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getVoucherNo() {
        return voucherNo;
    }

    public void setVoucherNo(String voucherNo) {
        this.voucherNo = voucherNo;
    }

    public String getBatchNo() {
        return batchNo;
    }

    public void setBatchNo(String batchNo) {
        this.batchNo = batchNo;
    }

    public Date getHostDate() {
        return hostDate;
    }

    public void setHostDate(Date hostDate) {
        this.hostDate = hostDate;
    }

    public String getiRspRef() {
        return iRspRef;
    }

    public void setiRspRef(String iRspRef) {
        this.iRspRef = iRspRef;
    }

    public String getPayType() {
        return payType;
    }

    public void setPayType(String payType) {
        this.payType = payType;
    }

    public String getNotifyType() {
        return notifyType;
    }

    public void setNotifyType(String notifyType) {
        this.notifyType = notifyType;
    }

    public Long getApplyId() {
        return applyId;
    }

    public void setApplyId(Long applyId) {
        this.applyId = applyId;
    }

    public Long getPayTypeID() {
        return payTypeID;
    }

    public void setPayTypeID(Long payTypeID) {
        this.payTypeID = payTypeID;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getOrderAmount() {
        return orderAmount;
    }

    public void setOrderAmount(String orderAmount) {
        this.orderAmount = orderAmount;
    }

    public String getInstallmentMark() {
        return installmentMark;
    }

    public void setInstallmentMark(String installmentMark) {
        this.installmentMark = installmentMark;
    }

    public String getCommodityType() {
        return commodityType;
    }

    public void setCommodityType(String commodityType) {
        this.commodityType = commodityType;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

}

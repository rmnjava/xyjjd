/**
 * Project Name:test
 * File Name:ClientFromWsdl.java
 * Package Name:test
 * Date:2015年12月15日上午9:45:26
 * Copyright (c) 2015, China Link Communications LTD All Rights Reserved.
 *
 */

package com.redstar.jjd.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.redstar.cardPin.dto.CardManagerDTO;
import com.redstar.cardsManage.dto.CardsManagerDTO;
import com.redstar.framework.action.BaseAction;
import com.redstar.framework.dto.OperationResult;
import com.redstar.jjd.vo.AccountBalanceView;
import com.redstar.paymentcode.dto.PaymentCodeDTO;
import com.redstar.service.user.dto.UserDTO;
import com.redstar.txn.dto.QueryDataDTO;
import com.redstar.txn.dto.TxnQuery;

/**
 * ClassName: ClientFromWsdl <br/>
 * Date: 2015年12月15日 上午9:45:26 <br/>
 * Description: 通汇卡交易工具类
 * 
 * @author huangrui
 * @version
 * @see
 */
public class WebservicePayClient {

    /**
     * 
     * @description 申请用户userId <br/>
     * 
     * @param txncode
     *            交易码（201510270007）
     * @param originate
     *            数据来源（00-微信 01-app 02-pc）
     * @param operaterId
     *            操作员Id
     * @return String userId
     * @throws
     */
    public static String getUserId(String txncode, String originate, String operaterId) {
        UserDTO userDTO = new UserDTO();
        userDTO.setOpreaterId(operaterId);
        userDTO.setOriginate(originate);
        BaseAction action = new BaseAction();
        OperationResult sendService = action.sendService(txncode, operaterId, userDTO);
        String userCardId = null;
        if (sendService == null) {
            System.out.println("连接后台异常");
        } else {
            if (sendService.getTxnstate().equals("1")) {
                UserDTO dto = (UserDTO) sendService.getDetailvo();
                userCardId = dto.getUserId();
            } else if (sendService.getTxnstate().equals("0")) {
                String errmessage = sendService.getErrMessage();
                System.out.println(errmessage);
            } else {
                System.out.println("返回状态非法");
            }
        }
        return userCardId;
    }

    /**
     * 
     * @description 支付码申请 <br/>
     * 
     * @param userId
     *            核心唯一标识
     * @param txncode
     *            交易码 （201510270005）
     * 
     * @param originate
     *            数据来源（00-微信 01-app 02-pc）
     * @param operaterId
     *            操作员Id
     * @return String payCode 交易码密文
     * @throws
     */
    public static String getPayCode(PaymentCodeDTO paymentCodeDTO, String txncode, String operaterId) {
        BaseAction action = new BaseAction();
        OperationResult sendService = action.sendService(txncode, operaterId, paymentCodeDTO);
        String payCode = null;
        if (sendService == null) {
            System.out.println("连接后台异常");
        } else {
            if (sendService.getTxnstate().equals("1")) {
                PaymentCodeDTO dto = (PaymentCodeDTO) sendService.getDetailvo();
                payCode = dto.getCode_cryptograph();
            } else if (sendService.getTxnstate().equals("0")) {
                String errmessage = sendService.getErrMessage();
                System.out.println(errmessage);
            } else {
                System.out.println("返回状态非法");
            }
        }
        return payCode;
    }

    /**
     * 
     * @description 交易状态查询(交易码：201510270006) <br/>
     * 
     * @param paymentCodeDTO
     * @param txncode
     * @param operaterId
     * @return Map<String,Object>
     * @throws
     */
    public static Map<String, Object> getPayResult(PaymentCodeDTO paymentCodeDTO, String txncode, String operaterId) {
        Map<String, Object> map = new HashMap<String, Object>();
        // 返回结果code和对应的描述
        String code = null;
        String value = null;
        BaseAction action = new BaseAction();
        OperationResult sendService = action.sendService(txncode, operaterId, paymentCodeDTO);
        if (sendService == null) {
            System.out.println("连接后台异常");
        } else {
            if (sendService.getTxnstate().equals("1")) {
                PaymentCodeDTO dto = (PaymentCodeDTO) sendService.getDetailvo();
                // pos机打印流水
                String posSeq = dto.getPosSeq();
                // 交易金额
                double txnAmt = Double.parseDouble(dto.getTxnAmt());
                // 支付时间
                SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                Date time = null;
                String timeStr = null;
                try {
                    time = format.parse(dto.getDate_time());
                    timeStr = sdf.format(time);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                map.put("posSeq", posSeq);
                map.put("txnAmt", txnAmt / 100.0);
                map.put("time", timeStr);
                String state = dto.getData_state();
                switch (state) {
                case "2":
                    code = "0";
                    value = "成功";
                    break;
                case "3":
                    // 失败响应码
                    String rspCode = dto.getRspCode();
                    if (rspCode.equals("51")) {
                        code = "2";
                        value = "余额不足";
                    } else if (state.equals("55")) {
                        code = "3";
                        value = "密码错误";
                    } else {
                        code = "1";
                        value = "交易失败";
                    }
                    break;
                case "4":
                    code = "4";
                    value = "已冲正";
                    break;
                case "5":
                    code = "5";
                    value = "已撤销";
                    break;
                }

            } else if (sendService.getTxnstate().equals("0")) {
                String errmessage = sendService.getErrMessage();
                if (errmessage.equals("E007:交易不存在")) {
                    code = "6";
                    value = "交易不存在";
                }
                System.out.println(errmessage);
            } else {
                System.out.println("返回状态非法");
            }
        }
        map.put("status", code);
        map.put("message", value);
        return map;
    }

    /**
     * 
     * @description 交易记录查询(201510270011) <br/>
     * 
     * @param userId
     * @param txncode
     * @param originate
     * @param operaterId
     * @return OperationResult
     * @throws
     */
    public static OperationResult getCardHistory(QueryDataDTO queryDto, String txncode, String originate, String operaterId) {
        BaseAction action = new BaseAction();
        OperationResult sendService = action.sendService(txncode, operaterId, queryDto);
        if (sendService == null) {
            System.out.println("连接后台异常");
        } else {
            if (sendService.getTxnstate().equals("1")) {
                QueryDataDTO dto = (QueryDataDTO) sendService.getDetailvo();
                List<TxnQuery> list = dto.getTxnQueryList();
            } else if (sendService.getTxnstate().equals("0")) {
                String errmessage = sendService.getErrMessage();
                System.out.println(errmessage);
            } else {
                System.out.println("返回状态非法");
            }
        }
        return sendService;
    }

    /**
     * 
     * @description 账户信息查询 (交易码：201511090001) <br/>
     * 
     * @param userDto
     * @param txncode
     * @param operaterId
     * @return OperationResult
     * @throws
     */
    public static AccountBalanceView getCardInfo(UserDTO userDto, String txncode, String operaterId) {
        AccountBalanceView view = new AccountBalanceView();
        Map<String, Double> sumByStore = new HashMap<String, Double>();
        BaseAction action = new BaseAction();
        OperationResult sendService = action.sendService(txncode, operaterId, userDto);
        // 账户余额
        double accountBalance = 0.0;
        if (sendService == null) {
            System.out.println("连接后台异常");
        } else {
            if (sendService.getTxnstate().equals("1")) {
                CardsManagerDTO dto = (CardsManagerDTO) sendService.getDetailvo();
                List<CardManagerDTO> cardList = dto.getCardManagerDTOs();

                for (CardManagerDTO cardDto : cardList) {
                    // 卡内余额
                    String cardBalance = cardDto.getBanlance();
                    accountBalance += Double.parseDouble(cardBalance);
                }
                // 计算所属商场的所有卡余额
                for (CardManagerDTO cardDto : cardList) {
                    // 卡所属商场
                    String acceptorName = cardDto.getAcceptorName();
                    double balance = Double.parseDouble(cardDto.getBanlance());
                    Double money = sumByStore.get(acceptorName);
                    if (null == money || money < 0.0) {
                        sumByStore.put(acceptorName, balance / 100.0);
                    } else {
                        money += balance;
                        sumByStore.put(acceptorName, money / 100.0);
                    }

                }

            } else if (sendService.getTxnstate().equals("0")) {
                String errmessage = sendService.getErrMessage();
                System.out.println(errmessage);
            } else {
                System.out.println("返回状态非法");
            }
        }
        view.setAccountBalance(accountBalance / 100.0);
        view.setSumByStore(sumByStore);
        return view;
    }

    /**
     * 
     * @description 注册（交易码：201511040001） <br/>
     * 
     * @param userDto
     * @param txncode
     * @param operaterId
     * @return OperationResult
     * @throws
     */
    public static String registerCard(UserDTO userDto, String txncode, String operaterId) {
        BaseAction action = new BaseAction();
        OperationResult sendService = action.sendService(txncode, operaterId, userDto);
        String userId = null;
        if (sendService == null) {
            System.out.println("连接后台异常");
        } else {
            if (sendService.getTxnstate().equals("1")) {
                UserDTO dto = (UserDTO) sendService.getDetailvo();
                userId = dto.getUserId();
            } else if (sendService.getTxnstate().equals("0")) {
                String errmessage = sendService.getErrMessage();
                System.out.println(errmessage);
            } else {
                System.out.println("返回状态非法");
            }
        }
        return userId;
    }

    public static void main(String[] args) throws Exception {
        // BaseAction action = new BaseAction();
        // String parameter = "201510270007";
        // System.out.println("申请用户UserId");
        // UserDTO userDTO = new UserDTO();
        // userDTO.setOpreaterId("111111111");
        // userDTO.setOriginate("01");
        // OperationResult sendService = action.sendService(parameter,
        // "201510270007", userDTO);
        // System.out.println(sendService);

    }
}

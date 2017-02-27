/**
 * Project Name:jjd-web
 * File Name:UserInfo.java
 * Package Name:com.redstar.jjd.service
 * Date:2016年6月8日下午2:31:33
 * Copyright (c) 2016, China Link Communications LTD All Rights Reserved.
 *
 */

package com.redstar.jjd.service;

import java.io.OutputStream;
import java.util.List;

import jxl.write.WritableWorkbook;

import com.redstar.jjd.model.User;
import com.redstar.jjd.query.UserQuery;
import com.redstar.jjd.query.UserWechatQuery;
import com.redstar.jjd.query.UserWechatSpecialQuery;
import com.redstar.jjd.vo.UserApplyView;
import com.redstar.jjd.vo.UserInfoView;
import com.redstar.jjd.vo.UserView;

/**
 * ClassName: UserInfo <br/>
 * Date: 2016年6月8日 下午2:31:33 <br/>
 * Description: TODO
 * 
 * @author huangrui
 * @version
 * @see
 */
public interface UserInfoService {

    public User login(UserView user) throws Exception;

    public List<UserInfoView> query(UserQuery query);

    /**
     * 
     * @description 查询app成功分期导出清单（帅选之后的内容） <br/>
     * 
     * @param query
     * @return List<UserInfoView>
     * @throws
     */
    public List<UserInfoView> queryAppExportList(UserQuery query);

    /**
     * 
     * @description app成功分期客户导出 <br/>
     * 
     * @param out
     * @param list
     * @return WritableWorkbook
     * @throws
     */
    public WritableWorkbook createExcelForApp(OutputStream out,
            List<UserInfoView> list);

    /**
     * 
     * @description 微信在线分期客户导出 <br/>
     * 
     * @param out
     * @param list
     * @return WritableWorkbook
     * @throws
     */
    public WritableWorkbook createExcelForWeixin(OutputStream out,
            List<UserApplyView> list);

    public List<UserApplyView> queryWechat(UserWechatQuery query);

    /**
     * 
     * @description 查询微信在线分期导出清单（帅选之后的内容） <br/>
     * 
     * @param query
     * @return List<UserInfoView>
     * @throws
     */
    public List<UserApplyView> queryWechatExport(UserWechatQuery query);

    /**
     * 
     * @description 查询微信专项贷款信息 <br/>
     * 
     * @param query
     * @return List<UserApplyView>
     * @throws
     */
    public List<UserApplyView> queryWechatSpecial(UserWechatSpecialQuery query);

    /**
     * 
     * @description 查询微信专项贷款导出清单（帅选之后的内容） <br/>
     * 
     * @param query
     * @return List<UserInfoView>
     * @throws
     */
    public List<UserApplyView> queryWechatSpecialExport(
            UserWechatSpecialQuery query);

    public WritableWorkbook createExcelForWechatSpecial(OutputStream out,
            List<UserApplyView> list);

}

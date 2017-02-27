/**
 * Project Name:jjd-weixin
 * File Name:UserPayDao.java
 * Package Name:com.redstar.jjd.dao
 * Date:2016年8月3日下午2:45:01
 * Copyright (c) 2016, China Link Communications LTD All Rights Reserved.
 *
 */

package com.redstar.jjd.dao;

import java.util.List;

import com.redstar.jjd.model.UserPay;
import com.redstar.jjd.query.UserPayQuery;

/**
 * ClassName: UserPayDao <br/>
 * Date: 2016年8月3日 下午2:45:01 <br/>
 * Description: TODO
 * 
 * @author huangrui
 * @version
 * @see
 */
public interface UserPayDao extends BaseDao<UserPay, Long> {
    /**
     * 
     * @description 查询导出列表（不分页） <br/>
     * 
     * @param userQuery
     * @return List<UserPay>
     * @throws
     */
    public List<UserPay> queryExport(UserPayQuery userQuery);

}

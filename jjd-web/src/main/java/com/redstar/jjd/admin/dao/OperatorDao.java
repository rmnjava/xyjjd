/**
 * Project Name:jjd-web
 * File Name:OperatorDao.java
 * Package Name:com.redstar.jjd.dao
 * Date:2016年6月28日下午2:21:47
 * Copyright (c) 2016, China Link Communications LTD All Rights Reserved.
 *
 */

package com.redstar.jjd.admin.dao;

import java.util.List;

import com.redstar.jjd.dao.BaseDao;
import com.redstar.jjd.model.Operator;

/**
 * ClassName: OperatorDao <br/>
 * Date: 2016年6月28日 下午2:21:47 <br/>
 * Description: TODO
 * 
 * @author huangrui
 * @version
 * @see
 */
public interface OperatorDao extends BaseDao<Operator, Long> {
    /**
     * 根据角色ID查询关联的用户集合
     * 
     * @param roleId
     * @return
     * */
    public List<Object[]> findUsersByRoleId(Long roleId);

    /**
     * 
     * @description 登录
     * 
     * @param loginName
     * @return Operator
     * @throws
     */
    public Operator getOperatorByLoginName(String loginName);

    /**
     * 删除用户t_sys_u2r关系记录
     * */
    public void deleteU2RByOperatorId(Long operatorId);

}

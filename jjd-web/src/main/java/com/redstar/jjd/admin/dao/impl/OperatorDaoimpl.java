/**
 * Project Name:jjd-web
 * File Name:OperatorDaoimpl.java
 * Package Name:com.redstar.jjd.dao.impl
 * Date:2016年6月28日下午2:22:27
 * Copyright (c) 2016, China Link Communications LTD All Rights Reserved.
 *
 */

package com.redstar.jjd.admin.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.redstar.jjd.admin.dao.OperatorDao;
import com.redstar.jjd.dao.impl.BaseDaoImpl;
import com.redstar.jjd.model.Operator;

/**
 * ClassName: OperatorDaoimpl <br/>
 * Date: 2016年6月28日 下午2:22:27 <br/>
 * Description: TODO
 * 
 * @author huangrui
 * @version
 * @see
 */
@Repository("operatorDao")
public class OperatorDaoimpl extends BaseDaoImpl<Operator, Long> implements
        OperatorDao {

    @SuppressWarnings("unchecked")
    @Override
    public List<Object[]> findUsersByRoleId(Long roleId) {
        String sql = "select * from T_SYS_U2R where ROLE_ID = ? ";
        Query query = sessionFactory.getCurrentSession().createSQLQuery(sql);
        query.setLong(0, roleId);
        return query.list();
    }

    @Override
    public Operator getOperatorByLoginName(String loginName) {
        Operator operator = findUnique("from Operator o where o.loginName=? ",
                loginName);
        return operator;
    }

    /**
     * 删除用户t_sys_u2r关系记录
     * */
    public void deleteU2RByOperatorId(Long operatorId) {
        StringBuffer sql = new StringBuffer();
        sql.append(" DELETE FROM T_SYS_U2R WHERE OPERATOR_ID = ?");
        Query query = sessionFactory.getCurrentSession().createSQLQuery(
                sql.toString());
        query.setLong(0, operatorId);
        query.executeUpdate();
    }

}

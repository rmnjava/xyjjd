/**
 * Project Name:jjd-web
 * File Name:MenuDaoImpl.java
 * Package Name:com.redstar.jjd.dao.impl
 * Date:2016年6月23日下午5:07:56
 * Copyright (c) 2016, China Link Communications LTD All Rights Reserved.
 *
 */

package com.redstar.jjd.admin.dao.impl;

import java.util.List;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.redstar.jjd.admin.dao.MenuDao;
import com.redstar.jjd.dao.impl.BaseDaoImpl;
import com.redstar.jjd.model.Menu;

/**
 * ClassName: MenuDaoImpl <br/>
 * Date: 2016年6月23日 下午5:07:56 <br/>
 * Description: TODO
 * 
 * @author huangrui
 * @version
 * @see
 */
@Repository("menuDao")
public class MenuDaoImpl extends BaseDaoImpl<Menu, Long> implements MenuDao {

    @Override
    public List<Menu> getRootMenuByOrder() {
        Criterion top = Restrictions.isNull("parent");
        @SuppressWarnings("unchecked")
        List<Menu> list = (List<Menu>) getSession().createCriteria(Menu.class)
                .add(top).addOrder(Order.asc("orderNum")).list();
        return list;
    }

}

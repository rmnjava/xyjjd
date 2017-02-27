/**
 * Project Name:jjd-web
 * File Name:LoanIntoDaoImpl.java
 * Package Name:com.redstar.jjd.admin.dao.impl
 * Date:2016年12月2日下午3:28:56
 * Copyright (c) 2016, China Link Communications LTD All Rights Reserved.
 *
 */

package com.redstar.jjd.admin.dao.impl;

import org.springframework.stereotype.Repository;

import com.redstar.jjd.admin.dao.LoanIntoDao;
import com.redstar.jjd.dao.impl.BaseDaoImpl;
import com.redstar.jjd.model.LoanInto;

/**
 * ClassName: LoanIntoDaoImpl <br/>
 * Date: 2016年12月2日 下午3:28:56 <br/>
 * Description: TODO
 * 
 * @author huangrui
 * @version
 * @see
 */
@Repository("loanIntoDao")
public class LoanIntoDaoImpl extends BaseDaoImpl<LoanInto, Long> implements
        LoanIntoDao {

}

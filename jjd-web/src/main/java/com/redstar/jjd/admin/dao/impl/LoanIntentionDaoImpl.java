/**
 * Project Name:jjd-web
 * File Name:LoanIntentionDaoImpl.java
 * Package Name:com.redstar.jjd.admin.dao.impl
 * Date:2016年11月29日下午3:16:33
 * Copyright (c) 2016, China Link Communications LTD All Rights Reserved.
 *
 */

package com.redstar.jjd.admin.dao.impl;

import org.springframework.stereotype.Repository;

import com.redstar.jjd.admin.dao.LoanIntentionDao;
import com.redstar.jjd.dao.impl.BaseDaoImpl;
import com.redstar.jjd.model.LoanIntention;

/**
 * ClassName: LoanIntentionDaoImpl <br/>
 * Date: 2016年11月29日 下午3:16:33 <br/>
 * Description: TODO
 * 
 * @author huangrui
 * @version
 * @see
 */
@Repository("loanIntentionDao")
public class LoanIntentionDaoImpl extends BaseDaoImpl<LoanIntention, Long>
        implements LoanIntentionDao {

}

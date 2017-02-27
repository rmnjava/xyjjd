/**
 * Project Name:jjd-web
 * File Name:LoanExamineDaoImpl.java
 * Package Name:com.redstar.jjd.admin.dao.impl
 * Date:2016年12月2日下午3:37:14
 * Copyright (c) 2016, China Link Communications LTD All Rights Reserved.
 *
 */

package com.redstar.jjd.admin.dao.impl;

import org.springframework.stereotype.Repository;

import com.redstar.jjd.admin.dao.LoanExamineDao;
import com.redstar.jjd.dao.impl.BaseDaoImpl;
import com.redstar.jjd.model.LoanExamine;

/**
 * ClassName: LoanExamineDaoImpl <br/>
 * Date: 2016年12月2日 下午3:37:14 <br/>
 * Description: TODO
 * 
 * @author huangrui
 * @version
 * @see
 */
@Repository("loanExamineDao")
public class LoanExamineDaoImpl extends BaseDaoImpl<LoanExamine, Long>
        implements LoanExamineDao {

}

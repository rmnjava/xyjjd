/**
 * Project Name:jjd-web
 * File Name:LoanExamineServiceImpl.java
 * Package Name:com.redstar.jjd.admin.service.impl
 * Date:2016年12月2日下午3:38:58
 * Copyright (c) 2016, China Link Communications LTD All Rights Reserved.
 *
 */

package com.redstar.jjd.admin.service.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.redstar.jjd.admin.dao.LoanExamineDao;
import com.redstar.jjd.admin.service.LoanExamineService;
import com.redstar.jjd.dao.UserApplyDao;
import com.redstar.jjd.model.LoanExamine;
import com.redstar.jjd.model.UserApply;
import com.redstar.jjd.utils.ConvertUtils;
import com.redstar.jjd.vo.LoanExamineView;

/**
 * ClassName: LoanExamineServiceImpl <br/>
 * Date: 2016年12月2日 下午3:38:58 <br/>
 * Description: TODO
 * 
 * @author huangrui
 * @version
 * @see
 */
@Service("loanExamineService")
public class LoanExamineServiceImpl implements LoanExamineService {
    @Autowired
    private LoanExamineDao loanExamineDao;

    @Autowired
    private UserApplyDao userApplyDao;

    @Override
    public LoanExamine save(LoanExamineView view) {
        LoanExamine loan = new LoanExamine();
        ConvertUtils.convertObject(view, loan);
        loan.setOperatorTime(new Date(System.currentTimeMillis()));
        LoanExamine loanExamine = loanExamineDao.save(loan);
        // 更新userApply表的贷款审批是否通过状态
        if (loanExamine != null) {
            UserApply userApply = userApplyDao.get(view.getApplyId());
            userApply.setIsLoanSuccess(view.getIsLoanSuccess());
            userApplyDao.saveOrUpdate(userApply);
        }
        return loanExamine;
    }

}

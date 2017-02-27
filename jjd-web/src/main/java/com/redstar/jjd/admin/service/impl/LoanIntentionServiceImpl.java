/**
 * Project Name:jjd-web
 * File Name:LoanIntentionServiceImpl.java
 * Package Name:com.redstar.jjd.admin.service.impl
 * Date:2016年11月29日下午3:17:52
 * Copyright (c) 2016, China Link Communications LTD All Rights Reserved.
 *
 */

package com.redstar.jjd.admin.service.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.redstar.jjd.admin.dao.LoanIntentionDao;
import com.redstar.jjd.admin.service.LoanIntentionService;
import com.redstar.jjd.dao.UserApplyDao;
import com.redstar.jjd.model.LoanIntention;
import com.redstar.jjd.model.UserApply;
import com.redstar.jjd.utils.ConvertUtils;
import com.redstar.jjd.vo.LoanIntentionView;

/**
 * ClassName: LoanIntentionServiceImpl <br/>
 * Date: 2016年11月29日 下午3:17:52 <br/>
 * Description: TODO
 * 
 * @author huangrui
 * @version
 * @see
 */
@Service("loanIntentionService")
public class LoanIntentionServiceImpl implements LoanIntentionService {

    @Autowired
    private LoanIntentionDao loanIntentionDao;

    @Autowired
    private UserApplyDao userApplyDao;

    @Override
    public LoanIntention save(LoanIntentionView view) {
        LoanIntention loan = new LoanIntention();
        ConvertUtils.convertObject(view, loan);
        loan.setOperatorTime(new Date(System.currentTimeMillis()));
        LoanIntention loanIntention = loanIntentionDao.save(loan);
        // 更新userApply表的贷款意向状态
        if (loanIntention != null) {
            UserApply userApply = userApplyDao.get(view.getApplyId());
            userApply.setIsLoan(view.getIsLoan());
            userApplyDao.saveOrUpdate(userApply);
        }
        return loanIntention;
    }

}

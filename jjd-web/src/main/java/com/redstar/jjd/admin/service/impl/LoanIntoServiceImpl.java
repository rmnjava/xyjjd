/**
 * Project Name:jjd-web
 * File Name:LoanIntoServiceImpl.java
 * Package Name:com.redstar.jjd.admin.service.impl
 * Date:2016年12月2日下午3:30:22
 * Copyright (c) 2016, China Link Communications LTD All Rights Reserved.
 *
 */

package com.redstar.jjd.admin.service.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.redstar.jjd.admin.dao.LoanIntoDao;
import com.redstar.jjd.admin.service.LoanIntoService;
import com.redstar.jjd.dao.UserApplyDao;
import com.redstar.jjd.model.LoanInto;
import com.redstar.jjd.model.UserApply;
import com.redstar.jjd.utils.ConvertUtils;
import com.redstar.jjd.vo.LoanIntoView;

/**
 * ClassName: LoanIntoServiceImpl <br/>
 * Date: 2016年12月2日 下午3:30:22 <br/>
 * Description: TODO
 * 
 * @author huangrui
 * @version
 * @see
 */
@Service("loanIntoService")
public class LoanIntoServiceImpl implements LoanIntoService {
    @Autowired
    private LoanIntoDao loanIntoDao;

    @Autowired
    private UserApplyDao userApplyDao;

    @Override
    public LoanInto save(LoanIntoView view) {
        LoanInto loan = new LoanInto();
        ConvertUtils.convertObject(view, loan);
        loan.setOperatorTime(new Date(System.currentTimeMillis()));
        LoanInto loanInto = loanIntoDao.save(loan);
        // 更新userApply表的贷款是否进件状态
        if (loanInto != null) {
            UserApply userApply = userApplyDao.get(view.getApplyId());
            userApply.setIsInto(view.getIsInto());
            userApplyDao.saveOrUpdate(userApply);
        }
        return loanInto;
    }
}

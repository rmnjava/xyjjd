/**
 * Project Name:jjd-web
 * File Name:LoanExamineService.java
 * Package Name:com.redstar.jjd.admin.service
 * Date:2016年12月2日下午3:38:39
 * Copyright (c) 2016, China Link Communications LTD All Rights Reserved.
 *
 */

package com.redstar.jjd.admin.service;

import com.redstar.jjd.model.LoanExamine;
import com.redstar.jjd.vo.LoanExamineView;

/**
 * ClassName: LoanExamineService <br/>
 * Date: 2016年12月2日 下午3:38:39 <br/>
 * Description: TODO
 * 
 * @author huangrui
 * @version
 * @see
 */
public interface LoanExamineService {
    public LoanExamine save(LoanExamineView view);
}

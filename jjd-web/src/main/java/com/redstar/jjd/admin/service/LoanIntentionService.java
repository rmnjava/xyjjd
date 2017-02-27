/**
 * Project Name:jjd-web
 * File Name:LoanIntentionService.java
 * Package Name:com.redstar.jjd.admin.service
 * Date:2016年11月29日下午3:17:32
 * Copyright (c) 2016, China Link Communications LTD All Rights Reserved.
 *
 */

package com.redstar.jjd.admin.service;

import com.redstar.jjd.model.LoanIntention;
import com.redstar.jjd.vo.LoanIntentionView;

/**
 * ClassName: LoanIntentionService <br/>
 * Date: 2016年11月29日 下午3:17:32 <br/>
 * Description: TODO
 * 
 * @author huangrui
 * @version
 * @see
 */
public interface LoanIntentionService {
    public LoanIntention save(LoanIntentionView view);
}

/**
 * Project Name:jjd-web
 * File Name:LoanIntoService.java
 * Package Name:com.redstar.jjd.admin.service
 * Date:2016年12月2日下午3:30:09
 * Copyright (c) 2016, China Link Communications LTD All Rights Reserved.
 *
 */

package com.redstar.jjd.admin.service;

import com.redstar.jjd.model.LoanInto;
import com.redstar.jjd.vo.LoanIntoView;

/**
 * ClassName: LoanIntoService <br/>
 * Date: 2016年12月2日 下午3:30:09 <br/>
 * Description: TODO
 * 
 * @author huangrui
 * @version
 * @see
 */
public interface LoanIntoService {
    public LoanInto save(LoanIntoView view);
}

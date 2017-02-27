/**
 * Project Name:jjd-web
 * File Name:UserLoanView.java
 * Package Name:com.redstar.jjd.vo
 * Date:2016年11月30日下午5:09:47
 * Copyright (c) 2016, China Link Communications LTD All Rights Reserved.
 *
 */

package com.redstar.jjd.vo;

/**
 * ClassName: UserLoanView <br/>
 * Date: 2016年11月30日 下午5:09:47 <br/>
 * Description: TODO
 * 
 * @author huangrui
 * @version
 * @see
 */
public class UserLoanView {

    private Long applyId;

    private LoanIntentionView loanIntentionView;

    private LoanIntoView loanIntoView;

    private LoanExamineView loanExamineView;

    public Long getApplyId() {
        return applyId;
    }

    public void setApplyId(Long applyId) {
        this.applyId = applyId;
    }

    public LoanIntentionView getLoanIntentionView() {
        return loanIntentionView;
    }

    public void setLoanIntentionView(LoanIntentionView loanIntentionView) {
        this.loanIntentionView = loanIntentionView;
    }

    public LoanIntoView getLoanIntoView() {
        return loanIntoView;
    }

    public void setLoanIntoView(LoanIntoView loanIntoView) {
        this.loanIntoView = loanIntoView;
    }

    public LoanExamineView getLoanExamineView() {
        return loanExamineView;
    }

    public void setLoanExamineView(LoanExamineView loanExamineView) {
        this.loanExamineView = loanExamineView;
    }
}

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/layouts/taglibs.jsp"%>
<html>
<head>
</head>
<body>

    <div class="easyui-panel" style="width:auto;">
        <div style="margin:16px 0px 25px 80px;line-height:24px;">
	        <c:if test="${empty userLoan.loanIntentionView.isLoan}">
	                                     还没有任何标记信息。
	        </c:if>
	        <c:if test="${not empty userLoan.loanIntentionView.isLoan}">
		        <div>
	                <span><b>一、是否有贷款意向？（显示结果）</b></span></br>
	                <span>
	                      <span style="color:green;"><c:if test="${userLoan.loanIntentionView.isLoan}">是</c:if></span>
	                      <span style="color:red;"><c:if test="${!userLoan.loanIntentionView.isLoan}">否</c:if></span>
	                </span>
	            </div>
	            <c:if test="${userLoan.loanIntentionView.isLoan}">
	                <div>
	                    <span>顾客类型</span>
	                    <span>
	                          <c:if test="${userLoan.loanIntentionView.customerType eq '1'}">A类</c:if>
	                          <c:if test="${userLoan.loanIntentionView.customerType eq '2'}">B类</c:if>
	                          <c:if test="${userLoan.loanIntentionView.customerType eq '3'}">C类</c:if>
	                          <c:if test="${userLoan.loanIntentionView.customerType eq '0'}">其他</c:if>
	                    </span>
	                </div>
	            </c:if>
	            
	            <div>
	                <span>备注:</span>
	                <span>
	                      ${userLoan.loanIntentionView.note}
	                </span>
	            </div>
	            
	            <div>
	                <span>操作人:</span>
	                <span>
	                      ${userLoan.loanIntentionView.deptName} ${userLoan.loanIntentionView.operatorName}
	                </span>
	            </div>
	            <div>
	                <span>操作时间:</span>
	                <span>
	                      ${userLoan.loanIntentionView.operatorTime}
	                </span>
	            </div>
	        </c:if>

            <c:if test="${not empty userLoan.loanIntoView.isInto}">
	            <div>
	                <span><b>二、是否已进件？</b></span></br>
	                <span>
	                      <span style="color:green;"><c:if test="${userLoan.loanIntoView.isInto}">是</c:if></span>
	                      <span style="color:red;"><c:if test="${!userLoan.loanIntoView.isInto}">终止进件</c:if></span>
	                </span>
	            </div>
	            <c:if test="${userLoan.loanIntoView.isInto}">
		            <div>
	                    <span>进件日期:</span>
	                    <span>
	                          ${userLoan.loanIntoView.intoTime}
	                    </span>
	                </div>
	            </c:if>
	            <div>
	                <span>备注:</span>
	                <span>
	                      ${userLoan.loanIntoView.note}
	                </span>
	            </div>
	            
	            <div>
	                <span>操作人:</span>
	                <span>
	                      ${userLoan.loanIntoView.deptName} ${userLoan.loanIntoView.operatorName}
	                </span>
	            </div>
	            <div>
                    <span>操作时间:</span>
                    <span>
                          ${userLoan.loanIntoView.operatorTime}
                    </span>
                </div>
            </c:if>
            <c:if test="${not empty userLoan.loanExamineView.isLoanSuccess}">
                <div>
                    <span><b>三、审批是否通过？</b></span></br>
                    <span>
                          <span style="color:green;"><c:if test="${userLoan.loanExamineView.isLoanSuccess}">是</c:if></span>
                          <span style="color:red;"><c:if test="${!userLoan.loanExamineView.isLoanSuccess}">否</c:if></span>
                    </span>
                </div>
                <c:if test="${userLoan.loanExamineView.isLoanSuccess}">
                    <div>
                        <span>审批通过日期:</span>
                        <span>
                              ${userLoan.loanExamineView.arrivalTime}
                        </span>
                    </div>
                </c:if>
                <div>
                    <span>备注:</span>
                    <span>
                          ${userLoan.loanExamineView.note}
                    </span>
                </div>
                
                <div>
                    <span>操作人:</span>
                    <span>
                          ${userLoan.loanExamineView.deptName} ${userLoan.loanExamineView.operatorName}
                    </span>
                </div>
                <div>
	                <span>操作时间:</span>
	                <span>
	                      ${userLoan.loanExamineView.operatorTime}
	                </span>
                </div>
            </c:if>
        </div>

    </div>
</body>

</html>

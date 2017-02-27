<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/layouts/taglibs.jsp"%>
<div>
    <div id="title">
        <div class="title left">家居贷后台管理系统</div>
        <div style="float:right;">
            <span class="subtitle right">你好,
                <shiro:principal property="name" />!<a href="${ctx}/admin/operator.do?method=showResetPwdPage">&lt;重置密码&gt;</a> <a href="${ctx}/logout.do">&lt;安全退出&gt;</a></span>
        </div>
    </div>
</div>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ page import="org.apache.shiro.web.filter.authc.FormAuthenticationFilter"%>
<%@ page import="org.apache.shiro.authc.ExcessiveAttemptsException"%>
<%@ page import="org.apache.shiro.authc.IncorrectCredentialsException"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>	 
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<title>家居贷后台管理系统--登录</title>
 	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/login.css"/>
</head>
<body>
	<div class="login">
		<div class="login-c">
			<img class="logo01" src="${pageContext.request.contextPath}/static/images/login/logo.jpg"/>
<!-- 			<a class="link link01" href="javascript:;">设为首页</a> -->
<!-- 			<a class="link link02" href="javascript:;">加入收藏</a> -->
			<form name="frmlogin" id="frmlogin" action="${pageContext.request.contextPath}/login" method="post" >
                <div class="checkWrap" style="*height:10px;">
                    <div class="warning red allCheck">
	                   <c:choose>
	                       <c:when  test="${shiroLoginFailure eq 'org.apache.shiro.authc.UnknownAccountException'}">
	                           <div >该用户不存在.</div>
	                       </c:when>
	                       <c:when test="${shiroLoginFailure eq 'org.apache.shiro.authc.IncorrectCredentialsException'}">
	                           <div >用户或密码错误.</div>
	                       </c:when>
	                       <c:when test="${shiroLoginFailure ne null}">
	                           <div >登录认证错误，请重试.</div>
	                       </c:when>
	                   </c:choose>

                    </div>
                </div>
                <div class="input-wrap">
                    <img src="${pageContext.request.contextPath}/static/images/login/user.png"/>
                    <input type="text" class="itxt" id="username" name="username" placeholder="请输入用户名"/>
                </div>
                <div class="input-wrap input-wrap02">
                    <img src="${pageContext.request.contextPath}/static/images/login/password.png"/>
                    <input type="password" class="itxt" id="password" name="password" placeholder="密码" missingMessage="请输入密码"/>
                </div>
                <input type="submit" id="login" value="" onclick="frmsubmit()"/>
			</form>
		</div>	
	</div>
<script type="text/javascript">

    function frmsubmit(){
    	$("#frmlogin").validate({
    	    rules:{
    	        username:{
    	            "required":true
    	        },
    	        password:'required'
    	    },
    	    messages:{
    	        username:{
    	            required:'请输入用户名！'
    	        },
    	        password:{
    	            required:"请输入密码！"
    	        }
    	        
    	    }
    	})
//     	$.extend($.fn.validatebox.defaults.rules, {
//     		minLength: {
//     	        validator: function(value, param){
//     	            return value.length >= param[0];
//     	        },
//     	        message: '请至少输入 {0}个字符.'
//     	    },
//     	    mobile: {//value值为文本框中的值  
//     	        validator: function (value) {  
//     	            var reg = /^1[3|4|5|8|9]\d{9}$/;  
//     	            return reg.test(value);  
//     	        },  
//     	        message: '输入手机号码格式不准确.'  
//     	    }, 
//     	    //用户账号验证(只能包括 _ 数字 字母)   
//     	    account: {//param的值为[]中值  
//     	        validator: function (value, param) {  
//     	            if (value.length < param[0] || value.length > param[1]) {  
//     	                $.fn.validatebox.defaults.rules.account.message = '用户名长度必须在' + param[0] + '至' + param[1] + '范围';  
//     	                return false;  
//     	            } else {  
//     	                if (!/^[\w]+$/.test(value)) {  
//     	                    $.fn.validatebox.defaults.rules.account.message = '用户名只能数字、字母、下划线组成.';  
//     	                    return false;  
//     	                } else {  
//     	                    return true;  
//     	                }  
//     	            }  
//     	        }, message: '' 
//         }
//     	});
//         var flag = $("#frmlogin").form('validate');
//         if(!flag) return;
//         $("#frmlogin").submit();
        if($("#frmlogin").validate()){
        	$("#frmlogin").submit();
        }
    }
</script>
</body>
</html>
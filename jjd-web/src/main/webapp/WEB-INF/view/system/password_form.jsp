<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/layouts/taglibs.jsp"%>
<form:form id="input-form" name="input-form"
	modelAttribute="imParameter"
	action="${ctx}/imparameter/imparameter.do?method=save" method="post">
	<div id="input-space-1" class="in-ct">
		<div class="ctt-tit-ct">
			<div class="ctt-tit">修改当前用户密码</div>
		</div>
		<div>
			<input type="reset" style="display: none;" />
		</div>
		<div id="input-space-1-content" class="ctt-ctt-ct">
			<div class="ctt-ctt-col-ct">
				<div class="ctt-ctt-col col-2">
					<div class="ctt-ctt-col-name">旧密码:</div>
					<div class="ctt-ctt-col-value">
						<input style="width: 250px;" id="oldPassWord" name="oldPassWord" 
							class="input-text easyui-validatebox" type="password" maxlength="32" 
							data-options="required:true,validType:'valiPwd'"/>
					<font color="red">*</font>
					</div>
					<div id="oldPwds" name="oldPwds"></div>
				</div>
				<div style="clear: both;"></div>
				<!-- 强制换行 -->
				<div class="ctt-ctt-col col-2">
					<div class="ctt-ctt-col-name">新密码:</div>
					<div class="ctt-ctt-col-value">
						<input style="width: 250px;" id="password" name="password" type="password"
							class="input-text easyui-validatebox" required="true" maxlength="32"
							data-options="required:true,validType:'valiNewPwd'"/>
					<font color="red">*</font>
					</div>
				</div>
				<div style="clear: both;"></div>
				<!-- 强制换行 -->
				<div class="ctt-ctt-col col-2">
					<div class="ctt-ctt-col-name">新密码确认:</div>
					<div class="ctt-ctt-col-value">
						<input style="width: 250px;" id="passwordConfirm" type="password"
							name="passwordConfirm" class="input-text easyui-validatebox"
							required="true" validType="equals['#password']" maxlength="32"/>
					<font color="red">*</font>
					</div>
				</div>
				<div><input type="reset"  name="passwordForm" style="display: none;" /></div>
			</div>
		</div>
	</div>
</form:form>

<div id="hdl-ct">
	<div id="hdl">
		<!-- InstanceBeginEditable name="handler" -->

		<div class="hx-pg-btn">
			<a id="handler-ok">确 定</a>
		</div>
	</div>
	<!-- InstanceEndEditable -->
</div>
<script type="text/javascript">
	$(document).ready(function() {
		
		$.extend($.fn.validatebox.defaults.rules, {
		valiPwd : {
			validator : function(value) {
				var flag = false;
				$.ajax({
					   type: "post",
					   url: '${ctx }/sys/modifyPwd.do?method=validatorPwd',
					   async : false,
					   data : {
						   'oldPassWord' : value
					   },
					   success: function(msg){
						   if(msg){
							   flag = true;
						   }
					   }
					});
				return flag;
			},
			message : '密码错误'
		},
		equals : {
			validator : function(value, param) {
				return (value.length>=8)&&(value.length<=32)&&(!/[^\d\w]/gi.test(value))&& value == $(param[0]).val();
			},
			message : '请输入与上面相同的密码'
		},
		valiNewPwd:{
			validator : function(value) {
			    var b1=(value.length>=8)&&(value.length<=32)&&((/\d/.test(value)&&/[a-z]/.test(value))||(/\d/.test(value)&&/[\@\#\!\_\.\*]/.test(value))||(/[\@\#\!\_\.\*]/.test(value)&&/[a-z]/.test(value))||(/\d/.test(value)&&/[A-Z]/.test(value))||(/[A-Z]/.test(value)&&/[a-z]/.test(value))||(/[A-Z]/.test(value)&&/[\@\#\!\_\.\*]/.test(value)));
				 var b2=/[\[\]\$%\^&\*\(\)\|~`\+';:",\\\/<>\?\{}\(\)-]/.test(value);
				  return !b2&&b1;
			},
			message : '密码不合法：允许8-32个字节，允许数字、大小写字母或特殊字符（@#!_.）任意两种及以上。'
		}
		});
		
	});
	$('#handler-ok').click(function(check) {
		var flag = $("#input-form").form('validate');
		if (!flag) {
			return;
		}else {
		$.ajax({
			type : "POST",
			url : '${ctx }/sys/modifyPwd.do?method=update',
			data : $("#input-form").serializeArray(),
			success: function(msg){
				$.messager.alert('提示',msg);
				$("input[type=reset]").trigger("click");
			}
		});
		}
	});
</script>

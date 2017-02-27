<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/layouts/taglibs.jsp"%>
<form:form id="input-form" name="input-form" modelAttribute="role" 
action="${ctx}/sys/role.do?method=save" method="post">
		<div id="input-space-1" class="in-ct">
		<input type="hidden" name="roleId"	value="${role.roleId}" />
		<div class="ctt-tit-ct">
			<div class="ctt-tit">角色信息</div>
		</div>
		<div id="input-space-1-content" class="ctt-ctt-ct">
			<div class="ctt-ctt-col-ct">
				<div class="ctt-ctt-col col-2">
					<div class="ctt-ctt-col-name">角色名:</div>
					<div class="ctt-ctt-col-value">
					<input id="form_roleName" name="roleName"
							style="width: 240px;" value="${role.roleName}"
							class="easyui-validatebox input-text"
							data-options="required:true,validType:'minLength[3]'" />
						<font color="red">*</font>
					</div>
				</div>
				<div class="ctt-ctt-col col-1">
					<div class="ctt-ctt-col-name">权限:</div>
					<div class="ctt-ctt-col-value">
					<input id="form_name" name="name"
							style="width: 240px;" value=""
							class="easyui-validatebox input-text"
							data-options="required:true" />
							<font color="red">*</font>
					</div>
				</div>
			</div>
		</div>
	</div>
</form:form>
	<div id="hdl-ct">
	<div id="hdl">
		<div class="hx-pg-btn">
			<a id="handler-ok">确 定</a>
		</div>
		<div class="hx-pg-btn">
			<a href="javascript:$.closeHXModalWin();" id="handler-cancel">取 消</a>
		</div>
	</div>
</div>
<script type="text/javascript">
	$(document).ready(function() {
		$('#input-form input').keyup(trimkeyup);
						$.extend($.fn.validatebox.defaults.rules, {
							  minLength: {  
							        validator: function(value, param){  
							            return value.length >= param[0];  
							        },  
							        message: '请输入至少 {0} 个字符.'  
							    }
						});
						$('#handler-ok').click(function(check) {
							var flag = $("#input-form").form('validate');
							if (!flag) {
								return;
							}else{
								var actionUrl = $("#input-form").attr("action");
								$.ajax({
									type: "POST",
									url: actionUrl,
									data : $("#input-form").serializeArray(),
									success: function(msg){
										$.messager.alert('提示',msg);
										$('#role-dg-list').datagrid('reload'); 
										$.closeHXModalWin();
									}
								});
								}
						});
					});
</script>

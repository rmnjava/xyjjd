<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/layouts/taglibs.jsp"%>
<html>
<head>
<link type="text/css" rel="stylesheet" href="<c:url value='/static/css/system/role.css' />" />
<script type="text/javascript">
	$(function(){
		$.extend($.fn.validatebox.defaults.rules,{
			roleNameVal:{
		        validator: function(value,param){ 
		        	if(value != null && value.length > 10){
		        		$.fn.validatebox.defaults.rules.roleNameVal.message = "角色长度必须小于10位";
		        		return false;
		        	}
	        		var flag = false;
		        	$.ajax({
					   type: "post",
					   url: '${ctx }/admin/role.do?method=checkRoleName',
					   async : false,
					   data : {
						   'roleNameInput' : value ,
						   'RoleIdExist' : ${role.roleId}
					   },
					   success: function(msg){
						   if(msg == "true"){
							   flag = true;
						   }else{
							   $.fn.validatebox.defaults.rules.roleNameVal.message = "该角色名已存在";
						   }
					   }
					})
				return flag;
	       		},
		        tipPosition: 'left',
		        message: '' 
	        }
		});
		$('#tree-perm').tree({
			url : '<c:url value="/admin/role.do?method=getPermTree"/>'+'&id='+$('#id').val(),
			animate : true,
			checkbox : true
		});

		$("#roleNameInput").validatebox({
			required : true,
			validType : 'roleNameVal'
		});
		
		function beforeFormSubmit() {
			var nodes = $('#tree-perm').tree('getChecked');
			var s = '';
			for ( var i = 0; i < nodes.length; i++) {
				if (s != '')
					s += ',';
				s += nodes[i].id;
			}
			$("#nodes").val(s);
			return true;
		}
		$("#handler-ok").click(function(){
			beforeFormSubmit();
			var nodes = $("#nodes").val();
			var flag = $("#input-form-role").form('validate');
			if(!flag) return;
			$.ajax({
				   type: "post",
				   url: '${ctx }/admin/role.do?method=save',
				   data : $("#input-form-role").serializeArray(),
				   success: function(data){
					   $.messager.alert('提醒',data.msg, 'info', function () {
						   parent.$('#role-dg-list').datagrid('reload');
						   closeWin();
				        });
					   
				   }
			});
		});
	});
	//$('#input-form-role input').keydown(cleartrim);
</script>
</head>
<body>
<div class="r_main">
		<form:form id="input-form-role" modelAttribute="role"
			action="${ctx}/admin/role.do?method=save" method="post">
			<input type="hidden" id="id" name="id" value="${role.roleId}" />			
			<div class="r_tr">
				<div class="r_field">
					<label for="roleNameInput" class="field">角色:</label>
				</div>
				<div class="r_value">
					<input type="text" id="roleNameInput" name="roleNameInput" size="20" value="${role.roleName}" missingMessage="不能为空"/>
				</div>
<%-- 				<shiro:hasPermission name="role:update"> --%>
					<a class="r_save easyui-linkbutton" name="handler-ok" id="handler-ok">保存</a>
<%-- 				 </shiro:hasPermission>	 --%>
			</div>
			<div class="r_tr">
				<div class="r_field">
					<label for="nodes" class="field">权限:</label> <input type="hidden"
						id="nodes" name="nodes" />
				</div>
				<div class="r_value"  style="margin-bottom:20px;" >
					<ul class="r_tree" id="tree-perm"></ul>
				</div>
				<div style="clear:both;"></div>
			</div>
		</form:form>
</div>

</body>
</html>

<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/layouts/taglibs.jsp"%>
<html>
<script>
$(document).ready(
		function() {
		$('#role-dg-list').datagrid({
					idField : 'roleId',
					url : '${ctx}/admin/role.do?method=query',
					singleSelect : false,
					rownumbers : false,
					nowrap : false,
					fit : true,
					fitColumns : true,
					pagination : true,
					pageSize: 15,
					pageList:[15,25,35,45,55],
					checkOnSelect : false,
					selectOnCheck : false,
					onLoadSuccess : function(){
						$("#role-dg-list").datagrid("clearChecked");
					},
					columns : [ [
							{
								field : 'ck',
								checkbox : true
							},
							{
								field : 'roleName',
								title : '角色名',
								width : 100
							},
							{
								field : 'permissionNames',
								title : '权限',
								width : 600
							},
							{
								field : 'createTime',
								title : '创建时间',
								width : 400,
								align : 'center',
								formatter:function(val){
                                    try{                        
                                        return new Date(val).pattern("yyyy/MM/dd HH:mm:ss"); 
                                    }catch(e){
                                        return ""; 
                                    }
                                }
							} ] ]
				});
		
		
		$("#query_role").click(function() {
			var params = $("#role-dg-list").datagrid('options').queryParams;
			var fields = $("#query-form").serializeArray();
			$.each(fields, function(i, field) {
				params[field.name] = field.value;
			});
			$("#role-dg-list").datagrid('load', params);
			$("#role-dg-list").datagrid('clearChecked');
		});
		
		//----添加角色
		$("#add_role").click(function() {
			$.layer({
			    type: 2,
			    shadeClose: true,
			    title: "新增角色",
			    closeBtn: [0, true],
			    shade: [0.8, '#000'],
			    border: [0],
			    offset: ['20px',''],
			    area: ['500px', ($(window).height() - 50) +'px'],
			    iframe: {src: '<c:url value="/admin/role.do?method=createRolePage"/>'}
			}); 
			
		});
		
		//---角色修改
		$("#update_role").click(function() {
			var selectedRow = $('#role-dg-list').datagrid('getChecked');
	            if (selectedRow.length != 1) {
	                $.messager.alert('提示', '请选中一条需要更新的记录', 'info');
	            }else {
	                var roleId = selectedRow[0].roleId;
	                // 超级管理员角色不允许修改
	                if(roleId==4050){
	                	$.messager.alert('提示', '超级管理员不能修改', 'info');
	                	return;
	                }
	                $.layer({
	                    type: 2,
	                    shadeClose: true,
	                    title: "修改角色",
	                    closeBtn: [0, true],
	                    shade: [0.8, '#000'],
	                    border: [0],
	                    offset: ['20px',''],
	                    area: ['500px', ($(window).height() - 50) +'px'],
	                    iframe: {src: '${ctx }/admin/role.do?method=updateRolePage&roleId='+roleId}
	                }); 
	                
	            }
                    
		});
});
</script>
<body>
	<div id="role-page" class="easyui-layout" fit="true">
		<div data-options="region:'north',split:false" style="height:30px;overflow:hidden;">
			<form id="query-form">
				<div style="float: left;padding-right:5px;">
					角色名: <input id="roleName" name="roleName" style="width: 80px">
					创建时间：<input id="startTime" name="startTime"  onfocus="WdatePicker({maxDate:'#F{$dp.$D(\'endTime\')}'})" 
						class="textbox Wdate" style="width: 140px"/>至<input id="endTime" name="endTime" onfocus="WdatePicker({minDate:'#F{$dp.$D(\'startTime\')}'})" 
					    class="textbox Wdate" style="width: 140px" />
				</div>
				<div id="search_role">
					<shiro:hasPermission name="system.role:view">
						<a id="query_role" href="javascript:void(0)"
						class="easyui-linkbutton" data-options="iconCls:'icon-search'">查询</a>
					</shiro:hasPermission>
					<shiro:hasPermission name="system.role:add">
						<a href="javascript:void(0)" class="easyui-linkbutton"
						data-options="iconCls:'icon-add'" id="add_role">添加</a>
					</shiro:hasPermission> 
					<shiro:hasPermission name="system.role:update">
						<a id="update_role" class="easyui-linkbutton" data-options="iconCls:'icon-edit'">编辑</a>
					 </shiro:hasPermission>
					<shiro:hasPermission name="system.role:delete"> 
						<a id="batchDel_role" href="javascript:void(0)"
						class="easyui-linkbutton" data-options="iconCls:'icon-remove'">批量删除</a>
					</shiro:hasPermission>
				</div>
			</form>
		</div>
		
		
		<div data-options="region:'center',title:'角色信息列表'"
			style="padding: 5px; background: #eee;">
			<table id="role-dg-list">
			</table>
		</div>
	</div>
</body>
<script type="text/javascript">

	$("#batchDel_role").click(function() {
		var selectedRow = $('#role-dg-list').datagrid('getChecked');
		var roleStr = "";
		if (selectedRow.length < 1) {
			$.messager.alert('提示', '请选中需要删除的记录', 'info');
		} else {
			for ( var i=0;i<selectedRow.length;i++) {
				roleStr = roleStr + "," + selectedRow[i].roleId;
			}
			roleStr = roleStr.substring(1);
			
			$.messager.confirm('提示', "确认删除？", function(r) {
				if (r) {
					$.ajax({
						type : "post",
						url : '${ctx }/admin/role.do?method=batchDel',
						data : 'ids=' + roleStr,
						success : function(data) {
							$("#role-dg-list").datagrid('reload');
							$.messager.alert('提示',data.msg);
						}
					});
				}
			});
		}
	});
	
</script>
</html>

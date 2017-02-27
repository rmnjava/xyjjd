<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/layouts/taglibs.jsp"%>
<html>
<head>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/common.css"/>
</head>
<script>
$(document).ready(
		function() {
			  $('#dept-dg-list').datagrid({
					idField : 'deptId',
					url : '${ctx}/admin/dept.do?method=query',
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
						$("#dept-dg-list").datagrid("clearChecked");
					},
					columns : [ [
							{
								field : 'ck',
								checkbox : true
							},
                            {
                                field : 'deptName',
                                title : '部门',
                                width : 100
                            },
                            {
                                field : 'deptDesc',
                                title : '部门描述',
                                width : 100
                            } ] ]
	    });
		
		
		$("#query_dept").click(function() {
			var params = $("#dept-dg-list").datagrid('options').queryParams;
			var fields = $("#query-form").serializeArray();
			$.each(fields, function(i, field) {
				params[field.name] = field.value;
			});
			$("#dept-dg-list").datagrid('load', params);
			$("#dept-dg-list").datagrid('clearChecked');
		});
		
		//----添加部门
		$("#add_dept").click(function() {
			$('#dlg').dialog('open').dialog('setTitle','添加部门');
		    $('#input-form-dept').form('clear');
		    $("#deptId").val(null);
		    $("#deptName").removeAttr("readonly");
		});
		
	    //---部门修改
        $("#update_dept").click(function() {
	        var selectedRow = $('#dept-dg-list').datagrid('getChecked');
	        if (selectedRow.length != 1) {
	            $.messager.alert('提示', '请选中一条需要更新的记录', 'info');
	        } else {
	            var deptId = selectedRow[0].deptId;
	            $("#deptId").val(deptId);
	            $("#deptName").attr("readonly","readonly");
	            var data = {
	                    deptName:selectedRow[0].deptName,
	                    deptDesc:selectedRow[0].deptDesc
	            }
	            var row = $("#dept-dg-list").datagrid("getSelected");
	            $('#dlg').dialog('open').dialog('setTitle','修改部门');
	            $('#input-form-dept').form('load',data);

	        }
        });
	      

		$("#handler-ok").click(function(){
	        $.extend($.fn.validatebox.defaults.rules, {
	            nameVal:{
	                validator: function(value){ 
	                    var flag = false;
	                        $.ajax({
	                           type: "post",
	                           url: '${ctx}/admin/dept.do?method=isNameExist',
	                           async : false,
	                           data : {
	                               'deptName' : value,
	                               'deptId' : $("#deptId").val()
	                           },
	                           success: function(msg){
	                               if(msg){
	                                   flag = true;
	                               }else{
	                                   $.fn.validatebox.defaults.rules.nameVal.message = "该名称已存在";
	                               }
	                           }
	                        })
	                    return flag;
	                    },
	                    message: '' 
	                }
	        });
            //校验并提交表单
            var flag = $("#input-form-dept").form('validate');
            if(!flag) {
            	return;
            }
            $.ajax({
                type: "post",
                url: '${ctx}/admin/dept.do?method=save&'+Math.random(),
                data : $("#input-form-dept").serializeArray(),
                success: function(data){
                    $.messager.alert('提醒',data.msg, 'info', function () {
                    	$('#dlg').dialog('close');
                    	$('#dept-dg-list').datagrid('reload');
                     });
                    
                }
            });
		
		 });
		
		$("#batchDel_dept").click(function() {
	        var selectedRow = $('#dept-dg-list').datagrid('getChecked');
	        var deptStr = "";
	        if (selectedRow.length < 1) {
	            $.messager.alert('提示', '请选中需要删除的记录', 'info');
	        } else {
	            for ( var i=0;i<selectedRow.length;i++) {
	                deptStr = deptStr + "," + selectedRow[i].deptId;
	            }
	            deptStr = deptStr.substring(1);
	            $.messager.confirm('提示', "确认删除？", function(r) {
	                if (r) {
	                    $.ajax({
	                        type : "post",
	                        url : '${ctx }/admin/dept.do?method=batchDel',
	                        data : 'ids=' + deptStr,
	                        success : function(data) {
	                            $.messager.alert('提示',data.msg);
	                            $("#dept-dg-list").datagrid('reload');
	                        }
	                    });
	                }
	            });
	        }
	    });
		
});
</script>
<body>
	<div id="dept-page" class="easyui-layout" fit="true">
		<div data-options="region:'north',split:false" style="height:30px;overflow:hidden;">
			<form id="query-form">
				<div style="float: left;line-height:26px;padding-right:5px;">
					部门名称: <input name="deptName" style="width: 100px">
				</div>
				<div id="search_dept">
					<shiro:hasPermission name="system.department:view">
						<a id="query_dept" href="javascript:void(0)"
						class="easyui-linkbutton" data-options="iconCls:'icon-search'">查询</a>
					</shiro:hasPermission>
					<shiro:hasPermission name="system.department:add">
						<a href="javascript:void(0)" class="easyui-linkbutton"
						data-options="iconCls:'icon-add'" id="add_dept">添加</a>
					</shiro:hasPermission> 
					<shiro:hasPermission name="system.department:update">
						<a href="javascript:void(0)" id="update_dept" class="easyui-linkbutton" data-options="iconCls:'icon-edit'">编辑</a>
					</shiro:hasPermission>
					<shiro:hasPermission name="system.department:delete"> 
						<a id="batchDel_dept" href="javascript:void(0)"
						class="easyui-linkbutton" data-options="iconCls:'icon-remove'">批量删除</a>
					</shiro:hasPermission>
				</div>
			</form>
		</div>
		
		<div data-options="region:'center',title:'部门信息列表'"
			style="padding: 5px; background: #eee;">
			<table id="dept-dg-list">
			</table>
		</div>
	</div>
	
	<div id="dlg" class="easyui-dialog" style="width:420px;height:230px;padding:10px 20px"
            closed="true" buttons="#dlg-buttons">
        <form  id="input-form-dept" class="fm" method="post">
	        <input type="hidden" id="deptId" name="deptId" value=""/>
            <div class="fitem">
                <label>部门:</label>
                <input id="deptName" name="deptName" class="easyui-validatebox" style="width:160px;" data-options="required:true,validType:'nameVal'" 
                 missingMessage="不能为空" maxlength="30">
            </div>
            <div class="fitem">
                <label>部门描述:</label>
                <textarea id="deptDesc" class="easyui-validatebox" name="deptDesc" style="width:160px;height:78px;" maxlength="128" 
                data-options="required:true" missingMessage="不能为空"></textarea>
            </div>
        </form>
    </div>
    <div id="dlg-buttons">
        <a href="#" class="easyui-linkbutton" iconCls="icon-ok" id="handler-ok">确定</a>
        <a href="#" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#dlg').dialog('close')">取消</a>
    </div>
</body>

</html>

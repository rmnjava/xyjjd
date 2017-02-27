<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/layouts/taglibs.jsp"%>
<html>
<head>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/common.css"/>
</head>
<script>
jQuery.validator.addMethod("chrnum", function(value,element,param) {  
    var phone =  /^[a-zA-z]{1}[a-zA-Z0-9]+$/;
    return this.optional(element) || phone.test(value);
}, "");
jQuery.validator.addMethod("chinese", function(value, element) {  
    var chinese = /^[\u4e00-\u9fa5]+$/;  
    return this.optional(element) || (chinese.test(value));  
}, "只能输入中文");
//手机号码验证      
jQuery.validator.addMethod("isMobile", function(value, element) {
  var length = value.length;
  var mobile = /^(((13[0-9]{1})|(15[0-9]{1})|(18[0-9]{1}))+\d{8})$/;
  return this.optional(element) || (length == 11 && mobile.test(value));
}, "请正确填写您的手机号码"); 
$(document).ready(function() {
		$("#form_storeId").hide();
		$('#operator-dg-list').datagrid({
			idField : 'operatorId',
			url : '${ctx}/admin/operator.do?method=query',
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
				$("#operator-dg-list").datagrid("clearChecked");
			},
			columns : [ [
					{
						field : 'ck',
						checkbox : true
					},
					{
						field : 'loginName',
						title : '登录名',
						width : 100
					},
					{
                              field : 'name',
                              title : '姓名',
                              width : 100
                          },
                          {
                              field : 'deptName',
                              title : '部门',
                              width : 100
                          },
                          {
                              field : 'roleNames',
                              title : '角色名',
                              width : 100
                          },
					{
						field : 'createTime',
						title : '创建时间',
						width : 200,
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

		$.extend($.fn.validatebox.methods, {    
            remove: function(jq, newposition){    
                return jq.each(function(){    
                    $(this).removeClass("validatebox-text validatebox-invalid").unbind('focus.validatebox').unbind('blur.validatebox');  
                });    
            },  
            reduce: function(jq, newposition){    
                return jq.each(function(){    
                   var opt = $(this).data().validatebox.options;
                   $(this).addClass("validatebox-text").validatebox(opt);  
                });    
            }     
        }); 
		
		$("#query_operator").click(function() {
			var params = $("#operator-dg-list").datagrid('options').queryParams;
			var fields = $("#query-form").serializeArray();
			$.each(fields, function(i, field) {
				params[field.name] = field.value;
			});
			
			$("#operator-dg-list").datagrid('load', params);
			$("#operator-dg-list").datagrid('clearChecked');
		});
		
		//----添加账户
		$("#add_operator").click(function() {
			$('#dlg').dialog('open').dialog('setTitle','添加账户');
		    $('#input-form-operator').form('clear');
		    $("#operatorId").val(null);
		    $("#username").removeAttr("readonly");
		    $("#username").parent(".fitem").show();
		    $("#form_roleId").removeAttr("disabled");
		    $("#form_roleId option[value='4050']").remove();
		    $("#form_storeId").hide();
		});
		
	      //---账户修改
        $("#update_operator").click(function() {
            var selectedRow = $('#operator-dg-list').datagrid('getChecked');
            if (selectedRow.length != 1) {
                $.messager.alert('提示', '请选中一条需要更新的记录', 'info');
            } else {
                var operatorId = selectedRow[0].operatorId;
                $("#operatorId").val(operatorId);
                var roleList = selectedRow[0].roleList;
                var role;
                for (var i=0; i<roleList.length;i++){
                    role = roleList[i];
                    if (role.roleId==4050){
                        $("#form_roleId").append("<option value='4050'>超级管理员</option>"); 
                    	$("#form_roleId").attr("disabled","disabled"); 
                    } else {
                    	$("#form_roleId").removeAttr("disabled");
                    	$("#form_roleId option[value='4050']").remove();
                    }
                    if((role.roleName=="商场业务") || (role.roleName=="商场财务")){
                        $("#form_storeId").show();
                        $("#provinceCode").val(selectedRow[0].provinceCode);
                        $.ajax({
                            type:'post',
                            url:"${ctx}/admin/store.do?method=getCitysByProvince&provinceCode="+$("#provinceCode").val(),
                            success:function(msg){
                                var options="";
                                for(var i=0;i<msg.length;i++){
                                    options+="<option value='"+msg[i]["id"]+"'>"+msg[i]["name"]+"</option>";
                                }
                                $('#cityCode').empty().append(options);
                                $("#cityCode").val(selectedRow[0].cityCode);
                                $.ajax({
                                    type:'post',
                                    async:false,
                                    url:"${ctx}/admin/store.do?method=getStoresByCity&cityCode="+$("#cityCode").val(),
                                    success:function(msg){
                                        var options="";
                                        for(var i=0;i<msg.length;i++){
                                            options+="<option value='"+msg[i]["id"]+"'>"+msg[i]["name"]+"</option>";
                                        }
                                        $('#storeId').empty().append(options);
                                        $("#storeId").val(selectedRow[0].storeId);
                                    },
                                    error:function(msg){
                                    }
                                });
                            },
                            error:function(msg){
                            }
                        });
                        
                    }else{
                        $("#form_storeId").hide();
                    }
                }
                var data = {
                    roleId:role.roleId,
                    loginName:selectedRow[0].loginName,
                    password:selectedRow[0].password,
                    deptId:selectedRow[0].deptId,
                    name:selectedRow[0].name,
                    storeId: selectedRow[0].storeId                       
                }
                $('#dlg').dialog('open').dialog('setTitle','修改账户');
                $("#username").parent(".fitem").hide();
                $('#input-form-operator').form('load',data);
                $("#repassword").val("");
                $("#password").val("");
            }
        });
	      
        $('#roleId').combobox({
            url : '${ctx}/admin/role.do?method=getAllRoleName',
            valueField : 'id',
            textField : 'name',
            value:'',
            editable: false,
            panelHeight:'auto'      
          
        });
		$("#input-form-operator").validate({
	        rules:{
	        	loginName:{
                    required:true,
                    minlength:5,
                    maxlength:16,
                    chrnum:true,
                    remote:"${ctx}/admin/operator.do?method=isNameExist"
                },
                password:{
                    required:true,
                    rangelength:[6,6]
                },
                repassword:{
                    required:true,
                    equalTo:"#password"
                },
                name:{
                    required:true,
                    chinese:true
                },
                phone:{
                	required:true,
                	isMobile:true
                },
                deptId:{
                    required:true 
                },
                roleId:{
                    required:true 
                },
                provinceCode:{
                    required:true 
                },
                cityCode:{
                    required:true 
                },
                storeId:{
                    required:true 
                }
	        },
	        messages:{                  
	        	loginName:{
	                required:"用户名不能为空！",
	                minlength:"用户名不能少于5位！",
	                maxlength:"用户名不能超过16位！",
	                chrnum:"用户名必须是字母开始,字母和数字组成！",
	                remote:"用户名重复！"
	            },
	            password:{
	            	required:"请输入密码！",
            		rangelength:"密码必须是6位英文或数字！"
                },
                repassword:{
                	required:"请输入确认密码！",
                	equalTo:"两次密码不一致！"
                },
                name:{
                	required:"请输入姓名！",
                    chinese:"只能输入中文！"
                },
                phone:{
                    required:"请输入手机号码！",
                    isMobile:"请正确填写您的手机号码!"
                },
                deptId:{
                	required:"请选择部门！" 
                },
                roleId:{
                	required:"请选择角色！" 
                },
                provinceCode:{
                    required:"请选择省份！" 
                },
                cityCode:{
                    required:"请选择城市！"
                },
                storeId:{
                    required:"请选择商场！" 
                }
	        }
	    }); 
		$("#handler-ok").click(function(){
	       if($("#input-form-operator").valid()){
	    	   //设置role的值
// 	           var r = $("#form_roleId").combobox("getValue");
	    	   var r = $("#form_roleId").val();
	           var roleStr = "";
	           roleStr = roleStr+r+",";
	           $("#role").val(roleStr);
	    	   $.ajax({
	                type: "post",
	                url: '${ctx}/admin/operator.do?method=save&'+Math.random(),
	                data : $("#input-form-operator").serializeArray(),
	                success: function(data){
	                    $.messager.alert('提醒',data.msg, 'info', function () {
	                        $('#dlg').dialog('close');
	                        $('#operator-dg-list').datagrid('reload');
	                     });
	                    
	                }
	            });
	       }
	    });
		
	    //导出Excel
	    $("#export").click(function(){
	        $.messager.confirm("提示", "确定导出所有信息吗?",function(r){
	            if(r){
	                $(".shieldBg").show();
	                var queryParam =  "";
	                if($('#loginName').val()!=""){
	                    var loginName = $('#loginName').val();
	                    queryParam+="&qloginName="+loginName;
	                }
	                var roleId=$("#roleId").combobox('getValue');
	                if(roleId!="-1"&&roleId!=null){
	                    queryParam+="&qroleId="+roleId;
	                }
	                
	                location.href="${ctx}/admin/operator.do?method=exportOperator" + queryParam;
	                $(".shieldBg").hide();

	            }
	        });
	    });
		

});
</script>
<body>
	<div id="role-page" class="easyui-layout" fit="true">
		<div data-options="region:'north',split:false" style="height:30px;overflow:hidden;">
			<form id="query-form">
				<div style="float: left;padding-right:5px;">
					用户名: <input id="loginName" name="qloginName" style="width: 80px">
					角色类型：<select class="easyui-combobox" id="roleId" name="qroleId"
                    style="width: 100px"></select>
				</div>
				<div id="search_role">
					<shiro:hasPermission name="system.operator:view">
						<a id="query_operator" href="javascript:void(0)"
						class="easyui-linkbutton" data-options="iconCls:'icon-search'">查询</a>
					</shiro:hasPermission>
					<shiro:hasPermission name="system.operator:add">
						<a href="javascript:void(0)" class="easyui-linkbutton"
						data-options="iconCls:'icon-add'" id="add_operator">添加</a>
					</shiro:hasPermission> 
					<shiro:hasPermission name="system.operator:update">
						<a href="javascript:void(0)" id="update_operator" class="easyui-linkbutton" data-options="iconCls:'icon-edit'">编辑</a>
					 </shiro:hasPermission>
					<shiro:hasPermission name="system.operator:delete"> 
						<a id="batchDel_operator" href="javascript:void(0)"
						class="easyui-linkbutton" data-options="iconCls:'icon-remove'">批量删除</a>
					</shiro:hasPermission>
					<a iconcls="icon-print" class="easyui-linkbutton"  id="export">导出</a>
				</div>
			</form>
		</div>
		<div class="shieldBg none"></div>
		<div data-options="region:'center',title:'账户信息列表'"
			style="padding: 5px; background: #eee;">
			<table id="operator-dg-list">
			</table>
		</div>
	</div>
	
	<div id="dlg" class="easyui-dialog" style="width:450px;height:350px;padding:10px 20px"
            closed="true" buttons="#dlg-buttons">
        <form  id="input-form-operator" class="fm" method="post">
	        <input type="hidden" id="role" name="role" value="" />
	        <input type="hidden" id="operatorId" name="operatorId" value=""/>
            <div class="fitem" >
                <label>用户名:</label>
                <input id="username" name="loginName" required="required"  value="" maxLength="12"/>
            </div>
            <div id="pwd" class="fitem">
                <label>密码:</label>
                <input id="password" name="password" type="password" value="" maxLength="12"/>
            </div>
            <div id="repwd" class="fitem">
                <label>确认密码:</label>
                <input type="password" name="repassword" id="repassword" maxLength="12"/>
            </div>
            <div class="fitem">
                <label>姓名:</label>
                <input name="name" maxLength="6"/>
            </div>
            <div class="fitem">
                <label>手机号码:</label>
                <input name="phone" maxLength="11"/>
            </div>
            <div class="fitem">
                <label>部门:</label>
                <select id="deptId" name="deptId"></select>
                <!-- <input class="easyui-combobox" id="deptId" name="deptId" /> -->
            </div>
            <div class="fitem">
                <label>角色:</label>
                <select id="form_roleId" name="roleId"></select>
                <!-- <input class="easyui-combobox" id="form_roleId" name="roleId"  editable="false" value=""/> -->
            </div>
            <div id="form_storeId" style="display:none;">
                <div class="fitem">
                <label>省:</label>
                <select id="provinceCode" name="provinceCode"></select>
	            </div>
	            <div class="fitem">
	                <label>城市:</label>
	                <select id="cityCode" name="cityCode"></select>
	            </div>
	            <div class="fitem">
	                <label>所属商场:</label>
	                <select id="storeId" name="storeId"></select>
	            </div>
            </div>
        </form>
    </div>
    <div id="dlg-buttons">
        <a href="#" class="easyui-linkbutton" iconCls="icon-ok" id="handler-ok">确定</a>
        <a href="#" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#dlg').dialog('close')">取消</a>
    </div>
</body>
<script type="text/javascript">

    //获取省
	$.ajax({
	    type:'post',
	    url:"${ctx}/admin/store.do?method=getAllProvince",
	    success:function(msg){
	        var options="";
	        for(var i=0;i<msg.length;i++){
	            options+="<option value='"+msg[i]["id"]+"'>"+msg[i]["name"]+"</option>";
	        }
	        $('#provinceCode').empty().append(options);
	    },
	    error:function(msg){
	    }
	});
	$('#provinceCode').change(function(){
		var provinceCode=$(this).children("option:selected").val();
		$.ajax({
	        type:'post',
	        url:"${ctx}/admin/store.do?method=getCitysByProvince&provinceCode="+provinceCode,
	        success:function(msg){
	            var options="";
	            for(var i=0;i<msg.length;i++){
	                options+="<option value='"+msg[i]["id"]+"'>"+msg[i]["name"]+"</option>";
	            }
	            $('#cityCode').empty().append(options);
	        },
	        error:function(msg){
	        }
	    });
	});
	
	$('#cityCode').change(function(){
		var cityCode=$(this).children("option:selected").val();
		$.ajax({
            type:'post',
            url:"${ctx}/admin/store.do?method=getStoresByCity&cityCode="+cityCode,
            success:function(msg){
                var options="";
                for(var i=0;i<msg.length;i++){
                    options+="<option value='"+msg[i]["id"]+"'>"+msg[i]["name"]+"</option>";
                }
                $('#storeId').empty().append(options);
            },
            error:function(msg){
            }
        });
	});
	/* $('#provinceCode').combobox({
	    url : '${ctx}/admin/store.do?method=getAllProvince',
	    valueField : 'id',
	    textField : 'name',
	    value:'-1',
	    panelHeight:300,
	    required:true,
	    editable: false,
	    onChange:function(provinceCode){
	          //$('#city').combobox('clear');
	        $('#cityCode').combobox({
	            valueField:'id', //值字段
	            textField:'name', //显示的字段
	            url:'${ctx}/admin/store.do?method=getCitysByProvince&provinceCode='+provinceCode,
	            panelHeight:300,
	            required:true,
	            editable: false,//不可编辑，只能选择
	            value:'---请选择---',
	            onChange:function(cityCode){
	              $('#storeId').combobox({
	                  valueField:'id', //值字段
	                  textField:'name', //显示的字段
	                  url:'${ctx}/admin/store.do?method=getStoresByCity&cityCode='+cityCode,
	                  panelHeight:300,
	                  required:true,
	                  editable: false,//不可编辑，只能选择
	                  value:'---请选择---',
	                  
	                  
	                  });
	              }
	            
	            });
	        }
	    
   });
     */
	//获取部门
	$.ajax({
        type:'post',
        url:"${ctx}/admin/dept.do?method=getAll",
        success:function(msg){
            var options="";
            for(var i=0;i<msg.length;i++){
                options+="<option value='"+msg[i]["id"]+"'>"+msg[i]["name"]+"</option>";
            }
            $('#deptId').append(options);
        },
        error:function(msg){
        }
    });
    //获取角色
	$.ajax({
        type:'post',
        url:"${ctx}/admin/role.do?method=getAllRoleName",
        success:function(msg){
        	var options="";
        	for(var i=0;i<msg.length;i++){
        		options+="<option value='"+msg[i]["id"]+"'>"+msg[i]["name"]+"</option>";
        	}
        	$('#form_roleId').append(options);
        },
        error:function(msg){
        }
    });
	$('#form_roleId').change(function(){
		var text=$(this).children("option:selected").text();
		if((text=="商场业务") || (text=="商场财务")){
			$("#form_storeId").show();
		}else{
			$("#form_storeId").hide();
		}		
	});

	$("#batchDel_operator").click(function() {
		var selectedRow = $('#operator-dg-list').datagrid('getChecked');
		var operatorStr = "";
		if (selectedRow.length < 1) {
			$.messager.alert('提示', '请选中需要删除的记录', 'info');
		} else {
			for ( var i=0;i<selectedRow.length;i++) {
				operatorStr = operatorStr + "," + selectedRow[i].operatorId;
			}
			operatorStr = operatorStr.substring(1);
			$.messager.confirm('提示', "确认删除？", function(r) {
				if (r) {
					$.ajax({
						type : "post",
						url : '${ctx }/admin/operator.do?method=batchDel',
						data : 'ids=' + operatorStr,
						success : function(data) {
							$.messager.alert('提示',data.msg);
							$("#operator-dg-list").datagrid('reload');
						}
					});
				}
			});
		}
	});
</script>
</html>

<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/layouts/taglibs.jsp"%>
<html>
<head>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/common.css"/>
</head>

<body>
	<div id="store-page" class="easyui-layout" fit="true">
		<div data-options="region:'north',split:false" style="height:30px;overflow:hidden;">
			<form id="query-form">
				<div style="float: left;line-height:26px;padding-right:5px;">
					商场名称: <input name="storeName" style="width: 100px">
					商场地址: <input name="storeAddress" style="width: 100px">
					金融机构名称: <select class="easyui-combobox" id="qbankId" name="qbankId" style="width:100px"></select>
				</div>
				<div id="search_store">
					<shiro:hasPermission name="storemgr.store:view">
						<a id="query_store" href="javascript:void(0)"
						class="easyui-linkbutton" data-options="iconCls:'icon-search'">查询</a>
					</shiro:hasPermission>
					<shiro:hasPermission name="storemgr.store:add">
						<a href="javascript:void(0)" class="easyui-linkbutton"
						data-options="iconCls:'icon-add'" id="add_store">添加</a>
					</shiro:hasPermission> 
					<shiro:hasPermission name="storemgr.store:update">
						<a href="javascript:void(0)" id="update_store" class="easyui-linkbutton" data-options="iconCls:'icon-edit'">编辑</a>
					 </shiro:hasPermission>
					<shiro:hasPermission name="storemgr.store:delete"> 
						<a id="batchDel_store" href="javascript:void(0)"
						class="easyui-linkbutton" data-options="iconCls:'icon-remove'">批量删除</a>
					</shiro:hasPermission>
					<a iconcls="icon-print" class="easyui-linkbutton"  id="store_bank_export">商场匹配金融机构导出</a>
				</div>
			</form>
		</div>
		
		<div data-options="region:'center',title:'商场信息列表'"
			style="padding: 5px; background: #eee;">
			<table id="store-dg-list">
			</table>
		</div>
	</div>
	<div class="shieldBg none"></div>
	
	<div id="dlg" class="easyui-dialog" style="width:400px;height:280px;padding:10px 20px"
            closed="true" buttons="#dlg-buttons">
        <form  id="input-form-store" class="fm" method="post">
	        <input type="hidden" id="id" name="id" value=""/>
	        <div class="fitem">
                <label>省:</label>
                <select class="easyui-combobox" id="provinceCode" name="provinceCode" style="width:126px" 
                data-options="required:true" missingMessage="不能为空"></select>
            </div>
            <div class="fitem">
                <label>城市:</label>
                <select class="easyui-combobox" id="cityCode" name="cityCode" style="width:126px" 
                data-options="required:true" missingMessage="不能为空"></select>
            </div>
            <div class="fitem">
                <label>商场名称:</label>
                <input id="name" name="name" class="easyui-validatebox" value="" data-options="required:true,validType:'nameVal'" 
                 missingMessage="不能为空" maxlength="64" style="width:122px" >
            </div>
            <div class="fitem">
                <label>商场地址:</label>
                <textarea id="address" name="address" style="width:120px;height:68px;" maxlength="128"></textarea>
<!--                 <input name="address" class="easyui-textbox" data-options="multiline:true"  -->
<!--                 value="" maxlength="128"> -->
            </div>
        </form>
    </div>
    <div id="dlg-buttons">
        <a href="#" class="easyui-linkbutton" iconCls="icon-ok" id="handler-ok">确定</a>
        <a href="#" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#dlg').dialog('close')">取消</a>
    </div>
    
    <!-- 添加金融机构dialog -->
    <div id="bank" class="easyui-dialog" style="width:400px;height:200px;padding:10px 20px"
            closed="true" buttons="#bank-buttons">
        <form  id="form-bank" class="fm" method="post" action="">
            <input type="hidden" id="storeId" name="storeId" value=""/>
            <input type="hidden" id="bankId" name="bankId" value=""/>
            <input type="hidden" id="productId" name="productId" value=""/>
            <input type="hidden" id="storeProductId" name="storeProductId" value=""/>
            <div class="fitem">
                <label>银行名称:</label>
                <select class="easyui-combobox" id="sbankId" name="bankId" style="width:100px" required="true" missingMessage="不能为空" ></select>
                
            </div>
        </form>
    </div>
    <div id="bank-buttons">
        <a href="#" class="easyui-linkbutton" iconCls="icon-ok" id="saveBank">确定</a>
        <a href="#" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#bank').dialog('close')">取消</a>
    </div>
</body>
<script>
    //导出商场匹配金融机构Excel
    $("#store_bank_export").click(function(){
        $.messager.confirm("提示", "确定导出所有信息吗?",function(r){
            if(r){
                $(".shieldBg").show();
                location.href="${ctx}/admin/store.do?method=storeBankExport";
                $(".shieldBg").hide();
    
            }
        });
    });
    
    //添加金融机构dialog
    function addBank(which){
        $('#bank').dialog('open').dialog('setTitle','添加金融机构');
        $("#storeId").val($(which).attr("storeId"));
        $('#storeProductId').val("");
        $('#sbankId').val("");
        var storeId=$("#storeId").val();
        //获取银行
        $('#sbankId').combobox({
            url : '${ctx}/admin/product.do?method=findBanksByStore&storeId='+storeId,
            valueField : 'id',
            textField : 'name',
            value:'',
            panelHeight:'auto',
            editable: false,
            onSelect:function(record){
                var bankId=$("#sbankId").combobox("getValue");
                $("#bankId").val(bankId);
                $.ajax({     
                    url: "${ctx}/admin/product.do?method=getProductByBankId&bankId="+bankId,
                    type: "POST",
                    dataType: "json",
                    success: function (data) {
                        $("#productId").val(data.id);
                    }
                });
        }
        });
    }

    
    // 删除金融机构
    function bankDelete(which){
        $.messager.confirm("提示", "确定删除吗?",function(r){
            if(r){
                $.ajax({
                    type : "post",
                    url : '${ctx }/admin/store.do?method=delStoreProduct',
                    data : {'id' : $(which).attr("returnId")},
                    success : function(data) {
                        $.messager.alert('提示',data.msg);
                        $("#store-dg-list").datagrid('reload');
                    }
                });
            }
        });
    }
    
    
$(document).ready(
        function() {
        	//获取银行
            $('#qbankId').combobox({
                url : '${ctx}/admin/product.do?method=getAllBank&hasBankPay=-1',
                valueField : 'id',
                textField : 'name',
                value:'',
                panelHeight:'auto'
            });
        	
            $('#store-dg-list').datagrid({
                    idField : 'id',
                    url : '${ctx}/admin/store.do?method=query',
                    singleSelect : false,
                    rownumbers : false,
                    nowrap : false,
                    fit : true,
                    fitColumns : true,
                    pagination : true,
                    pageSize: 10,
                    pageList:[10,20,30,40,50],
                    checkOnSelect : false,
                    selectOnCheck : false,
                    onLoadSuccess : function(){
                        $("#store-dg-list").datagrid("clearChecked");
                    },
                    columns : [ [
                            {
                                field : 'ck',
                                checkbox : true
                            },
                            {
                                field : 'provinceName',
                                title : '省',
                                width : 100
                            },
                            {
                                field : 'cityName',
                                title : '城市',
                                width : 100
                            },
                            {
                                field : 'name',
                                title : '商场名称',
                                width : 100
                            },
                            {
                                field : 'address',
                                title : '商场地址',
                                width : 100
                            },
                            {   field:"oper",
                                title:'操作',
                                width:100,
                                formatter:function(val,row){
//                                  var html="<a href='javascript:;' storeId='"+row.id+"' class='btn yellow' onclick='addBank(this)'>添加金融机构</a>";
                                    var html="<shiro:hasPermission name='storemgr.store.bank:add'><a href='javascript:;' storeId='"+row.id+"' class='btn yellow' onclick='addBank(this)'>添加金融机构</a></shiro:hasPermission>";
                                    return html;
                            }}] ],
                            view: detailview,
                            detailFormatter:function(index,row){
                                return '<div style="padding:2px"><table class="ddv"></table></div>'; 
                            },
                            onExpandRow: function(index,row){
                                var ddv = $(this).datagrid('getRowDetail',index).find('table.ddv');
                                ddv.datagrid({
                                    url:'${ctx}/admin/store.do?method=queryStoreProduct&storeId='+row.id,
                                    fitColumns:true,
                                    singleSelect:true,
                                    rownumbers:true,
                                    loadMsg:'请稍等...',
                                    autoRowHeight:true,
                                    columns:[[
                                        {field:'bankName',title:'金融机构',width:100},
                                        {field:"number",title:'操作',width:300,formatter:function(val,row){
//                                          var html="<a href='javascript:;' storeProductId='"+row.storeProductId+"' class='btn green' onclick='bankModify(this,"+row.productId+","+row.storeId+","+row.bankId+")'>修改</a><a href='javascript:;' returnId='"+row.storeProductId+"' class='btn green' onclick='bankDelete(this)'>删除</a>";
//                                          var html="<a href='javascript:;' returnId='"+row.storeProductId+"' class='btn green' onclick='bankDelete(this)'>删除</a>";
                                            var html="<shiro:hasPermission name='storemgr.store.bank:delete'><a href='javascript:;' returnId='"+row.storeProductId+"' class='btn green' onclick='bankDelete(this)'>删除</a></shiro:hasPermission>";
                                            return html;
                                        }}
                                    ]],
                                    onResize:function(){
                                        $('#store-dg-list').datagrid('fixDetailRowHeight',index);
                                    },
                                    onLoadSuccess:function(){
                                        setTimeout(function(){
                                            $('#store-dg-list').datagrid('fixDetailRowHeight',index);
                                        },0);
                                    }
                                });
                                $('#store-dg-list').datagrid('fixDetailRowHeight',index);
                            }
        });
        
        
        $("#query_store").click(function() {
            var params = $("#store-dg-list").datagrid('options').queryParams;
            var fields = $("#query-form").serializeArray();
            $.each(fields, function(i, field) {
                params[field.name] = field.value;
            });
            $("#store-dg-list").datagrid('load', params);
            $("#store-dg-list").datagrid('clearChecked');
        });
        
        //----添加商场
        $("#add_store").click(function() {
            $('#dlg').dialog('open').dialog('setTitle','添加商场');
            $('#input-form-store').form('clear');
            $("#id").val(null);
            $("#name").removeAttr("readonly");
            $("#provinceCode").combobox({  
                disabled: false  
            });
            $("#cityCode").combobox({  
                disabled: false  
            });
        });
        
        //---商场修改
        $("#update_store").click(function() {
            var selectedRow = $('#store-dg-list').datagrid('getChecked');
            if (selectedRow.length != 1) {
                $.messager.alert('提示', '请选中一条需要更新的记录', 'info');
            } else {
                var id = selectedRow[0].id;
                $("#id").val(id);
                $("#name").attr("readonly","readonly");
                var data = {
                        name : selectedRow[0].name,
                        address : selectedRow[0].address,
                        provinceCode : selectedRow[0].provinceCode,
                        cityCode : selectedRow[0].cityCode
                }
                var row = $("#store-dg-list").datagrid("getSelected");
                $('#dlg').dialog('open').dialog('setTitle','修改商场');
                $("#provinceCode").val(data.provinceCode);
                $("#cityCode").val(data.cityCode);
                $("#provinceCode").combobox({  
                    disabled:true  
                });
                $("#cityCode").combobox({  
                    disabled:true  
                }); 
                $('#input-form-store').form('load',data);
            }
        });
        
        //获取省
        $('#provinceCode').combobox({
            url : '${ctx}/admin/store.do?method=getAllProvince',
            valueField : 'id',
            textField : 'name',
            value:'---请选择---',
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
                    value:'---请选择---'
                    
                    });
                }
            
        });
          
        // 保存商场
        $("#handler-ok").click(function(){
            $.extend($.fn.validatebox.defaults.rules, {
                nameVal:{
                    validator: function(value){ 
                        var flag = false;
                            $.ajax({
                               type: "post",
                               url: '${ctx}/admin/store.do?method=isNameExist',
                               async : false,
                               data : {
                                   'storeName' : value,
                                   'storeId' : $("#id").val()
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
            var flag = $("#input-form-store").form('validate');
            if(!flag) {
                return;
            }
            $.ajax({
                type: "post",
                url: '${ctx}/admin/store.do?method=save&'+Math.random(),
                data : $("#input-form-store").serializeArray(),
                success: function(data){
                    $.messager.alert('提醒',data.msg, 'info', function () {
                        $('#dlg').dialog('close');
                        $('#store-dg-list').datagrid('reload');
                     });
                    
                }
            });
        
         });
        
        // 删除商场
        $("#batchDel_store").click(function() {
            var selectedRow = $('#store-dg-list').datagrid('getChecked');
            var deptStr = "";
            if (selectedRow.length < 1) {
                $.messager.alert('提示', '请选中需要删除的记录', 'info');
            } else {
                for ( var i=0;i<selectedRow.length;i++) {
                    deptStr = deptStr + "," + selectedRow[i].id;
                }
                deptStr = deptStr.substring(1);
                $.messager.confirm('提示', "确认删除？", function(r) {
                    if (r) {
                        $.ajax({
                            type : "post",
                            url : '${ctx }/admin/store.do?method=batchDel',
                            data : 'ids=' + deptStr,
                            success : function(data) {
                                $.messager.alert('提示',data.msg);
                                $("#store-dg-list").datagrid('reload');
                            }
                        });
                    }
                });
            }
        });
        
        // 保存金融机构
        $("#saveBank").click(function(){
            //校验并提交表单
            var flag = $("#form-bank").form('validate');
            if(!flag) {
                return;
            }
            $.ajax({
                type: "post",
                url: '${ctx}/admin/store.do?method=saveStoreProduct&'+Math.random(),
                data : $("#form-bank").serializeArray(),
                success: function(data){
                    $.messager.alert('提醒',data.msg, 'info', function () {
                        $('#bank').dialog('close');
                        $('#store-dg-list').datagrid('reload');
                     });
                    
                }
            });

         });
        
});
</script>
</html>

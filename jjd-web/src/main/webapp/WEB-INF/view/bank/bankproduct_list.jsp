<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/layouts/taglibs.jsp"%>
<html>
<head>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/common.css"/>
</head>
<script>

// 添加利率dialog
function addRate(which){
    $('#rate').dialog('open').dialog('setTitle','添加利率');
    $('#form-product-rate').form('clear');
    $("#productId").val($(which).attr("productId"));
}
// 修改利率
function rateModify(which){
	$("#rateId").val($(which).attr("returnId"));
	$.ajax({
        type : "post",
        url : '${ctx }/admin/product.do?method=getRateById',
        data : 'rateId=' + $(which).attr("returnId"),
        success : function(data) {
        	$('#period').val(data.period);
        	$('#interest').val(data.interest);
        	$('#companyAllowance').val(data.companyAllowance);
        	$('#storeAllowance').val(data.storeAllowance);
        	$('#productId').val(data.productId);
        	$('#isZero').combobox('setValue', data.isZero); 
        }
    });
	var data = {
			period : $('#period').val(),
			interest : $('#interest').val(),
			companyAllowance : $('#companyAllowance').val(),
			storeAllowance : $('#storeAllowance').val(),
            productId : $('#productId').val(),
            isZero : $('#isZero').val()
    }
    $('#rate').dialog('open').dialog('setTitle','修改金融机构');
	$('#form-product-rate').form('load',data);
    
}

// 删除利率
function rateDelete(which){
    $.messager.confirm("提示", "确定删除吗?",function(r){
        if(r){
            $.ajax({
                type : "post",
                url : '${ctx }/admin/product.do?method=delRate',
                data : 'id=' + $(which).attr("returnId"),
                success : function(data) {
                    $.messager.alert('提示',data.msg);
                    $("#product-dg-list").datagrid('reload');
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
			  $('#product-dg-list').datagrid({
					idField : 'id',
					url : '${ctx}/admin/product.do?method=query',
					autoRowHeight: true,
					singleSelect : false,
					rownumbers : false,
					nowrap : true,
					fit : true,
					fitColumns : true,
					pagination : true,
					pageSize: 10,
					pageList:[10,20,30,40,50],
					checkOnSelect : false,
					selectOnCheck : false,
					onLoadSuccess : function(){
						$("#product-dg-list").datagrid("clearChecked");
					},
					columns : [ [
							{
								field : 'ck',
								checkbox : true,
								sortable : false
							},
                            {
                                field : 'bankName',
                                title : '银行名称',
                                width : 100,
                                sortable : false
                            },
                            {
                                field : 'minAmount',
                                title : '最小贷款额度（万）',
                                width : 100,
                                sortable : false
                            } ,
                            {
                                field : 'maxAmount',
                                title : '最大贷款额度（万）',
                                width : 100,
                                sortable : false
                            },
                            {
                                field : 'startTime',
                                title : '合作开始时间',
                                width : 100,
                                sortable : false,
                                formatter:function(val){
                                    try{
                                    	if(val!=null){
                                    		return new Date(val).pattern("yyyy/MM/dd"); 
                                    	}else{
                                    		return "";
                                    	}
                                        
                                    }catch(e){
                                        return ""; 
                                    }
                                }
                            },
                            {
                                field : 'endTime',
                                title : '合作结束时间',
                                width : 100,
                                sortable : false,
                                formatter:function(val){
                                    try{                        
                                    	if(val!=null){
                                            return new Date(val).pattern("yyyy/MM/dd"); 
                                        }else{
                                            return "";
                                        }
                                    }catch(e){
                                        return ""; 
                                    }
                                }
                            },
                            {
                                field : 'description',
                                title : '产品介绍',
                                width : 100,
                                sortable : false
                            },
                            {
                                field : 'applyCondition',
                                title : '申请条件',
                                width : 100,
                                sortable : false
                            },
                            {
                                field : 'requireMaterial',
                                title : '所需材料',
                                width : 200,
                                sortable : false
                            },
                            {
                                field : 'incomingStandard',
                                title : '进件资料标准',
                                width : 100,
                                sortable : false
                            },
                            {
                                field : 'creditPolicy',
                                title : '授信政策',
                                width : 100,
                                sortable : false
                            },
                            {
                                field : 'payMethodDesc',
                                title : '还款方式',
                                width : 100,
                                sortable : false
                            },
                            {   field:"oper",
                            	title:'操作',
                            	width:100,
                            	formatter:function(val,row){
//                             		var html="<a href='javascript:;' productId='"+row.id+"' class='btn yellow' onclick='addRate(this)'>添加利率</a>";
                                    var html="<shiro:hasPermission name='bankmgr.bank.rate:add'><a href='javascript:;' productId='"+row.id+"' class='btn yellow' onclick='addRate(this)'>添加利率</a></shiro:hasPermission>";
                            	    return html;
                            }}] ],
                            view: detailview,
                            detailFormatter:function(index,row){
                            	return '<div style="padding:2px"><table class="ddv"></table></div>'; 
                            },
                            onExpandRow: function(index,row){
                            	var ddv = $(this).datagrid('getRowDetail',index).find('table.ddv');  
                            	ddv.datagrid({
                                    url:'${ctx}/admin/product.do?method=queryRate&productId='+row.id,
                                    fitColumns:true,
                                    singleSelect:true,
                                    rownumbers:true,
                                    loadMsg:'请稍等...',
                                    autoRowHeight:true,
                                    columns:[[
                                        {field:'isZero',title:'是否零利率',width:100,formatter:function(val,row){
                                        	if(val){
                                        		return "是";
                                        	}else
                                            return "否";
                                        }},
                                        {field:'period',title:'分期期数',width:100},
                                        {field:'interest',title:'标准利率',width:100},
                                        {field:'companyAllowance',title:'星易通汇补贴',width:100},
                                        {field:'storeAllowance',title:'商场补贴',width:100},
                                        {field:"number",title:'操作',width:300,formatter:function(val,row){
//                                         	var html="<a href='javascript:;' returnId='"+row.id+"' class='btn green' onclick='rateModify(this)'>修改</a><a href='javascript:;' returnId='"+row.id+"' class='btn green' onclick='rateDelete(this)'>删除</a>";
                                        	var html="<shiro:hasPermission name='bankmgr.bank.rate:update'><a href='javascript:;' returnId='"+row.id+"' class='btn green' onclick='rateModify(this)'>修改</a></shiro:hasPermission><shiro:hasPermission name='bankmgr.bank.rate:delete'><a href='javascript:;' returnId='"+row.id+"' class='btn green' onclick='rateDelete(this)'>删除</a></shiro:hasPermission>";
                                        	return html;
                                        }}
                                    ]],
                                    onResize:function(){
                                        $('#product-dg-list').datagrid('fixDetailRowHeight',index);
                                    },
                                    onLoadSuccess:function(){
                                        setTimeout(function(){
                                            $('#product-dg-list').datagrid('fixDetailRowHeight',index);
                                        },0);
                                    }
                                });
                                $('#product-dg-list').datagrid('fixDetailRowHeight',index);
                            }
	    });
			  
		$("#query_product").click(function() {
			var params = $("#product-dg-list").datagrid('options').queryParams;
			var fields = $("#query-form").serializeArray();
			$.each(fields, function(i, field) {
				params[field.name] = field.value;
			});
			$("#product-dg-list").datagrid('load', params);
			$("#product-dg-list").datagrid('clearChecked');
		});
		
		//----添加金融机构
		$("#add_product").click(function() {
			$('#dlg').dialog('open').dialog('setTitle','添加金融机构');
		    $('#input-form-product').form('clear');
		    $("#id").val(null);
		    $("#name").removeAttr("readonly");
		});
		
	    //---金融机构修改
        $("#update_product").click(function() {
	        var selectedRow = $('#product-dg-list').datagrid('getChecked');
	        if (selectedRow.length != 1) {
	            $.messager.alert('提示', '请选中一条需要更新的记录', 'info');
	        } else {
	            var id = selectedRow[0].id;
	            $("#id").val(id);
	            $("#sbankId").val(selectedRow[0].bankId);
	            $("#name").attr("readonly","readonly");
	            var data = {
	            		id:selectedRow[0].id,
	            		bankView:{
	            			id : selectedRow[0].bankId,
	            			name : selectedRow[0].bankName
	            		},
	            		bankProductView:{
	            			maxAmount : selectedRow[0].maxAmount,
                            minAmount : selectedRow[0].minAmount,
                            maxPeriod : selectedRow[0].maxPeriod,
                            Description : selectedRow[0].description,
                            applyCondition : selectedRow[0].applyCondition,
                            requireMaterial : selectedRow[0].requireMaterial,
                            startTime : selectedRow[0].startTime,
                            endTime : selectedRow[0].endTime,
                            payMethodDesc : selectedRow[0].payMethodDesc,
                            incomingStandard : selectedRow[0].incomingStandard,
                            creditPolicy : selectedRow[0].creditPolicy,
                            hasBankPay : selectedRow[0].hasBankPay,
                            note : selectedRow[0].note
	            		}
	            		
	                    
	            }
	            var startTime;
	            if(data.bankProductView.startTime!=null){
	            	startTime = new Date(data.bankProductView.startTime).pattern("yyyy/MM/dd");
	            }
	            var endTime;
	            if(data.bankProductView.endTime!=null){
	            	endTime= new Date(data.bankProductView.endTime).pattern("yyyy/MM/dd");
	            }
	            $("#name").val(data.bankView.name);
	            $("#maxAmount").val(data.bankProductView.maxAmount);
	            $("#minAmount").val(data.bankProductView.minAmount);
	            $("#Description").val(data.bankProductView.Description);
	            $("#applyCondition").val(data.bankProductView.applyCondition);
	            $("#requireMaterial").val(data.bankProductView.requireMaterial);
	            $("#qStartTime").val(startTime);
	            $("#qEndTime").val(endTime);
	            $("#payMethodDesc").val(data.bankProductView.payMethodDesc);
	            $("#incomingStandard").val(data.bankProductView.incomingStandard);
	            $("#note").val(data.bankProductView.note);
	            $("#creditPolicy").val(data.bankProductView.creditPolicy);
	            $("#hasBankPay").val(data.bankProductView.hasBankPay);
	            $('#dlg').dialog('open').dialog('setTitle','修改金融机构');
	            $('#input-form-product').form('load',data);

	        }
        });
	      
        // 检验规则
        $.extend($.fn.validatebox.defaults.rules, {
        	integ:{
                validator:function(value,param){
                    return /^[+]?[0-9]\d*$/.test(value);
                },
                message: '请输入整数'
            },
        	decimalNumber:{
                validator: function (value, param) {
                  return /^[0-9]+(\.[0-9]+)?$/.test(value);
                 },
                 message:'请输入小数'
            },
            testAmount: {
                  validator: function (value, param) {
                      var d1 = $(param[0]).val();
                      return parseFloat(value) >= parseFloat(d1);
                  },  
                  message: '最大贷款额度必须大于或等于最小贷款额度'  
            },
            
            mone:{
                validator: function (value, param) {
                  return (/^(([1-9]\d*)|\d)(\.\d{1,})?$/).test(value);
                 },
                 message:'请输入整数或小数'
              },
            nameVal:{
                validator: function(value){ 
                    var flag = false;
                        $.ajax({
                           type: "post",
                           url: '${ctx}/admin/product.do?method=isNameExist',
                           async : false,
                           data : {
                               'bankName' : value,
                               'bankId' : $("#sbankId").val()
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
        
        function check(){
        	var startTime = $("#qStartTime").val();
        	var endTime = $("#qEndTime").val();
        	if(startTime == null|| startTime==""){
        		$.messager.alert('提示',"合作开始时间不能为空");
        		return false;
        	}
        	if(endTime == null|| endTime==""){
        		$.messager.alert('提示',"合作结束时间不能为空");
        		return false
        	}
        	return true;
        }
        
        // 校验表单并保存金融机构
		$("#handler-ok").click(function(){
            //校验并提交表单
            var flag = $("#input-form-product").form('validate');
            if(check()){
            	if(flag){
            		$.ajax({
                        type: "post",
                        dataType : 'json',
                        url: '${ctx}/admin/product.do?method=save',
                        data : $("#input-form-product").serializeArray(),
                        success: function(data){
                            $.messager.alert('提醒',data.msg, 'info', function () {
                                $('#dlg').dialog('close');
                                $('#product-dg-list').datagrid('reload');
                             });
                            
                        }
                    });
            	}
            }
		 });
		// 删除产品
		$("#batchDel_product").click(function() {
	        var selectedRow = $('#product-dg-list').datagrid('getChecked');
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
	                        url : '${ctx }/admin/product.do?method=batchDel',
	                        data : 'ids=' + deptStr,
	                        success : function(data) {
	                            $.messager.alert('提示',data.msg);
	                            $("#product-dg-list").datagrid('reload');
	                        }
	                    });
	                }
	            });
	        }
	    });
		
		// 校验表单并保存利率
		$("#saveRate").click(function(){
		    //校验并提交表单
		    var flag = $("#form-product-rate").form('validate');
		    if(!flag) {
		        return;
		    }
		    $.ajax({
		        type: "post",
		        url: '${ctx}/admin/product.do?method=saveRate&'+Math.random(),
		        data : $("#form-product-rate").serializeArray(),
		        success: function(data){
		            $.messager.alert('提醒',data.msg, 'info', function () {
		                $('#rate').dialog('close');
		                $('#product-dg-list').datagrid('reload');
		             });
		            
		        }
		    });

		 });
		
	    //导出Excel
	    $("#btnOut").click(function(){
	        $.messager.confirm("提示", "确定导出所有信息吗?",function(r){
	            if(r){
	                $(".shieldBg").show();
	                var queryParam =  "";
	                var bankId=$("#qbankId").combobox('getValue');
	                if(bankId!="-1"&&bankId!=null){
	                    queryParam+="&qbankId="+bankId;
	                }
	               
	                location.href="${ctx}/admin/product.do?method=exportBank" + queryParam;
	                $(".shieldBg").hide();

	            }
	        });
	    });
		
});
</script>
<body>
	<div id="store-page" class="easyui-layout" fit="true">
		<div data-options="region:'north',split:false" style="height:30px;overflow:hidden;">
			<form id="query-form">
				<div style="float: left;line-height:26px;">
					银行名称: <select class="easyui-combobox" id="qbankId" name="qbankId" style="width:100px"></select>
				</div>
				<div id="search_store">
					<shiro:hasPermission name="bankmgr.bank:view">
						<a id="query_product" href="javascript:void(0)"
						class="easyui-linkbutton" data-options="iconCls:'icon-search'">查询</a>
					</shiro:hasPermission>
					<shiro:hasPermission name="bankmgr.bank:add">
						<a href="javascript:void(0)" class="easyui-linkbutton"
						data-options="iconCls:'icon-add'" id="add_product">添加</a>
					</shiro:hasPermission> 
					<shiro:hasPermission name="bankmgr.bank:update">
						<a href="javascript:void(0)" id="update_product" class="easyui-linkbutton" data-options="iconCls:'icon-edit'">编辑</a>
					 </shiro:hasPermission>
					<shiro:hasPermission name="bankmgr.bank:delete"> 
						<a id="batchDel_product" href="javascript:void(0)"
						class="easyui-linkbutton" data-options="iconCls:'icon-remove'">批量删除</a>
					</shiro:hasPermission>
					
					<a iconcls="icon-print" class="easyui-linkbutton"  id="btnOut">导出</a>
					
				</div>
			</form>
		</div>
		<div class="shieldBg none"></div>
		<div data-options="region:'center',title:'金融机构信息列表'"
			style="padding: 5px; background: #eee;">
			<table id="product-dg-list">
			</table>
		</div>
	</div>
	
	<!-- 添加金融机构dialog -->
	<div id="dlg" class="easyui-dialog" style="width:400px;height:500px;padding:10px 20px"
            closed="true" buttons="#dlg-buttons">
        <form  id="input-form-product" class="fm" method="post">
	        <input type="hidden" id="id" name="bankProductView.id" value=""/>
	        <input type="hidden" id="sbankId" name="bankView.id" value=""/>
	        <input type="hidden" id="hasBankPay" name="bankProductView.hasBankPay" value=""/>
            <div class="fitem">
                <label>银行名称:</label>
                <input id="name" name="bankView.name" class="easyui-validatebox" value="" data-options="required:true,validType:'nameVal'" 
                 missingMessage="不能为空" maxlength="20"/>
            </div>
            <div class="fitem">
                <label>最小贷款额度:</label>
                <input id="minAmount" name="bankProductView.minAmount" class="easyui-validatebox" value="" maxlength="10" 
                data-options="required:true,validType:'mone'" missingMessage="不能为空"/>万元
            </div>
            <div class="fitem">
                <label>最大贷款额度:</label>
                <input id="maxAmount" name="bankProductView.maxAmount" class="easyui-validatebox" value="" maxlength="10" 
                data-options="required:true,validType:['mone','testAmount[\'#minAmount\']']"  missingMessage="不能为空" />万元
            </div>
            <div class="fitem">
                <label>产品介绍:</label>
                <input id="Description" name="bankProductView.Description" class="easyui-validatebox" value="" maxlength="128" data-options="required:true" missingMessage="不能为空" />
            </div>
            <div class="fitem">
                <label>申请条件:</label>
                <input id="applyCondition" name="bankProductView.applyCondition" class="easyui-validatebox" value="" maxlength="128" data-options="required:true" missingMessage="不能为空"/>
            </div>
            <div class="fitem">
                <label>所需材料:</label>
                <input id="requireMaterial" name="bankProductView.requireMaterial" class="easyui-validatebox" value="" maxlength="128" data-options="required:true" missingMessage="不能为空"/>
            </div>
            <div class="fitem">
                <label>合作开始时间:</label>
                <input type="text" class="textbox Wdate" size="20" id="qStartTime" 
                        name="bankProductView.startTime" onfocus="WdatePicker({dateFmt:'yyyy/MM/dd',maxDate:'#F{$dp.$D(\'qEndTime\')}'})" value=""  required/>
            </div>
            <div class="fitem">
                <label>合作结束时间:</label>
                <input type="text" class="textbox Wdate"  size="20" id="qEndTime" 
                            name="bankProductView.endTime" onfocus="WdatePicker({dateFmt:'yyyy/MM/dd',minDate:'#F{$dp.$D(\'qStartTime\')}'})" value="" required />
            </div>
            <div class="fitem">
                <label>进件资料标准:</label>
                <input id="incomingStandard" name="bankProductView.incomingStandard" class="easyui-validatebox" value="" maxlength="128" data-options="required:true" missingMessage="不能为空">
            </div>
            <div class="fitem">
                <label>还款方式:</label>
                <input id="payMethodDesc" name="bankProductView.payMethodDesc" class="easyui-validatebox" value="" maxlength="128" data-options="required:true" missingMessage="不能为空">
            </div>
            <div class="fitem">
                <label>授信政策:</label>
                <input id="creditPolicy" name="bankProductView.creditPolicy" class="easyui-validatebox" value="" maxlength="128" data-options="required:true" missingMessage="不能为空">
            </div>
<!--             <div class="fitem"> -->
<!--                 <label>是否开通快捷通道:</label> -->
<!--                 <select class="easyui-combobox" id="hasBankPay" name="bankProductView.hasBankPay" style="width:80px;" data-options="required:true,editable:false,panelHeight:'auto'"  missingMessage="不能为空"> -->
<!--                         <option value="1">是</option> -->
<!--                         <option value="0">否</option> -->
<!--                 </select> -->
<!--             </div> -->
            <div class="fitem">
                <label>备注信息:</label>
                <textarea id="note" name="bankProductView.note" style="width:120px;height:58px;" maxlength="128"></textarea>
            </div>
            
        </form>
    </div>
    <div id="dlg-buttons">
        <a href="#" class="easyui-linkbutton" iconCls="icon-ok" id="handler-ok">确定</a>
        <a href="#" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#dlg').dialog('close')">取消</a>
    </div>
    
    <!-- 添加利率dialog -->
    <div id="rate" class="easyui-dialog" style="width:460px;height:300px;padding:10px 20px"
            closed="true" buttons="#rate-buttons">
        <form  id="form-product-rate" class="fm" method="post" action="">
            <input type="hidden" id="bankId" name="bankId" value=""/>
            <input type="hidden" id="productId" name="productId" value=""/>
            <input type="hidden" id="rateId" name="rateId" value=""/>
            <div class="fitem">
                <label>是否0利率:</label>
                <select class="easyui-combobox" id="isZero" name="isZero" style="width:123px;" data-options="required:true,editable:false,panelHeight:'auto'" data-options="required:true" missingMessage="不能为空">
                        <option value="1">是</option>
                        <option value="0">否</option>
                </select>
            </div>
            <div class="fitem">
                <label>分期期数:</label>
                <input id="period" name="period" class="easyui-validatebox" value="" data-options="required:true,validType:'integ'" 
                 missingMessage="不能为空" maxlength="6">
            </div>
            <div class="fitem">
                <label>标准利率:</label>
                <input id="interest" name="interest" class="easyui-validatebox" value="" data-options="required:true,validType:'decimalNumber'" missingMessage="不能为空" maxlength="8" >
                <br><p style="color:red;">请输入小数，不用带百分号。例如：5.5%只需输入0.055。</p>
            </div>
            <div class="fitem">
                <label>星易通汇补贴:</label>
                <input id="companyAllowance" name="companyAllowance" class="easyui-validatebox" data-options="validType:'decimalNumber'" value="" maxlength="8">
                <br><p style="color:red;">请输入小数，不用带百分号。例如：5.5%只需输入0.055。</p>
            </div>
            <div class="fitem">
                <label>商场补贴:</label>
                <input  id="storeAllowance" name="storeAllowance" class="easyui-validatebox" data-options="validType:'decimalNumber'" value="" maxlength="8">
                <br><p style="color:red;">请输入小数，不用带百分号。例如：5.5%只需输入0.055。</p>
            </div>
            
        </form>
    </div>
    <div id="rate-buttons">
        <a href="#" class="easyui-linkbutton" iconCls="icon-ok" id="saveRate">确定</a>
        <a href="#" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#rate').dialog('close')">取消</a>
    </div>
</body>

</html>

<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/layouts/taglibs.jsp"%>

   <div id="tbdiv">
        <form id="query-form">
        <div style="padding:5px">  
                <span>手机号:</span>
                <input id="phone" name="phone" type="text" style="width:110px">
                <span>申请开始时间:</span>
                <input type="text" class="textbox Wdate" size="20" id="qStartTime" 
                name="qStartTime" onfocus="WdatePicker({maxDate:'#F{$dp.$D(\'qEndTime\')}'})" value="${qStartTime }" style="width:120px"/>
                <span>申请结束时间:</span>
                <input type="text" class="textbox Wdate" size="20" id="qEndTime" 
                    name="qEndTime" onfocus="WdatePicker({minDate:'#F{$dp.$D(\'qStartTime\')}'})" value="${qEndTime }" style="width:120px"/>
                <span>银行:</span>
                <select class="easyui-combobox" id="qbankId" name="qbankId" style="width:100px"></select>
                <span>渠道:</span>
                <select class="easyui-combobox" id="dataSource" name="dataSource" style="width:80px;" data-options="editable:false,panelHeight:'auto'">
                        <option value="-1">全部</option>
                        <option value="">APP</option>
                        <option value="2">微信</option>
                        <option value="3">H5</option>
                        <option value="4">PC</option>
                        <option value="5">商场录入</option>
                </select>
                <span>是否有意向:</span>
                <select class="easyui-combobox" id="isLoan" name="isLoan" style="width:80px;" data-options="editable:false,panelHeight:'auto'">
                        <option value="">全部</option>
                        <option value="1">是</option>
                        <option value="0">否</option>
                </select>
                <span>是否已进件:</span>
                <select class="easyui-combobox" id="isInto" name="isInto" style="width:80px;" data-options="editable:false,panelHeight:'auto'">
                        <option value="">全部</option>
                        <option value="1">是</option>
                        <option value="0">否</option>
                </select>
                <span>审批是否通过:</span>
                <select class="easyui-combobox" id="isLoanSuccess" name="isLoanSuccess" style="width:80px;" data-options="editable:false,panelHeight:'auto'">
                        <option value="">全部</option>
                        <option value="1">是</option>
                        <option value="0">否</option>
                </select>
                
                <span>顾客类型:</span>
                <select class="easyui-combobox" id="qCustomerType" name="qCustomerType" style="width:80px;" data-options="editable:false,panelHeight:'auto'">
                        <option value="-1">全部</option>
                        <option value="1">A类</option>
                        <option value="2">B类</option>
                        <option value="3">C类</option>
                        <option value="0">其他</option>
                </select>
                
                <shiro:hasPermission name="jjd.special.info:view">
                    <a id="query" class="easyui-linkbutton" iconCls="icon-search">查询</a>
                </shiro:hasPermission>
                
                <shiro:hasPermission name="jjd.special.info:storeadd">
                        <a href="javascript:void(0)" class="easyui-linkbutton"
                        data-options="iconCls:'icon-add'" id="add_storeloan">商场录入</a>
                </shiro:hasPermission> 
                
                <shiro:hasPermission name="jjd.special.info:export">
                    <a iconcls="icon-print" class="easyui-linkbutton"  id="btnOut">导出</a>
                </shiro:hasPermission>
            </div> 
            </form>
    </div>

<div class="shieldBg none"></div>
<table id="userInfoGrid">
</table>

<!-- 是否有贷款意向dialog -->
	<div id="loan" class="easyui-dialog" style="width:460px;height:300px;padding:10px 20px"
	        closed="true" buttons="#loan-buttons">
	    <form  id="form-loan-intention" class="fm" method="post" action="">
	        <input type="hidden" id="applyId" name="loanIntentionView.applyId" value=""/>
	        <div class="fitem">
	            <label>是否有意向:</label>
		            <input class="easyui-validatebox" type="radio" name="loanIntentionView.isLoan" value="1" validType="requireRadio['#form-loan-intention input[name=\'loanIntentionView.isLoan\']', '是或者否']">是
	                <input class="easyui-validatebox" type="radio" name="loanIntentionView.isLoan" value="0">否
	        </div>
	        <div class="fitem">
	            <label>备注:</label>
	            <textarea id="note" name="loanIntentionView.note" style="width:200px;height:100px;" maxlength="128"></textarea>
	        </div>
	        <div id="typeDiv" class="fitem" style="display:none;">
                <label>顾客类型:</label>
                    <input id="customerType" class="easyui-validatebox" type="radio" name="loanIntentionView.customerType" value="1" validType="requireRadio['#form-loan-intention input[name=\'loanIntentionView.customerType\']', '顾客类型']">A
                    <input class="easyui-validatebox" type="radio" name="loanIntentionView.customerType" value="2">B
                    <input class="easyui-validatebox" type="radio" name="loanIntentionView.customerType" value="3">C
                    <input class="easyui-validatebox" type="radio" name="loanIntentionView.customerType" value="0">其他
            </div>
	    </form>
	</div>
	<div id="loan-buttons">
	    <a href="#" class="easyui-linkbutton" iconCls="icon-ok" id="saveLoan">确定</a>
	    <a href="#" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#loan').dialog('close')">取消</a>
	</div>
	
	<!-- 是否已进件dialog -->
    <div id="loanInto" class="easyui-dialog" style="width:460px;height:300px;padding:10px 20px"
            closed="true" buttons="#loanInto-buttons">
        <form  id="form-loan-into" class="fm" method="post" action="">
            <input type="hidden" id="intoApplyId" name="loanIntoView.applyId" value=""/>
            <div class="fitem">
                <label>是否已进件:</label>
                    <input class="easyui-validatebox" type="radio" name="loanIntoView.isInto" value="1" validType="requireRadio['#form-loan-into input[name=\'loanIntoView.isInto\']', '是或者否']">是
                    <input class="easyui-validatebox" type="radio" name="loanIntoView.isInto" value="0">终止进件
            </div>
            <div id="intoDateDiv" class="fitem">
                <label>进件日期:</label>
                <input type="text" class="textbox Wdate" size="20" id="intoDate" 
                        name="loanIntoView.intoDate" onfocus="WdatePicker({dateFmt:'yyyy/MM/dd',maxDate:'%y/%M/%d'})" />
            </div>
            <div class="fitem">
                <label>备注:</label>
                <textarea id="intoNote" name="loanIntoView.note" style="width:200px;height:100px;" maxlength="128"></textarea>
            </div>
            
        </form>
    </div>
    <div id="loanInto-buttons">
        <a href="#" class="easyui-linkbutton" iconCls="icon-ok" id="saveLoanInto">确定</a>
        <a href="#" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#loanInto').dialog('close')">取消</a>
    </div>
    
    
    <!-- 是否审批已通过dialog -->
    <div id="loanExamine" class="easyui-dialog" style="width:460px;height:300px;padding:10px 20px"
            closed="true" buttons="#loanInto-buttons">
        <form  id="form-loan-examine" class="fm" method="post" action="">
            <input type="hidden" id="examineApplyId" name="loanExamineView.applyId" value=""/>
            <div class="fitem">
                <label>审批是否通过:</label>
                    <input class="easyui-validatebox" type="radio" name="loanExamineView.isLoanSuccess" value="1" validType="requireRadio['#form-loan-examine input[name=\'loanExamineView.isLoanSuccess\']', '是或者否']">是
                    <input class="easyui-validatebox" type="radio" name="loanExamineView.isLoanSuccess" value="0">否
            </div>
            <div id="arrivalDateDiv" class="fitem">
                <label>审批通过时间:</label>
                <input type="text" class="textbox Wdate" size="20" id="arrivalDate" 
                        name="loanExamineView.arrivalDate" onfocus="WdatePicker({dateFmt:'yyyy/MM/dd',maxDate:'%y/%M/%d'})" />
            </div>
            <div class="fitem">
                <label>备注:</label>
                <textarea id="examineNote" name="loanExamineView.note" style="width:200px;height:100px;" maxlength="128"></textarea>
            </div>
            
        </form>
    </div>
    <div id="loanInto-buttons">
        <a href="#" class="easyui-linkbutton" iconCls="icon-ok" id="saveLoanExamine">确定</a>
        <a href="#" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#loanExamine').dialog('close')">取消</a>
    </div>
    
	
<style>
      .tooltip{margin-left:100px;} 
</style>
<script type="text/javascript">
	$.extend($.fn.validatebox.defaults.rules, {
	    requireRadio: {  
	        validator: function(value, param){  
	            var input = $(param[0]);
	            input.off('.requireRadio').on('click.requireRadio',function(){
	                $(this).focus();
	            });
	            return $(param[0] + ':checked').val() != undefined;
	        },  
	        message: '请选择{1}.'  
	    }  
	});
	
	$.extend($.fn.validatebox.methods, {  
	    remove: function(jq, newposition){    
	        return jq.each(function(){    
	            $(this).removeClass("validatebox-text validatebox-invalid").unbind('focus').unbind('blur');  
	        });
	    },  
	    reduce: function(jq, newposition){    
	        return jq.each(function(){    
	           var opt = $(this).data().validatebox.options;  
	           $(this).addClass("validatebox-text").validatebox(opt);  
	        });    
	    }     
	}); 
    
	//显示备注详情dialog
	function viewLoan(which){
		var applyId=$(which).attr("applyId");
		$.layer({
            type: 2,
            title: '备注详情',
            iframe: {src: '${ctx}/admin/userLoan.do?method=detailLoanPage&applyId=' + applyId},
            area: ['500px', '550px'],
            offset: ['100px', '']
        });
	}

	//添加贷款意向dialog
	function addLoan(which){
	    $('#loan').dialog('open').dialog('setTitle','是否有贷款意向');
	    $('#form-loan-intention').form('clear');
	    $("#applyId").val($(which).attr("applyId"));
	}
	
	// “是否有贷款意向”选择是的时候要填顾客类型
	$(":radio[name='loanIntentionView.isLoan']").click(function(){
	    var index = $(":radio[name='loanIntentionView.isLoan']").index($(this));
	    if(index == 0) {
	    	//选中第1个时，div显示
            $("#typeDiv").show();
            $('#customerType').validatebox('reduce');
            $("#note").removeClass("required");
	    } else {
	    	//当被选中的不是第1个时，div隐藏,”备注“项必填
            $("#typeDiv").hide();
            $('#customerType').validatebox('remove');
            $("#note").addClass("required");
	    }
	});
	
	// 校验表单并保存贷款意向
    $("#saveLoan").click(function(){
        //校验并提交表单
        var flag = $("#form-loan-intention").form('validate');
        var intentionType=$('input[name="loanIntentionView.isLoan"]:checked ').val(); 
        if(intentionType==0){
        	if($("#note").val()=="" || $("#note").val()==null){
                $.messager.alert('提示',"备注不能为空！");
                return;
            }
        }
        if(!flag) {
            return;
        }
        $.ajax({
            type: "post",
            url: '${ctx}/admin/userLoan.do?method=saveLoan&'+Math.random(),
            data : $("#form-loan-intention").serializeArray(),
            success: function(data){
                $.messager.alert('提醒',data.msg, 'info', function () {
                    $('#loan').dialog('close');
                    $('#userInfoGrid').datagrid('reload');
                 });
                
            }
        });

     });
	
    //添加贷款进件dialog
    function addLoanInto(which){
        $('#loanInto').dialog('open').dialog('setTitle','是否已进件');
        $('#form-loan-into').form('clear');
        $("#intoApplyId").val($(which).attr("intoApplyId"));
    }
    
    // “是否已进件”选择“是”的时候要填进件日期，选择“否”的时候要填写备注
    $(":radio[name='loanIntoView.isInto']").click(function(){
        var index = $(":radio[name='loanIntoView.isInto']").index($(this));
        if(index == 0) {
//         	$("#intoDateDiv").show();
            $("#intoNote").removeClass("required");
            $("#intoDate").addClass("required");
        } else {
//         	$("#intoDateDiv").hide();
            $("#intoNote").addClass("required");
            $("#intoDate").removeClass("required");
        }
    });
    
    // 校验表单并保存贷款进件
    $("#saveLoanInto").click(function(){
        //校验并提交表单
        var flag = $("#form-loan-into").form('validate');
        var intoType=$('input[name="loanIntoView.isInto"]:checked ').val(); 
        if(intoType==0){
            if($("#intoNote").val()=="" || $("#intoNote").val()==null){
                $.messager.alert('提示',"备注不能为空！");
                return;
            }
        } 
        if(intoType==1){
        	if($("#intoDate").val()=="" || $("#intoDate").val()==null){
                $.messager.alert('提示',"进件日期不能为空！");
                return;
            }else{
            	var intoDate = new Date($("#intoDate").val()).pattern("yyyy/MM/dd");
                $("#intoDate").val(intoDate);
            }
        }
        
        if(!flag) {
            return;
        }
        var data;
        if(intoType==1){
        	data = {
        			"loanIntoView.applyId" :$("#intoApplyId").val(),
        			"loanIntoView.isInto" :1,
        			"loanIntoView.intoDate" :$("#intoDate").val(),
        			"loanIntoView.note" :$("#intoNote").val()
        	}
        }else{
        	data = {
                    "loanIntoView.applyId" :$("#intoApplyId").val(),
                    "loanIntoView.isInto" :0,
                    "loanIntoView.note" :$("#intoNote").val()
            }
        }
        $.ajax({
            type: "post",
            url: '${ctx}/admin/userLoan.do?method=saveLoanInto',
            data : data,
            success: function(data){
                $.messager.alert('提醒',data.msg, 'info', function () {
                    $('#loanInto').dialog('close');
                    $('#userInfoGrid').datagrid('reload');
                 });
                
            }
        });

     });
    
    //添加贷款审批是否通过dialog
    function addLoanExamine(which){
        $('#loanExamine').dialog('open').dialog('setTitle','审批是否通过');
        $('#form-loan-examine').form('clear');
        $("#examineApplyId").val($(which).attr("examineApplyId"));
    }
    
    // “放款是否成功”选择“是”的时候要填到账日期，选择“否”的时候要填写备注
    $(":radio[name='loanExamineView.isLoanSuccess']").click(function(){
        var index = $(":radio[name='loanExamineView.isLoanSuccess']").index($(this));
        if(index == 0) {
            $("#examineNote").removeClass("required");
            $("#arrivalDate").addClass("required");
        } else {
            $("#examineNote").addClass("required");
            $("#arrivalDate").removeClass("required");
        }
    });
    
    // 校验表单并保存贷款审批
    $("#saveLoanExamine").click(function(){
        //校验并提交表单
        var flag = $("#form-loan-examine").form('validate');
        var intoType=$('input[name="loanExamineView.isLoanSuccess"]:checked ').val(); 
        if(intoType==0){
            if($("#examineNote").val()=="" || $("#examineNote").val()==null){
                $.messager.alert('提示',"备注不能为空！");
                return;
            }
        }
        if(intoType==1){
            if($("#arrivalDate").val()=="" || $("#arrivalDate").val()==null){
                $.messager.alert('提示',"审批通过日期不能为空！");
                return;
            }
        }
        if(!flag) {
            return;
        }
        var data;
        if(intoType==1){
            data = {
                    "loanExamineView.applyId" :$("#examineApplyId").val(),
                    "loanExamineView.isLoanSuccess" :1,
                    "loanExamineView.arrivalDate" :$("#arrivalDate").val(),
                    "loanExamineView.note" :$("#examineNote").val()
            }
        }else{
            data = {
                    "loanExamineView.applyId" :$("#examineApplyId").val(),
                    "loanExamineView.isLoanSuccess" :0,
                    "loanExamineView.note" :$("#examineNote").val()
            }
        }
        $.ajax({
            type: "post",
            url: '${ctx}/admin/userLoan.do?method=saveLoanExamine&'+Math.random(),
            data : data,
            success: function(data){
                $.messager.alert('提醒',data.msg, 'info', function () {
                    $('#loanExamine').dialog('close');
                    $('#userInfoGrid').datagrid('reload');
                 });
                
            }
        });

     });

	//获取银行
	$('#qbankId').combobox({
	    url : '${ctx}/admin/product.do?method=getAllBank&hasBankPay=false',
	    valueField : 'id',
	    textField : 'name',
	    value:'',
	    editable: false,
	    panelHeight:'auto'
	});
	

    //导出Excel
	$("#btnOut").click(function(){
	    $.messager.confirm("提示", "确定导出所有信息吗?",function(r){
	        if(r){
	            $(".shieldBg").show();
	            var queryParam =  "";
	            if($dp.$('qStartTime').value!=null){
	            	var start = $dp.$('qStartTime').value;
	            	queryParam+="&qStartTime="+start;
	            }
	            if($dp.$('qEndTime').value!=null){
                    var end = $dp.$('qEndTime').value;
                    queryParam+="&qEndTime="+end;
                }
	            if($('#phone').val()!=""){
                    var phone = $('#phone').val();
                    queryParam+="&phone="+phone;
                }
                var bankId=$("#qbankId").combobox('getValue');
                if(bankId!="-1"&&bankId!=null){
                    queryParam+="&bankId="+bankId;
                }
                var dataSource = $("#dataSource").combobox('getValue');
                if(dataSource!="-1"&&dataSource!=null){
                    queryParam+="&dataSource="+dataSource;
                }
                var isLoan = $("#isLoan").combobox('getValue');
                if(isLoan!=""&&isLoan!=null){
                    queryParam+="&isLoan="+isLoan;
                }
                var isInto = $("#isInto").combobox('getValue');
                if(isInto!=""&&isInto!=null){
                    queryParam+="&isInto="+isInto;
                }
                var isLoanSuccess = $("#isLoanSuccess").combobox('getValue');
                if(isLoanSuccess!=""&&isLoanSuccess!=null){
                    queryParam+="&isLoanSuccess="+isLoanSuccess;
                }
                var qCustomerType = $("#qCustomerType").combobox('getValue');
                if(qCustomerType!="-1"&&qCustomerType!=null){
                    queryParam+="&qCustomerType="+qCustomerType;
                }
	            location.href="${ctx}/admin/userLoan.do?method=exportSpecialLoan" + queryParam;
	            $(".shieldBg").hide();

	        }
	    });
	});

   //查询
   $('#query').bind('click', function() {
       var params = $("#userInfoGrid").datagrid(
       'options').queryParams;
        var fields = $("#query-form").serializeArray();
        $.each(fields, function(i, field) {
           params[field.name] = field.value;
        });
        
        $("#userInfoGrid").datagrid('load', params);
        
   });
   
   $(document).ready(function() {
       $('#userInfoGrid').datagrid({
           idField : 'applyId',
           url : '${ctx}/admin/userLoan.do?method=querySpecialLoan',
           singleSelect : false,
           rownumbers : false,
           nowrap: true,
           fitColumns : false,
           pagination : true,
           pageSize : 10,
           pageList : [ 10, 20, 30, 40, 50 ],
           checkOnSelect : false,
           selectOnCheck : false,
           columns : [ [
                   {
                       field : 'applyTime',
                       title : '申请时间',
                       width : 230,
                       align : 'center'
                   },
                   {
                       field : 'userName',
                       title : '客户姓名',
                        width : 150,
                       align : 'center'
                   },
                   {
                       field : 'phone',
                       title : '手机号码',
                        width : 160,
                       align : 'center'
                   },
                   {
                       field : 'idNumber',
                       title : '身份证号',
                       width : 280,
                       align : 'center'
                   },
                   {
                       field : 'storeName',
                       title : '商场名称',
                        width : 220,
                       align : 'center'
                   },
                   {
                       field : 'bankName',
                       title : '申请贷款银行',
                        width : 200,
                       align : 'center'
                   },
                   {
                       field : 'amount',
                       title : '申请贷款金额(元)',
                        width : 200,
                       align : 'center'
                   },
                   {
                       field : 'period',
                       title : '申请贷款期数',
                        width : 200,
                       align : 'center'
                   },
                   {
                       field : 'oper1',
                       title : '是否有意向贷款',
                       width : 200,
                       align : 'center',
                       formatter:function(val,row){
                    	   if(row.isLoan != null){
                    		   if(row.isLoan){
                                   return "是";
                               }else{
                                   return "否";
                               }
                    	   } else {
//                                var html="<a href='javascript:;' applyId='"+row.applyId+"' class='btn yellow' onclick='addLoan(this)'>操作</a>";
                            var html="<shiro:hasPermission name='jjd.special.info:loan'><a href='javascript:;' applyId='"+row.applyId+"' class='btn yellow' onclick='addLoan(this)'>操作</a></shiro:hasPermission>";
                               return html;
                    	   }

                       }
                   },
                   {
                       field : 'oper2',
                       title : '是否已进件',
                       width : 180,
                       align : 'center',
                       formatter:function(val,row){
//                     	   if(row.isLoan == null){
//                     		   return "/"; 
//                     	   }
                    	   if(row.isLoan!=null && (row.isLoan)){
                               if(row.isInto!=null){
                                   if(row.isInto){
                                       return "是";
                                   }else{
                                       return "否";
                                   }
                               } else {
//                                    var html="<a href='javascript:;' intoApplyId='"+row.applyId+"' class='btn yellow' onclick='addLoanInto(this)'>操作</a>";
                                var html="<shiro:hasPermission name='jjd.special.info:into'><a href='javascript:;' intoApplyId='"+row.applyId+"' class='btn yellow' onclick='addLoanInto(this)'>操作</a></shiro:hasPermission>";
                                   return html;
                               }
                    	   }else{
                    		   return "/";
                    	   }


                       }

                   },
                   {
                       field : 'oper3',
                       title : '审批是否通过',
                       width : 180,
                       align : 'center',
                       formatter:function(val,row){
//                     	   if(row.isLoan == null || row.isInto == null){
//                     		   return "/";
//                            }
                    	   if(row.isLoan && (row.isInto)){
                    		   if(row.isLoanSuccess!=null){
                    			   if(row.isLoanSuccess){
                                       return "是";
                                   }else{
                                       return "否";
                                   }
                               }else{
//                             	   var html="<a href='javascript:;' examineApplyId='"+row.applyId+"' class='btn yellow' onclick='addLoanExamine(this)'>操作</a>";
                                var html="<shiro:hasPermission name='jjd.special.info:examine'><a href='javascript:;' examineApplyId='"+row.applyId+"' class='btn yellow' onclick='addLoanExamine(this)'>操作</a></shiro:hasPermission>";
                                   return html;
                               }
                    	   } else {
                    		   return "/";
                    	   }
                       }
                   },
                   {
                       field : 'dataSource',
                       title : '渠道',
                       width : 100,
                       align : 'center',
                       formatter : function(value, row, index) {
                           if(row.dataSource == ""){
                               return "APP";
                           }else if(row.dataSource == "2"){
                               return "微信"; 
                           }else if(row.dataSource == "3"){
                               return "H5"; 
                           }else if(row.dataSource == "4"){
                                   return "PC"; 
                           }else if(row.dataSource == "5"){
                               return "商场录入"; 
                           }
                       }
                   },
                   {
                       field : 'sourceFrom',
                       title : '来源',
                       width : 80,
                       align : 'center',
                       formatter : function(value, row, index) {
                           if(row.sourceFrom == "0"){
                               return "红星";
                           }else if(row.sourceFrom == "1"){
                               return "农行"; 
                           }
                       }
                   },
                   {
                       field : 'operatorName',
                       title : '录入人',
                       width : 100,
                       align : 'center'
                       
                   },
                   {
                       field : 'operatorTimeStr',
                       title : '录入时间',
                       width : 230,
                       align : 'center'                       
                   },
                   {
                       field : 'oper4',
                       title : '备注',
                       width : 100,
                       align : 'center',
                       formatter : function(value, row, index) {
                           var html="<a href='#' applyId='"+row.applyId+"' class='btn yellow' onclick='viewLoan(this)'>详情</a>";
//                         var html="<shiro:hasPermission name='jjd.special.info:detail'><a href='javascript:;' applyId='"+row.applyId+"' class='btn yellow' onclick='viewLoan(this)'>详情</a></shiro:hasPermission>";
                           return html;
                       }
                   }
              ] ]
       });
       
       //商场录入
       $("#add_storeloan").click(function() {
           $.layer({
               type: 2,
               shadeClose: true,
               title: "商场录入",
               closeBtn: [0, true],
               shade: [0.8, '#000'],
               border: [0],
               offset: ['20px',''],
               area: ['450px', '580px'],
               iframe: {src: '<c:url value="/admin/userLoan.do?method=addStoreLoanPage"/>'}
           }); 
           
       });
       
       $(".layout-button-left").on('click',function(){
           console.log("22222");
           $('#userInfoGrid').datagrid("resize");
       });
       $(".layout-button-right").on('click',function(){
           console.log("1111");
           $('#userInfoGrid').datagrid("resize");
       });
   });

    </script>   


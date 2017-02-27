<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/layouts/taglibs.jsp"%>

   <div id="tbdiv">
        <form id="query-form">
            <div style="padding:5px">  
                <span>订单号:</span>
                <input id="orderNo" name="orderNo" type="text" style="width:110px">
                <span>手机号:</span>
                <input id="phone" name="phone" type="text" style="width:110px">
                <span>申请开始时间:</span>
                <input type="text" class="textbox Wdate" size="20" id="qStartTime" 
                name="qStartTime" onfocus="WdatePicker({maxDate:'#F{$dp.$D(\'qEndTime\')}'})" value="${qStartTime }" style="width:120px"/>
                <span>申请结束时间:</span>
                <input type="text" class="textbox Wdate" size="20" id="qEndTime" 
                    name="qEndTime" onfocus="WdatePicker({minDate:'#F{$dp.$D(\'qStartTime\')}'})" value="${qEndTime }" style="width:120px"/>
                <span>银行:</span>
                <select class="easyui-combobox" id="bankId" name="bankId" style="width:100px"></select>
                <span>贷款是否成功:</span>
                <select class="easyui-combobox" id="bankPayResult" name="bankPayResult" style="width:60px;" data-options="editable:false,panelHeight:'auto'">
                        <option value="">全部</option>
                        <option value="1">是</option>
                        <option value="0">否</option>
                </select>
                <span>渠道:</span>
                <select class="easyui-combobox" id="dataSource" name="dataSource" style="width:80px;" data-options="editable:false,panelHeight:'auto'">
                        <option value="-1">全部</option>
                        <option value="">APP</option>
                        <option value="2">微信</option>
                </select>
                <shiro:hasPermission name="jjd.online.info:view">
                    <a id="query" class="easyui-linkbutton" iconCls="icon-search">查询</a>
                </shiro:hasPermission>
                <shiro:hasPermission name="jjd.online.info:export">
                    <a iconcls="icon-print" class="easyui-linkbutton"  id="btnOut">导出</a>
                </shiro:hasPermission>
            </div> 
        </form>
    </div>
<div class="shieldBg none"></div>
<table id="userInfoGrid">
</table>
    
<script type="text/javascript">

    // 获取银行
    $('#bankId').combobox({
        url : '${ctx}/admin/product.do?method=getAllBank&hasBankPay=true',
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
                var queryParam="";
                if($dp.$('qStartTime').value!=null){
                    var start = $dp.$('qStartTime').value;
                    queryParam+="&qStartTime="+start;
                }
                if($dp.$('qEndTime').value!=null){
                    var end = $dp.$('qEndTime').value;
                    queryParam+="&qEndTime="+end;
                }
                var bankPayResult=$("#bankPayResult").combobox('getValue');
                if(bankPayResult!=""){
                    queryParam+="&bankPayResult="+bankPayResult;
                }
                if($('#phone').val()!=""){
                    var phone = $('#phone').val();
                    queryParam+="&phone="+phone;
                }
                if($('#orderNo').val()!=""){
                    var orderNo = $('#orderNo').val();
                    queryParam+="&orderNo="+orderNo;
                }
                var bankId=$("#bankId").combobox('getValue');
                if(bankId!="-1"&&bankId!=null){
                    queryParam+="&bankId="+bankId;
                }
                var dataSource = $("#dataSource").combobox('getValue');
                if(dataSource!="-1"&&dataSource!=null){
                    queryParam+="&dataSource="+dataSource;
                }
                location.href="${ctx}/admin/userLoan.do?method=exportOnline" + queryParam;
                $(".shieldBg").hide();
            }
        });
    });

   //查询
   $('#query').bind('click', function() {
       var params = $("#userInfoGrid").datagrid('options').queryParams;
       var fields = $("#query-form").serializeArray();
       $.each(fields, function(i, field) {
           params[field.name] = field.value;
       });
       $("#userInfoGrid").datagrid('load', params);
        
   });
   
   $(document).ready(function() {
       $('#userInfoGrid').datagrid({
           idField : 'userId',
           url : '${ctx}/admin/userLoan.do?method=queryOnline',
           singleSelect : false,
           rownumbers : false,
           nowrap: true,
           fitColumns : true,
           pagination : true,
           pageSize : 15,
           pageList : [ 15, 25, 35, 45, 55 ],
           checkOnSelect : false,
           selectOnCheck : false,
           columns : [ [
                   {
                       field : 'orderNo',
                       title : '订单号',
                        width : 160,
                       align : 'center'
                   },
                   {
                       field : 'applyTime',
                       title : '申请日期',
                        width : 220,
                       align : 'center'
                   },
                   {
                       field : 'orderTime',
                       title : '交易日期',
                        width : 220,
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
                       width : 300,
                       align : 'center'
                   },
                   {
                       field : 'provinceName',
                       title : '省',
                        width : 80,
                       align : 'center'
                   },
                   {
                       field : 'cityName',
                       title : '城市',
                        width : 80,
                       align : 'center'
                   },
                   {
                       field : 'storeName',
                       title : '商场名称',
                        width : 210,
                       align : 'center'
                   },
                   {
                       field : 'bankName',
                       title : '银行名称',
                        width : 100,
                       align : 'center'
                   },
                   {
                       field : 'amount',
                       title : '交易金额(元)',
                        width : 130,
                       align : 'center'
                   },
                   {
                       field : 'period',
                       title : '分期期数',
                        width : 120,
                       align : 'center'
                   },
//                    {
//                        field : 'returnPerMonthFmt',
//                        title : '分期金额(元)',
//                         width : 130,
//                        align : 'center'
//                    },
                   {
                       field : 'bankPayResult',
                       title : '贷款是否成功',
                        width : 140,
                       align : 'center',
                       formatter : function(value, row, index) {
                           if(row.bankPayResult == "1"){
                               return "是";
                           }else {
                               return "否";
                           }
                       }
                   },
//                    {
//                        field : 'openId',
//                        title : 'openID',
//                        width : 280,
//                        align : 'center'
//                    },
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
                           }else
                        	   return "PC";
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
                   }
              ] ]
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


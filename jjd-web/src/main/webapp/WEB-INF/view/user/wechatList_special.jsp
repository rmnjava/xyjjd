<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/layouts/taglibs.jsp"%>

   <div id="tbdiv">
        <form id="query-form">
            <table style="color:#000066;background:  left top repeat-x;background-color:#cbe5f2;">
                <tbody>
                    <tr>
                        <td>手机号:</td>
                        <td>
                            <input id="phone" name="phone" type="text">
                        </td>
                        <td>申请开始时间:</td>
                        <td>
                            <input type="text" class="textbox Wdate" size="20" id="qStartTime" 
                        name="qStartTime" onfocus="WdatePicker({maxDate:'#F{$dp.$D(\'qEndTime\')}'})" value="${qStartTime }" />
                        </td>
                        <td>申请结束时间:</td>
                        <td>
                            <input type="text" class="textbox Wdate" size="20" id="qEndTime" 
                            name="qEndTime" onfocus="WdatePicker({minDate:'#F{$dp.$D(\'qStartTime\')}'})" value="${qEndTime }" />
                        </td>
                        <td>银行:</td>
                        <td>
                            <select class="easyui-combobox" id="bankId" name="bankId" style="width:100px"></select>
                        </td>
                        <td align="right">
                            <shiro:hasPermission name="jjd.wechat.nocard:view">
                                <a id="query" class="easyui-linkbutton" iconCls="icon-search">查询</a>
                            </shiro:hasPermission>
                            <shiro:hasPermission name="jjd.wechat.nocard:export">
                                <a iconcls="icon-print" class="easyui-linkbutton"  id="btnOut">导出</a>
                            </shiro:hasPermission>
                        </td>
                    </tr>
                </tbody>
            </table>
        </form>
    </div>
<div class="shieldBg none"></div>
<table id="userInfoGrid">
</table>
    
<script type="text/javascript">

    // 获取银行
    $('#bankId').combobox({
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
                var queryParam="";
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
                var bankId=$("#bankId").combobox('getValue');
                if(bankId!="-1"&&bankId!=null){
                    queryParam+="&bankId="+bankId;
                }
                location.href="${ctx}/admin/user.do?method=listWechatSpecialExport" + queryParam;
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
           url : '${ctx}/admin/user.do?method=queryWechatSpecial',
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
                       field : 'applyTime',
                       title : '申请日期',
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
                       title : '贷款金额(元)',
                        width : 130,
                       align : 'center'
                   },
                   {
                       field : 'period',
                       title : '分期期数',
                        width : 120,
                       align : 'center'
                   },
                   {
                       field : 'returnPerMonthFmt',
                       title : '分期金额(元)',
                        width : 130,
                       align : 'center'
                   },
                   {
                       field : 'openId',
                       title : 'openID',
                       width : 280,
                       align : 'center'
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
   
   
   /* window.onload=function(){    
       setTimeout(function(){
           $(".datagrid-view").height($(".datagrid-view").height()-27);        
       },100);
   } */
    </script>   


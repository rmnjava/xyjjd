<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/layouts/taglibs.jsp"%>

   <div id="tbdiv">
        <form id="query-form">
	        <table style="color:#000066;background:  left top repeat-x;background-color:#cbe5f2;" width="auto;">
	            <tbody>
	                <tr>
	                    <td>开始时间:</td>
	                    <td>
	                        <input type="text" class="textbox Wdate" size="20" id="qStartTime" 
	                    name="qStartTime" onfocus="WdatePicker({maxDate:'#F{$dp.$D(\'qEndTime\')}'})" value="${qStartTime }" />
	                    </td>
	                    <td>结束时间:</td>
	                    <td>
	                        <input type="text" class="textbox Wdate" size="20" id="qEndTime" 
	                        name="qEndTime" onfocus="WdatePicker({minDate:'#F{$dp.$D(\'qStartTime\')}'})" value="${qEndTime }" />
	                     </td>

                         <td align="right">
                            <shiro:hasPermission name="jjd.app.card:view">
                                <a id="query" class="easyui-linkbutton" iconCls="icon-search">查询</a>
                            </shiro:hasPermission>
                            <shiro:hasPermission name="jjd.app.card:export">
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
	            location.href="${ctx}/admin/user.do?method=listExport1" + queryParam;
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
           idField : 'userId',
           url : '${ctx}/admin/user.do?method=query',
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
                       width : 300,
                       align : 'center'
                   },
                   {
                       field : 'applyTime',
                       title : '订单日期',
                       width : 200,
                       align : 'center'
                   },
                   {
                       field : 'orderTime',
                       title : '交易日期',
                        width : 200,
                       align : 'center'
                   },
                   {
                       field : 'userName',
                       title : '客户姓名',
                       width : 200,
                       align : 'center'
                   },
                   {
                       field : 'phone',
                       title : '手机号码',
                       width : 200,
                       align : 'center'
                   },
                   {
                       field : 'idNumber',
                       title : '身份证号',
                       width : 300,
                       align : 'center'
                   },
                   {
                       field : 'storeName',
                       title : '商场名称',
                       width : 300,
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
                       width : 200,
                       align : 'center'
                   },
                   {
                       field : 'period',
                       title : '分期期数',
                       width : 200,
                       align : 'center'
                   },
                   {
                       field : 'returnPerMonthFmt',
                       title : '分期金额(元)',
                       width : 200,
                       align : 'center'
                   }

              ] ]
       });
   });
   /* window.onload=function(){    
       setTimeout(function(){
           $(".datagrid-view").height($(".datagrid-view").height()-27);        
       },100);
   } */
    </script>   


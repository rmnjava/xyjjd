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
	                       <shiro:hasPermission name="jjd.app.nocard:view">
	                           <a id="query" class="easyui-linkbutton" iconCls="icon-search">查询</a>
	                       </shiro:hasPermission>
                           <shiro:hasPermission name="jjd.app.nocard:export">
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
               location.href="${ctx}/admin/user.do?method=listExport4" + queryParam;
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
	   $('#userInfoGrid').datagrid(
	           {
	               idField : 'userId',
	               url : '${ctx}/admin/user.do?method=query2',
	               singleSelect : false,
	               rownumbers : false,
	               fit : false,
	               fitColumns : true,
	               pagination : true,
	               pageSize : 15,
	               pageList : [ 15, 25, 35, 45, 55 ],
	               checkOnSelect : false,
	               selectOnCheck : false,
	               columns : [ [
							{
							    field : 'applyDateFmt',
							    title : '申请日期',
							    width : 190,
							    align : 'center'
							},
	                       {
	                           field : 'userInfo.name',
	                           title : '客户姓名',
	                           width : 180,
	                           align : 'center',
	                           formatter:function(value,row,index){
	                        	   return row.userInfo.name;
	                           }
	                       },
	                       {
	                           field : 'phone',
	                           title : '手机号码',
	                           width : 180,
	                           align : 'center'
	                       },
	                       {
	                           field : 'userInfo.idNumber',
	                           title : '身份证号',
	                           width : 290,
	                           align : 'center',
	                           formatter:function(value,row,index){
                                   return row.userInfo.idNumber;
                               }
	                       },
	                       {
	                           field : 'storeName',
	                           title : '商场名称',
	                           width : 280,
	                           align : 'center'
	                       },
                           {
                               field : 'bankName',
                               title : '银行',
                               width : 110,
                               align : 'center'
                           },
                           {
                               field : 'isZero',
                               title : '是否零成本',
                               width : 100,
                               align : 'center',
                               formatter:function(value,row,index){
                                   if(row.isZero){
                                       return "是";
                                   }else{
                                       return "否";
                                   }
                               }
                           },
	                       {
	                           field : 'amount',
	                           title : '申请金额(元)',
	                           width : 200,
	                           align : 'center'
	                       },
                           {
                               field : 'period',
                               title : '分期期数',
                               width : 80,
                               align : 'center'
                           },
                           {
                               field : 'returnPerMonthFmt',
                               title : '分期金额(元)',
                               width : 100,
                               align : 'center'
                           },
                           {
                               field : 'userInfo.income',
                               title : '月薪',
                               width : 100,
                               align : 'center',
                               formatter:function(value,row,index){
                            	   return row.userInfo.income;
                               }
                           },
                           {
                               field : 'userInfo.hasSocialSec',
                               title : '是否有本地社保',
                               width : 130,
                               align : 'center',
                               formatter:function(value,row,index){
                                   if(row.userInfo.hasSocialSec){
                                       return "是";
                                   }else{
                                       return "否";
                                   }
                               }
                           },
                           {
                               field : 'userInfo.hasAccumulation',
                               title : '是否有公积金',
                               width : 120,
                               align : 'center',
                               formatter:function(value,row,index){
                                   if(row.userInfo.hasAccumulation){
                                       return "是";
                                   }else{
                                       return "否";
                                   }
                               }
                           },
                           {
                               field : 'userInfo.hasHouse',
                               title : '是否有房产',
                               width : 120,
                               align : 'center',
                               formatter:function(value,row,index){
                                   if(row.userInfo.hasHouse){
                                       return "是";
                                   }else{
                                       return "否";
                                   }
                               }
                           },
                           {
                               field : 'userInfo.hasCar',
                               title : '是否有车',
                               width : 100,
                               align : 'center',
                               formatter:function(value,row,index){
                                   if(row.userInfo.hasCar){
                                       return "是";
                                   }else{
                                       return "否";
                                   }
                               }
                           },
                           {
                               field : 'userInfo.hasCreditCard',
                               title : '是否有信用卡',
                               width : 120,
                               align : 'center',
                               formatter:function(value,row,index){
                                   if(row.userInfo.hasCreditCard){
                                       return "是";
                                   }else{
                                       return "否";
                                   }
                               }
                           }
	                       ] ]
	        });
   });
	
    </script>


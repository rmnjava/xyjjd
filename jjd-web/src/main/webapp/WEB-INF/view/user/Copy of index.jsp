<%@ page language="java" contentType="text/html;charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/jquery-ui.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/js/easyui/1.3.2/themes/icon.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/js/easyui/1.3.2/themes/default/easyui.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/easyui/1.3.2/jquery.easyui.min.js"></script>

<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/datepicker/WdatePicker.js"></script>
<style>
    	html, body{ width: 100%; height: 100%; padding: 0; margin: 0;}
</style>

<title>家居贷管理后台</title>
</head>
<body>
   <div id="tbdiv">
        <form id="query-form">
	        <table style="color:#000066;background:  left top repeat-x;background-color:#cbe5f2;" width="100%" align="center">
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
	                    <input id="query" type="button" value="查询"/>
	                    </td>
	                </tr>
	            </tbody>
	        </table>
        </form>
    </div>
    <table id="userInfoGrid">
        </table>
    
   <script type="text/javascript">

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
	               url : '${pageContext.request.contextPath}/admin/user/query',
	               singleSelect : false,
	               rownumbers : false,
	               fit : true,
	               fitColumns : true,
	               pagination : false,
	               checkOnSelect : false,
	               selectOnCheck : false,
	               columns : [ [
	                       {
	                           field : 'orderNo',
	                           title : '订单号',
	                           width : 300,
	                           align : 'center',
	                           sortable : true
	                       },
	                       {
	                           field : 'applyTime',
	                           title : '订单日期',
	                           width : 200,
	                           align : 'center'
	                       },
	                       {
	                           field : 'userName',
	                           title : '客户姓名',
	                           width : 200,
	                           align : 'center',
	                           sortable : true
	                       },
	                       {
	                           field : 'phone',
	                           title : '手机号码',
	                           width : 200,
	                           sortable : true,
	                           align : 'center'
	                       },
	                       {
	                           field : 'idNumber',
	                           title : '身份证号',
	                           width : 300,
	                           sortable : true,
	                           align : 'center'
	                       },
	                       {
	                           field : 'storeName',
	                           title : '商场名称',
	                           width : 300,
	                           sortable : true,
	                           align : 'center'
	                       },
	                       {
	                           field : 'amount',
	                           title : '交易金额',
	                           width : 200,
	                           sortable : true,
	                           align : 'center'
	                       },
                           {
                               field : 'period',
                               title : '分期期数',
                               width : 200,
                               sortable : true,
                               align : 'center'
                           },
                           {
                               field : 'returnPerMonthFmt',
                               title : '分期金额',
                               width : 200,
                               sortable : true,
                               align : 'center'
                           }
	                       ] ]
	        });
   });
	
    </script>
</body>
</html>

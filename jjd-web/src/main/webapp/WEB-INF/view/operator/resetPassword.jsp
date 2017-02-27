<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/layouts/taglibs.jsp"%>
<html>
<head>
    <style>
        label.error, label.error02{left:120px;top:3px;}
    
    </style>

	</head>
	<body>

    <div class="easyui-panel" style="width:auto;">
        <div style="margin:16px 0px 25px 80px;line-height:24px;">
        <form id="ff" method="post">
        <input type="hidden" name='userMD5Pwd' id="userMD5Pwd" value="${userMD5Pwd }"/>
            <table style="width:300px;">
                <tr>
                    <td>旧密码:</td>
                    <td>
                        <div>
                            <input id="password" type="password" name="oldpassword"  maxLength="12"/>
                        </div>
                    </td>
                </tr>
            	<tr>
                    <td>新密码:</td>
                    <td>
                        <div>
                    	   <input id="pwd" type="password" name="password" maxLength="12"/>
                   	   </div>
                    </td>
                </tr>
                <tr>
                    <td>确认新密码:</td>
                    <td>
                        <div>
                            <input id="rpwd" type="password" name="rpwd" maxLength="12"/>
                        </div>
                    </td>
                </tr>
                <tr>
                	<td colspan="2" style="text-align:center;"> 
	                	<a href="javascript:void(0)" class="easyui-linkbutton" onclick="submitForm()">提交</a>
						<a href="javascript:void(0)" class="easyui-linkbutton" onclick="clearForm()">重置</a>
					</td>
                </tr>
            </table>
        </form>
        </div>

    </div>
</body>
<script type="text/javascript">

var submitForm = function() {
    if($("#ff").valid()){
        $.ajax({
            type: "post",
            url: '${ctx}/admin/operator.do?method=resetPwd&'+Math.random(),
            data : $("#ff").serializeArray(),
            success: function(data){
                $.messager.alert('提醒',data.msg, 'info', function () {
                    window.location.reload();
                 });
                
            }
        });
    }
};

function clearForm(){  
    $('#ff').form('clear');
}

$(function(){
    $("#ff").validate({
        rules:{
        	oldpassword:{
                required:true
            },
            password:{
                required:true,
                rangelength:[6,6]
            },
            rpwd:{
                required:true,
                equalTo:"#pwd"
            }
        },
        messages:{
        	oldpassword:{
                required:"请输入密码！"
            },
            password:{
                required:"请输入密码！",
                rangelength:"新密码必须是6位英文或数字！"
            },
            rpwd:{
                required:"请输入确认新密码！",
                equalTo:"两次新密码不一致！"
            }
        }
    });
    
    
    
    
});


</script>
</html>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/layouts/taglibs.jsp"%>
<html>
<head>
<style>
	body,a{ font-size:14px; color:#666;}
	ul,li{ margin:0; padding:0; list-style:none}
	.fn-vhid{ visibility:hidden;}
	.fn-tinput{ width:126px; height:20px; line-height:30px\9; border:#ddd solid 1px; background-color:transparent; padding:0 6px;}
	textarea {border:#ddd solid 1px; background-color:transparent; width:60%}
	.fn-tinput:focus,textarea:focus{ border-color:#c00;}
	.bl-form label{ float:left; width:100px; text-align:right; padding-top:6px;}
	.bl-form .controls label{ width:auto;}
	.bl-form li{ margin-top:10px; overflow:hidden;}
	em{ color:#C00;}
	span.error{color:#C00; padding:0 6px;}
</style>
<script type="text/javascript">
	//获取银行
// 	$.ajax({
// 	    type:'post',
// 	    url:"${ctx}/admin/product.do?method=getAllBank&hasBankPay=-1",
// 	    success:function(msg){
// 	        var options="";
// 	        for(var i=0;i<msg.length;i++){
// 	            options+="<option value='"+msg[i]["id"]+"'>"+msg[i]["name"]+"</option>";
// 	        }
// 	        $('#bankId').append(options);
// 	    },
// 	    error:function(msg){
// 	    }
// 	});
	
	//配置错误提示的节点，默认为label，这里配置成 span （errorElement:'span'）
	$.validator.setDefaults({
	    errorElement:'span'
	});
	
	//配置通用的默认提示语
	$.extend($.validator.messages, {
	    required: '必填',
	    equalTo: "请再次输入相同的值"
	});
	
	/*-------------扩展验证规则 懒人建站http://www.51xuediannao.com/-------------*/
	//邮箱 
	jQuery.validator.addMethod("mail", function (value, element) {
	    var mail = /^[a-z0-9._%-]+@([a-z0-9-]+\.)+[a-z]{2,4}$/;
	    return this.optional(element) || (mail.test(value));
	}, "邮箱格式不对");
	
	//电话验证规则
	jQuery.validator.addMethod("phone", function (value, element) {
	    var phone = /^0\d{2,3}-\d{7,8}$/;
	    return this.optional(element) || (phone.test(value));
	}, "电话格式如：0371-68787027");
	
	//区号验证规则  
	jQuery.validator.addMethod("ac", function (value, element) {
	    var ac = /^0\d{2,3}$/;
	    return this.optional(element) || (ac.test(value));
	}, "区号如：010或0371");
	
	//无区号电话验证规则  
	jQuery.validator.addMethod("noactel", function (value, element) {
	    var noactel = /^\d{7,8}$/;
	    return this.optional(element) || (noactel.test(value));
	}, "电话格式如：68787027");
	
	//手机验证规则  
	jQuery.validator.addMethod("mobile", function (value, element) {
	    var mobile = /^1[3|4|5|7|8]\d{9}$/;
	    return this.optional(element) || (mobile.test(value));
	}, "手机格式不对");
	
	//邮箱或手机验证规则  
	jQuery.validator.addMethod("mm", function (value, element) {
	    var mm = /^[a-z0-9._%-]+@([a-z0-9-]+\.)+[a-z]{2,4}$|^1[3|4|5|7|8]\d{9}$/;
	    return this.optional(element) || (mm.test(value));
	}, "格式不对");
	
	//电话或手机验证规则  
	jQuery.validator.addMethod("tm", function (value, element) {
	    var tm=/(^1[3|4|5|7|8]\d{9}$)|(^\d{3,4}-\d{7,8}$)|(^\d{7,8}$)|(^\d{3,4}-\d{7,8}-\d{1,4}$)|(^\d{7,8}-\d{1,4}$)/;
	    return this.optional(element) || (tm.test(value));
	}, "格式不对");
	
	//年龄
	jQuery.validator.addMethod("age", function(value, element) {   
	    var age = /^(?:[1-9][0-9]?|1[01][0-9]|120)$/;
	    return this.optional(element) || (age.test(value));
	}, "不能超过120岁"); 
	///// 20-60   /^([2-5]\d)|60$/
	
	//传真
	jQuery.validator.addMethod("fax",function(value,element){
	    var fax = /^(\d{3,4})?[-]?\d{7,8}$/;
	    return this.optional(element) || (fax.test(value));
	},"传真格式如：0371-68787027");
	
	//验证当前值和目标val的值相等 相等返回为 false
	jQuery.validator.addMethod("equalTo2",function(value, element){
	    var returnVal = true;
	    var id = $(element).attr("data-rule-equalto2");
	    var targetVal = $(id).val();
	    if(value === targetVal){
	        returnVal = false;
	    }
	    return returnVal;
	},"不能和原始密码相同");
	
	//大于指定数
	jQuery.validator.addMethod("gt",function(value, element){
	    var returnVal = false;
	    var gt = $(element).data("gt");
	    if(value > gt && value != ""){
	        returnVal = true;
	    }
	    return returnVal;
	},"不能小于0 或空");
	
	//汉字
	jQuery.validator.addMethod("chinese", function (value, element) {
	    var chinese = /^[\u4E00-\u9FFF]+$/;
	    return this.optional(element) || (chinese.test(value));
	}, "格式不对");
	
	//指定数字的整数倍
	jQuery.validator.addMethod("times", function (value, element) {
	    var returnVal = true;
	    var base=$(element).attr('data-rule-times');
	    if(value%base!=0){
	        returnVal=false;
	    }
	    return returnVal;
	}, "必须是发布赏金的整数倍");
	
	//身份证
	jQuery.validator.addMethod("idCard", function (value, element) {
	    var isIDCard1=/^[1-9]\d{7}((0\d)|(1[0-2]))(([0|1|2]\d)|3[0-1])\d{3}$/;//(15位)
	    var isIDCard2=/^[1-9]\d{5}[1-9]\d{3}((0\d)|(1[0-2]))(([0|1|2]\d)|3[0-1])\d{3}([0-9]|X)$/;//(18位)
	
	    return this.optional(element) || (isIDCard1.test(value)) || (isIDCard2.test(value));
	}, "格式不对");
	
	</script>
</head>
<body>
	<div class="demobox" style="background: #fff;padding: 10px;">
	    
	    <form action="" method="post" class="bl-form bl-formhor" id="special-loan-add">
	        <input type="hidden" id="provinceId" value="${storeView.provinceCode }"/>
	        <input type="hidden" id="provinceName" value="${storeView.provinceName }"/>
	        <input type="hidden" id="cityId" value="${storeView.cityCode }"/>
	        <input type="hidden" id="storeCode" value="${storeId }"/>
	        <ul>
	            <li class="bl-form-group">
	                <label>姓名：</label>
	                <div class="controls">
	                    <input type="text" value="" id="userName" name="userName" class="fn-tinput" required data-msg-required="请输入姓名" maxlength="20"/>
	                </div>
	            </li>
	            <li class="bl-form-group">
	                <label>手机号码：</label>
	                <div class="controls">
	                    <input type="text" value="" id="phone" name="phone" class="fn-tinput" required data-rule-mobile="true" data-msg-required="请输入手机号码" data-msg-mobile="请输入正确格式" maxlength="12"/>
	                </div>
	            </li>
	            <li class="bl-form-group">
	                <label>身份证号码：</label>
	                <div class="controls">
	                    <input type="text" value="" id="idNumber" name="idNumber" class="fn-tinput" data-rule-idCard="true" data-msg-idcard="请输入正确格式" maxlength="18"/>
	                </div>
	            </li>
	            <li class="bl-form-group">
                    <label>省：</label>
                    <div class="controls">
                        <select id="provinceCode" name="provinceCode" required>
                        </select>
                    </div>
                </li>
	            <li class="bl-form-group">
                    <label>城市：</label>
                    <div class="controls">
                        <select id="cityCode" name="cityCode" required>
                        </select>
                    </div>
                </li>
                <li class="bl-form-group">
                    <label>所属商场：</label>
                    <div class="controls">
                        <select id="storeId" name="storeId" required>
                        </select>
                    </div>
                </li>
                
                <li class="bl-form-group">
                    <label>申请贷款银行：</label>
                    <div class="controls">
                        <select id="bankId" name="bankId" required>
                        </select>
                    </div>
                </li>
                <script type="text/javascript">
                
                
                
                </script>
                <li class="bl-form-group">
                    <label>申请贷款金额：</label>
                    <div class="controls">
                        <input type="text" value="" id="amount" name="amount" class="fn-tinput" required data-rule-gt="true" data-gt="0" maxlength="8" />万元
                    </div>
                </li>
                <li class="bl-form-group">
                    <label>申请贷款期数：</label>
                    <div class="controls">
                        <select id="period" name="period" required>
                            <option value="">----请选择----</option>
	                        <option value="6">6</option>
	                        <option value="12">12</option>
	                        <option value="24">24</option>
	                        <option value="36">36</option>
                        </select>
                    </div>
                </li>
                <li class="bl-form-group">
                    <label>申请日期：</label>
                    <div class="controls">
                        <input type="text" value="" id="applyDate" name="applyDate" class="textbox Wdate" style="width:138px;" 
                        onfocus="WdatePicker({dateFmt:'yyyy/MM/dd H:mm:ss',maxDate:'%y/%M/%d %H:%m:%s'})"  required/>
                    </div>
                </li>
	            <li class="bl-form-group">
	                <label>备注信息：</label>
	                <div class="controls editor">
	                    <textarea id="loanNote" name="loanNote" style="width:138px;height:78px;" maxlength="128" ></textarea>
	                </div>
	            </li>
	            <li class="bl-form-group bl-form-btns">
	                <label class="fn-vhid">提交：</label>
	                <div class="controls">
	                    <button class="fn-btn btn-primary btn-submit submitBtn" type="submit">提交</button>
	                    <button type="reset" class="fn-btn" onclick="closeWin()">取消</button>
	                </div>
	            </li>
	        </ul>
	    </form>
	            
	</div>
	
<script type="text/javascript">

    var storeCode=$("#storeCode").val();
	$(function(){
	    if(storeCode!=null&&storeCode!=""){
	        var provinceId=$("#provinceId").val();
	        var provinceName=$("#provinceName").val();
	        var option="<option value='"+provinceId+"'>"+provinceName+"</option>";
            $('#provinceCode').empty().append(option);
	        $("#provinceCode").attr("disabled","disabled");
	        var cityId=$("#cityId").val();
	        $("#cityCode").val(cityId);
	        $("#storeId").val(storeCode);
	        $.ajax({
	            type:'post',
	            url:"${ctx}/admin/store.do?method=getCitysByProvince&provinceCode="+provinceId,
	            success:function(msg){
	                var options="";
	                for(var i=0;i<msg.length;i++){
	                    options+="<option value='"+msg[i]["id"]+"'>"+msg[i]["name"]+"</option>";
	                }
	                $('#cityCode').empty().append(options);
	                $("#cityCode").val(cityId);
	                $("#cityCode").attr("disabled","disabled"); 
	                $.ajax({
	                    type:'post',
	                    async:false,
	                    url:"${ctx}/admin/store.do?method=getStoresByCity&cityCode="+cityId,
	                    success:function(msg){
	                        var options="";
	                        for(var i=0;i<msg.length;i++){
	                            options+="<option value='"+msg[i]["id"]+"'>"+msg[i]["name"]+"</option>";
	                        }
	                        $('#storeId').empty().append(options);
	                        $("#storeId").val(storeCode);
	                        $("#storeId").attr("disabled","disabled");
	                        $.ajax({
                                type:'post',
                                url:"${ctx}/admin/store.do?method=getBanksByStore&storeId="+$("#storeId").val(),
                                success:function(msg){
                                    var options="";
                                    for(var i=0;i<msg.length;i++){
                                        options+="<option value='"+msg[i]["id"]+"'>"+msg[i]["name"]+"</option>";
                                    }
                                    $('#bankId').empty().append(options);
                                    $("#bankId").removeAttr("disabled"); 
                                },
                                error:function(msg){
                                }
                            });
	                    },
	                    error:function(msg){
	                    }
	                });
	            },
	            error:function(msg){
	            }
	        });
	    }else{
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
	                $("#provinceCode").removeAttr("disabled"); 
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
	                    $("#cityCode").removeAttr("disabled"); 
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
// 	                    $("#storeId").removeAttr("disabled"); 
	                },
	                error:function(msg){
	                }
	            });
	        });
	        
	        $('#storeId').change(function(){
                var storeId=$("#storeId").val();
                $.ajax({
                    type:'post',
                    url:"${ctx}/admin/store.do?method=getBanksByStore&storeId="+storeId,
                    success:function(msg){
                        var options="";
                        for(var i=0;i<msg.length;i++){
                            options+="<option value='"+msg[i]["id"]+"'>"+msg[i]["name"]+"</option>";
                        }
                        $('#bankId').empty().append(options);
                        $("#bankId").removeAttr("disabled"); 
                    },
                    error:function(msg){
                    }
                });
            });
	    }
	    
	    //jquery.validate
	    $("#special-loan-add").validate({
	        submitHandler: function() {
	        	$.ajax({
	                type: "post",
	                url: '${ctx}/admin/userLoan.do?method=saveSpecialLoan&'+Math.random(),
	                data : $("#special-loan-add").serializeArray(),
	                success: function(data){
	                    $.messager.alert('提醒',data.msg, 'info', function () {
	                    	parent.$('#userInfoGrid').datagrid('reload');
	                    	closeWin();
	                     });
	                    
	                }
	            });
	        	
	        }
	    })
	        
	})
	
</script>
    
</body>

</html>

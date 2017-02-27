<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/layouts/taglibs.jsp"%>
<div class="easyui-accordion" style="width:100%;height: auto;" data-options="border:false,fit:true">
		<div title="菜单" data-options="iconCls:'icon-search'"
			style="padding: 10px;">
			<ul class="easyui-tree">
<%-- 				<li><a href='<c:url value="/admin/user/search2"/>'>首页</a></li> --%>
                <li><span>申请家居贷信息管理</span>
                    <ul>
                        
                        <li><span>星易家居贷APP</span>
                            <ul>
                            <shiro:hasPermission name="app.nocard:view">
                                <li><a href='<c:url value="/admin/user/search2"/>'>无卡用户申请</a></li>
                            </shiro:hasPermission>
                            <shiro:hasPermission name="app.card:view">
                                <li><a href='<c:url value="/admin/user/search"/>'>农行在线分期</a></li>
                            </shiro:hasPermission>
                            </ul>
                        </li>
                        
                        <li><span>星易家居贷微信</span>
                            <ul>
                            <shiro:hasPermission name="wechat.nocard:view">
                                <li><a href='<c:url value="/admin/user/searchWechatSpecial"/>'>专项贷款申请</a></li>
                            </shiro:hasPermission>
                            <shiro:hasPermission name="wechat.card:view">
                                <li><a href='<c:url value="/admin/user/searchWechat"/>'>农行在线分期</a></li>
                            </shiro:hasPermission>
                            </ul>
                        </li>
                        
                        <shiro:hasPermission name="abc.single:view">
	                        <li><span>农行专项活动</span>
	                            <ul>
	                                <li><a href='<c:url value="/admin/user/searchSingle"/>'>9月金可儿</a></li>
	                            </ul>
	                        </li>
                        </shiro:hasPermission>
                    </ul>
                </li>
<!-- 				<li><span>申请信息管理</span> -->
<!-- 					<ul>    -->
<%-- 					    <shiro:hasPermission name="user:view"> --%>
<%-- 							<li><a href='<c:url value="/admin/user/search"/>'>app成功分期客户</a></li> --%>
<%-- 							<li><a href='<c:url value="/admin/user/search2"/>'>app无卡申请客户</a></li> --%>
<%-- 							<li><a href='<c:url value="/admin/user/searchWechat"/>'>微信分期客户</a></li> --%>
<%-- 							<li><a href='<c:url value="/admin/user/searchSingle"/>'>农行单品成功客户</a></li> --%>
<%-- 					    </shiro:hasPermission> --%>
<!-- 					</ul> -->
<!-- 				</li> -->
<%-- 				<shiro:hasPermission name="role:view"> --%>
                
	                <li><span>系统管理</span>
                        <ul>
                        <shiro:hasPermission name="system.department:view">
                             <li><a href='<c:url value="/admin/dept.do?method=list"/>'>部门管理</a></li>
                        </shiro:hasPermission>
                        <shiro:hasPermission name="system.role:view">
                             <li><a href='<c:url value="/admin/role.do?method=list"/>'>角色管理</a></li>
                        </shiro:hasPermission>
                        <shiro:hasPermission name="system.operator:view">
                             <li><a href='<c:url value="/admin/operator.do?method=list"/>'>账户管理</a></li>
                        </shiro:hasPermission>
                             
                        </ul>
	                </li>
                
	                <li><span>金融机构管理</span>
	                        <ul>
	                        <shiro:hasPermission name="bank:view">
	                            <li><a href='<c:url value="/admin/product.do?method=list"/>'>金融机构管理</a></li>
	                        </shiro:hasPermission>
	                        </ul>
	                </li>
                
                
	                <li><span>商场管理</span>
	                    <ul>
	                        <shiro:hasPermission name="store:view">
	                            <li><a href='<c:url value="/admin/store.do?method=list"/>'>商场管理</a></li>
	                        </shiro:hasPermission>
	                    </ul>
	                </li>
                
<!--                 <li><span>个人中心</span> -->
<!--                         <ul> -->
<%--                             <li><a href='<c:url value="/admin/operator.do?method=list"/>'>修改密码</a></li> --%>
<!--                         </ul> -->
<!--                         <ul> -->
<%--                             <li><a href='<c:url value="/admin/operator.do?method=list"/>'>绑定手机号</a></li> --%>
<!--                         </ul> -->
<!--                         <ul> -->
<%--                             <li><a href='<c:url value="/admin/operator.do?method=list"/>'>找回密码</a></li> --%>
<!--                         </ul> -->
<!--                 </li> -->
<!-- 				<li><span>演示</span> -->
<!-- 					<ul> -->
<!-- 						<li><span>列表</span> -->
<!-- 							<ul> -->
<!-- 								<li><a href=';;'>博客管理</a></li> -->
<!-- 							</ul></li> -->
<!-- 					</ul> -->
<!-- 				</li> -->

                
			</ul>
		</div></div>
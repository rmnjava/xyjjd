<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/layouts/taglibs.jsp"%>
<form:form id="input-form" name="input-form" modelAttribute="role">
	<div id="input-space-1" class="in-ct">
		<input type="hidden" name="roleId"
			value="${role.roleId}" />
		<div class="ctt-tit-ct">
			<div class="ctt-tit">角色信息</div>
		</div>
		<div id="input-space-1-content" class="ctt-ctt-ct">
			<div class="ctt-ctt-col-ct">
			<div class="ctt-ctt-col col-2">
					<div class="ctt-ctt-col-name">角色名:</div>
					<div class="ctt-ctt-col-value">
					${role.roleName}
					</div>
				</div>
				<div class="ctt-ctt-col col-1">
					<div class="ctt-ctt-col-name">权限:</div>
					<div class="ctt-ctt-col-value">
					${role.permissionNames}
					</div>
				</div>
			</div>
		</div>
	</div>
</form:form>
<div id="hdl-ct">
	<div id="hdl">
		<!-- InstanceBeginEditable name="handler" -->
		<div class="hx-pg-btn">
			<a href="javascript:$.closeHXModalWin();" id="handler-cancel">返 回</a>
		</div>
	</div>
</div>
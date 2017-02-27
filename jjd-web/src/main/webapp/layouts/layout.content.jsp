<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/layouts/taglibs.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">  
<html>
<head>
<jsp:include page="/layouts/header.jsp" />
</head>
<body>
	<div class="container-fluid">
		<div class="row-fluid">
			<div id="content" class="span12">
				<!-- content starts -->
				<tiles:insertAttribute name="workspace" />
				<!-- content ends -->
			</div>
			<!--/#content.span10-->
		</div>
		<!--/fluid-row-->
	</div>
</body>
</html>
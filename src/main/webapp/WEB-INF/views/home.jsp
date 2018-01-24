<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html>
<html>
<head>
<base href="<%=basePath%>">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="content-type" content="text/html; charset=UTF-8">
</head>
<body>
	<div id="container">
		<!-- 按钮触发模态框 -->
		<button class="btn btn-primary btn-lg"
			data-value="view/action/home_cro #container" data-show="init_cro"
			data-loaded="load_cro" data-script="resources/js/action/home_cro.js">变更单</button>
		<button class="btn btn-primary btn-lg" data-loaded="load_user"
			data-value="view/action/home_addUser #container"
			data-script="resources/js/action/home_addUser.js">添加用户</button>
		<!-- 模态框（Modal） 变更单-->
		<div class="modal fade" id="myModal" tabindex="-1" role="dialog"
			aria-labelledby="myModalLabel" aria-hidden="true">
			<div class="modal-dialog">
				<div class="modal-content">
					<!-- /.modal-content -->
				</div>
				<!-- /.modal -->
			</div>
		</div>
</body>
</html>
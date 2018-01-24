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
		<div class="modal-header">
			<button type="button" class="close" data-dismiss="modal"
				aria-hidden="true">&times;</button>
			<h4 class="modal-title" id="myModalLabel">添加用户</h4>
		</div>
		<div class="modal-body">
			<form class="form-inline pull-left" role="form"
				onSubmit="return false;">
				<div style="margin-bottom: 5px;">
					<div class="form-group">
						<label><span style="visibility:hidden">的的</span>姓名：</label> <input
							type="text" class="form-control" name="name" />
					</div>
				</div>
				<div style="margin-bottom: 5px;">
					<div class="form-group">
						<label>邮箱账号：</label> <input type="text" class="form-control"
							name="email" />
					</div>
				</div>
				<div>
					<div class="form-group pull-right">
						<button class="btn btn-success form-control"
							onclick="user_submit(this.form);return false;" title="警告"
							id="form_submit_addUser" data-placement="top">添加</button>
					</div>
				</div>
			</form>
		</div>
		<div class="modal-footer"></div>
	</div>
</body>
</html>
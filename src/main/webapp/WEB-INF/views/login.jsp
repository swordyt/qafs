<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	Date date = new Date();
	int month = date.getMonth();
	String season = "resources/img/season/";
	if (month <= 11 && 9 <= month) {
		season += "autumn";//秋天
	} else if (month <= 5 && 3 <= month) {
		season += "spring";//春天
	} else if (month <= 8 && 6 <= month) {
		season += "summer";//夏天
	} else if (month <= 2 || 12 == month) {
		season += "winter";//冬天
	}
	season += ".jpg";
%>
<!DOCTYPE html>
<html>
<head>
<base href="<%=basePath%>">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="content-type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css"
	href="resources/css/bootstrap/bootstrap.min.css">
<link rel="stylesheet" type="text/css"
	href="resources/css/bootstrap/bootoast.css">
<script type="text/javascript"
	src="resources/js/jquery/jquery-3.2.1.min.js"></script>
<script type="text/javascript"
	src="resources/js/jquery/jquery.cookie.js"></script>
<script type="text/javascript"
	src="resources/js/bootstrap/bootstrap.min.js"></script>
<title>QAFS</title>
</head>
<body
	style="background-image:url('<%=season%>');background-repeat:no-repeat;background-attachment:fixed;background-position:50% 50%;">
	<div class="container">
		<div class="row" style="margin-top:200px;">
			<div class="col-xs-4"></div>
			<div class="col-xs-4">
				<div id="myAlert" class="alert alert-warning" style="display:none">
					<strong>警告！</strong><span id="alertMessage"></span>
				</div>
				<form action="user/login" role="form" method="post"
					onSubmit="submit">
					<div class="form-group">
						<label class="sr-only" for="email">email</label> <input
							class="form-control" type="email" name="email"
							placeholder="请输入email地址" id="email" required="required" />
					</div>
					<div class="form-group">
						<label class="sr-only" for="password">密码</label> <input
							class="form-control" type="password" name="password"
							placeholder="请输入登录密码" id="password" required="required" />
					</div>
					<input class="btn btn-success form-control" type="submit"
						value="登录" onclick="login(this.form);return false;">
				</form>
			</div>
			<div class="col-xs-4"></div>
		</div>
	</div>
	<script src="resources/js/common.js"></script>
	<script src="resources/js/login.js"></script>
	<script type="text/javascript"
	src="resources/js/bootstrap/bootoast.js"></script>
</body>
</html>
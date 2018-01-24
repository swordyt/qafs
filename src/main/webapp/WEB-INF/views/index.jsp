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
<link rel="stylesheet" type="text/css"
	href="resources/css/bootstrap/bootstrap.min.css">
<link rel="stylesheet" type="text/css"
	href="resources/css/bootstrap/bootoast.css">
<link rel="stylesheet" type="text/css"
	href="resources/css/bootstrap-datetimepicker/bootstrap-datetimepicker.min.css">
<script type="text/javascript"
	src="resources/js/jquery/jquery-3.2.1.min.js"></script>
<script type="text/javascript"
	src="resources/js/jquery/jquery.cookie.js"></script>
<script type="text/javascript"
	src="resources/js/bootstrap/bootstrap-multiselect.js"></script>
<script type="text/javascript"
	src="resources/js/bootstrap-datetimepicker/bootstrap-datetimepicker.min.js"></script>
<link rel="stylesheet"
	href="resources/css/bootstrap/bootstrap-multiselect.css">
<script type="text/javascript"
	src="resources/js/bootstrap/bootstrap.min.js"></script>
<script type="text/javascript" src="resources/js/common.js"></script>

<link rel="stylesheet"
	href="//cdnjs.cloudflare.com/ajax/libs/bootstrap-table/1.11.1/bootstrap-table.min.css">

<!-- Latest compiled and minified JavaScript -->
<script
	src="//cdnjs.cloudflare.com/ajax/libs/bootstrap-table/1.11.1/bootstrap-table.min.js"></script>

<!-- Latest compiled and minified Locales -->
<script
	src="//cdnjs.cloudflare.com/ajax/libs/bootstrap-table/1.11.1/locale/bootstrap-table-zh-CN.min.js"></script>

<link rel="shortcut icon" href="favicon.ico" />
<title>QAFS</title>
<style type="text/css">
div.modal-footer{
	border-top: 0px solid #e5e5e5;
}
</style>
</head>
<body>
	<div class="container" style="padding: 0">
		<div class="row" id="frameHead" title="提示" data-placement="bottom">
			<nav class="navbar navbar-default" role="navigation">
				<div class="container=fluid">
					<div class="navbar-header">
						<button type="button" class="navbar-toggle" data-toggle="collapse"
							data-target="#navbar-collapse">
							<span class="sr-only">切换导航</span> <span class="icon-bar"></span>
							<span class="icon-bar"></span> <span class="icon-bar"></span>
						</button>
						<a class="navbar-brand active" href="javascript:void(0);"
							name="nav-home">QAFS</a>
					</div>
					<div class="collapse navbar-collapse" id="navbar-collapse">
						<ul class="nav navbar-nav">
							<li><a href="javascript:void(0);" name="nav-my">我的 </a>
							<li><a href="javascript:void(0);" name="nav-pending">待处理
							</a></li>
							<li><a href="javascript:void(0);" name="nav-completed">已完成
							</a></li>
						</ul>
						<form class="navbar-form navbar-right" role="search">
							<div class="form-group">
								<select class="form-control pull-right" id="projectSelect">
								</select>
							</div>
						</form>
						<p class="navbar-text navbar-right">
							<a href="view/login" id="index_userName"></a>
						</p>
					</div>
				</div>
			</nav>
		</div>
		<div class="row" id="frameBody"></div>
	</div>
	<script src="resources/js/head.js"></script>
	<script src="resources/js/home.js"></script>
	<script src="resources/js/my.js"></script>
	<script src="resources/js/pending.js"></script>
	<script type="text/javascript" src="resources/js/bootstrap/bootoast.js"></script>
</body>
</html>

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
<script type="text/javascript"
	src="resources/js/jquery/jquery-3.2.1.min.js"></script>
<link rel="shortcut icon" href="favicon.ico" />
<title>QAFS</title>
</head>
<body>
	<script type="text/javascript">
		function clk() {
			$.getScript("resources/js/test.js");
			test();
		}
	</script>
	<input type="button" value="点我" onclick="clk()" />
	<script type="text/javascript" src="resources/js/test.js"></script>
</body>
</html>
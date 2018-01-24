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
<script type="text/javascript"
	src="resources/js/jquery/jquery-3.2.1.min.js"></script>
<script type="text/javascript"
	src="resources/js/bootstrap/bootstrap.min.js"></script>
<script type="text/javascript" src="resources/js/common.js"></script>
<link rel="shortcut icon" href="favicon.ico" />
<title>QAFS</title>
<style type="text/css">
td {
	font-size: 18px;
	padding: 0;
}

input,textarea {
	display: block;
	width: 100%;
	height: 100%;
	min-height: 100px;
}
</style>
</head>
<body>
	<div class="container">
		<div class="row">
			<table border="5" width="100%">
				<tr>
					<td colspan="6" align="center"><strong>红高粱财富变更单</strong> <input
						type="hidden" style="display:none" id="id" /></td>
				</tr>
				<tr>
					<td><strong>上线时间</strong>
					</td>
					<td colspan="2" id="onlineTime">2018年1月2日</td>
					<td><strong>上线项</strong>
					</td>
					<td colspan="2" id="onlineTitle">PR-4500红高粱项目二期</td>
				</tr>
				<tr>
					<td><strong>变更单申请人</strong>
					</td>
					<td colspan="2" id="changeApplicant">刘冬</td>
					<td><strong>变更单处理人</strong>
					</td>
					<td colspan="2" id="changeDealMan">小明</td>
				</tr>
				<tr>
					<td><strong>技术</strong></td>
					<td colspan="5" id="developmentEngineer">孙玉林</td>
				</tr>
				<tr>
					<td><strong>运维</strong></td>
					<td colspan="5" id="operationEngineer">孙玉林</td>
				</tr>
				<tr>
					<td><strong>测试</strong></td>
					<td colspan="5" id="testEngineer">孙玉林</td>
				</tr>
				<tr>
					<td><strong>上线内容</strong></td>
					<td colspan="5" id="onlineContent"></td>
				</tr>
				<tr>
					<td><strong>工单创建者备注信息</strong></td>
					<td colspan="5" id="remark"></td>
				</tr>
				<tr>
					<td><strong>涉及系统</strong>
					</td>
					<td colspan="2"><textarea id="relatedSystem"></textarea>
					</td>
					<td><strong>变更系统上线顺序</strong>
					</td>
					<td colspan="2"><textarea id="changeSystemOrder"></textarea>
					</td>
				</tr>
				<tr>
					<td><strong>网络配置变更</strong>
					</td>
					<td colspan="2"><textarea id="networkConfigurationChange"></textarea>
					</td>
					<td><strong>配置文件变更</strong>
					</td>
					<td colspan="2"><textarea id="configurationFileChange"></textarea>
					</td>
				</tr>
				<tr>
					<td><strong>数据库变更</strong>
					</td>
					<td colspan="2"><textarea id="databaseChange"></textarea>
					</td>
					<td><strong>回滚方案</strong>
					</td>
					<td colspan="2"><textarea id="rollbackPlan"></textarea>
					</td>
				</tr>
				<tr>
					<td><strong>投产条件是否具备</strong></td>
					<td colspan="5"><input type="text" id="upYesAndNo">
					</td>
				</tr>
				<tr>
					<td><strong>备注</strong></td>
					<td colspan="5"><textarea id="comment"></textarea>
					</td>
				</tr>
				<tr>
					<td colspan="3"><button class="btn btn-danger btn-block"
							onclick="comfirm('0');return false;">拒绝上线</button>
					</td>
					<td colspan="3"><button class="btn-block btn btn-success"
							onclick="comfirm('1');return false;">确认上线</button></td>
				</tr>
			</table>
		</div>
		<div class="row text-info bg-success" id="orderLog"></div>
	</div>
	<script type="text/javascript" src="resources/js/rcodetail.js"></script>
</body>
</html>

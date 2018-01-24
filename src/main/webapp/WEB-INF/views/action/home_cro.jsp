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
			<h4 class="modal-title" id="myModalLabel">创建变更单</h4>
		</div>
		<div class="modal-body">
			<form class="form-inline pull-right" role="form"
				onSubmit="return false;">
				<div style="margin-bottom: 5px;">
					<div class="form-group">
						<label><span style="visibility:hidden">我的的的</span>上线时间：</label> <input
							size="16" type="text" readonly id="datetimepicker"
							name="onlinetime" class="form-control">
					</div>
				</div>
				<div style="margin-bottom: 5px;">
					<div class="form-group">
						<label><span style="visibility:hidden">我的的的的</span>上线项：</label> <input
							type="text" class="form-control" name="onlinetitle" />
					</div>
				</div>
				<div style="margin-bottom: 5px;">
					<div class="form-group">
						<label><span style="visibility:hidden">我的</span>变更单申请人：</label> <input
							id="changeApplicant" type="text" value="" class="form-control"
							readonly />
					</div>
				</div>
				<div style="margin-bottom: 5px;">
					<div class="form-group">
						<label><span style="visibility:hidden">的的</span>变更单处理人：</label> <input
							type="text" class="form-control" name="changedealman" readonly />
					</div>
				</div>
				<div style="margin-bottom: 5px;">
					<div class="form-group">
						<label><span style="visibility:hidden">我的的的等等</span>技术：</label><select
							id="example-getting-develop" multiple="multiple">
						</select>
					</div>
				</div>
				<div style="margin-bottom: 5px;">
					<div class="form-group">
						<label><span style="visibility:hidden">我的的等等的</span>运维：</label> <select
							id="example-getting-operation" multiple="multiple">
					</div>
				</div>
				<div style="margin-bottom: 5px;">
					<div class="form-group">
						<label><span style="visibility:hidden">
						</label> <select multiple="multiple">
					</div>
				</div>
				<div style="margin-bottom: 5px;">
					<div class="form-group">
						<label><span style="visibility:hidden">我的的等等的</span>测试：</label> <select
							id="example-getting-test" multiple="multiple">
					</div>
				</div>
				<div style="margin-bottom: 5px;">
					<div class="form-group">
						<label><span style="visibility:hidden">
						</label> <select multiple="multiple">
					</div>
				</div>

				<div style="margin-bottom: 5px;">
					<div class="form-group">
						<label><span style="visibility:hidden">我的的的</span>上线内容：</label>
						<textarea cols="50" rows="5" class="form-control"
							name="onlinecontent"></textarea>
					</div>
				</div>
				<div style="margin-bottom: 5px;">
					<div class="form-group">
						<label><span style="visibility:hidden">我的的的</span>涉及系统：</label>
						<textarea cols="50" rows="5" class="form-control"
							name="relatedsystem"></textarea>
					</div>
				</div>
				<div style="margin-bottom: 5px;">
					<div class="form-group">
						<label>变更系统上线顺序：</label>
						<textarea cols="50" rows="5" class="form-control"
							name="changesystemorder"></textarea>
					</div>
				</div>
				<div style="margin-bottom: 5px;">
					<div class="form-group">
						<label><span style="visibility:hidden">的的</span>网络配置变更：</label>
						<textarea cols="50" rows="5" class="form-control"
							name="networkconfigurationchange"></textarea>
					</div>
				</div>
				<div style="margin-bottom: 5px;">
					<div class="form-group">
						<label><span style="visibility:hidden">的的</span>配置文件变更：</label>
						<textarea cols="50" rows="5" class="form-control"
							name="configurationfilechange"></textarea>
					</div>
				</div>
				<div style="margin-bottom: 5px;">
					<div class="form-group">
						<label><span style="visibility:hidden">我的的</span>数据库变更：</label>
						<textarea cols="50" rows="5" class="form-control"
							name="databasechange"></textarea>
					</div>
				</div>
				<div style="margin-bottom: 5px;">
					<div class="form-group">
						<label><span style="visibility:hidden">我的的的</span>回滚方案：</label>
						<textarea cols="50" rows="5" class="form-control"
							name="rollbackplan"></textarea>
					</div>
				</div>
				<div style="margin-bottom: 5px;">
					<div class="form-group">
						<label>投产条件是否具备：</label> <select class="form-control"
							name="upyesandno">
							<option>是</option>
							<option selected>否</option>
						</select>
					</div>
				</div>
				<div style="margin-bottom: 5px;">
					<div class="form-group">
						<label><span style="visibility:hidden">我的等等的的</span>备注：</label>
						<textarea cols="50" rows="5" class="form-control" name="comment"></textarea>
					</div>
				</div>
				<div>
					<div class="form-group pull-right">
						<button class="btn btn-success form-control"
							onclick="requirement_change_order_submit(this.form);return false;"
							title="警告" id="form_submit" data-placement="top">提交</button>
					</div>
				</div>
			</form>
		</div>
		<div class="modal-footer"></div>
	</div>
</body>
</html>
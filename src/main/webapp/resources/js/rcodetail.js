$(document).ready(
		function() {
			var id = Tools.getUrlParam("id");
			function callback(data) {
				$("#id").val(data.data.id);
				$("#onlineTime").html(
						Tools.formatTimestampToDate(data.data.onlinetime));
				$("#onlineTitle").html(data.data.onlinetitle);
				$("#changeApplicant").html(data.data.changeapplicantName);
				$("#changeDealMan").html(data.data.changedealmanName);
				$("#developmentEngineer").html(
						JSON.stringify(data.data.developmentengineerName));
				$("#operationEngineer").html(
						JSON.stringify(data.data.operationengineerName));
				$("#testEngineer").html(
						JSON.stringify(data.data.testengineerName));
				$("#onlineContent").html(data.data.onlinecontent);

				$("#relatedSystem").val(data.data.relatedsystem);
				$("#changeSystemOrder").val(data.data.changesystemorder);
				$("#networkConfigurationChange").val(
						data.data.networkconfigurationchange);
				$("#configurationFileChange").val(
						data.data.configurationfilechange);
				$("#databaseChange").val(data.data.databasechange);
				$("#rollbackPlan").val(data.data.rollbackplan);
				$("#upYesAndNo").val(data.data.upyesandno);
				$("#remark").html(data.data.comment);
			}
			Send.Post("rco/detail", {
				id : id
			}).then(callback);
			function callback_log(data) {
				if (data.code == "000000") {
					$.each(data.data, function(k, v) {
						$("#orderLog").append('<p>'+Tools.formatTimestampToDate(v.createtime) + '  '+v.ordercontent + '</p>');
					});
				}
			}
			Send.Post("rco/log", {
				id : id
			}).then(callback_log);
		});
function comfirm(type) {
	var data = new Object();
	data.id = $("#id").val().trim();
	data.relatedsystem = $("#relatedSystem").val().trim();
	data.changesystemorder = $("#changeSystemOrder").val().trim();
	data.networkconfigurationchange = $("#networkConfigurationChange").val()
			.trim();
	data.configurationfilechange = $("#configurationFileChange").val().trim();
	data.databasechange = $("#databaseChange").val().trim();
	data.rollbackplan = $("#rollbackPlan").val().trim();
	data.upyesandno = $("#upYesAndNo").val().trim();
	data.comment = $("#comment").val().trim();
	data.erId = Tools.getUrlParam("erId");
	if (!Tools.checkNotEmpty(data.comment)) {
		alert("备注不能为空！");
		return false;
	}
	function callback(data) {
		if (data.code == "000000") {
			location.href = "view/index";
		} else {
			alert(data.msg);
		}
	}
	if (type == "0") {
		Send.Post("or/refuse", data).then(callback);
	}
	if (type == "1") {
		Send.Post("or/confirm", data).then(callback);
	}

	return false;
}
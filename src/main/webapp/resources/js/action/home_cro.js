function load_cro() {
	$("#datetimepicker").datetimepicker({
		format : 'yyyy-mm-dd hh:ii'
	});
	$("#changeApplicant").val($.cookie("name"));
	$('#form_submit').on('shown.bs.popover', function() {
		setTimeout('popoverClose("#form_submit")', 1000);
	});
}
function init_cro() {
	var mulSelect = new MultiSelect();

	mulSelect.initByUrl("#example-getting-develop", "user/users", {
		limit : 0
	}).then(function(data) {
		mulSelect.initByData("#example-getting-operation");
		mulSelect.initByData("#example-getting-test");
	});

	// setTimeout(function() {

	// }, 1000);

}
function requirement_change_order_submit(form) {
	var data = new Object();
	data.onlinetitle = $(form.onlinetitle).val().trim();
	data.onlinetime = $(form.onlinetime).val().trim();
	data.changedealman = $(form.changedealman).val().trim();
	data.onlinecontent = $(form.onlinecontent).val().trim();
	data.relatedsystem = $(form.relatedsystem).val().trim();
	data.changesystemorder = $(form.changesystemorder).val().trim();
	data.networkconfigurationchange = $(form.networkconfigurationchange).val()
			.trim();
	data.configurationfilechange = $(form.configurationfilechange).val().trim();
	data.databasechange = $(form.databasechange).val().trim();
	data.rollbackplan = $(form.rollbackplan).val().trim();
	data.upyesandno = $(form.upyesandno).val().trim();
	data.comment = $(form.comment).val().trim();
	data.developmentengineer = JSON.stringify(new MultiSelect()
			.getValues("#example-getting-develop"));
	data.operationengineer = JSON.stringify(new MultiSelect()
			.getValues("#example-getting-operation"));
	data.testengineer = JSON.stringify(new MultiSelect()
			.getValues("#example-getting-test"));
	if (data.testengineer == '[]' || data.operationengineer == "[]"
			|| data.developmentengineer == "[]" || data.onlinetitle == ""
			|| data.onlinetime == "" || data.onlinecontent == "") {
		Tools.toast("上线时间，上线项，技术，运维，测试，上线内容参数不能为空！");
//		$("#form_submit")
//				.popover(
//						{
//							html : true,
//							content : '<span class="text-warning"></span>',
//						});
//		$("#form_submit").popover('show');
		return false;
	}
	function callback(data) {
		if (data.code == '000000') {
			form.reset();
			$('#myModal').modal('hide');
		}
	}
	Send.Post("rco/add", data).then(callback);
	return false;
}
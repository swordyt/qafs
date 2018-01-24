function user_submit(e) {
	var data = new Object();
	data.email = $(e.email).val().trim();
	data.name = $(e.name).val().trim();
	console.log(data);
	if (!Tools.checkNotEmpty(data.email) || !Tools.checkNotEmpty(data.name)) {
		Tools.toast("邮箱账号或密码不能为空！");
		return false;
	}
	if(!data.email.match(/^([a-zA-Z0-9_-])+@nonobank\.com/)){
		Tools.toast("邮箱格式不合法，格式应满足xxxx@nonobank.com");
		return false;
	}
	function callback(data) {
		if (data.code == "000000") {
			$('#myModal').modal('hide');
			e.reset();
		}
	}
	console.log(data);
	Send.Post("user/add", data).then(callback);
	return false;

}
function load_user() {
	$('#form_submit_addUser').on('shown.bs.popover', function() {
		setTimeout('popoverClose("#form_submit_addUser")', 1000);
	});
}
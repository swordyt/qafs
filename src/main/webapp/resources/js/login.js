function login(e) {
	function fun(data) {
		if (data.code == "000000") {
			$.cookie('token', data.data.token, {
				path : '/qafs'
			});
			$.cookie('userId', data.data.userId, {
				path : '/qafs'
			});
			$.cookie('name', data.data.name, {
				path : '/qafs'
			});
			$.cookie('email', data.data.email, {
				path : '/qafs'
			});
			var prevLink = document.referrer; 
			if(prevLink ==""){
				prevLink="/qafs/";
			}
			location.href = prevLink;
			
		} else {
			$("#myAlert").css("display", "block");
			$("#alertMessage").html(data.msg);
		}
	}
	var data = new Object();
	data.email = $(e.email).val().trim();
	data.password = $(e.password).val().trim();
	console.log(data);
	Send.Post("user/login", data).then(fun);
}
$(document).ready(function() {
	$("#email,#password").focus(function() {
		$("#myAlert").css("display", "none");
	});
})
function test() {
	new Promise(function(resolve, reject) {
		setTimeout(function() {
			console.log("setTimeout", 2000);
			// resolve("执行完成");
			reject("执行出错");
		});
	}).then(function(data) {
		console.log(data);
	}, function(err) {
		console.log("错误", err);
	})
}
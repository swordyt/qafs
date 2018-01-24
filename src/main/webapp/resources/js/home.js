NavMapping['nav-home'] = {
	url : 'view/home',
	init : function() {
		var obj;
		$("#myModal").on("hidden.bs.modal", function() {
			$(this).removeData("bs.modal");
		});
		$("#myModal").on("show.bs.modal", function() {
			var method = eval($(obj).attr("data-show"));
			if (method != undefined) {
				new method();
			}
		});
		$("#myModal").on("loaded.bs.modal", function() {
			var method = eval($(obj).attr("data-loaded"));
			if (method != undefined) {
				new method();
			}

		});
		$("button[data-value]").click(function() {
			obj = this;
			value = $(this).attr("data-value");
			var script = $(this).attr("data-script");
			if (script != null && script != undefined && script.trim() != "") {
				$.getScript(script, function() {
					console.log(script);
					$("#myModal").modal({
						remote : value
					});
				});
			} else {
				console.log(script);
				$("#myModal").modal({
					remote : value
				});
			}

		});
	}
};
function popoverClose(id) {
	$(id).popover('hide');
}
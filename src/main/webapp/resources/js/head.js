var NavMapping = {};
NavMapping['nav-completed'] = {
	url : '',
	init : function() {
		
	}
};
$(document).ready(
		function() {
			$("#navbar-collapse ul li a,a.navbar-brand").click(
					function() {
						var name = $(this).attr("name");
						var obj = NavMapping[name];
						$("#frameBody").load(obj.url + " #container",
								function(response, status, xhr) {
									obj.init();
								});
					});
		});
$("#index_userName").html($.cookie("name"));
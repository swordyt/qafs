NavMapping['nav-pending'] = {
	url : 'view/pending',
	init : function() {
		var data = [
				{
					field : 'orderId',
					title : '编号'
				},
				{
					field : 'onlinetime',
					title : '上线日期',
					formatter : function(value, row, index) {
						return Tools.formatTimestampToDate(value);
					}
				},
				{
					field : 'onlinetitle',
					title : '上线项',
					formatter : function(value, row, index) {
						return '<a target="_blank" href="view/rcodetail?id='
								+ row.orderId + '&erId='+row.id+'">' + value + '</a>'
					}
				},
				{
					field : 'confirmstatusName',
					title : '确认状态'

				},
				{
					field : 'createtime',
					title : '开始时间',
					formatter : function(value, row, index) {
						return Tools.formatTimestampToDate(value);
					}
				},
				{
					field : 'opt',
					title : '操作',
					formatter : function(value, row, index) {
						if (row.orderstatus == "I") {
							return [
									'<button type="button" class="btn-xs btn-success" onclick="orderDeal('
											+ row.id + ',' + 1
											+ ')">发布</button>',
									'<button type="button" class="btn-xs btn-danger" onclick="orderDeal('
											+ row.id + ',' + 0
											+ ')">删除</button>' ].join('');
						}
						if (row.orderstatus == "S") {
							return null;
						}
						return [
								,
								'<button type="button" class="btn-xs btn-success" onclick="orderDeal('
										+ row.id + ',' + 2 + ')">停止</button>' ]
								.join('');
					},
				} ];
		initBootstrapTable("#table", "or/search", data);
	}
};
function orderDeal(orderId, type) {
	var data = new Object();
	data.orderId = orderId;
	data.type = type;
	function callback(data) {
		if (data.code == "000000") {
			$("#table").bootstrapTable('refresh');
		}
	}
	Send.Post("rco/deal", data).then(callback);
}

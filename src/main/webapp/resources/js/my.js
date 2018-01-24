NavMapping['nav-my'] = {
	url : 'view/my',
	init : function() {
		var data = [
				{
					field : 'id',
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
								+ row.id + '">' + value + '</a>';
					}
				},
				{
					field : 'orderstatusName',
					title : '订单状态'
				},
				{
					field : 'createtime',
					title : '创建时间',
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
						return null;
					}

				} ];
		initBootstrapTable("#table", "rco/search", data);
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

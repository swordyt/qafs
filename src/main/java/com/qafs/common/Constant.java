package com.qafs.common;

public class Constant {
	/**
	 * jwt
	 * */
	public class JWT {
		public static final String JWT_ID = "jwt";
		public static final String JWT_SECRET = "7786df7fc3a34e26a61c034d5ec8245d";
		public static final int JWT_TTL = 60 * 60 * 1000; // millisecond
		public static final int JWT_REFRESH_INTERVAL = 55 * 60 * 1000; // millisecond
		public static final int JWT_REFRESH_TTL = 12 * 60 * 60 * 1000; // millisecond
	}

	/**
	 * code编码
	 * */
	public class CODE {
		public static final String SUCCESS = "000000"; // 成功
		public static final String FALSE = "000001"; // 错误(有返回信息)
		public static final String EXCEPTION = "000002"; // 请求抛出异常
		public static final String NOLOGIN = "999999"; // 登录失效
		public static final String NOEXIST = "000004"; // 查询结果为空
		public static final String NOAUTH = "000005"; // 无操作权限
	}

	/**
	 * message 消息
	 * */
	public class MSG {
		public static final String SUCCESS = "成功";
		public static final String ACCOUNT_OR_PASSWORD_ERROR = "账号或密码错误！";
		public static final String ACCOUNT_OR_PASSWORD_NOTEMPTY = "用户名或密码不能为空！";
		public static final String NOLOGIN = "请登录！";
		public static final String FALSE = "错误";
		public static final String ORDER_STATUS_EXCEPTION = "订单状态异常";
		public static final String PARAMETER_NOT_NULL = "参数不能为空";
		public static final String NAME_OR_EMAIL_IS_EXIST="姓名或邮箱已经存在";

	}

	public class EMAIL {
		public static final String TITLE = "需求变更单确认通知";
		public static final String TEXT = "有工单需要您的确认，请点击下方链接进行确认，初始账号：邮箱号，密码：123456，链接：";
	}

	public static final Integer DEFAULT_ROLEID = 1;// 默认角色ID
}

package com.qafs.common;

import java.util.HashMap;

public class RCOOrderStatus {
	public static final String I = "I";// 初始创建
	public static final String DC = "DC";// 等待研发确认
	public static final String TC = "TC";// 等待测试确认
	public static final String OJ="OJ";//上线拒绝
	public static final String S = "S";// 完成
	public final static HashMap<String, String> value = new HashMap<String, String>() {
		{
			put(RCOOrderStatus.I, "等待发布");
			put(RCOOrderStatus.DC, "等待研发确认");
			put(RCOOrderStatus.TC, "等待测试确认");
			put(RCOOrderStatus.OJ,"上线拒绝");
			put(RCOOrderStatus.S, "完成");
		}
	};
}

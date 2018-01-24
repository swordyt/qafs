package com.qafs.common;

import java.util.HashMap;

public class EmailConfirmStatus {
	public final static Integer TBC = 0;// to be confirm
	public final static Integer CC = 1;// Confirm completed
	public final static Integer NAK = 2;// Confirm the failure
	
	public final static HashMap<Integer, String> value=new HashMap<Integer,String>(){{
		put(EmailConfirmStatus.TBC, "等待确认");
		put(EmailConfirmStatus.CC, "确认完成");
		put(EmailConfirmStatus.NAK, "确认拒绝");
	}};
}

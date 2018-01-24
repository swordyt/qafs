package com.qafs.util;

public class ParaUtil {
	public static boolean notNull(Object o) {
		if (o != null) {
			return true;
		}
		return false;
	}

	public static boolean notEmpty(Object obj) {
		if (obj instanceof String) {
			if (notNull(obj) && !((String) obj).trim().equals("")) {
				return true;
			}
		} else {
			return notNull(obj);
		}

		return false;
	}
}

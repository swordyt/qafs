package com.qafs.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;

public class PropertiesUtil {
	private static final Logger log = Logger.getLogger(PropertiesUtil.class);
	private static Properties configFile;

	public static void loadConfig() {
		log.debug("导入配置文件config.properties");
		configFile = new Properties();
		InputStream in = null;
		try {
//			in = new FileInputStream(new File("config.properties"));
			in = new FileInputStream(PropertiesUtil.class.getResource("/").getPath()+"config.properties");
//			configFile.load(PropertiesUtil.class.getClassLoader().getResourceAsStream("config.properties"));
			configFile.load(in);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (in != null) {
				try {
					in.close();
				} catch (IOException e) {
					log.error("config.properties文件流关闭出现异常");
				}
			}
		}
	}

	public static String getProperty(String key) {
		if (null == configFile) {
			loadConfig();
		}
		return configFile.getProperty(key);
	}

	public static String getProperty(String key, String defaultValue) {
		if (null == configFile) {
			loadConfig();
		}
		return configFile.getProperty(key, defaultValue);
	}

	public static String fillPath(String path) {
		Pattern p = Pattern.compile("\\$\\{.*?\\}");
		Matcher m = p.matcher(path);
		while (m.find()) {
			String parameter = m.group().substring(2, m.group().length() - 1);
			path = path.replace(m.group(),
					getProperty(parameter) == null ? "null"
							: getProperty(parameter));
			m = p.matcher(path);
		}
		log.info("格式化字符串为：" + path);
		return path.trim();
	}

	/**
	 * 
	 * 从System中提取指定key,并根据指令进行转化
	 */
	public static String getFormatProperty(String key) {
		return getProperty(fillPath(key));
	}
}

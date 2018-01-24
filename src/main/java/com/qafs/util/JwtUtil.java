package com.qafs.util;

import java.util.Date;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONObject;
import com.qafs.common.Constant;
import com.qafs.domain.User;

@Component
public class JwtUtil {
	// @Value("#{configProperties['t1.msgname']}")
	@Value("${spring.profiles.active}")
	private String profiles;

	/**
	 * 由字符串生成加密key
	 * 
	 * @return
	 * */
	public SecretKey generalKey() {
		String stringKey = profiles + Constant.JWT.JWT_SECRET;
		byte[] encodeKey = Base64.decodeBase64(stringKey);
		SecretKey key = new SecretKeySpec(encodeKey, 0, encodeKey.length, "AES");
		return key;
	}

	/***
	 * iss: jwt签发者 sub: jwt所面向的用户 aud: 接收jwt的一方 exp: jwt的过期时间，这个过期时间必须要大于签发时间
	 * nbf: 定义在什么时间之前，该jwt都是不可用的. iat: jwt的签发时间 jti:
	 * jwt的唯一身份标识，主要用来作为一次性token,从而回避重放攻击。
	 * 
	 * @param id
	 *            token唯一标识
	 * @param subject
	 *            jwt面向用户
	 * @param ttlMillis
	 * @param
	 * @return
	 * @throws Exception
	 * */
	public String createJWT(String id, String subject, long ttlMillis) {
		SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
		long nowMillis = System.currentTimeMillis();
		Date now = new Date(nowMillis);
		SecretKey key = generalKey();
		JwtBuilder builder = Jwts.builder().setId(id).setIssuedAt(now)// 签发时间
				.setSubject(subject).signWith(signatureAlgorithm, key);
		if (ttlMillis >= 0) {
			long expMillis = nowMillis + ttlMillis;
			Date exp = new Date(expMillis);
			builder.setExpiration(exp);// 过期时间
		}
		return builder.compact();
	}

	/**
	 * 解密jwt
	 * 
	 * @param jwt
	 * @return
	 * @throws Exception
	 * */
	public Claims parseJWT(String jwt) {
		SecretKey key = generalKey();
		Claims claims = Jwts.parser().setSigningKey(key).parseClaimsJws(jwt)
				.getBody();
		return claims;
	}

	/**
	 * 生成subject信息
	 * 
	 * @param user
	 * @return
	 * */
	public static String generalSubject(User user) {
		JSONObject jo = new JSONObject();
		jo.put("userId", user.getId());
		return jo.toJSONString();
	}

	/**
	 * 获取sub中指定key值
	 * */
	public static Object parseSubject(JwtUtil jwt, String token, String key)
			throws Exception {
		Claims claims = jwt.parseJWT(token);
		String sub = claims.getSubject();
		return JSONObject.parseObject(sub).get(key);
	}
}

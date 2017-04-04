package com.qjj.wechat.pojo;

import lombok.Getter;
import lombok.Setter;

/**
 * 凭证
 *
 * @author Jimmy
 * @date 2017-04-04
 */
@Getter
@Setter
public class Token {
	// 接口访问凭证
	private String accessToken;
	// 凭证有效期，单位：秒
	private int expiresIn;
}
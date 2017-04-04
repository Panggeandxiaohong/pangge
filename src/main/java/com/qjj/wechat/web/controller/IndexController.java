package com.qjj.wx.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {

	@RequestMapping("/index.do")
	public String index() {
		System.out.println("code=" );
		// 通过code获取网页授权的access_token
//		String result = HttpUtil.get(WeixinUtil.WEB_ACCESS_TOKEN_URL.replace("APPID",
//				WeixinUtil.APPID).replace("SECRET", WeixinUtil.APPSECRET).replace("CODE", code));
//		System.out.println(result);
		return "index";
	}

}

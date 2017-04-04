package com.qjj.wechat.web.controller;

import com.qjj.wechat.damain.XmlMessageEntity;
import com.qjj.wechat.service.CoreService;
import com.qjj.wechat.util2.SignUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

@Controller
public class WeixinController {
	public static final String TOKEN = "pangge";

	@RequestMapping(value = "/weixin.do", method = RequestMethod.GET)
	@ResponseBody
	public String validate(String signature, String timestamp, String nonce,
			String echostr,HttpServletResponse response)throws Exception {
        System.out.println("echostr="+echostr);
		response.setCharacterEncoding("UTF-8");
		System.out.println("set response utf-8");
		boolean checkSignature = SignUtil.checkSignature(signature, timestamp, nonce);
        if(checkSignature){
			PrintWriter out = response.getWriter();
			out.flush();
			out.print(echostr);
			System.out.println("write sucess");
			out.flush();
			System.out.println("flush sucess");
			out.close();
			out=null;
			return echostr;
        }
        System.out.println("fail");
        return null;
	}

	@RequestMapping(value = "/weixin.do", method = RequestMethod.POST)
	@ResponseBody
	public void handleMessage(@RequestBody XmlMessageEntity entity, HttpServletRequest request, HttpServletResponse response) throws Exception{
// 将请求、响应的编码均设置为UTF-8（防止中文乱码）
		response.setCharacterEncoding("UTF-8");
		// 调用核心业务类接收消息、处理消息
		String respXml = CoreService.processRequest(entity);
		// 响应消息
		PrintWriter out = response.getWriter();
		out.print(respXml);
		out.close();
	}

}

package com.joe.web;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.support.spring.annotation.ResponseJSONP;
import com.joe.util.Recodeutil;

@Controller
public class Qrcode {

	@RequestMapping(value="/qrcode",method=RequestMethod.GET)
	@ResponseBody
	public void qrcode(HttpServletRequest request, HttpServletResponse response, @RequestParam(name="content") String content) throws IOException {
		if(StringUtils.isBlank(content)) {
			response.sendRedirect("/404.html");
			return;
		}
		Recodeutil.createRrCode(content, 180, 180, response);
	}
	
	
}

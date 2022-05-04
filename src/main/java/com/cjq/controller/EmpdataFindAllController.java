package com.cjq.controller;
/**
 * @author CJQ
 * 2022/04/23
 * @version 1.0
 * 描述：查询所有社员信息的的控制类 
 */
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


import com.cjq.bean.Empdata;
import com.cjq.service.EmpdataFindAllService;

@Controller//告诉spring当前类是需要管理的控制类
public class EmpdataFindAllController {
	/*
	 * 成员属性：EmpdataFindAllService empdataFindAllService：EmpdataFindAllService类型，属性名是empdataFindAllService
	 * @Autowired：完成自动注入
	 */
	@Autowired
	private EmpdataFindAllService empdataFindAllService;//引用service层
	@GetMapping("/emplist")
	/*
	    * 方法名：findAll
	    * 范围修饰符：public
	    * 返回值：String 需要访问的web资源地址
	    * 参数： Model model：以键值对形式存储对象，传递给前端
         *描述：查询所有社员信息一览
	    */
	public String findAll(Model model){
		List<Empdata> list=empdataFindAllService.empdataFindfAll();
		model.addAttribute("emplist",list);
		return "/emplist";
		
	}
	
}

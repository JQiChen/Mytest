package com.cjq.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.cjq.bean.Empdata;
import com.cjq.service.EmpdataService;


/**
 * @author CJQ
 * 2022/04/25
 * @version 1.0
 * 描述：删除社员信息，关键字检索和社员信息详细功能的控制类
 */
@Controller
public class EmpdataController {
	/*
	 * 成员属性：EmpdataService empdataService：EmpdataService类型，属性名是empdataService
	 * @Autowired：完成自动注入
	 */
	@Autowired
 private EmpdataService empdataService;
	 /*
	    * 方法名：deleteEmp
	    * 范围修饰符：public
	    * 返回值：String 需要访问的web资源地址
	    * 参数： @RequestParam(value="empCd")接收参数，String empCd：根据empCd进行删除
            *描述：删除社员信息
	    */
 @RequestMapping("/deleteEmp")
 public String deleteEmp(@RequestParam(value="empCd")String empCd) {		
	 empdataService.deleteEmp(empCd);				
		return "redirect:/emplist";
 }
 /*
  * 方法名：findEmp
  * 范围修饰符：public
  * 返回值：String 需要访问的web资源地址
  * 参数： @RequestParam(value="keyword")接收参数，String keyword：根据empCd进行检索，
  * Model model：以键值对形式存储对象，传递给前端
      *描述：根据关键字查找社员信息
  */
 @GetMapping("/findEmp")
 public String findEmp(@RequestParam(value="keyword")String keyword,Model model) {
	 List<Empdata> empList=empdataService.findEmp(keyword);
	 model.addAttribute("emplist", empList);
	 return "/emplist";
 }
 /*
  * 方法名：showDetails
  * 范围修饰符：public
  * 返回值：String 需要访问的web资源地址
  * 参数： @RequestParam(value="empCd")接收参数，String keyword：根据empCd进行检索，
  * Model model：以键值对形式存储对象，传递给前端
      *描述：根据empCd获取社员详细信息
  */
 @GetMapping("/getEmp")
	public String showDetails(@RequestParam(value="empCd")String empCd,Model model) {
		//a通过empCd获得社员信息
		Empdata empdata = empdataService.getEmp(empCd);
		//b传递
		model.addAttribute("empdata",empdata);
		return "getEmp";
	}
 }


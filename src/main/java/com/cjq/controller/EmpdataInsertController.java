package com.cjq.controller;

import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.cjq.bean.Empdata;
import com.cjq.bean.Gender;
import com.cjq.bean.Nationality;
import com.cjq.form.EmpForm;
import com.cjq.service.EmpdataInsertService;
import com.cjq.service.EmpdataService;
import com.cjq.service.GenderService;
import com.cjq.service.NationalityService;
/**
 * 
 * @author CJQ
 * 2022/04/25
 * @version 1.0
 * 描述：添加社员信息的的控制类
 *
 */




@Controller
public class EmpdataInsertController {
	/*
	 * 成员属性：
	 * NationalityService nationalityService：NationalityService类型，属性名nationalityService
	 * @Autowired：完成自动注入
	 */
	@Autowired
	private NationalityService nationalityService;
	@Autowired
	private GenderService genderService;
	@Autowired
	private EmpdataInsertService empdataInsertService;	
	@Autowired
	private EmpdataService empdataService;	
	@Autowired
	private MessageSource messageSource;
	 @Autowired
	  HttpSession session; 
	 /*
	    * 方法名：toAddEmp
	    * 范围修饰符：public
	    * 返回值：String 需要访问的web资源地址
	    * 参数： @ModelAttribute("empForm")  对应前端empForm对象 EmpDataForm empForm：  接受前端表单数据，并带有数据格式验证 
      *  Model model：  以键值对形式存储对象，传递给前端
	    *描述：为添加页面提供国籍和性别数据
	    */
	  @GetMapping("/AddEmp")
		public String toAddEmp(@ModelAttribute("form") Empdata empdata, Model model) {
			// 1获取国籍数据
			List<Nationality> nationalityList = nationalityService.getNationalityList();
			// 2追加到Session值
			session.setAttribute("nationalityList", nationalityList);
			// 3获取性别数据
			List<Gender> genderList = genderService.getGenderList();
			// 4 追加到Session值
			session.setAttribute("genderList", genderList);
			return ("AddEmp");
		}
	  /*
	    * 方法名：addEmp
	    * 范围修饰符：public
	    * 返回值：String 需要访问的web资源地址
	    * 参数： @ModelAttribute("empForm")  对应前端empForm对象 EmpDataForm empForm：  接受前端表单数据，并带有数据格式验证
	    *   BindingResult result：指示存储验证结果的对象应如何存储和检索验证结果
     *  Model model：  以键值对形式存储对象，传递给前端
     *  Locale locale：国际化的使用
	    *描述：添加填写正确的社员信息
	    */
	  @PostMapping( "/AddEmp")
		public String addEmp(@ModelAttribute("form") @Valid EmpForm empForm, BindingResult result, 
					Model model, Locale locale) {	
		  
			if (result.hasErrors()) {
				//a将错误信息添加到集合里
				List<ObjectError> errorList = result.getAllErrors();
				model.addAttribute("errorList", errorList);
				return ("AddEmp");
			} else {
	            //	如果登录验证没有问题，则通过empCd获取社员信息
				Empdata emp = empdataService.getEmp(empForm.getEmpCd());
				//a如果社员信息不为空则显示提示信息，返回添加页面
				if (emp != null) {
					model.addAttribute("message", messageSource.getMessage("AddEmp.error", null, locale));
					return ("AddEmp");
				} else {
					// l将社员信息输入数据库并跳转到信息一览页面
					empdataInsertService.empdataInsert(empForm);
					return ("redirect:/emplist");
				}
			}
	  }
}

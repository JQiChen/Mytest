package com.cjq.controller;

import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.cjq.bean.Empdata;
import com.cjq.bean.Gender;
import com.cjq.bean.Nationality;
import com.cjq.form.EmpForm;
import com.cjq.service.EmpdataService;
import com.cjq.service.GenderService;
import com.cjq.service.NationalityService;


/**
/**
 * @author CJQ
 * 2022/04/23
 * @version 1.0
 * 描述：更新社员信息的的控制类 
 */
 
@Controller
public class UpdateEmpController {
	/*
	 * 定义成员属性
	 * @Autowired：完成自动注入
	 */
	@Autowired
 private EmpdataService empdataService;
	@Autowired
private NationalityService 	nationalityService;
	@Autowired
 private GenderService genderService;
	@Autowired
	HttpSession session;
	 /*
	    * 方法名：UpdateEmp
	    * 范围修饰符：public
	    * 返回值：String 需要访问的web资源地址
	    * 参数： @RequestParam(value="empCd")String empCd 接收参数值empCd
	    * @ModelAttribute("empForm")  对应前端empForm对象 EmpDataForm empForm：  接受前端表单数据，并带有数据格式验证 

	    *描述：为更新页面提供数据
	    */
 @GetMapping("/UpdateEmp")
 public String UpdataEmp(@RequestParam(value="empCd")String empCd, @ModelAttribute("form") Empdata empdata) {
	 //l通过社员番号获得社员信息
	 Empdata emp=empdataService.getEmp(empCd);
	 //l传值
	 empdata.setEmpCd(emp.getEmpCd());
	 empdata.setName(emp.getName());
	 empdata.setBirthday(emp.getBirthday().toString());
	 empdata.setNationalityCd(emp.getNationalityCd());
	 empdata.setGenderCd(emp.getGenderCd());
	 //l获取国籍数据
	 List<Nationality> nationalityList=nationalityService.getNationalityList();
	 session.setAttribute("nationalityList", nationalityList);	
	 //l获取性别数据
	 List<Gender> genderList=genderService.getGenderList();
	 session.setAttribute("genderList", genderList);
	 return "/UpdateEmp";
 }
 /*
  * 方法名：changeEmp
  * 范围修饰符：public
  * 返回值：String 需要访问的web资源地址
  * 参数： @ModelAttribute("empForm")  对应前端empForm对象 EmpDataForm empForm：  接受前端表单数据，并带有数据格式验证
  *   BindingResult result：指示存储验证结果的对象应如何存储和检索验证结果
*  Model model：  以键值对形式存储对象，传递给前端
*  Locale locale：国际化的使用
  *描述：将更新的社员信息传给页面
  */
	@PostMapping("/UpdateEmp")
	public String changeEmp(@ModelAttribute("form")  @Valid EmpForm empForm, BindingResult result,
	@RequestParam(value="empCd") String empCd, Model model) {		
		if (result.hasErrors()) {
			//a将错误信息添加到集合里
			List<ObjectError> errorList = result.getAllErrors();
			model.addAttribute("errorList", errorList);
			return "UpdateEmp";
		}else {		
			empdataService.updateEmp(empForm);
			return "redirect:/emplist";
		}
}
}

package com.cjq.controller;
import java.util.List;
import java.util.Locale;



import javax.annotation.Resource;
import javax.validation.Valid;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;



import com.cjq.form.UserForm;
import com.cjq.service.LoginUserService;
/**
 * 控制类接受页面请求的数据，调用服务层类的方法，并将具体数据响应给页面
 * 
 * 1s层下所有类的含义
 * 2s代码逐行添加注释
 * 3s总结访问流程
 */
import com.cjq.service.UserServiceImp;



@Controller//告诉spring当前类是需要管理的控制类

public class LoginUserController {
	@Resource//@Autowired（引用service层并通过注解完成自动注入）
    private LoginUserService loginUserService;
	@Resource
	private UserServiceImp userServiceImp;//
	
	@GetMapping("/login")
	//f定义一个返回值是字符串的方法login，返回字符串是返回的访问地址
	public String login(@ModelAttribute("form") UserForm form,Model model) {
		return ("/login");
	}
	@PostMapping("/auth")
	//a定义一个方法auth，
	//@ModelAttribute：将数据添加到Model对象中，@Valid 注解通常用于对象属性字段的规则检测
	public String auth(@ModelAttribute("form")@Valid UserForm userForm,
			BindingResult result,Model model,Locale locale) {
		String url=null;
	//a做错误信息的判断
		//l判定本次访问之前是否已经有错误信息

		if(result.hasErrors()) {
			//a将错误信息添加到集合里
			List<ObjectError> errorList=result.getAllErrors();
			model.addAttribute("errorList", errorList);//传递数据
			url="/login";
			return url;
		}
		//q调用userServiceImp的方法getResult，用集合来接收
		List<String> errorlist=userServiceImp.getResult(userForm,locale);
		//a如果集合里数据不为0，则返回到login页面，否则返回页面success
		if(!(errorlist.size()==0)) {
			model.addAttribute("message", errorlist.get(0));
			url="login";
			return url;
		}else {
			url="redirect:/emplist";
		
				return url;
		}		
		
	}
}

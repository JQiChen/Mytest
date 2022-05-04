package com.cjq.serviceTest;

import java.util.ArrayList;
import java.util.Locale;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;

import com.cjq.CompanysystemApplicationTests;
import com.cjq.form.UserForm;
import com.cjq.service.UserService;

public class UserServiceTest extends CompanysystemApplicationTests{
	@Autowired
  private UserService userService;
	@Autowired
  private MessageSource messageSource;
	private Locale locale=Locale.getDefault();
	
	//Id和密码正确
	@Test
	public void testGetResult1() throws Exception{
		UserForm loginForm=new UserForm();
		loginForm.setAccountId("111@abc.com");
		loginForm.setPassword("000001");
		ArrayList<String> errors=null;
		errors=userService.getResult(loginForm, locale);
		Assertions.assertEquals(0, errors.size());
	}
	//ID不存在
	@Test
	public void testGetResult2() throws Exception{
		UserForm loginForm=new UserForm();
		loginForm.setAccountId("111@aaa.com");
		loginForm.setPassword("000001");
		ArrayList<String> errors=null;
		errors=userService.getResult(loginForm, locale);
		//a期待的结果是显示错误信息
		Assertions.assertEquals(messageSource.getMessage("login.message.accountId.error", null, locale), 
				errors.get(0));
	}
	//1密码不正确
	@Test
	public void testGetResult3() throws Exception{
		UserForm loginForm=new UserForm();
		loginForm.setAccountId("111@abc.com");
		loginForm.setPassword("123456");
		ArrayList<String> errors=null;
		errors=userService.getResult(loginForm, locale);
		//a期待的结果是显示错误信息
		Assertions.assertEquals(messageSource.getMessage("login.message.password.error", null, locale), 
				errors.get(0));
	}
}

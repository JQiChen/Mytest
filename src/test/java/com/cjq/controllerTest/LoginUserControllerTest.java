package com.cjq.controllerTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.validation.ObjectError;

import com.cjq.CompanysystemApplicationTests;
import com.cjq.controller.LoginUserController;

public class LoginUserControllerTest extends CompanysystemApplicationTests {
	@Autowired
	@InjectMocks
	private LoginUserController  loginUserController;
	@Autowired
	private MessageSource messageSource;
	private Locale locale=Locale.getDefault();
	
	MockMvc mockMvc;//模拟客户端进行测试
	@BeforeEach
	public void setUp() {
		//a通过spring自动配置controller
		mockMvc = MockMvcBuilders.standaloneSetup(loginUserController).build();
	}
	//ID和密码正确，登陆成功
	@Test
	public void testLoginSuccess() throws Exception {
		//i构造请求 模拟客户端
		MockHttpServletRequestBuilder getRequest = MockMvcRequestBuilders.post("/auth")
				.param("accountId", "111@abc.com")
				.param("password", "000001");
		
		ResultActions results = mockMvc.perform(getRequest);//执行请求
		results.andDo(print());//打印结果
		//1期待结果为跳转到success页面
		results.andExpect(view().name("success"));
		//1没有任何错误
		results.andExpect(model().errorCount(0));
	}
	//ID输入为空
	@Test
	public void testAccountIdIsEmpty() throws Exception {
		MockHttpServletRequestBuilder getRequest = MockMvcRequestBuilders.post("/auth")
				.param("accountId", "")
				.param("password", "000001");
		ResultActions results = mockMvc.perform(getRequest);
		
		results.andDo(print());
		//	期待结果为跳转到login页面
		results.andExpect(view().name("/login"));
		//a有一个错误
		results.andExpect(model().errorCount(1));
		//a取得错误信息的集合
		@SuppressWarnings("unchecked")//抑制单类型警告
		List<ObjectError> errorList = (List<ObjectError>) results.andReturn().getModelAndView().getModel()
				.get("errorList");
		
		//a取得错误信息
		String message = errorList.get(0).getDefaultMessage();
		//2错误信息的值为login.error.accountId.notEmpty
		System.out.println(message);
		assertTrue(message.contains("login.error.accountId.notEmpty"));//contains包含
	}
	//ID输入不正确
	@Test
	public void testAccountIdNotMail() throws Exception {
		
		MockHttpServletRequestBuilder getRequest = MockMvcRequestBuilders.post("/auth")
				.param("accountId", "111")
				.param("password", "000001");
		
		ResultActions results = mockMvc.perform(getRequest);
		
		results.andDo(print());
		//	期待结果为跳转到login页面
		results.andExpect(view().name("/login"));
		//a有一个错误
		results.andExpect(model().errorCount(1));
		
		@SuppressWarnings("unchecked")//抑制单类型警告
		List<ObjectError> errorList = (List<ObjectError>) results.andReturn().getModelAndView().getModel()
				.get("errorList");//取服务层的errorList 
		//a取得错误信息
		String message = errorList.get(0).getDefaultMessage();
		//2错误信息的值为login.error.accountId.notEmpty
		assertTrue(message.contains("login.error.accountId.isEmail"));
	}
	//4密码输入为空
	@Test
	public void testPasswordIsEmpty() throws Exception {

		MockHttpServletRequestBuilder getRequest = MockMvcRequestBuilders.post("/auth")
				.param("accountId", "111@abc.com")
				.param("password", "");
       ResultActions results = mockMvc.perform(getRequest);
       results.andDo(print()); 

		results.andExpect(view().name("/login"));

		results.andExpect(model().errorCount(2));

		@SuppressWarnings("unchecked")
		List<ObjectError> errorList = (List<ObjectError>) results.andReturn().getModelAndView().getModel()
				.get("errorList");		
		List<String> messages = new ArrayList<>();

		for(ObjectError error:errorList) {
			String message = error.getDefaultMessage();
			messages.add(message);
		}
		//dエラーメッセージには"{login.error.password.notEmpty}"を含む
		System.out.println(messages);
		assertTrue(messages.contains("{login.error.password.notEmpty}"));
	}
	
	//5密码不是6位半角英数字
	@Test
	public void testPasswordLength() throws Exception {
		
		MockHttpServletRequestBuilder getRequest = MockMvcRequestBuilders.post("/auth")
				.param("accountId", "111@abc.com")
				.param("password", "0000000");

		ResultActions results = mockMvc.perform(getRequest);

		results.andDo(print());

		results.andExpect(view().name("/login"));

		results.andExpect(model().errorCount(1));

		@SuppressWarnings("unchecked")
		List<ObjectError> errorList = (List<ObjectError>) results.andReturn().getModelAndView().getModel()
				.get("errorList");
		String message = errorList.get(0).getDefaultMessage();
	
		assertTrue(message.contains("login.error.password.length"));
	}
	
	//id不存在
	@Test
	public void testLoginAccountError() throws Exception {
		//
		MockHttpServletRequestBuilder getRequest = MockMvcRequestBuilders.post("/auth")
				.param("accountId", "111@aaaa.com")
				.param("password", "000001");
		//
		getRequest.locale(locale);
		ResultActions results = mockMvc.perform(getRequest);
		//
		results.andDo(print());
	      //
		results.andExpect(view().name("login"));
		//
		results.andExpect(model().errorCount(0));
		//
		
		String message = (String) results.andReturn().getModelAndView().getModel().get("message");
		//
		assertEquals(messageSource.getMessage("login.message.accountId.error", null, locale), message);
	}
  //a密码不正确
	@Test
	public void testLoginPasswordError() throws Exception {

		MockHttpServletRequestBuilder getRequest = MockMvcRequestBuilders.post("/auth")
				.param("accountId", "111@abc.com")
				.param("password", "000002");
		getRequest.locale(locale);
		ResultActions results = mockMvc.perform(getRequest);
		//
		results.andDo(print());
	      //
		results.andExpect(view().name("login"));
		//
		results.andExpect(model().errorCount(0));
		//
		String message = (String) results.andReturn().getModelAndView().getModel().get("message");
		//
		assertEquals(messageSource.getMessage("login.message.password.error", null, locale), message);
	}
}

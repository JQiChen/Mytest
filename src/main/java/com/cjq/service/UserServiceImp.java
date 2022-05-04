package com.cjq.service;
/**
  * @author CJQ
 * 2022/04/25
 * @version 1.0
 * 用来实现UserService接口，引用mybatis所代理的mapper接口，定义业务逻辑方法（获取错误信息和国际化相关得信息）
 * 
 */
import java.util.ArrayList;
import java.util.Locale;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import com.cjq.bean.LoginUser;
import com.cjq.form.UserForm;
import com.cjq.mapper.LoginUserMapper;


@Service("userService")
public class UserServiceImp implements UserService{
  @Resource//自动注入LoginUserMapper,生成该接口的实例对象
  private LoginUserMapper loginUserMapper;//引用mybatis所代理的接口
  @Autowired//自动注入
  private MessageSource messageSource;//处理验证信息和国际化信息
  /*
   * a定义一个方法getResult
   * 参数：UserForm对应form表单，Locale国际化
   * 
   */
  public ArrayList<String> getResult(UserForm userForm,Locale locale){
	 
	  //a调用loginUserMapper中的方法，将查询到的数据赋值给实体类LoginUser
	  LoginUser user=loginUserMapper.loginuser(userForm.getAccountId());
	  //a实例化生成ArrayList集合对象，用来存错误信息和国际化信息
	  ArrayList<String> errorlist=new ArrayList<String>();
	  if(user==null) {
		  //t如果user没有数据，则添加错误信息和国际化相关的对象，将信息添加到集合对象中
		 errorlist.add(messageSource.getMessage("login.message.accountId.error", 
				  null,locale));
		 // errorlist.add("Id不正确");
	  }else if(!user.getPassword().equals(userForm.getPassword())) {
		//t如果数据库的密码和输入的密码不相等，则添加错误信息和国际化相关的对象，将信息添加到集合对象中
		  /*getMessage集合中添加信息的方法
		   * 第一个参数：根据key找到国际化文件中对应的数据
		   * 第二个参数表示key是否对应数组，没有则对应null
		   * 第三个参数：封装的本地化语言环境对象
		   */
		  errorlist.add(messageSource.getMessage("login.message.password.error", 
				  null,locale));
		 // errorlist.add("密码不正确");
		  
	  }
	  return errorlist;//返回集合
  }
}

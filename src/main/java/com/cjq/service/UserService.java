package com.cjq.service;
/**
 * @author CJQ
 * 2022/04/25
 * @version 1.0
 * 描述：封装一个查询所有的接口
 * 拿到通过验证的form信息，和国际化相关的对象
 */

import java.util.ArrayList;
import java.util.Locale;

import com.cjq.form.UserForm;

public interface UserService {
	//
  public ArrayList<String> getResult(UserForm userForm,Locale locale);
}

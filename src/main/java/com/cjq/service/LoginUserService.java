package com.cjq.service;
/**
 ** @author CJQ
 * 2022/04/25
 * @version 1.0
 * 描述：登录功能的接口
 */
import com.cjq.bean.LoginUser;

public interface LoginUserService {
	public LoginUser loginuser(String accountId);
}

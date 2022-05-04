package com.cjq.service;
/**
 * 实现LoginUserService接口
 */
import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.cjq.bean.LoginUser;
import com.cjq.mapper.LoginUserMapper;
@Service
public class LoginUserServiceImp implements LoginUserService{
	@Resource//@Autowired
  private LoginUserMapper loginUserMapper;

@Override
public LoginUser loginuser(String accountId) {
	
	return loginUserMapper.loginuser(accountId);
}
}

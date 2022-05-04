package com.cjq.mapperTest;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import static org.junit.jupiter.api.Assertions.assertEquals;
import com.cjq.CompanysystemApplicationTests;
import com.cjq.bean.LoginUser;
import com.cjq.mapper.LoginUserMapper;

public class LoginUserMapperTest extends CompanysystemApplicationTests{
  @Autowired
  private LoginUserMapper loginUserMapper;
  
  //Id存在
  @Test
  public void testQueryUser1() {
	  LoginUser user=loginUserMapper.loginuser("111@abc.com");
	  assertEquals("111@abc.com",user.getAccountId());
	  assertEquals("000001",user.getPassword());
  }
  @Test
  public void testQueryUser2() {
	  LoginUser user=loginUserMapper.loginuser("1@abc.com");
	  assertEquals(null,user);
  }
}

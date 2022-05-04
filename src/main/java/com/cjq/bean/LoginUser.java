package com.cjq.bean;
/**
 * 对应数据库表，类当中的属性要与表当中的列名完全一致（数据类型也要一致）
 */
import lombok.Getter;
import lombok.Setter;

@Getter//获取
@Setter//设置
public class LoginUser {
	//a根据数据库表中的列名定义成员属性
  private String accountId;
  private String password;
}

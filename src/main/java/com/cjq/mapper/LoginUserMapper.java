package com.cjq.mapper;
/**
 * @author CJQ
 * 2022/04/20
 * @version 1.0
 *  mybatis代理的接口，该接口对应mybatis的映射文件，定义了对数据库的操作方法
 *接口名通常和对应的映射文件名一样
 */

import org.apache.ibatis.annotations.Mapper;

import com.cjq.bean.LoginUser;
@Mapper//告诉spring该接口类的实现类对象交给mybatis底层创建
public interface LoginUserMapper {
	 public LoginUser loginuser(String accountId);
	}



package com.cjq.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cjq.bean.Empdata;
import com.cjq.mapper.EmpdataFindAllMapper;

/**
 * 
* @author CJQ
 * 2022/04/25
 * @version 1.0
 * 描述：查询所有社员信息的服务层接口的实现类
 *
 */
@Service
public class EmpdataFindAllServiceImp implements EmpdataFindAllService{
	 @Autowired
	 private EmpdataFindAllMapper empdataFindAllMapper;
	@Override
	public List<Empdata> empdataFindfAll() {
		
		return empdataFindAllMapper.empdataFindfAll();
	}
   
}

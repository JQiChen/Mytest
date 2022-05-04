package com.cjq.service;
/**
 * @author CJQ
 * 2022/04/25
 * @version 1.0
 * 描述：查询性别的服务层接口的实现类
 */
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cjq.bean.Gender;
import com.cjq.mapper.GenderMapper;

@Service
public class GenderServiceImp implements GenderService{	
	//GenderMapper を注入する
	@Autowired
	private GenderMapper genderMapper;	
	//性別一覧を取得する
	public List<Gender> getGenderList(){
		//GenderMapper.getGenderList()を呼出し、性別一覧を取得する
		return genderMapper.getGenderList();
	}
}

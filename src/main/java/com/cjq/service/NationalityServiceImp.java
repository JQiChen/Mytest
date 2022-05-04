package com.cjq.service;
/**
 ** @author CJQ
 * 2022/04/25
 * @version 1.0
 * 描述：登录功能的接口的实现类
 */
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cjq.bean.Nationality;
import com.cjq.mapper.NationalityMapper;



 // NationalityServiceインターフェースを実装する
@Service
public class NationalityServiceImp implements NationalityService{
	//NationalityMapper を注入する
	@Autowired
	private NationalityMapper  nationalityMapper;
	
	//国籍リストを取得
	public List<Nationality> getNationalityList(){
		//NationalityMapper.getNationalityLisを呼出し、国籍一覧を取得する
		return nationalityMapper.getNationalityList();
	}
}

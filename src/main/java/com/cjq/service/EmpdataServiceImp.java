package com.cjq.service;
/**
 * @author CJQ
 * 2022/04/25
 * @version 1.0
 * 描述：更新，删除，关键字检索社员信息和显示社员详细信息的服务层接口的实现类
 */
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cjq.bean.Empdata;
import com.cjq.form.EmpForm;
import com.cjq.mapper.EmpdataMapper;
@Service
public class EmpdataServiceImp implements EmpdataService{
  @Autowired
  private EmpdataMapper empdataMapper;
	@Override
	public void updateEmp(EmpForm EmpForm) {
		// a更新
		empdataMapper.updateEmp(EmpForm);
		
	}

	@Override
	public void deleteEmp(String empCd) {
		// b删除
		empdataMapper.deleteEmp(empCd);
	}

	@Override
	public List<Empdata> findEmp(String keyword) {
		// c检索
		return empdataMapper.findEmp("%"+keyword+"%");
	}

	@Override
	public Empdata getEmp(String empCd) {
		//d显示社员详细信息
		return empdataMapper.getEmp(empCd);
	}

}

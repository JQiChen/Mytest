package com.cjq.service;
/**
 * @author CJQ
 * 2022/04/25
 * @version 1.0
 * 描述： 添加社员信息的服务层接口的实现类
 */
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cjq.form.EmpForm;
import com.cjq.mapper.EmpdataInsertMapper;
@Service
public class EmpdataInsertServiceImp implements EmpdataInsertService{
	@Autowired
	private EmpdataInsertMapper empdataInsertMapper;
	@Override
	public void empdataInsert(EmpForm empForm) {
		empdataInsertMapper.empdataInsert(empForm);
	}

	

	
}

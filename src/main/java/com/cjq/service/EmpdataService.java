package com.cjq.service;
/**
 * @author CJQ
 * 2022/04/25
 * @version 1.0
 * 描述：更新，删除，关键字检索社员信息和显示社员详细信息的服务层接口
 */
import java.util.List;

import com.cjq.bean.Empdata;
import com.cjq.form.EmpForm;

public interface EmpdataService {
	//a社员信息更新
		public void updateEmp(EmpForm EmpForm);
		//b社员信息删除
		public void deleteEmp(String empCd);
		//c社员信息检索
		public List<Empdata> findEmp(String keyword);
		//d显示社员详细信息
		public Empdata getEmp(String empCd);
}

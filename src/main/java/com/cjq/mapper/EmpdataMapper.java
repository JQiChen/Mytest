package com.cjq.mapper;
/**
 * @author CJQ
 * 2022/04/23
 * @version 1.0
 *  mybatis代理的接口，该接口对应mybatis的映射文件，定义了对数据库的操作方法
 *
 */
 
import java.util.List;

/**
 * 更新，删除，检索
 */
import org.apache.ibatis.annotations.Mapper;

import com.cjq.bean.Empdata;
import com.cjq.form.EmpForm;


@Mapper
public interface EmpdataMapper {
    //a社员信息更新
	public void updateEmp(EmpForm Empform);
	//b社员信息删除
	public void deleteEmp(String empCd);
	//c社员信息检索
	public List<Empdata> findEmp(String keyword);
	//d显示社员详细信息
	public Empdata getEmp(String empCd);
}

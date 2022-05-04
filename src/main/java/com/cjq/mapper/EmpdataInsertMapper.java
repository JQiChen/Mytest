package com.cjq.mapper;

import org.apache.ibatis.annotations.Mapper;


import com.cjq.form.EmpForm;


/**
 * @author CJQ
 * 2022/04/23
 * @version 1.0
 *  mybatis代理的接口，该接口对应mybatis的映射文件，定义了对数据库的操作方法
 *
 */
/*
 * 添加社员信息
 */
@Mapper
public interface EmpdataInsertMapper {
 public void empdataInsert(EmpForm empForm);
}

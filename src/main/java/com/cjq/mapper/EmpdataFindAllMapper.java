package com.cjq.mapper;
/**
 * @author CJQ
 * 2022/04/23
 * @version 1.0
 *  mybatis代理的接口，该接口对应mybatis的映射文件，定义了对数据库的操作方法
 */
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.cjq.bean.Empdata;
//l查询所有，完成社员信息一览画面
@Mapper
public interface EmpdataFindAllMapper {
   public List<Empdata> empdataFindfAll();
}

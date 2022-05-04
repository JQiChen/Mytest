package com.cjq.service;
/**
 * @author CJQ
 * 2022/04/25
 * @version 1.0
 * 描述： 查询所有社员信息的服务层接口
 */
import java.util.List;

import com.cjq.bean.Empdata;
//l查询所有
public interface EmpdataFindAllService {
	public List<Empdata> empdataFindfAll();
}

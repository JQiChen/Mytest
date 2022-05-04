package com.cjq.service;

import java.util.List;
import com.cjq.bean.Nationality;
/**
 * @author CJQ
 * 2022/04/25
 * @version 1.0
 * 描述：查询国籍的服务层接口
 */

public interface  NationalityService {
	public List<Nationality> getNationalityList();	
}

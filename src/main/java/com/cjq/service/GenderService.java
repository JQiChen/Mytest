package com.cjq.service;
/**
 * @author CJQ
 * 2022/04/25
 * @version 1.0
 * 描述：查询性别的服务层接口
 */
import java.util.List;
import com.cjq.bean.Gender;



public interface GenderService {	
		public List<Gender> getGenderList();
}

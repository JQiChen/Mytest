package com.cjq.form;
/**
 * @author CJQ
 * 2022/04/23
 * @version 1.0
 * 描述：对应页面form表单，通常用来存form表单中的数据，并对数据进行验证
 */
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class EmpForm {
	@NotEmpty(message = "{empCode.notEmpty}")
	private String empCd;
	
	@NotEmpty(message = "{empName.notEmpty}")
	@Pattern(regexp = "^[一-龥 ア-ン あ-ん a-z A-Z]*$", message = "{empName.error}")
	private String name;

	@NotEmpty(message = "{empBirthday.notEmpty}")
	private String birthday;
	
	@NotEmpty(message = "{nationality.notEmpty}")
	private String nationalityCd;
	
	@NotEmpty(message = "{empGender.notEmpty}")
	private String genderCd;
	
}

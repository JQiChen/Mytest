package com.cjq.form;
/**
 * @author CJQ
 * 2022/04/20
 * @version 1.0
 * 描述：对应页面form表单，通常用来存form表单中的数据，并对数据进行验证
 */
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserForm {
	//message里的是引用国际化资源文件（i8n）里的key
	@NotEmpty(message="{login.error.accountId.notEmpty}")//accountId不能为空
	@Email(message="{login.error.accountId.isEmail}")//为email格式
    private String accountId;
	
	@NotEmpty(message="{login.error.password.notEmpty}")//password不能为空
	@Size(min=6,max=6,message="{login.error.password.length}")//长度为6
    private String password;
}

package com.cjq.config;
/**
 * 管理springboot下的国际化信息和验证信息
 */
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

@Configuration
public class MessageConfig {
	    //MessageSourceを注入する
		@Autowired
		private MessageSource messageSource;
		
		@Bean
		//入力検証のエラーメッセージソースとメッセージソースは同一MessageSourceに指定する
		public LocalValidatorFactoryBean validator() {			
			LocalValidatorFactoryBean localValidatorFactoryBean = new LocalValidatorFactoryBean();
			//入力検証のエラーメッセージソースとメッセージソースは同一MessageSourceに指定する
			localValidatorFactoryBean.setValidationMessageSource(messageSource);			
			return localValidatorFactoryBean;
		}
}

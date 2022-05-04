package com.cjq.bean;

import lombok.Getter;
import lombok.Setter;

/**
 * 
 * @author CJQ
 * 根据数据库的表epmdata封装实体类
 *
 */

@Getter
@Setter
public class Empdata {
   private String empCd;
   private String name;
   private String birthday;
   private String nationalityCd;
   private String genderCd;
   private String nationalityName;
   private String genderName;
   

}

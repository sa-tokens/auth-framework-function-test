package com.pj.mockdao;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * User 实体类 
 * 
 * @author click33
 * @since 2024-8-6
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SysUser implements Serializable {

	/**
	 * 用户id
	 */
	private long id;
	
	/**
	 * 用户名称
	 */
	private String username;

	/**
	 * 用户名称
	 */
	private String password;

	/**
	 * 用户年龄
	 */
	private int age;

}

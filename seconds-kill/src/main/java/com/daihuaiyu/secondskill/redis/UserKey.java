package com.daihuaiyu.secondskill.redis;

/**
 * 用户key
 *
 * @author daihuaiyu
 * @create: 2021-05-11 14:24
 **/
public class UserKey extends BasePrefix{

	private UserKey(String prefix) {
		super(prefix);
	}
	public static UserKey getById = new UserKey("id");
	public static UserKey getByName = new UserKey("name");
}

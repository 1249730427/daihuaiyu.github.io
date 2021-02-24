package dao;

import annotation.Select;

/**
 * UserDAO
 *
 * @author daihuaiyu
 * @create: 2021-02-24 14:18
 **/
public interface IUserDAO {

    @Select("select userName from user where id = #{uId}")
    String queryUserInfo(String uId);
}


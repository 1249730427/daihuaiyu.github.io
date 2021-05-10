package com.daihuaiyu.secondskill.dao;

import com.daihuaiyu.secondskill.domain.MiaoshaUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
 * @Interface: MiaoshaUserMapper
 * @Author: hydai
 * @Date: 2021/5/10 22:51
 * @Description:
 */
@Mapper
public interface MiaoshaUserMapper {

    @Select(value = "select * from  miaosha_user where id = #{id}")
    MiaoshaUser getUserById(String mobile);
}

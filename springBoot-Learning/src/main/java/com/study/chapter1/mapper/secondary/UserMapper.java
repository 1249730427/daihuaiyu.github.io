package com.study.chapter1.mapper.secondary;

import com.study.chapter1.entity.User;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface UserMapper {

    @Select("select * from user where user_name = #{userName}")
    List<User> selectByUserName(@Param("userName") String userName);
}

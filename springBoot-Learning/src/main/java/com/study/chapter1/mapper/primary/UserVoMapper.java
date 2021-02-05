package com.study.chapter1.mapper.primary;

import com.study.chapter1.domain.UserVo;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface UserVoMapper {

    @Select("SELECT * FROM USER_VO WHERE USER_NAME = #{userName}")
    List<UserVo> selectByName(@Param("userName") String userName);

}

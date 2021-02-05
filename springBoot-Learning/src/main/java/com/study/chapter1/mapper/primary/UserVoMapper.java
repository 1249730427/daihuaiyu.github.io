package com.study.chapter1.mapper.primary;

import com.study.chapter1.domain.UserVo;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

@Component
public interface UserVoMapper {

    @Select("SELECT * FROM USER_VO WHERE NAME = #{name}")
     UserVo selectByName(String userName);

}

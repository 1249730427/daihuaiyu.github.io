package com.study.chapter1.service;

import com.study.chapter1.domain.UserVo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserVo,Long> {

}

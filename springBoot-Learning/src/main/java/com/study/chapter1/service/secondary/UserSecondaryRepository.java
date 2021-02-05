package com.study.chapter1.service.secondary;

import com.study.chapter1.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserSecondaryRepository extends JpaRepository<User,Long> {
}

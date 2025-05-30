package com.commerce.config.repository;

import com.commerce.config.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long>{
User findByEmail(String email);


}

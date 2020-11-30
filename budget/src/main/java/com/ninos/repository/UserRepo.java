package com.ninos.repository;

import com.ninos.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User,Long> {

    User findUserByUsername(String username);

}

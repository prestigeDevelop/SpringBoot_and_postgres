package com.myjdbc.jdbcdata.pg.repository;

import com.myjdbc.jdbcdata.pg.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    //Optional<User> findByUsername(String username);
}

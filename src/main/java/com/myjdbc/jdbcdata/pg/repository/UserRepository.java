package com.myjdbc.jdbcdata.pg.repository;

import com.myjdbc.jdbcdata.pg.entity.User;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
@Qualifier("jpaUserRepository")
public interface UserRepository extends JpaRepository<User, Long> {

    //Optional<User> findByUsername(String username);
}

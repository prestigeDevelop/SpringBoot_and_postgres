package com.myjdbc.jdbcdata.pg.repository;

import com.myjdbc.jdbcdata.pg.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/*
write a test for the UserRepository class
 */
@SpringBootTest
@ActiveProfiles("test")
class UserRepositoryTest {
    @Autowired
    private UserRepository userRepository;
    private User teseUser;

    @org.junit.jupiter.api.Test
    void findAll() {
        List<User> users = userRepository.findAll();
        assertNotNull(users);
        assertTrue(users.size() > 0);
    }

    @org.junit.jupiter.api.Test
    void findById() {
        User user = userRepository.findById(1).orElse(null);
        assertEquals("john_doe", user.getUsername());

    }

    @org.junit.jupiter.api.Test
    void save() {
        User user = new User();
        user.setUsername("test_user");
        user.setEmail("test@email.com");
        user.setFirstName("test");
        user.setLastName("user");
        user.setPhoneNumber("1234567890");
        user.setPasswordHash("password");
        teseUser = userRepository.save(user);
        // Assertions
        assertNotNull(teseUser);
        assertEquals("test_user", teseUser.getUsername());
        userRepository.delete(teseUser);
    }

//        @org.junit.jupiter.api.Test
//        void delete() {
//            teseUser = userRepository.findById(22).orElse(null);
//        userRepository.delete(teseUser);
//        }


}
package com.myjdbc.jdbcdata.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
public class RedisTestController {

    @Autowired(required = false)
    private RedisConnectionFactory redisConnectionFactory;

    @GetMapping("/test-redis")
    public Map<String, Object> testRedis() {
        Map<String, Object> response = new HashMap<>();

        if (redisConnectionFactory == null) {
            response.put("error", "RedisConnectionFactory not autowired - check Redis configuration");
            return response;
        }

        try {
            RedisConnection connection = redisConnectionFactory.getConnection();
            String pingResponse = connection.ping();
            connection.close();
            if (pingResponse == null || !pingResponse.equalsIgnoreCase("PONG")) {
                response.put("error", "Unexpected ping response: " + pingResponse);
                return response;
            }
            response.put("status", "success");
            response.put("pingResponse", pingResponse);
            response.put("connectionFactoryClass", redisConnectionFactory.getClass().getName());
        } catch (Exception e) {
            response.put("error", e.getMessage());
            response.put("stackTrace", Arrays.stream(e.getStackTrace())
                    .limit(5)
                    .map(StackTraceElement::toString)
                    .collect(Collectors.toList()));
        }

        return response;
    }
}

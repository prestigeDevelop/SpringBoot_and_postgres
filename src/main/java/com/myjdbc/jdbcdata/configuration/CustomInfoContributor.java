package com.myjdbc.jdbcdata.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.actuate.info.Info;
import org.springframework.boot.actuate.info.InfoContributor;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;

import java.lang.management.ManagementFactory;
import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

@Component
public class CustomInfoContributor implements InfoContributor {
    @Autowired
    private ConfigurableApplicationContext applicationContext;
    @Value("${info.app.name:My Default App}")
    private String appName;

    @Value("${info.app.version:1.0.0}")
    private String appVersion;

    @Value("${info.app.description:Default Description}")
    private String appDescription;

    @Override
    public void contribute(Info.Builder builder) {
        Map<String, Object> appInfo = new HashMap<>();
        appInfo.put("name", appName);
        appInfo.put("version", appVersion);
        appInfo.put("description", appDescription);
        appInfo.put("status", getApplicationStatus());
        appInfo.put("timestamp", Instant.now().toString());
        appInfo.put("isActive", applicationContext.isActive());
        appInfo.put("beanCount", applicationContext.getBeanDefinitionCount());
        builder.withDetail("application", appInfo)
                .withDetail("environment", System.getenv("SPRING_PROFILES_ACTIVE"));
    }

    private String getApplicationStatus() {
        if (applicationContext.isActive()) {
            return "RUNNING";
        } else {
            return "STARTING_OR_STOPPED";
        }
    }

    private String getUptime() {
        try {
            long uptime = ManagementFactory.getRuntimeMXBean().getUptime();
            long seconds = uptime / 1000;
            long minutes = seconds / 60;
            long hours = minutes / 60;

            return String.format("%dh %dm %ds", hours, minutes % 60, seconds % 60);
        } catch (Exception e) {
            return "Unknown";
        }
    }
}
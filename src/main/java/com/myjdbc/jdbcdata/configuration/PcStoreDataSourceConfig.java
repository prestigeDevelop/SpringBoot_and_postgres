package com.myjdbc.jdbcdata.configuration;

import jakarta.persistence.EntityManagerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        basePackages = "com.myjdbc.jdbcdata.pcstore.repository",
        entityManagerFactoryRef = "pcStoreEntityManagerFactory",
        transactionManagerRef = "pcStoreTransactionManager"
)
public class PcStoreDataSourceConfig {

    @Primary
    @Bean(name = "pcStoreDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.pc-store")
    public DataSource dataSource() {
        return DataSourceBuilder.create().build();
    }

    @Primary
    @Bean(name = "pcStoreEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(
            EntityManagerFactoryBuilder builder,
            @Qualifier("pcStoreDataSource") DataSource dataSource
    ) {
        return builder
                .dataSource(dataSource)
                .packages("com.myjdbc.jdbcdata.pcstore.entity")
                .persistenceUnit("pcStore")
                .build();
    }

    @Primary
    @Bean(name = "pcStoreTransactionManager")
    public PlatformTransactionManager transactionManager(
            @Qualifier("pcStoreEntityManagerFactory") EntityManagerFactory entityManagerFactory
    ) {
        return new JpaTransactionManager(entityManagerFactory);
    }
}


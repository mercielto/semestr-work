package com.example.semestrovkacourse2sem2oris.configuration;

import liquibase.integration.spring.SpringLiquibase;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;

// TODO: убрать, использовать boot
@Configuration
public class LiquibaseConfig {

    @Bean
    @Primary
    @ConfigurationProperties(prefix = "spring.datasource")
    public DataSourceProperties getPrimaryDatasourceProperties() {
        return new DataSourceProperties();
    }

    @Bean
    @Primary
    public DataSource primaryDataSource() {
        return getPrimaryDatasourceProperties()
                .initializeDataSourceBuilder()
                .build();
    }

    @Bean(name = "liquibase")
    public SpringLiquibase liquibase(@Value("${spring.liquibase.change-log}") String changeLog) {
        SpringLiquibase liquibase = new SpringLiquibase();
        liquibase.setDataSource(primaryDataSource());
        liquibase.setChangeLog(changeLog);
        return liquibase;
    }
}

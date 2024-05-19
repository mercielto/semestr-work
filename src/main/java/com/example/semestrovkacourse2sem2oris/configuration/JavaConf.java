package com.example.semestrovkacourse2sem2oris.configuration;

import com.example.semestrovkacourse2sem2oris.util.LinkGenerator;
import com.example.semestrovkacourse2sem2oris.util.LinkGeneratorImpl;
import com.example.semestrovkacourse2sem2oris.util.UserParamsChecker;
import com.example.semestrovkacourse2sem2oris.util.UserParamsCheckerImpl;
import jakarta.persistence.EntityManagerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;
import org.springframework.web.servlet.view.freemarker.FreeMarkerViewResolver;

@Configuration
@ComponentScan("com.example.semestrovkacourse2sem2oris")
@EnableJpaRepositories(basePackages = "com.example.semestrovkacourse2sem2oris.repository")
public class JavaConf implements WebMvcConfigurer {

    @Bean
    public PlatformTransactionManager transactionManager(EntityManagerFactory entityManagerFactory){
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactory);
        return transactionManager;
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {

        registry.addResourceHandler("/static/styles/**")
                .addResourceLocations("classpath:/static/styles/");
        registry.addResourceHandler("/static/scripts/**")
                .addResourceLocations("classpath:/static/scripts/");
        registry.addResourceHandler("/static/images/**")
                .addResourceLocations("classpath:/static/images/");
    }

    @Bean
    public UserParamsChecker userParamsChecker() {
        return new UserParamsCheckerImpl();
    }
}
package com.evghenii.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

@Configuration
@ComponentScan(basePackages = "com.evghenii.*")
@EnableTransactionManagement
@EnableWebMvc
@EnableScheduling
@EnableJpaRepositories(basePackages = "com.evghenii.repository")
@Import({EmailConfig.class, ValidatorConfig.class})
public class ConfigApp implements WebMvcConfigurer {

    @Bean
    public PlatformTransactionManager transactionManager(EntityManagerFactory factory) {
        JpaTransactionManager transactionManager = new JpaTransactionManager();

        transactionManager.setEntityManagerFactory(factory);
        transactionManager.setDataSource(dataSource());

        return transactionManager;
    }

    @Bean
    public PersistenceExceptionTranslationPostProcessor postProcessor() {
        return new PersistenceExceptionTranslationPostProcessor();
    }

    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost:3306/board?serverTimezone=Europe/Berlin");
        dataSource.setUsername("root");
        dataSource.setPassword("root");

        return dataSource;
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(DataSource dataSource, JpaVendorAdapter adapter) {
        LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();

        factory.setDataSource(dataSource);
        factory.setJpaVendorAdapter(adapter);
        factory.setPackagesToScan("com.evghenii.domain");

        return factory;
    }


    @Bean
    public JpaVendorAdapter jpaVendorAdapter() {
        HibernateJpaVendorAdapter adapter = new HibernateJpaVendorAdapter();

        adapter.setDatabase(Database.MYSQL);
        adapter.setShowSql(true);
        adapter.setDatabasePlatform("org.hibernate.dialect.MySQL8Dialect");
        adapter.setGenerateDdl(true);

        return adapter;
    }

}

package com.niufennan.jtodos.config;

import org.eclipse.persistence.platform.database.MySQLPlatform;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.EclipseLinkJpaVendorAdapter;

import javax.sql.DataSource;

@Configuration
@EnableJpaRepositories(basePackages = "com.niufennan.jtodos.persistence")
public class DataBaseConfig {

    @Bean
    public DataSource dataSource(){
        DriverManagerDataSource dataSource=new DriverManagerDataSource();
        dataSource.setDriverClassName(com.mysql.cj.jdbc.Driver.class.getName());
        dataSource.setUrl("jdbc:mysql://localhost:3306/jtodos?serverTimezone=GMT%2b8");
        dataSource.setUsername("root");
        dataSource.setPassword("1234");
        return dataSource;
    }

    @Bean
    public JdbcTemplate jdbcTemplate(DataSource dataSource){
        return  new JdbcTemplate(dataSource);
    }


    @Bean
    public JpaVendorAdapter jpaVendorAdapter(){
        EclipseLinkJpaVendorAdapter adapter=new EclipseLinkJpaVendorAdapter();
        adapter.setDatabase(Database.MYSQL);
        adapter.setShowSql(true);
        adapter.setGenerateDdl(false);
        adapter.setDatabasePlatform(MySQLPlatform.class.getName());
        return adapter;
    }

    @Bean
    public JpaTransactionManager transactionManager(DataSource dataSource){
        JpaTransactionManager transactionManager=new JpaTransactionManager();
        transactionManager.setDataSource(dataSource);
        return transactionManager;
    }
    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(DataSource dataSource,
                                                                       JpaVendorAdapter jpaVendorAdapter){
        LocalContainerEntityManagerFactoryBean lcemf=new LocalContainerEntityManagerFactoryBean();
        lcemf.setLoadTimeWeaver(new ExtInstrumentationLoadTimeWeaver( ));
        lcemf.setDataSource(dataSource);
        lcemf.setJpaVendorAdapter(jpaVendorAdapter);
        lcemf.setPackagesToScan("com.niufennan.jtodos.models");
        return lcemf;
    }


}

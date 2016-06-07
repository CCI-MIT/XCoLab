package org.xcolab.service.files.persistence;

import com.zaxxer.hikari.HikariDataSource;

import org.jooq.impl.DataSourceConnectionProvider;
import org.jooq.impl.DefaultDSLContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.LazyConnectionDataSourceProxy;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.xcolab.service.utils.db.DataSourceUtil;
import org.xcolab.service.utils.db.jooq.config.JooqConfiguration;

@Configuration

@EnableTransactionManagement
@PropertySource({"classpath:application.properties", "file:${user.home}/.xcolab.application.properties"})
public class PersistenceContext {

    @Autowired
    private Environment env;

    @Bean(destroyMethod = "shutdown")
    public HikariDataSource hikariDataSource() {
        return DataSourceUtil.getConfiguredDataSource(
                env.getRequiredProperty("db.driver"),
                env.getRequiredProperty("db.url"),
                env.getRequiredProperty("db.username"),
                env.getRequiredProperty("db.password"));
    }

//    @Bean
//    public DataSourceTransactionManager transactionManager() {
//        return new DataSourceTransactionManager(new LazyConnectionDataSourceProxy(hikariDataSource()));
//    }
//
//    @Bean
//    public TransactionAwareDataSourceProxy transactionAwareDataSource() {
//        return new TransactionAwareDataSourceProxy(new LazyConnectionDataSourceProxy(hikariDataSource()));
//    }

    @Bean
    public DefaultDSLContext dsl() {
        return new DefaultDSLContext(new JooqConfiguration(
                new DataSourceConnectionProvider(
                        new LazyConnectionDataSourceProxy(hikariDataSource())),
                env.getRequiredProperty("jooq.sql.dialect")));
    }
}

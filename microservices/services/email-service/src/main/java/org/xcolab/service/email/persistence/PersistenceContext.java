package org.xcolab.service.email.persistence;

import com.zaxxer.hikari.HikariDataSource;
import org.jooq.impl.DataSourceConnectionProvider;
import org.jooq.impl.DefaultDSLContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.LazyConnectionDataSourceProxy;


import org.xcolab.service.utils.db.DataSourceUtil;
import org.xcolab.service.utils.db.jooq.config.JooqConfiguration;

import javax.sql.DataSource;

@Configuration
//@EnableTransactionManagement
@PropertySource("file:${user.home}/.xcolab.application.properties")
public class PersistenceContext {

    @Autowired
    private Environment env;

    @Bean(destroyMethod = "close")
    public HikariDataSource hikariDataSource() {
        return DataSourceUtil.getConfiguredDataSource(env);
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
    @Autowired
    public DefaultDSLContext dsl(DataSource dataSource) {
        return new DefaultDSLContext(new JooqConfiguration(
                new DataSourceConnectionProvider(new LazyConnectionDataSourceProxy(dataSource)),
                env.getRequiredProperty("jooq.sql.dialect")));
    }
}

package org.xcolab.persistence;

import com.jolbox.bonecp.BoneCPDataSource;
import org.jooq.SQLDialect;
import org.jooq.impl.DataSourceConnectionProvider;
import org.jooq.impl.DefaultConfiguration;
import org.jooq.impl.DefaultDSLContext;
import org.jooq.impl.DefaultExecuteListenerProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.LazyConnectionDataSourceProxy;
import org.springframework.jdbc.datasource.TransactionAwareDataSourceProxy;
import org.springframework.jdbc.datasource.init.DataSourceInitializer;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.xcolab.exceptions.JOOQToSpringExceptionTransformer;

import javax.sql.DataSource;

@Configuration

@EnableTransactionManagement
@PropertySource("classpath:application.properties")
public class PersistenceContext {
 
    @Autowired
    private Environment env;

    public DataSource dataSource() {
        BoneCPDataSource dataSource = new BoneCPDataSource();
 
        dataSource.setDriverClass(env.getRequiredProperty("db.driver"));
        dataSource.setJdbcUrl(env.getRequiredProperty("db.url"));
        dataSource.setUsername(env.getRequiredProperty("db.username"));
        dataSource.setPassword(env.getRequiredProperty("db.password"));
 
        return dataSource;
    }


    public LazyConnectionDataSourceProxy lazyConnectionDataSource() {
        return new LazyConnectionDataSourceProxy(dataSource());
    }
 
    @Bean
    public TransactionAwareDataSourceProxy transactionAwareDataSource() {
        return new TransactionAwareDataSourceProxy(lazyConnectionDataSource());
    }

 
    @Bean
    public DataSourceTransactionManager transactionManager() {
        return new DataSourceTransactionManager(lazyConnectionDataSource());
    }
 
    @Bean
    public DataSourceConnectionProvider connectionProvider() {
        return new DataSourceConnectionProvider(transactionAwareDataSource());
    }
 
    @Bean
    public JOOQToSpringExceptionTransformer jooqToSpringExceptionTransformer() {
        return new JOOQToSpringExceptionTransformer();
    }
 
    @Bean
    public DefaultConfiguration configuration() {
        DefaultConfiguration jooqConfiguration = new DefaultConfiguration();
 
        jooqConfiguration.set(connectionProvider());
        jooqConfiguration.set(new DefaultExecuteListenerProvider(
            jooqToSpringExceptionTransformer()
        ));
 
        String sqlDialectName = env.getRequiredProperty("jooq.sql.dialect");
        SQLDialect dialect = SQLDialect.valueOf(sqlDialectName);
        jooqConfiguration.set(dialect);
 
        return jooqConfiguration;
    }
 
    @Bean
    public DefaultDSLContext dsl() {
        return new DefaultDSLContext(configuration());
    }
 
    @Bean
    public DataSourceInitializer dataSourceInitializer() {
        DataSourceInitializer initializer = new DataSourceInitializer();
        initializer.setDataSource(dataSource());
        
        /*
        ResourceDatabasePopulator populator = new ResourceDatabasePopulator();
        populator.addScript(
                new ClassPathResource(env.getRequiredProperty("db.schema.script"))
        );
 
        initializer.setDatabasePopulator(populator);
        */
        return initializer;
    }
}

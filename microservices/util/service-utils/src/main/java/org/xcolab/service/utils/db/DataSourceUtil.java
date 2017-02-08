package org.xcolab.service.utils.db;

import com.zaxxer.hikari.HikariDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.env.PropertyResolver;

public final class DataSourceUtil {

    private static final Logger log = LoggerFactory.getLogger(DataSourceUtil.class);

    /* Pool configuration constants  */
    private static final int MAX_POOL_SIZE = 6;
    private static final int MIN_IDLE = 2;
    private static final int IDLE_TIMEOUT_MS = 120_000;
    private static final int PREPARED_STATEMENT_CACHE_SIZE = 250;
    private static final int PREPARED_STATEMENT_CACHE_SQL_LIMIT = 1024;

    /* Property names for data source configuration */
    private static final String DB_DRIVER_PROPERTY_NAME = "db.driver";
    private static final String DB_URL_BASE_PROPERTY_NAME = "db.url.base";
    private static final String DB_URL_PARAMS_PROPERTY_NAME = "db.url.params";
    private static final String DB_USERNAME_PROPERTY_NAME = "db.username";
    private static final String DB_PASSWORD_PROPERTY_NAME = "db.password";
    private static final String DB_SCHEMA_PROPERTY_NAME = "db.schema";

    private DataSourceUtil() {
    }

    public static HikariDataSource getConfiguredDataSource(PropertyResolver propertyResolver) {
        return getConfiguredDataSource(propertyResolver, DB_SCHEMA_PROPERTY_NAME);
    }

    public static HikariDataSource getConfiguredDataSource(PropertyResolver propertyResolver,
            String schemaPropertyKey) {

        final String driverClassName = propertyResolver.getRequiredProperty(DB_DRIVER_PROPERTY_NAME);
        final String databaseSchema = propertyResolver.getRequiredProperty(schemaPropertyKey);
        final String databaseUrlBase = propertyResolver.getRequiredProperty(DB_URL_BASE_PROPERTY_NAME);
        final String databaseUrlParams = propertyResolver.getRequiredProperty(DB_URL_PARAMS_PROPERTY_NAME);
        final String databaseUsername = propertyResolver.getRequiredProperty(DB_USERNAME_PROPERTY_NAME);
        final String databasePassword = propertyResolver.getRequiredProperty(DB_PASSWORD_PROPERTY_NAME);

        return getConfiguredDataSource(driverClassName, databaseSchema, databaseUrlBase,
                databaseUrlParams, databaseUsername, databasePassword);
    }

    private static HikariDataSource getConfiguredDataSource(String driverClassName,
            String databaseSchema, String databaseUrlBase, String databaseUrlParams,
            String databaseUsername, String databasePassword) {

        log.info("Initializing HikariDataSource for user {} on {}", databaseUsername,
                databaseUrlBase);

        final HikariDataSource dataSource = new HikariDataSource();

        dataSource.setDriverClassName(driverClassName);
        dataSource.setJdbcUrl(databaseUrlBase + "/" + databaseSchema + "?" + databaseUrlParams);
        dataSource.setUsername(databaseUsername);
        dataSource.setPassword(databasePassword);

        dataSource.setMaximumPoolSize(MAX_POOL_SIZE);
        dataSource.setMinimumIdle(MIN_IDLE);
        dataSource.setIdleTimeout(IDLE_TIMEOUT_MS);
        dataSource.setRegisterMbeans(false);

        //mysql optimizations
        dataSource.addDataSourceProperty("cachePrepStmts", true);
        dataSource.addDataSourceProperty("prepStmtCacheSize", PREPARED_STATEMENT_CACHE_SIZE);
        dataSource.addDataSourceProperty("prepStmtCacheSqlLimit",
                PREPARED_STATEMENT_CACHE_SQL_LIMIT);

        return dataSource;
    }
}

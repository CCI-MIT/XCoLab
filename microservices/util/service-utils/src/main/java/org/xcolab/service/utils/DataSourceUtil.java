package org.xcolab.service.utils;

import com.zaxxer.hikari.HikariDataSource;

import javax.sql.DataSource;

public final class DataSourceUtil {

    public static final int MAX_POOL_SIZE = 20;
    public static final int MIN_IDLE = 5;
    public static final int IDLE_TIMEOUT_MS = 120000;
    public static final int PREPARED_STATEMENT_CACHE_SIZE = 250;
    public static final int PREPARED_STATEMENT_CACHE_SQL_LIMIT = 1024;

    private DataSourceUtil() {
    }

    public static DataSource getConfiguredDataSource(String driverClassName, String databaseUrl,
            String databaseUsername, String databasePassword) {

        final HikariDataSource dataSource = new HikariDataSource();

        dataSource.setDriverClassName(driverClassName);
        dataSource.setJdbcUrl(databaseUrl);
        dataSource.setUsername(databaseUsername);
        dataSource.setPassword(databasePassword);

        dataSource.setMaximumPoolSize(MAX_POOL_SIZE);
        dataSource.setMinimumIdle(MIN_IDLE);
        dataSource.setIdleTimeout(IDLE_TIMEOUT_MS);

        //mysql optimizations
        dataSource.addDataSourceProperty("cachePrepStmts", true);
        dataSource.addDataSourceProperty("prepStmtCacheSize", PREPARED_STATEMENT_CACHE_SIZE);
        dataSource.addDataSourceProperty("prepStmtCacheSqlLimit",
                PREPARED_STATEMENT_CACHE_SQL_LIMIT);

        return dataSource;
    }
}

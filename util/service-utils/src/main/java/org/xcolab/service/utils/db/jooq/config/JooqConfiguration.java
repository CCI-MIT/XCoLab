package org.xcolab.service.utils.db.jooq.config;

import org.jooq.ConnectionProvider;
import org.jooq.SQLDialect;
import org.jooq.impl.DefaultConfiguration;
import org.jooq.impl.DefaultExecuteListenerProvider;

public class JooqConfiguration extends DefaultConfiguration {

    public JooqConfiguration(ConnectionProvider connectionProvider, String sqlDialectName) {
        this.set(connectionProvider);
        this.set(new DefaultExecuteListenerProvider(
                new JooqToSpringExceptionTransformer()
        ));
        SQLDialect dialect = SQLDialect.valueOf(sqlDialectName);
        this.set(dialect);
    }
}

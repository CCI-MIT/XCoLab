package org.xcolab.portlets.userprofile.utils;

import org.hibernate.ejb.HibernatePersistence;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collections;
import java.util.List;

import javax.persistence.spi.PersistenceProvider;
import javax.persistence.spi.PersistenceProviderResolver;
import javax.persistence.spi.PersistenceProviderResolverHolder;

public class HibernatePersistenceProviderResolver implements PersistenceProviderResolver {
    private final static Logger _log = LoggerFactory.getLogger(HibernatePersistenceProviderResolver.class);

    private volatile PersistenceProvider persistenceProvider = new HibernatePersistence();

    public static void register() {
        _log.info("Registering HibernatePersistenceProviderResolver");
        PersistenceProviderResolverHolder.setPersistenceProviderResolver(new HibernatePersistenceProviderResolver());
    }

    @Override
    public List<PersistenceProvider> getPersistenceProviders() {
        return Collections.singletonList(persistenceProvider);
    }

    @Override
    public void clearCachedProviders() {
        persistenceProvider = new HibernatePersistence();
    }
}
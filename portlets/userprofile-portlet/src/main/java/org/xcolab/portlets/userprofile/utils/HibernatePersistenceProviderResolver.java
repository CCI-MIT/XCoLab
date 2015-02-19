package org.xcolab.portlets.userprofile.utils;

/**
 * Created by Thomas on 2/18/2015.
 */


import java.util.Collections;
import java.util.List;
import java.util.logging.Logger;

import org.hibernate.ejb.HibernatePersistence;
import javax.persistence.spi.PersistenceProvider;
import javax.persistence.spi.PersistenceProviderResolver;
import javax.persistence.spi.PersistenceProviderResolverHolder;


public class HibernatePersistenceProviderResolver implements PersistenceProviderResolver {
    private static final Logger LOGGER = Logger.getLogger(HibernatePersistenceProviderResolver.class.getName());

    private volatile PersistenceProvider persistenceProvider = new HibernatePersistence();

    public List<PersistenceProvider> getPersistenceProviders() {
        return Collections.singletonList(persistenceProvider);
    }

    public void clearCachedProviders() {
        persistenceProvider = new HibernatePersistence();
    }

    public static void register() {
        LOGGER.info("Registering HibernatePersistenceProviderResolver");
        PersistenceProviderResolverHolder.setPersistenceProviderResolver(new HibernatePersistenceProviderResolver());
    }
}
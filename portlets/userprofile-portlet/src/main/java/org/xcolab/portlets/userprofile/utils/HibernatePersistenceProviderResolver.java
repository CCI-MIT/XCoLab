package org.xcolab.portlets.userprofile.utils;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import org.hibernate.ejb.HibernatePersistence;

import javax.persistence.spi.PersistenceProvider;
import javax.persistence.spi.PersistenceProviderResolver;
import javax.persistence.spi.PersistenceProviderResolverHolder;
import java.util.Collections;
import java.util.List;

public class HibernatePersistenceProviderResolver implements PersistenceProviderResolver {
    private final static Log _log = LogFactoryUtil.getLog(HibernatePersistenceProviderResolver.class.getName());

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
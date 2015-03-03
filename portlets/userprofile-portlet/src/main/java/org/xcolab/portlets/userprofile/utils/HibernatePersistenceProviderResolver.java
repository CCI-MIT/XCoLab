package org.xcolab.portlets.userprofile.utils;

/**
 * Created by Thomas on 2/18/2015.
 */
import java.util.Collections;
import java.util.List;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import org.hibernate.ejb.HibernatePersistence;
import javax.persistence.spi.PersistenceProvider;
import javax.persistence.spi.PersistenceProviderResolver;
import javax.persistence.spi.PersistenceProviderResolverHolder;


public class HibernatePersistenceProviderResolver implements PersistenceProviderResolver {
    private final static Log _log = LogFactoryUtil.getLog(HibernatePersistenceProviderResolver.class.getName());

    private volatile PersistenceProvider persistenceProvider = new HibernatePersistence();

    public List<PersistenceProvider> getPersistenceProviders() {
        return Collections.singletonList(persistenceProvider);
    }

    public void clearCachedProviders() {
        persistenceProvider = new HibernatePersistence();
    }

    public static void register() {
        _log.info("Registering HibernatePersistenceProviderResolver");
        PersistenceProviderResolverHolder.setPersistenceProviderResolver(new HibernatePersistenceProviderResolver());
    }
}
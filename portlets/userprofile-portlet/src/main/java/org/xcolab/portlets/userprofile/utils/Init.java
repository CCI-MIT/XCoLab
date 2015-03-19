package org.xcolab.portlets.userprofile.utils;

/**
 * Created by Thomas on 2/18/2015.
 */

import javax.servlet.ServletContextEvent;

public class Init{

    public void contextDestroyed(ServletContextEvent arg0) {
    }

    public void contextInitialized(ServletContextEvent arg0) {
        HibernatePersistenceProviderResolver.register();
    }
}

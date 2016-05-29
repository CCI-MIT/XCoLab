package org.xcolab.portlets.userprofile.utils;

import javax.servlet.ServletContextEvent;

public class Init {

    public void contextDestroyed(ServletContextEvent arg0) { }

    public void contextInitialized(ServletContextEvent arg0) {
        HibernatePersistenceProviderResolver.register();
    }
}

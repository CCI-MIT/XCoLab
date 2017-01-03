package org.xcolab.view.config.rewrite;

import org.ocpsoft.logging.Logger.Level;
import org.ocpsoft.rewrite.config.Configuration;
import org.ocpsoft.rewrite.config.ConfigurationBuilder;
import org.ocpsoft.rewrite.config.Log;
import org.ocpsoft.rewrite.servlet.config.HttpConfigurationProvider;

import javax.servlet.ServletContext;

public class RewriteConfigProvider extends HttpConfigurationProvider {

    @Override
    public Configuration getConfiguration(ServletContext servletContext) {
        return ConfigurationBuilder.begin()
                .addRule()
                    .perform(Log.message(Level.INFO, "Filter is working"));
    }

    @Override
    public int priority() {
        return 10;
    }
}

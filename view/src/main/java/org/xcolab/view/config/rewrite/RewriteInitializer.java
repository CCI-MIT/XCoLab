package org.xcolab.view.config.rewrite;

import org.ocpsoft.rewrite.servlet.impl.RewriteServletContextListener;
import org.ocpsoft.rewrite.servlet.impl.RewriteServletRequestListener;
import org.springframework.boot.web.servlet.ServletContextInitializer;

import java.util.EnumSet;

import javax.servlet.DispatcherType;
import javax.servlet.FilterRegistration.Dynamic;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;

public class RewriteInitializer implements ServletContextInitializer {

    private static final String ANNOTATION_SCAN_PARAM =
            "org.ocpsoft.rewrite.annotation.BASE_PACKAGES";

    @Override
    public void onStartup(ServletContext servletContext) {
        //disable annotation scanning, as it doesn't work in embedded containers
        servletContext.setInitParameter(ANNOTATION_SCAN_PARAM, "none");

        servletContext.addListener(new RewriteServletRequestListener());
        servletContext.addListener(new RewriteServletContextListener());

        final Dynamic rewriteFilter =
                servletContext.addFilter("OCPsoft Rewrite Filter", new CustomRewriteFilter());
        rewriteFilter.setAsyncSupported(true);
        rewriteFilter.addMappingForUrlPatterns(EnumSet.allOf(DispatcherType.class), false, "/*");
    }
}

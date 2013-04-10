package org.apache.wicket.portlet;

import org.apache.wicket.request.UrlRenderer;
import org.apache.wicket.request.cycle.RequestCycle;
import org.apache.wicket.request.cycle.RequestCycleContext;

/**
 * RequestCycle implementation that uses {@link PortletUrlRenderer}
 * 
 * @author msabo (marek.sabo@mgm-tp.com)
 */
public class PortletRequestCycle extends RequestCycle {
    /**
     * Construct.
     * 
     * @param context current context
     */
    public PortletRequestCycle(RequestCycleContext context) {
        super(context);
    }

    @Override
    protected UrlRenderer newUrlRenderer() {
        return new PortletUrlRenderer(getRequest());
    }
}

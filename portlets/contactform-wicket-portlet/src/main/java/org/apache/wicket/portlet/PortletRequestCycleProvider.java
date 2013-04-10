package org.apache.wicket.portlet;

import org.apache.wicket.IRequestCycleProvider;
import org.apache.wicket.request.cycle.RequestCycle;
import org.apache.wicket.request.cycle.RequestCycleContext;

/**
 * {@link PortletRequestCycle} (Wicket) factory implementation
 * 
 * @author msabo (marek.sabo@mgm-tp.com)
 */
public class PortletRequestCycleProvider implements IRequestCycleProvider {
    @Override
    public RequestCycle get(RequestCycleContext context) {
        return new PortletRequestCycle(context);
    }
}

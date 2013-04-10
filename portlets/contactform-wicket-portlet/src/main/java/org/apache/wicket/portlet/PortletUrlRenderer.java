package org.apache.wicket.portlet;

import org.apache.wicket.request.Request;
import org.apache.wicket.request.Url;
import org.apache.wicket.request.UrlRenderer;
import org.apache.wicket.util.lang.Args;

/**
 * Custom {@link UrlRenderer} implementation that removes regression (portlet-wise) introduced
 * in https://issues.apache.org/jira/browse/WICKET-4645
 * 
 * @author msabo (marek.sabo@mgm-tp.com)
 */
public class PortletUrlRenderer extends UrlRenderer {

    public PortletUrlRenderer(Request request) {
        super(request);
    }

    @Override
    public String renderRelativeUrl(final Url url) {
        Args.notNull(url, "url");
        if (url.isAbsolute()) {
            return url.toString();
        } else {
            return super.renderRelativeUrl(url);
        }
    }
}

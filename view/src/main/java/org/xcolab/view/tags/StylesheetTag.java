package org.xcolab.view.tags;

import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.JspException;

/**
 * JSP tag for rendering a stylesheet (HTML '{@code link}' with {@code rel="stylesheet"}) whose href attribute is URL encoded
 * using the {@link HttpServletResponse#encodeURL(String)} method.
 */
public class StylesheetTag extends AbstractHtmlTag {

    private static final String LINK_TAG = "link";

    private static final String HREF_ATTRIBUTE = "href";
    private static final String REL_ATTRIBUTE = "rel";
    private static final String INTEGRITY_ATTRIBUTE = "integrity";
    private static final String CROSSORIGIN_ATTRIBUTE = "crossorigin";

    private String href;
    private String integrity;

    public StylesheetTag() {
        super(LINK_TAG);
    }

	/**
	 * Writes the opening part of the '{@code link}' tag.
	 */
	@Override
	protected void writeTagContent() throws JspException {
	    writeOptionalAttribute(REL_ATTRIBUTE, "stylesheet");
        final String encodedHref = encodeUrl(getHref());
        writeOptionalAttribute(HREF_ATTRIBUTE, encodedHref);
        writeOptionalAttribute(INTEGRITY_ATTRIBUTE, getIntegrity());
        if (StringUtils.isNotEmpty(getIntegrity())) {
            writeOptionalAttribute(CROSSORIGIN_ATTRIBUTE, "anonymous");
        }

        final boolean isLocalToCdn = getHref().startsWith("/") && !encodedHref.startsWith("/");
        if (isLocalToCdn) {
            writeOptionalAttribute("onerror", "retryLocal(this)");
        }
	}


    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    public String getIntegrity() {
        return integrity;
    }

    public void setIntegrity(String integrity) {
        this.integrity = integrity;
    }
}

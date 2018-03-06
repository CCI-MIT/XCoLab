package org.xcolab.view.tags;

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
    private String crossorigin;

    public StylesheetTag() {
        super(LINK_TAG);
    }

	/**
	 * Writes the opening part of the '{@code link}' tag.
	 */
	@Override
	protected void writeTagContent() throws JspException {
	    writeOptionalAttribute(REL_ATTRIBUTE, "stylesheet");
        writeOptionalAttribute(HREF_ATTRIBUTE, encodeUrl(getHref()));
        writeOptionalAttribute(INTEGRITY_ATTRIBUTE, getIntegrity());
        writeOptionalAttribute(CROSSORIGIN_ATTRIBUTE, getCrossorigin());
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

    public String getCrossorigin() {
        return crossorigin;
    }

    public void setCrossorigin(String crossorigin) {
        this.crossorigin = crossorigin;
    }
}

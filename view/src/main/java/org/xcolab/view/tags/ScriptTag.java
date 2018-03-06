package org.xcolab.view.tags;

import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.JspException;

/**
 * JSP tag for rendering a '{@code script}' whose src attribute is URL encoded
 * using the {@link HttpServletResponse#encodeURL(String)} method.
 */
public class ScriptTag extends AbstractHtmlTag {

    private static final String SCRIPT_TAG = "script";

    private static final String SRC_ATTRIBUTE = "src";
    private static final String ASYNC_ATTRIBUTE = "async";
    private static final String DEFER_ATTRIBUTE = "defer";
    private static final String INTEGRITY_ATTRIBUTE = "integrity";
    private static final String CROSSORIGIN_ATTRIBUTE = "crossorigin";

    private String src;
    private String async;
    private String defer;
    private String integrity;

    public ScriptTag() {
        super(SCRIPT_TAG);
    }

	/**
	 * Writes the opening part of the '{@code script}' tag.
	 */
	@Override
	protected void writeTagContent() throws JspException {
        writeOptionalAttribute(SRC_ATTRIBUTE, encodeUrl(getSrc()));
        writeOptionalAttribute(ASYNC_ATTRIBUTE, getAsync());
        writeOptionalAttribute(DEFER_ATTRIBUTE, getDefer());
        writeOptionalAttribute(INTEGRITY_ATTRIBUTE, getIntegrity());
        if (StringUtils.isNotEmpty(getIntegrity())) {
            writeOptionalAttribute(CROSSORIGIN_ATTRIBUTE, "anonymous");
        }
	}


    public String getSrc() {
        return src;
    }

    public void setSrc(String src) {
        this.src = src;
    }

    public String getAsync() {
        return async;
    }

    public void setAsync(String async) {
        this.async = async;
    }

    public String getDefer() {
        return defer;
    }

    public void setDefer(String defer) {
        this.defer = defer;
    }

    public String getIntegrity() {
        return integrity;
    }

    public void setIntegrity(String integrity) {
        this.integrity = integrity;
    }
}

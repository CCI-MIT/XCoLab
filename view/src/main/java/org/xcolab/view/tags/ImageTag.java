package org.xcolab.view.tags;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.JspException;

/**
 * JSP tag for rendering an HTML '{@code img}' whose src attribute is URL encoded
 * using the {@link HttpServletResponse#encodeURL(String)} method.
 */
public class ImageTag extends AbstractHtmlTag {

    private static final String IMAGE_TAG = "img";

    private static final String SRC_ATTRIBUTE = "src";
    private static final String SRCSET_ATTRIBUTE = "srcset";
    private static final String SIZES_ATTRIBUTE = "sizes";
    private static final String ALT_ATTRIBUTE = "alt";
    private static final String WIDTH_ATTRIBUTE = "width";
    private static final String HEIGHT_ATTRIBUTE = "height";

    private String src;
    private String srcset;
    private String sizes;
    private String alt;
    private Integer width;
    private Integer height;

    public ImageTag() {
        super(IMAGE_TAG);
    }

	/**
	 * Writes the opening part of the '{@code img}' tag.
	 */
	@Override
	protected void writeTagContent() throws JspException {
        final String encodedSrc = encodeUrl(getSrc());
        writeOptionalAttribute(SRC_ATTRIBUTE, encodedSrc);
        writeOptionalAttribute(SRCSET_ATTRIBUTE, getSrcset());
        writeOptionalAttribute(SIZES_ATTRIBUTE, getSizes());
        writeOptionalAttribute(ALT_ATTRIBUTE, getAlt());
        writeOptionalAttribute(WIDTH_ATTRIBUTE, getWidth());
        writeOptionalAttribute(HEIGHT_ATTRIBUTE, getHeight());

        final boolean isLocalToCdn = getSrc().startsWith("/") && !encodedSrc.startsWith("/");
        if (isLocalToCdn) {
            writeOptionalAttribute("onerror", "retryLocal(this)");
        }
	}


    public String getSrc() {
        return src;
    }

    public void setSrc(String src) {
        this.src = src;
    }

    public String getAlt() {
        return alt;
    }

    public void setAlt(String alt) {
        this.alt = alt;
    }

    public Integer getWidth() {
        return width;
    }

    public void setWidth(Integer width) {
        this.width = width;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public String getSrcset() {
        return srcset;
    }

    public void setSrcset(String srcset) {
        this.srcset = srcset;
    }

    public String getSizes() {
        return sizes;
    }

    public void setSizes(String sizes) {
        this.sizes = sizes;
    }
}

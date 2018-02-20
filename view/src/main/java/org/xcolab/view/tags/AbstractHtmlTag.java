package org.xcolab.view.tags;

import org.springframework.util.ObjectUtils;
import org.springframework.web.servlet.tags.RequestContextAwareTag;
import org.springframework.web.servlet.tags.form.TagWriter;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.JspException;

import static org.springframework.util.ObjectUtils.getDisplayString;

/**
 * Base class for custom HTML tags.
 *
 * This class provides a way to write a custom version of a HTML tag. It adds common default
 * attributes provides and provides the ability to add custom attributes and child tags via the
 * {@link TagWriter} returned by the {@link #getTagWriter()} method.
 *
 * {@see org.springframework.web.servlet.tags.form.AbstractHtmlElementTag}
 */
public abstract class AbstractHtmlTag extends RequestContextAwareTag {

    private static final String ID_ATTRIBUTE = "id";
    private static final String CLASS_ATTRIBUTE = "class";
    private static final String STYLE_ATTRIBUTE = "style";
    private static final String TITLE_ATTRIBUTE = "title";

    private static final String ARIA_LABEL_ATTRIBUTE = "aria-label";
    private static final String ARIA_HIDDEN_ATTRIBUTE = "aria-hidden";

    private static final String DATA_TOOLTIP_CONTENT_ATTRIBUTE = "data-tooltip-content";

    private String id;
    private String cssClass;
    private String cssStyle;
    private String title;

    private String ariaLabel;
    private String ariaHidden;

    private String dataTooltipContent;

    private TagWriter tagWriter;

    private final String tagName;

    protected AbstractHtmlTag(String tagName) {
        this.tagName = tagName;
    }

    /**
     * Provide a simple template method that creates a {@link TagWriter} and passes
     * it to the {@link #writeTagContent()} method.
     * @return the value returned by {@link #writeTagContent()}
     */
    @Override
    protected final int doStartTagInternal() throws JspException {
        this.tagWriter = new TagWriter(this.pageContext);
        tagWriter.startTag(tagName);
        writeDefaultAttributes();

        writeTagContent();

        return EVAL_BODY_INCLUDE;
    }

    /**
     * Closes the outer tag.
     */
    @Override
    public int doEndTag() throws JspException {
        getTagWriter().endTag();
        return EVAL_PAGE;
    }

    /**
     * Clears the stored {@link TagWriter}.
     */
    @Override
    public void doFinally() {
        super.doFinally();
        this.tagWriter = null;
    }

    private void writeDefaultAttributes() throws JspException {
        writeOptionalAttribute(ID_ATTRIBUTE, getId());
        writeOptionalAttribute(CLASS_ATTRIBUTE, getCssClass());
        writeOptionalAttribute(STYLE_ATTRIBUTE, getCssStyle());
        writeOptionalAttribute(TITLE_ATTRIBUTE, getTitle());

        writeOptionalAttribute(ARIA_LABEL_ATTRIBUTE, getAriaLabel());
        writeOptionalAttribute(ARIA_HIDDEN_ATTRIBUTE, getAriaHidden());

        writeOptionalAttribute(DATA_TOOLTIP_CONTENT_ATTRIBUTE, getDataTooltipContent());
    }

    /**
     * Optionally writes the supplied value under the supplied attribute name.
     *
     * The {@link ObjectUtils#getDisplayString String representation} is written as the
     * attribute value. If the resultant {@code String} representation is {@code null}
     * or empty, no attribute is written.
     * @see TagWriter#writeOptionalAttributeValue(String, String)
     */
    protected final void writeOptionalAttribute(String attributeName,
            Object value) throws JspException {

        if (value != null) {
            tagWriter.writeOptionalAttributeValue(attributeName, getDisplayString(value));
        }
    }

    /**
     * Retrieve the {@link TagWriter} to add custom child HTML elements.
     */
    protected TagWriter getTagWriter() {
        return tagWriter;
    }

    /**
     * Encodes the given URL using the {@link HttpServletResponse#encodeURL(String)} method.
     *
     * @throws JspException if the {@link javax.servlet.ServletResponse} is not an {@link HttpServletResponse}
     */
    protected String encodeUrl(String url) throws JspException {
        if (!(pageContext.getResponse() instanceof HttpServletResponse)) {
            throw new JspException("This tag only supports HttpServlets");
        }
        final HttpServletResponse response = (HttpServletResponse) pageContext.getResponse();
        return response.encodeURL(url);
    }

    /**
     * Subclasses should implement this method to perform tag content rendering.
     */
    protected abstract void writeTagContent() throws JspException;


    @Override
    public String getId() {
        return id;
    }

    @Override
    public void setId(String id) {
        this.id = id;
    }

    public String getCssClass() {
        return cssClass;
    }

    public void setCssClass(String cssClass) {
        this.cssClass = cssClass;
    }

    public String getCssStyle() {
        return cssStyle;
    }

    public void setCssStyle(String cssStyle) {
        this.cssStyle = cssStyle;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAriaLabel() {
        return ariaLabel;
    }

    public void setAriaLabel(String ariaLabel) {
        this.ariaLabel = ariaLabel;
    }

    public String getAriaHidden() {
        return ariaHidden;
    }

    public void setAriaHidden(String ariaHidden) {
        this.ariaHidden = ariaHidden;
    }

    public String getDataTooltipContent() {
        return dataTooltipContent;
    }

    public void setDataTooltipContent(String dataTooltipContentAttribute) {
        this.dataTooltipContent = dataTooltipContentAttribute;
    }
}

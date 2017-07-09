package org.xcolab.view.util.tags;

import org.springframework.context.NoSuchMessageException;
import org.springframework.web.servlet.tags.MessageTag;

import org.xcolab.client.admin.pojo.ContestType;
import org.xcolab.client.admin.util.TemplateReplacementUtilPlaceholder;

import javax.servlet.jsp.JspException;

/**
 * Extension of the Spring message tag to be aware of platform constants.
 */
public class PlaceholderAwareMessageTag extends MessageTag {

    private ContestType contestType;

    /**
     * Resolve the specified message into a concrete message String.
     * The returned message String should be unescaped.
     *
     * Unlike its super method, this method also resolves placeholders
     * for platform constants and ContestTypes.
     */
    @Override
    protected String resolveMessage() throws JspException, NoSuchMessageException {
        String message = super.resolveMessage();

        //resolve platform-wide placeholders
        message = TemplateReplacementUtilPlaceholder.replacePlatformConstants(message);

        //resolve ContestType-specific placeholders
        if (contestType != null) {
            message = contestType.format(message);
        }
        return message;
    }

    public void setContestType(ContestType contestType) {
        this.contestType = contestType;
    }
}

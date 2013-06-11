package org.xcolab.portlets.messaging.validators;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

import org.apache.commons.lang3.StringUtils;
import org.xcolab.portlets.messaging.utils.MessageLimitManager;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
/**
 * Validates if user is allowed to send more messages.
 * 
 * @author janusz
 *
 */
public class MessagesLimitValidator implements Validator {
    private final static Log _log = LogFactoryUtil.getLog(MessagesLimitValidator.class);

    @Override
    public void validate(FacesContext arg0, UIComponent arg1, Object arg2) throws ValidatorException {
        try {
            String[] recipientIds =  ((String) arg2).split(",");
            int count = 0;
            for (String recipientId: recipientIds) {
                if (StringUtils.isNotBlank(recipientId)) count ++;
            }
            
            if (! MessageLimitManager.canSendMessages(count)) { 
                FacesMessage message = new FacesMessage("Messages limit has been exceeded, if you want to send more messages, please contact the administrators");
                message.setSeverity(FacesMessage.SEVERITY_ERROR);
                throw new ValidatorException(message);
            }
        } catch (PortalException | SystemException e) {
            _log.error("Can't validate user messages limit", e);

            FacesMessage message = new FacesMessage("An error has ocurred, please contact the administrators");
            message.setSeverity(FacesMessage.SEVERITY_ERROR);
            throw new ValidatorException(message);
        }
        
    }

}

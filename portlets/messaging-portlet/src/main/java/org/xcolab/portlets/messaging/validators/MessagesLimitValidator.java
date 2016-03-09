package org.xcolab.portlets.messaging.validators;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import org.xcolab.portlets.messaging.Helper;
import org.xcolab.utils.IdListUtil;
import org.xcolab.utils.MessageLimitManager;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import java.util.List;

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
            List<Long> recipientIds = IdListUtil.getIdsFromString((String) arg2);
            int count = recipientIds.size();
            
            if (! MessageLimitManager.canSendMessages(count, Helper.getLiferayUser())) {
                FacesMessage message = new FacesMessage("Messages limit has been exceeded, if you want to send more messages, please contact the administrators");
                message.setSeverity(FacesMessage.SEVERITY_ERROR);
                throw new ValidatorException(message);
            }
        } catch (PortalException | SystemException e) {
            _log.error("Can't validate user messages limit", e);

            FacesMessage message = new FacesMessage("An error has occurred, please contact the administrators");
            message.setSeverity(FacesMessage.SEVERITY_ERROR);
            throw new ValidatorException(message);
        }
        
    }

}

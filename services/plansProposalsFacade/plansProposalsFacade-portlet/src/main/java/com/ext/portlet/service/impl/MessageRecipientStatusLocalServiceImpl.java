package com.ext.portlet.service.impl;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.ext.portlet.NoSuchMessageRecipientStatusException;
import com.ext.portlet.model.Message;
import com.ext.portlet.model.MessageRecipientStatus;
import com.ext.portlet.model.Proposal;
import com.ext.portlet.service.MessageLocalServiceUtil;
import com.ext.portlet.service.base.MessageRecipientStatusLocalServiceBaseImpl;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.model.User;

/**
 * The implementation of the message recipient status local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link com.ext.portlet.service.MessageRecipientStatusLocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see com.ext.portlet.service.base.MessageRecipientStatusLocalServiceBaseImpl
 * @see com.ext.portlet.service.MessageRecipientStatusLocalServiceUtil
 */
public class MessageRecipientStatusLocalServiceImpl
    extends MessageRecipientStatusLocalServiceBaseImpl {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never reference this interface directly. Always use {@link com.ext.portlet.service.MessageRecipientStatusLocalServiceUtil} to access the message recipient status local service.
     */

}

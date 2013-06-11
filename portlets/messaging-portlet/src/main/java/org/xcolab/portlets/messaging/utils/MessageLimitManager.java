package org.xcolab.portlets.messaging.utils;

import java.util.Calendar;

import org.climatecollaboratorium.utils.Helper;
import org.xcolab.utils.PropertiesUtils;

import com.ext.portlet.community.CommunityConstants;
import com.ext.portlet.model.Message;
import com.ext.portlet.model.MessageRecipientStatus;
import com.ext.portlet.service.MessageLocalServiceUtil;
import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQueryFactoryUtil;
import com.liferay.portal.kernel.dao.orm.ProjectionFactoryUtil;
import com.liferay.portal.kernel.dao.orm.PropertyFactoryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.model.User;
import com.liferay.portlet.expando.model.ExpandoColumn;
import com.liferay.portlet.expando.model.ExpandoColumnConstants;
import com.liferay.portlet.expando.model.ExpandoTable;
import com.liferay.portlet.expando.service.ExpandoColumnLocalServiceUtil;
import com.liferay.portlet.expando.service.ExpandoTableLocalServiceUtil;
import com.liferay.portlet.expando.service.ExpandoValueLocalServiceUtil;

/**
 * Class responsible for handling verification if user is allowed to send
 * messages or maybe daily limit has been reached.
 * 
 * @author janusz
 * 
 */
public class MessageLimitManager {
    private static final long companyId = 10112L;
    private static final String MESSAGES_LIMIT_COLUMN = "messages_limit";

    private static final String MESSAGE_ENTITY_CLASS_LOADER_CONTEXT = "plansProposalsFacade-portlet";

    /**
     * Method responsible for checking if user is allowed to send given number of messages.
     * 
     * @param messagesToSend number of messages that user wants to send
     * @return
     * @throws PortalException in case of LR error
     * @throws SystemException in case of LR error
     */
    public static boolean canSendMessages(int messagesToSend) throws PortalException, SystemException {
        User user = Helper.getLiferayUser();

        // ensure that expando column has been created
        ExpandoColumn column = null;
        try {
            column = ExpandoColumnLocalServiceUtil.getColumn(companyId, User.class.getName(),
                    CommunityConstants.EXPANDO, MESSAGES_LIMIT_COLUMN);
        } catch (SystemException e) {
        }
        if (column == null) {
            
            ExpandoTable table = ExpandoTableLocalServiceUtil.getTable(companyId, User.class.getName(),
                    CommunityConstants.EXPANDO);
            ExpandoColumnLocalServiceUtil.addColumn(table.getTableId(), MESSAGES_LIMIT_COLUMN,
                    ExpandoColumnConstants.INTEGER);
        }
        // get messagesLimit from expando table (if exists)
        Integer messagesLimit = ExpandoValueLocalServiceUtil.getData(companyId, User.class.getName(), CommunityConstants.EXPANDO,
                MESSAGES_LIMIT_COLUMN, user.getUserId(), -1);
        
        if (messagesLimit < 0) {
            // limit not defined in expando table, fetch it from properties file
            messagesLimit = Integer.parseInt(PropertiesUtils.get("messages.dailyLimitPerUser"));
        }
        if (messagesLimit == 0) {
            // limit is set to 0, user can send as many messages as he wants
            return true;
        }
        else {
            // count messages that user has already sent today
            
            
            ClassLoader portletClassLoader = (ClassLoader) PortletBeanLocatorUtil.locate(MESSAGE_ENTITY_CLASS_LOADER_CONTEXT, 
                    "portletClassLoader");
            
            Calendar c = Calendar.getInstance();
            c.add(Calendar.DATE, -1);
            
            DynamicQuery messagesQuery = DynamicQueryFactoryUtil.forClass(Message.class, portletClassLoader);
            messagesQuery.add(PropertyFactoryUtil.forName("fromId").eq(user.getUserId()));
            messagesQuery.add(PropertyFactoryUtil.forName("createDate").gt(c.getTime()));
            
            DynamicQuery messageRecipientsQuery = DynamicQueryFactoryUtil.forClass(MessageRecipientStatus.class, portletClassLoader);
            messageRecipientsQuery.add(PropertyFactoryUtil.forName("messageId").in(messagesQuery.setProjection(ProjectionFactoryUtil.property("messageId"))));
            
            long count = MessageLocalServiceUtil.dynamicQueryCount(messageRecipientsQuery);
            
            if (messagesLimit < count + messagesToSend) {
                
                // limit exceeded, throw exception
                return false;
            }
        }
        return true;
    }

}

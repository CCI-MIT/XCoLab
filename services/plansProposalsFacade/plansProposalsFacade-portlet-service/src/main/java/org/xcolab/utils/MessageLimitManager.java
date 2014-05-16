package org.xcolab.utils;

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

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Class responsible for handling verification if user is allowed to send
 * messages or maybe daily limit has been reached.
 * 
 * @author janusz
 */
public class MessageLimitManager {
    private static final long companyId = 10112L;
    private static final String MESSAGES_LIMIT_COLUMN = "messages_limit";

    private static final String MESSAGE_ENTITY_CLASS_LOADER_CONTEXT = "plansProposalsFacade-portlet";

    private static Map<Long, Long> mutexes = new HashMap<>();

	private static final int MESSAGES_DAILY_LIMIT = 10;

	/**
	 * Keeps track of the last validation error mail that has been send to a specific user
	 */
	private static Map<User, Date> lastValidationDateMap = new HashMap<>();

    /**
     * Method responsible for checking if user is allowed to send given number
     * of messages.
     * 
     * @param messagesToSend
     *            number of messages that user wants to send
     * @return
     * @throws com.liferay.portal.kernel.exception.PortalException
     *             in case of LR error
     * @throws com.liferay.portal.kernel.exception.SystemException
     *             in case of LR error
     */
    public static boolean canSendMessages(int messagesToSend, User user) throws PortalException, SystemException {
        // synchronize on senderId
        Long mutex = getMutex(user.getUserId());

        synchronized (mutex) {

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
            Integer messagesLimit = ExpandoValueLocalServiceUtil.getData(companyId, User.class.getName(),
                    CommunityConstants.EXPANDO, MESSAGES_LIMIT_COLUMN, user.getUserId(), -1);

            if (messagesLimit < 0) {
                // limit not defined in expando table, fetch it from properties
                // file
                messagesLimit = MESSAGES_DAILY_LIMIT;
            }

            // count messages that user has already sent today

            ClassLoader portletClassLoader = (ClassLoader) PortletBeanLocatorUtil.locate(
                    MESSAGE_ENTITY_CLASS_LOADER_CONTEXT, "portletClassLoader");

            Calendar c = Calendar.getInstance();
            c.add(Calendar.DATE, -1);

            DynamicQuery messagesQuery = DynamicQueryFactoryUtil.forClass(Message.class, portletClassLoader);
            messagesQuery.add(PropertyFactoryUtil.forName("fromId").eq(user.getUserId()));
            messagesQuery.add(PropertyFactoryUtil.forName("createDate").gt(c.getTime()));

            DynamicQuery messageRecipientsQuery = DynamicQueryFactoryUtil.forClass(MessageRecipientStatus.class,
                    portletClassLoader);
            messageRecipientsQuery.add(PropertyFactoryUtil.forName("messageId").in(
                    messagesQuery.setProjection(ProjectionFactoryUtil.property("messageId"))));

            long count = MessageLocalServiceUtil.dynamicQueryCount(messageRecipientsQuery);

            if (messagesLimit < count + messagesToSend) {

                // limit exceeded, throw exception
                return false;
            }

            return true;
        }
    }

    public static synchronized Long getMutex(Long senderId) {
        Long mutex = mutexes.get(senderId);
        if (mutex == null) {
            mutex = senderId;
            mutexes.put(mutex, mutex);
        }
        return mutex;
    }

	public static boolean shouldSendValidationErrorMessage(User user) {
		if (user == null) {
			return false;
		}
		Date lastEmailSendDate = lastValidationDateMap.get(user);

		Date now = new Date();
		if (lastEmailSendDate != null) {
			// Send mail if the last email send was over 24h ago
			if ((now.getTime() - lastEmailSendDate.getTime()) > 24 * 3600 * 1000) {
				lastValidationDateMap.put(user, now);
				return true;
			}
			else {
				return false;
			}
		} else {
			lastValidationDateMap.put(user, now);
			return true;
		}
	}

}

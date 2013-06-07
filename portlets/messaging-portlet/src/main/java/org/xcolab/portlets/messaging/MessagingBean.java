package org.xcolab.portlets.messaging;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.model.DataModel;
import javax.mail.internet.AddressException;

import org.xcolab.portlets.messaging.utils.DataPage;
import org.xcolab.portlets.messaging.utils.PagedListDataModel;

import com.ext.portlet.messaging.MessageUtil;
import com.ext.portlet.model.Message;
import com.ext.portlet.service.MessageLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.search.ParseException;
import com.liferay.portal.model.User;
import com.liferay.util.mail.MailEngineException;

public class MessagingBean implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private User user;
	private DataModel onePageDataModel;
	private MessageType messageType = MessageType.INBOX;
	private int pageSize = 25;
	private static final Log _log = LogFactoryUtil.getLog(MessagingBean.class);
	private int messagesCount = 0;
	private List<MessageBean> items = new ArrayList<MessageBean>();
	private boolean sendingMessage;
	private SendMessageBean sendMessageBean;
	private Long messageToShow;
	private MessageBean replyMessage;

	public MessagingBean() throws SystemException, PortalException {
		if (Helper.isUserLoggedIn()) {
			user = Helper.getLiferayUser();
			// MessageLocalServiceUtil.
			messagesCount = MessageUtil.countMessages(user.getUserId(),
					messageType.getTypeStr());

			Object composeObj = FacesContext.getCurrentInstance()
					.getExternalContext().getRequestParameterMap()
					.get("compose");
			Object messageIdObj = FacesContext.getCurrentInstance()
					.getExternalContext().getRequestParameterMap()
					.get("messageId");

			if (composeObj != null && composeObj.toString().equals("true")) {
				sendingMessage = true;
			}

			if (messageIdObj != null) {
				try {
					setMessageToShow(Long.parseLong(messageIdObj.toString()));
				} catch (NumberFormatException e) {
					// ignore
				}
			}

		}
	}

	public boolean isInitialized() {
		return user != null;
	}

	/**
	 * Bound to DataTable value in the ui.
	 */
	public DataModel getData() {
		if (onePageDataModel == null) {
			onePageDataModel = new LocalDataModel(pageSize);
		}
		return onePageDataModel;
	}

	/**
	 * This is where the Customer data is retrieved from the database and
	 * returned as a list of CustomerBean objects for display in the UI.
	 * 
	 * @throws IOException
	 * @throws ParseException
	 * @throws PortalException
	 * @throws SystemException
	 */
	private DataPage getDataPage(int startRow, int pageSize)
			throws IOException, SystemException, PortalException {

		messagesCount = MessageUtil.countMessages(user.getUserId(),
				messageType.getTypeStr());

		// Calculate indices to be displayed in the ui.
		int endIndex = startRow + pageSize;
		if (endIndex > messagesCount) {
			endIndex = messagesCount;
		}

		items = new ArrayList<MessageBean>();
		for (Message message : MessageUtil.getMessages(user.getUserId(),
				startRow, endIndex, messageType.getTypeStr())) {
			items.add(new MessageBean(message));
		}

		return new DataPage(messagesCount, startRow, items);
	}

	private class LocalDataModel extends PagedListDataModel implements
			Serializable {
		/**
		 * test comment
		 */
		private static final long serialVersionUID = 1L;

		public LocalDataModel() {
			super(-1);
		}

		public LocalDataModel(int pageSize) {
			super(pageSize);
		}

		public DataPage fetchPage(int startRow, int pageSize) {
			// call enclosing managed bean method to fetch the data
			try {
				System.out.println("user: " + user + " type: " + messageType);
				return getDataPage(startRow, pageSize);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return new DataPage(0, 0, new ArrayList<MessageBean>());
		}
	}

	public void changeType(ActionEvent e) {
		try {
			MessageType type = MessageType.valueOf(e.getComponent()
					.getAttributes().get("type").toString());

			if (type != messageType) {
				messageType = type;
				onePageDataModel = null;
				messagesCount = MessageUtil.countMessages(user.getUserId(),
						messageType.getTypeStr());
			}
		} catch (Exception ex) {
			_log.error("There was an error when changing view type", ex);
		}
	}

	public MessageType getType() {
		return messageType;
	}

	public int getMessagesCount() {
		return messagesCount;
	}

	public void archiveSelectedMessages(ActionEvent e) throws SystemException,
			PortalException {
		for (MessageBean item : items) {
			if (item.isSelected()) {
				Message message = item.getMessage();
				if (!MessageLocalServiceUtil.isArchived(message,
						user.getUserId())) {
					MessageLocalServiceUtil.setArchived(message,
							user.getUserId());
				}
			}
		}
		messagesCount = MessageUtil.countMessages(user.getUserId(),
				messageType.getTypeStr());
		onePageDataModel = null;
	}

	public void toggleSendMessage(ActionEvent e) throws PortalException,
			SystemException {
		Object replyMessageIdObj = e.getComponent().getAttributes()
				.get("replyMessageId");
		if (replyMessageIdObj != null) {
			Long replyMessageId = Long.parseLong(replyMessageIdObj.toString());
			for (MessageBean item : items) {
				if (item.getMessageId().equals(replyMessageId)) {
					replyMessage = item;
					toggleSendMessage(item);
					return;
				}
			}
		}
		replyMessage = null;
		toggleSendMessage((MessageBean) null);

	}

	public void toggleSendMessage(MessageBean replyMessage)
			throws PortalException, SystemException {
		sendingMessage = !sendingMessage;
		if (sendingMessage) {
			if (sendMessageBean != null) {
				if (replyMessage != null) {
					sendMessageBean.init(replyMessage);
				} else {
					sendMessageBean.init();
				}
			}
		}
		onePageDataModel = null;
	}

	public void messageSent() {
		sendingMessage = false;
	}

	public void setSendMessageBean(SendMessageBean sendMessageBean)
			throws PortalException, SystemException {
		this.sendMessageBean = sendMessageBean;
		if (replyMessage != null) {
			sendMessageBean.init(replyMessage);
		} else {
			sendMessageBean.init();
		}
	}

	public boolean getSendingMessage() {
		return sendingMessage;
	}

	public User getUser() {
		return user;
	}

	public void setMessageToShow(Long messageToShow) {
		this.messageToShow = messageToShow;
	}

	public Long getMessageToShow() {
		return messageToShow;
	}

	public void send(ActionEvent e) throws AddressException, SystemException,
			PortalException, MailEngineException {
		sendMessageBean.send(e);
	}

	public void cancel(ActionEvent e) throws PortalException, SystemException {
		sendMessageBean.cancel(e);
	}

}

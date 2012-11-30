package com.ext.portlet.messaging.model;

import com.liferay.portal.model.PersistedModel;

/**
 * The extended model interface for the Message service. Represents a row in the &quot;Messaging_Message&quot; database table, with each column mapped to a property of this class.
 *
 * @author Brian Wing Shun Chan
 * @see MessageModel
 * @see com.ext.portlet.messaging.model.impl.MessageImpl
 * @see com.ext.portlet.messaging.model.impl.MessageModelImpl
 * @generated
 */
public interface Message extends MessageModel, PersistedModel {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify this interface directly. Add methods to {@link com.ext.portlet.messaging.model.impl.MessageImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
     */
    public java.util.List<com.ext.portlet.messaging.model.MessageRecipientStatus> getRecipients()
        throws com.liferay.portal.kernel.exception.SystemException;

    public boolean hasReciever(long userid)
        throws com.liferay.portal.kernel.exception.SystemException;

    public boolean isOpened(long userid)
        throws com.ext.portlet.messaging.NoSuchMessageRecipientStatusException,
            com.liferay.portal.kernel.exception.SystemException;

    public void setOpened(long userid)
        throws com.ext.portlet.messaging.NoSuchMessageRecipientStatusException,
            com.liferay.portal.kernel.exception.SystemException;

    public boolean isArchived(long userid)
        throws com.ext.portlet.messaging.NoSuchMessageRecipientStatusException,
            com.liferay.portal.kernel.exception.SystemException;

    public void setArchived(long userid)
        throws com.ext.portlet.messaging.NoSuchMessageRecipientStatusException,
            com.liferay.portal.kernel.exception.SystemException;
}

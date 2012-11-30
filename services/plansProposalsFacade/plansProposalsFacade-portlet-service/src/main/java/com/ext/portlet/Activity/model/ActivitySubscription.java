package com.ext.portlet.Activity.model;

import com.liferay.portal.model.PersistedModel;

/**
 * The extended model interface for the ActivitySubscription service. Represents a row in the &quot;Activity_ActivitySubscription&quot; database table, with each column mapped to a property of this class.
 *
 * @author Brian Wing Shun Chan
 * @see ActivitySubscriptionModel
 * @see com.ext.portlet.Activity.model.impl.ActivitySubscriptionImpl
 * @see com.ext.portlet.Activity.model.impl.ActivitySubscriptionModelImpl
 * @generated
 */
public interface ActivitySubscription extends ActivitySubscriptionModel,
    PersistedModel {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify this interface directly. Add methods to {@link com.ext.portlet.Activity.model.impl.ActivitySubscriptionImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
     */
    public void store()
        throws com.liferay.portal.kernel.exception.SystemException;

    public com.ext.portlet.Activity.ICollabActivityInterpreter getInterpreter();

    public java.lang.String getName();

    public com.ext.portlet.Activity.SubscriptionType getSubscriptionType();

    public void delete()
        throws com.liferay.portal.kernel.exception.SystemException;
}

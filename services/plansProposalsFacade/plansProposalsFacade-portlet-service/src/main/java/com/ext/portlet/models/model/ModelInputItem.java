package com.ext.portlet.models.model;

import com.liferay.portal.model.PersistedModel;

/**
 * The extended model interface for the ModelInputItem service. Represents a row in the &quot;Models_ModelInputItem&quot; database table, with each column mapped to a property of this class.
 *
 * @author Brian Wing Shun Chan
 * @see ModelInputItemModel
 * @see com.ext.portlet.models.model.impl.ModelInputItemImpl
 * @see com.ext.portlet.models.model.impl.ModelInputItemModelImpl
 * @generated
 */
public interface ModelInputItem extends ModelInputItemModel, PersistedModel {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify this interface directly. Add methods to {@link com.ext.portlet.models.model.impl.ModelInputItemImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
     */
    public edu.mit.cci.simulation.client.MetaData getMetaData()
        throws com.liferay.portal.kernel.exception.SystemException,
            java.io.IOException;

    public edu.mit.cci.simulation.client.Simulation getModel()
        throws com.liferay.portal.kernel.exception.SystemException,
            java.io.IOException;

    public java.util.Map<java.lang.String, java.lang.String> getPropertyMap();

    public void saveProperties(
        java.util.Map<java.lang.String, java.lang.String> props)
        throws com.liferay.portal.kernel.exception.SystemException;

    public void store()
        throws com.liferay.portal.kernel.exception.SystemException;
}

package com.ext.portlet.models.model;

import com.liferay.portal.model.PersistedModel;

/**
 * The extended model interface for the ModelInputGroup service. Represents a row in the &quot;Models_ModelInputGroup&quot; database table, with each column mapped to a property of this class.
 *
 * @author Brian Wing Shun Chan
 * @see ModelInputGroupModel
 * @see com.ext.portlet.models.model.impl.ModelInputGroupImpl
 * @see com.ext.portlet.models.model.impl.ModelInputGroupModelImpl
 * @generated
 */
public interface ModelInputGroup extends ModelInputGroupModel, PersistedModel {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify this interface directly. Add methods to {@link com.ext.portlet.models.model.impl.ModelInputGroupImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
     */
    public java.util.List<com.ext.portlet.models.model.ModelInputItem> getInputItems();

    public java.util.List<com.ext.portlet.models.model.ModelInputGroup> getChildGroups();

    public com.ext.portlet.models.model.ModelInputGroup getParent();

    public edu.mit.cci.simulation.client.Simulation getModel()
        throws com.liferay.portal.kernel.exception.SystemException,
            java.io.IOException;

    public edu.mit.cci.simulation.client.MetaData getMetaData()
        throws com.liferay.portal.kernel.exception.SystemException,
            java.io.IOException;
}

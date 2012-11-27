package com.ext.portlet.ontology.model;

import com.liferay.portal.model.PersistedModel;

/**
 * The extended model interface for the OntologyTermEntity service. Represents a row in the &quot;ontology_OntologyTermEntity&quot; database table, with each column mapped to a property of this class.
 *
 * @author Brian Wing Shun Chan
 * @see OntologyTermEntityModel
 * @see com.ext.portlet.ontology.model.impl.OntologyTermEntityImpl
 * @see com.ext.portlet.ontology.model.impl.OntologyTermEntityModelImpl
 * @generated
 */
public interface OntologyTermEntity extends OntologyTermEntityModel,
    PersistedModel {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify this interface directly. Add methods to {@link com.ext.portlet.ontology.model.impl.OntologyTermEntityImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
     */
    public void store()
        throws com.liferay.portal.kernel.exception.SystemException;

    public void remove()
        throws com.liferay.portal.kernel.exception.SystemException;
}

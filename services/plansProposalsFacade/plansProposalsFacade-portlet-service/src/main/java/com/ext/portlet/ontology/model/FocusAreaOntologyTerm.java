package com.ext.portlet.ontology.model;

import com.liferay.portal.model.PersistedModel;

/**
 * The extended model interface for the FocusAreaOntologyTerm service. Represents a row in the &quot;ontology_FocusAreaOntologyTerm&quot; database table, with each column mapped to a property of this class.
 *
 * @author Brian Wing Shun Chan
 * @see FocusAreaOntologyTermModel
 * @see com.ext.portlet.ontology.model.impl.FocusAreaOntologyTermImpl
 * @see com.ext.portlet.ontology.model.impl.FocusAreaOntologyTermModelImpl
 * @generated
 */
public interface FocusAreaOntologyTerm extends FocusAreaOntologyTermModel,
    PersistedModel {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify this interface directly. Add methods to {@link com.ext.portlet.ontology.model.impl.FocusAreaOntologyTermImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
     */
    public void store()
        throws com.liferay.portal.kernel.exception.SystemException;

    public com.ext.portlet.ontology.model.OntologyTerm getTerm()
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException;

    public com.ext.portlet.ontology.model.FocusArea getArea()
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException;
}

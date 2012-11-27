package com.ext.portlet.ontology.model;

import com.liferay.portal.model.PersistedModel;

/**
 * The extended model interface for the FocusArea service. Represents a row in the &quot;ontology_FocusArea&quot; database table, with each column mapped to a property of this class.
 *
 * @author Brian Wing Shun Chan
 * @see FocusAreaModel
 * @see com.ext.portlet.ontology.model.impl.FocusAreaImpl
 * @see com.ext.portlet.ontology.model.impl.FocusAreaModelImpl
 * @generated
 */
public interface FocusArea extends FocusAreaModel, PersistedModel {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify this interface directly. Add methods to {@link com.ext.portlet.ontology.model.impl.FocusAreaImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
     */
    public void store()
        throws com.liferay.portal.kernel.exception.SystemException;

    public java.util.List<com.ext.portlet.ontology.model.OntologyTerm> getTerms()
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException;

    public void removeTerm(java.lang.Long termId)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException;

    public void addTerm(java.lang.Long termId)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException;

    public void tagClass(java.lang.Class clasz, java.lang.Long pk)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException;
}

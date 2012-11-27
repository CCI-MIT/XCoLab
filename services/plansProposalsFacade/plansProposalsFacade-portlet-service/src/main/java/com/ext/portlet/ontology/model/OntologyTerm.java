package com.ext.portlet.ontology.model;

import com.liferay.portal.model.PersistedModel;

/**
 * The extended model interface for the OntologyTerm service. Represents a row in the &quot;ontology_OntologyTerm&quot; database table, with each column mapped to a property of this class.
 *
 * @author Brian Wing Shun Chan
 * @see OntologyTermModel
 * @see com.ext.portlet.ontology.model.impl.OntologyTermImpl
 * @see com.ext.portlet.ontology.model.impl.OntologyTermModelImpl
 * @generated
 */
public interface OntologyTerm extends OntologyTermModel, PersistedModel {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify this interface directly. Add methods to {@link com.ext.portlet.ontology.model.impl.OntologyTermImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
     */
    public void store()
        throws com.liferay.portal.kernel.exception.SystemException;

    public com.ext.portlet.ontology.model.OntologyTerm getParent()
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException;

    public int getChildTermsCount()
        throws com.liferay.portal.kernel.exception.SystemException;

    public java.util.List<com.ext.portlet.ontology.model.OntologyTerm> getChildTerms()
        throws com.liferay.portal.kernel.exception.SystemException;

    public com.ext.portlet.ontology.model.OntologySpace getSpace()
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException;

    public void tagClass(java.lang.Class clasz, java.lang.Long id)
        throws com.liferay.portal.kernel.exception.SystemException;

    public java.util.List<java.lang.Long> findTagedIdsForClass(
        java.lang.Class clasz)
        throws com.liferay.portal.kernel.exception.SystemException;
}

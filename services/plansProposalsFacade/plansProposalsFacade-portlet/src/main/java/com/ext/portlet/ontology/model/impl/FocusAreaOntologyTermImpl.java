package com.ext.portlet.ontology.model.impl;

import com.ext.portlet.ontology.model.FocusArea;
import com.ext.portlet.ontology.model.OntologyTerm;
import com.ext.portlet.ontology.service.FocusAreaLocalServiceUtil;
import com.ext.portlet.ontology.service.FocusAreaOntologyTermLocalServiceUtil;
import com.ext.portlet.ontology.service.OntologyTermLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;

/**
 * The extended model implementation for the FocusAreaOntologyTerm service. Represents a row in the &quot;ontology_FocusAreaOntologyTerm&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * Helper methods and all application logic should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link com.ext.portlet.ontology.model.FocusAreaOntologyTerm} interface.
 * </p>
 *
 * @author Brian Wing Shun Chan
 */
public class FocusAreaOntologyTermImpl extends FocusAreaOntologyTermBaseImpl {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never reference this class directly. All methods that expect a focus area ontology term model instance should use the {@link com.ext.portlet.ontology.model.FocusAreaOntologyTerm} interface instead.
     */
    public FocusAreaOntologyTermImpl() {
    }
    
    public void store() throws SystemException {
        if (isNew()) {
            FocusAreaOntologyTermLocalServiceUtil.addFocusAreaOntologyTerm(this);
        }
        else {
            FocusAreaOntologyTermLocalServiceUtil.updateFocusAreaOntologyTerm(this);
        }
    }
    
    public OntologyTerm getTerm() throws PortalException, SystemException {
        return OntologyTermLocalServiceUtil.getOntologyTerm(getOntologyTermId());
    }
    
    public FocusArea getArea() throws PortalException, SystemException {
        return FocusAreaLocalServiceUtil.getFocusArea(getFocusAreaId());
    }
}

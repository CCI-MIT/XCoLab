package com.ext.portlet.ontology.model.impl;

import java.util.List;

import com.ext.portlet.ontology.model.OntologySpace;
import com.ext.portlet.ontology.model.OntologyTerm;
import com.ext.portlet.ontology.model.OntologyTermEntity;
import com.ext.portlet.ontology.service.OntologySpaceLocalServiceUtil;
import com.ext.portlet.ontology.service.OntologyTermEntityLocalServiceUtil;
import com.ext.portlet.ontology.service.OntologyTermLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.service.ClassNameLocalServiceUtil;

/**
 * The extended model implementation for the OntologyTerm service. Represents a row in the &quot;ontology_OntologyTerm&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * Helper methods and all application logic should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link com.ext.portlet.ontology.model.OntologyTerm} interface.
 * </p>
 *
 * @author Brian Wing Shun Chan
 */
public class OntologyTermImpl extends OntologyTermBaseImpl {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never reference this class directly. All methods that expect a ontology term model instance should use the {@link com.ext.portlet.ontology.model.OntologyTerm} interface instead.
     */
    public OntologyTermImpl() {
    }
    
    public void store() throws SystemException {
        if (isNew()) {
            OntologyTermLocalServiceUtil.addOntologyTerm(this);
        }
        else {
            OntologyTermLocalServiceUtil.updateOntologyTerm(this);
        }
    }
    
    public OntologyTerm getParent() throws PortalException, SystemException {
        if (getParentId() != null) {
            return OntologyTermLocalServiceUtil.getOntologyTerm(getParentId());
        }
        
        return null;
    }
    
    public int getChildTermsCount() throws SystemException {
        return OntologyTermLocalServiceUtil.countChildTerms(getId());
        
    }
    
    public List<OntologyTerm> getChildTerms() throws SystemException {
        return OntologyTermLocalServiceUtil.findByParentId(getId());
        
    }
    
    public OntologySpace getSpace() throws PortalException, SystemException {
        return OntologySpaceLocalServiceUtil.getOntologySpace(getOntologySpaceId());
    }
    
    public void tagClass(Class clasz, Long id) throws SystemException {
        Long classNameId = ClassNameLocalServiceUtil.getClassNameId(clasz);
        
        OntologyTermEntity ote = OntologyTermEntityLocalServiceUtil.createOntologyTermEntity(null);
        ote.setClassNameId(classNameId);
        ote.setTermId(getId());
        ote.setClassPK(id);
        
        ote.store();
    }
    
    public List<Long> findTagedIdsForClass(Class clasz) throws SystemException  {
        Long classNameId = ClassNameLocalServiceUtil.getClassNameId(clasz);
        
        return OntologyTermEntityLocalServiceUtil.findTagedIdsForClass(getId(), clasz);
    }

}

package com.ext.portlet.service.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.ext.portlet.model.FocusAreaOntologyTerm;
import com.ext.portlet.model.OntologySpace;
import com.ext.portlet.model.OntologyTerm;
import com.ext.portlet.model.OntologyTermEntity;
import com.ext.portlet.service.OntologySpaceLocalServiceUtil;
import com.ext.portlet.service.OntologyTermEntityLocalServiceUtil;
import com.ext.portlet.service.OntologyTermLocalService;
import com.ext.portlet.service.OntologyTermLocalServiceUtil;
import com.ext.portlet.service.FocusAreaOntologyTermLocalServiceUtil;
import com.ext.portlet.service.base.OntologyTermLocalServiceBaseImpl;
import com.liferay.counter.service.CounterLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.service.ClassNameLocalServiceUtil;

/**
 * The implementation of the ontology term local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link com.ext.portlet.service.OntologyTermLocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see com.ext.portlet.service.base.OntologyTermLocalServiceBaseImpl
 * @see com.ext.portlet.service.OntologyTermLocalServiceUtil
 */
public class OntologyTermLocalServiceImpl
    extends OntologyTermLocalServiceBaseImpl {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never reference this interface directly. Always use {@link com.ext.portlet.service.OntologyTermLocalServiceUtil} to access the ontology term local service.
     */

    public List<OntologyTerm> findByParentId(Long parentId) throws SystemException {
        return ontologyTermPersistence.findByParentId(parentId);
    }

    
    public List<OntologyTerm> findByParentIdSpaceId(Long parentId, Long spaceId) throws SystemException {
        return ontologyTermPersistence.findByParentIdSpaceId(parentId, spaceId);
    }
    
    
    public OntologyTerm createTerm(Long parentId, String name, Long spaceId, String descriptionUrl) throws SystemException {
        Long termId = CounterLocalServiceUtil.increment(OntologyTerm.class.getName());
        
        OntologyTerm t = createOntologyTerm(termId);
        t.setName(name);
        if (parentId != null) {
        	t.setParentId(parentId);
        }
        t.setOntologySpaceId(spaceId);
        t.setDescriptionUrl(descriptionUrl);
        
        store(t);
        
        return t;
        
    }

    public int countChildTerms(Long parentId) throws SystemException {
        return ontologyTermPersistence.countByParentId(parentId);
    }
    
    public void clearClassTags(Class clasz, Long id) throws SystemException {
        
        Long classNameId = ClassNameLocalServiceUtil.getClassNameId(clasz);
        
        for (OntologyTermEntity ote: ontologyTermEntityPersistence.findByClassNameIdClassPk(classNameId, id)) {
            OntologyTermEntityLocalServiceUtil.remove(ote);
        }
    }
    
    
    public void store(OntologyTerm ontologyTerm) throws SystemException {
        if (ontologyTerm.isNew()) {
            addOntologyTerm(ontologyTerm);
        }
        else {
            ontologyTermPersistence.clearCache();
            updateOntologyTerm(ontologyTerm);
        }
    }
    
    public OntologyTerm getParent(OntologyTerm ontologyTerm) throws PortalException, SystemException {
        if (ontologyTerm.getParentId() > 0) {
            return OntologyTermLocalServiceUtil.getOntologyTerm(ontologyTerm.getParentId());
        }
        
        return null;
    }
    
    public int getChildTermsCount(OntologyTerm ontologyTerm) throws SystemException {
        return OntologyTermLocalServiceUtil.countChildTerms(ontologyTerm.getId());
        
    }
    
    public List<OntologyTerm> getChildTerms(OntologyTerm ontologyTerm) throws SystemException {
        return OntologyTermLocalServiceUtil.findByParentId(ontologyTerm.getId());
    }

    public List<OntologyTerm> getAllDescendantTerms(OntologyTerm ontologyTerm) throws SystemException {
        List<OntologyTerm> terms = new ArrayList<>();

        for (OntologyTerm t : OntologyTermLocalServiceUtil.getChildTerms(ontologyTerm)){
            terms.add(t);
            terms.addAll(OntologyTermLocalServiceUtil.getAllDescendantTerms(t));
        }

        return terms;
    }
    
    public OntologySpace getSpace(OntologyTerm ontologyTerm) throws PortalException, SystemException {
        return OntologySpaceLocalServiceUtil.getOntologySpace(ontologyTerm.getOntologySpaceId());
    }
    
    public void tagClass(OntologyTerm ontologyTerm, Class clasz, Long id) throws SystemException {
        Long classNameId = ClassNameLocalServiceUtil.getClassNameId(clasz);
        
        OntologyTermEntity ote = OntologyTermEntityLocalServiceUtil.createOntologyTermEntity(0);
        ote.setClassNameId(classNameId);
        ote.setTermId(ontologyTerm.getId());
        ote.setClassPK(id);
        
        OntologyTermEntityLocalServiceUtil.store(ote);
    }
    
    public List<Long> findTagedIdsForClass(OntologyTerm ontologyTerm, Class clasz) throws SystemException  {
        Long classNameId = ClassNameLocalServiceUtil.getClassNameId(clasz);
        
        return OntologyTermEntityLocalServiceUtil.findTagedIdsForClass(ontologyTerm.getId(), clasz);
    }


    public Boolean isAnyOntologyTermOfFocusAreaIdADescendantOfOntologyTermId(Long focusAreaId, Long ontologyTermId) throws Exception{

        OntologyTerm ontologyParentTerm = OntologyTermLocalServiceUtil.getOntologyTerm(ontologyTermId);
        List<OntologyTerm> ontologyTermList = OntologyTermLocalServiceUtil.getAllDescendantTerms(ontologyParentTerm);
        ontologyTermList.add(ontologyParentTerm);
        List<FocusAreaOntologyTerm> focusAreaOntologyTerms = FocusAreaOntologyTermLocalServiceUtil.findTermsByFocusArea(focusAreaId);

        Set<Long> ontologyTermIds = new HashSet<>();

        for(OntologyTerm ontologyTerm : ontologyTermList) {
            ontologyTermIds.add(ontologyTerm.getId());
        }

        for (FocusAreaOntologyTerm focusAreaOntologyTerm : focusAreaOntologyTerms) {
            if (ontologyTermIds.contains(focusAreaOntologyTerm.getOntologyTermId())) {
                return true;
            }
        }
        return false;
    }
}

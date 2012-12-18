package com.ext.portlet.ontology.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.ext.portlet.ontology.NoSuchOntologyTermException;
import com.ext.portlet.ontology.model.FocusArea;
import com.ext.portlet.ontology.model.FocusAreaOntologyTerm;
import com.ext.portlet.ontology.model.OntologyTerm;
import com.ext.portlet.ontology.service.FocusAreaLocalServiceUtil;
import com.ext.portlet.ontology.service.FocusAreaOntologyTermLocalServiceUtil;
import com.ext.portlet.ontology.service.OntologyTermLocalServiceUtil;
import com.ext.portlet.ontology.service.base.FocusAreaLocalServiceBaseImpl;
import com.ext.portlet.ontology.service.persistence.FocusAreaOntologyTermPK;
import com.liferay.counter.service.CounterLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;

/**
 * The implementation of the focus area local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link com.ext.portlet.ontology.service.FocusAreaLocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see com.ext.portlet.ontology.service.base.FocusAreaLocalServiceBaseImpl
 * @see com.ext.portlet.ontology.service.FocusAreaLocalServiceUtil
 */
public class FocusAreaLocalServiceImpl extends FocusAreaLocalServiceBaseImpl {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never reference this interface directly. Always use {@link com.ext.portlet.ontology.service.FocusAreaLocalServiceUtil} to access the focus area local service.
     */
    

    public void store(FocusArea focusArea) throws SystemException {
        if (focusArea.isNew()) {
            if (focusArea.getId() <= 0) {
                focusArea.setId(CounterLocalServiceUtil.increment(FocusArea.class.getName()));
            }
            FocusAreaLocalServiceUtil.addFocusArea(focusArea);
        } else {
            FocusAreaLocalServiceUtil.updateFocusArea(focusArea);
        }
    }
    
    public List<OntologyTerm> getTerms(FocusArea focusArea) throws PortalException, SystemException {
        List<OntologyTerm> ret = new ArrayList<OntologyTerm>();
        for (FocusAreaOntologyTerm faot: FocusAreaOntologyTermLocalServiceUtil.findTermsByFocusArea(focusArea.getId())) {
            try {
                ret.add(FocusAreaOntologyTermLocalServiceUtil.getTerm(faot));
            }
            catch (NoSuchOntologyTermException e) {
                // if term has been deleted, remove association
                FocusAreaOntologyTermLocalServiceUtil.deleteFocusAreaOntologyTerm(faot);
            }
        }
        return ret;
    }
    
    public void removeTerm(FocusArea focusArea, Long termId) throws PortalException, SystemException {
        FocusAreaOntologyTermLocalServiceUtil.deleteFocusAreaOntologyTerm(new FocusAreaOntologyTermPK(focusArea.getId(), termId));
    }
    
    public void addTerm(FocusArea focusArea, Long termId) throws PortalException, SystemException {
        OntologyTerm term = OntologyTermLocalServiceUtil.getOntologyTerm(termId);
        /*
        for (OntologyTerm t: getTerms()) {
            if (t.getOntologySpaceId().equals(term.getOntologySpaceId())) {
                // remove term from the same space
                removeTerm(t.getId());
            }
        }*/
        FocusAreaOntologyTerm faot = FocusAreaOntologyTermLocalServiceUtil.createFocusAreaOntologyTerm(
                new FocusAreaOntologyTermPK(focusArea.getId(), termId));
        store(focusArea);
    }
    
    public void tagClass(FocusArea focusArea, Class clasz, Long pk) throws SystemException, PortalException {
        OntologyTermLocalServiceUtil.clearClassTags(clasz, pk);
        for (OntologyTerm t: getTerms(focusArea)) {
            OntologyTermLocalServiceUtil.tagClass(t, clasz, pk);
        }
    }

}

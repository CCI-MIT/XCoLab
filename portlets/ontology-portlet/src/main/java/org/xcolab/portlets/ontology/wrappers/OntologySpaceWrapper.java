package org.xcolab.portlets.ontology.wrappers;

import java.util.ArrayList;
import java.util.List;

import javax.faces.event.ActionEvent;

import com.ext.portlet.model.OntologySpace;
import com.ext.portlet.model.OntologyTerm;
import com.ext.portlet.service.OntologySpaceLocalServiceUtil;
import com.ext.portlet.service.OntologyTermLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;

import edu.emory.mathcs.backport.java.util.Collections;

public class OntologySpaceWrapper {
    private OntologySpace space;
    private List<OntologySpaceTermWrapper> terms = new ArrayList<OntologySpaceTermWrapper>();
    private OntologyTermWrapper newTerm;
    private boolean changingTerm;

    public OntologySpaceWrapper(OntologySpace space, List<OntologyTerm> faSpaceTerms) {
        this.space = space;
        for (OntologyTerm t: faSpaceTerms) {
            terms.add(new OntologySpaceTermWrapper(t, this));
        }
    }

    public OntologySpace getSpace() {
        return space;
    }

    public void setSpace(OntologySpace space) {
        this.space = space;
    }

    public List<OntologySpaceTermWrapper> getTerms() {
        return terms;
    }

    public void setChangingTerm(boolean changingTerm) {
        this.changingTerm = changingTerm;
    }

    public boolean isChangingTerm() {
        return changingTerm;
    }
    
    public void addTerm(ActionEvent e) throws SystemException {
        changingTerm = !changingTerm;
        newTerm = new OntologyTermWrapper(OntologySpaceLocalServiceUtil.getTopTerm(space));
    }

    public void setNewTerm(OntologyTermWrapper newTerm) {
        this.newTerm = newTerm;
    }

    public OntologyTermWrapper getNewTerm() {
        return newTerm;
    }
    
    public List<OntologyTermWrapper> getAllTermParents() throws PortalException, SystemException {
        List<OntologyTermWrapper> ret = new ArrayList<OntologyTermWrapper>();
        
        if (newTerm != null) {
            OntologyTermWrapper t = newTerm;
            
            while (t != null) {
                ret.add(t);
                t = t.getParent();
            }
            
            Collections.reverse(ret);
            
        }
        return ret;
        
    }
    
    public void selectTerm(ActionEvent e) throws PortalException, SystemException {
        Long termId = Long.parseLong(e.getComponent().getAttributes().get("termId").toString());
        
        terms.add(new OntologySpaceTermWrapper(OntologyTermLocalServiceUtil.getOntologyTerm(termId), this));
        changingTerm = !changingTerm;
    }
    
    public void setNewTermId(ActionEvent e) throws PortalException, SystemException {
        Long termId = Long.parseLong(e.getComponent().getAttributes().get("termId").toString());
        newTerm = new OntologyTermWrapper(OntologyTermLocalServiceUtil.getOntologyTerm(termId));
        
    }

    public void remove(OntologySpaceTermWrapper ontologySpaceTermWrapper) {
        terms.remove(ontologySpaceTermWrapper);
        
    }
    

}

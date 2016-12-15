package org.xcolab.portlets.ontology.wrappers;

import com.ext.portlet.model.OntologyTerm;
import com.ext.portlet.service.OntologyTermLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;

import org.xcolab.client.contest.OntologyClientUtil;

import java.util.ArrayList;
import java.util.List;

public class OntologyTermWrapper {
    private OntologyTerm ontologyTerm;

    public OntologyTermWrapper(OntologyTerm t) {
        if (t == null) 
            throw new NullPointerException("OntologyTerm can't be null");
        ontologyTerm = t;
    }
    
    public String getName() {
        return ontologyTerm.getName();
    }
    
    public int getChildTermsCount() throws SystemException {
        return OntologyTermLocalServiceUtil.getChildTermsCount(ontologyTerm);
    }
    
    public long getId() {
        return ontologyTerm.getId();
    }
    
    public OntologyTermWrapper getParent() throws PortalException, SystemException {
        if (ontologyTerm.getParentId() > 0) {
            return new OntologyTermWrapper(OntologyTermLocalServiceUtil.getParent(ontologyTerm));
        }
        return null;
    }
    
    public String getDescriptionUrl() {
        return ontologyTerm.getDescriptionUrl();
    }
    
    public long getParentId() {
        return ontologyTerm.getParentId();
    }
    
    public void setDescriptionUrl(String descriptionUrl) {
        ontologyTerm.setDescriptionUrl(descriptionUrl);
    }
    
    public void setName(String name) {
        ontologyTerm.setName(name);
    }

    public OntologyTerm getWrapped() {
        return ontologyTerm;
    }
    
    public void setParentId(long parentId) {
        ontologyTerm.setParentId(parentId);
    }
    
    public List<OntologyTermWrapper> getChildTerms() throws SystemException {
        List<OntologyTermWrapper> ret = new ArrayList<>();
        for (OntologyTerm t: OntologyTermLocalServiceUtil.getChildTerms(ontologyTerm)) {
            ret.add(new OntologyTermWrapper(t));
        }
        
        return ret;
    }

    public void setWeight(Integer weight) {
        ontologyTerm.setOrder_(weight);
    }

    public Integer getWeight() {
        return ontologyTerm.getOrder_();
    }

    public void printOntologyHierarchy() {
        for(org.xcolab.client.contest.pojo.ontology.OntologyTerm oTerm : OntologyClientUtil.getAllOntologyTerms()) {
            if (oTerm.getParent() == null) {
                printOntologies(OntologyClientUtil.getOntologyTerm(oTerm.getId_()), 0);
            } else if (oTerm.getParent().getId() == 0) {
                printOntologies(OntologyClientUtil.getOntologyTerm(oTerm.getId_()), 0);
            }
        }
    }

    private void printOntologies(org.xcolab.client.contest.pojo.ontology.OntologyTerm term, int depth) {
        for(org.xcolab.client.contest.pojo.ontology.OntologyTerm child : OntologyClientUtil.getChildOntologyTerms(term.getId())) {
            String prefix = "";
            for(int i = 0 ; i <depth; i++) {
                prefix += "; ";
            }
            System.out.println(prefix + child.getId() + "; " + child.getName());
            printOntologies(child, depth +1);
        }
    }
}

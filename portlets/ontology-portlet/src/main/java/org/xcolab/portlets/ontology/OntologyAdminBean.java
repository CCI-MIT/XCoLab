package org.xcolab.portlets.ontology;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Stack;

import javax.faces.event.ActionEvent;
import javax.faces.model.SelectItem;

import org.xcolab.portlets.ontology.wrappers.OntologyTermWrapper;

import com.ext.portlet.model.OntologySpace;
import com.ext.portlet.model.OntologyTerm;
import com.ext.portlet.service.OntologySpaceLocalServiceUtil;
import com.ext.portlet.service.OntologyTermLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;

import edu.emory.mathcs.backport.java.util.Collections;

public class OntologyAdminBean {
    
    private long termId = 0;
    private long spaceId = 0;
    
    private String newTerm;
    private String newTermDescriptionUrl;

    private String newSpace;

    private String newSpaceDescription;
    private Long editedTermId = null;
    
    private List<OntologyTermWrapper> terms = null;
    private boolean reparent;
    
    public List<OntologyTermWrapper> getOntologyTerms() throws SystemException {
        terms = new ArrayList<>();
        for (OntologyTerm t: OntologyTermLocalServiceUtil.findByParentIdSpaceId(termId, spaceId)) {
            terms.add(new OntologyTermWrapper(t));
        }
        
        return terms;
    }
    
    public List<OntologySpace> getOntologySpaces() throws SystemException {
        return OntologySpaceLocalServiceUtil.getOntologySpaces(0, Integer.MAX_VALUE);
    }
    
    
    public void setTermId(ActionEvent e) {
        termId = 0;
        
        try {
            termId = Long.parseLong(e.getComponent().getAttributes().get("termId").toString());
        }
        catch (NumberFormatException ex) {
            // ignore
        }
        
    }
    
    public OntologySpace getSelectedSpace() throws PortalException, SystemException {
        if (spaceId > 0) {
            return OntologySpaceLocalServiceUtil.getOntologySpace(spaceId);
        }
        return null;
    }
    
    public void setSpaceId(ActionEvent e) {
        spaceId = 0;
        termId = 0;
        try {
            if (e.getComponent().getAttributes().get("spaceId") != null) 
                spaceId = Long.parseLong(e.getComponent().getAttributes().get("spaceId").toString());
        }
        catch (NumberFormatException ex) {
            // ignore
        }
    }
    
    public void reparentTerm(ActionEvent e) {
        reparent = !reparent;
    }



    public void setNewTerm(String newTerm) {
        this.newTerm = newTerm;
    }


    public String getNewTerm() {
        return newTerm;
    }
    
    
    
    public void createNewTerm(ActionEvent e) throws SystemException {
        if (spaceId <= 0 || newTerm == null || newTerm.trim().equals("")) {
            return;
        }
        
        // check if there is no such term
        List<OntologyTermWrapper> actTerms = getOntologyTerms();
        for (OntologyTermWrapper t: actTerms) {
            if (t.getName().equals(newTerm)) return;
        }
        
        
        OntologyTermLocalServiceUtil.createTerm(termId, newTerm, spaceId, newTermDescriptionUrl);
        newTerm = "";
        newTermDescriptionUrl = "";
    }
    
    public void createNewSpace(ActionEvent e) throws SystemException {
        if (newSpace == null || newSpace.trim().equals("")) {
            return;
        }
        
        // check if there is no such term
        List<OntologySpace> actSpaces = getOntologySpaces();
        for (OntologySpace t: actSpaces) {
            if (t.getName().equals(newSpace)) return;
        }
        
        
        OntologySpaceLocalServiceUtil.createSpace(newSpace, newSpaceDescription);
        
        newSpace = "";
        newSpaceDescription = "";
    }
    
    public void deleteSpace(ActionEvent e) throws PortalException, SystemException {
        Long spaceId = null;
        try {
            Object space = e.getComponent().getAttributes().get("spaceId");
            if (space != null) {
				spaceId = Long.parseLong(space.toString());
            }

        }
        catch (NumberFormatException ex) {
            // ignore
        }
        if (spaceId != null) {
            removeOntologySpace(spaceId);
        }
        
    }

	public void deleteTerm(ActionEvent e) throws PortalException, SystemException {
		Long termId = null;
		try {
			Object term = e.getComponent().getAttributes().get("termId");
			if (term != null) {
				termId = Long.parseLong(term.toString());
			}

		}
		catch (NumberFormatException ex) {
			// ignore
		}
		if (termId != null) {
			removeNodeWithChildren(termId);
		}

	}
    
    private void removeNodeWithChildren(long nodeId) throws SystemException, PortalException {
        for (OntologyTerm t: OntologyTermLocalServiceUtil.getChildTerms(OntologyTermLocalServiceUtil.getOntologyTerm(nodeId))) {
            removeNodeWithChildren(t.getId());
        }
        OntologyTermLocalServiceUtil.deleteOntologyTerm(nodeId);
        
    }

	private void removeOntologySpace(long spaceId) throws SystemException, PortalException {
		OntologySpaceLocalServiceUtil.deleteOntologySpace(spaceId);
	}
    
    public void editTerm(ActionEvent e) throws PortalException, SystemException {
        Long termId = null;
        reparent = false;
        try {
            Object term = e.getComponent().getAttributes().get("termId");
            if (term != null) {
                termId = Long.parseLong(term.toString());
            }
        }
        catch (NumberFormatException ex) {
            // ignore
        }
        if (termId != null) {
            setEditedTermId(termId);
        }
        else {
            editedTermId = null;
        }
    }
    
    public void updateTerm(ActionEvent e) throws PortalException, SystemException {
        Long termId = null;
        try {
            Object term = e.getComponent().getAttributes().get("termId");
            if (term != null) {
                termId = Long.parseLong(term.toString());
            }

        }
        catch (NumberFormatException ex) {
            // ignore
        }
        if (termId != null) {
            for (OntologyTermWrapper t: terms) {
                if (t.getId() == editedTermId) {
                    OntologyTermLocalServiceUtil.store(t.getWrapped());
                }
            }
            setEditedTermId(termId);
        }
        editedTermId = null;
    }
    
    public List<OntologyTerm> getAllTermParents() throws PortalException, SystemException {
        List<OntologyTerm> ret = new ArrayList<OntologyTerm>();
        
        if (termId > 0) {
            OntologyTerm t = OntologyTermLocalServiceUtil.getOntologyTerm(termId);
            
            while (t != null) {
                ret.add(t);
                if (t.getParentId() > 0) {
                    t = OntologyTermLocalServiceUtil.getParent(t);
                }
                else {
                    t = null;
                }
            }
            
            Collections.reverse(ret);
            
        }
        return ret;
        
    }
    
    public Long getTermId() {
        return termId;
    }


    public String getNewSpace() {
        return newSpace;
    }

    public void setNewSpace(String newSpace) {
        this.newSpace = newSpace;
    }

    public String getNewSpaceDescription() {
        return newSpaceDescription;
    }

    public void setNewSpaceDescription(String newSpaceDescription) {
        this.newSpaceDescription = newSpaceDescription;
    }

    public void setNewTermDescriptionUrl(String newTermDescriptionUrl) {
        this.newTermDescriptionUrl = newTermDescriptionUrl;
    }

    public String getNewTermDescriptionUrl() {
        return newTermDescriptionUrl;
    }

    public void setEditedTermId(Long editedTermId) {
        this.editedTermId = editedTermId;
    }

    public Long getEditedTermId() {
        return editedTermId;
    }
    
    public boolean getReparent() {
        return reparent;
    }
    
    public List<SelectItem> getAllAvailableTermsList() throws SystemException, PortalException {
        List<SelectItem> ret = new ArrayList<SelectItem>();
        
        for (OntologyTerm t: OntologyTermLocalServiceUtil.getOntologyTerms(0, Integer.MAX_VALUE)) {
            
            Stack<OntologyTerm> parentsPath = new Stack<OntologyTerm>();
            OntologyTerm current = t;
            while (current != null) {
                parentsPath.push(current);
                current = OntologyTermLocalServiceUtil.getParent(current);
            }
            
            StringBuilder nameStr = new StringBuilder();
            nameStr.append(OntologyTermLocalServiceUtil.getSpace(t).getName() + " # ");
            while (! parentsPath.isEmpty()) {
                current = parentsPath.pop();
                nameStr.append(" > " + current.getName());
            }
            nameStr.append(" (" + t.getId() + ")");
            ret.add(new SelectItem(current.getId(), nameStr.toString()));
        }
        
        Collections.sort(ret, new Comparator<SelectItem>() {

            @Override
            public int compare(SelectItem o1, SelectItem o2) {
                return o1.getLabel().compareTo(o2.getLabel());
            }
            
        });
        return ret;
    }
    
}

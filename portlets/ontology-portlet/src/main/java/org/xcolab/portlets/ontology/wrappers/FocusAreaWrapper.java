package org.xcolab.portlets.ontology.wrappers;

import java.util.ArrayList;
import java.util.List;

import javax.faces.event.ActionEvent;

import com.ext.portlet.model.FocusArea;
import com.ext.portlet.model.OntologySpace;
import com.ext.portlet.model.OntologyTerm;
import com.ext.portlet.service.FocusAreaLocalServiceUtil;
import com.ext.portlet.service.OntologySpaceLocalServiceUtil;
import com.liferay.counter.service.CounterLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;

public class FocusAreaWrapper {
    private FocusArea area;
    private List<OntologySpaceWrapper> spaces;
   
    
    public FocusAreaWrapper(Long areaId) throws PortalException, SystemException {
        if (areaId == null || areaId <= 0) {
            area = FocusAreaLocalServiceUtil.createFocusArea(CounterLocalServiceUtil.increment(FocusArea.class.getName()));
        }
        else {
            area = FocusAreaLocalServiceUtil.getFocusArea(areaId);
        }
    }

    public FocusArea getArea() {
        return area;
    }
    
    public void save() {
        
    }
    
    public List<OntologySpaceWrapper> getOntologySpaces() throws SystemException, PortalException {
        if (spaces == null) {
            spaces = new ArrayList<OntologySpaceWrapper>();
            
            for (OntologySpace space: OntologySpaceLocalServiceUtil.getOntologySpaces(0, Integer.MAX_VALUE)) {
                List<OntologyTerm> faSpaceTerms = new ArrayList<OntologyTerm>();
                
                for (OntologyTerm t: FocusAreaLocalServiceUtil.getTerms(area)) {
                    if (t.getOntologySpaceId() == space.getId()) {
                        faSpaceTerms.add(t);
                    }
                }
                
                spaces.add(new OntologySpaceWrapper(space, faSpaceTerms));
            }
        }
        return spaces;
    }
    
    public void save(ActionEvent e) throws PortalException, SystemException {
        FocusAreaLocalServiceUtil.store(area);
        for (OntologyTerm t: FocusAreaLocalServiceUtil.getTerms(area)) {
            FocusAreaLocalServiceUtil.removeTerm(area, t.getId());
        }
        for (OntologySpaceWrapper osw: getOntologySpaces()) {
            for (OntologySpaceTermWrapper ostw: osw.getTerms()) {
                
                FocusAreaLocalServiceUtil.addTerm(area, ostw.getTerm().getId());
            }
        }
        
    }
    
    public int getOrder() {
        return area.getOrder();
    }
    
    public void setOrder(int order) {
        area.setOrder(order);
    }
}

package org.xcolab.portlets.ontology.wrappers;

import javax.faces.event.ActionEvent;

import com.ext.portlet.model.OntologyTerm;

public class OntologySpaceTermWrapper {
    
    private OntologyTerm term;
    private OntologySpaceWrapper osw;

    public OntologySpaceTermWrapper(OntologyTerm t, OntologySpaceWrapper osw) {
        this.term = t;
        this.osw = osw;
    }
    
    public OntologyTerm getTerm() {
        return term;
    }
    
    public void remove(ActionEvent e) {
        osw.remove(this);
    }

}

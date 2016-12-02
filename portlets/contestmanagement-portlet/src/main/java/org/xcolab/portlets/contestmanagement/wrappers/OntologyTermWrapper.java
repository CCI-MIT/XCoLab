package org.xcolab.portlets.contestmanagement.wrappers;


import org.xcolab.client.contest.OntologyClientUtil;
import org.xcolab.client.contest.pojo.ontology.OntologyTerm;

import java.util.ArrayList;
import java.util.List;

public class OntologyTermWrapper {

    private OntologyTerm ontologyTerm;

    public OntologyTermWrapper(long ontologyTermId) {
        this.ontologyTerm = OntologyClientUtil.getOntologyTerm(ontologyTermId);
    }

    public OntologyTermWrapper(OntologyTerm ontologyTerm) {
        this.ontologyTerm = ontologyTerm;
    }

    public long getId() {
        return ontologyTerm.getId_();
    }

    public String getName() {
        return ontologyTerm.getName();
    }

    public List<OntologyTermWrapper> getChildren() {
        List<OntologyTermWrapper> children = new ArrayList<>();
        for(OntologyTerm term : OntologyClientUtil.getChildOntologyTerms(this.ontologyTerm.getId())) {
            children.add(new OntologyTermWrapper(term));
        }
        return children;
    }
}


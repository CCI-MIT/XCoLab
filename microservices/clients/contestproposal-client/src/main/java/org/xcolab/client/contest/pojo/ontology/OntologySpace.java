package org.xcolab.client.contest.pojo.ontology;

import org.xcolab.util.http.client.enums.ServiceNamespace;

import java.util.ArrayList;
import java.util.List;

public class OntologySpace extends AbstractOntologySpace {

    public OntologySpace() {}

    public OntologySpace(OntologySpace value) {
        super(value);
    }

    public OntologySpace(AbstractOntologySpace abstractOntologySpace,
            ServiceNamespace serviceNamespace) {
        super(abstractOntologySpace);
    }

    private final List<OntologyTerm> terms = new ArrayList<>();
    private final List<OntologyTerm> rootTerms = new ArrayList<>();

    public void addTerm(OntologyTerm term) {
        terms.add(term);
        if (! term.hasParent()) {
            rootTerms.add(term);
        }
    }
    public List<OntologyTerm> getTerms() {
        return terms;
    }


    public long getId() {
        return this.getId();
    }

    public List<OntologyTerm> getRootTerms() {
        return rootTerms;
    }

    public int getOrder() {
        return this.getOrder_();
    }
}

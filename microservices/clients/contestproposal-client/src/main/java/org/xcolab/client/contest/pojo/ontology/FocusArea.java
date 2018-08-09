package org.xcolab.client.contest.pojo.ontology;


import org.xcolab.util.http.client.enums.ServiceNamespace;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class FocusArea extends AbstractFocusArea implements Serializable {

    private static final long serialVersionUID = 1L;

    private final List<OntologyTerm> ontologyTerms = new ArrayList<>();
    private final List<Long> ontologyTermsIds = new ArrayList<>();

    public FocusArea() {}

    public FocusArea(FocusArea value) {
        super(value);
    }

    public FocusArea(Long id, String name, Integer order_) {
        super(id, name, order_);
    }

    public FocusArea(AbstractFocusArea abstractFocusArea, ServiceNamespace serviceNamespace) {
        super(abstractFocusArea);
    }


    public FocusArea getWrapped() {
        return this;
    }

    public List<OntologyTerm> getOntologyTerms() {
        return ontologyTerms;
    }

    public void addOntologyTerm(OntologyTerm term) {
        if (term != null) {
            ontologyTerms.add(term);
            ontologyTermsIds.add(term.getId());
        }
    }

    public String getTermsIdsStr() {
        return ontologyTermsIds.toString();
    }
}

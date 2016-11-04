package org.xcolab.client.contest.pojo.ontology;

import org.xcolab.util.http.client.RestService;

import java.util.ArrayList;
import java.util.List;

public class FocusArea extends AbstractFocusArea {

    private List<OntologyTerm> ontologyTerms = new ArrayList<>();
    private List<Long> ontologyTermsIds = new ArrayList<>();

    public FocusArea() {}

    public FocusArea(FocusArea value) {
        super(value);
    }

    public FocusArea(Long id_, String name, Integer order_) {
        super(id_, name, order_);
    }

    public FocusArea(AbstractFocusArea abstractFocusArea, RestService restService) {
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

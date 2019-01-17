package org.xcolab.client.contest.pojo;

import org.springframework.core.ParameterizedTypeReference;

import org.xcolab.client.contest.pojo.tables.pojos.FocusArea;
import org.xcolab.util.http.client.types.TypeProvider;

import java.util.ArrayList;
import java.util.List;

public class FocusAreaWrapper extends FocusArea {

    public static final TypeProvider<FocusAreaWrapper> TYPES = new TypeProvider<>(FocusAreaWrapper.class,
            new ParameterizedTypeReference<List<FocusAreaWrapper>>() {});

    private static final long serialVersionUID = 1L;

    private final List<OntologyTermWrapper> ontologyTerms = new ArrayList<>();
    private final List<Long> ontologyTermsIds = new ArrayList<>();

    public FocusAreaWrapper() {}

    public FocusAreaWrapper(FocusAreaWrapper value) {
        super(value);
    }

    public FocusAreaWrapper(Long id, String name, Integer order_) {
        super(id, name, order_);
    }

    public FocusAreaWrapper(FocusArea abstractFocusArea) {
        super(abstractFocusArea);
    }

    public List<OntologyTermWrapper> getOntologyTerms() {
        return ontologyTerms;
    }

    public void addOntologyTerm(OntologyTermWrapper term) {
        if (term != null) {
            ontologyTerms.add(term);
            ontologyTermsIds.add(term.getId());
        }
    }

    public String getTermsIdsStr() {
        return ontologyTermsIds.toString();
    }
}

package org.xcolab.client.contest.pojo.wrapper;

import org.springframework.core.ParameterizedTypeReference;

import org.xcolab.client.contest.pojo.tables.pojos.OntologySpace;
import org.xcolab.util.http.client.types.TypeProvider;

import java.util.ArrayList;
import java.util.List;

public class OntologySpaceWrapper extends OntologySpace {

    public static final TypeProvider<OntologySpaceWrapper> TYPES = new TypeProvider<>(
            OntologySpaceWrapper.class,
            new ParameterizedTypeReference<List<OntologySpaceWrapper>>() {});

    public OntologySpaceWrapper() {}

    public OntologySpaceWrapper(OntologySpaceWrapper value) {
        super(value);
    }

    public OntologySpaceWrapper(OntologySpace abstractOntologySpace) {
        super(abstractOntologySpace);
    }

    private final List<OntologyTermWrapper> terms = new ArrayList<>();
    private final List<OntologyTermWrapper> rootTerms = new ArrayList<>();

    public void addTerm(OntologyTermWrapper term) {
        terms.add(term);
        if (! term.hasParent()) {
            rootTerms.add(term);
        }
    }
    public List<OntologyTermWrapper> getTerms() {
        return terms;
    }

    public List<OntologyTermWrapper> getRootTerms() {
        return rootTerms;
    }

    public int getOrder() {
        return this.getSortOrder();
    }
}

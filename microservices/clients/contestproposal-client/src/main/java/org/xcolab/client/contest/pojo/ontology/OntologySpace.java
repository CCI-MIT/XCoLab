package org.xcolab.client.contest.pojo.ontology;

import org.springframework.core.ParameterizedTypeReference;

import org.xcolab.util.http.client.types.TypeProvider;

import java.util.ArrayList;
import java.util.List;

public class OntologySpace extends AbstractOntologySpace {

    public static final TypeProvider<OntologySpace> TYPES = new TypeProvider<>(OntologySpace.class,
            new ParameterizedTypeReference<List<OntologySpace>>() {});

    public OntologySpace() {}

    public OntologySpace(OntologySpace value) {
        super(value);
    }

    public OntologySpace(AbstractOntologySpace abstractOntologySpace) {
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

    public List<OntologyTerm> getRootTerms() {
        return rootTerms;
    }

    public int getOrder() {
        return this.getSortOrder();
    }
}

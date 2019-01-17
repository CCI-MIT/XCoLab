package org.xcolab.client.contest.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import org.springframework.core.ParameterizedTypeReference;

import org.xcolab.util.http.client.types.TypeProvider;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(Include.NON_NULL)
public class OntologySpace extends AbstractOntologySpace implements Serializable {

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

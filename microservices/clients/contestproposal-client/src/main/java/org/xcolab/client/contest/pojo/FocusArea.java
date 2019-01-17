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
public class FocusArea extends AbstractFocusArea implements Serializable {

    public static final TypeProvider<FocusArea> TYPES = new TypeProvider<>(FocusArea.class,
            new ParameterizedTypeReference<List<FocusArea>>() {});

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

    public FocusArea(AbstractFocusArea abstractFocusArea) {
        super(abstractFocusArea);
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

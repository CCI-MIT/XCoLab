package org.xcolab.client.contest.pojo.wrapper;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import org.xcolab.client.contest.pojo.tables.pojos.FocusArea;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@JsonInclude(Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class FocusAreaWrapper extends FocusArea implements Serializable {

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

    @JsonIgnore
    public List<OntologyTermWrapper> getOntologyTerms() {
        return ontologyTerms;
    }

    @JsonIgnore
    public void addOntologyTerm(OntologyTermWrapper term) {
        if (term != null) {
            ontologyTerms.add(term);
            ontologyTermsIds.add(term.getId());
        }
    }

    @JsonIgnore
    public String getTermsIdsStr() {
        return ontologyTermsIds.toString();
    }
}

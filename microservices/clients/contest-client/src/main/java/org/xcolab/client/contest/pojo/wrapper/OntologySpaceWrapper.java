package org.xcolab.client.contest.pojo.wrapper;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import org.xcolab.client.contest.pojo.tables.pojos.OntologySpace;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@JsonInclude(Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class OntologySpaceWrapper extends OntologySpace implements Serializable {

    public OntologySpaceWrapper() {}

    public OntologySpaceWrapper(OntologySpaceWrapper value) {
        super(value);
    }

    public OntologySpaceWrapper(OntologySpace abstractOntologySpace) {
        super(abstractOntologySpace);
    }

    private final List<OntologyTermWrapper> terms = new ArrayList<>();
    private final List<OntologyTermWrapper> rootTerms = new ArrayList<>();

    @JsonIgnore
    public void addTerm(OntologyTermWrapper term) {
        terms.add(term);
        if (!term.hasParent()) {
            rootTerms.add(term);
        }
    }

    @JsonIgnore
    public List<OntologyTermWrapper> getTerms() {
        return terms;
    }

    @JsonIgnore
    public List<OntologyTermWrapper> getRootTerms() {
        return rootTerms;
    }

    @JsonIgnore
    public int getOrder() {
        return this.getSortOrder();
    }
}

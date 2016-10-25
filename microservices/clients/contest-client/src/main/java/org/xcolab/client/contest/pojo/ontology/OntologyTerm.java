package org.xcolab.client.contest.pojo.ontology;

import com.fasterxml.jackson.annotation.JsonIgnore;

import org.xcolab.util.http.client.RestService;

public class OntologyTerm extends AbstractOntologyTerm {

    public OntologyTerm() {}

    public OntologyTerm(OntologyTerm value) {
        super(value);
    }

    public OntologyTerm(Long id_, Long parentid, Long ontologyspaceid, String name,
            String descriptionurl, Integer order_) {
        super(id_, parentid, ontologyspaceid, name, descriptionurl, order_);
    }

    public OntologyTerm(AbstractOntologyTerm abstractOntologyTerm, RestService restService) {
        super(abstractOntologyTerm);
    }

    @JsonIgnore
    public Long getId() {
        return getId_();
    }
}

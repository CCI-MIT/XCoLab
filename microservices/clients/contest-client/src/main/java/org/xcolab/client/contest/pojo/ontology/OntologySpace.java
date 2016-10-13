package org.xcolab.client.contest.pojo.ontology;

import org.xcolab.util.http.client.RestService;

public class OntologySpace extends AbstractOntologySpace {

    public OntologySpace() {}

    public OntologySpace(OntologySpace value) {
        super(value);
    }

    public OntologySpace(AbstractOntologySpace abstractOntologySpace, RestService restService) {
        super(abstractOntologySpace);
    }
}

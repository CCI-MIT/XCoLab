package org.xcolab.client.contest.pojo.ontology;

import org.xcolab.util.http.client.enums.ServiceNamespace;

public class FocusAreaOntologyTerm extends AbstractFocusAreaOntologyTerm {

    public FocusAreaOntologyTerm() {}

    public FocusAreaOntologyTerm(FocusAreaOntologyTerm value) {
        super(value);
    }

    public FocusAreaOntologyTerm(Long focusareaid, Long ontologytermid, Integer order_) {
        super(focusareaid, ontologytermid, order_);
    }

    public FocusAreaOntologyTerm(AbstractFocusAreaOntologyTerm abstractFocusAreaOntologyTerm,
            ServiceNamespace serviceNamespace) {
        super(abstractFocusAreaOntologyTerm);
    }
}

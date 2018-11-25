package org.xcolab.client.contest.pojo.ontology;

import org.springframework.core.ParameterizedTypeReference;

import org.xcolab.util.http.client.types.TypeProvider;

import java.util.List;

public class FocusAreaOntologyTerm extends AbstractFocusAreaOntologyTerm {

    public static final TypeProvider<FocusAreaOntologyTerm> TYPES =
            new TypeProvider<>(FocusAreaOntologyTerm.class,
                    new ParameterizedTypeReference<List<FocusAreaOntologyTerm>>() {});

    public FocusAreaOntologyTerm() {}

    public FocusAreaOntologyTerm(FocusAreaOntologyTerm value) {
        super(value);
    }

    public FocusAreaOntologyTerm(Long focusareaid, Long ontologytermid, Integer order_) {
        super(focusareaid, ontologytermid, order_);
    }

    public FocusAreaOntologyTerm(AbstractFocusAreaOntologyTerm abstractFocusAreaOntologyTerm) {
        super(abstractFocusAreaOntologyTerm);
    }
}

package org.xcolab.client.contest.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import org.springframework.core.ParameterizedTypeReference;

import org.xcolab.util.http.client.types.TypeProvider;

import java.io.Serializable;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(Include.NON_NULL)
public class FocusAreaOntologyTerm extends AbstractFocusAreaOntologyTerm implements Serializable {

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

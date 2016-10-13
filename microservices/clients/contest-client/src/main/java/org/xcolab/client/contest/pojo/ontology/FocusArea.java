package org.xcolab.client.contest.pojo.ontology;

import org.xcolab.util.http.client.RestService;

public class FocusArea extends AbstractFocusArea {

    public FocusArea() {}

    public FocusArea(FocusArea value) {
        super(value);
    }

    public FocusArea(Long id_, String name, Integer order_) {
        super(id_, name, order_);
    }

    public FocusArea(AbstractFocusArea abstractFocusArea, RestService restService) {
        super(abstractFocusArea);
    }
}

package org.xcolab.view.taglibs.xcolab.wrapper;

import java.util.ArrayList;
import java.util.List;

public class OntologySpaceWrapper {

    private final org.xcolab.client.contest.pojo.wrapper.OntologySpaceWrapper wrapped;
    private final List<OntologyTermWrapper> terms = new ArrayList<>();
    private final List<OntologyTermWrapper> rootTerms = new ArrayList<>();

    public OntologySpaceWrapper(org.xcolab.client.contest.pojo.wrapper.OntologySpaceWrapper wrapped) {
        super();
        this.wrapped = wrapped;
    }

    public void addTerm(OntologyTermWrapper term) {
        terms.add(term);
        if (!term.hasParent()) {
            rootTerms.add(term);
        }
    }

    public List<OntologyTermWrapper> getTerms() {
        return terms;
    }

    public String getName() {
        return wrapped.getName();
    }

    public String getDescription() {
        return wrapped.getDescription();
    }

    public long getId() {
        return wrapped.getId();
    }

    public List<OntologyTermWrapper> getRootTerms() {
        return rootTerms;
    }

    public int getOrder() {
        return wrapped.getOrder();
    }
}

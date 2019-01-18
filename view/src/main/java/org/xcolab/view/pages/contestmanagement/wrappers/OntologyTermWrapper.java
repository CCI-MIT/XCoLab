package org.xcolab.view.pages.contestmanagement.wrappers;

import org.xcolab.client.contest.OntologyClientUtil;

import java.util.ArrayList;
import java.util.List;

public class OntologyTermWrapper {

    private final org.xcolab.client.contest.pojo.wrapper.OntologyTermWrapper ontologyTerm;

    public OntologyTermWrapper(long ontologyTermId) {
        this.ontologyTerm = OntologyClientUtil.getOntologyTerm(ontologyTermId);
    }

    public OntologyTermWrapper(
            org.xcolab.client.contest.pojo.wrapper.OntologyTermWrapper ontologyTerm) {
        this.ontologyTerm = ontologyTerm;
    }

    public long getId() {
        return ontologyTerm.getId();
    }

    public String getName() {
        return ontologyTerm.getName();
    }

    public List<OntologyTermWrapper> getChildren() {
        List<OntologyTermWrapper> children = new ArrayList<>();
        for (org.xcolab.client.contest.pojo.wrapper.OntologyTermWrapper term : OntologyClientUtil
                .getChildOntologyTerms(this.ontologyTerm.getId())) {
            children.add(new OntologyTermWrapper(term));
        }
        return children;
    }
}


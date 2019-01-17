package org.xcolab.view.pages.contestmanagement.wrappers;

import org.xcolab.client.contest.OntologyClientUtil;
import org.xcolab.client.contest.pojo.OntologyTerm;

import java.util.ArrayList;
import java.util.List;

public class OntologyTermWrapper {

    private final OntologyTerm ontologyTerm;

    public OntologyTermWrapper(long ontologyTermId) {
        this.ontologyTerm = OntologyClientUtil.getOntologyTerm(ontologyTermId);
    }

    public OntologyTermWrapper(OntologyTerm ontologyTerm) {
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
        for (OntologyTerm term : OntologyClientUtil
                .getChildOntologyTerms(this.ontologyTerm.getId())) {
            children.add(new OntologyTermWrapper(term));
        }
        return children;
    }
}


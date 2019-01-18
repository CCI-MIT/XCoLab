package org.xcolab.client.contest.pojo.wrapper;

import org.springframework.core.ParameterizedTypeReference;

import org.xcolab.client.contest.pojo.tables.pojos.OntologyTerm;
import org.xcolab.util.http.client.types.TypeProvider;

import java.util.ArrayList;
import java.util.List;

public class OntologyTermWrapper extends OntologyTerm {

    public static final TypeProvider<OntologyTermWrapper> TYPES = new TypeProvider<>(
            OntologyTermWrapper.class,
            new ParameterizedTypeReference<List<OntologyTermWrapper>>() {});

    private final List<OntologyTermWrapper> children = new ArrayList<>();
    private OntologyTermWrapper parent;

    public OntologyTermWrapper() {}

    public OntologyTermWrapper(OntologyTermWrapper value) {
        super(value);
    }

    public OntologyTermWrapper(Long id, Long parentid, Long ontologyspaceid, String name,
            String descriptionurl, Integer order_) {
        super(id, parentid, ontologyspaceid, name, descriptionurl, order_);
    }

    public OntologyTermWrapper(OntologyTerm abstractOntologyTerm) {
        super(abstractOntologyTerm);
    }

    public OntologyTermWrapper getParent() {
        return parent;
    }

    public void setParent(OntologyTermWrapper parent) {
        if (parent != null) {
            this.parent = parent;
            parent.children.add(this);
        }
    }

    public List<OntologyTermWrapper> getChildren() {
        return children;
    }

    public boolean hasParent() {
        return this.getParentId() > 0;
    }

    public int getOrder() {
        return this.getSortOrder();
    }
}

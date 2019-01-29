package org.xcolab.view.taglibs.xcolab.wrapper;

import java.util.ArrayList;
import java.util.List;

public class OntologyTermWrapper {

    private final org.xcolab.client.contest.pojo.wrapper.OntologyTermWrapper wrapped;
    private final List<OntologyTermWrapper> children = new ArrayList<>();
    private OntologyTermWrapper parent;

    public OntologyTermWrapper(org.xcolab.client.contest.pojo.wrapper.OntologyTermWrapper wrapped) {
        this.wrapped = wrapped;
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

    public String getName() {
        return wrapped.getName();
    }

    public long getParentId() {
        return wrapped.getParentId();
    }

    public boolean hasParent() {
        return wrapped.getParentId() > 0;
    }

    public long getId() {
        return wrapped.getId();
    }

    public long getOntologySpaceId() {
        return wrapped.getOntologySpaceId();
    }

    public int getOrder() {
        return wrapped.getSortOrder();
    }
}

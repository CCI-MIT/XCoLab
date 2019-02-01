package org.xcolab.client.contest.pojo.wrapper;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import org.xcolab.client.contest.pojo.tables.pojos.OntologyTerm;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@JsonInclude(Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class OntologyTermWrapper extends OntologyTerm implements Serializable {

    private final List<OntologyTermWrapper> children = new ArrayList<>();
    private OntologyTermWrapper parent;

    public OntologyTermWrapper() {}

    public OntologyTermWrapper(OntologyTermWrapper value) {
        super(value);
    }

    public OntologyTermWrapper(Long id, Long parentId, Long ontologySpaceId, String name,
            String descriptionUrl, Integer order) {
        super(id, parentId, ontologySpaceId, name, descriptionUrl, order);
    }

    public OntologyTermWrapper(OntologyTerm abstractOntologyTerm) {
        super(abstractOntologyTerm);
    }

    @JsonIgnore
    public OntologyTermWrapper getParent() {
        return parent;
    }

    @JsonIgnore
    public void setParent(OntologyTermWrapper parent) {
        if (parent != null) {
            this.parent = parent;
            parent.children.add(this);
        }
    }

    @JsonIgnore
    public List<OntologyTermWrapper> getChildren() {
        return children;
    }

    @JsonIgnore
    public boolean hasParent() {
        return this.getParentId() > 0;
    }

    @JsonIgnore
    public int getOrder() {
        return this.getSortOrder();
    }
}

package org.xcolab.client.contest.pojo;

import java.io.Serializable;
import java.util.Objects;

public abstract class AbstractContestCollectionCard implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long    id;
    private Long    parent;
    private Long    bigOntologyTerm;
    private Long    smallOntologyTerm;
    private String  description;
    private String  shortName;
    private Boolean visible;
    private Integer order;
    private Long    ontologyTermToLoad;
    private Boolean onlyFeatured;

    public AbstractContestCollectionCard() {}

    public AbstractContestCollectionCard(AbstractContestCollectionCard value) {
        this.id = value.id;
        this.parent = value.parent;
        this.bigOntologyTerm = value.bigOntologyTerm;
        this.smallOntologyTerm = value.smallOntologyTerm;
        this.description = value.description;
        this.shortName = value.shortName;
        this.visible = value.visible;
        this.order = value.order;
        this.ontologyTermToLoad = value.ontologyTermToLoad;
        this.onlyFeatured = value.onlyFeatured;
    }

    public AbstractContestCollectionCard(
            Long    id,
            Long    parent,
            Long    bigOntologyTerm,
            Long    smallOntologyTerm,
            String  description,
            String  shortName,
            Boolean visible,
            Integer order,
            Long    ontologyTermToLoad,
            Boolean onlyFeatured
    ) {
        this.id = id;
        this.parent = parent;
        this.bigOntologyTerm = bigOntologyTerm;
        this.smallOntologyTerm = smallOntologyTerm;
        this.description = description;
        this.shortName = shortName;
        this.visible = visible;
        this.order = order;
        this.ontologyTermToLoad = ontologyTermToLoad;
        this.onlyFeatured = onlyFeatured;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getParent() {
        return this.parent;
    }

    public void setParent(Long parent) {
        this.parent = parent;
    }

    public Long getBig_ontology_term() {
        return this.bigOntologyTerm;
    }

    public void setBig_ontology_term(Long bigOntologyTerm) {
        this.bigOntologyTerm = bigOntologyTerm;
    }

    public Long getSmall_ontology_term() {
        return this.smallOntologyTerm;
    }

    public void setSmall_ontology_term(Long smallOntologyTerm) {
        this.smallOntologyTerm = smallOntologyTerm;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getShort_name() {
        return this.shortName;
    }

    public void setShort_name(String shortName) {
        this.shortName = shortName;
    }

    public Boolean getVisible() {
        return this.visible;
    }

    public void setVisible(Boolean visible) {
        this.visible = visible;
    }

    public Integer getOrder() {
        return this.order;
    }

    public void setOrder(Integer order) {
        this.order = order;
    }

    public Long getOntology_term_to_load() {
        return this.ontologyTermToLoad;
    }

    public void setOntology_term_to_load(Long ontologyTermToLoad) {
        this.ontologyTermToLoad = ontologyTermToLoad;
    }

    public Boolean getOnly_featured() {
        return this.onlyFeatured;
    }

    public void setOnly_featured(Boolean onlyFeatured) {
        this.onlyFeatured = onlyFeatured;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof AbstractContestCollectionCard)) {
            return false;
        }
        AbstractContestCollectionCard that = (AbstractContestCollectionCard) o;
        return Objects.equals(getId(), that.getId())
                && Objects.equals(getParent(), that.getParent())
                && Objects.equals(bigOntologyTerm, that.bigOntologyTerm)
                && Objects.equals(smallOntologyTerm, that.smallOntologyTerm)
                && Objects.equals(getDescription(), that.getDescription())
                && Objects.equals(shortName, that.shortName)
                && Objects.equals(getVisible(), that.getVisible())
                && Objects.equals(getOrder(), that.getOrder())
                && Objects.equals(ontologyTermToLoad, that.ontologyTermToLoad)
                && Objects.equals(onlyFeatured, that.onlyFeatured);
    }

    @Override
    public int hashCode() {
        return Objects
                .hash(getId(), getParent(), bigOntologyTerm, smallOntologyTerm, getDescription(),
                        shortName, getVisible(), getOrder(), ontologyTermToLoad, onlyFeatured);
    }

    @Override
    public String toString() {

        return "ContestCollectionCard (" + id + ", " + parent + ", " + bigOntologyTerm + ", "
                + smallOntologyTerm + ", " + description + ", " + shortName + ", " + visible + ", "
                + order + ", " + ontologyTermToLoad + ", " + onlyFeatured + ")";
    }
}

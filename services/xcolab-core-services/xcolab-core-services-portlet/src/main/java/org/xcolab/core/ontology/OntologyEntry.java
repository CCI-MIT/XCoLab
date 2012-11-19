package org.xcolab.core.ontology;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * Ontology entry is an entry the ontology used to describe elements of the problem
 * space this instance of the xcolab is intended to function with.  The ontology can be though
 * as a means for indexing the space of possible solutions.
 *
 *
 *
 * User: jintrone
 * Date: 11/14/12
 * Time: 5:09 PM
 */
public interface OntologyEntry {

    /**
     * Within a dimension, an ontology entry has a single parent
     *
     * @return The parent of the entry, or null if this is a top level item
     */
    OntologyEntry getParent();

    /**
     * An ontology entry may have multiple children
     *
     * @return The children of this entry; returns an empty list if no children.  Sort order is
     * stable
     */
    List<OntologyEntry> getChildren();


    /**
     * Returns the dimension of the ontology that this terms resides in
     *
     * @return The dimension for this entry
     */
    OntologyDimension getDimension();

    /**
     * @return A short name used to refer to this entity
     */
    String getShortName();

    /**
     *
     * @return
     */
    String getDescription();

    /**
     * A url associated with this ontology
     *
     * @return
     */
    String getUrl();

    /**
     * Return all entities associate with this url of a certain type
     *
     * @param clazz
     * @param <T>
     * @return
     */
    <T> Collection<T> getAssociatedEntities(Class<T> clazz);


}

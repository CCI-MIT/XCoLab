package org.xcolab.core.ontology;

import java.util.List;

/**
 * User: jintrone
 * Date: 11/16/12
 * Time: 2:11 PM
 */
public interface HasOntologyEntries {

     public void addOntologyEntry(OntologyEntry entry);

    public List<OntologyEntry> getOntologyEntries();

    public List<OntologyEntry> getOntologyEntries(OntologyDimension dimension);
}

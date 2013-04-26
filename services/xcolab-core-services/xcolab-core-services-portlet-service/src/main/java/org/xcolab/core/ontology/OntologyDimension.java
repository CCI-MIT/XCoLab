package org.xcolab.core.ontology;

import java.util.List;

/**
 * User: jintrone
 * Date: 11/14/12
 * Time: 5:09 PM
 */
public interface OntologyDimension {

    public String getShortName();

    public String getDescription();

    public List<OntologyEntry> getRoots();

}

package org.xcolab.core.proposals.template;


import org.xcolab.core.documententity.DocumentEntityWrapper;
import org.xcolab.core.ontology.HasOntologyEntries;
import org.xcolab.core.ontology.OntologyDimension;
import org.xcolab.core.ontology.OntologyEntry;

import java.util.List;

/**
 * User: jintrone
 * Date: 11/15/12
 * Time: 2:43 PM
 */
public interface ProposalTemplateSection extends DocumentEntityWrapper, HasOntologyEntries {
    String getName();

    void setName(String s);

    /**
     * Help text is provided for editors, and is not envisioned as being made
     * available to people reading the propoals.
     *
     * @return
     */
    String getHelpText();

    void setHelpText(String s);








}

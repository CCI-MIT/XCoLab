package org.xcolab.core.proposals.template;

import org.xcolab.core.documententity.DocumentEntityWrapper;

/**
 * User: jintrone
 * Date: 11/15/12
 * Time: 2:43 PM
 */
public interface ProposalTemplateSection extends DocumentEntityWrapper {
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

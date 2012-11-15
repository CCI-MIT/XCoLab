package org.xcolab.core.proposals.template;

import org.xcolab.core.documententity.DocumentEntityWrapper;

/**
 * Defines a section of a {@link ProposalTemplate}.  ProposalTemplateSections have a life
 * outside of a given template, and so may be reused in multiple templates.
 *
 * User: jintrone
 * Date: 11/14/12
 * Time: 10:21 PM
 */
public interface ProposalTemplateSection extends DocumentEntityWrapper{

    public String getName();
    public void setName(String s);

    /**
     * Help text is provided for editors, and is not envisioned as being made
     * available to people reading the propoals.
     *
     * @return
     */
    public String getHelpText();
    public void setHelpText(String s);

    /**
     * Default text that should be used to pre-populate a field with information.
     * If the section is locked, this text will be persisted with the {@link org.xcolab.core.proposals.ProposalSection}
     *
     * @return The default text
     */
    public String getDefaultText();
    public void setDefaultText(String s);

    /**
     *
     * @return The maximum number of characters allowed in this field, or -1 if unlimited.
     */
    public int getMaxCharacters();
    public void setMaxCharacters(int x);


    /**
     *
     * @return Whether or not a plan section based on this section is editable by the end-user
     */
    public boolean isLocked();
    public boolean setLocked(boolean b);




}

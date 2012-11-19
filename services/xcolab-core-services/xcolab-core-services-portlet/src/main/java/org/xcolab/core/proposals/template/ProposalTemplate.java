package org.xcolab.core.proposals.template;

import org.xcolab.core.documententity.DocumentEntityWrapper;

import java.util.Collection;
import java.util.List;

/**
 * A proposal template describes the structure of a proposal.  It
 * is mostly an aggregate of {@link ProposalTemplateTextSection}s.  The
 * proposal template covers all elements of a proposal. These elements are
 * grouped by a set of labels, intended to provide a functional grouping that
 * clients can use to render the proposal.  Groups are not ordered, but items
 * within groups are.
 *
 * Note that groups are a property of the template, and not of the section.
 *
 *
 *
 * User: jintrone
 * Date: 11/14/12
 * Time: 10:21 PM
 */
public interface ProposalTemplate extends DocumentEntityWrapper{


    public String getName();

    public void setName(String name);

     /**
     * Retrieve a list of groups used by this proposal template
     * @return
     */
    public Collection<String> getGroups();

    /**
     * Add a new group for organizing this template.
     *
     * @param group
     * @return whether or not the add was successful.  Fails if the group already exists.
     */
    public boolean addGroup(String group);

    /**
        * Removes a group from this template.
        * @param group
        * @return A list of proposal template sections that were previously associated with this group.
        */
       public List<ProposalTemplateSection> removeGroup(String group);



    /**
     * Add a set of proposal sections to the end of the list of sections in the specified group.
     *
     * @param group
     * @param section
     * @throws ProposalTemplateStructureException if no such group exists
     */
    public void addSections(String group, ProposalTemplateSection... section) throws ProposalTemplateStructureException;


     /**
     * Insert a section a a position in a group
     *
     * @param group
     * @param section
     * @throws ProposalTemplateStructureException if no such group exists, ArrayIndexOutOfBounds if the position is
      *  &lt;0 or &gt; the number of existing selections.
     */
    public void insertSection(String group, ProposalTemplateSection section, int position) throws ProposalTemplateStructureException;


    /**
     * Removes the specified section from the group
     *
     * @param group
     * @return True if the removal was successful
     */
    public boolean removeSection(String group, ProposalTemplateSection section);

    /**
     * Find the groups that this section is a part of.
     *
     * @param section
     * @return A list of groups that a section appears in
     */
    public List<String> findSectionGroups(ProposalTemplateSection section);

    /**
     * Get all sections for this group. Sort order is stable and can be adjusted
     * @param group
     * @return A list of proposal sections
     */
    public List<ProposalTemplateSection> getSectionsInGroup(String group);


    /**
     * Performs a shallow copy of the original proposal
     * @param original
     * @return
     */
    public ProposalTemplate copy(ProposalTemplate original);




}

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
     * Adds a proposal section to the end of the list of sections in the specified group.
     * Creates the group if it does not already exist
     *
     * @param group
     * @param section
     */
    public void addSection(String group, ProposalTemplateSection section);

    public void insertSection(ProposalTemplateSection section, String group);


    public void setSectionGroup(ProposalTemplateSection section, String group);

    public String getSectionGroup(ProposalTemplateSection section);

    public Collection<String> getSectionGroups();

    public List<ProposalTemplateSection> getSectionsForGroup(String group);



    public ProposalTemplateSection removeSection(int i);

    public void setSection(List<ProposalTemplateSection> sections);

    /**
     * Performs a shallow copy of the original proposal
     * @param original
     * @return
     */
    public ProposalTemplate copy(ProposalTemplate original);




}

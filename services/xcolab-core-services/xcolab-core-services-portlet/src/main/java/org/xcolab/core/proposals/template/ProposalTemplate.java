package org.xcolab.core.proposals.template;

import org.xcolab.core.documententity.DocumentEntityWrapper;

import java.util.List;

/**
 * A proposal template describes the structure of a proposal.  It
 * is mostly an aggregate of {@link ProposalTemplateSection}s.  The
 * proposal template covers the text / images, and not other structured
 * content within a proposal.
 *
 * User: jintrone
 * Date: 11/14/12
 * Time: 10:21 PM
 */
public interface ProposalTemplate extends DocumentEntityWrapper{

    public String getName();

    public void setName(String name);

    public List<ProposalTemplateSection>  getSections();

    public void insertSection(int index);

    public void addSection(ProposalTemplateSection section);

    public ProposalTemplateSection removeSection(int i);

    public void setSection(List<ProposalTemplateSection> sections);



}

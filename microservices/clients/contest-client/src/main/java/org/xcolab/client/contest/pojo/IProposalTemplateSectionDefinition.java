package org.xcolab.client.contest.pojo;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import org.xcolab.client.contest.pojo.wrapper.ProposalTemplateSectionDefinitionWrapper;

@JsonDeserialize(as = ProposalTemplateSectionDefinitionWrapper.class)
public interface IProposalTemplateSectionDefinition {

    Long getId();

    void setId(Long id);

    String getType();

    void setType(String type);

    String getAdminTitle();

    void setAdminTitle(String adminTitle);

    String getTitle();

    void setTitle(String title);

    String getDefaultText();

    void setDefaultText(String defaultText);

    String getHelpText();

    void setHelpText(String helpText);

    Integer getCharacterLimit();

    void setCharacterLimit(Integer characterLimit);

    Long getFocusAreaId();

    void setFocusAreaId(Long focusAreaId);

    Long getTier();

    void setTier(Long tier);

    String getAllowedContestTypeIds();

    void setAllowedContestTypeIds(String allowedContestTypeIds);

    String getAllowedValues();

    void setAllowedValues(String allowedValues);

    String getAdditionalIds();

    void setAdditionalIds(String additionalIds);

    Boolean isLocked();

    void setLocked(Boolean locked);

    Boolean isContestIntegrationRelevance();

    void setContestIntegrationRelevance(Boolean contestIntegrationRelevance);

    Boolean isIncludeInJudgingReport();

    void setIncludeInJudgingReport(Boolean includeInJudgingReport);
}

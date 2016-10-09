package org.xcolab.portlets.proposals.utils.edit;

import com.ext.portlet.PlanSectionTypeKeys;
import org.xcolab.client.proposals.enums.ProposalAttributeKeys;
;



import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.theme.ThemeDisplay;
import org.apache.commons.lang.StringUtils;
import org.xcolab.analytics.AnalyticsUtil;
import org.xcolab.client.proposals.Proposal2PhaseClient;
import org.xcolab.client.proposals.ProposalAttributeClient;
import org.xcolab.client.proposals.ProposalsClient;
import org.xcolab.client.proposals.exceptions.ProposalNotFoundException;
import org.xcolab.client.proposals.pojo.Proposal;
import org.xcolab.client.proposals.pojo.Proposal2Phase;
import org.xcolab.portlets.proposals.requests.UpdateProposalDetailsBean;
import org.xcolab.portlets.proposals.wrappers.ProposalSectionWrapper;
import org.xcolab.portlets.proposals.wrappers.ProposalWrapper;
import org.xcolab.util.html.HtmlUtil;
import org.xcolab.utils.LinkUtils;

import javax.portlet.ActionRequest;
import javax.validation.Valid;

public class ProposalUpdateHelper {

    public final static String PROPOSAL_ANALYTICS_KEY = "CONTEST_ENTRY_UPDATE";
    public final static String PROPOSAL_ANALYTICS_CATEGORY = "User";
    public final static String PROPOSAL_ANALYTICS_ACTION = "Contest entry update";
    public final static String PROPOSAL_ANALYTICS_LABEL = "";

    private final UpdateProposalDetailsBean updateProposalSectionsBean;
    private final ActionRequest request;
    private final ThemeDisplay themeDisplay;
    private final ProposalWrapper proposalWrapper;
    private final Proposal2Phase p2p;
    private final long userId;

    public ProposalUpdateHelper(@Valid UpdateProposalDetailsBean updateProposalSectionsBean, ActionRequest request,
            ThemeDisplay themeDisplay, ProposalWrapper proposalWrapper, Proposal2Phase p2p, long userId) {
        this.updateProposalSectionsBean = updateProposalSectionsBean;
        this.request = request;
        this.themeDisplay = themeDisplay;
        this.proposalWrapper = proposalWrapper;
        this.p2p = p2p;
        this.userId = userId;
    }

    public void updateProposal() throws PortalException, SystemException {
        boolean filledAll = updateBasicFields();

        boolean updateProposalReferences = false;
        for (ProposalSectionWrapper section : proposalWrapper.getSections()) {
            String newSectionValue =
                    updateProposalSectionsBean.getSectionsContent().get(section.getSectionDefinitionId());
            switch (section.getType()) {
                case TEXT:
                case PROPOSAL_LIST_TEXT_REFERENCE:
                case DROPDOWN_MENU:
                    if (newSectionValue != null && !newSectionValue.trim().equals(section.getContent())) {
                        ProposalAttributeClient
                                .setProposalAttribute(themeDisplay.getUserId(), proposalWrapper.getProposalId(),
                                        ProposalAttributeKeys.SECTION, section.getSectionDefinitionId(),
                                        HtmlUtil.cleanSome(newSectionValue, LinkUtils.getBaseUri(request)));
                        if (section.getType() == PlanSectionTypeKeys.PROPOSAL_LIST_TEXT_REFERENCE) {
                            updateProposalReferences = true;
                        }
                    } else {
                        filledAll = false;
                    }
                    break;
                case ONTOLOGY_REFERENCE:
                    if (StringUtils.isNumeric(newSectionValue)) {
                        long newNumericVal = Long.parseLong(newSectionValue);
                        if (newNumericVal != section.getNumericValue()) {
                            ProposalAttributeClient.setProposalAttribute(themeDisplay.getUserId(),
                                    proposalWrapper.getProposalId(), ProposalAttributeKeys.SECTION,
                                    section.getSectionDefinitionId(), newNumericVal);
                        }
                    } else {
                        filledAll = false;
                    }
                    break;
                case PROPOSAL_REFERENCE:
                    if (StringUtils.isNumeric(newSectionValue) && StringUtils.isNotBlank(newSectionValue)) {
                        final long newNumericValue = Long.parseLong(newSectionValue);
                        if (section.getNumericValue() != newNumericValue) {
                            ProposalAttributeClient
                                    .setProposalAttribute(themeDisplay.getUserId(), proposalWrapper.getProposalId(),
                                            ProposalAttributeKeys.SECTION, section.getSectionDefinitionId(),
                                            newNumericValue);
                            updateProposalReferences = true;
                        }
                    } else if (StringUtils.isBlank(newSectionValue)) {
                        ProposalAttributeClient
                                .setProposalAttribute(themeDisplay.getUserId(), proposalWrapper.getProposalId(),
                                        ProposalAttributeKeys.SECTION, section.getSectionDefinitionId(), 0L);
                    }
                    break;
                case PROPOSAL_LIST_REFERENCE:
                    StringBuilder cleanedReferences = new StringBuilder();
                    if (StringUtils.isNotBlank(newSectionValue)) {
                        String[] referencedProposals = newSectionValue.split(",");
                        for (String referencedProposal : referencedProposals) {
                            if (StringUtils.isNotBlank(referencedProposal) && StringUtils
                                    .isNumeric(referencedProposal)) {
                                cleanedReferences.append(referencedProposal).append(",");
                            }
                        }
                    }
                    if (!section.getStringValue().equals(cleanedReferences.toString())) {
                        ProposalAttributeClient.setProposalAttribute(themeDisplay.getUserId(), proposalWrapper.getProposalId(),
                                        ProposalAttributeKeys.SECTION, section.getSectionDefinitionId(),
                                        cleanedReferences.toString());
                        updateProposalReferences = true;
                    }
                    break;
                default:
            }
        }

        if (p2p != null && p2p.getVersionTo() != -1) {
            // we are in a completed phase - need to adjust the end version
            try {
                final Proposal updatedProposal = ProposalsClient.getProposal(proposalWrapper.getProposalId());
                p2p.setVersionTo(updatedProposal.getCurrentVersion());
                Proposal2PhaseClient.updateProposal2Phase(p2p);
            }catch (ProposalNotFoundException ignored){

            }
        }

        if (updateProposalReferences) {
            ProposalsClient.populateTableWithProposal(proposalWrapper.getWrapped().getProposalId());
        }

        doAnalytics(request, filledAll);
    }

    private boolean updateBasicFields() throws PortalException, SystemException {
        boolean filledAll = true;

        if (!StringUtils.equals(updateProposalSectionsBean.getName(), proposalWrapper.getName())) {
            ProposalAttributeClient.setProposalAttribute(themeDisplay.getUserId(), proposalWrapper.getProposalId(),
                    ProposalAttributeKeys.NAME,0l, HtmlUtil.cleanMost(updateProposalSectionsBean.getName()));
        } else {
            filledAll = false;
        }

        if (!StringUtils.equals(updateProposalSectionsBean.getPitch(), proposalWrapper.getPitch())) {
            ProposalAttributeClient.setProposalAttribute(themeDisplay.getUserId(), proposalWrapper.getProposalId(),
                    ProposalAttributeKeys.PITCH, 0l, HtmlUtil.cleanSome(updateProposalSectionsBean.getPitch(),
                            LinkUtils.getBaseUri(request)));
        } else {
            filledAll = false;
        }

        if (!StringUtils.equals(updateProposalSectionsBean.getDescription(), proposalWrapper.getDescription())) {
            ProposalAttributeClient.setProposalAttribute(themeDisplay.getUserId(), proposalWrapper.getProposalId(),
                    ProposalAttributeKeys.DESCRIPTION,0l, HtmlUtil.cleanSome(updateProposalSectionsBean.getDescription(),
                            LinkUtils.getBaseUri(request)));
        } else {
            filledAll = false;
        }

        if (!StringUtils.equals(updateProposalSectionsBean.getTeam(), proposalWrapper.getTeam())) {
            ProposalAttributeClient.setProposalAttribute(themeDisplay.getUserId(), proposalWrapper.getProposalId(),
                    ProposalAttributeKeys.TEAM,0l, HtmlUtil.cleanMost(updateProposalSectionsBean.getTeam()));
        } else {
            filledAll = false;
        }

        if (updateProposalSectionsBean.getImageId() > 0
                && updateProposalSectionsBean.getImageId() != proposalWrapper.getImageId()) {
            ProposalAttributeClient.setProposalAttribute(themeDisplay.getUserId(), proposalWrapper.getProposalId(),
                    ProposalAttributeKeys.IMAGE_ID, 0l,updateProposalSectionsBean.getImageId());
        } else {
            filledAll = false;
        }
        return filledAll;
    }

    public void doAnalytics(ActionRequest request, boolean filledAll) throws SystemException {
        int analyticsValue;

        if (filledAll) {
            analyticsValue = 3;
        } else {
            analyticsValue = 2;
        }

        AnalyticsUtil.publishEvent(request, userId, PROPOSAL_ANALYTICS_KEY + analyticsValue,
                PROPOSAL_ANALYTICS_CATEGORY, PROPOSAL_ANALYTICS_ACTION, PROPOSAL_ANALYTICS_LABEL, analyticsValue);
    }
}

package org.xcolab.view.pages.proposals.utils.edit;

import org.apache.commons.lang3.StringUtils;

import org.xcolab.client.admin.attributes.platform.PlatformAttributeKey;
import org.xcolab.client.contest.pojo.wrapper.ProposalTemplateSectionDefinitionWrapper;
import org.xcolab.client.contest.proposals.IMembershipClient;
import org.xcolab.client.contest.proposals.StaticProposalContext;
import org.xcolab.client.contest.proposals.exceptions.ConflictException;
import org.xcolab.client.user.StaticUserContext;
import org.xcolab.client.user.pojo.wrapper.PlatformTeamWrapper;
import org.xcolab.client.user.pojo.wrapper.UserWrapper;
import org.xcolab.client.contest.proposals.enums.ProposalAttributeKeys;
import org.xcolab.client.contest.proposals.exceptions.ProposalNotFoundException;
import org.xcolab.client.contest.pojo.wrapper.ProposalWrapper;
import org.xcolab.client.contest.pojo.wrapper.ProposalAttribute;
import org.xcolab.client.contest.pojo.IProposal2Phase;
import org.xcolab.commons.IdListUtil;
import org.xcolab.commons.html.HtmlUtil;
import org.xcolab.util.enums.proposal.ProposalTemplateSectionType;
import org.xcolab.util.http.exceptions.EntityNotFoundException;
import org.xcolab.view.pages.proposals.requests.UpdateProposalDetailsBean;
import org.xcolab.view.pages.proposals.utils.context.ProposalContext;
import org.xcolab.view.util.entity.analytics.AnalyticsUtil;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

public class ProposalUpdateHelper {

    public final static String PROPOSAL_ANALYTICS_KEY = "CONTEST_ENTRY_UPDATE";
    public final static String PROPOSAL_ANALYTICS_CATEGORY = "User";
    public final static String PROPOSAL_ANALYTICS_ACTION = "Contest entry update";
    public final static String PROPOSAL_ANALYTICS_LABEL = "";

    private final UpdateProposalDetailsBean updateProposalSectionsBean;
    private final ProposalWrapper proposal;
    private final IProposal2Phase p2p;
    private final long userId;
    private Integer version;

    private final HttpServletRequest request;
    private final ProposalContext proposalContext;

    public ProposalUpdateHelper(HttpServletRequest request, ProposalContext proposalContext,
            UpdateProposalDetailsBean updateProposalSectionsBean, ProposalWrapper proposal,
            IProposal2Phase p2p, long userId) {
        this.request = request;
        this.proposalContext = proposalContext;
        this.updateProposalSectionsBean = updateProposalSectionsBean;
        this.proposal = proposal;
        this.p2p = p2p;
        this.userId = userId;
    }

    private ProposalAttribute updateAttribute(String stringValue, ProposalTemplateSectionDefinitionWrapper section,
            Integer version) {
        return proposalContext.getClients().getProposalAttributeClient()
                .setProposalAttribute(userId, proposal.getId(),
                        ProposalAttributeKeys.SECTION, section.getSectionDefinitionId(),
                        stringValue, version);
    }

    private ProposalAttribute updateAttribute(Long newNumericVal, ProposalTemplateSectionDefinitionWrapper section,
            Integer version) {
        return proposalContext.getClients().getProposalAttributeClient().setProposalAttribute(
                userId,
                proposal.getId(), ProposalAttributeKeys.SECTION,
                section.getSectionDefinitionId(), newNumericVal, version);
    }

    public void updateProposal() {
        boolean filledAll = updateBasicFields();

        boolean updateProposalReferences = false;
        boolean evictCache = false;
        for (ProposalTemplateSectionDefinitionWrapper section : proposal.getSections()) {
            String newSectionValue =
                    updateProposalSectionsBean.getSectionsContent().get(section.getSectionDefinitionId());
            final ProposalTemplateSectionType sectionType = section.getTypeEnum();
            switch (sectionType) {
                case TEXT:
                case PROPOSAL_LIST_TEXT_REFERENCE:
                case DROPDOWN_MENU:
                    if (newSectionValue != null && !newSectionValue.trim().equals(section.getContent())) {
                        final String baseUri = PlatformAttributeKey.COLAB_URL.get();

                        version = updateAttribute(HtmlUtil.cleanSome(newSectionValue, baseUri),
                                section, version).getVersion();
                        evictCache = true;
                        if (sectionType == ProposalTemplateSectionType.PROPOSAL_LIST_TEXT_REFERENCE) {
                            updateProposalReferences = true;
                        }
                    } else {
                        filledAll = false;
                    }
                    break;
                case CHECKBOX_OPTION:
                    if (newSectionValue != null && !newSectionValue.trim().equals(section.getContent())) {
                        String optionValues = newSectionValue.replace(",",";");

                        version = updateAttribute(optionValues, section, version).getVersion();
                        evictCache = true;
                    } else {
                        filledAll = false;
                    }
                    break;
                case ONTOLOGY_REFERENCE:
                    if (StringUtils.isNumeric(newSectionValue)) {
                        long newNumericVal = Long.parseLong(newSectionValue);
                        if (newNumericVal != section.getNumericValue()) {
                            version = updateAttribute(newNumericVal, section, version).getVersion();
                        }
                    } else {
                        filledAll = false;
                    }
                    break;
                case PROPOSAL_REFERENCE:
                    if (StringUtils.isNumeric(newSectionValue) && StringUtils.isNotBlank(newSectionValue)) {
                        final long newNumericValue = Long.parseLong(newSectionValue);
                        if (section.getNumericValue() != newNumericValue) {
                            version = updateAttribute(newNumericValue, section, version).getVersion();
                            updateProposalReferences = true;
                        }
                    } else if (StringUtils.isBlank(newSectionValue)) {
                        version = updateAttribute(0L, section, version).getVersion();
                    }
                    break;
                case PROPOSAL_LIST_REFERENCE:
                    //parse ids to make sure we only save actual ids
                    final List<Long> parsedIds = IdListUtil.getIdsFromString(newSectionValue);
                    final String cleanedReferences = IdListUtil.getStringFromIds(parsedIds);
                    if (!section.getStringValue().equals(cleanedReferences)) {
                        version = updateAttribute(cleanedReferences, section, version).getVersion();
                        updateProposalReferences = true;
                    }
                    break;
                default:
            }

        }

        if (p2p != null && p2p.getVersionTo() != -1) {
            // we are in a completed phase - need to adjust the end version
            try {
                final ProposalWrapper
                        updatedProposal = proposalContext.getClients().getProposalClient().getProposal(
                        proposal.getId());
                p2p.setVersionTo(updatedProposal.getCurrentVersion());
                proposalContext.getClients().getProposalPhaseClient().updateProposal2Phase(p2p);
            } catch (ProposalNotFoundException ignored) {

            }
        }

        if (updateProposalReferences) {
            proposalContext.getClients().getProposalClient().populateTableWithProposal(proposal.getId());
        }

        if(evictCache){
            proposalContext.getClients().getProposalAttributeClient().invalidateProposalAttibuteCache(
                    proposal);
            proposalContext.getClients().getProposalClient().invalidateProposalCache(proposal.getId());
        }
        doAnalytics(request, filledAll);
    }

    private boolean updateBasicFields() {
        boolean filledAll = true;

        if (!StringUtils.equals(updateProposalSectionsBean.getName(), proposal.getName())) {
            version = proposalContext.getClients().getProposalAttributeClient()
                    .setProposalAttribute(userId, proposal.getId(),
                            ProposalAttributeKeys.NAME, 0L,
                            HtmlUtil.cleanMost(updateProposalSectionsBean.getName()), version)
                    .getVersion();
        } else {
            filledAll = false;
        }

        if (!StringUtils.equals(updateProposalSectionsBean.getPitch(), proposal.getPitch())) {
            final String baseUri = PlatformAttributeKey.COLAB_URL.get();
            version = proposalContext.getClients().getProposalAttributeClient()
                    .setProposalAttribute(userId, proposal.getId(),
                            ProposalAttributeKeys.PITCH, 0L,
                            HtmlUtil.cleanSome(updateProposalSectionsBean.getPitch(), baseUri),
                            version).getVersion();
        } else {
            filledAll = false;
        }

        if (!StringUtils.equals(updateProposalSectionsBean.getDescription(), proposal.getDescription())) {
            final String baseUri = PlatformAttributeKey.COLAB_URL.get();
            version = proposalContext.getClients().getProposalAttributeClient()
                    .setProposalAttribute(userId, proposal.getId(),
                            ProposalAttributeKeys.DESCRIPTION, 0L,
                            HtmlUtil.cleanSome(updateProposalSectionsBean.getDescription(),
                                    baseUri), version).getVersion();
        } else {
            filledAll = false;
        }

        if (updateProposalSectionsBean.getSelectedTeam() != null) {
            try {
                // Setup team stuff
                PlatformTeamWrapper team = StaticUserContext.getPlatformTeamClient().getPlatformTeam(updateProposalSectionsBean.getSelectedTeam());
                List<UserWrapper> members = StaticUserContext.getPlatformTeamClient().listTeamUsers(team.getId());
                for (UserWrapper member : members) {
                    Long userId = member.getId();
                    IMembershipClient client = StaticProposalContext.getMembershipClient();
                    try {
                        client.addUserToProposalTeam(userId, proposal);
                    } catch (ConflictException e) {
                        // User already exists in proposal team
                    }
                }

                // Set team name
                updateProposalSectionsBean.setTeam(team.getName());
            } catch (EntityNotFoundException ignored) {}
        }

        if (!StringUtils.equals(updateProposalSectionsBean.getTeam(), proposal.getTeam())) {
            version = proposalContext.getClients().getProposalAttributeClient()
                    .setProposalAttribute(userId, proposal.getId(),
                            ProposalAttributeKeys.TEAM, 0L,
                            HtmlUtil.cleanMost(updateProposalSectionsBean.getTeam()), version)
                    .getVersion();
        } else {
            filledAll = false;
        }

        if (updateProposalSectionsBean.getImageId() != proposal.getImageId()) {
            version = proposalContext.getClients().getProposalAttributeClient()
                    .setProposalAttribute(userId, proposal.getId(),
                            ProposalAttributeKeys.IMAGE_ID, 0L,
                            updateProposalSectionsBean.getImageId(), version).getVersion();
        } else {
            filledAll = false;
        }
        return filledAll;
    }

    private void doAnalytics(HttpServletRequest request, boolean filledAll) {
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

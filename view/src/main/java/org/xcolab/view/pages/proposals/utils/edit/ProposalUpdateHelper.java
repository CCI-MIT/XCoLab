package org.xcolab.view.pages.proposals.utils.edit;

import org.apache.commons.lang3.StringUtils;

import org.xcolab.client.admin.attributes.platform.PlatformAttributeKey;
import org.xcolab.client.contest.pojo.templates.PlanSectionDefinition;
import org.xcolab.client.members.PlatformTeamsClient;
import org.xcolab.client.members.pojo.Member;
import org.xcolab.client.members.pojo.PlatformTeam;
import org.xcolab.client.proposals.MembershipClient;
import org.xcolab.client.proposals.enums.ProposalAttributeKeys;
import org.xcolab.client.proposals.exceptions.ProposalNotFoundException;
import org.xcolab.client.proposals.pojo.Proposal;
import org.xcolab.client.proposals.pojo.attributes.ProposalAttribute;
import org.xcolab.client.proposals.pojo.phases.Proposal2Phase;
import org.xcolab.commons.IdListUtil;
import org.xcolab.util.enums.proposal.PlanSectionTypeKeys;
import org.xcolab.commons.html.HtmlUtil;
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
    private final Proposal proposalWrapper;
    private final Proposal2Phase p2p;
    private final long userId;
    private Integer version;

    private final HttpServletRequest request;
    private final ProposalContext proposalContext;

    public ProposalUpdateHelper(HttpServletRequest request, ProposalContext proposalContext,
            UpdateProposalDetailsBean updateProposalSectionsBean, Proposal proposalWrapper,
            Proposal2Phase p2p, long userId) {
        this.request = request;
        this.proposalContext = proposalContext;
        this.updateProposalSectionsBean = updateProposalSectionsBean;
        this.proposalWrapper = proposalWrapper;
        this.p2p = p2p;
        this.userId = userId;
    }

    private ProposalAttribute updateAttribute(String stringValue, PlanSectionDefinition section,
            Integer version) {
        return proposalContext.getClients().getProposalAttributeClient()
                .setProposalAttribute(userId, proposalWrapper.getProposalId(),
                        ProposalAttributeKeys.SECTION, section.getSectionDefinitionId(),
                        stringValue, version);
    }

    private ProposalAttribute updateAttribute(Long newNumericVal, PlanSectionDefinition section,
            Integer version) {
        return proposalContext.getClients().getProposalAttributeClient().setProposalAttribute(
                userId,
                proposalWrapper.getProposalId(), ProposalAttributeKeys.SECTION,
                section.getSectionDefinitionId(), newNumericVal, version);
    }

    public void updateProposal() {
        boolean filledAll = updateBasicFields();

        boolean updateProposalReferences = false;
        boolean evictCache = false;
        for (PlanSectionDefinition section : proposalWrapper.getSections()) {
            String newSectionValue =
                    updateProposalSectionsBean.getSectionsContent().get(section.getSectionDefinitionId());
            switch (section.getType()) {
                case TEXT:
                case PROPOSAL_LIST_TEXT_REFERENCE:
                case DROPDOWN_MENU:
                    if (newSectionValue != null && !newSectionValue.trim().equals(section.getContent())) {
                        final String baseUri = PlatformAttributeKey.COLAB_URL.get();

                        version = updateAttribute(HtmlUtil.cleanSome(newSectionValue, baseUri),
                                section, version).getVersion();
                        evictCache = true;
                        if (section.getType() == PlanSectionTypeKeys.PROPOSAL_LIST_TEXT_REFERENCE) {
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
                        if (section.getType() == PlanSectionTypeKeys.PROPOSAL_LIST_TEXT_REFERENCE) {
                            updateProposalReferences = true;
                        }
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
                final Proposal updatedProposal = proposalContext.getClients().getProposalClient().getProposal(proposalWrapper.getProposalId());
                p2p.setVersionTo(updatedProposal.getCurrentVersion());
                proposalContext.getClients().getProposalPhaseClient().updateProposal2Phase(p2p);
            } catch (ProposalNotFoundException ignored) {

            }
        }

        if (updateProposalReferences) {
            proposalContext.getClients().getProposalClient().populateTableWithProposal(proposalWrapper.getWrapped().getProposalId());
        }

        if(evictCache){
            proposalContext.getClients().getProposalAttributeClient().invalidateProposalAttibuteCache(proposalWrapper);
            proposalContext.getClients().getProposalClient().invalidateProposalCache(proposalWrapper.getProposalId());
            if(p2p!=null) {
                proposalContext.getClients().getProposalPhaseClient()
                        .invalidateProposal2PhaseCache(proposalWrapper.getProposalId(), p2p
                                .getContestPhaseId());
            }
        }
        doAnalytics(request, filledAll);
    }

    private boolean updateBasicFields() {
        boolean filledAll = true;

        if (!StringUtils.equals(updateProposalSectionsBean.getName(), proposalWrapper.getName())) {
            version = proposalContext.getClients().getProposalAttributeClient()
                    .setProposalAttribute(userId, proposalWrapper.getProposalId(),
                            ProposalAttributeKeys.NAME, 0L,
                            HtmlUtil.cleanMost(updateProposalSectionsBean.getName()), version)
                    .getVersion();
        } else {
            filledAll = false;
        }

        if (!StringUtils.equals(updateProposalSectionsBean.getPitch(), proposalWrapper.getPitch())) {
            final String baseUri = PlatformAttributeKey.COLAB_URL.get();
            version = proposalContext.getClients().getProposalAttributeClient()
                    .setProposalAttribute(userId, proposalWrapper.getProposalId(),
                            ProposalAttributeKeys.PITCH, 0L,
                            HtmlUtil.cleanSome(updateProposalSectionsBean.getPitch(), baseUri),
                            version).getVersion();
        } else {
            filledAll = false;
        }

        if (!StringUtils.equals(updateProposalSectionsBean.getDescription(), proposalWrapper.getDescription())) {
            final String baseUri = PlatformAttributeKey.COLAB_URL.get();
            version = proposalContext.getClients().getProposalAttributeClient()
                    .setProposalAttribute(userId, proposalWrapper.getProposalId(),
                            ProposalAttributeKeys.DESCRIPTION, 0L,
                            HtmlUtil.cleanSome(updateProposalSectionsBean.getDescription(),
                                    baseUri), version).getVersion();
        } else {
            filledAll = false;
        }

        if (updateProposalSectionsBean.getSelectedTeam() != null) {
            try {
                // Setup team stuff
                PlatformTeam team = PlatformTeamsClient.getPlatformTeam(updateProposalSectionsBean.getSelectedTeam());
                List<Member> members = PlatformTeamsClient.getTeamMembers(team);
                Long groupId = proposalWrapper.getGroupId();
                Long proposalId = proposalWrapper.getProposalId();
                for (Member member : members) {
                    Long userId = member.getUserId();
                    MembershipClient client = proposalContext.getClients().getMembershipClient();
                    client.addUserToProposalTeam(userId, proposalId);
                }

                // Set team name
                updateProposalSectionsBean.setTeam(team.getName());
            } catch (EntityNotFoundException ignored) {}
        }

        if (!StringUtils.equals(updateProposalSectionsBean.getTeam(), proposalWrapper.getTeam())) {
            version = proposalContext.getClients().getProposalAttributeClient()
                    .setProposalAttribute(userId, proposalWrapper.getProposalId(),
                            ProposalAttributeKeys.TEAM, 0L,
                            HtmlUtil.cleanMost(updateProposalSectionsBean.getTeam()), version)
                    .getVersion();
        } else {
            filledAll = false;
        }

        if (updateProposalSectionsBean.getImageId() > 0
                && updateProposalSectionsBean.getImageId() != proposalWrapper.getImageId()) {
            version = proposalContext.getClients().getProposalAttributeClient()
                    .setProposalAttribute(userId, proposalWrapper.getProposalId(),
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

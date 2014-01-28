package com.ext.portlet.service.impl;

import java.util.Date;

import com.ext.portlet.NoSuchProposalAttributeException;
import com.ext.portlet.ProposalAttributeKeys;
import com.ext.portlet.model.Contest;
import com.ext.portlet.model.ContestPhase;
import com.ext.portlet.model.ContestPhaseType;
import com.ext.portlet.model.PlanSectionDefinition;
import com.ext.portlet.model.PlanTemplate;
import com.ext.portlet.model.Proposal;
import com.ext.portlet.model.Proposal2Phase;
import com.ext.portlet.model.ProposalAttribute;
import com.ext.portlet.model.ProposalVersion;
import com.ext.portlet.service.ContestLocalServiceUtil;
import com.ext.portlet.service.ContestPhaseLocalServiceUtil;
import com.ext.portlet.service.ContestPhaseTypeLocalServiceUtil;
import com.ext.portlet.service.PlanTemplateLocalServiceUtil;
import com.ext.portlet.service.Proposal2PhaseLocalServiceUtil;
import com.ext.portlet.service.ProposalVersionLocalServiceUtil;
import com.ext.portlet.service.base.ProposalServiceBaseImpl;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.jsonwebservice.JSONWebService;
import com.liferay.portal.kernel.jsonwebservice.JSONWebServiceMode;
import com.liferay.portal.model.User;
import com.liferay.portal.security.ac.AccessControlled;
import com.liferay.portal.service.UserLocalServiceUtil;

/**
 * The implementation of the proposal remote service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link com.ext.portlet.service.ProposalService} interface.
 *
 * <p>
 * This is a remote service. Methods of this service are expected to have security checks based on the propagated JAAS credentials because this service can be accessed remotely.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see com.ext.portlet.service.base.ProposalServiceBaseImpl
 * @see com.ext.portlet.service.ProposalServiceUtil
 */
@JSONWebService(mode = JSONWebServiceMode.MANUAL)
public class ProposalServiceImpl extends ProposalServiceBaseImpl {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never reference this interface directly. Always use {@link com.ext.portlet.service.ProposalServiceUtil} to access the proposal remote service.
     */

    private final long MILLISECONDS_TO_GROUP_VERSIONS = 1000 * 60;

    /* TODO IMPROVE CODE QUALITY */
    @JSONWebService
    @AccessControlled(guestAccessEnabled=true)
    public JSONObject getProposalVersions(long contestPhaseId, long proposalId, int start, int end) throws PortalException, SystemException {
        Date oldDate = new Date();

        Proposal2Phase p2p = null;
        if (contestPhaseId > 0) p2p = Proposal2PhaseLocalServiceUtil.getByProposalIdContestPhaseId(proposalId,contestPhaseId);

        JSONObject result = JSONFactoryUtil.createJSONObject();
        result.put("proposalId", proposalId);
        result.put("start", start);
        result.put("end", end);

        // COUNT VERSIONS
        int numberOfVersions = 0;
        int offset = 0;
        int counter = 0;
        for (ProposalVersion proposalVersion: ProposalVersionLocalServiceUtil.getByProposalId(proposalId, 0, 10000)) {
            if (p2p != null
                    && (proposalVersion.getVersion() <= p2p.getVersionFrom() || proposalVersion.getVersion() > p2p.getVersionTo())
                    ) {
                continue;
            }

            if (Math.abs(oldDate.getTime() - proposalVersion.getCreateDate().getTime()) > MILLISECONDS_TO_GROUP_VERSIONS){
                numberOfVersions++;
                oldDate = proposalVersion.getCreateDate();
            }
        }
        result.put("totalCount", numberOfVersions);


        JSONArray proposalVersionsArray = JSONFactoryUtil.createJSONArray();
        result.put("versions", proposalVersionsArray);
        offset = 0;
        counter = 0;
        oldDate = new Date();
        for (ProposalVersion proposalVersion: ProposalVersionLocalServiceUtil.getByProposalId(proposalId, 0, 10000)) {
            if (p2p != null
                    && (proposalVersion.getVersion() <= p2p.getVersionFrom() || proposalVersion.getVersion() > p2p.getVersionTo())
                    ) {
                continue;
            }
            if (Math.abs(oldDate.getTime() - proposalVersion.getCreateDate().getTime()) > MILLISECONDS_TO_GROUP_VERSIONS){
                if (counter > (end-start)) {
                    oldDate = proposalVersion.getCreateDate();
                    continue;
                }
                if (offset < start){
                    offset++;
                    oldDate = proposalVersion.getCreateDate();
                    continue;
                }
                JSONObject proposalVersionJsonObj = JSONFactoryUtil.createJSONObject();
                proposalVersionsArray.put(proposalVersionJsonObj);

                proposalVersionJsonObj.put("version", proposalVersion.getVersion());
                proposalVersionJsonObj.put("date", proposalVersion.getCreateDate().getTime());
                proposalVersionJsonObj.put("author", converUserToJson(proposalVersion.getAuthorId()));
                proposalVersionJsonObj.put("updateType", proposalVersion.getUpdateType());
                try{
                    proposalVersionJsonObj.put("contestPhase", getContestPhaseName(proposalVersion));
                } catch(SystemException se) { proposalVersionJsonObj.put("contestPhase", "null");}
                counter++;
                oldDate = proposalVersion.getCreateDate();
            }
        }


        return result;
    }

    @JSONWebService
    @AccessControlled(guestAccessEnabled=true)
    public JSONObject getProposalVersions(long proposalId, int start, int end) throws PortalException, SystemException {
        return  getProposalVersions(-1, proposalId, start, end);
    }

    @JSONWebService
    @AccessControlled(guestAccessEnabled=true)
    public JSONArray getProposalContestSections(long proposalId, int version, long contestId) throws PortalException, SystemException {
    	JSONArray ret = JSONFactoryUtil.createJSONArray();
    	
    	Proposal proposal = proposalLocalService.getProposal(proposalId);
    	Contest contest = contestLocalService.getContest(contestId);
    	
        PlanTemplate planTemplate = ContestLocalServiceUtil.getPlanTemplate(contest);

        if (planTemplate != null) {
            for (PlanSectionDefinition psd : PlanTemplateLocalServiceUtil.getSections(planTemplate)) {
            	try {
            		ProposalAttribute attribute = proposalLocalService.getAttribute(proposalId, version, ProposalAttributeKeys.SECTION, psd.getId());
            		if (attribute != null && attribute.getStringValue().trim().length() > 0) {
            			JSONObject obj = JSONFactoryUtil.createJSONObject();
            			obj.put("title", psd.getTitle());
            			obj.put("sectionId", psd.getId());
            			obj.put("content", attribute.getStringValue());
            		
            			ret.put(obj);
            		}
            	}
            	catch (NoSuchProposalAttributeException e) {
            		// ignore
            	}
            }
        }
    	return ret;
    }
    
    private JSONObject converUserToJson(long userId) throws PortalException, SystemException {
        User user = UserLocalServiceUtil.getUser(userId);

        JSONObject userJsonObj = JSONFactoryUtil.createJSONObject();
        userJsonObj.put("userId", user.getUserId());
        userJsonObj.put("screenName", user.getScreenName());
        userJsonObj.put("fullName", user.getFullName());
        
        return userJsonObj;
    }

    private JSONObject getContestPhaseName(ProposalVersion proposalVersion) throws PortalException, SystemException{
        ContestPhase contestPhase = ContestPhaseLocalServiceUtil.getContestPhase(
                Proposal2PhaseLocalServiceUtil.getForVersion(proposalVersion).getContestPhaseId());
        ContestPhaseType contestPhaseType = ContestPhaseTypeLocalServiceUtil.getContestPhaseType(contestPhase.getContestPhaseType());

        JSONObject contestPhaseJsonObj = JSONFactoryUtil.createJSONObject();
        contestPhaseJsonObj.put("id", contestPhase.getPrimaryKey());
        contestPhaseJsonObj.put("name", contestPhaseType.getName());

        return contestPhaseJsonObj;
    }
}

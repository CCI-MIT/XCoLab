/*
 * Copyright (c) 2010. M.I.T. All Rights Reserved
 * Licensed under the MIT license. Please see http://www.opensource.org/licenses/mit-license.php
 * or the license.txt file included in this distribution for the full text of the license.
 */

package org.xcolab.hooks.climatecolab;

import com.ext.portlet.model.Contest;
import com.ext.portlet.service.ContestLocalServiceUtil;
import com.liferay.portal.kernel.events.Action;
import com.liferay.portal.kernel.events.ActionException;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.model.Theme;
import com.liferay.portal.service.ThemeLocalServiceUtil;
import com.liferay.portal.theme.ThemeDisplay;

import org.xcolab.client.admin.enums.ConfigurationAttributeKey;
import org.xcolab.client.contest.ContestClientUtil;
import org.xcolab.client.contest.pojo.ContestType;
import org.xcolab.client.members.MembersClient;
import org.xcolab.client.members.MessagingClient;
import org.xcolab.client.members.pojo.Member;
import org.xcolab.entity.utils.members.MemberAuthUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class EXTServicePreAction extends Action {
    private static final String COLLABORATORIUM_THEME_NAME = "climatecolab-theme";
    private static final String THEME_TIMESTAMP_ATTRIBUTE = "THEME_TIMESTAMP";
    private static final Log _log = LogFactoryUtil.getLog(EXTServicePreAction.class);

    @Override
    public void run(HttpServletRequest req, HttpServletResponse res) throws ActionException {

        ThemeDisplay themeDisplay = (ThemeDisplay) req.getAttribute(WebKeys.THEME_DISPLAY);
        Map<String, Object> vmVariables = (Map) req.getAttribute(WebKeys.VM_VARIABLES);
        if (vmVariables == null) {
            vmVariables = new HashMap<>();
        }
        //Liferay doesn't seem to set this reliably
        req.setAttribute("javax.servlet.request", req);

        List<Theme> themes = ThemeLocalServiceUtil.getThemes(themeDisplay.getCompanyId());

        String themeTimestamp = "";

        for (Theme theme : themes) {
            if (theme.getName().equals(COLLABORATORIUM_THEME_NAME)) {
                themeTimestamp = String.valueOf(theme.getTimestamp());
            }
        }

        final long memberId = MemberAuthUtil.getMemberId(req);
        final boolean isImpersonating = MemberAuthUtil.isImpersonating(req);
        vmVariables.put("_showImpersonationBar", isImpersonating);
        if (isImpersonating) {
            final long trueMemberId = MemberAuthUtil.getRealMemberId(req);
            vmVariables.put("_trueMember", MembersClient.getMemberUnchecked(trueMemberId));
        }
        if (memberId != 0L) {
            Member member = MembersClient.getMemberUnchecked(memberId);
            vmVariables.put("member", member);
        }

        vmVariables.put("unreadMessages", MessagingClient.countUnreadMessagesForUser(memberId));

        vmVariables.put("_contest_pages", ContestClientUtil.getActiveContestTypes());
        vmVariables.put("_colab_name", ConfigurationAttributeKey.COLAB_NAME.get());
        vmVariables.put("_colab_short_name", ConfigurationAttributeKey.COLAB_SHORT_NAME.get());
        vmVariables.put("_googleAnalyticsKey", ConfigurationAttributeKey.GOOGLE_ANALYTICS_KEY.get());

        vmVariables.put("betaRibbonShow", ConfigurationAttributeKey.BETA_RIBBON_SHOW.get());
        vmVariables.put("_showSearchMenuItem", ConfigurationAttributeKey.SHOW_SEARCH_MENU_ITEM.get());
        vmVariables.put("_openGraphShareTitle", ConfigurationAttributeKey.OPEN_GRAPH_SHARE_TITLE.get());
        vmVariables.put("_openGraphShareDescription", ConfigurationAttributeKey.OPEN_GRAPH_SHARE_DESCRIPTION.get());

        vmVariables.put("isSharedColab", ConfigurationAttributeKey.IS_SHARED_COLAB.get());
        final String partnerColabName = ConfigurationAttributeKey.PARTNER_COLAB_NAME.get();
        final String partnerColabImgsAndClasses = partnerColabName.replace(" ","");
        vmVariables.put("partnerColabName",partnerColabName);
        vmVariables.put("partnerColabClassName",partnerColabImgsAndClasses+ "-sketchy");
        vmVariables.put("partnerColabLogo",partnerColabImgsAndClasses+ "PartnerLogo.png");
        vmVariables.put("adminEmail", ConfigurationAttributeKey.ADMIN_EMAIL.get());
        List<ContestType> contestTypes = ContestClientUtil.getAllContestTypes();
        if(!contestTypes.isEmpty()) {
            vmVariables.put("contestNameLowerCase",contestTypes.get(contestTypes.size()-1).getContestName().toLowerCase() );
            vmVariables.put("proposalNameLowerCase",contestTypes.get(contestTypes.size()-1).getProposalName().toLowerCase() );
        }

        final boolean mitHeaderBarShow = ConfigurationAttributeKey.MIT_HEADER_BAR_SHOW.get();
        vmVariables.put("mitHeaderBarShow", mitHeaderBarShow);
        if (mitHeaderBarShow) {
            vmVariables.put("mitHeaderBarLinkText", ConfigurationAttributeKey.MIT_HEADER_BAR_LINK_TEXT.get());
            vmVariables.put("mitHeaderBarLinkUrl", ConfigurationAttributeKey.MIT_HEADER_BAR_LINK_URL.get());
        }

        String contestIdStr = req.getParameter("_collab_paramcontestId");
        if (contestIdStr != null) {
            try {
                Contest contest = ContestLocalServiceUtil.getContest(Long.parseLong(contestIdStr));
                vmVariables.put("collab_contest", contest);
            } catch (NumberFormatException e) {
                _log.error("An exception has been thrown when trying to parse contest id " + contestIdStr);
            } catch (PortalException | SystemException e) {
                _log.error("An exception has been thrown when loading contest with id " + contestIdStr, e);
            }
        }

        vmVariables.put("themeTimestamp", themeTimestamp);

        req.setAttribute(WebKeys.VM_VARIABLES, vmVariables);
        req.setAttribute(THEME_TIMESTAMP_ATTRIBUTE, themeTimestamp);
    }
}

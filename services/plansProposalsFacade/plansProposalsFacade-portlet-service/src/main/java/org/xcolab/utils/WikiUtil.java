package org.xcolab.utils;

import com.ext.portlet.model.Contest;
import com.ext.portlet.service.ContestLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.model.ResourceConstants;
import com.liferay.portal.security.permission.ActionKeys;
import com.liferay.portal.service.ResourcePermissionLocalServiceUtil;
import com.liferay.portlet.wiki.NoSuchPageException;
import com.liferay.portlet.wiki.NoSuchPageResourceException;
import com.liferay.portlet.wiki.model.WikiPage;
import com.liferay.portlet.wiki.model.WikiPageResource;
import com.liferay.portlet.wiki.service.WikiPageLocalServiceUtil;
import com.liferay.portlet.wiki.service.WikiPageResourceLocalServiceUtil;
import org.xcolab.enums.ColabConstants;
import org.xcolab.enums.MemberRole;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;


public final class WikiUtil {
    
    public final static Long WIKI_NODE_ID = 18855L;
    public final static Long WIKI_GROUP_ID = 10136L;

    private WikiUtil() {}

    public static String removeSpecialChars(String stringToHaveSpecialCharacterRemoved){
        return stringToHaveSpecialCharacterRemoved.replace(":", "").replace(",","").replace(";","");
    }

    public static void updateWikiPageTitleIfExists(String oldTitleRaw, String newTitleRaw)
            throws PortalException, SystemException {
        String oldTitle = removeSpecialChars(oldTitleRaw);
        String newTitle = removeSpecialChars(newTitleRaw);
        if(isWikiPageCreatedForContest(removeSpecialChars(oldTitle))){
            WikiPageResource wikiPageResource = WikiPageResourceLocalServiceUtil.
                    getPageResource(WIKI_NODE_ID, oldTitle);
            try {
                WikiPage wikiPage = WikiPageLocalServiceUtil.getPage(wikiPageResource.getResourcePrimKey());
                updateWikiPageResourceTitle(wikiPageResource, newTitle);
                updateWikiPageTitle(wikiPage, wikiPageResource, newTitle);
            } catch (NoSuchPageException e){
                updateWikiPageResourceTitle(wikiPageResource, newTitle);
            }
        }
    }



    private static void updateWikiPageTitle(WikiPage wikiPage, WikiPageResource wikiPageResource, String newTitle)
            throws SystemException {
        wikiPage.setTitle(newTitle);
        wikiPage.setResourcePrimKey(wikiPageResource.getResourcePrimKey());
        wikiPage.persist();
        WikiPageLocalServiceUtil.updateWikiPage(wikiPage);
    }

    private static void updateWikiPageResourceTitle(WikiPageResource wikiPageResource, String newTitle)
            throws SystemException, PortalException {
        wikiPageResource.setTitle(newTitle);
        addWikiPageResourceViewPermissionsForRoleIfNoneExist(wikiPageResource.getResourcePrimKey(), MemberRole.GUEST);
        addWikiPageResourceViewPermissionsForRoleIfNoneExist(wikiPageResource.getResourcePrimKey(), MemberRole.MEMBER);

        wikiPageResource.persist();
        WikiPageResourceLocalServiceUtil.updateWikiPageResource(wikiPageResource);
    }

    private static boolean isWikiPageCreatedForContest(String oldTitle) throws SystemException, PortalException {
        boolean isWikiPageCreatedForContest = false;
        try {
            WikiPageResourceLocalServiceUtil.getPageResource(WIKI_NODE_ID, oldTitle);
            isWikiPageCreatedForContest = true;
        } catch(NoSuchPageResourceException ignored){ }
        return isWikiPageCreatedForContest;
    }

    public static void addWikiPageResourceViewPermissionsForRoleIfNoneExist(long wikiPageResourcePK, MemberRole role)
            throws SystemException, PortalException {
        if (!ResourcePermissionLocalServiceUtil.hasResourcePermission(ColabConstants.COLAB_COMPANY_ID, WikiPage.class.getName(),
                ResourceConstants.SCOPE_INDIVIDUAL, Long.toString(wikiPageResourcePK), role.getRoleId(), ActionKeys.VIEW)) {
            ResourcePermissionLocalServiceUtil.setResourcePermissions(ColabConstants.COLAB_COMPANY_ID, WikiPage.class.getName(),
                    ResourceConstants.SCOPE_INDIVIDUAL, Long.toString(wikiPageResourcePK), role.getRoleId(),
                    new String[]{ActionKeys.VIEW});
        }
    }

    public static void updateContestResourceUrl(Contest contest, String wikiPageTitle)
            throws SystemException, UnsupportedEncodingException {
        String wikiPageTitleWithoutColon = WikiUtil.removeSpecialChars(wikiPageTitle);
        String escapedWikiPageUrlLink = "/web/guest/resources/-/wiki/Main/" +
                URLEncoder.encode(wikiPageTitleWithoutColon,"UTF-8");
        contest.setResourcesUrl(escapedWikiPageUrlLink);
        contest.persist();
        ContestLocalServiceUtil.updateContest(contest);
    }
}

package org.xcolab.portlets.contestmanagement.wrappers;

import com.ext.portlet.model.Contest;
import com.ext.portlet.service.ContestLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.model.ResourceConstants;
import com.liferay.portal.security.permission.ActionKeys;
import com.liferay.portal.service.ResourcePermissionLocalServiceUtil;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portlet.wiki.NoSuchPageException;
import com.liferay.portlet.wiki.NoSuchPageResourceException;
import com.liferay.portlet.wiki.model.WikiPage;
import com.liferay.portlet.wiki.model.WikiPageResource;
import com.liferay.portlet.wiki.service.WikiPageLocalServiceUtil;
import com.liferay.portlet.wiki.service.WikiPageResourceLocalServiceUtil;
import org.xcolab.enums.ColabConstants;
import org.xcolab.enums.MemberRole;
import org.xcolab.portlets.contestmanagement.beans.ContestResourcesBean;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * Created by Thomas on 2/15/2015.
 */
public class WikiPageWrapper {

    private WikiPage wikiPage;
    private WikiPageResource wikiPageResource;
    private final String contestTitle;
    private final Contest contest;
    private final Long loggedInUserId;

    private final static Long WIKI_NODE_ID = 18855L;
    private final static Long WIKI_GROUP_ID = 10136L;

    public WikiPageWrapper(Contest contest, Long loggedInUserId) throws SystemException, UnsupportedEncodingException, PortalException {
        this.contest = contest;
        String contestTitle = contest.getContestShortName();
        this.contestTitle = removeSpecialChars(contestTitle);
        this.loggedInUserId = loggedInUserId;
        initWikiPageResourceAndCreateIfNoneExistsForThisContest();
        initWikiPageAndCreateIfNoneExistsForThisContest();
    }

    private static String removeSpecialChars(String stringToHaveSpecialCharacterRemoved){
        return stringToHaveSpecialCharacterRemoved.replace(":", "").replace(",","").replace(";","");
    }

    public ContestResourcesBean getContestResourcesBean() throws Exception{
        ContestResourcesBean contestResourcesBean = new ContestResourcesBean();
        String resourcesContent = wikiPage.getContent();
        contestResourcesBean.fillSectionsWithContent(resourcesContent);
        return contestResourcesBean;
    }

    public void updateWikiPage(ContestResourcesBean updatedContestResourcesBean) throws UnsupportedEncodingException, java.text.ParseException, PortalException, SystemException {
        updatedContestResourcesBean.fillOverviewSectionContent(contest);
        String updatedResourcesContent = updatedContestResourcesBean.getSectionsAsHtml();
        if(!wikiPage.getContent().equals(updatedResourcesContent)) {
            Double currentVersion = wikiPage.getVersion() * 10;
            double newVersion = (double) (currentVersion.intValue() + 1) / (double) 10;
            removeHeadFlagFromCurrentWikiPage();
            addWikiPage(newVersion, updatedResourcesContent);
        }
    }

    public static void updateWikiPageTitleIfExists(String oldTitleRaw, String newTitleRaw) throws PortalException, SystemException {
        String oldTitle = removeSpecialChars(oldTitleRaw);
        String newTitle = removeSpecialChars(newTitleRaw);
        if(isWikiPageCreatedForContest(removeSpecialChars(oldTitle))){
            WikiPageResource wikiPageResource = WikiPageResourceLocalServiceUtil.getPageResource(WIKI_NODE_ID, oldTitle);
            try {
                WikiPage wikiPage = WikiPageLocalServiceUtil.getPage(wikiPageResource.getResourcePrimKey());
                updateWikiPageResourceTitle(wikiPageResource, newTitle);
                updateWikiPageTitle(wikiPage, wikiPageResource, newTitle);
            } catch (NoSuchPageException e){
                updateWikiPageResourceTitle(wikiPageResource, newTitle);
            }
        }
    }

    private static void updateWikiPageTitle(WikiPage wikiPage, WikiPageResource wikiPageResource, String newTitle) throws SystemException {
        wikiPage.setTitle(newTitle);
        wikiPage.setResourcePrimKey(wikiPageResource.getResourcePrimKey());
        wikiPage.persist();
        WikiPageLocalServiceUtil.updateWikiPage(wikiPage);
    }

    private static void updateWikiPageResourceTitle(WikiPageResource wikiPageResource, String newTitle) throws SystemException, PortalException {
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

    private void initWikiPageResourceAndCreateIfNoneExistsForThisContest() throws SystemException, PortalException {
        try {
            wikiPageResource = WikiPageResourceLocalServiceUtil.getPageResource(WIKI_NODE_ID, contestTitle);
        } catch(NoSuchPageResourceException e){
            createWikiPageResource();
        }
    }

    private void createWikiPageResource() throws SystemException {
        wikiPageResource = WikiPageResourceLocalServiceUtil.addPageResource(WIKI_NODE_ID, contestTitle);
    }

    private void initWikiPageAndCreateIfNoneExistsForThisContest() throws SystemException, UnsupportedEncodingException, PortalException {
        try {
            wikiPage = WikiPageLocalServiceUtil.getPage(wikiPageResource.getResourcePrimKey());
        } catch(NoSuchPageException e){
            //createWikiPage();
            addWikiPage((double) 1, "");
        }
    }

    private void addWikiPage(double version, String content) throws SystemException, UnsupportedEncodingException, PortalException {
        ServiceContext serviceContext = new ServiceContext();
        if(wikiPageResource == null) {
            initWikiPageResourceAndCreateIfNoneExistsForThisContest();
        }

        Long resourcePrimaryKey = wikiPageResource.getPrimaryKey();
        String summary = "";
        final boolean minorEdit = false;
        final boolean head = true;

        wikiPage = WikiPageLocalServiceUtil.addPage(
                loggedInUserId,
                WIKI_NODE_ID,
                contestTitle,
                version,
                content,
                summary,
                minorEdit,
                "html",
                head,
                "", "",
                serviceContext);
        wikiPage.setGroupId(WIKI_GROUP_ID);
        wikiPage.setResourcePrimKey(resourcePrimaryKey);
        wikiPage.persist();
        WikiPageLocalServiceUtil.updateWikiPage(wikiPage);

        addWikiPageResourceViewPermissionsForRoleIfNoneExist(resourcePrimaryKey, MemberRole.GUEST);
        addWikiPageResourceViewPermissionsForRoleIfNoneExist(resourcePrimaryKey, MemberRole.GUEST);

        updateContestResourceUrl();
    }

    private void updateContestResourceUrl() throws SystemException, UnsupportedEncodingException {
        updateContestResourceUrl(contest, wikiPage.getTitle());
    }

    public static void updateContestResourceUrl(Contest contest, String wikiPageTitle) throws SystemException, UnsupportedEncodingException {
        String wikiPageTitleWithoutColon = removeSpecialChars(wikiPageTitle);
        String escapedWikiPageUrlLink = "/web/guest/resources/-/wiki/Main/" + URLEncoder.encode(wikiPageTitleWithoutColon,"UTF-8");
        contest.setResourcesUrl(escapedWikiPageUrlLink);
        contest.persist();
        ContestLocalServiceUtil.updateContest(contest);
    }

    private void removeHeadFlagFromCurrentWikiPage() throws SystemException {
        wikiPage.setHead(false);
        wikiPage.persist();
        WikiPageLocalServiceUtil.updateWikiPage(wikiPage);
    }


    private static void addWikiPageResourceViewPermissionsForRoleIfNoneExist(long wikiPageResourcePK, MemberRole role)
            throws SystemException, PortalException {
        if (!ResourcePermissionLocalServiceUtil.hasResourcePermission(ColabConstants.COLAB_COMPANY_ID, WikiPage.class.getName(),
                ResourceConstants.SCOPE_INDIVIDUAL, Long.toString(wikiPageResourcePK), role.getRoleId(), ActionKeys.VIEW)) {
            ResourcePermissionLocalServiceUtil.setResourcePermissions(ColabConstants.COLAB_COMPANY_ID, WikiPage.class.getName(),
                    ResourceConstants.SCOPE_INDIVIDUAL, Long.toString(wikiPageResourcePK), role.getRoleId(), new String[]{ActionKeys.VIEW});
        }
    }
}

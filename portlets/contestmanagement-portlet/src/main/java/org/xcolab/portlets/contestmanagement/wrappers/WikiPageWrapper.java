package org.xcolab.portlets.contestmanagement.wrappers;

import com.ext.portlet.model.Contest;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portlet.wiki.NoSuchPageException;
import com.liferay.portlet.wiki.NoSuchPageResourceException;
import com.liferay.portlet.wiki.model.WikiPage;
import com.liferay.portlet.wiki.model.WikiPageResource;
import com.liferay.portlet.wiki.service.WikiPageLocalServiceUtil;
import com.liferay.portlet.wiki.service.WikiPageResourceLocalServiceUtil;
import org.xcolab.enums.MemberRole;
import org.xcolab.portlets.contestmanagement.beans.ContestResourcesBean;
import org.xcolab.utils.WikiUtil;

import java.io.UnsupportedEncodingException;

/**
 * Created by Thomas on 2/15/2015.
 */
public class WikiPageWrapper {

    private WikiPage wikiPage;
    private WikiPageResource wikiPageResource;
    private final String contestTitle;
    private final Contest contest;
    private final Long loggedInUserId;



    public WikiPageWrapper(Contest contest, Long loggedInUserId) throws SystemException, UnsupportedEncodingException, PortalException {
        this.contest = contest;
        String contestTitle = contest.getContestUrlName()+ "-" + contest.getContestYear();
        this.contestTitle = WikiUtil.removeSpecialChars(contestTitle);
        this.loggedInUserId = loggedInUserId;
        initWikiPageResourceAndCreateIfNoneExistsForThisContest();
        initWikiPageAndCreateIfNoneExistsForThisContest();
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



    private void initWikiPageResourceAndCreateIfNoneExistsForThisContest() throws SystemException, PortalException {
        try {
            wikiPageResource = WikiPageResourceLocalServiceUtil.getPageResource(WikiUtil.WIKI_NODE_ID, contestTitle);
        } catch(NoSuchPageResourceException e){
            createWikiPageResource();
        }
    }

    private void createWikiPageResource() throws SystemException {
        wikiPageResource = WikiPageResourceLocalServiceUtil.addPageResource(WikiUtil.WIKI_NODE_ID, contestTitle);
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
                WikiUtil.WIKI_NODE_ID,
                contestTitle,
                version,
                content,
                summary,
                minorEdit,
                "html",
                head,
                "", "",
                serviceContext);
        wikiPage.setGroupId(WikiUtil.WIKI_GROUP_ID);
        wikiPage.setResourcePrimKey(resourcePrimaryKey);
        wikiPage.persist();
        WikiPageLocalServiceUtil.updateWikiPage(wikiPage);

        WikiUtil.addWikiPageResourceViewPermissionsForRoleIfNoneExist(resourcePrimaryKey, MemberRole.GUEST);
        WikiUtil.addWikiPageResourceViewPermissionsForRoleIfNoneExist(resourcePrimaryKey, MemberRole.GUEST);

        updateContestResourceUrl();
    }

    private void updateContestResourceUrl() throws SystemException, UnsupportedEncodingException {
        WikiUtil.updateContestResourceUrl(contest, wikiPage.getTitle());
    }



    private void removeHeadFlagFromCurrentWikiPage() throws SystemException {
        wikiPage.setHead(false);
        wikiPage.persist();
        WikiPageLocalServiceUtil.updateWikiPage(wikiPage);
    }



}

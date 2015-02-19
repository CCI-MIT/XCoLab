package org.xcolab.portlets.contestmanagement.wrappers;

import com.ext.portlet.model.Contest;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portlet.wiki.NoSuchPageException;
import com.liferay.portlet.wiki.NoSuchPageResourceException;
import com.liferay.portlet.wiki.model.WikiPage;
import com.liferay.portlet.wiki.model.WikiPageResource;
import com.liferay.portlet.wiki.service.WikiPageLocalServiceUtil;
import com.liferay.portlet.wiki.service.WikiPageResourceLocalServiceUtil;
import org.springframework.web.util.HtmlUtils;
import org.xcolab.portlets.contestmanagement.beans.ContestResourcesBean;

import java.net.URLEncoder;

/**
 * Created by Thomas on 2/15/2015.
 */
public class WikiPageWrapper {

    private WikiPage wikiPage;
    private WikiPageResource wikiPageResource;
    final private String contestTitle;
    final private Contest contest;
    final private Long loggedInUserId;

    final static Long WIKI_NODE_ID = 18855L;
    final static Long WIKI_GROUP_ID = 10136L;
    final static Long DEFAULT_COMPANY_ID = 10112L;
    final static Long WIKI_USER_ID  = 10144L;

    public WikiPageWrapper(Contest contest, Long loggedInUserId) throws Exception{
        this.contest = contest;
        this.contestTitle = contest.getContestShortName();
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

    public void updateWikiPage(ContestResourcesBean updatedContestResourcesBean) throws Exception{
        updatedContestResourcesBean.fillOverviewSectionContent(contest);
        String updatedResourcesContent = updatedContestResourcesBean.getSectionsAsHtml();
        if(!wikiPage.getContent().equals(updatedResourcesContent)) {
            Double currentVersion = wikiPage.getVersion() * 10;
            double newVersion = (double) (currentVersion.intValue() + 1) / (double) 10;
            removeHeadFlagFromCurrentWikiPage();
            addWikiPage(newVersion, updatedResourcesContent);
        }
    }

    private void initWikiPageResourceAndCreateIfNoneExistsForThisContest() throws Exception{
        try {
            wikiPageResource = WikiPageResourceLocalServiceUtil.getPageResource(WIKI_NODE_ID, contestTitle);
        } catch(NoSuchPageResourceException e){
            createWikiPageResource();
        }
    }

    private void createWikiPageResource() throws Exception{
        wikiPageResource = WikiPageResourceLocalServiceUtil.addPageResource(WIKI_NODE_ID, contestTitle);
    }

    private void initWikiPageAndCreateIfNoneExistsForThisContest() throws Exception{
        try {
            wikiPage = WikiPageLocalServiceUtil.getPage(wikiPageResource.getResourcePrimKey());
        } catch(NoSuchPageException e){
            createWikiPage();
        }
    }

    private void createWikiPage() throws Exception {
        double version = 1;
        String content = "";
        addWikiPage(version, content);
    }

    private void addWikiPage(double version, String content) throws Exception{
        ServiceContext serviceContext = new ServiceContext();
        if(wikiPageResource != null) initWikiPageResourceAndCreateIfNoneExistsForThisContest();
        Long resourcePrimaryKey = wikiPageResource.getPrimaryKey();
        String summary = "";
        boolean minorEdit = false;
        boolean head = true;
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


        wikiPage.setResourcePrimKey(resourcePrimaryKey);
        wikiPage.persist();
        WikiPageLocalServiceUtil.updateWikiPage(wikiPage);

        String escapedWikiPageUrlLink = "/resources/-/wiki/Main/" + URLEncoder.encode(wikiPage.getTitle());
        contest.setResourcesUrl(escapedWikiPageUrlLink);
    }

    private void removeHeadFlagFromCurrentWikiPage() throws Exception{
        wikiPage.setHead(false);
        wikiPage.persist();
        WikiPageLocalServiceUtil.updateWikiPage(wikiPage);
    }
}

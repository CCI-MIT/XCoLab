package org.xcolab.portlets.feeds.search;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ext.portlet.Activity.ActivityUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.search.BaseIndexer;
import com.liferay.portal.kernel.search.BooleanClauseOccur;
import com.liferay.portal.kernel.search.BooleanQuery;
import com.liferay.portal.kernel.search.BooleanQueryFactoryUtil;
import com.liferay.portal.kernel.search.Document;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.search.Hits;
import com.liferay.portal.kernel.search.ParseException;
import com.liferay.portal.kernel.search.SearchContext;
import com.liferay.portal.kernel.search.SearchEngineUtil;
import com.liferay.portal.kernel.search.SearchException;
import com.liferay.portal.kernel.search.Summary;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.util.PortletKeys;
import com.liferay.portlet.social.model.SocialActivity;
import com.liferay.portlet.social.service.SocialActivityLocalServiceUtil;

import java.util.Locale;

import javax.portlet.PortletURL;

/**
 * This class indexes all User activities that are already clustered together (like seen in the Feeds portlet)
 */
public class ActivityIndexer extends BaseIndexer {

    public static final String CLASS_NAME_ID_KEY = "classNameId";
    public static final String CLASS_PK_KEY = "classPK";
    public static final String USER_ID_KEY = "userId";
    public static final String CREATE_DATE_KEY = "createDate";
    private static final String ACTIVITY_ID_KEY = "activityId";
    private static final String TYPE_KEY = "type_";

    public static final String[] CLASS_NAMES = {SocialActivity.class.getName()};

    public static final String PORTLET_ID = PortletKeys.USERS_ADMIN;

    private static Logger _log = LoggerFactory.getLogger(ActivityIndexer.class);

    @Override
    protected void doDelete(Object obj) throws Exception {

    }

    @Override
    protected Document doGetDocument(Object obj) throws Exception {
        return getSocialActivityDocument(getAggregatedActivity((SocialActivity)obj));
    }

    @Override
    protected Summary doGetSummary(Document document, Locale locale, String snippet, PortletURL portletURL) throws Exception {
        return null;
    }

    @Override
    protected void doReindex(Object obj) throws Exception {
        SocialActivity aggregatedActivity = getAggregatedActivity((SocialActivity)obj);

        SearchEngineUtil.updateDocument(SearchEngineUtil.getDefaultSearchEngineId(), aggregatedActivity.getCompanyId(), getSocialActivityDocument(aggregatedActivity));
    }

    @Override
    protected void doReindex(String className, long classPK) throws Exception {
        doReindex(SocialActivityLocalServiceUtil.getSocialActivity(classPK));
    }

    @Override
    protected void doReindex(String[] ids) throws Exception {
        reindexActivities();
    }

    @Override
    protected String getPortletId(SearchContext searchContext) {
        return PORTLET_ID;
    }

    @Override
    public String[] getClassNames() {
        return CLASS_NAMES;
    }

    @Override
    public String getPortletId() {
        return PORTLET_ID;
    }

    private SocialActivity getAggregatedActivity(SocialActivity sa) {
        try {
            SearchContext context = new SearchContext();
            context.setCompanyId(10112L);
            BooleanQuery query = BooleanQueryFactoryUtil.create(context);
            query.addRequiredTerm(Field.ENTRY_CLASS_NAME, CLASS_NAMES[0]);

            BooleanQuery subQuery = BooleanQueryFactoryUtil.create(context);
            subQuery.addExactTerm(CLASS_NAME_ID_KEY, sa.getClassNameId());
            query.add(subQuery, BooleanClauseOccur.MUST);

            subQuery = BooleanQueryFactoryUtil.create(context);
            subQuery.addExactTerm(CLASS_PK_KEY, sa.getClassPK());
            query.add(subQuery, BooleanClauseOccur.MUST);

            subQuery = BooleanQueryFactoryUtil.create(context);
            subQuery.addExactTerm(USER_ID_KEY, sa.getUserId());
            query.add(subQuery, BooleanClauseOccur.MUST);

            subQuery = BooleanQueryFactoryUtil.create(context);
            subQuery.addExactTerm(TYPE_KEY, sa.getType());
            query.add(subQuery, BooleanClauseOccur.MUST);

            // Try to get a similar activity in the time frame
            subQuery = BooleanQueryFactoryUtil.create(context);
            subQuery.addNumericRangeTerm(CREATE_DATE_KEY, 0L, (sa.getCreateDate() - ActivityUtil.AGGREGATION_TIME_WINDOW));
            query.add(subQuery, BooleanClauseOccur.MUST_NOT);

            Hits hits = SearchEngineUtil.search(context, query);

            if (hits.getLength() > 0) {
                Document activityDoc =  hits.getDocs()[0];
                try {
                    return SocialActivityLocalServiceUtil.getSocialActivity(GetterUtil.getLong(activityDoc.getField(ACTIVITY_ID_KEY).getValue()));
                } catch (SystemException | PortalException e) {
                    _log.warn("Can't get social activity", e);
                }
            }
        } catch (ParseException | SearchException e) {
            _log.warn("Can't aggregate social activities", e);
        }

        return sa;
    }

    private Document getSocialActivityDocument(SocialActivity sa) throws SystemException {
        Document document = getBaseModelDocument(PORTLET_ID, sa);

        document.addNumber(ACTIVITY_ID_KEY, sa.getActivityId());
        document.addNumber(CLASS_NAME_ID_KEY, sa.getClassNameId());
        document.addNumber(CLASS_PK_KEY, sa.getClassPK());
        document.addNumber(USER_ID_KEY, sa.getUserId());
        document.addNumber(CREATE_DATE_KEY, sa.getCreateDate());
        document.addNumber(TYPE_KEY, sa.getType());

        return document;
    }

    private void reindexActivities() throws SystemException {

        //TODO: see this again when doing search functionality
        /*List<Activity> allActivities = SocialActivityLocalServiceUtil.getSocialActivities(QueryUtil.ALL_POS, QueryUtil.ALL_POS);
        List<SocialActivity> aggregatedActivities = ActivityUtil.groupActivities(allActivities);

        for (SocialActivity sa : aggregatedActivities) {
            try {
                SearchEngineUtil.updateDocument(SearchEngineUtil.getDefaultSearchEngineId(), sa.getCompanyId(), getSocialActivityDocument(sa));
            } catch (SearchException |SystemException e) {
                _log.error("Could not reindex SocialActivity with primary key " + sa.getActivityId(), e);
            }
        }*/
    }
}

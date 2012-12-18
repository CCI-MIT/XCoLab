package com.ext.portlet.discussions.model.impl;

import java.util.Date;
import java.util.List;

import com.ext.portlet.discussions.model.DiscussionCategoryGroup;
import com.ext.portlet.discussions.model.DiscussionMessage;
import com.ext.portlet.discussions.service.DiscussionCategoryGroupLocalServiceUtil;
import com.ext.portlet.discussions.service.DiscussionCategoryLocalServiceUtil;
import com.ext.portlet.discussions.service.DiscussionMessageLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.search.Indexer;
import com.liferay.portal.kernel.search.SearchException;
import com.liferay.portal.model.User;
import com.liferay.portal.service.UserLocalServiceUtil;

/**
 * The extended model implementation for the DiscussionCategory service. Represents a row in the &quot;Discussions_DiscussionCategory&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * Helper methods and all application logic should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link com.ext.portlet.discussions.model.DiscussionCategory} interface.
 * </p>
 *
 * @author Brian Wing Shun Chan
 */
public class DiscussionCategoryImpl extends DiscussionCategoryBaseImpl {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never reference this class directly. All methods that expect a discussion category model instance should use the {@link com.ext.portlet.discussions.model.DiscussionCategory} interface instead.
     */

    private static final Log _log = LogFactoryUtil.getLog(DiscussionCategoryImpl.class);
    public DiscussionCategoryImpl() {
    }

}

package org.xcolab.portlets.loginregister.activity;

import com.ext.portlet.Activity.BaseFeedEntryWithMailInfo;
import com.ext.portlet.Activity.ICollabActivityInterpreter;
import com.ext.portlet.model.DiscussionCategory;
import com.ext.portlet.model.DiscussionCategoryGroup;
import com.ext.portlet.model.DiscussionMessage;
import com.ext.portlet.service.DiscussionCategoryGroupLocalServiceUtil;
import com.ext.portlet.service.DiscussionCategoryLocalServiceUtil;
import com.ext.portlet.service.DiscussionMessageLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.model.User;
import com.liferay.portal.service.UserLocalServiceUtil;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.portlet.social.model.BaseSocialActivityInterpreter;
import com.liferay.portlet.social.model.SocialActivity;
import com.liferay.portlet.social.model.SocialActivityFeedEntry;

/**
 * Social activity for login/register related activities.
 * 
 * @author janusz
 * 
 */
public class LoginRegisterFeedEntry extends BaseSocialActivityInterpreter implements ICollabActivityInterpreter {

    private static Log _log = LogFactoryUtil.getLog(LoginRegisterFeedEntry.class);

    private static final String[] _CLASS_NAMES = { User.class.getName() };

    public String[] getClassNames() {
        return _CLASS_NAMES;
    }

    @Override
    public String getName(Long classNameId, Long classPK, Integer type, String extraData) {
        StringBuilder name = new StringBuilder();

        User user;
        try {
            user = UserLocalServiceUtil.getUser(classPK);
            LoginRegisterActivityKeys key = LoginRegisterActivityKeys.getForType(type);
            
            if (key != null) {
                return key.getBody(user);
            } else {
                _log.error("Unknown activity for user with type: " + type + "\textra data: " + extraData);
            }
        } catch (PortalException | SystemException e) {
            _log.error("Can't get user for activity: " + classPK);
        }
        return null;
    }

    @Override
    protected SocialActivityFeedEntry doInterpret(SocialActivity activity, ThemeDisplay themeDisplay) throws Exception {

        LoginRegisterActivityKeys key = LoginRegisterActivityKeys.getForType(activity.getType());
        
        if (key != null) {
            User user = UserLocalServiceUtil.getUser(activity.getClassPK());
            String body = "";
            String mailSubject = key.getTitle(user);
            
            String mailBody = "";
            String title = mailSubject;


            body = key.getBody(user);
            mailBody = body;

            return new BaseFeedEntryWithMailInfo("", title, body, mailSubject, mailBody);
        }

        return null;
    }

}

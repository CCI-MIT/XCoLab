package org.xcolab.portlets.loginregister.activity;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ext.portlet.Activity.BaseFeedEntryWithMailInfo;
import com.ext.portlet.Activity.ICollabActivityInterpreter;
import com.ext.portlet.Activity.LoginRegisterActivityKeys;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.model.User;
import com.liferay.portal.service.UserLocalServiceUtil;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.portlet.social.model.BaseSocialActivityInterpreter;
import com.liferay.portlet.social.model.SocialActivity;
import com.liferay.portlet.social.model.SocialActivityFeedEntry;

public class LoginRegisterFeedEntry extends BaseSocialActivityInterpreter implements ICollabActivityInterpreter {

    private static final Logger _log = LoggerFactory.getLogger(LoginRegisterFeedEntry.class);

    private static final String[] _CLASS_NAMES = {User.class.getName()};

    @Override
    public String[] getClassNames() {
        return _CLASS_NAMES;
    }

    @Override
    public String getName(Long classNameId, Long classPK, Integer type, String extraData) {
        try {
            User user = UserLocalServiceUtil.getUser(classPK);
            LoginRegisterActivityKeys key = LoginRegisterActivityKeys.getForType(type);

            if (key != null) {
                return key.getBody(user);
            } else {
                _log.error("Unknown activity for user with type: {}\textra data: {}", type,
                        extraData);
            }
        } catch (PortalException | SystemException e) {
            _log.error("Can't get user for activity: {}", classPK);
        }
        return null;
    }

    @Override
    protected SocialActivityFeedEntry doInterpret(SocialActivity activity, ThemeDisplay themeDisplay)
            throws PortalException, SystemException {

        LoginRegisterActivityKeys key = LoginRegisterActivityKeys.getForType(activity.getType());

        if (key != null) {
            User user = UserLocalServiceUtil.getUser(activity.getClassPK());
            String mailSubject = key.getTitle(user);


            String body = key.getBody(user);

            return new BaseFeedEntryWithMailInfo("", mailSubject, body, mailSubject, body);
        }

        return null;
    }

}

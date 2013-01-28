package org.xcolab.portlets.contests.activities;

import com.ext.portlet.Activity.ICollabActivityInterpreter;
import com.ext.portlet.model.Contest;
import com.ext.portlet.service.ContestLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.portlet.social.model.BaseSocialActivityInterpreter;
import com.liferay.portlet.social.model.SocialActivity;
import com.liferay.portlet.social.model.SocialActivityFeedEntry;

public class ContestsFeedEntry  extends BaseSocialActivityInterpreter implements ICollabActivityInterpreter {


	private static final String[] _CLASS_NAMES = new String[] {"com.ext.portlet.contests.model.Contest"};
	
	@Override
	public String[] getClassNames() {
		return _CLASS_NAMES;
	}

	@Override
	public String getName(Long classNameId, Long classPK, Integer type,
			String extraData) {
		Contest c;
		try {
			c = ContestLocalServiceUtil.getContest(classPK);
			return c.getContestShortName() + " contest";
		} catch (PortalException e) {
			e.printStackTrace();
		} catch (SystemException e) {
			e.printStackTrace();
		}
        	
		return null;
		
	}

	@Override
	protected SocialActivityFeedEntry doInterpret(SocialActivity activity,
			ThemeDisplay themeDisplay) throws Exception {
		return null;
	}

}

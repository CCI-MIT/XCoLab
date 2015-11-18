package org.xcolab.hooks.climatecolab.expando;

import com.ext.portlet.community.CommunityConstants;
import com.liferay.portal.kernel.events.SimpleAction;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.model.User;
import com.liferay.portlet.expando.model.ExpandoColumnConstants;
import com.liferay.portlet.expando.model.ExpandoTable;
import com.liferay.portlet.expando.service.ExpandoColumnLocalServiceUtil;
import com.liferay.portlet.expando.service.ExpandoTableLocalServiceUtil;

/**
 * @author pdeboer
 * 
 */
public class EnsureCountryExpandoCreated extends SimpleAction {
    private static final Log _log = LogFactoryUtil.getLog(EnsureCountryExpandoCreated.class);
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.liferay.portal.kernel.events.SimpleAction#run(java.lang.String[])
	 */
	@Override
	public void run(String[] ids) {
		ExpandoTable table;

		try {
			table = ExpandoTableLocalServiceUtil.addTable(User.class.getName(),
					CommunityConstants.EXPANDO);
		} catch (SystemException | PortalException dtne) {
			try {
				table = ExpandoTableLocalServiceUtil.getTable(User.class.getName(),
						CommunityConstants.EXPANDO);
			} catch (SystemException | PortalException e) {
				_log.error("Failed to create or retrieve explando table", e);
				return;
			}
		}
		
		try {
			ExpandoColumnLocalServiceUtil.addColumn(table.getTableId(),
					CommunityConstants.COUNTRY, ExpandoColumnConstants.STRING);
		} catch (SystemException | PortalException ignored) { }
	}
}

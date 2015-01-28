/**
 * 
 */
package org.xcolab.hooks.climatecolab.expando;

import com.ext.portlet.community.CommunityConstants;
import com.liferay.portal.kernel.events.SimpleAction;
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
    private static Log _log = LogFactoryUtil.getLog(EnsureCountryExpandoCreated.class);


	/**
	 * 

	public EnsureCountryExpandoCreated() {

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.liferay.portal.kernel.events.SimpleAction#run(java.lang.String[])
	 */
	//@Override
	public void run(String[] ids) {
		
 
		ExpandoTable table = null;

		try {
			table = ExpandoTableLocalServiceUtil.addTable(User.class.getName(),
					CommunityConstants.EXPANDO);
		} catch (Exception dtne) {
			try {
				table = ExpandoTableLocalServiceUtil.getTable(User.class.getName(),
						CommunityConstants.EXPANDO);
			} catch (Exception e) {
				e.printStackTrace();
			}
			//_log.warn("Expando table probably already exists", dtne);
		}
		
		try {
			ExpandoColumnLocalServiceUtil.addColumn(table.getTableId(),
					CommunityConstants.COUNTRY, ExpandoColumnConstants.STRING);
		}
		catch (Exception dcne) {
			//_log.warn("Expando column already exists",dcne);

		}

	}

}

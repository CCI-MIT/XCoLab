package org.xcolab.core.xcolabententity;

import java.util.List;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

/**
 * Utility class for XColabEntity that allows access to XColabEntity service.
 *  
 * @author janusz
 *
 */
public class XColabEntityServiceUtil {
	private static XColabEntityService service;
	
	private final static Log _log = LogFactoryUtil.getLog(XColabEntityServiceUtil.class);
	
	public XColabEntityServiceUtil(XColabEntityService service) {
		XColabEntityServiceUtil.service = service;
	}
	
	public static List<XColabEntity> findDocumentEntities(String context) throws XColabEntityException {
		return getService().findDocumentEntities(context);
	}
	
	public static XColabEntityService getService() {
		if (service == null) {
			_log.error("DocumentEntityService is null, not initialized properly");
			throw new RuntimeException("DocumentEntityService is null, not initialized properly");
		}
		return service;
	}

}

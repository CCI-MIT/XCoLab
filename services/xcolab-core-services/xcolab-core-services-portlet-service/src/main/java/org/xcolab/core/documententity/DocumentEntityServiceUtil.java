package org.xcolab.core.documententity;

import java.util.List;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

public class DocumentEntityServiceUtil {
	private static DocumentEntityService service;
	
	private final static Log _log = LogFactoryUtil.getLog(DocumentEntityServiceUtil.class);
	
	public DocumentEntityServiceUtil(DocumentEntityService service) {
		DocumentEntityServiceUtil.service = service;
	}
	
	public static List<DocumentEntity> findDocumentEntities(String context) throws DocumentEntityException {
		return getService().findDocumentEntities(context);
	}
	
	
	private static DocumentEntityService getService() {
		if (service == null) {
			_log.error("DocumentEntityService is null, not initialized properly");
			throw new RuntimeException("DocumentEntityService is null, not initialized properly");
		}
		return service;
	}

}

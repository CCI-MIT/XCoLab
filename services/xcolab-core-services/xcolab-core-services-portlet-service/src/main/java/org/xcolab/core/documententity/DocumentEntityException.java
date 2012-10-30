package org.xcolab.core.documententity;

import com.liferay.portal.kernel.exception.SystemException;

public class DocumentEntityException extends SystemException {

	private static final long serialVersionUID = -445876274445767670L;

	public DocumentEntityException() {
		super();
	}
	
	public DocumentEntityException(String message, Throwable cause) {
		super(message, cause);
	}

	public DocumentEntityException(String message) {
		super(message);
	}

	public DocumentEntityException(Throwable cause) {
		super(cause);
	}
}

package org.xcolab.core.xcolabententity;

import com.liferay.portal.kernel.exception.SystemException;

/**
 * Exception thrown when an error occurs when processing XColabEntity.
 * 
 * @author janusz
 *
 */
public class XColabEntityException extends SystemException {

	private static final long serialVersionUID = -445876274445767670L;

	public XColabEntityException() {
		super();
	}
	
	public XColabEntityException(String message, Throwable cause) {
		super(message, cause);
	}

	public XColabEntityException(String message) {
		super(message);
	}

	public XColabEntityException(Throwable cause) {
		super(cause);
	}
}

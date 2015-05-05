/**
 * Copyright (c) 2000-2013 Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.ext.portlet;

import com.liferay.portal.NoSuchModelException;

/**
 * @author Brian Wing Shun Chan
 */
public class NoSuchUsers_RolesException extends NoSuchModelException {

	public NoSuchUsers_RolesException() {
		super();
	}

	public NoSuchUsers_RolesException(String msg) {
		super(msg);
	}

	public NoSuchUsers_RolesException(String msg, Throwable cause) {
		super(msg, cause);
	}

	public NoSuchUsers_RolesException(Throwable cause) {
		super(cause);
	}

}
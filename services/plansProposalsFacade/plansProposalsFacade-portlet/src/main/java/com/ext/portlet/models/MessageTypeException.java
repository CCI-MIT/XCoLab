/*
 * Copyright (c) 2010. M.I.T. All Rights Reserved
 * Licensed under the MIT license. Please see http://www.opensource.org/licenses/mit-license.php
 * or the license.txt file included in this distribution for the full text of the license.
 */
package com.ext.portlet.models;

import com.liferay.portal.kernel.exception.PortalException;

/**
 * Exception representing error in message type.
 * 
 * @author janusz.p
 * @version 1.0
 * 
 */
public class MessageTypeException extends PortalException {

    /**
     * Serial version ID for serialization.
     */
    private static final long serialVersionUID = 4376124754980233770L;

    /**
     * Default constructor.
     */
    public MessageTypeException() {
        super();
    }

    /**
     * Initializes exception with given message.
     * 
     * @param msg
     *            error message.
     */
    public MessageTypeException(String msg) {
        super(msg);
    }

    /**
     * Initializes exception with given message and cause.
     * 
     * @param msg
     *            error message
     * @param cause
     *            error cause
     */
    public MessageTypeException(String msg, Throwable cause) {
        super(msg, cause);
    }

    /**
     * Initializes exception with given cause.
     * 
     * @param cause
     *            error cause
     */
    public MessageTypeException(Throwable cause) {
        super(cause);
    }
}

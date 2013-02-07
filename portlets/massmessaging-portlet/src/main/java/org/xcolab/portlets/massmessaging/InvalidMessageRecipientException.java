/*
 * Copyright (c) 2010. M.I.T. All Rights Reserved
 * Licensed under the MIT license. Please see http://www.opensource.org/licenses/mit-license.php
 * or the license.txt file included in this distribution for the full text of the license.
 */

package org.xcolab.portlets.massmessaging;

public class InvalidMessageRecipientException extends Exception {
    
    /**
     * 
     */
    private static final long serialVersionUID = 5826436793643253828L;

    public InvalidMessageRecipientException(String message) {
        super(message);
    }
    
    public InvalidMessageRecipientException(String message, Throwable cause) {
        super(message, cause);
    }

}

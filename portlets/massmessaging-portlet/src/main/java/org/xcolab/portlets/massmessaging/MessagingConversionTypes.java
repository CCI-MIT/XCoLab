/*
 * Copyright (c) 2010. M.I.T. All Rights Reserved
 * Licensed under the MIT license. Please see http://www.opensource.org/licenses/mit-license.php
 * or the license.txt file included in this distribution for the full text of the license.
 */

package org.xcolab.portlets.massmessaging;

public enum MessagingConversionTypes {
    EMAIL_OPENED("email_opened"),
    EMAIL_LINK_CLICKED("email_link_clicked"),
    USER_REGISTERED("user_registered"),
    ANY("");
    
    public final String name;

    MessagingConversionTypes(String name) {
        this.name = name;
    }
}

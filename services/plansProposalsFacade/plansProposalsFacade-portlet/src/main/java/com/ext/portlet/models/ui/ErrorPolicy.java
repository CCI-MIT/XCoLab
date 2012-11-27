/*
 * Copyright (c) 2010. M.I.T. All Rights Reserved
 * Licensed under the MIT license. Please see http://www.opensource.org/licenses/mit-license.php
 * or the license.txt file included in this distribution for the full text of the license.
 */

package com.ext.portlet.models.ui;

/**
 * Created by IntelliJ IDEA.
 * User: jintrone
 * Date: Jun 1, 2010
 * Time: 5:10:24 PM
 * To change this template use File | Settings | File Templates.
 */
public enum ErrorPolicy {



    DISPLAY_AVAILABLE_WITH_MSG(true,true), DISPLAY_AVAILBLE_NO_MSG(true,false), NO_DISPLAY_WITH_MSG(false,true);

    private boolean showData;
    private boolean showMsg;

    ErrorPolicy(boolean show_data,boolean show_msg) {
        showData = show_data;
        showMsg = show_msg;
    }

    public boolean showData() {
        return showData;
    }

    public boolean showMsg() {
        return showMsg;
    }




}

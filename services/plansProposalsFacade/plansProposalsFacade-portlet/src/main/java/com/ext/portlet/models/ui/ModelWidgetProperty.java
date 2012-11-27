/*
 * Copyright (c) 2010. M.I.T. All Rights Reserved
 * Licensed under the MIT license. Please see http://www.opensource.org/licenses/mit-license.php
 * or the license.txt file included in this distribution for the full text of the license.
 */

package com.ext.portlet.models.ui;

/**
 * Created by IntelliJ IDEA.
 * User: jintrone
 * Date: Sep 9, 2010
 * Time: 8:38:37 AM
 * To change this template use File | Settings | File Templates.
 */
public interface ModelWidgetProperty {

    public static enum Slider implements ModelWidgetProperty {
        MIN_LABEL, MAX_LABEL, INTERVAL
    }
}

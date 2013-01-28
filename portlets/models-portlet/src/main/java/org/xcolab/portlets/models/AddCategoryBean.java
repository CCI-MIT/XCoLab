/*
 * Copyright (c) 2010. M.I.T. All Rights Reserved
 * Licensed under the MIT license. Please see http://www.opensource.org/licenses/mit-license.php
 * or the license.txt file included in this distribution for the full text of the license.
 */

package org.xcolab.portlets.models;

/**
 * Created by IntelliJ IDEA.
 * User: jintrone
 * Date: Aug 10, 2010
 * Time: 10:40:38 PM
 * To change this template use File | Settings | File Templates.
 */
public class AddCategoryBean {


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void reset() {
        name = "";
        description = "";
    }

    public String name;

    public String description;


}

/*
 * Copyright (c) 2010. M.I.T. All Rights Reserved
 * Licensed under the MIT license. Please see http://www.opensource.org/licenses/mit-license.php
 * or the license.txt file included in this distribution for the full text of the license.
 */

package com.ext.portlet.contests;

/**
 * Created by IntelliJ IDEA.
 * User: jintrone
 * Date: Aug 5, 2010
 * Time: 2:59:51 PM
 * To change this template use File | Settings | File Templates.
 */
public enum ContestStatus {

    NOT_YET_OPEN("Not yet open",false, false,false),
    OPEN_FOR_EDIT("Open for editing",false, true,false),
    OPEN_FOR_SUBMISSION("Open for submission",true, true,false),
    CLOSED_FOR_JUDGING("Closed for judging",false, false,false),
    VOTING("Open for voting",false, false,true),
    FINISHED("Finished",false, false,false),
    CLOSED("Closed",false, false,false);



    private String myName;

    public boolean isCanEdit() {
        return canEdit;
    }

    public boolean isCanVote() {
        return canVote;
    }

    private final boolean canCreate;
    private final boolean canEdit;
    private final boolean canVote;

    ContestStatus(String name, boolean canCreate, boolean canEdit, boolean canVote) {
        this.canCreate = canCreate;
        this.canEdit = canEdit;
        this.canVote = canVote;
        this.myName = name;

    }

    public String toString() {
        return myName;
    }

    public boolean isCanCreate() {
        return canCreate;
    }

}

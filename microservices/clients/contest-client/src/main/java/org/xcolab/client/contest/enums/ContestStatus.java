/*
 * Copyright (c) 2010. M.I.T. All Rights Reserved
 * Licensed under the MIT license. Please see http://www.opensource.org/licenses/mit-license.php
 * or the license.txt file included in this distribution for the full text of the license.
 */

package org.xcolab.client.contest.enums;

import org.xcolab.client.admin.attributes.configuration.ConfigurationAttributeKey;
import org.xcolab.client.contest.pojo.wrapper.ProposalWrapper;
import org.xcolab.commons.SortColumn;

import java.util.Comparator;

public enum ContestStatus {

    OPEN_FOR_EDIT("Open for editing", false, true, false),
    OPEN_FOR_SUBMISSION("Open for submission", true, true, false),
    VOTING("Open for voting", false, false, true),
    CLOSED("Closed", false, false, false),
    COMPLETED("Completed", false, false, false);

    private final String myName;
    private final boolean canCreate;
    private final boolean canEdit;
    private final boolean canVote;

    ContestStatus(String name, boolean canCreate, boolean canEdit, boolean canVote) {
        this.canCreate = canCreate;
        this.canEdit = canEdit;
        this.canVote = canVote;
        this.myName = name;
    }

    public boolean isCanEdit() {
        return canEdit;
    }

    public boolean isCanVote() {
        return canVote;
    }

    public boolean isCanCreate() {
        return canCreate;
    }

    public boolean isCanAnything() {
        return canCreate || canVote || canEdit;
    }

    public Comparator<ProposalWrapper> getDefaultProposalComparator() {
        final SortColumn sortColumn;
        switch (this) {
            case OPEN_FOR_SUBMISSION:
            case OPEN_FOR_EDIT:
                sortColumn = new SortColumn("-" + ProposalSortColumn.MODIFIED.name());
                break;
            case VOTING:
                sortColumn = new SortColumn(
                        ConfigurationAttributeKey.PROPOSALS_PHASE_VOTING_SORT_ORDER.get());
                break;
            default:
                sortColumn = new SortColumn(
                        ConfigurationAttributeKey.PROPOSALS_PHASE_CLOSED_SORT_ORDER.get());
                break;
        }
        final Comparator<ProposalWrapper> comparator =
                ProposalSortColumn.valueOf(sortColumn.getColumnName()).getComparator();
        return sortColumn.isAscending() ? comparator : comparator.reversed();
    }

    @Override
    public String toString() {
        return myName;
    }
}

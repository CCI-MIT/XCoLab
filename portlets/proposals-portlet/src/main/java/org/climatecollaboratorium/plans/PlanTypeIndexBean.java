/*
 * Copyright (c) 2010. M.I.T. All Rights Reserved
 * Licensed under the MIT license. Please see http://www.opensource.org/licenses/mit-license.php
 * or the license.txt file included in this distribution for the full text of the license.
 */

package org.climatecollaboratorium.plans;

import com.ext.portlet.contests.model.Contest;
import com.ext.portlet.contests.service.ContestLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import org.climatecollaboratorium.events.EventBus;
import org.climatecollaboratorium.plans.wrappers.ContestPhaseWrapper;
import org.climatecollaboratorium.plans.wrappers.ContestWrapper;

import javax.faces.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: jintrone
 * Date: Aug 6, 2010
 * Time: 2:18:03 PM
 * To change this template use File | Settings | File Templates.
 */
public class PlanTypeIndexBean {

    List<ContestWrapper> contests = new ArrayList<ContestWrapper> ();

    EventBus eventBus;

    

    public PlanTypeIndexBean() throws SystemException, PortalException {
        refresh();
    }

    public void refresh() throws SystemException, PortalException {
        contests.clear();
        for (Contest contest: ContestLocalServiceUtil.getContests(0,Integer.MAX_VALUE)) {
            if (contest.getContestActive()) {
                contests.add(0,new ContestWrapper(contest));
            } else {
                contests.add(new ContestWrapper(contest));
            }
        }
    }

    public void setEventBus(EventBus bus) {
        this.eventBus = bus;
    }

    public EventBus getEventBus() {
        return eventBus;
    }

    public List<ContestWrapper> getContests() {
        return contests;
    }

    public ContestPhaseWrapper lookupWrapper(Long contestPhaseId) {
        ContestPhaseWrapper result = null;
        for (ContestWrapper contest:contests) {
            if ((result = contest.findPhase(contestPhaseId))!=null) {
                return result;
            }

        }
        return result;
    }



}

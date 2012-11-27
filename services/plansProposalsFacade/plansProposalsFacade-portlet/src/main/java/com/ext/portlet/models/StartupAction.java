/*
 * Copyright (c) 2010. M.I.T. All Rights Reserved
 * Licensed under the MIT license. Please see http://www.opensource.org/licenses/mit-license.php
 * or the license.txt file included in this distribution for the full text of the license.
 */

package com.ext.portlet.models;

import com.liferay.portal.kernel.events.ActionException;
import com.liferay.portal.kernel.events.SimpleAction;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.PropsUtil;

import edu.mit.cci.simulation.client.Simulation;
import edu.mit.cci.simulation.client.comm.ClientRepository;

/**
 *
 */
public class StartupAction extends SimpleAction {

    private static Log _log = LogFactoryUtil.getLog(StartupAction.class);

	public void run(String[] ids) throws ActionException {
		try {
			doRun(GetterUtil.getLong(ids[0]));
		}
		catch (Exception e) {
			throw new ActionException(e);
		}
	}

	protected void doRun(long companyId) throws Exception {
        _log.info("Starting up modeling client ");
        String host = PropsUtil.get("climatecollaboratorium.model.server");
        int port = Integer.parseInt(PropsUtil.get("climatecollaboratorium.model.port"));
		ClientRepository.instance(host,port);

        for (Simulation s: ClientRepository.instance().getAllSimulations()) {
            _log.info("Loaded... "+s.getName());
        }
        _log.info("Modeling client initialized");

	}

}
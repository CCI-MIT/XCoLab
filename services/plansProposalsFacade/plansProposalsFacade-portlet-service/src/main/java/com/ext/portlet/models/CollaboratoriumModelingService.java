/*
 * Copyright (c) 2010. M.I.T. All Rights Reserved
 * Licensed under the MIT license. Please see http://www.opensource.org/licenses/mit-license.php
 * or the license.txt file included in this distribution for the full text of the license.
 */

package com.ext.portlet.models;

import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.PropsUtil;
import com.liferay.util.portlet.PortletProps;

import edu.mit.cci.simulation.client.Simulation;
import edu.mit.cci.simulation.client.comm.ClientRepository;
 
public class CollaboratoriumModelingService {

    static ClientRepository instance;

    private static Log _log = LogFactoryUtil.getLog(CollaboratoriumModelingService.class);

    public static ClientRepository repository() throws SystemException {
        if (instance == null) {

            // try to read configuration from default location (portal-ext.properties)
            String host = null;
            int port = 0;
            try {
                host = PropsUtil.get("climatecollaboratorium.model.server");
                if (host != null) {
                    port = Integer.parseInt(PropsUtil.get("climatecollaboratorium.model.port"));
                }
                
            } catch (Throwable e) {
                _log.error("Exception has been thrown when trying to access PropsUtil: " + e.getClass().getName());
            }
            if (host == null) {
                // if configuration isn't available try to load it from portlet preferences
                host = PortletProps.get("climatecollaboratorium.model.server");
                port = Integer.parseInt(PortletProps.get("climatecollaboratorium.model.port"));
            }

           
             _log.info("Starting up modeling client ("+host+":"+port+")");
            try {
                instance = ClientRepository.instance(host, port);

                for (Simulation s : ClientRepository.instance().getAllSimulations()) {
                    _log.info("Loaded... " + s.getName());
                }
                _log.info("Modeling client initialized");
            } catch (Exception e) {
                _log.error(e);
                throw new SystemException("Error initializing modeling service client", e);
            }

        }
        return instance;
    }
}

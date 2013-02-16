/*
 * Copyright (c) 2010. M.I.T. All Rights Reserved
 * Licensed under the MIT license. Please see http://www.opensource.org/licenses/mit-license.php
 * or the license.txt file included in this distribution for the full text of the license.
 */

package com.ext.portlet.models;

import com.ext.PropertiesUtils;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.PropsUtil;
import com.liferay.util.portlet.PortletProps;

import edu.mit.cci.roma.client.Simulation;
import edu.mit.cci.roma.client.comm.ClientRepository;
 
public class CollaboratoriumModelingService {

    static ClientRepository instance;

    private static Log _log = LogFactoryUtil.getLog(CollaboratoriumModelingService.class);

    public static ClientRepository repository() throws SystemException {
        if (instance == null) {

            // try to read configuration from default location (portal-ext.properties)
            String host = PropsUtil.get("edu.mit.roma.address");
            if (host == null) {
                _log.error("Can't find edu.mit.roma.address property, it has to be set in portal-ext.properties");
                throw new SystemException("Error initializing modeling service client: Can't find " +
                		"edu.mit.roma.address property, it has to be set in portal-ext.properties");
            }
           
             _log.info("Starting up modeling client ("+host+")");
            try {
                instance = ClientRepository.instance(host);

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

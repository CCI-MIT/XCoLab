/*
 * Copyright (c) 2010. M.I.T. All Rights Reserved
 * Licensed under the MIT license. Please see http://www.opensource.org/licenses/mit-license.php
 * or the license.txt file included in this distribution for the full text of the license.
 */

package org.climatecollaboratorium.plans.wrappers;

import java.io.IOException;
import java.io.Serializable;

import com.ext.portlet.models.CollaboratoriumModelingService;
import com.liferay.portal.kernel.exception.SystemException;

import edu.mit.cci.roma.client.Simulation;
import edu.mit.cci.roma.client.model.impl.ClientSimulation;

/**
 * Created by IntelliJ IDEA.
 * User: jintrone
 * Date: Jul 27, 2010
 * Time: 5:05:55 PM
 * To change this template use File | Settings | File Templates.
 */
public class PlanModelWrapper implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	static String DEFAULT_DESCRIPTION = "The Basic Disaggregation model uses three regions for emissions and global indicators for land use";
    static String DEFAULT_NAME="Basic Model";

    Simulation coreModel;
    Simulation disaggregationModel = null;
    boolean simple = false;
    String link = null;

    public PlanModelWrapper(Simulation sim, boolean simple) throws NumberFormatException, IOException {
       coreModel = sim;
       this.simple = simple;
       if (!simple) {
        disaggregationModel = lookupDisaggregationModel(sim);
        if (disaggregationModel!=null) {
            String[] parts = disaggregationModel.getURL().toExternalForm().split("/");
            link = "/excel_wrapper-servlet/rest/file/"+parts[parts.length-1];
        }
       }
    }

    public PlanModelWrapper(Simulation sim) throws NumberFormatException, IOException {
         this(sim,true);

    }

    private static Simulation lookupDisaggregationModel(Simulation sim) throws NumberFormatException, IOException {
         String disaggid = ClientSimulation.parseTypes(sim).get("disagg");
        try {
            return disaggid==null?null: CollaboratoriumModelingService.repository().getSimulation(Long.parseLong(disaggid));
        } catch (SystemException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        return null;
    }

    public String getName() {
     return simple?coreModel.getName():disaggregationModel==null?DEFAULT_NAME:disaggregationModel.getName();
    }

    public String getDescription() {
        return simple?parseFirstParagraph(coreModel.getDescription()):disaggregationModel==null?DEFAULT_DESCRIPTION:disaggregationModel.getDescription();
    }

    public Long getId() {
        return coreModel.getId();
    }

    public Simulation getCoreModel() {
        return coreModel;
    }

    public Simulation getDisaggregationModel() {
        return disaggregationModel;
    }

    public String getLink() {
        return link;
    }

    private static String parseFirstParagraph(String htmlText) {
       String[] result =  htmlText.split("</?p>");
       if (result!=null && result.length > 1) {
           return result[1];
       }
        return "";
    }


    public static String getDisaggregationName(Simulation sim) throws NumberFormatException, IOException {
       Simulation model= lookupDisaggregationModel(sim);
        if (model == null) {
            return DEFAULT_NAME;
        } else return model.getName();
    }

  

}
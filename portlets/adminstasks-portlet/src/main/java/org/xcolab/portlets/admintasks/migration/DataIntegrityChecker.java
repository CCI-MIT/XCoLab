package org.xcolab.portlets.admintasks.migration;

import com.ext.portlet.service.PlanItemLocalServiceUtil;
import com.ext.portlet.service.ProposalAttributeLocalServiceUtil;
import com.icesoft.faces.async.render.SessionRenderer;
import com.ext.portlet.model.*;
import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.dao.orm.*;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: patrickhiesel
 * Date: 9/24/13
 * Time: 6:06 PM
 * To change this template use File | Settings | File Templates.
 */
public class DataIntegrityChecker implements Runnable {

    List<String> reference;
    public boolean STOP = false;

    private static final String ENTITY_CLASS_LOADER_CONTEXT = "plansProposalsFacade-portlet";

    private ClassLoader portletClassLoader = (ClassLoader) PortletBeanLocatorUtil.locate(
            ENTITY_CLASS_LOADER_CONTEXT, "portletClassLoader");

    public DataIntegrityChecker(List<String> reference){
        this.reference = reference;

    }

    public void run() {
        pushAjaxUpdate("Starting to check DB integrity");
        pushAjaxUpdate("Starting simple count checks");

        checkNumberOfUnmatchedAttributes("NAME");
        checkNumberOfUnmatchedAttributes("DESCRIPTION");
        checkNumberOfUnmatchedAttributes("PITCH");
        checkNumberOfUnmatchedAttributes("IMAGE_ID");
        checkNumberOfUnmatchedAttributes("SCENARIO_ID");

        pushAjaxUpdate("Finished to check DB integrity");
    }

    /**
     * Checks the number of proposal attributes in old schema that have no corresponding entry entry in new schema
     * @return
     */
    public long checkNumberOfUnmatchedAttributes(String attributeName){
        long unmatchedAttributes = 0;
        pushAjaxUpdate("Matching " + attributeName);
        pushAjaxUpdate("0%");
        // get all plans (only latest versions)
        List<PlanItem> allPlans = null;
        try {
            allPlans = PlanItemLocalServiceUtil.getPlans();
        } catch (Exception e){ e.printStackTrace(); return -1; }

        // get all sections related to this plan and version
        DynamicQuery proposalNamesQuery = DynamicQueryFactoryUtil.forClass(ProposalAttribute.class, portletClassLoader);
        proposalNamesQuery.add(PropertyFactoryUtil.forName("primaryKey.name").eq(attributeName));
        proposalNamesQuery.setProjection(ProjectionFactoryUtil.property(valueType(attributeName)));
        int counter = 0;
        List<String> proposalNames = null;
        try{
            proposalNames = ProposalAttributeLocalServiceUtil.dynamicQuery(proposalNamesQuery);
        } catch (Exception e){ e.printStackTrace(); return -1; }

        long attributeValueLong;
        String attributeValueString = "";
        for (PlanItem plan : allPlans){
            if (++counter > 0 && (counter % (allPlans.size() / 3)) == 0) updateLastAjaxUpdate((100 * counter / allPlans.size() + 1) + "%");
            try{
                if (attributeName.equalsIgnoreCase("NAME")){
                    attributeValueString = PlanItemLocalServiceUtil.getName(plan);
                    if (!proposalNames.contains(attributeValueString)) unmatchedAttributes++;
                } else if (attributeName.equalsIgnoreCase("SCENARIO_ID")){
                    attributeValueLong = PlanItemLocalServiceUtil.getScenarioId(plan);
                    if (!proposalNames.contains(attributeValueLong)) unmatchedAttributes++;
                } else if (attributeName.equalsIgnoreCase("DESCRIPTION")){
                    attributeValueString = PlanItemLocalServiceUtil.getDescription(plan);
                    if (!proposalNames.contains(attributeValueString)) unmatchedAttributes++;
                } else if (attributeName.equalsIgnoreCase("PITCH")){
                    attributeValueString = PlanItemLocalServiceUtil.getPitch(plan);
                    if (!proposalNames.contains(attributeValueString)) unmatchedAttributes++;
                } else if (attributeName.equalsIgnoreCase("IMAGE_ID")){
                    attributeValueLong = PlanItemLocalServiceUtil.getImageId(plan);
                    if (!proposalNames.contains(attributeValueLong)) unmatchedAttributes++;
                }
            } catch (Exception e){ e.printStackTrace(); return -1; }
        }
        pushAjaxUpdate("Found " + unmatchedAttributes + " unmatched " + attributeName + " (0 expected)");
        return unmatchedAttributes;
    }

    private String valueType(String attribute){
        if (attribute.equalsIgnoreCase("NAME")) return "stringValue";
        else if (attribute.equalsIgnoreCase("SCENARIO_ID")) return "numericValue";
        else if (attribute.equalsIgnoreCase("DESCRIPTION")) return "stringValue";
        else if (attribute.equalsIgnoreCase("PITCH")) return "stringValue";
        else if (attribute.equalsIgnoreCase("IMAGE_ID")) return "numericValue";
        return null;
    }

    private void pushAjaxUpdate(String message){
        reference.add(message);
        SessionRenderer.render("migration");
    }

    private void updateLastAjaxUpdate(String message){
        reference.remove(reference.size()-1);
        reference.add((message));
        SessionRenderer.render("migration");
    }
}

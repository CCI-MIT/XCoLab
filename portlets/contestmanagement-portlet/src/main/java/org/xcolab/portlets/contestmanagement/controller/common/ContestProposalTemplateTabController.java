package org.xcolab.portlets.contestmanagement.controller.common;

import com.ext.portlet.model.OntologyTerm;
import com.ext.portlet.model.PlanSectionDefinition;
import com.ext.portlet.service.OntologyTermLocalServiceUtil;
import com.ext.portlet.service.PlanSectionDefinitionLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.portlet.bind.annotation.ResourceMapping;
import org.xcolab.controller.BaseTabController;
import org.xcolab.enums.ContestTier;
import org.xcolab.enums.OntologySpaceEnum;
import org.xcolab.interfaces.TabEnum;
import org.xcolab.portlets.contestmanagement.beans.SectionDefinitionBean;
import org.xcolab.portlets.contestmanagement.entities.LabelStringValue;
import org.xcolab.portlets.contestmanagement.entities.LabelValue;
import org.xcolab.portlets.contestmanagement.entities.SectionTypes;
import org.xcolab.wrapper.TabWrapper;

import javax.faces.model.SelectItem;
import javax.portlet.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Stack;

/**
 * Created by Thomas on 2/13/2015.
 */
public abstract class ContestProposalTemplateTabController extends BaseTabController {

    private final static Log _log = LogFactoryUtil.getLog(ContestProposalTemplateTabController.class);
    static final public String TAB_VIEW = "common/proposalTemplateTab";
    static final public String NO_PERMISSION_TAB_VIEW = "common/noPermissionTab";
    static final public String NOT_FOUND_TAB_VIEW = "common/notFound";

    protected TabWrapper tabWrapper;

    @ModelAttribute("currentTabWrapped")
    public abstract TabWrapper populateCurrentTabWrapped(PortletRequest request) throws PortalException, SystemException;

    @ModelAttribute("levelSelectionItems")
    public List<LabelValue> populateLevelSelectionItems(){
        return getContestLevelSelectionItems();
    }

    @ModelAttribute("sectionTypeSelectionItems")
    public List<LabelStringValue> populateSectionTypesSelectionItems(){
        return getSectionTypesSelectionItems();
    }

    @ModelAttribute("whatTerms")
    public List<LabelValue> populateWhatTerms() throws PortalException, SystemException {
        return getWhatTerms();
    }
    @ModelAttribute("whereTerms")
    public List<LabelValue> populateWhereTerms() throws PortalException, SystemException {
        return getWhereTerms();
    }
    @ModelAttribute("whoTerms")
    public List<LabelValue> populateWhoTerms() throws PortalException, SystemException {
        return getWhoTerms();
    }
    @ModelAttribute("howTerms")
    public List<LabelValue> populateHowTerms() throws PortalException, SystemException {
        return getHowTerms();
    }

    @ResourceMapping(value="getSectionDefinition")
    public @ResponseBody
    void getSectionDefinition(
            @RequestParam("sectionDefinitionId") Long sectionDefinitionId,
            ResourceResponse response
    ) throws Exception{

        PlanSectionDefinition planSectionDefinition =
                PlanSectionDefinitionLocalServiceUtil.getPlanSectionDefinition(sectionDefinitionId);
        SectionDefinitionBean sectionDefinitionBean = new SectionDefinitionBean(planSectionDefinition);
        ObjectMapper mapper = new ObjectMapper();
        response.setContentType("application/json");
        response.getWriter().write(mapper.writeValueAsString(sectionDefinitionBean));
    }

    private List<LabelValue> getContestLevelSelectionItems(){
        List<LabelValue> selectItems = new ArrayList<>();
        try {
            for (ContestTier contestLevel : ContestTier.values()) {
                selectItems.add(new LabelValue(new Long(contestLevel.getTierType()), contestLevel.getTierName()));
            }
        } catch (Exception e){
        }
        return selectItems;
    }

    private List<LabelStringValue> getSectionTypesSelectionItems(){
        List<LabelStringValue> selectItems = new ArrayList<>();
        try {
            for (SectionTypes sectionTypes : SectionTypes.values()) {
                selectItems.add(new LabelStringValue(sectionTypes.getSectionType(), sectionTypes.getDisplayName()));
            }
        } catch (Exception e){
        }
        return selectItems;
    }

    private List<LabelValue> getWhatTerms() throws SystemException, PortalException {
        return getTermsFromOntologySpace(OntologySpaceEnum.WHAT);
    }

    private List<LabelValue> getWhereTerms() throws SystemException, PortalException {
        return getTermsFromOntologySpace(OntologySpaceEnum.WHERE);
    }

    private List<LabelValue> getWhoTerms() throws SystemException, PortalException {
        return getTermsFromOntologySpace(OntologySpaceEnum.WHO);
    }

    private List<LabelValue> getHowTerms() throws SystemException, PortalException {
        return getTermsFromOntologySpace(OntologySpaceEnum.HOW);
    }

    private List<LabelValue> getTermsFromOntologySpace(OntologySpaceEnum ontologySpace) throws SystemException, PortalException {
        List<LabelValue> termSelectItems = new ArrayList<>();
        List<SelectItem> ret = new ArrayList<SelectItem>();

        for (OntologyTerm term: OntologyTermLocalServiceUtil.getOntologyTerms(0, Integer.MAX_VALUE)) {
            // Just consider terms in the passed ontologySpace
            if (term.getOntologySpaceId() != ontologySpace.getSpaceId()) {
                continue;
            }


            Stack<OntologyTerm> parentsPath = getOntologyTermParentPath(term);
            String ontologyTermPathString = buildOntologyTermPathString(parentsPath, term);

            termSelectItems.add(new LabelValue(term.getId(), ontologyTermPathString));
        }

        Collections.sort(ret, new Comparator<SelectItem>() {

            @Override
            public int compare(SelectItem o1, SelectItem o2) {
                return o1.getLabel().compareTo(o2.getLabel());
            }

        });
        return termSelectItems;
    }

    private Stack<OntologyTerm> getOntologyTermParentPath(OntologyTerm term) throws SystemException, PortalException {
        Stack<OntologyTerm> parentsPath = new Stack<OntologyTerm>();
        OntologyTerm current = term;
        while (current != null) {
            parentsPath.push(current);
            current = OntologyTermLocalServiceUtil.getParent(current);
        }

        return parentsPath;
    }

    private String buildOntologyTermPathString(Stack<OntologyTerm> parentsPath, OntologyTerm childTerm) throws SystemException, PortalException {
        OntologyTerm currentTerm;
        StringBuilder nameStr = new StringBuilder();
        nameStr.append(OntologyTermLocalServiceUtil.getSpace(childTerm).getName() + " # ");
        while (! parentsPath.isEmpty()) {
            currentTerm = parentsPath.pop();
            nameStr.append(" > " + currentTerm.getName());
        }
        nameStr.append(" (" + childTerm.getId() + ")");
        return nameStr.toString();
    }

    public void setPageAttributes(PortletRequest request, Model model, TabEnum tab)
            throws PortalException, SystemException {
    }

}
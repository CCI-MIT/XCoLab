package org.xcolab.view.pages.contestmanagement.controller.common;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import org.xcolab.client.contest.ContestClientUtil;
import org.xcolab.client.contest.OntologyClientUtil;
import org.xcolab.client.contest.PlanTemplateClientUtil;
import org.xcolab.client.contest.pojo.ContestType;
import org.xcolab.client.contest.pojo.ontology.OntologyTerm;
import org.xcolab.client.contest.pojo.templates.PlanSectionDefinition;
import org.xcolab.client.proposals.PointsClientUtil;
import org.xcolab.client.proposals.enums.points.DistributionStrategy;
import org.xcolab.client.proposals.pojo.points.PointType;
import org.xcolab.entity.utils.enums.OntologySpaceEnum;
import org.xcolab.util.enums.contest.ContestTier;
import org.xcolab.view.pages.contestmanagement.entities.LabelStringValue;
import org.xcolab.view.pages.contestmanagement.entities.LabelValue;
import org.xcolab.view.pages.contestmanagement.entities.SectionTypes;
import org.xcolab.view.pages.contestmanagement.wrappers.SectionDefinitionWrapper;
import org.xcolab.view.taglibs.xcolab.controller.BaseTabController;
import org.xcolab.view.taglibs.xcolab.interfaces.TabEnum;
import org.xcolab.view.taglibs.xcolab.wrapper.TabWrapper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.EmptyStackException;
import java.util.List;
import java.util.Stack;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public abstract class ContestProposalTemplateTabController extends BaseTabController {

    public static final String TAB_VIEW = "contestmanagement/manager/proposalTemplateTab";
    public static final String NO_PERMISSION_TAB_VIEW = "common/noPermissionTab";
    protected static final String NOT_FOUND_TAB_VIEW = "common/notFound";

    protected TabWrapper tabWrapper;

    @ModelAttribute("levelSelectionItems")
    public List<LabelValue> populateLevelSelectionItems() {
        return getContestLevelSelectionItems();
    }

    private List<LabelValue> getContestLevelSelectionItems() {
        List<LabelValue> selectItems = new ArrayList<>();
        for (ContestTier contestLevel : ContestTier.values()) {
            selectItems.add(new LabelValue(contestLevel.getTierType(), contestLevel.getTierName()));
        }
        return selectItems;
    }

    @ModelAttribute("sectionTypeSelectionItems")
    public List<LabelStringValue> populateSectionTypesSelectionItems() {
        return getSectionTypesSelectionItems();
    }

    private List<LabelStringValue> getSectionTypesSelectionItems() {
        List<LabelStringValue> selectItems = new ArrayList<>();
        for (SectionTypes sectionTypes : SectionTypes.values()) {
            selectItems.add(new LabelStringValue(sectionTypes.getSectionType(),
                    sectionTypes.getDisplayName()));
        }
        return selectItems;
    }

    @ModelAttribute("contestTypeSelectionItems")
    public List<LabelValue> populateContestTypeSelectionItems() {
        return getContestTypeSelectionItems();
    }

    private List<LabelValue> getContestTypeSelectionItems() {
        List<LabelValue> selectItems = new ArrayList<>();
        for (ContestType contestType : ContestClientUtil.getActiveContestTypes()) {
            selectItems.add(new LabelValue(contestType.getId_(),
                    contestType.getLabelName()));
        }
        return selectItems;

    }

    @ModelAttribute("pointTypeSelectionItems")
    public List<LabelValue> populatePointTypeSelectionItems() {
        return getPointTypeSelectionItems();
    }

    private List<LabelValue> getPointTypeSelectionItems() {
        List<LabelValue> selectItems = new ArrayList<>();
        selectItems.add(new LabelValue(0L, "Default"));
        for (PointType pointType : PointsClientUtil.getAllPointTypes()) {
            if (pointType.getDistributionStrategy()
                    .equalsIgnoreCase(DistributionStrategy.SECTION_DEFINED.name())) {
                selectItems.add(new LabelValue(pointType.getId_(),
                        String.format("%d - %s : %s", pointType.getId_(),
                                pointType.getDistributionStrategy(),
                                pointType.getReceiverLimitationStrategy())));
            }
        }
        return selectItems;

    }

    @ModelAttribute("whatTerms")
    public List<LabelValue> populateWhatTerms() {
        return getWhatTerms();
    }

    @ModelAttribute("whereTerms")
    public List<LabelValue> populateWhereTerms() {
        return getWhereTerms();
    }

    @ModelAttribute("whoTerms")
    public List<LabelValue> populateWhoTerms() {
        return getWhoTerms();
    }

    @ModelAttribute("howTerms")
    public List<LabelValue> populateHowTerms() {
        return getHowTerms();
    }

    @GetMapping("/admin/contest/api/getSectionDefinition/{sectionDefinitionId}")
    public @ResponseBody void getSectionDefinition(HttpServletResponse response,
            @PathVariable long sectionDefinitionId)
            throws IOException {

        PlanSectionDefinition planSectionDefinition =
                PlanTemplateClientUtil.getPlanSectionDefinition(sectionDefinitionId);
        SectionDefinitionWrapper sectionDefinitionWrapper =
                new SectionDefinitionWrapper(planSectionDefinition);
        ObjectMapper mapper = new ObjectMapper();
        response.setContentType("application/json");
        response.getWriter().write(mapper.writeValueAsString(sectionDefinitionWrapper));
    }

    private List<LabelValue> getWhatTerms() {
        return getTermsFromOntologySpace(OntologySpaceEnum.WHAT);
    }

    private List<LabelValue> getWhereTerms() {
        return getTermsFromOntologySpace(OntologySpaceEnum.WHERE);
    }

    private List<LabelValue> getWhoTerms() {
        return getTermsFromOntologySpace(OntologySpaceEnum.WHO);
    }

    private List<LabelValue> getHowTerms() {
        return getTermsFromOntologySpace(OntologySpaceEnum.HOW);
    }

    private List<LabelValue> getTermsFromOntologySpace(OntologySpaceEnum ontologySpace) {
        List<Stack<OntologyTerm>> allParentsPaths =
                getAllOntologyTermParentPathStacks(ontologySpace);
        sortOntologyTermParentPathsAlphabetically(allParentsPaths);

        return buildOntologyTermParentPathSelectItemList(allParentsPaths);
    }

    private List<Stack<OntologyTerm>> getAllOntologyTermParentPathStacks(
            OntologySpaceEnum ontologySpace) {

        List<Stack<OntologyTerm>> allParentsPaths = new ArrayList<>();
        for (OntologyTerm term : OntologyClientUtil
                .getAllOntologyTerms()) {
            // Just consider terms in the passed ontologySpace
            if (term.getOntologySpaceId() != ontologySpace.getSpaceId()) {
                continue;
            }

            Stack<OntologyTerm> parentsPath = getOntologyTermParentPath(term);
            allParentsPaths.add(parentsPath);
        }

        return allParentsPaths;

    }

    private void sortOntologyTermParentPathsAlphabetically(
            List<Stack<OntologyTerm>> allParentsPaths) {
        allParentsPaths.sort((o1, o2) -> compareOntologyTermStacks(
                (Stack<OntologyTerm>) o1.clone(), (Stack<OntologyTerm>) o2.clone()));
    }

    private int compareOntologyTermStacks(Stack<OntologyTerm> stack1, Stack<OntologyTerm> stack2) {
        String stack1FirstItemName;
        try {
            stack1FirstItemName = stack1.pop().getName();
        } catch (EmptyStackException e) {
            return -1;
        }
        String stack2FirstItemName;
        try {
            stack2FirstItemName = stack2.pop().getName();
        } catch (EmptyStackException e) {
            return 1;
        }

        if (stack1FirstItemName.compareTo(stack2FirstItemName) == 0) {
            return compareOntologyTermStacks(stack1, stack2);
        }

        return stack1FirstItemName.compareTo(stack2FirstItemName);
    }

    private List<LabelValue> buildOntologyTermParentPathSelectItemList(
            List<Stack<OntologyTerm>> allParentsPaths) {
        List<LabelValue> termSelectItems = new ArrayList<>();

        for (Stack<OntologyTerm> ontologyTermParentsPath : allParentsPaths) {
            OntologyTerm childTerm = ontologyTermParentsPath.firstElement();
            String ontologyTermPathString = buildOntologyTermPathString(ontologyTermParentsPath);
            termSelectItems.add(new LabelValue(childTerm.getId_(), ontologyTermPathString));
        }

        return termSelectItems;
    }


    private Stack<OntologyTerm> getOntologyTermParentPath(OntologyTerm term) {

        Stack<OntologyTerm> parentsPath = new Stack<>();
        OntologyTerm current = term;
        while (current != null) {
            parentsPath.push(current);
            current = OntologyClientUtil.getOntologyTermParent(current);

        }
        return parentsPath;

    }

    private String buildOntologyTermPathString(Stack<OntologyTerm> parentsPath) {
        if (parentsPath.size() == 1) {
            return parentsPath.pop().getName();
        }

        // Build a path string in the form of "--|--|--|-- childTermName"
        OntologyTerm currentTerm = parentsPath.pop();
        StringBuilder nameStr = new StringBuilder();
        boolean firstItem = true;
        while (!parentsPath.isEmpty()) {
            currentTerm = parentsPath.pop();
            if (firstItem) {
                nameStr.append("-");
                firstItem = false;
            }
            nameStr.append("-|-");
        }
        nameStr.append("- ").append(currentTerm.getName());
        return nameStr.toString();
    }

    @Override
    public void setPageAttributes(HttpServletRequest request, Model model, TabEnum tab) {
    }

}
package org.xcolab.portlets.models.suggest;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.faces.model.SelectItem;

import com.ext.portlet.models.ui.ModelDisplay;
import com.ext.portlet.models.ui.ModelInputDisplayItem;
import com.ext.portlet.models.ui.ModelInputGroupDisplayItem;
import com.ext.portlet.models.ui.ModelInputIndividualDisplayItem;
import com.ext.portlet.models.ui.ModelInputWidgetType;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portlet.wiki.model.WikiPage;
import com.liferay.portlet.wiki.service.WikiPageLocalServiceUtil;

import edu.emory.mathcs.backport.java.util.Collections;

public class SupportBean {
    
    public List<SelectItem> getInputWidgets() {
        List<SelectItem> selectItems = new ArrayList<SelectItem>();
        
        for(ModelInputWidgetType type: ModelInputWidgetType.values()) {
            selectItems.add(new SelectItem(type.name(), type.name()));
        }
        return selectItems;
    }
    
    public static List<SelectItem> getModelInputsOptions(ModelDisplay display) {
        List<SelectItem> selectItems = new ArrayList<SelectItem>();
        selectItems.add(new SelectItem(null, "-- choose --"));
        
        for(ModelInputDisplayItem item: display.getInputs()) {
            if (item instanceof ModelInputIndividualDisplayItem) {
                selectItems.add(new SelectItem(item.getMetaData().getId(), item.getName()));
            } 
            else if (item instanceof ModelInputGroupDisplayItem) {
                for (ModelInputDisplayItem individualItem: ((ModelInputGroupDisplayItem) item).getDisplayItems()) {
                    selectItems.add(new SelectItem(individualItem.getMetaData().getId(), individualItem.getName()));
                }
            }
        }
        return selectItems;
    }
    
    public List<SelectItem> getAllWikiPages() throws SystemException {
        List<SelectItem> items = new ArrayList<SelectItem>();
        
        Set<Long> alreadyAddedPages = new HashSet<Long>();
        for (WikiPage page: WikiPageLocalServiceUtil.getWikiPages(0, Integer.MAX_VALUE)) {
            if (! alreadyAddedPages.contains(page.getResourcePrimKey())) {
                StringBuilder sb = new StringBuilder();
                for (WikiPage parent: page.getParentPages()) {
                    sb.append(parent.getTitle());
                    sb.append(" > ");
                }
                sb.append(page.getTitle());
                items.add(new SelectItem(page.getResourcePrimKey(), sb.toString()));
                alreadyAddedPages.add(page.getResourcePrimKey());
            }
            
        }
        Collections.sort(items, new Comparator<SelectItem>() {

            @Override
            public int compare(SelectItem arg0, SelectItem arg1) {
                return arg0.getLabel().compareTo(arg1.getLabel());
            }
            
        });
        
        return items;
    }
}

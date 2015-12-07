package org.climatecollaboratorium.validation;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;

import com.ext.portlet.NoSuchDiscussionCategoryException;
import com.ext.portlet.model.DiscussionCategory;
import com.ext.portlet.service.DiscussionCategoryGroupLocalServiceUtil;
import com.ext.portlet.service.DiscussionCategoryLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

public class CategoryNameValidator {
    
    private final static Log _log = LogFactoryUtil.getLog(CategoryNameValidator.class);
    public static boolean validateComponent(UIInput input) {
        String value = input.getValue().toString();
        Long categoryGroupId = (Long) input.getAttributes().get("categoryGroupId");
        Long categoryId = (Long) input.getAttributes().get("categoryId");
        
        
        
        boolean isValid = true;
        if (categoryId != null) {
            try {
                DiscussionCategory category = DiscussionCategoryLocalServiceUtil.getDiscussionCategoryById(categoryId);
                if (category.getName().equals(value)) {
                    // value hasn't changed ... it's ok
                    return true;
                }
            } catch (NoSuchDiscussionCategoryException | SystemException e) {
                isValid = false;
                _log.error("Exception thrown when validating new category name", e);
            }

        }
        if (isValid && categoryGroupId != null) {
            try {
                for (DiscussionCategory cat: 
                    DiscussionCategoryGroupLocalServiceUtil.getCategories(
                            DiscussionCategoryGroupLocalServiceUtil.getDiscussionCategoryGroup(categoryGroupId))) {
                    
                    if (cat.getName().equals(value)) {
                        isValid = false;
                    }
                }
            } catch (SystemException | PortalException e) {
                isValid = false;
                _log.error("Exception thrown when validating new category name", e);
            }

        }
        else {
            isValid = false;
        }
        
        if (! isValid) {
            FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Category with given name already exists", null);
            FacesContext.getCurrentInstance().addMessage(input.getClientId(FacesContext.getCurrentInstance()), fm);
        }
        return isValid;
    }

}

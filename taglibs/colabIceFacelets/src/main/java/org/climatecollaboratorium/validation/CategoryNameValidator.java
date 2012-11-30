package org.climatecollaboratorium.validation;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;

import com.ext.portlet.discussions.NoSuchDiscussionCategoryException;
import com.ext.portlet.discussions.model.DiscussionCategory;
import com.ext.portlet.discussions.service.DiscussionCategoryGroupLocalServiceUtil;
import com.ext.portlet.discussions.service.DiscussionCategoryLocalServiceUtil;
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
        if (value == null) {
            isValid = false;
        }
        if (isValid && categoryId != null) {
            try {
                DiscussionCategory category = DiscussionCategoryLocalServiceUtil.getDiscussionCategoryById(categoryId);
                if (category.getName().equals(value)) {
                    // value hasn't changed ... it's ok
                    return true;
                }
            } catch (NoSuchDiscussionCategoryException e) {
                isValid = false;
                _log.error("Exception thrown when validating new category name", e);
            } catch (SystemException e) {
                isValid = false;
                _log.error("Exception thrown when validating new category name", e);
            }
            
        }
        if (isValid && categoryGroupId != null) {
            try {
                for (DiscussionCategory cat: 
                    DiscussionCategoryGroupLocalServiceUtil.getDiscussionCategoryGroup(categoryGroupId).getCategories()) {
                    
                    if (cat.getName().equals(value)) {
                        isValid = false;
                    }
                }
            } catch (SystemException e) {
                isValid = false;
                _log.error("Exception thrown when validating new category name", e);
            } catch (PortalException e) {
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

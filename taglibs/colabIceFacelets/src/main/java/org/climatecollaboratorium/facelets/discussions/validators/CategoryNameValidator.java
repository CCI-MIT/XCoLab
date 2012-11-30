package org.climatecollaboratorium.facelets.discussions.validators;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

import com.ext.portlet.discussions.model.DiscussionCategory;
import com.ext.portlet.discussions.service.DiscussionCategoryGroupLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

public class CategoryNameValidator implements Validator {
    private final static Log _log = LogFactoryUtil.getLog(CategoryNameValidator.class);

    @Override
    public void validate(FacesContext ctx, UIComponent comp, Object value) throws ValidatorException {
        String categoryName = value.toString();
        Long categoryGroupId = (Long) comp.getAttributes().get("categoryGroupId");
        
        boolean isValid = true;

        if (categoryGroupId != null) {
            try {
                for (DiscussionCategory cat: 
                    DiscussionCategoryGroupLocalServiceUtil.getDiscussionCategoryGroup(categoryGroupId).getCategories()) {
                    
                    if (cat.getName().equals(categoryName)) {
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
            throw new ValidatorException(
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Category with given name already exists", "Category with given name already exists"));
        }

    }

}

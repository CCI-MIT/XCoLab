package com.ext.portlet.service.impl;

import java.util.List;

import com.ext.portlet.model.ModelCategory;
import com.ext.portlet.model.ModelGlobalPreference;
import com.ext.portlet.service.ModelGlobalPreferenceLocalServiceUtil;
import com.ext.portlet.service.base.ModelCategoryLocalServiceBaseImpl;
import com.liferay.counter.service.CounterLocalServiceUtil;
import com.liferay.portal.kernel.exception.SystemException;

/**
 * The implementation of the model category local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link com.ext.portlet.service.ModelCategoryLocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see com.ext.portlet.service.base.ModelCategoryLocalServiceBaseImpl
 * @see com.ext.portlet.service.ModelCategoryLocalServiceUtil
 */
public class ModelCategoryLocalServiceImpl
    extends ModelCategoryLocalServiceBaseImpl {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never reference this interface directly. Always use {@link com.ext.portlet.service.ModelCategoryLocalServiceUtil} to access the model category local service.
     */
    
    public List<ModelGlobalPreference> getModelPreferences(ModelCategory category) throws SystemException {
        return ModelGlobalPreferenceLocalServiceUtil.findByCategory(category);

    }
    
    public ModelCategory addCategory(String name,String description) throws SystemException {
        Long pk = CounterLocalServiceUtil.increment(ModelCategory.class.getName());
        ModelCategory cat = createModelCategory(pk);
        cat.setModelCategoryName(name);
        cat.setModelCategoryDescription(description);
        cat.setModelCategoryDisplayWeight(0);
        addModelCategory(cat);
        return cat;
    }
}

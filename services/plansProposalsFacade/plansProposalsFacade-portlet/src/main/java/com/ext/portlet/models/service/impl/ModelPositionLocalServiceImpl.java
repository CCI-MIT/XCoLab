package com.ext.portlet.models.service.impl;

import java.util.List;

import com.ext.portlet.models.model.ModelPosition;
import com.ext.portlet.models.service.base.ModelPositionLocalServiceBaseImpl;
import com.liferay.counter.service.CounterLocalServiceUtil;
import com.liferay.portal.kernel.exception.SystemException;

/**
 * The implementation of the model position local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link com.ext.portlet.models.service.ModelPositionLocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see com.ext.portlet.models.service.base.ModelPositionLocalServiceBaseImpl
 * @see com.ext.portlet.models.service.ModelPositionLocalServiceUtil
 */
public class ModelPositionLocalServiceImpl
    extends ModelPositionLocalServiceBaseImpl {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never reference this interface directly. Always use {@link com.ext.portlet.models.service.ModelPositionLocalServiceUtil} to access the model position local service.
     */
    
    public List<ModelPosition> getModelPositionsByModelId(Long modelId) throws SystemException {
        return modelPositionPersistence.findByModelId(modelId);
    }
    
    public void setModelPositions(Long modelId, List<Long> positionIds) throws SystemException {
        modelPositionPersistence.removeByModelId(modelId);
        
        for (Long positionId: positionIds) {
            Long id = CounterLocalServiceUtil.increment(ModelPosition.class.getName());
            ModelPosition modelPosition = createModelPosition(id);
            modelPosition.setModelId(modelId);
            modelPosition.setPositionId(positionId);
            
            addModelPosition(modelPosition);
        }
    }
}

package com.ext.portlet.models.service.impl;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

import com.ext.portlet.models.CollaboratoriumModelingService;
import com.ext.portlet.models.model.ModelInputGroup;
import com.ext.portlet.models.model.ModelInputItem;
import com.ext.portlet.models.service.ModelInputGroupLocalServiceUtil;
import com.ext.portlet.models.service.ModelInputItemLocalServiceUtil;
import com.ext.portlet.models.service.base.ModelInputGroupLocalServiceBaseImpl;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;

import edu.mit.cci.simulation.client.MetaData;
import edu.mit.cci.simulation.client.Simulation;

/**
 * The implementation of the model input group local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link com.ext.portlet.models.service.ModelInputGroupLocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see com.ext.portlet.models.service.base.ModelInputGroupLocalServiceBaseImpl
 * @see com.ext.portlet.models.service.ModelInputGroupLocalServiceUtil
 */
public class ModelInputGroupLocalServiceImpl
    extends ModelInputGroupLocalServiceBaseImpl {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never reference this interface directly. Always use {@link com.ext.portlet.models.service.ModelInputGroupLocalServiceUtil} to access the model input group local service.
     */

    public List<ModelInputGroup> getInputGroups(Simulation sim) {
        try {
            return modelInputGroupPersistence.findByModelId(sim.getId());
        } catch (SystemException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }

        return Collections.emptyList();
    }

     public List<ModelInputGroup> getChildGroups(ModelInputGroup group) {
         try {
             return modelInputGroupPersistence.findByparentModelId(group.getModelInputGroupPK());
         } catch (SystemException e) {
             e.printStackTrace();

         }
         return Collections.emptyList();
     }
     
     

     public List<ModelInputItem> getInputItems(ModelInputGroup group) {
         return ModelInputItemLocalServiceUtil.getItemForGroupId(group.getModelInputGroupPK());
        
     }

     public ModelInputGroup getParent(ModelInputGroup group) {
         try {
             return ModelInputGroupLocalServiceUtil.getModelInputGroup(group.getParentGroupPK());
         } catch (PortalException e) {
             e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
         } catch (SystemException e) {
             e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
         }
         return null;
     }

      public Simulation getModel(ModelInputGroup group) throws SystemException, IOException {
         return CollaboratoriumModelingService.repository().getSimulation(group.getModelId());       
     }

     public MetaData getMetaData(ModelInputGroup group) throws SystemException, IOException {
         if (group.getNameAndDescriptionMetaDataId() > 0) {
             return CollaboratoriumModelingService.repository().getMetaData(group.getNameAndDescriptionMetaDataId());
         }
         return null;
     }
}

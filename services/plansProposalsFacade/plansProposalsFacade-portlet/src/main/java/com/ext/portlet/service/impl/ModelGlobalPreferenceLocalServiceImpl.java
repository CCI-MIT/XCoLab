package com.ext.portlet.service.impl;

import java.util.List;

import com.ext.portlet.NoSuchModelCategoryException;
import com.ext.portlet.NoSuchModelGlobalPreferenceException;
import com.ext.portlet.model.ModelCategory;
import com.ext.portlet.model.ModelGlobalPreference;
import com.ext.portlet.service.ModelCategoryLocalServiceUtil;
import com.ext.portlet.service.base.ModelGlobalPreferenceLocalServiceBaseImpl;
import com.liferay.counter.service.CounterLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;

import edu.mit.cci.roma.client.Simulation;

/**
 * The implementation of the model global preference local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link com.ext.portlet.service.ModelGlobalPreferenceLocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see com.ext.portlet.service.base.ModelGlobalPreferenceLocalServiceBaseImpl
 * @see com.ext.portlet.service.ModelGlobalPreferenceLocalServiceUtil
 */
public class ModelGlobalPreferenceLocalServiceImpl
    extends ModelGlobalPreferenceLocalServiceBaseImpl {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never reference this interface directly. Always use {@link com.ext.portlet.service.ModelGlobalPreferenceLocalServiceUtil} to access the model global preference local service.
     */


    public boolean isVisible(Simulation s) throws SystemException {
        ModelGlobalPreference pref = null;
        try {
            pref = modelGlobalPreferencePersistence.findByModelId(s.getId());
        } catch (NoSuchModelGlobalPreferenceException e) {
          //no worries
        }
        return (pref !=null && pref.getVisible());
    }
    
    public ModelGlobalPreference getByModelId(long modelId) throws SystemException {
        ModelGlobalPreference pref = null;
        try {
            pref = modelGlobalPreferencePersistence.findByModelId(modelId);
        } catch (NoSuchModelGlobalPreferenceException e) {
        	pref = createModelGlobalPreference(CounterLocalServiceUtil.increment(ModelGlobalPreference.class.getName()));
        	pref.setModelId(modelId);
        	pref.setUsesCustomInputs(false);
        	
        	addModelGlobalPreference(pref);
        }
        return pref;
    }

    public void setVisible(Simulation s, boolean visible) throws SystemException {
         ModelGlobalPreference pref = null;
        try {
            pref = modelGlobalPreferencePersistence.findByModelId(s.getId());
        } catch (NoSuchModelGlobalPreferenceException e) {
          //no worries
        }
        if (pref == null) {

            pref = createNewForSim(s);
            
        }
        pref.setVisible(visible);
        updateModelGlobalPreference(pref);

    }
    

    public int getWeight(Simulation s) throws SystemException {
        ModelGlobalPreference pref = null;
        try {
            pref = modelGlobalPreferencePersistence.findByModelId(s.getId());
        } catch (NoSuchModelGlobalPreferenceException e) {
          //no worries
        }
        int weight = 0;
        if (pref !=null) {
            weight = pref.getWeight();
        }
        return weight;
    }
    
    public void setWeight(Simulation s, int weight) throws SystemException {
        ModelGlobalPreference pref = null;
       try {
           pref = modelGlobalPreferencePersistence.findByModelId(s.getId());
       } catch (NoSuchModelGlobalPreferenceException e) {
         //no worries
       }
       if (pref == null) {
          pref = createNewForSim(s);
       }
       pref.setWeight(weight);
       updateModelGlobalPreference(pref);
   }
    
    public Long getExpertEvaluationPageId(Simulation s) throws SystemException {
        ModelGlobalPreference pref = null;
        try {
            pref = modelGlobalPreferencePersistence.findByModelId(s.getId());
        } catch (NoSuchModelGlobalPreferenceException e) {
          //no worries
        }
        return pref != null ? pref.getExpertEvaluationPageId() : null;
    }
    
   public void setExpertEvaluationPageId(Simulation s, Long pageId) throws SystemException {
       ModelGlobalPreference pref = null;
       try {
           pref = modelGlobalPreferencePersistence.findByModelId(s.getId());
       } catch (NoSuchModelGlobalPreferenceException e) {
         //no worries
       }
       if (pref == null) {
           pref = createNewForSim(s);
       }
       pref.setExpertEvaluationPageId(pageId);
       updateModelGlobalPreference(pref);
   }

    public List<ModelGlobalPreference> findByCategory(ModelCategory category) throws SystemException {
        return modelGlobalPreferencePersistence.findByModelCategoryId(category.getModelCategoryPK());
    }

    public ModelCategory getCategory(Simulation sim) throws SystemException, PortalException {
        ModelGlobalPreference pref = null;
        try {
            pref = modelGlobalPreferencePersistence.findByModelId(sim.getId());
        } catch (NoSuchModelGlobalPreferenceException e) {
          //no worries
        }
        try {
            return (pref!=null&&pref.getModelCategoryId()> 0)? ModelCategoryLocalServiceUtil.getModelCategory(pref.getModelCategoryId()):null;
        } catch (NoSuchModelCategoryException e) {
            return null;
        }
    }

    

    private ModelGlobalPreference createNewForSim(Simulation sim) throws SystemException {
        ModelGlobalPreference pref = null;
        Long pk = CounterLocalServiceUtil.increment(ModelGlobalPreference.class.getName());
           pref = createModelGlobalPreference(pk);
           pref.setModelId(sim.getId());
           pref.setWeight(0);
           pref.setVisible(false);
           addModelGlobalPreference(pref);
        return pref;

    }

    public void updateModelCategory(ModelCategory cat, Simulation sim) throws SystemException {
       ModelGlobalPreference pref = null;
       try {
           pref = modelGlobalPreferencePersistence.findByModelId(sim.getId());
       } catch (NoSuchModelGlobalPreferenceException e) {
         //no worries
       }
       if (pref == null) {
           pref = createNewForSim(sim);
       }
       pref.setModelCategoryId(cat==null?-1L:cat.getModelCategoryPK());
       updateModelGlobalPreference(pref);
   }
}

package com.ext.portlet.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.ext.portlet.model.OntologyTermEntity;
import com.ext.portlet.service.OntologyTermEntityLocalServiceUtil;
import com.ext.portlet.service.base.OntologyTermEntityLocalServiceBaseImpl;
import com.liferay.counter.service.CounterLocalServiceUtil;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.service.ClassNameLocalServiceUtil;

/**
 * The implementation of the ontology term entity local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link com.ext.portlet.service.OntologyTermEntityLocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see com.ext.portlet.service.base.OntologyTermEntityLocalServiceBaseImpl
 * @see com.ext.portlet.service.OntologyTermEntityLocalServiceUtil
 */
public class OntologyTermEntityLocalServiceImpl
    extends OntologyTermEntityLocalServiceBaseImpl {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never reference this interface directly. Always use {@link com.ext.portlet.service.OntologyTermEntityLocalServiceUtil} to access the ontology term entity local service.
     */

    public List<Long> findTagedIdsForClass(Long termId, Class clasz) throws SystemException {
        List<Long> ret = new ArrayList<Long>();
        
        for (OntologyTermEntity ote: ontologyTermEntityPersistence.findByTermIdClassNameId(termId, ClassNameLocalServiceUtil.getClassNameId(clasz))) {
            ret.add(ote.getClassPK());
        }
        
        return ret;
    }
    
    
    public void store(OntologyTermEntity ote) throws SystemException {
        if (ote.isNew()) {
            if (ote.getId() <= 0) {
                ote.setId(CounterLocalServiceUtil.increment(OntologyTermEntity.class.getName()));
            }
            OntologyTermEntityLocalServiceUtil.addOntologyTermEntity(ote);
        }
        else {
            OntologyTermEntityLocalServiceUtil.updateOntologyTermEntity(ote);
        }
    }
    
    public void remove(OntologyTermEntity ote) throws SystemException {
        OntologyTermEntityLocalServiceUtil.deleteOntologyTermEntity(ote);
    }
}

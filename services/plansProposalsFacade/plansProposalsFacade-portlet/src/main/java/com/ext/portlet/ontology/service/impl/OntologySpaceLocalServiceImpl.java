package com.ext.portlet.ontology.service.impl;

import com.ext.portlet.ontology.model.OntologySpace;
import com.ext.portlet.ontology.model.OntologyTerm;
import com.ext.portlet.ontology.service.OntologyTermLocalServiceUtil;
import com.ext.portlet.ontology.service.base.OntologySpaceLocalServiceBaseImpl;
import com.liferay.counter.service.CounterLocalServiceUtil;
import com.liferay.portal.kernel.exception.SystemException;

/**
 * The implementation of the ontology space local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link com.ext.portlet.ontology.service.OntologySpaceLocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see com.ext.portlet.ontology.service.base.OntologySpaceLocalServiceBaseImpl
 * @see com.ext.portlet.ontology.service.OntologySpaceLocalServiceUtil
 */
public class OntologySpaceLocalServiceImpl
    extends OntologySpaceLocalServiceBaseImpl {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never reference this interface directly. Always use {@link com.ext.portlet.ontology.service.OntologySpaceLocalServiceUtil} to access the ontology space local service.
     */
    
    public OntologySpace createSpace(String name, String description) throws SystemException  {
        OntologySpace space = createOntologySpace(CounterLocalServiceUtil.increment(OntologySpace.class.getName()));
        
        space.setName(name);
        space.setDescription(description);
        space.store();
        
        // create parent term for new space
        OntologyTerm t = OntologyTermLocalServiceUtil.createTerm(null, "all", space.getId(), null);
        
        return space;
    }
    
}

package com.ext.portlet.service.impl;

import com.ext.portlet.model.OntologySpace;
import com.ext.portlet.model.OntologyTerm;
import com.ext.portlet.service.OntologySpaceLocalServiceUtil;
import com.ext.portlet.service.OntologyTermLocalServiceUtil;
import com.ext.portlet.service.base.OntologySpaceLocalServiceBaseImpl;
import com.liferay.counter.service.CounterLocalServiceUtil;
import com.liferay.portal.kernel.exception.SystemException;

import java.util.List;

/**
 * The implementation of the ontology space local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link com.ext.portlet.service.OntologySpaceLocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see com.ext.portlet.service.base.OntologySpaceLocalServiceBaseImpl
 * @see com.ext.portlet.service.OntologySpaceLocalServiceUtil
 */
public class OntologySpaceLocalServiceImpl
    extends OntologySpaceLocalServiceBaseImpl {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never reference this interface directly. Always use {@link com.ext.portlet.service.OntologySpaceLocalServiceUtil} to access the ontology space local service.
     */
    
    public OntologySpace createSpace(String name, String description) throws SystemException  {
        OntologySpace space = createOntologySpace(CounterLocalServiceUtil.increment(OntologySpace.class.getName()));
        
        space.setName(name);
        space.setDescription(description);
        store(space);
        
        // create parent term for new space
        OntologyTerm t = OntologyTermLocalServiceUtil.createTerm(0L, "all", space.getId(), null);
        
        return space;
    }
    
    public void store(OntologySpace space) throws SystemException {
        if (space.isNew()) {
            OntologySpaceLocalServiceUtil.addOntologySpace(space);
        }
        else {
            OntologySpaceLocalServiceUtil.updateOntologySpace(space);
        }
    }
    
    public List<OntologyTerm> getTopTerms(OntologySpace space) throws SystemException {
        return OntologyTermLocalServiceUtil.findByParentIdSpaceId(0L, space.getId());
    }

}

package com.xcolab.services.sample.service.impl;


import java.util.List;

import com.liferay.portal.kernel.exception.SystemException;
import com.xcolab.services.sample.model.SampleEntity;
import com.xcolab.services.sample.service.base.SampleEntityLocalServiceBaseImpl;

/**
 * The implementation of the sample entity local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link com.xcolab.services.sample.service.SampleEntityLocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see com.xcolab.services.sample.service.base.SampleEntityLocalServiceBaseImpl
 * @see com.xcolab.services.sample.service.SampleEntityLocalServiceUtil
 */
public class SampleEntityLocalServiceImpl
    extends SampleEntityLocalServiceBaseImpl {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never reference this interface directly. Always use {@link com.xcolab.services.sample.service.SampleEntityLocalServiceUtil} to access the sample entity local service.
     */
	
	public List<SampleEntity> getAllEntities() throws SystemException {
		return sampleEntityLocalService.getSampleEntities(0, Integer.MAX_VALUE);
	}
	
	public void printSomething() {
		System.out.println("Something!");
	}
	
	public void printNotSomething() {
		System.out.println("Not something!");
	}	
	
	public void printSomethingElse() {
		System.out.println("### Something else!");
	}
}

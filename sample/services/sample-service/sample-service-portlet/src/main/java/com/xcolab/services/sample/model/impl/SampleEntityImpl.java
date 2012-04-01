package com.xcolab.services.sample.model.impl;

import com.liferay.counter.service.CounterLocalServiceUtil;
import com.liferay.portal.kernel.exception.SystemException;
import com.xcolab.services.sample.model.SampleEntity;
import com.xcolab.services.sample.service.SampleEntityLocalServiceUtil;

/**
 * The extended model implementation for the SampleEntity service. Represents a row in the &quot;sample_SampleEntity&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * Helper methods and all application logic should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link com.xcolab.services.sample.model.SampleEntity} interface.
 * </p>
 *
 * @author Brian Wing Shun Chan
 */
public class SampleEntityImpl extends SampleEntityBaseImpl {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never reference this class directly. All methods that expect a sample entity model instance should use the {@link com.xcolab.services.sample.model.SampleEntity} interface instead.
     */
    public SampleEntityImpl() {
    }
    
    public void store() throws SystemException {
    	if (isNew()) {
    		if (this.getId() <= 0) {
    			setId(CounterLocalServiceUtil.increment(SampleEntity.class.getName()));
    		}
    		SampleEntityLocalServiceUtil.addSampleEntity(this);
    	}
    	else {
    		SampleEntityLocalServiceUtil.updateSampleEntity(this);
    	}
    }
}

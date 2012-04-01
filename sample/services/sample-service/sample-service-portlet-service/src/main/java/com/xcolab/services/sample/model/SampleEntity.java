package com.xcolab.services.sample.model;

import com.liferay.portal.model.PersistedModel;

/**
 * The extended model interface for the SampleEntity service. Represents a row in the &quot;sample_SampleEntity&quot; database table, with each column mapped to a property of this class.
 *
 * @author Brian Wing Shun Chan
 * @see SampleEntityModel
 * @see com.xcolab.services.sample.model.impl.SampleEntityImpl
 * @see com.xcolab.services.sample.model.impl.SampleEntityModelImpl
 * @generated
 */
public interface SampleEntity extends SampleEntityModel, PersistedModel {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify this interface directly. Add methods to {@link com.xcolab.services.sample.model.impl.SampleEntityImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
     */
    public void store()
        throws com.liferay.portal.kernel.exception.SystemException;
}

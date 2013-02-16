package com.ext.portlet.service.impl;

import com.ext.portlet.NoSuchModelOutputItemException;
import com.ext.portlet.model.ModelOutputItem;
import com.ext.portlet.service.base.ModelOutputItemLocalServiceBaseImpl;
import com.liferay.portal.kernel.exception.SystemException;

import edu.mit.cci.roma.client.MetaData;

/**
 * The implementation of the model output item local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link com.ext.portlet.service.ModelOutputItemLocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see com.ext.portlet.service.base.ModelOutputItemLocalServiceBaseImpl
 * @see com.ext.portlet.service.ModelOutputItemLocalServiceUtil
 */
public class ModelOutputItemLocalServiceImpl
    extends ModelOutputItemLocalServiceBaseImpl {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never reference this interface directly. Always use {@link com.ext.portlet.service.ModelOutputItemLocalServiceUtil} to access the model output item local service.
     */

    public ModelOutputItem getOutputItem(MetaData md) throws SystemException, NoSuchModelOutputItemException {
        return modelOutputItemPersistence.findByModelOutputId(md.getId());
    }
}

package com.ext.portlet.service.impl;

import com.ext.portlet.NoSuchModelOutputChartOrderException;
import com.ext.portlet.model.ModelOutputChartOrder;
import com.ext.portlet.service.base.ModelOutputChartOrderLocalServiceBaseImpl;
import com.liferay.portal.kernel.exception.SystemException;
import edu.mit.cci.roma.client.Simulation;

/**
 * The implementation of the model output chart order local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link com.ext.portlet.service.ModelOutputChartOrderLocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see com.ext.portlet.service.base.ModelOutputChartOrderLocalServiceBaseImpl
 * @see com.ext.portlet.service.ModelOutputChartOrderLocalServiceUtil
 */
public class ModelOutputChartOrderLocalServiceImpl
    extends ModelOutputChartOrderLocalServiceBaseImpl {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never reference this interface directly. Always use {@link com.ext.portlet.service.ModelOutputChartOrderLocalServiceUtil} to access the model output chart order local service.
     */

    @Override
    public ModelOutputChartOrder getChartOrder(Simulation sim, String label) throws SystemException, NoSuchModelOutputChartOrderException {
        return modelOutputChartOrderPersistence.findByModelIdAndLabel(sim.getId(),label);
    }
}

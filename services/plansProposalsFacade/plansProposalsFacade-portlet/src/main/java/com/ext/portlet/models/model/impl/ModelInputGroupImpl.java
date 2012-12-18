package com.ext.portlet.models.model.impl;

import java.io.IOException;
import java.util.List;

import com.ext.portlet.models.CollaboratoriumModelingService;
import com.ext.portlet.models.model.ModelInputGroup;
import com.ext.portlet.models.model.ModelInputItem;
import com.ext.portlet.models.service.ModelInputGroupLocalServiceUtil;
import com.ext.portlet.models.service.ModelInputItemLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;

import edu.mit.cci.simulation.client.MetaData;
import edu.mit.cci.simulation.client.Simulation;

/**
 * The extended model implementation for the ModelInputGroup service. Represents a row in the &quot;Models_ModelInputGroup&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * Helper methods and all application logic should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link com.ext.portlet.models.model.ModelInputGroup} interface.
 * </p>
 *
 * @author Brian Wing Shun Chan
 */
public class ModelInputGroupImpl extends ModelInputGroupBaseImpl {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never reference this class directly. All methods that expect a model input group model instance should use the {@link com.ext.portlet.models.model.ModelInputGroup} interface instead.
     */
    public ModelInputGroupImpl() {
    }

}

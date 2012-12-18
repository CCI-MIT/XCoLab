package com.ext.portlet.ontology.model.impl;

import java.util.ArrayList;
import java.util.List;

import com.ext.portlet.ontology.NoSuchOntologyTermException;
import com.ext.portlet.ontology.model.FocusArea;
import com.ext.portlet.ontology.model.FocusAreaOntologyTerm;
import com.ext.portlet.ontology.model.OntologyTerm;
import com.ext.portlet.ontology.service.FocusAreaLocalServiceUtil;
import com.ext.portlet.ontology.service.FocusAreaOntologyTermLocalServiceUtil;
import com.ext.portlet.ontology.service.OntologyTermLocalServiceUtil;
import com.ext.portlet.ontology.service.persistence.FocusAreaOntologyTermPK;
import com.liferay.counter.service.CounterLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;

/**
 * The extended model implementation for the FocusArea service. Represents a row in the &quot;ontology_FocusArea&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * Helper methods and all application logic should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link com.ext.portlet.ontology.model.FocusArea} interface.
 * </p>
 *
 * @author Brian Wing Shun Chan
 */
public class FocusAreaImpl extends FocusAreaBaseImpl {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never reference this class directly. All methods that expect a focus area model instance should use the {@link com.ext.portlet.ontology.model.FocusArea} interface instead.
     */
    public FocusAreaImpl() {
    }

}

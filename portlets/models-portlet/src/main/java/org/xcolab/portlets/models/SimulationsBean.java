package org.xcolab.portlets.models;

import java.io.IOException;
import java.util.List;

import javax.faces.event.ActionEvent;

import org.xcolab.portlets.models.suggest.SimulationDecorator;
import org.xcolab.portlets.models.suggest.SimulationsHelper;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;

import edu.mit.cci.roma.client.comm.ClientRepository;

public class SimulationsBean {

    private ClientRepository r;
    private List<SimulationDecorator> simulations;
    private CategoriesBean categories;
    private boolean editing;

    public SimulationsBean() throws IOException, SystemException, PortalException {
        simulations = SimulationsHelper.getInstance().getSimulations();
        //updateVisible();
    }

    private void updateVisible() throws SystemException, IOException, PortalException {
        categories.updateCategorySimulations();
    }
        


    public List<SimulationDecorator> getSimulations() {
        return simulations;
    }

    public boolean isEditing() {
        return editing;
    }

    public void setEditing(boolean editing) {
        this.editing = editing;
    }

    public void edit(ActionEvent e) throws SystemException, IOException, PortalException {
        editing = !editing;
        if (!editing) updateVisible();
    }

    public CategoriesBean getCategories() {
            return categories;
    }

    public void setCategories(CategoriesBean bean) {
        this.categories = bean;
    }
}
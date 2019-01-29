package org.xcolab.client.modeling.models.ui;

import edu.mit.cci.roma.client.MetaData;
import edu.mit.cci.roma.client.Scenario;
import edu.mit.cci.roma.client.Simulation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.xcolab.client.modeling.StaticModelingContext;
import org.xcolab.client.modeling.pojo.IModelInputGroup;
import org.xcolab.client.modeling.pojo.IModelInputItem;
import org.xcolab.client.modeling.pojo.tables.pojos.ModelInputGroup;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObjectBuilder;

/**
 * Encapsulates a "group" of input elements to be displayed together.  This
 * element is backed by an {@link IModelInputGroup}
 */
public class ModelInputGroupDisplayItem extends ModelInputDisplayItem implements Serializable {

    private static final long serialVersionUID = 1L;

    private static final Logger _log = LoggerFactory.getLogger(ModelInputGroupDisplayItem.class);

    private final IModelInputGroup group;

    private ModelInputGroupDisplayItem parent;
    private List<ModelInputDisplayItem> items = new ArrayList<>();
    private List<ModelInputGroupDisplayItem> groups = new ArrayList<>();
    private Set<MetaData> knownMetaData = new HashSet<>();

    /**
     * Public constructor requires an existing backed dao. Generally, clients
     * will not call this directly, and the factory will take care of instantiating groups
     * or the static factory method on this class is called.
     */
    public ModelInputGroupDisplayItem(Simulation simulation, IModelInputGroup group) throws IOException {
        super(simulation, StaticModelingContext.getModelingClient().getMetaData(group));
        this.group = group;
        populateChildren();
    }

    private void populateChildren() throws IOException {
        knownMetaData = new HashSet<>();
        items = new ArrayList<>();
        for (IModelInputItem item : StaticModelingContext.getModelingClient().getInputItems(group)) {
            knownMetaData.add(StaticModelingContext.getModelingClient().getMetaData(item));
            items.add(ModelUIFactory.getInstance().getInputItem(item));
        }

        groups = new ArrayList<>();
        for (IModelInputGroup child : StaticModelingContext.getModelingClient().getChildGroups(group)) {
            groups.add(ModelUIFactory.getInstance().getGroupItem(getSimulation(), child));
        }
        //why is this here?
        StaticModelingContext.getModelingClient().updateModelInputGroup(group);
    }

    /**
     * This is the preferred means for creating a new group with a specifc name / description
     */
    public static ModelInputGroupDisplayItem create(Simulation s, String name, String description,
            ModelInputGroupType type, Long parentGroupPK) throws IOException {
        IModelInputGroup group = new ModelInputGroup();
        group.setName(name);
        group.setDescription(description);
        group.setModelId(s.getId());
        group.setGroupType(type.name());

        if (parentGroupPK != null && parentGroupPK > 0) {
            group.setParentGroupId(parentGroupPK);
        }

        StaticModelingContext.getModelingClient().createModelInputGroup(group);

        return new ModelInputGroupDisplayItem(s, group);
    }

    /**
     * This is the preferred means for creating a new group with a piece of metadata to be used for
     * the name and description
     */
    public static ModelInputGroupDisplayItem create(Simulation s, MetaData md,
            ModelInputGroupType type, Long parentGroupPK) throws IOException {
        IModelInputGroup group = new ModelInputGroup();
        group.setModelId(s.getId());
        group.setNameAndDescriptionMetaDataId(md.getId());
        group.setGroupType(type.name());
        if (parentGroupPK != null && parentGroupPK > 0) {
            group.setParentGroupId(parentGroupPK);
        }
        StaticModelingContext.getModelingClient().createModelInputGroup(group);

        return new ModelInputGroupDisplayItem(s, group);
    }

    /**
     * Sets the scenario for this display group; sets the scenario for all children
     */
    @Override
    public void setScenario(Scenario s) throws IncompatibleScenarioException {
        super.setScenario(s);
        for (ModelInputDisplayItem item : getAllItems()) {
            item.setScenario(s);
        }
    }

    @Override
    public int getOrder() {
        return group.getDisplayItemOrder();
    }

    /**
     * Set the index of this (group) element in the parent contains.  **Currently the
     * parent container is always the top most simulation display.
     */
    @Override
    public void setOrder(int o) {
        group.setDisplayItemOrder(o);
        StaticModelingContext.getModelingClient().updateModelInputGroup(group);
    }

    public List<ModelInputDisplayItem> getAllItems() {
        List<ModelInputDisplayItem> result = new ArrayList<>();
        result.addAll(items);
        result.addAll(groups);
        Collections.sort(result);
        return result;
    }

    /**
     * This method will add a new element to this group, and create a new dao for this item.
     * This is the primary means by which new groups can be formed.
     */
    public ModelInputDisplayItem addDisplayItem(MetaData d, ModelInputWidgetType type)
            throws IOException {
        if (!knownMetaData.contains(d)) {
            ModelInputIndividualDisplayItem item =
                    ModelInputIndividualDisplayItem
                            .create(getSimulation(), d, type);
            item.item.setModelGroupId(group.getId());
            StaticModelingContext.getModelingClient().updateModelInputItem(item.item);
            items.add(item);
            return item;
        }
        return null;
    }

    public void addChildGroup(ModelInputGroupDisplayItem group)
            throws IllegalUIConfigurationException, IOException {
        if (group.getGroupType() == ModelInputGroupType.TAB) {
            throw new IllegalUIConfigurationException("Tabs cannot be contained by other groups");
        }
        group.setParent(this);
    }

    public ModelInputGroupType getGroupType() {
        //return ModelInputGroupType.valueOf("HORIZONTAL");
        return group.getGroupType() == null || group.getGroupType().trim().isEmpty()
                ? ModelInputGroupType.HORIZONTAL
                : ModelInputGroupType.valueOf(group.getGroupType());
    }

    public void setGroupType(ModelInputGroupType type) {
        group.setGroupType(type.name());

    }

    public void setParent(ModelInputGroupDisplayItem parent) throws IOException {
        ModelInputGroupDisplayItem old = this.parent;
        group.setParentGroupId(parent == null ? null : parent.group.getId());
        StaticModelingContext.getModelingClient().updateModelInputGroup(group);
        this.parent = parent;
        if (old != null) {
            old.populateChildren();
        }
        parent.populateChildren();

    }

    /**
     * This method will remove a display item from this group, and update the backing store
     * accordingly
     */
    public void removeDisplayItem(MetaData d) throws IOException {
        ModelInputIndividualDisplayItem toRemove = null;
        for (ModelInputDisplayItem item : getDisplayItems()) {
            if (item.getMetaData().equals(d)) {
                toRemove = (ModelInputIndividualDisplayItem) item;
                break;
            }
        }
        if (toRemove != null) {
            knownMetaData.remove(toRemove.getMetaData());
            StaticModelingContext.getModelingClient().deleteModelInputItem(toRemove.item.getId());

        }
        populateChildren();

    }

    /**
     * Get all display items contained by this group.  Items sorted according to their order
     * **Currently, each of these will be
     * a {@link ModelInputIndividualDisplayItem}
     */
    public List<ModelInputDisplayItem> getDisplayItems() {
        Collections.sort(items);
        return items;
    }

    /**
     * Removes given group from the model.
     */
    public void delete() throws IOException {
        for (ModelInputGroupDisplayItem gchild : getChildGroups()) {
            gchild.setParent(null);
        }
        for (ModelInputDisplayItem item : getDisplayItems()) {
            ((ModelInputIndividualDisplayItem) item).setGroupId(null);
        }
        populateChildren();
        StaticModelingContext.getModelingClient().deleteModelInputGroup(group.getId());
    }

    public List<ModelInputGroupDisplayItem> getChildGroups() {
        Collections.sort(groups);
        return groups;
    }

    /**
     * Returns original value of name property (from db).
     */
    public String getOriginalName() {
        return group.getName();
    }

    /**
     * Returns original value of description property (from db).
     */
    public String getOriginalDescription() {
        return group.getDescription();
    }

    /**
     * Sets meta data to passed value
     *
     * @param md meta data
     */
    public void setMetaData(MetaData md) {
        group.setNameAndDescriptionMetaDataId(md == null ? null : md.getId());
        StaticModelingContext.getModelingClient().updateModelInputGroup(group);
    }

    @Override
    public JsonObjectBuilder toJson() {
        final JsonObjectBuilder jsonBuilder = super.toJson()
                .add("groupType", getGroupType().name())
                .add("groupId", getGroupId())
                .add("parentGroupId", getParentGroupId());

        JsonArrayBuilder childInputs = Json.createArrayBuilder();
        for (ModelInputDisplayItem input : getAllItems()) {
            childInputs.add(input.toJson());
        }

        return jsonBuilder.add("inputs", childInputs);
    }

    /**
     * Returns the name on the metadata, or if present, the name stored in the backing data for
     * this elemtn
     * A non-null name in the dao will always override the metadata name.
     *
     */
    @Override
    public String getName() {
        try {
            return group.getName() == null || group.getName().trim().equals("") ?
                    StaticModelingContext.getModelingClient().getMetaData(group) == null ?
                            null : StaticModelingContext.getModelingClient().getMetaData(group).getName()
                    : group.getName();
        } catch (IOException e) {
            _log.error("Could not retrieve group description", e);
        }
        return null;
    }

    /**
     * Sets the name for this element.  Will override any name in the metadata.  This method
     * sets a value in the backing store.
     */
    public void setName(String name) {
        group.setName(name);
        StaticModelingContext.getModelingClient().updateModelInputGroup(group);
    }

    /**
     * Returns the description on the metadata, or if present the description stored in the
     * backing data for this element
     * A non-null description in the dao will always override the metadata description.
     *
     */
    @Override
    public String getDescription() {
        try {
            return group.getDescription() == null || group.getDescription().trim().equals("") ?
                    StaticModelingContext.getModelingClient().getMetaData(group) == null ?
                            null
                            : StaticModelingContext.getModelingClient().getMetaData(group).getDescription()
                    : group.getDescription();
        } catch (IOException e) {
            _log.error("Could not retrieve group description", e);
        }
        return null;
    }

    /**
     * Sets the description for this element.  Will override any description in the metadata.  This
     * method sets a value in the backing store.
     */
    public void setDescription(String desc) {
        group.setDescription(desc);
        StaticModelingContext.getModelingClient().updateModelInputGroup(group);
    }

    /**
     * Returns display item type.
     */
    @Override
    public ModelInputDisplayItemType getDisplayItemType() {
        return ModelInputDisplayItemType.GROUP;
    }

    /**
     * Returns group ID.
     */
    public Long getGroupId() {
        return group.getId();
    }

    public Long getParentGroupId() {
        return group.getParentGroupId();
    }
}

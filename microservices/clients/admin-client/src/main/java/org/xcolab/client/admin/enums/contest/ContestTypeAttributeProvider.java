package org.xcolab.client.admin.enums.contest;

import org.springframework.util.Assert;

import org.xcolab.client.admin.ContestTypeClient;
import org.xcolab.client.admin.pojo.ContestTypeAttribute;
import org.xcolab.util.attributes.i18n.LocalizableAttributeProvider;

class ContestTypeAttributeProvider
        implements LocalizableAttributeProvider<ContestTypeAttribute> {

    private final String name;

    ContestTypeAttributeProvider(String name) {
        Assert.notNull(name, "Name is required");
        this.name = name;
    }

    @Override
    public String name() {
        return name;
    }

    @Override
    public ContestTypeAttribute get() {
        throw new UnsupportedOperationException("ContestTypeAttributes require an id");
    }

    @Override
    public ContestTypeAttribute get(long additionalId) {
        return ContestTypeClient.getContestTypeAttribute(name, additionalId, null);
    }

    @Override
    public ContestTypeAttribute get(String locale) {
        throw new UnsupportedOperationException("ContestTypeAttributes require an id");
    }

    @Override
    public ContestTypeAttribute get(String locale, long additionalId) {
        return ContestTypeClient.getContestTypeAttribute(name, additionalId, locale);
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }

        if (!(obj instanceof ContestTypeAttributeProvider)) {
            return false;
        }

        final ContestTypeAttributeProvider
                other = (ContestTypeAttributeProvider) obj;
        return name.equals(other.name);
    }
}

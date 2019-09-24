package org.xcolab.client.admin.attributes.contest;

import org.springframework.util.Assert;

import org.xcolab.client.admin.StaticAdminContext;
import org.xcolab.client.admin.pojo.IContestTypeAttribute;
import org.xcolab.commons.attributes.exceptions.AttributeNotFoundException;
import org.xcolab.commons.attributes.i18n.LocalizableAttributeProvider;

class ContestTypeAttributeProvider
        implements LocalizableAttributeProvider<IContestTypeAttribute> {

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
    public IContestTypeAttribute get() {
        throw new UnsupportedOperationException("ContestTypeAttributes require an id");
    }

    @Override
    public IContestTypeAttribute get(long additionalId) {
        return StaticAdminContext.getContestTypeClient()
                .getContestTypeAttribute(name, additionalId, null)
                .<AttributeNotFoundException>orElseThrow(() -> {
                    throw new AttributeNotFoundException(
                            "ContestTypeAttribute not found with additionalId " + additionalId);
                });
    }

    @Override
    public IContestTypeAttribute get(String locale) {
        throw new UnsupportedOperationException("ContestTypeAttributes require an id");
    }

    @Override
    public IContestTypeAttribute get(String locale, long additionalId) {
        return StaticAdminContext.getContestTypeClient()
                .getContestTypeAttribute(name, additionalId, locale)
                .<AttributeNotFoundException>orElseThrow(() -> {
                    throw new AttributeNotFoundException(
                            "ContestTypeAttribute not found with locale  " + locale
                                    + " and additionalId " + additionalId);
                });
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

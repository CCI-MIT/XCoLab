package com.ext.portlet.service.persistence;

import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;

import java.io.Serializable;

/**
 * @author Brian Wing Shun Chan
 * @generated
 */
public class ConfigurationAttributePK implements Comparable<ConfigurationAttributePK>,
    Serializable {
    public String name;
    public long additionalId;

    public ConfigurationAttributePK() {
    }

    public ConfigurationAttributePK(String name, long additionalId) {
        this.name = name;
        this.additionalId = additionalId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getAdditionalId() {
        return additionalId;
    }

    public void setAdditionalId(long additionalId) {
        this.additionalId = additionalId;
    }

    @Override
    public int compareTo(ConfigurationAttributePK pk) {
        if (pk == null) {
            return -1;
        }

        int value = 0;

        value = name.compareTo(pk.name);

        if (value != 0) {
            return value;
        }

        if (additionalId < pk.additionalId) {
            value = -1;
        } else if (additionalId > pk.additionalId) {
            value = 1;
        } else {
            value = 0;
        }

        if (value != 0) {
            return value;
        }

        return 0;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof ConfigurationAttributePK)) {
            return false;
        }

        ConfigurationAttributePK pk = (ConfigurationAttributePK) obj;

        if ((name.equals(pk.name)) && (additionalId == pk.additionalId)) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        return (String.valueOf(name) + String.valueOf(additionalId)).hashCode();
    }

    @Override
    public String toString() {
        StringBundler sb = new StringBundler(10);

        sb.append(StringPool.OPEN_CURLY_BRACE);

        sb.append("name");
        sb.append(StringPool.EQUAL);
        sb.append(name);

        sb.append(StringPool.COMMA);
        sb.append(StringPool.SPACE);
        sb.append("additionalId");
        sb.append(StringPool.EQUAL);
        sb.append(additionalId);

        sb.append(StringPool.CLOSE_CURLY_BRACE);

        return sb.toString();
    }
}

package com.ext.portlet.service.persistence;

import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;

import java.io.Serializable;


public class EmailListPK implements Comparable<EmailListPK>, Serializable {
    public String name;
    public String email;

    public EmailListPK() {
    }

    public EmailListPK(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int compareTo(EmailListPK pk) {
        if (pk == null) {
            return -1;
        }

        int value = 0;

        value = name.compareTo(pk.name);

        if (value != 0) {
            return value;
        }

        value = email.compareTo(pk.email);

        if (value != 0) {
            return value;
        }

        return 0;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }

        EmailListPK pk = null;

        try {
            pk = (EmailListPK) obj;
        } catch (ClassCastException cce) {
            return false;
        }

        if ((name.equals(pk.name)) && (email.equals(pk.email))) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        return (String.valueOf(name) + String.valueOf(email)).hashCode();
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
        sb.append("email");
        sb.append(StringPool.EQUAL);
        sb.append(email);

        sb.append(StringPool.CLOSE_CURLY_BRACE);

        return sb.toString();
    }
}

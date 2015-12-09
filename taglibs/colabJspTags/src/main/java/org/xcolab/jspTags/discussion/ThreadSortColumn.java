package org.xcolab.jspTags.discussion;

import com.liferay.portal.kernel.exception.PortalException;

public enum ThreadSortColumn {
    TITLE,
    REPLIES,
    LAST_COMMENTER,
    DATE;

    public static ThreadSortColumn from(String name) throws NoSuchThreadSortColumnException {
        for (ThreadSortColumn sortColumn : ThreadSortColumn.values()) {
            if (sortColumn.name().equalsIgnoreCase(name)) {
                return sortColumn;
            }
        }
        throw new NoSuchThreadSortColumnException("Invalid name: " + name);
    }

    public static class NoSuchThreadSortColumnException extends PortalException {
        public NoSuchThreadSortColumnException(String message) {
            super(message);
        }
    }
}

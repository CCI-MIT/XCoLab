package org.xcolab.client.comment.util;

public enum ThreadSortColumn {
    TITLE("title"),
    REPLIES("comments"),
    LAST_COMMENTER("activityAuthor"),
    DATE("activityDate");

    private final String identifier;

    ThreadSortColumn(String identifier) {

        this.identifier = identifier;
    }

    public static ThreadSortColumn from(String name) throws NoSuchThreadSortColumnException {
        for (ThreadSortColumn sortColumn : ThreadSortColumn.values()) {
            if (sortColumn.name().equalsIgnoreCase(name)) {
                return sortColumn;
            }
        }
        throw new NoSuchThreadSortColumnException("Invalid name: " + name);
    }

    public String getIdentifier(boolean ascending) {
        if (ascending) {
            return identifier;
        } else {
            return "-" + identifier;
        }
    }

    public static class NoSuchThreadSortColumnException extends RuntimeException {

        public NoSuchThreadSortColumnException(String message) {
            super(message);
        }
    }
}

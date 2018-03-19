package org.xcolab.commons.exceptions;


public class ReferenceResolutionException extends IllegalStateException {
    public ReferenceResolutionException(String msg) {
        super(msg);
    }

    public ReferenceResolutionException(Throwable cause) {
        super(cause);
    }

    public ReferenceResolutionException(String msg, Throwable cause) {
        super(msg, cause);
    }

    private ReferenceResolutionException(Class<?> toObject, Object toId) {
        super(String.format("%s %s does not exist", toObject.getSimpleName(), toId));
    }

    private ReferenceResolutionException(Class<?> toObject, Object toId,
            Class<?> fromObject, Object fromId) {
        super(String.format("%s %s, referred from %s %s, does not exist",
                toObject.getSimpleName(), toId, fromObject.getSimpleName(), fromId));
    }

    public static Builder toObject(Class<?> toObject, Object objectId) {
        return new Builder(toObject, objectId);
    }

    public static class Builder {

        private final Class<?> toObject;
        private final Object toId;

        private Builder(Class<?> toObject, Object toId) {

            this.toObject = toObject;
            this.toId = toId;
        }

        public ReferenceResolutionException build() {
            return new ReferenceResolutionException(toObject, toId);
        }

        public ReferenceResolutionException fromObject(Class<?> fromObject, Object objectId) {
            return new ReferenceResolutionException(toObject, toId, fromObject, objectId);
        }
    }
}

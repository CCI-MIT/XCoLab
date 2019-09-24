package org.xcolab.client.user.exceptions;

public class MemberCategoryNotFoundException extends IllegalStateException {
    public MemberCategoryNotFoundException(){
        super();
    }
    public MemberCategoryNotFoundException(String msg) {
        super(msg);
    }
}

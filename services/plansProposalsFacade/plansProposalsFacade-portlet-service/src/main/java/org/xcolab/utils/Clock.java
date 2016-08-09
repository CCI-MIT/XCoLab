package org.xcolab.utils;

import java.util.Date;

/**
 * The implementation of this interface can be changed to alter the clock in unit tests
 */
public interface Clock {
    Date now();
}

package org.xcolab.core.events;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Annotation for methods that are used as event handlers.
 * 
 * <p>
 * Each method that should be used as a event handler should be annotated with this annotation, in addition this method
 * should accept exactly one parameter (it's class will be taken as event type).
 * </p> 
 *  
 * @author janusz
 *
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface EventListener {
 
}

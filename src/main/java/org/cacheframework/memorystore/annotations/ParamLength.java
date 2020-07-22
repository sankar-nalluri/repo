package org.cacheframework.memorystore.annotations;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Annotation to specify number of parameters
 * allowed in a command
 * 
 * @version 0.1
 * @since JDK 1.8
 * @author Vijay
 *
 */

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
public @interface ParamLength {
  int value();
}

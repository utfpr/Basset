/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.m4rc310.basset.binders.annotations;

import java.lang.annotation.*;

/**
 *
 * @author tchulla
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.METHOD})
@Documented
public @interface Command {
    String text() default "";
    CommandType type() default CommandType.JBUTTON;
    boolean enable() default false;
    
}
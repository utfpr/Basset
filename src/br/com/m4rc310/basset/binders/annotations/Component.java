/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.m4rc310.basset.binders.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 *
 * @author tchulla
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD})
//@Documented
public @interface Component {
    
}

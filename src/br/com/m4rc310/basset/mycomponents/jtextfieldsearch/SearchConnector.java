/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.m4rc310.basset.mycomponents.jtextfieldsearch;

import java.util.List;

/**
 *
 * @author tchulla
 */
public interface SearchConnector<T> {
//    ANYWHERE
    T get(String cod, String description);
    List<T>getList(String cod, String description, boolean anywhere);
    
    String[] converter(T t);
    boolean isValid(T t);
    Class getType();
}

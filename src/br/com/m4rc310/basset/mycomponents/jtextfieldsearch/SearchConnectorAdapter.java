/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.m4rc310.basset.mycomponents.jtextfieldsearch;

import java.lang.reflect.ParameterizedType;
import java.util.List;

/**
 *
 * @author tchulla
 */
public abstract class SearchConnectorAdapter<T> implements SearchConnector<T> {

    @Override
    public T get(String cod, String description) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<T> getList(String cod, String description, boolean anywhere) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public String[] converter(T t) {
        return new String[]{"",""};
    }

    @Override
    public boolean isValid(T t) {
        return true;
    }

    @Override
    public Class getType() {
        Class clazz = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
        return clazz;
    }
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.m4rc310.basset.binders;

import javax.swing.JComponent;

/**
 *
 * @author tchulla
 */
public interface BinderListeners<M,V> {
    void changeComponent(M o, V component);
}

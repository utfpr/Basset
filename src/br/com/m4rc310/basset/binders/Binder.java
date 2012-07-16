/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.m4rc310.basset.binders;

import java.lang.reflect.Method;
import javax.swing.JComponent;

/**
 *
 * @author tchulla
 */
public interface Binder <M,V> {
    void setModelAndView(M model, V view);
    void addListeners();
    
    void addBinderListenerses(BinderListeners bl);
    void removeBinderListenerses(BinderListeners bl);
    
    void setInitModel(M model);
            
    void updateModel(V view);
    void updateView(M model);
    
    void setMethodInc(Method methodInc);
    void setObject(Object object);
    
    M getNewInstance();
    
    Class getClassModel();
    JComponent getComponent();
}

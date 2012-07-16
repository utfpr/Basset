/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.m4rc310.basset.binders.basics;

import br.com.m4rc310.basset.binders.BinderImpl;
import javax.swing.InputVerifier;
import javax.swing.JComponent;
import javax.swing.JTextField;

/**
 *
 * @author tchulla
 */
public class ObjectBind extends BinderImpl<Object, JComponent> {

    @Override
    public Object getNewInstance() {
        return new Object();
    }

    @Override
    public JComponent getComponent() {
        return new JComponent() {};
    }

    
}

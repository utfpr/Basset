/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.m4rc310.basset.binders.basics;

import br.com.m4rc310.basset.binders.BinderImpl;
import br.com.m4rc310.basset.components.ComponentFactury2;
import javax.swing.JComponent;
import javax.swing.JPanel;

/**
 *
 * @author tchulla
 */
public class ObjectBind extends BinderImpl<Object, JPanel> {

    @Override
    public Object getNewInstance() {
        return new Object();
    }

    @Override
    public JComponent getComponent() {
        
        return new JPanel();
    }

    @Override
    public void updateModel(JPanel view) {
        super.updateModel(view);
    }

    @Override
    public void updateView(Object model) {
        view = new ComponentFactury2().getJPanel(model);
    }

    @Override
    public void setInitModel(Object model) {
        System.out.println("----");
        view = new ComponentFactury2().getJPanel(model);
    }
    
    

    
    
    
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.m4rc310.basset.binders.basics;

import br.com.m4rc310.basset.binders.BinderImpl;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.lang.reflect.Method;
import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 *
 * @author tchulla
 */
public class BooleanBind extends BinderImpl<Boolean, JCheckBox> {

    @Override
    public JComponent getComponent() {
        return new JCheckBox();
    }

    @Override
    public void setMethodInc(Method methodInc) {
        super.setMethodInc(methodInc);
    }

    @Override
    public void setModelAndView(Boolean model, JCheckBox view) {
        super.setModelAndView(model, view);
    }

    
    
    
    @Override
    public void updateModel(JCheckBox view) {
        model = view.isSelected();
        putModel(model);
    }

    @Override
    public void updateView(Boolean model) {
        view.setSelected(model);
    }

    
    
    @Override
    public Boolean getNewInstance() {
        return Boolean.FALSE;
    }

    @Override
    public void addListeners() {
    
        view.addItemListener(new ItemListener() {

            @Override
            public void itemStateChanged(ItemEvent ie) {
                updateModel(view);
            }
        });
    }
   
    
    

}

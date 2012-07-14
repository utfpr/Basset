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
public class LongBind extends BinderImpl<Long, JTextField> {

    @Override
    public Long getNewInstance() {
        return Long.MIN_VALUE;
    }

    @Override
    public JComponent getComponent() {
        JTextField r = new JTextField(5);
        r.setHorizontalAlignment(JTextField.RIGHT);
        return r;
    }

    @Override
    public void updateModel(JTextField view) {
        model = Long.parseLong(view.getText());
    }

    @Override
    public void updateView(Long model) {
        view.setText(model.toString());
    }

    @Override
    public void addListeners() {
        view.setInputVerifier(new InputVerifier() {

            @Override
            public boolean verify(JComponent jc) {
                try {
                    Long value = Long.parseLong(view.getText());
                    putModel(value);
                    updateView(value);
                } catch (Exception e) {
                    return false;
                }
                
                return true;
            }
        });
        
        
    }
    
    
}

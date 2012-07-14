/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.m4rc310.basset.binders.basics;

import br.com.m4rc310.basset.binders.BinderImpl;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.InputVerifier;
import javax.swing.JComponent;
import javax.swing.JTextField;

/**
 *
 * @author tchulla
 */
public class StringBind extends BinderImpl<String, JTextField> {

    @Override
    public void setModelAndView(String model, JTextField view) {
        super.setModelAndView(model, view);
    }

    @Override
    public JComponent getComponent() {
        JTextField ret = new JTextField(15);
        return ret;
    }

    @Override
    public void updateModel(JTextField view) {
        super.model = view.getText();
        putModel(model);
    }

    @Override
    public void updateView(String model) {
        view.setText(model);
    }
    
    

    @Override
    public void addListeners() {
        view.addKeyListener(new KeyAdapter() {

            @Override
            public void keyReleased(KeyEvent ke) {
                updateModel((JTextField)ke.getComponent());
            }
            
        });
        
        view.setInputVerifier(new InputVerifier() {

            @Override
            public boolean verify(JComponent jc) {
                updateModel((JTextField)jc);
                return true;
            }
        });
    }

    @Override
    public String getNewInstance() {
        return new String();
    }
    
    
}

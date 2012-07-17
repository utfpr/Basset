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
public class IntegerBind extends BinderImpl<Integer, JTextField> {

    @Override
    public Integer getNewInstance() {
        return Integer.MIN_VALUE;
    }

    @Override
    public JComponent getComponent() {
        JTextField ret = new JTextField(3);
        ret.setHorizontalAlignment(JTextField.RIGHT);
        
        return ret;
    }

    @Override
    public void updateModel(JTextField view) {
        model = Integer.parseInt(view.getText());
    }

    @Override
    public void updateView(Integer model) {
        view.setText(model.toString());
    }

    @Override
    public void addListeners() {
        view.addKeyListener(new KeyAdapter() {

            @Override
            public void keyPressed(KeyEvent ke) {
                System.out.println(ke);
            }
            
        });
    }
    
}

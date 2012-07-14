/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.m4rc310.basset.binders.basics;

import br.com.m4rc310.basset.binders.BinderImpl;
import br.com.m4rc310.utils.DateUtils;
import java.text.ParseException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.InputVerifier;
import javax.swing.JComponent;
import javax.swing.JTextField;

/**
 *
 * @author tchulla
 */
public class DateBind extends BinderImpl<Date, JTextField> {

    @Override
    public JComponent getComponent() {
        return new JTextField(10);
    }

    @Override
    public void updateModel(JTextField view) {
        try {
            model = DateUtils.getDate(view.getText(), "dd/MM/yyyy");
            putModel(model);
        } catch (ParseException ex) {
//            Logger.getLogger(DateBind.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void updateView(Date model) {
        try {
            view.setText(DateUtils.dateToString(model, "dd/MM/yyyy"));
            putModel(model);
        } catch (Exception ex) {
            Logger.getLogger(DateBind.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Override
    public void addListeners() {
        view.setInputVerifier(new InputVerifier() {

            @Override
            public boolean verify(JComponent jc) {
                try {
                    updateView(DateUtils.getDate(((JTextField)jc).getText()));
                } catch (Exception e) {
                }
                return true;
            }
        });
    }

    @Override
    public Date getNewInstance() {
        return new Date();
    }
    
    
    
    
    
}

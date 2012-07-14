/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.m4rc310.basset.binders.basics;

import br.com.m4rc310.basset.binders.BinderImpl;
import br.com.m4rc310.basset.teste.EnumSexo;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

/**
 *
 * @author tchulla
 */
public class EnumBind extends BinderImpl<EnumSexo, JComboBox> {

    @Override
    public EnumSexo getNewInstance() {
        return EnumSexo.MASCULINO;
    }

    @Override
    public JComponent getComponent() {
        JComboBox combo = new JComboBox(EnumSexo.values());
        
        combo.setRenderer(new DefaultListCellRenderer(){

            @Override
            public Component getListCellRendererComponent(JList jlist, Object o, int i, boolean bln, boolean bln1) {
                return super.getListCellRendererComponent(jlist, ((EnumSexo)o).getValue(), i, bln, bln1);
            }
        });
        return combo;
    }

    @Override
    public void updateModel(JComboBox view) {
        model = (EnumSexo) view.getSelectedItem();
        putModel(model);
    }

    @Override
    public void updateView(EnumSexo model) {
        view.setSelectedItem(model);
    }

    @Override
    public void addListeners() {
        view.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                updateModel(view);
            }
        });
        
    
    }
    
    
    
    
    

}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.m4rc310.utils;

import java.awt.Color;
import java.awt.Font;
import javax.swing.*;
import javax.swing.text.JTextComponent;

/**
 *
 * @author tchulla
 */
public class ComponentsUtils {
    private Font fontDefault;

    public ComponentsUtils() {
        fontDefault = UIManager.getDefaults().getFont("TabbedPane.font");
    }
    
    public JTextField getJTextField(int column){
        JTextField ret = new JTextField(column);
        setFont(ret);
        return ret;
    }
    
    public JLabel getJLabel(String text){
        JLabel ret = new JLabel(text);
        setFont(ret);
        return ret;
    }
    
    public JPanel getJPanel(String label){
        JPanel panel = new JPanel();
        if(label != null){
            panel.setBorder(BorderFactory.createTitledBorder(label));
        }
        return panel;
    }
    
    public void setEnable(boolean value, JComponent ... components){
        for (JComponent com : components) {
            com.setEnabled(value);
        }
    }
    public void setText(String text, JTextComponent ... components){
        for (JTextComponent jtc : components) {
            jtc.setText(text);
        }
    }
    
    public void setBackgroundColor(Color color, JTextComponent ... components){
        for (JTextComponent jtc : components) {
            jtc.setBackground(color);
        }
    }
    
    private void setFont(JComponent component){
        component.setFont(fontDefault);
    }
    
}

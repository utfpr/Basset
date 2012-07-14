/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.m4rc310.utils;

import java.awt.Component;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import net.miginfocom.swing.MigLayout;

/**
 *
 * @author tchulla
 */
public class JComponentsStringLayout {
    
    private Map<String,String> mapsComponents;
    
    private JComponentsStringLayout() {
        mapsComponents = new HashMap<String, String>();
    }
    
    public static JComponentsStringLayout getInstance() {
        return JComponentsStringLayoutHolder.INSTANCE;
    }

    
    private static class JComponentsStringLayoutHolder {
        private static final JComponentsStringLayout INSTANCE = new JComponentsStringLayout();
    }
    
    private void loadMapComponents(){
        
    }
    
    public void putComponentStringLayout(JComponent component, String stringLayout){
        System.out.println(component.getClass().getName());
        
        System.out.println(component.getContainerListeners().length);
    }
    
    public String getStringLayout(String component, String default_){
        if(mapsComponents.containsKey(component)){
            return mapsComponents.get(component);
        }else{
            mapsComponents.put(component, default_);
            return default_;
        }
    }
    
    public static void main(String[] args) {
        new JComponentsStringLayout().teste();
    }
    
    private void teste() {
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new MigLayout());
        
        JButton jButton = new JButton("Teste");
        
        frame.add(jButton, "wrap");
        
        
        frame.pack();
        frame.setVisible(true);
    }
    
}

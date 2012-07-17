/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.m4rc310.basset.components;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JComponent;

/**
 *
 * @author tchulla
 */
public class EventForComponent {

    private Object object;
    private Map<JComponent, Method> maps;

    public EventForComponent() {
        maps = new HashMap<JComponent, Method>();
    }

    public EventForComponent(Object object) {
        this.object = object;
    }

    public synchronized void addComponent(Object object, Method method, JComponent component) {
        if (!maps.containsKey(component)) {
            this.object = object;
            maps.put(component, method);
        }
    }

    @Override
    public boolean equals(Object o) {
        return object.equals(o);
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 61 * hash + (this.object != null ? this.object.hashCode() : 0);
        return hash;
    }

    public void processEnable() {
        try {

            for (Map.Entry<JComponent, Method> entry : maps.entrySet()) {
                JComponent jComponent = entry.getKey();
                Method m = entry.getValue();

                Boolean value = (Boolean) m.invoke(object);
                jComponent.setEnabled(value);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void processEditable() {
        try {

            for (Map.Entry<JComponent, Method> entry : maps.entrySet()) {
                JComponent jComponent = entry.getKey();
                Method m = entry.getValue();

                Boolean value = (Boolean) m.invoke(object);
                jComponent.setEnabled(value);
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void processVisible() {
        try {
            for (Map.Entry<JComponent, Method> entry : maps.entrySet()) {
                JComponent jComponent = entry.getKey();
                Method m = entry.getValue();

                Boolean value = (Boolean) m.invoke(object);
                jComponent.setVisible(value);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}

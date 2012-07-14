/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.m4rc310.basset.binders;

import br.com.m4rc310.utils.JComponentsStringLayout;
import br.com.m4rc310.utils.Primitives;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import net.miginfocom.swing.MigLayout;
import resource.B;

/**
 *
 * @author tchulla
 */
public class ComponentFactury {

    private Object o;

    public JComponent getComponent(Object o, Field field) {

        Binder bind = BinderManager.getInstance().getBinderForType(field.getType());

        if (bind != null) {
            JComponent com = bind.getComponent();
            
            com.setName(o.getClass().getName() + "." + field.getName());

            Method mtGet = getMethodsReturn(o, field);
            try {
                
                if(mtGet == null) return com;
                
                Object value = mtGet.invoke(o);

                
                if (!BinderManager.getInstance().isModeDebug()) {

                    Object model= mtGet.invoke(o);
                    bind.setModelAndView(model, com);
                    bind.addListeners();
                    bind.setObject(o);
                    
                    String mtSet = mtGet.getName();
                    mtSet = mtSet.replace("get", "set");
                    mtSet = mtSet.replace("is", "set");

                    bind.setMethodInc(o.getClass().getDeclaredMethod(mtSet, field.getType()));
                    bind.setInitModel(value);

                }

            } catch (Exception ex) {
                Logger.getLogger(ComponentFactury.class.getName()).log(Level.SEVERE, null, ex);
            }

            return com;

        }

        return null;
    }

    private boolean existMethod(Object o, String method) {
        try {
            o.getClass().getDeclaredMethod(method);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    private Method getMethodsReturn(Object o, Field f) {
        try {
            if (existMethod(o, getNameMethodIs(f))) {
                return o.getClass().getDeclaredMethod(getNameMethodIs(f));
            }

            if (existMethod(o, getNameMethodGet(f))) {
                return o.getClass().getDeclaredMethod(getNameMethodGet(f));
            }
            return null;
        } catch (Exception e) {
            return null;
        }
    }

    private String getNameMethodGet(Field field) {
        String m = field.getName();
        m = "get" + m.substring(0, 1).toUpperCase() + m.substring(1, m.length());
        return m;
    }

    private String getNameMethodIs(Field field) {
        String m = field.getName();
        m = "is" + m.substring(0, 1).toUpperCase() + m.substring(1, m.length());
        return m;
    }

    JPanel p = new JPanel();
    public JPanel getJPanel(final Object o) {
        
        System.out.println(BinderManager.getInstance());
        if(BinderManager.getInstance().isModeDebug()){
            p.setLayout(new MigLayout("debug"));
        }else{
            p.setLayout(new MigLayout());
        }
        
        for (Field field : o.getClass().getDeclaredFields()) {
            try {
                JComponent com = getComponent(o, field);
                if (com != null) {
                    JLabel jLabel = new JLabel(B.getString(o.getClass().getSimpleName().toLowerCase() + "." + field.getName()));
                    jLabel.addMouseListener(getMouseListener());
                    com.addMouseListener(getMouseListener());
                    
                    p.add(jLabel);
                    p.add(com, g(com.getName(),"wrap"));
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return p;
    }
    
    public String g(String component, String _default){
        return JComponentsStringLayout.getInstance().getStringLayout(component, _default);
    }
    
    private MouseListener getMouseListener(){
        return new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent me) {
                JComponent com = (JComponent) me.getComponent();
                com.setBorder(BorderFactory.createTitledBorder(""));
                
//                p.add(new ComponentFactury().getJPanel(com));
                
            }
            
        };
    }
}

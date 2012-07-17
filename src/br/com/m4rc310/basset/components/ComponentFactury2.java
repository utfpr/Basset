/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.m4rc310.basset.components;

import br.com.m4rc310.basset.binders.Binder;
import br.com.m4rc310.basset.binders.BinderListeners;
import br.com.m4rc310.basset.binders.BinderManager;
import br.com.m4rc310.basset.binders.annotations.*;
import br.com.m4rc310.basset.teste.Aluno;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import net.miginfocom.swing.MigLayout;
import resource.B;

/**
 *
 * @author tchulla
 */
public class ComponentFactury2 {

    private BinderManager binderManager;
    private EventForComponent eventForComponent;

    public ComponentFactury2() {
        this.binderManager = BinderManager.getInstance();
        this.eventForComponent = new EventForComponent();
    }

//    private static class NewSingletonHolder {
//        private static final ComponentFactury2 INSTANCE = new ComponentFactury2();
//    }
//    
//    public static ComponentFactury2 getInstance() {
//        return ComponentFactury2.NewSingletonHolder.INSTANCE;
//    }
//    
    public JComponent getComponent(final Object o, final Method method) {
        try {
            if (method.isAnnotationPresent(Command.class)) {
                Command command = method.getAnnotation(Command.class);

                switch (command.type()) {
                    case JBUTTON:
                        JButton jButton = new JButton(b(o, method.getName()));
                        jButton.addActionListener(new ActionListener() {

                            @Override
                            public void actionPerformed(ActionEvent ae) {
                                try {
                                    method.invoke(o);
                                    eventForComponent.processEditable();
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                        });
                        return jButton;
                }

            }
            return null;

        } catch (Exception e) {
            return null;
        }
    }

    public JComponent getComponent(final Object o, final Field field) {
        Binder binder = binderManager.getBinderForField(field);
        try {

            if (binder == null) {
                return null;
            }

            JComponent com = binder.getComponent();
            com.setName((o.getClass().getName() + "." + field.getName()).toLowerCase());

            Method methodReturn = getMethodsReturn(o, field);

            if (methodReturn == null) {
                Logger.getLogger(ComponentFactury.class.getName()).log(Level.WARNING,
                        "Metodos GET e SET não implementados para o modelo: {0}",
                        o);
                return com;
            }

            if (BinderManager.getInstance().isModeDebug()) {
                return com;
            }

            Object value = getValue(o, methodReturn);


            binder.setModelAndView(value, com);
            binder.addListeners();
            binder.setObject(o);

            String mtSet = methodReturn.getName();
            mtSet = mtSet.replace("get", "set");
            mtSet = mtSet.replace("is", "set");

            binder.setMethodInc(o.getClass().getDeclaredMethod(mtSet, field.getType()));
            binder.setInitModel(value);

            binder.addBinderListenerses(new BinderListeners() {

                @Override
                public void changeComponent(Object obj, Object component) {
                    eventForComponent.processEditable();
                }
            });

            return com;

        } catch (Exception e) {
            Logger.getLogger(ComponentFactury.class.getName()).log(Level.INFO, null, e);
        }
        return null;
    }
    
//    private JComponent getComponentAnnotado(final Object o, final Field field){
//        Component component = field.getAnnotation(Component.class);
//        
//        switch(component.type()){
//            case JLABEL:
//                return new JLabel(component.value());
//                
//        }
//        return null;
//    }

    private void addControlEnable(Object o, Method method, JComponent component) {
        try {
            Depends depends = method.getAnnotation(Depends.class);
            Method m = o.getClass().getMethod(depends.method());
            eventForComponent.addComponent(o, m, component);
            
        } catch (Exception e) {
        }
    }
    private void addControlEnable(Object o, Field field, JComponent component) {
        try {
            Depends depends = field.getAnnotation(Depends.class);
            Method m = o.getClass().getMethod(depends.method());
            eventForComponent.addComponent(o, m, component);
        } catch (Exception e) {
//            Logger.getLogger(ComponentFactury.class.getName()).log(Level.INFO, null, e);
        }
    }

    public JPanel getJPanel(final Object object) {

        JPanel j = new JPanel();
        j.setLayout(new MigLayout("inset 0"));

        for (Field field : object.getClass().getDeclaredFields()) {
            try {

                JComponent component = null;
                component = getComponent(object, field);
                if(field.isAnnotationPresent(Component.class)){
                }else{
//                    component = getComponentAnnotado(object, field);
                }
                

                JLabel label = getJLabel(object, field);
                if (label != null) {
                    j.add(label);
                    addControlEnable(object, field, label);
                } else {
                    if (component instanceof AbstractButton) {
                        ((AbstractButton) component).setText(b(object, field.getName()));
                    }
                }

                j.add(component, getStringLayout(field));
                addControlEnable(object, field, component);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }

        eventForComponent.processEditable();
        
        for (Method method : object.getClass().getDeclaredMethods()) {
            JComponent component = getComponent(object, method);
            
            if(component!=null){
                j.add(component);
                addControlEnable(object, method, component);
            }
            
        }
        

        return j;
    }

    private String getStringLayout(Field field) {
        if (field.isAnnotationPresent(LayoutString.class)) {
            return field.getAnnotation(LayoutString.class).value();
        }
        return "";
    }

    private Object getValue(Object o, Method method) {
        try {
            Object value = method.invoke(o);

            return value;
        } catch (Exception e) {
            return null;
        }
    }

    public JLabel getJLabel(final Object o, Field field) {
        if (field.isAnnotationPresent(Label.class)) {
            Label label = field.getAnnotation(Label.class);

            if (label.ignore()) {
                return null;
            }

            return new JLabel(b(o, label.value()));
        }



        return new JLabel(b(o, field.getName()));
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

    private boolean existMethod(Object o, String method) {
        try {
            o.getClass().getDeclaredMethod(method);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    private String b(Object o, String key) {
        return B.getString((o.getClass().getSimpleName() + "." + key).toLowerCase());
    }

    public static void main(String[] args) {
        new ComponentFactury2().teste();
    }

    private void teste() {
        Aluno a = new Aluno();

        for (Field field : a.getClass().getDeclaredFields()) {
            getComponent(a, field);
        }

    }
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.m4rc310.basset.binders;

import br.com.m4rc310.basset.binders.annotations.*;
import br.com.m4rc310.basset.binders.basics.ObjectBind;
import br.com.m4rc310.utils.ComponentsUtils;
import br.com.m4rc310.utils.JComponentsStringLayout;
import java.awt.event.*;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import net.miginfocom.swing.MigLayout;
import resource.B;

/**
 *
 * @author tchulla
 */
public class ComponentFactury {

    private Object teste;
    private ComponentsUtils ju;
    
    private List<EnableComponent> listComponents;
    
    public ComponentFactury() {
        listComponents = new ArrayList<EnableComponent>();
        ju = new ComponentsUtils();
        teste = new Object();
    }

    public JComponent getComponent(final Object o, final Field field) {

        Binder bind = BinderManager.getInstance().getBinderForType(field.getType());

        if (bind != null) {
            JComponent com = bind.getComponent();


            com.setName(o.getClass().getName() + "." + field.getName());

            Method mtGet = getMethodsReturn(o, field);
            try {

                if (mtGet == null) {
                    return com;
                }

                Object value = mtGet.invoke(o);


                if (!BinderManager.getInstance().isModeDebug()) {

                    Object model = mtGet.invoke(o);
                    bind.setModelAndView(model, com);
                    bind.addListeners();
                    bind.setObject(o);

                    String mtSet = mtGet.getName();
                    mtSet = mtSet.replace("get", "set");
                    mtSet = mtSet.replace("is", "set");

                    bind.setMethodInc(o.getClass().getDeclaredMethod(mtSet, field.getType()));
                    bind.setInitModel(value);


                    bind.addBinderListenerses(new BinderListeners() {
                        @Override
                        public void changeComponent(Object obj, Object component) {
                            
                            for (EnableComponent ec : listComponents) {
                                ec.processEnable();
                            }
                            
                            
                        }
                    });
                    
                    if (field.isAnnotationPresent(Depends.class)) {
                        Depends depends = field.getAnnotation(Depends.class);
                        
//                        System.out.println(o.getClass().getDeclaredMethod(depends.method()));
                        
                        Method m = o.getClass().getMethod(depends.method());
                        listComponents.add(new EnableComponent(o, m , com));
                    }
                    
                }

            } catch (Exception ex) {
                Logger.getLogger(ComponentFactury.class.getName()).log(Level.SEVERE, null, ex);
            }

            return com;

        } else {
            try {

                if (field.isAnnotationPresent(Component.class)) {

                    Method mtGet = getMethodsReturn(o, field);

                    if (mtGet != null) {
                        final Object object = mtGet.invoke(o) == null ? field.getType().newInstance() : mtGet.invoke(o);

                        Binder binder = new ObjectBind();

                        binder.addListeners();
                        binder.setModelAndView(object, new ComponentFactury().getJPanel(object));

                        binder.setObject(object);



//                        if(object==null){
//                            return new ComponentFactury().getJPanel(field.getType().newInstance());
//                        }else{
//                            return new ComponentFactury().getJPanel(object);
//                        }

                    } else {
                        Logger.getLogger(ComponentFactury.class.getName()).log(Level.INFO, "Não há um metodo GET para o parametro: {0}", field.getName());
                    }
                }

            } catch (Exception ex) {
                Logger.getLogger(ComponentFactury.class.getName()).log(Level.SEVERE, null, ex);
            }
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
        if (BinderManager.getInstance().isModeDebug()) {
            p.setLayout(new MigLayout("debug, inset 0"));
        } else {
            p.setLayout(new MigLayout("inset 0"));
        }



        for (Field field : o.getClass().getDeclaredFields()) {
            try {
                JComponent com = getComponent(o, field);
                if (com != null) {
                    JLabel label = ju.getJLabel("");


                    if (field.isAnnotationPresent(Label.class)) {
                        Label l = field.getAnnotation(Label.class);
                        label.setText(B.getString(l.value()));

                        if (l.ignore()) {
                            p.add(com, g(com.getName(), "wrap, split, span, growx"));
                        } else {
                            p.add(label);
                            p.add(com, g(com.getName(), "wrap"));
                        }
                    } else {
                        label.setText(B.getString(o.getClass().getSimpleName().toLowerCase() + "." + field.getName()));
                        p.add(label);

                        if (field.isAnnotationPresent(LayoutString.class)) {

                            LayoutString ls = field.getAnnotation(LayoutString.class);
                            p.add(com, g(com.getName(), ls.value()));
                        } else {
                            p.add(com, g(com.getName(), "wrap"));
                        }
                    }


//                    getCommands(o);


//                    jLabel.addMouseListener(getMouseListener());
//                    com.addMouseListener(getMouseListener());

                }

            } catch (Exception e) {
                System.out.println("---------------------");
                System.out.println(e.getClass().getName());
                System.out.println(e.getMessage());
                System.out.println("---------------------");
            }
        }


        getCommands(o, p);

        return p;
    }

    private JComponent getCommands(final Object o, JPanel p1) {
        try {
            Class type = o.getClass();

            AbstractButton jButtonRet = null;

            for (final Method method : type.getDeclaredMethods()) {
                if (method.isAnnotationPresent(Command.class)) {
                    Command command = method.getAnnotation(Command.class);

                    switch (command.type()) {
                        case JBUTTON:
                            jButtonRet = ju.getJButton(command.text());
                    }

                    if (jButtonRet != null) {
                        jButtonRet.addActionListener(new ActionListener() {

                            @Override
                            public void actionPerformed(ActionEvent ae) {
                                try {

                                    System.out.println(o);
                                    System.out.println(method.getName());

                                    System.out.println(method.invoke(o));
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                        });

                        p1.add(jButtonRet);
                    }
                }
            }


        } catch (Exception e) {
        }
        return null;
    }

    public String g(String component, String _default) {
        return JComponentsStringLayout.getInstance().getStringLayout(component, _default);
    }

    private MouseListener getMouseListener() {
        return new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent me) {
                JComponent com = (JComponent) me.getComponent();
                com.setBorder(BorderFactory.createTitledBorder(""));

//                p.add(new ComponentFactury().getJPanel(com));

            }
        };
    }
    
    private class EnableComponent{
        private Object object;
        private Method method;
        private JComponent component;

        public EnableComponent() {
        }

        public EnableComponent(Object object, Method method, JComponent component) {
            this.object = object;
            this.method = method;
            this.component = component;
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
        
        public void processEnable(){
            try {
                Boolean value = (Boolean) method.invoke(object);
                component.setEnabled(value);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        
    }
    
}

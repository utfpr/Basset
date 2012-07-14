/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.m4rc310.basset.binders;

import br.com.m4rc310.basset.mycomponents.jtextfieldsearch.ConnectorsManager;
import br.com.m4rc310.basset.mycomponents.jtextfieldsearch.JTextFieldSearch;
import br.com.m4rc310.basset.mycomponents.jtextfieldsearch.SearchConnector;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;

/**
 *
 * @author tchulla
 */
public abstract class BinderImpl<M, V> implements Binder<M, V> {

    protected M model;
    protected V view;

    private Method methodInc;
    private Object object;
    

    @Override
    public void setMethodInc(Method methodInc) {
        this.methodInc = methodInc;
    }

    @Override
    public void setObject(Object object) {
        this.object = object;
    }
    
    protected void putModel(M m){
        try {
            methodInc.invoke(object, m);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    

    @Override
    public void setInitModel(M model) {
        if(model==null){
            this.model = getNewInstance();
        }else{
            updateView(model);
        }
    }
    

    @Override
    public void updateView(M model) {
    }

    @Override
    public void updateModel(V view) {
    }
    
    
    
    
    @Override
    public void setModelAndView(M model, V view) {
        this.model = model;
        this.view = view;
        
        if(view instanceof JTextFieldSearch){
            SearchConnector connector = 
                    ConnectorsManager.getInstance().getSearchConnectorForType(getClassModel());
            
            //System.out.println("-- " + view.hashCode());
            
            JTextFieldSearch jtfs = (JTextFieldSearch)view;
            jtfs.setSearchAdapter(connector);
            jtfs.setObject(model);
            updateModel(view);
            //System.out.println("-- " + jtfs.hashCode());
        }
        
    }

    @Override
    public Class getClassModel() {
        Class clazz = (Class<M>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
        return clazz;
    }

    @Override
    public void addListeners() {
//        throw new UnsupportedOperationException("Not supported yet.");
    }
    
}

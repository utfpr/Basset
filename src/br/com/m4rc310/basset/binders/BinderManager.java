/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.m4rc310.basset.binders;

import br.com.m4rc310.utils.Primitives;
import java.util.ArrayList;
import java.util.List;
import net.sf.trugger.scan.ClassScan;

/**
 *
 * @author tchulla
 */
public class BinderManager {
    
    private static BinderManager INSTANCE; 
    
    private List<String> packagesWithBinders;
    private List<Binder> binders; 
    
    private boolean modeDebug;
    
    private BinderManager() {
        packagesWithBinders = new ArrayList<String>();
        binders = new ArrayList<Binder>();
        scanBinds();
    }
    
    private static class NewSingletonHolder{
        private static final BinderManager INSTANCE = new BinderManager();
    } 
    
    
    

    public void setModeDebug(boolean modeDebug) {
        this.modeDebug = modeDebug;
    }

    public boolean isModeDebug() {
        return modeDebug;
    }
    
    public static BinderManager getInstance() {
        return BinderManager.NewSingletonHolder.INSTANCE;
    }
    
//    public static BinderManager getInstance(){
//        
//        if(INSTANCE != null){
//            return INSTANCE;
//        }else{
//            System.out.println(INSTANCE);
//            return new BinderManager();
//        }
//    }
    
    private void scanBinds(){
        
        scanBinds(Binder.class.getPackage().getName());
        
        if(!packagesWithBinders.isEmpty()){
            for (String package_ : packagesWithBinders) {
                scanBinds(package_);
            }
        }
    }
    
    private void scanBinds(String packageNameNode){
        for (Class clazz : ClassScan.findAll().recursively().assignableTo(Binder.class).in(packageNameNode)) {
            try {
                if(!clazz.isInterface()){
                    if(clazz == BinderImpl.class){continue;}
                    Binder obj = (Binder) clazz.newInstance();
                    binders.add(obj);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    
    public void addPackageWithBinds(String packageNode){
        if (!packagesWithBinders.contains(packageNode)) {
            packagesWithBinders.add(packageNode);
        }
    }
    
    public Binder getBinderForType(Class type){
        
        type = type.isPrimitive()?Primitives.toWrapper(type):type;
        
        for (Binder binder : binders) {
            
            if(binder.getClassModel() == type){
                return binder;
            }
        }
        return null;
    }
    
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.m4rc310.basset.mycomponents.jtextfieldsearch;

import br.com.m4rc310.basset.binders.Binder;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.sf.trugger.scan.ClassScan;

/**
 *
 * @author tchulla
 */
public class ConnectorsManager {

    private List<SearchConnector> searchConnectors;
    private List<String> packageList;
    private String defaultPackage;
    

    private ConnectorsManager() {
        defaultPackage = SearchConnector.class.getPackage().getName();
        packageList = new ArrayList<String>();
        searchConnectors = new ArrayList<SearchConnector>();
        loadConnectors(defaultPackage);
    }
    
    public void addPackageName(String _package){
        if(!packageList.contains(_package)){
            packageList.add(_package);
            loadConnectors(_package);
        }
    }

    public static ConnectorsManager getInstance() {
        return ConnectorsManagerHolder.INSTANCE;
    }

    private static class ConnectorsManagerHolder {
        private static final ConnectorsManager INSTANCE = new ConnectorsManager();
    }

    private void loadConnectors(String _package) {
        for (Class clazz : ClassScan.findAll().recursively().assignableTo(SearchConnector.class).in(_package)) {
            if(!clazz.isInterface()){
                if(clazz == SearchConnectorAdapter.class)continue;
                try {
                    searchConnectors.add((SearchConnector)clazz.newInstance());
                } catch (Exception ex) {
                    Logger.getLogger(ConnectorsManager.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
    
    public SearchConnector getSearchConnectorForType(Class type){
        for (SearchConnector sc : searchConnectors) {
            
            if(sc.getType() == type){
                return sc;
            }
        }
        return null;
    }
    
    
    public void teste(){
        
    }
}

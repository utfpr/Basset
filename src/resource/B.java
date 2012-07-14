/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package resource;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author tchulla
 */
public class B {
    
    private static final String RESOURCE_FILE = "resource/Bundle";
    
    public static String getString(String key){
        return getString(key, key);
    }
    public static String getString(String key, String valueDefault){
        try {
          return ResourceBundle.getBundle(RESOURCE_FILE).getString(key);
        } catch (Exception e) {
            return valueDefault;
        }
    }
    
    
    /**
     * TODO apagar    
     * Os metodos abaixo foram gerados automáticamente com intuito de testar as funcionalidades 
     * desta classe quando necessario.
     */
    
    public static void main(String[] args) {
        new B().teste();
    }

    private void teste(){
        System.out.println(getString("teste..mmma","mls"));
    }

}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.m4rc310.basset;

import br.com.m4rc310.basset.binders.BinderManager;
import br.com.m4rc310.basset.binders.ComponentFactury;
import br.com.m4rc310.basset.teste.Aluno;
import br.com.m4rc310.basset.teste.Endereco;
import javax.swing.JDialog;
import net.miginfocom.swing.MigLayout;

/**
 *
 * @author tchulla
 */
public class Main {
    
    private Aluno aluno;
    private Endereco endereco;
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        BinderManager.getInstance();
        
        new Main().teste();
        
        
    }

    private void teste2() {
        String bb = "a";
        
    }
    private void teste() {
        ComponentFactury cf = new ComponentFactury();
        
        JDialog j = new JDialog();
        j.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        j.setLayout(new MigLayout("inset 5"));
        
        Aluno aluno = new Aluno();
        
        j.add(cf.getJPanel(aluno));
        j.pack();
        
        j.setModal(true);
        j.setVisible(true);
        
    }
    
    
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.m4rc310.basset.binders.out;

import br.com.m4rc310.basset.binders.BinderImpl;
import br.com.m4rc310.basset.mycomponents.jtextfieldsearch.JTextFieldSearch;
import br.com.m4rc310.basset.teste.Endereco;
import javax.swing.JComponent;

/**
 *
 * @author tchulla
 */
public class EnderecoBind extends BinderImpl<Endereco, JTextFieldSearch<Endereco>> {

    @Override
    public Endereco getNewInstance() {
        return new Endereco();
    }

    @Override
    public JComponent getComponent() {
        JTextFieldSearch<Endereco> jtfs = new JTextFieldSearch<Endereco>();
        jtfs.setColumnSize(3);
        return jtfs;
    }
}

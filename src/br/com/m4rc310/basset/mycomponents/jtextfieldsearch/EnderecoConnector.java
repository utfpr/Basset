/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.m4rc310.basset.mycomponents.jtextfieldsearch;

import br.com.m4rc310.basset.teste.Aluno;
import br.com.m4rc310.basset.teste.Endereco;
import br.com.m4rc310.basset.teste.HashStore;
import java.util.List;

/**
 *
 * @author tchulla
 */
public class EnderecoConnector extends SearchConnectorAdapter<Endereco> {

    @Override
    public Endereco get(String cod, String description) {
        return HashStore.getInstance().getEndereco(Long.parseLong(cod));
    }

    @Override
    public String[] converter(Endereco t) {
        if(t != null){
            return new String[]{t.getId().toString(), t.getRua()};
        }
        return new String[]{"", ""};
    }

    
    
    
    
}

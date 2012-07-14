/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.m4rc310.basset.mycomponents.jtextfieldsearch;

import br.com.m4rc310.basset.teste.Aluno;
import br.com.m4rc310.basset.teste.HashStore;
import java.util.List;

/**
 *
 * @author tchulla
 */
public class AlunoConnector extends SearchConnectorAdapter<Aluno> {

    @Override
    public Aluno get(String cod, String description) {
        try {
            Aluno a = HashStore.getInstance().getAluno(Long.parseLong(cod));
            System.out.println(a);
            return a;
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public List<Aluno> getList(String cod, String description, boolean anywhere) {
        System.out.println(cod);
        System.out.println(description);
        return null;
    }

    @Override
    public String[] converter(Aluno t) {
        if(t==null){
            return new String[]{"",""};
        }else{
            System.out.println(t.getRa());
            return new String[]{t.getRa()+"", t.getNome()};
        }
    }

    @Override
    public boolean isValid(Aluno t) {
        return true;
    }

}

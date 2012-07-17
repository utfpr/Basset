/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.m4rc310.basset.teste;

import br.com.m4rc310.basset.binders.annotations.*;

/**
 *
 * @author tchulla
 */
public class TesteDeAcoes {
    
    @Component
    @Label(ignore=true)
    private Aluno aluno;

    public Aluno getAluno() {
        return aluno;
    }

    public void setAluno(Aluno aluno) {
        this.aluno = aluno;
    }
    
    @Command(label="Salvar",type= CommandType.JBUTTON)
    public void salvar(){
        System.out.println("Salvando: " + aluno);
//        return true;
    }
    
}

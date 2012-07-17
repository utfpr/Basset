/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.m4rc310.basset.teste;

import br.com.m4rc310.basset.binders.annotations.Command;
import br.com.m4rc310.basset.binders.annotations.CommandType;
import br.com.m4rc310.basset.binders.annotations.Component;
import br.com.m4rc310.basset.binders.annotations.Depends;
import br.com.m4rc310.utils.DateUtils;
import java.util.Date;

/**
 *
 * @author tchulla
 */
public class Arquivo {

    private String nome;
    private Date dataNascimento;
    
    
    private Aluno aluno;
    
    @Depends(method="validaData")
    private String status;

    public boolean validaData() {
        try {
            return DateUtils.getIdade(dataNascimento) < 18L;
        } catch (Exception e) {
            return false;
        }
    }
    
    public void salvar(){
        System.out.println(nome);
    }

    public Aluno getAluno() {
        return aluno;
    }

    public void setAluno(Aluno aluno) {
        this.aluno = aluno;
    }

    
    
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    
    public Date getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(Date dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}

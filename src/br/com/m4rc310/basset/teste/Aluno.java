/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.m4rc310.basset.teste;

import br.com.m4rc310.basset.binders.annotations.Depends;
import br.com.m4rc310.basset.binders.annotations.Label;
import br.com.m4rc310.basset.binders.annotations.LayoutString;
import java.util.Date;

/**
 *
 * @author tchulla
 */
public class Aluno {
    @LayoutString("split")
    private Long ra;
    @LayoutString("wrap")
    @Label(ignore=true,value="Bloqueado")
    private boolean bloqueado;
    @LayoutString("growx, wrap")
    private String nome;
    @LayoutString("growx, wrap")
    private String sobreNome;
    
    @Depends(method="isBloqueado")
    private Date dataNascimento;
    
    @Depends(method="isBloqueado")
    private EnumSexo sexo;
    
    @Label(value="Endereço:", stringLayout="wrap")
    private Endereco endereco;
    

    public Long getRa() {
        return ra;
    }

    public void setRa(Long ra) {
        this.ra = ra;
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

    public String getSobreNome() {
        return sobreNome;
    }

    public void setSobreNome(String sobreNome) {
        this.sobreNome = sobreNome;
    }

    public EnumSexo getSexo() {
        return sexo;
    }

    public void setSexo(EnumSexo sexo) {
        this.sexo = sexo;
    }

    public boolean isBloqueado() {
        return bloqueado;
    }

    public void setBloqueado(boolean bloqueado) {
        this.bloqueado = bloqueado;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }
    
    
}

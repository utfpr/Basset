/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.m4rc310.basset.teste;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author tchulla
 */
public class HashStore {
    
    private Map<Long,Aluno> alunoMap;
    private Map<Long,Endereco> enderecoMap;
    
    private HashStore() {
        alunoMap = new HashMap<Long, Aluno>();
        enderecoMap = new HashMap<Long, Endereco>();
        popularHash();
    }
    
    public static HashStore getInstance() {
        return HashStoreHolder.INSTANCE;
    }
    
    private static class HashStoreHolder {

        private static final HashStore INSTANCE = new HashStore();
    }
    
    
    private void popularHash(){
        alunoMap.put(1L, getAluno(1L, "Marcelo", "Lopes da Silva", EnumSexo.MASCULINO, true));
        alunoMap.put(2L, getAluno(2L, "Joao", "Marcos de souza", EnumSexo.MASCULINO, true));
        alunoMap.put(3L, getAluno(3L, "Monica", "Farias", EnumSexo.FEMININO, true));
        
        enderecoMap.put(1L, getEndereco(1L, "rua tocantins", "centro", "Corumbataí do Sul"));
        enderecoMap.put(2L, getEndereco(2L, "rua sao paulo", "centro", "Corumbataí do Sul"));
    }
    
    private Endereco getEndereco(Long id, String rua, String bairro, String municipio){
        Endereco e = new Endereco();
        e.setId(id);
        e.setRua(rua);
        e.setBairro(bairro);
        e.setMunicipio(municipio);
        
        return e;
    }
    
    private Aluno getAluno(Long ra,String nome, String sobrenome, EnumSexo sexo, boolean bloqueado){
        Aluno a = new Aluno();
        a.setRa(ra);
        a.setNome(nome);
        a.setSobreNome(sobrenome);
        a.setSexo(sexo);
        a.setBloqueado(bloqueado);
        
        return a;
    }
    
    public Aluno getAluno(Long ra){
        return alunoMap.get(ra);
    }
    public Endereco getEndereco(Long id){
        return enderecoMap.get(id);
    }
}

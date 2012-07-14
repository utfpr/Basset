/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.m4rc310.basset.teste;

import resource.B;

/**
 *
 * @author tchulla
 */
public enum EnumSexo {
    INDEFINIDO("I"),
    MASCULINO("M"),
    FEMININO("F");
    private String value;

    private EnumSexo(String value) {
        this.value = value;
    }

    public String getValue() {
        return B.getString(EnumSexo.class.getSimpleName().toLowerCase()+"."+value);
    }
    
}

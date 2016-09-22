/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.Date;

/**
 *
 * @author aalano87
 */
public class ValorVenda {
    private int idvalorvenda;
    private double valor;
    private Date datainserido;
    
    private String modificado;
    
    public int getIdvalorvenda() {
        return idvalorvenda;
    }

    public void setIdvalorvenda(int idvalorvenda) {
        this.idvalorvenda = idvalorvenda;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public Date getDatainserido() {
        return datainserido;
    }

    public void setDatainserido(Date datainserido) {
        this.datainserido = datainserido;
    }

    public String getModificado() {
        return modificado;
    }

    public void setModificado(String modificado) {
        this.modificado = modificado;
    }

   
    
}

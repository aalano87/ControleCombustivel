/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package model;

import java.util.Objects;

/**
 *
 * @author novo
 */
public class Proprietario {
    private int id;
    private String nome;
    private String documento;
    private String modificado;
    private String vendadiesel;

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 89 * hash + this.id;
        hash = 89 * hash + Objects.hashCode(this.nome);
        hash = 89 * hash + Objects.hashCode(this.documento);
        hash = 89 * hash + Objects.hashCode(this.modificado);
        hash = 89 * hash + Objects.hashCode(this.vendadiesel);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Proprietario other = (Proprietario) obj;
        if (this.id != other.id) {
            return false;
        }
        if (!Objects.equals(this.nome, other.nome)) {
            return false;
        }
        if (!Objects.equals(this.documento, other.documento)) {
            return false;
        }
        if (!Objects.equals(this.modificado, other.modificado)) {
            return false;
        }
        if (!Objects.equals(this.vendadiesel, other.vendadiesel)) {
            return false;
        }
        return true;
    }

    

    @Override
    public String toString() {
        return this.nome;
    }

   

  

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public String getModificado() {
        return modificado;
    }

    public void setModificado(String modificado) {
        this.modificado = modificado;
    }

    public String getVendadiesel() {
        return vendadiesel;
    }

    public void setVendadiesel(String vendadiesel) {
        this.vendadiesel = vendadiesel;
    }

    
}

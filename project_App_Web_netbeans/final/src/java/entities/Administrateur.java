/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author imed
 */
@Entity
@Table(name = "administrateur")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Administrateur.findAll", query = "SELECT a FROM Administrateur a"),
    @NamedQuery(name = "Administrateur.findByIdAdmin", query = "SELECT a FROM Administrateur a WHERE a.idAdmin = :idAdmin"),
    @NamedQuery(name = "Administrateur.findByNomAdmin", query = "SELECT a FROM Administrateur a WHERE a.nomAdmin = :nomAdmin"),
    @NamedQuery(name = "Administrateur.findByPrenomAdmin", query = "SELECT a FROM Administrateur a WHERE a.prenomAdmin = :prenomAdmin"),
    @NamedQuery(name = "Administrateur.findByPasseAdmin", query = "SELECT a FROM Administrateur a WHERE a.passeAdmin = :passeAdmin"),
    @NamedQuery(name = "Administrateur.findByLoginAdmin", query = "SELECT a FROM Administrateur a WHERE a.loginAdmin = :loginAdmin")})
public class Administrateur implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idAdmin")
    private Integer idAdmin;
    @Size(max = 254)
    @Column(name = "nomAdmin")
    private String nomAdmin;
    @Size(max = 254)
    @Column(name = "prenomAdmin")
    private String prenomAdmin;
    @Column(name = "passeAdmin")
    private String passeAdmin;
    @Column(name = "loginAdmin")
    private String loginAdmin;

    public Administrateur() {
    }

    public Administrateur(Integer idAdmin) {
        this.idAdmin = idAdmin;
    }

    public Integer getIdAdmin() {
        return idAdmin;
    }

    public void setIdAdmin(Integer idAdmin) {
        this.idAdmin = idAdmin;
    }

    public String getNomAdmin() {
        return nomAdmin;
    }

    public void setNomAdmin(String nomAdmin) {
        this.nomAdmin = nomAdmin;
    }

    public String getPrenomAdmin() {
        return prenomAdmin;
    }

    public void setPrenomAdmin(String prenomAdmin) {
        this.prenomAdmin = prenomAdmin;
    }

    public String getPasseAdmin() {
        return passeAdmin;
    }

    public void setPasseAdmin(String passeAdmin) {
        this.passeAdmin = passeAdmin;
    }

    public String getLoginAdmin() {
        return loginAdmin;
    }

    public void setLoginAdmin(String loginAdmin) {
        this.loginAdmin = loginAdmin;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idAdmin != null ? idAdmin.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Administrateur)) {
            return false;
        }
        Administrateur other = (Administrateur) object;
        if ((this.idAdmin == null && other.idAdmin != null) || (this.idAdmin != null && !this.idAdmin.equals(other.idAdmin))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Administrateur[ idAdmin=" + idAdmin + " ]";
    }
    
}

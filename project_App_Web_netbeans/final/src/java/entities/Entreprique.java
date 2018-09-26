/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author imed
 */
@Entity
@Table(name = "entreprique")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Entreprique.findAll", query = "SELECT e FROM Entreprique e"),
    @NamedQuery(name = "Entreprique.findByIdEntreprise", query = "SELECT e FROM Entreprique e WHERE e.idEntreprise = :idEntreprise"),
    @NamedQuery(name = "Entreprique.findByNumEntreprise", query = "SELECT e FROM Entreprique e WHERE e.numEntreprise = :numEntreprise"),
    @NamedQuery(name = "Entreprique.findByNomEntreprise", query = "SELECT e FROM Entreprique e WHERE e.nomEntreprise = :nomEntreprise"),
    @NamedQuery(name = "Entreprique.findByTeleEntreprise", query = "SELECT e FROM Entreprique e WHERE e.teleEntreprise = :teleEntreprise"),
    @NamedQuery(name = "Entreprique.findByAdresseEntreprise", query = "SELECT e FROM Entreprique e WHERE e.adresseEntreprise = :adresseEntreprise")})
public class Entreprique implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idEntreprise")
    private Integer idEntreprise;
    @Column(name = "numEntreprise")
    private Integer numEntreprise;
    @Size(max = 254)
    @Column(name = "nomEntreprise")
    private String nomEntreprise;
    @Column(name = "teleEntreprise")
    private Integer teleEntreprise;
    @Size(max = 254)
    @Column(name = "adresseEntreprise")
    private String adresseEntreprise;
        @OneToMany(mappedBy = "identreprise")
    private Collection<Client> clientCollection;

    public Entreprique() {
    }

    public Entreprique(Integer idEntreprise) {
        this.idEntreprise = idEntreprise;
    }

    public Collection<Client> getClientCollection() {
        return clientCollection;
    }

    public void setClientCollection(Collection<Client> clientCollection) {
        this.clientCollection = clientCollection;
    }

 

    public Integer getIdEntreprise() {
        return idEntreprise;
    }

    public void setIdEntreprise(Integer idEntreprise) {
        this.idEntreprise = idEntreprise;
    }

    public Integer getNumEntreprise() {
        return numEntreprise;
    }

    public void setNumEntreprise(Integer numEntreprise) {
        this.numEntreprise = numEntreprise;
    }

    public String getNomEntreprise() {
        return nomEntreprise;
    }

    public void setNomEntreprise(String nomEntreprise) {
        this.nomEntreprise = nomEntreprise;
    }

    public Integer getTeleEntreprise() {
        return teleEntreprise;
    }

    public void setTeleEntreprise(Integer teleEntreprise) {
        this.teleEntreprise = teleEntreprise;
    }

    public String getAdresseEntreprise() {
        return adresseEntreprise;
    }

    public void setAdresseEntreprise(String adresseEntreprise) {
        this.adresseEntreprise = adresseEntreprise;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idEntreprise != null ? idEntreprise.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Entreprique)) {
            return false;
        }
        Entreprique other = (Entreprique) object;
        if ((this.idEntreprise == null && other.idEntreprise != null) || (this.idEntreprise != null && !this.idEntreprise.equals(other.idEntreprise))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Entreprique[ idEntreprise=" + idEntreprise + " ]";
    }
    
}

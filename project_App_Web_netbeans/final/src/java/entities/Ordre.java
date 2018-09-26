/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author imed
 */
@Entity
@Table(name = "ordre")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Ordre.findAll", query = "SELECT o FROM Ordre o"),
    @NamedQuery(name = "Ordre.findByIdOrdre", query = "SELECT o FROM Ordre o WHERE o.idOrdre = :idOrdre"),
    @NamedQuery(name = "Ordre.findByIdAction", query = "SELECT o FROM Ordre o WHERE o.idAction = :idAction"),
    @NamedQuery(name = "Ordre.findByIdClient", query = "SELECT o FROM Ordre o WHERE o.idClient = :idClient"),
    @NamedQuery(name = "Ordre.findByDateOrdre", query = "SELECT o FROM Ordre o WHERE o.dateOrdre = :dateOrdre"),
    @NamedQuery(name = "Ordre.findByNbAction", query = "SELECT o FROM Ordre o WHERE o.nbAction = :nbAction"),
    @NamedQuery(name = "Ordre.findByTypeOrdre", query = "SELECT o FROM Ordre o WHERE o.typeOrdre = :typeOrdre"),
    @NamedQuery(name = "Ordre.findByDateExecution", query = "SELECT o FROM Ordre o WHERE o.dateExecution = :dateExecution"),
    @NamedQuery(name = "Ordre.findByEtatOrdre", query = "SELECT o FROM Ordre o WHERE o.etatOrdre = :etatOrdre")})
public class Ordre implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idOrdre")
    private Integer idOrdre;
    @JoinColumn(name = "idAction", referencedColumnName = "idAction")
    @ManyToOne
    private Action idAction;
    
    @JoinColumn(name = "idClient", referencedColumnName = "idClient")
    @ManyToOne
    private Client idClient;
    @Column(name = "dateOrdre")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateOrdre;
    
    @Column(name = "nbAction")
    private Integer nbAction;
    @Size(max = 254)
    @Column(name = "typeOrdre")
    private String typeOrdre;
    @Column(name = "dateExecution")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateExecution;
    @Size(max = 254)
    @Column(name = "etatOrdre")
    private String etatOrdre;

    public Ordre() {
    }

    public Ordre(Integer idOrdre) {
        this.idOrdre = idOrdre;
    }

    public Ordre(Integer idOrdre, int idAction, int idClient) {
        this.idOrdre = idOrdre;
     
       
    }

    public Integer getIdOrdre() {
        return idOrdre;
    }

    public void setIdOrdre(Integer idOrdre) {
        this.idOrdre = idOrdre;
    }

    public Action getIdAction() {
        return idAction;
    }

    public void setIdAction(Action idAction) {
        this.idAction = idAction;
    }

    public Client getIdClient() {
        return idClient;
    }

    public void setIdClient(Client idClient) {
        this.idClient = idClient;
    }

    public Date getDateOrdre() {
        return dateOrdre;
    }

    public void setDateOrdre(Date dateOrdre) {
        this.dateOrdre = dateOrdre;
    }

    public Integer getNbAction() {
        return nbAction;
    }

    public void setNbAction(Integer nbAction) {
        this.nbAction = nbAction;
    }

    public String getTypeOrdre() {
        return typeOrdre;
    }

    public void setTypeOrdre(String typeOrdre) {
        this.typeOrdre = typeOrdre;
    }

    public Date getDateExecution() {
        return dateExecution;
    }

    public void setDateExecution(Date dateExecution) {
        this.dateExecution = dateExecution;
    }

    public String getEtatOrdre() {
        return etatOrdre;
    }

    public void setEtatOrdre(String etatOrdre) {
        this.etatOrdre = etatOrdre;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idOrdre != null ? idOrdre.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Ordre)) {
            return false;
        }
        Ordre other = (Ordre) object;
        if ((this.idOrdre == null && other.idOrdre != null) || (this.idOrdre != null && !this.idOrdre.equals(other.idOrdre))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Ordre[ idOrdre=" + idOrdre + " ]";
    }
    
}

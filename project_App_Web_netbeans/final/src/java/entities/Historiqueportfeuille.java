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

import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author imed
 */
@Entity
@Table(name = "historiqueportfeuille")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Historiqueportfeuille.findAll", query = "SELECT h FROM Historiqueportfeuille h"),
    @NamedQuery(name = "Historiqueportfeuille.findByDateExecu", query = "SELECT h FROM Historiqueportfeuille h WHERE h.dateModif = :dateModif"),
    @NamedQuery(name = "Historiqueportfeuille.findByValeur", query = "SELECT h FROM Historiqueportfeuille h WHERE h.valeurAction = :valeurAction"),

    @NamedQuery(name = "Historiqueportfeuille.findByNbrAction", query = "SELECT h FROM Historiqueportfeuille h WHERE h.nbrAction = :nbrAction"),
    @NamedQuery(name = "Historiqueportfeuille.findByIdHisPf", query = "SELECT h FROM Historiqueportfeuille h WHERE h.idHisPf = :idHisPf")})
public class Historiqueportfeuille implements Serializable {
    private static final long serialVersionUID = 1L;
    @Column(name = "dateExecu")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateModif;
    @Column(name = "valeur")
    private Integer valeurAction;
 
     @JoinColumn(name = "idPf", referencedColumnName = "idPf")
    @ManyToOne
    private Portefeuille idPf;
    
    
     @Column(name = "valeurTotal")
    private Integer valeurTotal;
    
    @Column(name = "nbrAction")
    private Integer nbrAction;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idHisPf")
    private Integer idHisPf;

    public Historiqueportfeuille() {
    }

    public Historiqueportfeuille(Integer idHisPf) {
        this.idHisPf = idHisPf;
    }

    public Date getDateModif() {
        return dateModif;
    }

    public void setDateModif(Date dateExecu) {
        this.dateModif = dateExecu;
    }

    public Integer getValeurAction() {
        return valeurAction;
    }

    public void setValeurAction(Integer valeur) {
        this.valeurAction = valeur;
    }

  

    public Integer getNbrAction() {
        return nbrAction;
    }

    public Portefeuille getIdPf() {
        return idPf;
    }

    public void setIdPf(Portefeuille idPortefeuille) {
        this.idPf = idPortefeuille;
    }

    public Integer getValeurTotal() {
        return valeurTotal;
    }

    public void setValeurTotal(Integer valeurTotal) {
        this.valeurTotal = valeurTotal;
    }

    public void setNbrAction(Integer nbrAction) {
        this.nbrAction = nbrAction;
    }

    public Integer getIdHisPf() {
        return idHisPf;
    }

    public void setIdHisPf(Integer idHisPf) {
        this.idHisPf = idHisPf;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idHisPf != null ? idHisPf.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Historiqueportfeuille)) {
            return false;
        }
        Historiqueportfeuille other = (Historiqueportfeuille) object;
        if ((this.idHisPf == null && other.idHisPf != null) || (this.idHisPf != null && !this.idHisPf.equals(other.idHisPf))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Historiqueportfeuille[ idHisPf=" + idHisPf + " ]";
    }
    
}

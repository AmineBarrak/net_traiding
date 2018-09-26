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
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author imed
 */
@Entity
@Table(name = "historiquegrade")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Historiquegrade.findAll", query = "SELECT h FROM Historiquegrade h"),
    @NamedQuery(name = "Historiquegrade.findByIdTrader", query = "SELECT h FROM Historiquegrade h WHERE h.idTrader = :idTrader"),
   @NamedQuery(name = "Historiquegrade.findByIdhistoriquegrade", query = "SELECT h FROM Historiquegrade h WHERE h.idhistoriquegrade = :idhistoriquegrade")})
public class Historiquegrade implements Serializable {
    private static final long serialVersionUID = 1L;

    @JoinColumn(name = "idTrader", referencedColumnName = "idTrader")
    @ManyToOne
    private Trader idTrader;
    @Column(name = "dateAffectaion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateAffectaion;
 
       @Column(name = "GRADE")
    @NotNull
    private String grade;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    private Integer idhistoriquegrade;

    public Historiquegrade() {
    }

    public Historiquegrade(Integer idhistoriquegrade) {
        this.idhistoriquegrade = idhistoriquegrade;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public Trader getIdTrader() {
        return idTrader;
    }

    public void setIdTrader(Trader idTrader) {
        this.idTrader = idTrader;
    }

    public Date getDateAffectaion() {
        return dateAffectaion;
    }

    public void setDateAffectaion(Date dateDebut) {
        this.dateAffectaion = dateDebut;
    }

  

    public Integer getIdhistoriquegrade() {
        return idhistoriquegrade;
    }

    public void setIdhistoriquegrade(Integer idhistoriquegrade) {
        this.idhistoriquegrade = idhistoriquegrade;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idTrader != null ? idTrader.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Historiquegrade)) {
            return false;
        }
        Historiquegrade other = (Historiquegrade) object;
        if ((this.idTrader == null && other.idTrader != null) || (this.idTrader != null && !this.idTrader.equals(other.idTrader))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Historiquegrade[ idTrader=" + idTrader + " ]";
    }
    
}

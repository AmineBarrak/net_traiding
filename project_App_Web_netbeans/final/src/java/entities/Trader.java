/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
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
@Table(name = "trader")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Trader.findAll", query = "SELECT t FROM Trader t"),
    @NamedQuery(name = "Trader.findByIdTrader", query = "SELECT t FROM Trader t WHERE t.idTrader = :idTrader"),
    @NamedQuery(name = "Trader.findByNomTrader", query = "SELECT t FROM Trader t WHERE t.nomTrader = :nomTrader"),
    @NamedQuery(name = "Trader.findByPrenomTrader", query = "SELECT t FROM Trader t WHERE t.prenomTrader = :prenomTrader"),
    @NamedQuery(name = "Trader.findByTelephoneTrader", query = "SELECT t FROM Trader t WHERE t.telephoneTrader = :telephoneTrader"),
    @NamedQuery(name = "Trader.findByDateRecrutement", query = "SELECT t FROM Trader t WHERE t.dateRecrutement = :dateRecrutement"),
    @NamedQuery(name = "Trader.findByMotDePasseTrader", query = "SELECT t FROM Trader t WHERE t.motDePasseTrader = :motDePasseTrader"),
    @NamedQuery(name = "Trader.findByLoginTrader", query = "SELECT t FROM Trader t WHERE t.loginTrader = :loginTrader"),
    @NamedQuery(name = "Trader.findByGrade", query = "SELECT t FROM Trader t WHERE t.grade = :grade")})
public class Trader implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idTrader")
    private Integer idTrader;
    @Size(max = 254)
    @Column(name = "nomTrader")
    private String nomTrader;
    @Size(max = 254)
    @Column(name = "prenomTrader")
    private String prenomTrader;
    @Column(name = "telephoneTrader")
    private Integer telephoneTrader;
    @Column(name = "dateRecrutement")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateRecrutement;
    @Column(name = "motDePasseTrader")
    private String motDePasseTrader;
    @Size(max = 254)
    @Column(name = "loginTrader")
    private String loginTrader;
    @Size(max = 254)
    @Column(name = "grade")
    private String grade;
        @OneToMany(mappedBy = "idTrader")
    private Collection<Client> clientCollection;
    @OneToMany(mappedBy = "idTrader")
    private Collection<Historiquegrade> historiquegradeCollection;

    public Trader() {
    }

    public Trader(Integer idTrader) {
        this.idTrader = idTrader;
    }

    public Collection<Client> getClientCollection() {
        return clientCollection;
    }

    public void setClientCollection(Collection<Client> clientCollection) {
        this.clientCollection = clientCollection;
    }

    public Collection<Historiquegrade> getHistoriquegradeCollection() {
        return historiquegradeCollection;
    }

    public void setHistoriquegradeCollection(Collection<Historiquegrade> historiquegradeCollection) {
        this.historiquegradeCollection = historiquegradeCollection;
    }
    

    public Integer getIdTrader() {
        return idTrader;
    }

    public void setIdTrader(Integer idTrader) {
        this.idTrader = idTrader;
    }

    public String getNomTrader() {
        return nomTrader;
    }

    public void setNomTrader(String nomTrader) {
        this.nomTrader = nomTrader;
    }

    public String getPrenomTrader() {
        return prenomTrader;
    }

    public void setPrenomTrader(String prenomTrader) {
        this.prenomTrader = prenomTrader;
    }

    public Integer getTelephoneTrader() {
        return telephoneTrader;
    }

    public void setTelephoneTrader(Integer telephoneTrader) {
        this.telephoneTrader = telephoneTrader;
    }

    public Date getDateRecrutement() {
        return dateRecrutement;
    }

    public void setDateRecrutement(Date dateRecrutement) {
        this.dateRecrutement = dateRecrutement;
    }

    public String getMotDePasseTrader() {
        return motDePasseTrader;
    }

    public void setMotDePasseTrader(String motDePasseTrader) {
        this.motDePasseTrader = motDePasseTrader;
    }

    public String getLoginTrader() {
        return loginTrader;
    }

    public void setLoginTrader(String loginTrader) {
        this.loginTrader = loginTrader;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
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
        if (!(object instanceof Trader)) {
            return false;
        }
        Trader other = (Trader) object;
        if ((this.idTrader == null && other.idTrader != null) || (this.idTrader != null && !this.idTrader.equals(other.idTrader))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Trader[ idTrader=" + idTrader + " ]";
    }
    
}

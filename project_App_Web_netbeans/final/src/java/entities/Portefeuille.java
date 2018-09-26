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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
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
@Table(name = "portefeuille")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Portefeuille.findAll", query = "SELECT p FROM Portefeuille p"),
    
    @NamedQuery(name = "Portefeuille.findByClientAction", query = "SELECT p FROM Portefeuille p WHERE p.idClient = :idClient AND p.idAction= :idAction"),
    
    @NamedQuery(name = "Portefeuille.findByIdPf", query = "SELECT p FROM Portefeuille p WHERE p.idPf = :idPf"),
    @NamedQuery(name = "Portefeuille.findByIdAction", query = "SELECT p FROM Portefeuille p WHERE p.idAction = :idAction"),
    @NamedQuery(name = "Portefeuille.findByIdClient", query = "SELECT p FROM Portefeuille p WHERE p.idClient = :idClient"),

    @NamedQuery(name = "Portefeuille.findByDateOuverture", query = "SELECT p FROM Portefeuille p WHERE p.dateOuverture = :dateOuverture"),
    @NamedQuery(name = "Portefeuille.findByValeurTotal", query = "SELECT p FROM Portefeuille p WHERE p.valeurTotal = :valeurTotal"),
    @NamedQuery(name = "Portefeuille.findByNbrAction", query = "SELECT p FROM Portefeuille p WHERE p.nbrAction = :nbrAction")})
public class Portefeuille implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idPf")
    private Integer idPf;
    
    
     @JoinColumn(name = "idAction", referencedColumnName = "idAction")
    @ManyToOne
    private Action idAction;
   
    
     @JoinColumn(name = "idClient", referencedColumnName = "idClient")
    @ManyToOne
    private Client idClient;
    
    
    
    @Column(name = "dateOuverture")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateOuverture;
    @Column(name = "valeurTotal")
    private Integer valeurTotal;
    @Column(name = "nbrAction")
    private Integer nbrAction;
    
    @OneToMany(mappedBy = "idPf")
    private Collection<Historiqueportfeuille> historiqueportefeuilleCollection;
     
    public Portefeuille() {
    }

    public Portefeuille(Integer idPf) {
        this.idPf = idPf;
    }

    public Portefeuille(Integer idPf, int idAction, int idClient, int idHisPf) {
        this.idPf = idPf;
        
      
    }

    public Integer getIdPf() {
        return idPf;
    }

    public void setIdPf(Integer idPf) {
        this.idPf = idPf;
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



    public Date getDateOuverture() {
        return dateOuverture;
    }

    public void setDateOuverture(Date dateOuverture) {
        this.dateOuverture = dateOuverture;
    }

    public Integer getValeurTotal() {
        return valeurTotal;
    }

    public void setValeurTotal(Integer valeurTotal) {
        this.valeurTotal = valeurTotal;
    }

    public Integer getNbrAction() {
        return nbrAction;
    }

    public void setNbrAction(Integer nbrAction) {
        this.nbrAction = nbrAction;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idPf != null ? idPf.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Portefeuille)) {
            return false;
        }
        Portefeuille other = (Portefeuille) object;
        if ((this.idPf == null && other.idPf != null) || (this.idPf != null && !this.idPf.equals(other.idPf))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Portefeuille[ idPf=" + idPf + " ]";
    }
    
}

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
import javax.persistence.OneToOne;
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
@Table(name = "action")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Action.findAll", query = "SELECT a FROM Action a"),
    @NamedQuery(name = "Action.findByIdAction", query = "SELECT a FROM Action a WHERE a.idAction = :idAction"),
    @NamedQuery(name = "Action.findByIdEntreprise", query = "SELECT a FROM Action a WHERE a.idEntreprise = :idEntreprise"),
    @NamedQuery(name = "Action.findByValeurAction", query = "SELECT a FROM Action a WHERE a.valeurAction = :valeurAction"),
    @NamedQuery(name = "Action.findByDateAction", query = "SELECT a FROM Action a WHERE a.dateAction = :dateAction"),
    @NamedQuery(name = "Action.findByNbrot", query = "SELECT a FROM Action a WHERE a.nbrot = :nbrot"),
 @NamedQuery(name = "Action.getHaut", query = "SELECT a FROM Action a ORDER BY a.valeurAction DESC"),
 @NamedQuery(name = "Action.getBas", query = "SELECT a FROM Action a  ORDER BY a.valeurAction")
})
public class Action implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idAction")
    private Integer idAction;
      @JoinColumn(name = "idEntreprise", referencedColumnName = "idEntreprise")
    @ManyToOne
    @NotNull
    private Entreprique idEntreprise;
    @Column(name = "valeurAction")
    private Integer valeurAction;
    @Column(name = "dateAction")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateAction;
    @Column(name = "nbrot")
    private Integer nbrot;
     
     @OneToMany(mappedBy = "idAction")
    private Collection<Ordre> ordreCollection;   
  
          @OneToMany(mappedBy = "idAction")
    private Collection<Portefeuille> portefeuilleCollection;
  
              @OneToMany(mappedBy = "idAction")
    private Collection<Historiqueaction> historiqueactionCollection;
          
          
    public Action() {
    }

    public Action(Integer idAction) {
        this.idAction = idAction;
    }

    public Action(Integer idAction, Entreprique idEntreprise) {
        this.idAction = idAction;
        this.idEntreprise = idEntreprise;
    }

    public Entreprique getIdEntreprise() {
        return idEntreprise;
    }

    public void setIdEntreprise(Entreprique identreprise) {
        this.idEntreprise = identreprise;
    }

    public Collection<Portefeuille> getPortefeuilleCollection() {
        return portefeuilleCollection;
    }

    public void setPortefeuilleCollection(Collection<Portefeuille> portefeuilleCollection) {
        this.portefeuilleCollection = portefeuilleCollection;
    }

    public Collection<Historiqueaction> getHistoriqueactionCollection() {
        return historiqueactionCollection;
    }

    public void setHistoriqueactionCollection(Collection<Historiqueaction> historiqueactionCollection) {
        this.historiqueactionCollection = historiqueactionCollection;
    }

    public Integer getIdAction() {
        return idAction;
    }

    public void setIdAction(Integer idAction) {
        this.idAction = idAction;
    }

    public Collection<Ordre> getOrdreCollection() {
        return ordreCollection;
    }

    public void setOrdreCollection(Collection<Ordre> ordreCollection) {
        this.ordreCollection = ordreCollection;
    }

  

    public Integer getValeurAction() {
        return valeurAction;
    }

    public void setValeurAction(Integer valeurAction) {
        this.valeurAction = valeurAction;
    }

    public Date getDateAction() {
        return dateAction;
    }

    public void setDateAction(Date dateAction) {
        this.dateAction = dateAction;
    }

    public Integer getNbrot() {
        return nbrot;
    }

    public void setNbrot(Integer nbrot) {
        this.nbrot = nbrot;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idAction != null ? idAction.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Action)) {
            return false;
        }
        Action other = (Action) object;
        if ((this.idAction == null && other.idAction != null) || (this.idAction != null && !this.idAction.equals(other.idAction))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Action[ idAction=" + idAction + " ]";
    }
    
}

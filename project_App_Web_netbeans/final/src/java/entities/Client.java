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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author imed
 */
@Entity
@Table(name = "client")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Client.findAll", query = "SELECT c FROM Client c"),
    @NamedQuery(name = "Client.findByIdClient", query = "SELECT c FROM Client c WHERE c.idClient = :idClient"),
    @NamedQuery(name = "Client.findByIdTrader", query = "SELECT c FROM Client c WHERE c.idTrader = :idTrader"),
    @NamedQuery(name = "Client.findByNomClient", query = "SELECT c FROM Client c WHERE c.nomClient = :nomClient"),
    @NamedQuery(name = "Client.findByPrenomClient", query = "SELECT c FROM Client c WHERE c.prenomClient = :prenomClient"),
    @NamedQuery(name = "Client.findByAdresseClient", query = "SELECT c FROM Client c WHERE c.adresseClient = :adresseClient"),
    @NamedQuery(name = "Client.findByTelClient", query = "SELECT c FROM Client c WHERE c.telClient = :telClient"),
    @NamedQuery(name = "Client.findByEmailClient", query = "SELECT c FROM Client c WHERE c.emailClient = :emailClient"),
    @NamedQuery(name = "Client.findByProfession", query = "SELECT c FROM Client c WHERE c.profession = :profession"),
    @NamedQuery(name = "Client.findByMotDePasseClient", query = "SELECT c FROM Client c WHERE c.motDePasseClient = :motDePasseClient"),
    @NamedQuery(name = "Client.findByLoginClient", query = "SELECT c FROM Client c WHERE c.loginClient = :loginClient")})
public class Client implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idClient")
    private Integer idClient;

    @JoinColumn(name = "idTrader", referencedColumnName = "idTrader")
    @ManyToOne
    private Trader idTrader;

    @Size(max = 254)
    @Column(name = "nomClient")
    private String nomClient;
    @Size(max = 254)
    @Column(name = "prenomClient")
    private String prenomClient;
    @Size(max = 254)
    @Column(name = "adresseClient")
    private String adresseClient;
    @Column(name = "telClient")
    private Integer telClient;
    @Size(max = 254)
    @Column(name = "emailClient")
    private String emailClient;
    @Size(max = 254)
    @Column(name = "profession")
    private String profession;
    @Size(max = 254)
    @Column(name = "motDePasseClient")
    private String motDePasseClient;
    @Size(max = 254)
    @Column(name = "loginClient")
    private String loginClient;
        @JoinColumn(name = "IDENTREPRISE", referencedColumnName = "IDENTREPRISE")
    @ManyToOne
    private Entreprique identreprise;
        
    @OneToMany(mappedBy = "idClient")
    private Collection<Ordre> ordreCollection;   

        @OneToMany(mappedBy = "idClient")
    private Collection<Portefeuille> portefeuilleCollection;
        
    public Client() {
    }

    public Client(Integer idClient) {
        this.idClient = idClient;
    }

    public Entreprique getIdentreprise() {
        return identreprise;
    }

    public void setIdentreprise(Entreprique identreprise) {
        this.identreprise = identreprise;
    }

    public Collection<Portefeuille> getPortefeuilleCollection() {
        return portefeuilleCollection;
    }

    public void setPortefeuilleCollection(Collection<Portefeuille> portefeuilleCollection) {
        this.portefeuilleCollection = portefeuilleCollection;
    }

    public Collection<Ordre> getOrdreCollection() {
        return ordreCollection;
    }

    public void setOrdreCollection(Collection<Ordre> ordreCollection) {
        this.ordreCollection = ordreCollection;
    }
 
  
    public Integer getIdClient() {
        return idClient;
    }

    public void setIdClient(Integer idClient) {
        this.idClient = idClient;
    }

    public Trader getIdTrader() {
        return idTrader;
    }

    public void setIdTrader(Trader idTrader) {
        this.idTrader = idTrader;
    }

    public String getNomClient() {
        return nomClient;
    }

    public void setNomClient(String nomClient) {
        this.nomClient = nomClient;
    }

    public String getPrenomClient() {
        return prenomClient;
    }

    public void setPrenomClient(String prenomClient) {
        this.prenomClient = prenomClient;
    }

    public String getAdresseClient() {
        return adresseClient;
    }

    public void setAdresseClient(String adresseClient) {
        this.adresseClient = adresseClient;
    }

    public Integer getTelClient() {
        return telClient;
    }

    public void setTelClient(Integer telClient) {
        this.telClient = telClient;
    }

    public String getEmailClient() {
        return emailClient;
    }

    public void setEmailClient(String emailClient) {
        this.emailClient = emailClient;
    }

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    public String getMotDePasseClient() {
        return motDePasseClient;
    }

    public void setMotDePasseClient(String motDePasseClient) {
        this.motDePasseClient = motDePasseClient;
    }

    public String getLoginClient() {
        return loginClient;
    }

    public void setLoginClient(String loginClient) {
        this.loginClient = loginClient;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idClient != null ? idClient.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Client)) {
            return false;
        }
        Client other = (Client) object;
        if ((this.idClient == null && other.idClient != null) || (this.idClient != null && !this.idClient.equals(other.idClient))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Client[ idClient=" + idClient + " ]";
    }
    
}

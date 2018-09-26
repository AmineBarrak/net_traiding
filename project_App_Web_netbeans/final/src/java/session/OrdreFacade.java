/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import entities.Client;
import entities.Ordre;
import entities.Trader;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;
/**
 *
 * @author imed
 */
@Stateless
public class OrdreFacade extends AbstractFacade<Ordre> {
    @PersistenceContext(unitName = "finalPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public OrdreFacade() {
        super(Ordre.class);
    }
    
    public List<Ordre> allOrdresAchat(){
            Query query=em.createNamedQuery("Ordre.findByTypeOrdre", Ordre.class);
       query.setParameter("typeOrdre", "achat");
  return (List<Ordre>) query.getResultList();
    }
    
     public List<Ordre> allOrdresVente(){
            Query query=em.createNamedQuery("Ordre.findByTypeOrdre", Ordre.class);
       query.setParameter("typeOrdre", "vente");
  return (List<Ordre>) query.getResultList();
    }

    public List<Ordre> allOrdresClient(Client c) {
    Query query=em.createNamedQuery("Ordre.findByIdClient", Ordre.class);
       query.setParameter("idClient", c);
  return (List<Ordre>) query.getResultList();
    
    }
    
    
}

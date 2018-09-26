/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import entities.Action;
import entities.Administrateur;
import entities.Trader;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author imed
 */
@Stateless
public class StatFacade{
    @PersistenceContext(unitName = "finalPU")
    private EntityManager em;

     public StatFacade() {
        super();
    }
    
   
    protected EntityManager getEntityManager() {
return em;
    }
    
       public Administrateur findByLogin(String loginAdmin) {

        Query query=em.createNamedQuery("Administrateur.findByLoginAdmin", Administrateur.class);
       query.setParameter("loginAdmin", loginAdmin);
  return (Administrateur) query.getSingleResult();
    
    }
       
       public List<Action> getHauteActions(){
           Query query=em.createNamedQuery("Action.getHaut", Action.class);
         
             query.setMaxResults(5);
  return (List<Action>) query.getResultList();
       }
       
              public List<Action> getBasseActions(){
           Query query=em.createNamedQuery("Action.getBas", Action.class);
             query.setMaxResults(5);
  return (List<Action>) query.getResultList();
       }

    
}

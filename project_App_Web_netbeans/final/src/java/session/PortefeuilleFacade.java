/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import entities.Action;
import entities.Client;
import entities.Portefeuille;
import entities.Trader;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author imed
 */
@Stateless
public class PortefeuilleFacade extends AbstractFacade<Portefeuille> {
    @PersistenceContext(unitName = "finalPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PortefeuilleFacade() {
        super(Portefeuille.class);
    }
    
    public Portefeuille findByActionAndClient(Action a,Client c){
          Query query=em.createNamedQuery("Portefeuille.findByClientAction", Portefeuille.class);
       query.setParameter("idAction", a);
        query.setParameter("idClient", c);
  return (Portefeuille) query.getSingleResult();
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

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
public class TraderFacade extends AbstractFacade<Trader> {
    @PersistenceContext(unitName = "finalPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TraderFacade() {
        super(Trader.class);
    }

    public Trader findByLogin(String login){
       Query query=em.createNamedQuery("Trader.findByLoginTrader", Trader.class);
       query.setParameter("loginTrader", login);
  return (Trader) query.getSingleResult();
   }
    
}

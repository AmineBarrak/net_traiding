/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import entities.Administrateur;
import entities.Client;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author imed
 */
@Stateless
public class AdministrateurFacade extends AbstractFacade<Administrateur> {
    @PersistenceContext(unitName = "finalPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public AdministrateurFacade() {
        super(Administrateur.class);
    }

    public Administrateur findByLogin(String loginAdmin) {

        Query query=em.createNamedQuery("Administrateur.findByLoginAdmin", Administrateur.class);
       query.setParameter("loginAdmin", loginAdmin);
  return (Administrateur) query.getSingleResult();
    
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import entities.Historiqueportfeuille;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author imed
 */
@Stateless
public class HistoriqueportfeuilleFacade extends AbstractFacade<Historiqueportfeuille> {
    @PersistenceContext(unitName = "finalPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public HistoriqueportfeuilleFacade() {
        super(Historiqueportfeuille.class);
    }
    
}

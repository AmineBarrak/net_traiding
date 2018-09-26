/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package webServices;

import entities.Action;
import entities.Administrateur;
import java.util.List;
import javax.ejb.EJB;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import session.StatFacade;

/**
 *
 * @author imed
 */
@WebService(serviceName = "NewWebService")
public class NewWebService {
    @EJB
    private StatFacade ejbRef;// Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Web Service Operation")

    @WebMethod(operationName = "findByLogin")
    public Administrateur findByLogin(@WebParam(name = "loginAdmin") String loginAdmin) {
        return ejbRef.findByLogin(loginAdmin);
    }

    @WebMethod(operationName = "getHauteActions")
    public List<Action> getHauteActions() {
        return ejbRef.getHauteActions();
    }

    @WebMethod(operationName = "getBasseActions")
    public List<Action> getBasseActions() {
        return ejbRef.getBasseActions();
    }
    
}

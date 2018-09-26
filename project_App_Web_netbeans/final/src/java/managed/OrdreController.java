package managed;

import entities.Action;
import entities.Client;
import entities.Ordre;
import entities.Portefeuille;
import managed.util.JsfUtil;
import managed.util.JsfUtil.PersistAction;
import session.OrdreFacade;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import session.ActionFacade;
import session.PortefeuilleFacade;

@ManagedBean(name = "ordreController")
@RequestScoped
public class OrdreController implements Serializable {

    @EJB
    private session.OrdreFacade ejbFacade;
    
      @EJB
    private session.ActionFacade ejbFacadeAction;
      
      @EJB
       private session.PortefeuilleFacade ejbFacadePortefeuille;
      
      
      private Portefeuille portefeuille;
    
    private List<Ordre> items = null;
    private Ordre selected;

    public OrdreController() {
       selected = new Ordre();
       portefeuille =new Portefeuille();
    }

    public Ordre getSelected() {
        return selected;
    }

    public void setSelected(Ordre selected) {
        this.selected = selected;
    }

    public Portefeuille getPortefeuille() {
        return portefeuille;
    }

    public void setPortefeuille(Portefeuille nbrMaxVente) {
        this.portefeuille = nbrMaxVente;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private OrdreFacade getFacade() {
        return ejbFacade;
    }

    public Ordre prepareCreate() {
        selected = new Ordre();
        initializeEmbeddableKey();
        return selected;
    }

    public ActionFacade getEjbFacadeAction() {
        return ejbFacadeAction;
    }

    public void setEjbFacadeAction(ActionFacade ejbFacadeAction) {
        this.ejbFacadeAction = ejbFacadeAction;
    }

    public PortefeuilleFacade getEjbFacadePortefeuille() {
        return ejbFacadePortefeuille;
    }

    public void setEjbFacadePortefeuille(PortefeuilleFacade ejbFacadePortefeuille) {
        this.ejbFacadePortefeuille = ejbFacadePortefeuille;
    }
    
    
    public List<Ordre> getOrdresAchats(){
        return ejbFacade.allOrdresAchat();
    }
    
     public List<Ordre> getOrdresVente(){
        return ejbFacade.allOrdresVente();
    }
     
         public void createVente() {
         Date d = new Date();
       selected.setDateOrdre(d);
       Client sessionClient=(Client)((HttpSession)(FacesContext.getCurrentInstance().getExternalContext().getSession(true))).getAttribute("sessionClient");
       selected.setEtatOrdre("en attente");
       selected.setIdClient(sessionClient);
       selected.setTypeOrdre("vente");
       selected.setIdAction(this.portefeuille.getIdAction());
        
        
           if(selected.getNbAction()<=0){
            JsfUtil.addErrorMessage("tapez un nombre supérieur a zero");
       }
       
       else if(this.portefeuille.getNbrAction()<selected.getNbAction() ){
            JsfUtil.addErrorMessage("vous avez depasse le nombre maximal d'actions");
      }
      else {
          
              persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("OrdreCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
       
          
      }
    }


    public void createAchat() {
         Date d = new Date();
       selected.setDateOrdre(d);
       Client sessionClient=(Client)((HttpSession)(FacesContext.getCurrentInstance().getExternalContext().getSession(true))).getAttribute("sessionClient");
       selected.setEtatOrdre("en attente");
       selected.setIdClient(sessionClient);
       selected.setTypeOrdre("achat");
        
     
       
       if(selected.getNbAction()<=0){
            JsfUtil.addErrorMessage("tapez un nombre supérieur a zero");
       }
       
       else if(selected.getIdAction().getNbrot()<selected.getNbAction() ){
            JsfUtil.addErrorMessage("vous avez depasse le nombre maximal d'actions");
      }
      else {
          
              persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("OrdreCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
       
          
      }
       
       
       
    }

    public void update() {
        

        
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("OrdreUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("OrdreDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<Ordre> getItems() {
        if (items == null) {
            items = getFacade().findAll();
        }
        return items;
    }

    private void persist(PersistAction persistAction, String successMessage) {
        if (selected != null) {
            setEmbeddableKeys();
            try {
                if (persistAction != PersistAction.DELETE) {
                    getFacade().edit(selected);
                } else {
                    getFacade().remove(selected);
                }
                JsfUtil.addSuccessMessage(successMessage);
            } catch (EJBException ex) {
                String msg = "";
                Throwable cause = ex.getCause();
                if (cause != null) {
                    msg = cause.getLocalizedMessage();
                }
                if (msg.length() > 0) {
                    JsfUtil.addErrorMessage(msg);
                } else {
                    JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
                }
            } catch (Exception ex) {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
                JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            }
        }
    }

    public List<Ordre> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<Ordre> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = Ordre.class)
    public static class OrdreControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            OrdreController controller = (OrdreController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "ordreController");
            return controller.getFacade().find(getKey(value));
        }

        java.lang.Integer getKey(String value) {
            java.lang.Integer key;
            key = Integer.valueOf(value);
            return key;
        }

        String getStringKey(java.lang.Integer value) {
            StringBuilder sb = new StringBuilder();
            sb.append(value);
            return sb.toString();
        }

        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof Ordre) {
                Ordre o = (Ordre) object;
                return getStringKey(o.getIdOrdre());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Ordre.class.getName()});
                return null;
            }
        }

    }
    
    public void ordreClient(Client c){
         try{
        FacesContext context = FacesContext.getCurrentInstance(); 
          HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest(); 
       FacesContext.getCurrentInstance().getExternalContext().redirect(request.getContextPath()+"/espace_trader/ordreClient.xhtml");                  
       
         }catch(Exception e){ }
         
         
         HttpSession session=   (HttpSession)(FacesContext.getCurrentInstance().getExternalContext().getSession(true));  
                 
                session.setAttribute( "sessionOrdres", ejbFacade.allOrdresClient(c));
       
      
    }
     public void executerOrdreVente(Ordre ordre){
             

        ordre.setEtatOrdre("execute");
        ordre.setDateExecution(d);
        ejbFacade.edit(ordre);
     }
    
    public void executerOrdreAchat(Ordre ordre){
           Date d = new Date(); 
         
        

              int nouveau=ordre.getIdAction().getNbrot()-ordre.getNbAction();
        ordre.getIdAction().setNbrot(nouveau);
        ejbFacadeAction.edit(ordre.getIdAction());
            
            
        Portefeuille pf=new Portefeuille();
       pf.setIdClient(ordre.getIdClient());
       pf.setIdAction(ordre.getIdAction());
    
       pf.setDateOuverture(d);
       pf.setNbrAction(ordre.getNbAction());
       int vTotal=ordre.getNbAction()*ordre.getIdAction().getValeurAction();
       pf.setValeurTotal(vTotal);
       ejbFacadePortefeuille.create(pf);
        
     
        ordre.setEtatOrdre("execute");
        ordre.setDateExecution(d);
        ejbFacade.edit(ordre);
    }
    
    

    
    
    
   

}

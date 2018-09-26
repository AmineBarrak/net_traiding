package managed;

import entities.Administrateur;
import managed.util.JsfUtil;
import managed.util.JsfUtil.PersistAction;
import session.AdministrateurFacade;

import java.io.Serializable;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@ManagedBean(name = "administrateurController")
@SessionScoped
public class AdministrateurController implements Serializable {

    @EJB
    private session.AdministrateurFacade ejbFacade;
    private List<Administrateur> items = null;
    private Administrateur selected;

    public AdministrateurController() {
        selected=new Administrateur();
    }

    public Administrateur getSelected() {
        return selected;
    }

    public void setSelected(Administrateur selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private AdministrateurFacade getFacade() {
        return ejbFacade;
    }
    
 

    public Administrateur prepareCreate() {
        selected = new Administrateur();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("AdministrateurCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("AdministrateurUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("AdministrateurDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<Administrateur> getItems() {
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

    public List<Administrateur> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<Administrateur> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = Administrateur.class)
    public static class AdministrateurControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            AdministrateurController controller = (AdministrateurController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "administrateurController");
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
            if (object instanceof Administrateur) {
                Administrateur o = (Administrateur) object;
                return getStringKey(o.getIdAdmin());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Administrateur.class.getName()});
                return null;
            }
        }

    }
    
    
    
        
      public void login(){
                    FacesContext context = FacesContext.getCurrentInstance();  
        HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest(); 
	try{
                    Administrateur adminBDD= this.ejbFacade.findByLogin( selected.getLoginAdmin() );
  
                if(selected.getPasseAdmin().equals(adminBDD.getPasseAdmin())) {
              
                    
                  
                HttpSession session=   (HttpSession)(FacesContext.getCurrentInstance().getExternalContext().getSession(true));  
                  adminBDD.setPasseAdmin(null);
                session.setAttribute( "sessionAdmin", adminBDD);
                                 FacesContext.getCurrentInstance().getExternalContext().redirect(request.getContextPath()+"/admin/index.xhtml");                  

                }
                
                else {
                    JsfUtil.addErrorMessage("Le mot de passe est incorrecte.");
                }}

                  catch(Exception e) {

                    JsfUtil.addErrorMessage("Ce login n'existe pas.");
        }
    
}
      
           public void logout(){
         try{
                             FacesContext context = FacesContext.getCurrentInstance();  
        HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest(); 
                HttpSession session=   (HttpSession)(FacesContext.getCurrentInstance().getExternalContext().getSession(true));  
                                if(session != null){
            session.invalidate();
        }
                  FacesContext.getCurrentInstance().getExternalContext().redirect(request.getContextPath()+"/login.xhtml");                  

         }catch(Exception e){ }
         }
    
    
    

}

package managed;

import entities.Action;
import managed.util.JsfUtil;
import managed.util.JsfUtil.PersistAction;
import session.ActionFacade;

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
import session.StatFacade;


@ManagedBean(name = "actionController")
@SessionScoped
public class ActionController implements Serializable {

    @EJB
    private session.ActionFacade ejbFacade;
    
        @EJB
    private session.StatFacade statFacade;
    private List<Action> items = null;
    private Action selected;

    public ActionController() {
    }

    public Action getSelected() {
        return selected;
    }

    public void setSelected(Action selected) {
        this.selected = selected;
    }

    public StatFacade getStatFacade() {
        return statFacade;
    }

    public void setStatFacade(StatFacade statFacade) {
        this.statFacade = statFacade;
    }



    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private ActionFacade getFacade() {
        return ejbFacade;
    }

    public Action prepareCreate() {
        selected = new Action();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("ActionCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("ActionUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("ActionDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<Action> getItems() {
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

    public List<Action> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<Action> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }
    
    
        public List<Action> getHauteActions() {
        return statFacade.getHauteActions();
    }
    
                public List<Action> getBasseActions() {
        return statFacade.getBasseActions();
    }
    

    @FacesConverter(forClass = Action.class)
    public static class ActionControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            ActionController controller = (ActionController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "actionController");
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
            if (object instanceof Action) {
                Action o = (Action) object;
                return getStringKey(o.getIdAction());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Action.class.getName()});
                return null;
            }
        }

    }

}

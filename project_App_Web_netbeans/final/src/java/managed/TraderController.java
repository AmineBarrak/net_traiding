package managed;

import entities.Administrateur;
import entities.Historiquegrade;
import entities.Trader;
import managed.util.JsfUtil;
import managed.util.JsfUtil.PersistAction;
import session.TraderFacade;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
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
import session.HistoriquegradeFacade;

@ManagedBean(name = "traderController")
@RequestScoped
public class TraderController implements Serializable {

    @EJB
    private session.TraderFacade ejbFacade;
    @EJB
    private session.HistoriquegradeFacade histogradeFacade;
    private List<Trader> items = null;
    private Trader selected;
    private ArrayList<String> grades;

    public TraderController() {
        selected = new Trader();
        grades = new ArrayList<String>(
                Arrays.asList("junior", "senior", "comfirmé"));

    }

    public Trader getSelected() {
        return selected;
    }

    public HistoriquegradeFacade getHistogradeFacade() {
        return histogradeFacade;
    }

    public void setHistogradeFacade(HistoriquegradeFacade histogradeFacade) {
        this.histogradeFacade = histogradeFacade;
    }

    public void setSelected(Trader selected) {

        if (selected != null) {
            if (selected.getGrade().equals("junior")) {
                grades = new ArrayList<String>(
                        Arrays.asList("junior", "senior", "comfirmé"));
            } else if (selected.getGrade().equals("senior")) {
                grades = new ArrayList<String>(
                        Arrays.asList("senior", "comfirmé"));
            } else if (selected.getGrade().equals("comfirmé")) {
                grades = new ArrayList<String>(
                        Arrays.asList("comfirmé"));
            }
        }
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private TraderFacade getFacade() {
        return ejbFacade;
    }

    public Trader prepareCreate() {
        selected = new Trader();
        grades = new ArrayList<String>(
                Arrays.asList("junior", "senior", "comfirmé"));
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
       Historiquegrade g=new Historiquegrade();    
        Date d = new Date();
        g.setDateAffectaion(d);
        g.setGrade(selected.getGrade());
        g.setIdTrader(selected);
        histogradeFacade.create(g);

        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
      
      
            Historiquegrade g = new Historiquegrade();
            Date d = new Date();
            g.setDateAffectaion(d);
            g.setGrade(selected.getGrade());
            g.setIdTrader(selected);
     
        
          persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("TraderUpdated"));


    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("TraderDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<Trader> getItems() {
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

    public List<Trader> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<Trader> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = Trader.class)
    public static class TraderControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            TraderController controller = (TraderController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "traderController");
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
            if (object instanceof Trader) {
                Trader o = (Trader) object;
                return getStringKey(o.getIdTrader());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Trader.class.getName()});
                return null;
            }
        }

    }

    public void login() {
        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
        try {
            Trader traderBDD = this.ejbFacade.findByLogin(selected.getLoginTrader());

            if (selected.getMotDePasseTrader().equals(traderBDD.getMotDePasseTrader())) {

                HttpSession session = (HttpSession) (FacesContext.getCurrentInstance().getExternalContext().getSession(true));
                traderBDD.setMotDePasseTrader(null);
                session.setAttribute("sessionTrader", traderBDD);
                FacesContext.getCurrentInstance().getExternalContext().redirect(request.getContextPath() + "/espace_trader/index.xhtml");

            } else {
                JsfUtil.addErrorMessage("Le mot de passe est incorrecte.");
            }
        } catch (Exception e) {

            JsfUtil.addErrorMessage("Ce login n'existe pas.");
        }

    }

    public void logout() {
        try {
            FacesContext context = FacesContext.getCurrentInstance();
            HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
            HttpSession session = (HttpSession) (FacesContext.getCurrentInstance().getExternalContext().getSession(true));
            if (session != null) {
                session.invalidate();
            }
            FacesContext.getCurrentInstance().getExternalContext().redirect(request.getContextPath() + "/loginTrader.xhtml");

        } catch (Exception e) {
        }
    }

    public ArrayList<String> getGrades() {

        return grades;
    }

}

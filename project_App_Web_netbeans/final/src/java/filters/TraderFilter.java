/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package filters;


import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
/**
 *
 * @author imed
 */
@WebFilter(urlPatterns = "/espace_trader/*")
public class TraderFilter  implements Filter {

	public static final String ACCES_PUBLIC ="/loginTrader.xhtml";
    /**
     * Default constructor. 
     */
    public TraderFilter() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
    @Override
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
    @Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
		/* Cast des objets request et response */
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) res;
		/* R�cup�ration de la session depuis la requ�te */
		HttpSession session = request.getSession();
		/**
		* Si l'objet utilisateur n'existe pas dans la session en cours,
		alors
		* l'utilisateur n'est pas connect�.
		*/
		if ( session.getAttribute("sessionTrader") == null ) {
		/* Redirection vers la page publique */
		response.sendRedirect( request.getContextPath() +ACCES_PUBLIC );
		} else {
		/* Affichage de la page restreinte */
		chain.doFilter( request, response );
		}
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
    @Override
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

    
}

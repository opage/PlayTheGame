package ecom.jonas.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.ejb.EJB;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;


import ecom.jonas.session.InitRemote;


public class InitServlet extends HttpServlet {

	private static final long serialVersionUID = -8441732986036293455L;
	
	@EJB
	protected InitRemote initRemote;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
	{
		response.setContentType("text/html");
	    PrintWriter pw;
	    
		Context initialContext = null;
		System.setProperty(Context.INITIAL_CONTEXT_FACTORY,
				"org.objectweb.carol.jndi.spi.MultiOrbInitialContextFactory");
		try {
			initialContext = new InitialContext();
		} catch (Exception e) {
			System.err.println("Cannot get initial context : " + e);
			System.exit(2);
		}	    
	    
		try {
			pw = response.getWriter();
			initRemote = (InitRemote) initialContext.lookup("InitBean");
			initRemote.init();
		    pw.print("<H2>Base de données initialisée</H2>");		    
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

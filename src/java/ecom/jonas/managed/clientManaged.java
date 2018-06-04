package ecom.jonas.managed;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.faces.component.html.HtmlDataTable;
import javax.faces.context.FacesContext;

import javax.faces.event.ValueChangeEvent;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import com.icesoft.faces.component.ext.HtmlInputText;
import com.icesoft.faces.component.ext.HtmlOutputText;

import ecom.jonas.entity.Game;

import ecom.jonas.entity.Console;
import ecom.jonas.entity.Customer;
import ecom.jonas.entity.Platform;
import ecom.jonas.entity.Type;
import ecom.jonas.session.AdminRemote;

/**
 * 
 * @author Nabil
 */

public class clientManaged {
	@EJB
	private AdminRemote admin;

	private Customer selectedCustomer = new Customer();

	private HtmlDataTable dataTable;
	private List<Customer> customers;
	private String nameCustomer = "";
	private String crediter = "0";
	private String debiter = "0";
private long idCustomer=-1;
	private HtmlInputText inputDebiter;
	private HtmlInputText inputCrediter;

	public HtmlInputText getInputDebiter() {
		return inputDebiter;
	}

	public void setInputDebiter(HtmlInputText inputDebiter) {
		this.inputDebiter = inputDebiter;
	}

	public HtmlInputText getInputCrediter() {
		return inputCrediter;
	}

	public void setInputCrediter(HtmlInputText inputCrediter) {
		this.inputCrediter = inputCrediter;
	}

	public clientManaged() {
		Connecction();
	}

	/**
	 * @param dataTable
	 *            the dataTable to set
	 */
	public void setDataTable(HtmlDataTable dataTable) {
		this.dataTable = dataTable;
	}

	/**
	 * @return the dataTable
	 */
	public HtmlDataTable getDataTable() {
		return dataTable;
	}

	private void Connecction() {

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

			admin = (AdminRemote) initialContext.lookup("AdminBean");
			System.out.println("I can get AdminBean ");
		} catch (NamingException e) {
			// TODO Auto-generated catch blockCannot
			System.out.println("cannot get AdminBean");

		}

	}

	 public String removeCustomer() {
		
	 admin.removeCustomer(idCustomer);
	 idCustomer=-1;
	 visibleDel = false;
	 return null;
	 }
		

	/**
	 * @param selectedCustomer
	 *            the selectedCustomer to set
	 */
	public void setSelectedCustomer(Customer selectedCustomer) {
		this.selectedCustomer = selectedCustomer;
	}

	/**
	 * @return the selectedCustomer
	 */
	public Customer getSelectedCustomer() {
		return selectedCustomer;
	}

	/**
	 * @param nameCustomer
	 *            the nameCustomer to set
	 */
	public void setNameCustomer(String nameCustomer) {
		this.nameCustomer = nameCustomer;
	}

	/**
	 * @return the nameCustomer
	 */
	public String getNameCustomer() {
		return nameCustomer;
	}

	public String doCrediter() {
      
		try {
			long idAccount = Long.parseLong(FacesContext.getCurrentInstance()
					.getExternalContext().getRequestParameterMap().get(
							"idAccount"));
			double balanceAccount = Double.parseDouble(inputCrediter.getValue()
					.toString());
			admin.Crediter(idAccount, balanceAccount);
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		visibleCre = false;
		return " ";
	}

	public String doDebiter() {
		 
			try {
				long idAccount = Long.parseLong(FacesContext.getCurrentInstance()
						.getExternalContext().getRequestParameterMap().get(
								"idAccount"));
				double balanceAccount = Double.parseDouble(inputDebiter.getValue()
						.toString());
				admin.Debiter(idAccount, balanceAccount);
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			visibleDeb = false;
			return " ";
	}

	/**
	 * @param customers
	 *            the customers to set
	 */
	public void setCustomers(List<Customer> customers) {
		this.customers = customers;
	}

	/**
	 * @return the customers
	 */
	public List<Customer> getCustomers() {
		return (List<Customer>) admin.getCustomersByName(nameCustomer);
	}

	private boolean visibleDel = false;

	public boolean isVisibleDel() {
		return visibleDel;
	}

	public void setVisibleDel(boolean visible) {
		this.visibleDel = visible;
	}

	public String closePopupDel() {
		idCustomer=-1;
		visibleDel = false;
		return null;
	}

	public String openPopupDel() {
		idCustomer=Long.parseLong(FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("idCustomer"));
	
		visibleDel = true;
		return null;
	}

	private boolean visibleCre = false;

	public boolean isVisibleCre() {
		return visibleCre;
	}

	public String closeVisibleCre() {
		
		visibleCre = false;
		return null;
	}

	public String openVisibleCre() {
		
		visibleCre = true;
		return null;
	}

	private boolean visibleDeb = false;

	public boolean isVisibleDeb() {
		return visibleDeb;
	}

	public String closeVisibleDeb() {
		// idProduct=-1;
		visibleDeb = false;
		return null;
	}

	public String openVisibleDeb() {
		// idProduct=Long.parseLong(FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("idProduct"));
		// System.out.println("I can get idProduct ="+idProduct);
		visibleDeb = true;
		return null;
	}

	public void setCrediter(String crediter) {
		this.crediter = crediter;
	}

	/**
	 * @return the crediter
	 */
	public String getCrediter() {
		return crediter;
	}

	/**
	 * @param debiter
	 *            the debiter to set
	 */
	public void setDebiter(String debiter) {
		this.debiter = debiter;
	}

	/**
	 * @return the debiter
	 */
	public String getDebiter() {
		return debiter;
	}
}
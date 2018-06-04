package ecom.jonas.session;

import static ecom.jonas.entity.Customer.QN.FIND_CUSTOMERBYMAIL;

import java.util.Date;

import javax.ejb.Remote;
import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import ecom.jonas.entity.Account;
import ecom.jonas.entity.Customer;
import ecom.jonas.entity.OrderCustomer;
import ecom.jonas.entity.Product;

/**
 * Session Bean implementation class CustomerBean
 */
@Stateful(mappedName = "CustomerBean")
@Remote(CustomerRemote.class)
public class CustomerBean implements CustomerRemote {

	@PersistenceContext
	private EntityManager em;

	private Customer customer = null;
	
	private OrderCustomer ordercustomer = new OrderCustomer(customer);

	// Login of Customer
	public boolean loginCustomer(String loginCustomer, String pwdCustomer) {
		Query customerQuery = em.createNamedQuery(FIND_CUSTOMERBYMAIL).setParameter("MAIL", loginCustomer);
		Customer c = (customerQuery.getResultList().size() != 0 ? (Customer) customerQuery
				.getResultList().get(0) : null);
		if ( (c != null) && (c.getPasswordCustomer() != null) && (c.getPasswordCustomer().compareTo(pwdCustomer) == 0) ) {
			this.customer = c;
			return true;
		} else {
			return false;
		}
	}

	// Logout of Customer
	public void logoutCustomer() {
		this.customer = null;
	}

	// Verification if the customer is log on
	public boolean isConnected() {
		return (this.customer != null);
	}
	
	//Return the account of customer
	public Account getAccount(){
		return this.customer.getAccount();
	}

	public boolean EditDetails(String firstnameCustomer,
			String lastnameCustomer, String emailCustomer,
			Date birthdayCustomer, String streetHomeCustomer,
			Integer postalCodeHomeCustomer, String cityHomeCustomer,
			String countryHomeCustomer, String streetDeliveryCustomer,
			Integer postalCodeDeliveryCustomer, String cityDeliveryCustomer,
			String countryDeliveryCustomer) {
		
		return (this.isConnected()
			&& this.EditDetailsPersonalInfos(firstnameCustomer, lastnameCustomer, emailCustomer, birthdayCustomer)
			&& this.EditDetailsHomeAddress(streetHomeCustomer, postalCodeHomeCustomer, cityHomeCustomer, countryHomeCustomer)
			&& this.EditDetailsDelivreryAddress(streetDeliveryCustomer, postalCodeDeliveryCustomer, cityDeliveryCustomer, countryDeliveryCustomer));
	}

	public boolean EditDetailsPersonalInfos(String firstnameCustomer,
			String lastnameCustomer, String emailCustomer, Date birthdayCustomer) {
		boolean ok = false;
		if (this.isConnected()) {
			// Personal Informations of Customer
			this.customer.setFirstnameCustomer(firstnameCustomer);
			this.customer.setLastnameCustomer(lastnameCustomer);
			this.customer.setEmailCustomer(emailCustomer);
			this.customer.setBirthdayCustomer(birthdayCustomer);
			ok = true;
		}
		return ok;
	}
	
	public boolean EditDetailsHomeAddress(
			String streetHomeCustomer, Integer postalCodeHomeCustomer, String cityHomeCustomer, String countryHomeCustomer) {
		boolean ok = false;
		if (this.isConnected()) {
			// Home address
			//this.customer.setStreetHomeCustomer(streetHomeCustomer);
			//this.customer.setPostalCodeHomeCustomer(postalCodeHomeCustomer);
		//	this.customer.setCityHomeCustomer(cityHomeCustomer);
		//	this.customer.setCountryHomeCustomer(countryHomeCustomer);
			ok = true;
		}
		return ok;
	}
	
	//Edition of informations of customer
	public boolean EditDetailsDelivreryAddress(
			String streetDeliveryCustomer, Integer postalCodeDeliveryCustomer, String cityDeliveryCustomer, String countryDeliveryCustomer){
		boolean ok = false;
		if (this.isConnected()) {
			// Delivery address
			//this.customer.setStreetDeliveryCustomer(streetDeliveryCustomer);
			//this.customer.setPostalCodeDeliveryCustomer(postalCodeDeliveryCustomer);
		//	this.customer.setCityDeliveryCustomer(cityDeliveryCustomer);
		//	this.customer.setCountryDeliveryCustomer(countryDeliveryCustomer);
			ok = true;
		}
		return ok;
	}
	
	//Get informations of current Customer
	public String GetInfos() {
		String mes_infos_customer = "\n Personal information : \n"
		+ "Login :" + this.customer.getLoginCustomer() + "\n"
		+ "Lastname customer :" + this.customer.getLastnameCustomer() + "\n"
		+ "Firstname customer :" + this.customer.getFirstnameCustomer() + "\n"
		+ "Birthday customer :" + this.customer.getBirthdayCustomer() + "\n";
		
//		mes_infos_customer += "\n Home address : "
//		+ this.customer.getStreetHomeCustomer() + ", " + this.customer.getPostalCodeHomeCustomer() + ", " + this.customer.getCityHomeCustomer()+ ", "+ this.customer.getCountryHomeCustomer()+ "\n"; 
//		
//		mes_infos_customer += "\n Delivery address : "
//		+ this.customer.getStreetDeliveryCustomer() + ", " + this.customer.getPostalCodeDeliveryCustomer() + ", " + this.customer.getCityHomeCustomer()+ ", " + this.customer.getCountryDeliveryCustomer()+"\n"; 

		return mes_infos_customer;
	}


	@Override
	public void addCustomer(Customer client) {
		this.customer = client;
		em.persist(client);
	}

	@Override
	public Customer getCustomer() {
		
		if (customer == null)
			return null;
		
		String queryString = "select c from Customer c where c.idCustomer = " + this.customer.getIdCustomer();		
		Query query = em.createQuery(queryString);
		return  (Customer) query.getSingleResult();
	}
}

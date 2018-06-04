package ecom.jonas.session;
import javax.ejb.Remote;

import ecom.jonas.entity.Customer;

@Remote
public interface CustomerRemote {


	public void addCustomer(Customer client);
	public boolean loginCustomer(String loginCustomer, String pwdCustomer);
	public void logoutCustomer();
	public Customer getCustomer();
	public boolean isConnected();
	

}

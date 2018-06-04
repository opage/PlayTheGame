package ecom.jonas.session;
import java.util.ArrayList;

import javax.ejb.Remote;

import ecom.jonas.entity.Address;
import ecom.jonas.entity.Customer;
import ecom.jonas.entity.Product;
import ecom.jonas.entity.ProductOrderList;

@Remote
public interface CartRemote {
	
	public ArrayList<ProductOrderList> getItems();
	public void addProduct(Product p, int quantity);
	public void removeProduct(Product p); 
	public void modifyProductQuantity(Product p, int quantity);
	public boolean validate();
	public void cleanAll();
	public void purchase(Customer customer, Address ad);
	public double priceTotal();
}

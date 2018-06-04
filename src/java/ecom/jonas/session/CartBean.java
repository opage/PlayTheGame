package ecom.jonas.session;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;

import javax.ejb.Remote;
import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import ecom.jonas.entity.Address;
import ecom.jonas.entity.Customer;
import ecom.jonas.entity.OrderCustomer;
import ecom.jonas.entity.Product;
import ecom.jonas.entity.ProductOrderList;

/**
 * Session Bean implementation class CartBean
 */
@Stateful(mappedName = "CartBean")
@Remote(CartRemote.class)
public class CartBean implements CartRemote {
	
	@PersistenceContext
	private EntityManager em;
	
	Collection<ProductOrderList> productOrderList = new ArrayList<ProductOrderList>();

    /**
     * Default constructor. 
     */
    public CartBean() {
        // TODO Auto-generated constructor stub
    }

	@Override
	public void addProduct(Product p, int quantity) {
		ProductOrderList productOrder = new ProductOrderList();
		productOrder.setProduct(p);
		productOrder.setQuantity(quantity);
		productOrderList.add(productOrder);
	}

	@Override
	public void removeProduct(Product p) {
		Iterator<ProductOrderList> it = productOrderList.iterator();		
		while (it.hasNext())
		{
			ProductOrderList productOrder = (ProductOrderList) it.next();
			if (productOrder.getProduct().equals(p))
			{
				productOrderList.remove(productOrder);
				break;
			}
		}		
	}

	@Override
	public void modifyProductQuantity(Product p, int quantity) {
		Iterator<ProductOrderList> it = productOrderList.iterator();		
		while (it.hasNext())
		{
			ProductOrderList productOrder = (ProductOrderList) it.next();
			if (productOrder.getProduct().equals(p))
			{
				productOrder.setQuantity(quantity);
				break;
			}
		}
		
	}

	@Override
	public boolean validate() {

		Iterator<ProductOrderList> it = productOrderList.iterator();
		
		while (it.hasNext())
		{
			ProductOrderList productOrder = it.next();
			// Si depasse le stock, on retourne false
			
			Product prod = productOrder.getProduct();
			
			String queryString = "select p from Product p where p.idProduct = " + prod.getIdProduct();		
			Query query = em.createQuery(queryString);
			prod = (Product) query.getSingleResult();
			
			if ( productOrder.getQuantity() > prod.getQuantityProduct() )
			{
				return false;
			}
		}
		return true;
	}

	@Override
	public ArrayList<ProductOrderList> getItems() {
		return (ArrayList<ProductOrderList>) productOrderList;
	}

	@Override
	public void cleanAll() {
		this.productOrderList.clear();
	}

	@Override
	public void purchase(Customer customer, Address ad) {
		
		Date today = new Date();
		
		OrderCustomer order = new OrderCustomer();
		
		Iterator<ProductOrderList> it = productOrderList.iterator();
		while (it.hasNext())
		{
			ProductOrderList productOrder = it.next();
			
			Product prod = productOrder.getProduct();
			prod.setQuantityProduct(prod.getQuantityProduct()-productOrder.getQuantity());
			em.merge(prod);
			
			order.addProductOrder(productOrder);
		}
		
		order.setCustomer(customer);
		order.setDateOrder(today);
		order.setAmountOrder(this.priceTotal());
		order.setAmountDeliveryOrder(new Double(5));
		
		order.setDateDeliveryOrder(today);
		
		customer.getAccount().setBalanceAccount(customer.getAccount().getBalanceAccount() - priceTotal() );
		em.merge(customer.getAccount());
		
		ad.setCustomer(customer);
		
		order.setAddress(ad);
		
		em.persist(order);
		
		// New cart
		productOrderList.clear();
		
	}

	@Override
	public double priceTotal() {
		double priceTotal = 0;
		
		Iterator<ProductOrderList> it = productOrderList.iterator();
		while (it.hasNext())
		{
			ProductOrderList product = it.next();
			priceTotal += product.getPrice();
		}
		return priceTotal;
	}
}

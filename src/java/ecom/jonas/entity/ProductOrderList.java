/**
 * 
 */
package ecom.jonas.entity;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.JoinColumn;

import ecom.jonas.entity.OrderCustomer;
import ecom.jonas.entity.Product;

/**
 * @author Olivier Page
 *
 */
@Entity
public class ProductOrderList implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6135040162824117065L;
	
	/**
	 * Primary key (will be auto generated).
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long idProductOrderList;
	
	/**
	 * Quantity of Product in the Order.
	 */
	private Integer quantity = null;
	
	/**
	 * This Product of the Order.
	 */
	@ManyToOne
	@JoinColumn(name = "idProduct", referencedColumnName = "idProduct")
	private Product product = null;
	
	/**
	 * My Order
	 */
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "idOrderCustomer", referencedColumnName = "idOrderCustomer")
    private OrderCustomer ordercustomer= null;	
	
	/**
	 * Default constructor.
	 */
	public ProductOrderList() {

	}
	
	public ProductOrderList(OrderCustomer ordercustomer, Product product, Integer quantity) {
		this.setProduct(product);
		this.setOrderCustomer(ordercustomer);
		this.setQuantity(quantity);
	}

	/**
	 * @param quantity the quantity to set
	 */
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	/**
	 * @return the quantity
	 */
	public Integer getQuantity() {
		return quantity;
	}

	/**
	 * @param product the product to set
	 */
	public void setProduct(Product product) {
		this.product = product;
	}

	/**
	 * @return the product
	 */
	@ManyToOne
	@JoinColumn(name="idProduct")
	public Product getProduct() {
		return product;
	}

	/**
	 * @param idProductOrderList the idProductOrderList to set
	 */
	public void setIdProductOrderList(Long idProductOrderList) {
		this.idProductOrderList = idProductOrderList;
	}

	/**
	 * @return the idProductOrderList
	 */
	public Long getIdProductOrderList() {
		return idProductOrderList;
	}

	/**
	 * @param order the order to set
	 */
	public void setOrderCustomer(OrderCustomer ordercustomer) {
		this.ordercustomer = ordercustomer;
	}

	/**
	 * @return the order
	 */
	public OrderCustomer getOrderCustomer() {
		return ordercustomer;
	}
	public double getPrice()
	{
		return this.quantity * this.product.getPriceProduct();
	}

}

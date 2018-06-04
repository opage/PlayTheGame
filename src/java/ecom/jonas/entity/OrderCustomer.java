/**
 * 
 */
package ecom.jonas.entity;

import static ecom.jonas.entity.OrderCustomer.QN.ALL_ORDERCUSTOMERS;
import static ecom.jonas.entity.OrderCustomer.QN.FIND_ORDERCUSTOMERBYID;
import static ecom.jonas.entity.OrderCustomer.QN.FIND_ORDERCUSTOMERBYIDCUSTOMER;
import static javax.persistence.FetchType.EAGER;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;


/**
 * @author Olivier Page
 *
 */
@Entity
@NamedQueries({@NamedQuery(name=ALL_ORDERCUSTOMERS, query="SELECT oc FROM OrderCustomer oc"),
@NamedQuery(name=FIND_ORDERCUSTOMERBYID, query="SELECT oc FROM OrderCustomer oc WHERE oc.idOrderCustomer = :IDORDERCUSTOMER"),
@NamedQuery(name=FIND_ORDERCUSTOMERBYIDCUSTOMER, query="SELECT OBJECT(oc) FROM OrderCustomer oc JOIN oc.customer c WHERE c.idCustomer = :IDCUSTOMER")})
public class OrderCustomer implements Serializable {	

	public static interface QN {
        /**
         * Search all OrderCustomer.
         */
        String ALL_ORDERCUSTOMERS = "OrderCustomer.allOrderCustomers";

        /**
         * Search a named OrderCustomer.
         */
        String FIND_ORDERCUSTOMERBYID = "OrderCustomer.findOrderCustomerById";
        
        /**
         * Search on a idCustomer.
         */
        String FIND_ORDERCUSTOMERBYIDCUSTOMER = "OrderCustomer.findOrderCustomerByIdCustomer";
    }
	/**
	 * 
	 */
	private static final long serialVersionUID = -7264977385545036467L;
	
	 /**
     * Primary key (will be auto generated).
     */
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long idOrderCustomer;
    
    /**
     * Date of OrderCustomer
     */    
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dateOrder = null;
    
    /**
     * Amount of OrderCustomer
     */    
    private Double amountOrder = null;    
    
    /**
     * Amount of Delivery OrderCustomer
     */    
    private Double amountDeliveryOrder = null;
    
    /**
     * State of OrderCustomer
     */    
    // 1 : en attend
    // 2 : delivrer
    private int stateOrder = 1;
    
    public int getStateOrder() {
		return stateOrder;
	}

	public void setStateOrder(int stateOrder) {
		this.stateOrder = stateOrder;
	}

	/**
     * Amount of Delivery OrderCustomer
     */    
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dateDeliveryOrder = null;
    
    /**
     * Set of my ProductOrderCustomerList
     */    
    @OneToMany(mappedBy = "ordercustomer", fetch = EAGER, cascade = CascadeType.ALL)
    private Collection<ProductOrderList> productorderlist = new ArrayList<ProductOrderList>();    
    
    /**
     * The Customer
     */  
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name="idCustomer")
    private Customer customer = null;
    
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "idAddress", referencedColumnName = "idAddress")
	private Address address = null;
    
    /**
	 * Default constructor.
	 */
	public OrderCustomer() {

	}
	
	public OrderCustomer(Customer customer){
		this.setCustomer(customer);
	}
    
    /**
	 * @param idOrderCustomer the idOrderCustomer to set
	 */
	public void setIdOrderCustomer(Long idOrderCustomer) {
		this.idOrderCustomer = idOrderCustomer;
	}

	/**
	 * @return the idOrderCustomer
	 */
	public Long getIdOrderCustomer() {
		return this.idOrderCustomer;
	}

	/**
	 * @param dateOrder the dateOrder to set
	 */
	public void setDateOrder(Date dateOrder) {
		this.dateOrder = dateOrder;
	}

	/**
	 * @return the dateOrder
	 */
	public Date getDateOrder() {
		return this.dateOrder;
	}

	/**
	 * @param amountOrder the amountOrder to set
	 */
	public void setAmountOrder(Double amountOrder) {
		this.amountOrder = amountOrder;
	}

	/**
	 * @return the amountOrder
	 */
	public Double getAmountOrder() {
		return this.amountOrder;
	}

	/**
	 * @param amountDeliveryOrder the amountDeliveryOrder to set
	 */
	public void setAmountDeliveryOrder(Double amountDeliveryOrder) {
		this.amountDeliveryOrder = amountDeliveryOrder;
	}

	/**
	 * @return the amountDeliveryOrder
	 */
	public Double getAmountDeliveryOrder() {
		return this.amountDeliveryOrder;
	}
	
	/**
	 * @param dateDeliveryOrder the dateDeliveryOrder to set
	 */
	public void setDateDeliveryOrder(Date dateDeliveryOrder) {
		this.dateDeliveryOrder = dateDeliveryOrder;
	}

	/**
	 * @return the dateDeliveryOrder
	 */
	public Date getDateDeliveryOrder() {
		return this.dateDeliveryOrder;
	}

	/**
	 * @param productOrderCustomerlist the productOrderCustomerlist to set
	 */	
	public void setProductOrderlist(Collection<ProductOrderList> productorderlist) {
		this.productorderlist = productorderlist;
	}

	/**
	 * @return the productOrderCustomerlist
	 */	
	public Collection<ProductOrderList> getProductOrderCustomerlist() {
		return this.productorderlist;
	}
	
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	/**
	 * @return the dateDeliveryOrder
	 */
	public Customer getCustomer() {
		return this.customer;
	}
	
	
    public void addProductOrder(ProductOrderList productOrder)
    {
        if (!productorderlist.contains(productOrder))
        {
        	productorderlist.add(productOrder);
            productOrder.setOrderCustomer(this);
        }
    }

    public void removeProductOrder(ProductOrderList productOrder)
    {
        productOrder.setOrderCustomer(null);
        if (productorderlist.contains(productOrder))
        {
        	productorderlist.remove(productOrder);
        }
    }

	/**
	 * @param address the address to set
	 */
	public void setAddress(Address address) {
		this.address = address;
	}

	/**
	 * @return the address
	 */
	public Address getAddress() {
		return address;
	}
	
}

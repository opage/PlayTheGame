package ecom.jonas.entity;

import static ecom.jonas.entity.Customer.QN.ALL_CUSTOMERS;
import static ecom.jonas.entity.Customer.QN.FIND_CUSTOMERBYMAIL;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;

/**
 * @author Olivier Page
 *
 */
@Entity
@NamedQueries({@NamedQuery(name=ALL_CUSTOMERS, query="SELECT c FROM Customer c"),
@NamedQuery(name=FIND_CUSTOMERBYMAIL, query="SELECT c FROM Customer c WHERE c.emailCustomer = :MAIL")})
public class Customer implements Serializable {


	public static interface QN {
        /**
         * Search all customers.
         */
        String ALL_CUSTOMERS = "Customer.allCustomers";

        /**
         * Search a customer.
         */
        String FIND_CUSTOMERBYMAIL = "Customer.findCustomerByMail";
    }
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8537693874451493864L;
	
	/**
     * Primary key (will be auto generated).
     */
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long idCustomer;
    
    /**
     * Login of the customer.
     */
    private String loginCustomer = null;
    
    /**
     * Password of the customer.
     */
    private String passwordCustomer = null;
    
    
    /**
     * firstnameCustomer of the customer.
     */
    private String firstnameCustomer = null;
    
    /**
     * lastnamrCustomer of the customer.
     */
    private String lastnameCustomer = null;
    
    /**
     * emailCustomer of the customer.
     */
    private String emailCustomer = null;
    
    /**
     * date of birthday  of the customer.
     */
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date birthdayCustomer = null;
    
    
    /**
     * The street home of the customer.
     */
    private String telFixCustomer = null;
    
    /**
     * The street home of the customer.
     */
    private String telMobilCustomer = null;
    
    @OneToOne(cascade={CascadeType.ALL})
    private Account account = null;
    
    /**
     * Set of my orders'list
     */
    @OneToMany(mappedBy="customer", cascade= CascadeType.ALL)
    @Column(name="idOrder")
    private Collection<OrderCustomer> orderList = new ArrayList<OrderCustomer>();
    /**
     * Set of my orders'list
     */
  
    @OneToMany(mappedBy = "customer")
	private Collection<Address> address = new ArrayList<Address>();
    /**
     * Set of my comments'list
     */
    @OneToMany(mappedBy="customer", cascade= CascadeType.ALL)
    private Collection<Comment> commentList = new ArrayList<Comment>();
    
    /**
	 * My favorite products
	 */
	@ManyToMany(mappedBy = "favoritedCustomerList", cascade = CascadeType.ALL)
	@JoinTable(name = "ProductFavoriteCustomer")
	private Collection<Product> productFavoriteList = new ArrayList<Product>();
 
    /**
	 * Default constructor.
	 */
	public Customer() {

	}
	
	public Customer(final String LoginCustomer, final String PasswordCustomer) {
		this.setLoginCustomer(LoginCustomer);
		this.setPasswordCustomer(PasswordCustomer);	
	}

	/**
	 * @param idCustomer the idCustomer to set
	 */
	public void setIdCustomer(Long idCustomer) {
		this.idCustomer = idCustomer;
	}

	/**
	 * @return the idCustomer
	 */
	public Long getIdCustomer() {
		return idCustomer;
	}

	/**
	 * @param loginCustomer the loginCustomer to set
	 */
	public void setLoginCustomer(String loginCustomer) {
		this.loginCustomer = loginCustomer;
	}

	/**
	 * @return the loginCustomer
	 */
	public String getLoginCustomer() {
		return loginCustomer;
	}

	/**
	 * @param passwordCustomer the passwordCustomer to set
	 */
	public void setPasswordCustomer(String passwordCustomer) {
		this.passwordCustomer = passwordCustomer;
	}

	/**
	 * @return the passwordCustomer
	 */
	public String getPasswordCustomer() {
		return passwordCustomer;
	}

	/**
	 * @param firstnameCustomer the firstnameCustomer to set
	 */
	public void setFirstnameCustomer(String firstnameCustomer) {
		this.firstnameCustomer = firstnameCustomer;
	}

	/**
	 * @return the firstnameCustomer
	 */
	public String getFirstnameCustomer() {
		return firstnameCustomer;
	}

	/**
	 * @param lastnameCustomer the lastnamrCustomer to set
	 */
	public void setLastnameCustomer(String lastnameCustomer) {
		this.lastnameCustomer = lastnameCustomer;
	}

	/**
	 * @return the lastnameCustomer
	 */
	public String getLastnameCustomer() {
		return lastnameCustomer;
	}

	/**
	 * @param emailCustomer the emailCustomer to set
	 */
	public void setEmailCustomer(String emailCustomer) {
		this.emailCustomer = emailCustomer;
	}

	/**
	 * @return the emailCustomer
	 */
	public String getEmailCustomer() {
		return emailCustomer;
	}
	
	/**
	 * @param set my account
	 */
	public void setAccount(Account account, boolean firstTime) {
		this.account = account;
		if (firstTime)
		{
			account.setCustomer(this, !firstTime);
		}
	}
	
	public void setAccount(Account account) {
		this.setAccount(account, true);
	}

	/**
	 * @return the Customer
	 */
	@OneToOne
	public Account getAccount() {
		return account;
	}
	
	/**
     * @return String representation of this entity object.
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(this.getClass().getName());
        sb.append("[id=");
        sb.append(getIdCustomer());
        sb.append(", name=");
        sb.append(getLoginCustomer());
        sb.append("]");
        return sb.toString();
    }

	/**
	 * @param birthdayCustomer the birthdayCustomer to set
	 */
	public void setBirthdayCustomer(Date birthdayCustomer) {
		this.birthdayCustomer = birthdayCustomer;
	}

	/**
	 * @return the birthdayCustomer
	 */
	public Date getBirthdayCustomer() {
		return birthdayCustomer;
	}

	

	/**
	 * @param telFixCustomer the telFixCustomer to set
	 */
	public void setTelFixCustomer(String telFixCustomer) {
		this.telFixCustomer = telFixCustomer;
	}

	/**
	 * @return the telFixCustomer
	 */
	public String getTelFixCustomer() {
		return telFixCustomer;
	}

	/**
	 * @param telMobilCustomer the telMobilCustomer to set
	 */
	public void setTelMobilCustomer(String telMobilCustomer) {
		this.telMobilCustomer = telMobilCustomer;
	}

	/**
	 * @return the telMobilCustomer
	 */
	public String getTelMobilCustomer() {
		return telMobilCustomer;
	}

	/**
	 * @param orderList the orderList to set
	 */
	public void setOrderList(Collection<OrderCustomer> orderList) {
		this.orderList = orderList;
	}

	/**
	 * @return the orderList
	 */
	public Collection<OrderCustomer> getOrderList() {
		return orderList;
	}

	/**
	 * @param commentList the commentList to set
	 */
	public void setCommentList(Collection<Comment> commentList) {
		this.commentList = commentList;
	}

	/**
	 * @return the commentList
	 */
	public Collection<Comment> getCommentList() {
		return commentList;
	}	
	
	/**
	 * @param productFavoriteList the productFavoriteList to set
	 */
	public void setProductFavoriteList(Collection<Product> productFavoriteList) {
		this.productFavoriteList = productFavoriteList;
	}

	/**
	 * @return the productFavoriteList
	 */
	@ManyToMany(mappedBy = "favoritedCustomerList")
	public Collection<Product> getProductFavoriteList() {
		return productFavoriteList;
	}

	/**
	 * @param address the address to set
	 */
	public void setAddress(Collection<Address> address) {
		this.address = address;
	}

	/**
	 * @return the address
	 */
	public Collection<Address> getAddress() {
		return address;
	}
    
}

package ecom.jonas.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
@Entity
public class Address implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
     * Primary key (will be auto generated).
     */
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long idAddress;
	/**
     * The street .
     */
    private String streetAddress = null;
    
    /**
     * The postal code .
     */
    private Integer postalCodeAddress = null;
    
    /**
     * The city .
     */
    private String cityAddress = null;
    
    /**
     * The country .
     */
    private String countryAddress = null;

	/**
	 * @param streetAddress the streetAddress to set
	 */
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "idCustomer", referencedColumnName = "idCustomer")
	private Customer customer=null;
    
   @OneToMany(mappedBy = "address", cascade = CascadeType.REFRESH)  
    private Collection<OrderCustomer> ordersCustomer= new ArrayList<OrderCustomer>();
    
	public void setStreetAddress(String streetAddress) {
		this.streetAddress = streetAddress;
	}

	/**
	 * @return the streetAddress
	 */
	public String getStreetAddress() {
		return streetAddress;
	}

	/**
	 * @param postalCodeAddress the postalCodeAddress to set
	 */
	public void setPostalCodeAddress(Integer postalCodeAddress) {
		this.postalCodeAddress = postalCodeAddress;
	}

	/**
	 * @return the postalCodeAddress
	 */
	public Integer getPostalCodeAddress() {
		return postalCodeAddress;
	}

	/**
	 * @param cityAddress the cityAddress to set
	 */
	public void setCityAddress(String cityAddress) {
		this.cityAddress = cityAddress;
	}

	/**
	 * @return the cityAddress
	 */
	public String getCityAddress() {
		return cityAddress;
	}

	/**
	 * @param countryAddress the countryAddress to set
	 */
	public void setCountryAddress(String countryAddress) {
		this.countryAddress = countryAddress;
	}

	/**
	 * @return the countryAddress
	 */
	public String getCountryAddress() {
		return countryAddress;
	}

	/**
	 * @param idAddress the idAddress to set
	 */
	public void setIdAddress(Long idAddress) {
		this.idAddress = idAddress;
	}

	/**
	 * @return the idAddress
	 */
	public Long getIdAddress() {
		return idAddress;
	}

	/**
	 * @param customer the customer to set
	 */
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	/**
	 * @return the customer
	 */
	public Customer getCustomer() {
		return customer;
	}

	/**
	 * @param ordersCustomer the ordersCustomer to set
	 */
	public void setOrdersCustomer(Collection<OrderCustomer> ordersCustomer) {
		this.ordersCustomer = ordersCustomer;
	}

	/**
	 * @return the ordersCustomer
	 */
	public Collection<OrderCustomer> getOrdersCustomer() {
		return ordersCustomer;
	}
}

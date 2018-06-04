package ecom.jonas.entity;

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
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
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
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@NamedQueries({@NamedQuery(name = "ALL_PRODUCTS", query = "SELECT p FROM Product p"),
@NamedQuery(name = "FIND_PRODUCTBYNAME", query="SELECT p FROM Product p WHERE p.nameProduct = :NAMEP"),
@NamedQuery(name = "FIND_PRODUCTBYID", query = "SELECT p FROM Product p WHERE p.idProduct=:param")})
public class Product implements Serializable {

	public static interface QN {
		/**
		 * Search all products.
		 */
		String ALL_PRODUCTS = "Product.allProducts";

		String FIND_PRODUCTBYNAME = "Product.findProductByName";
		
		String FIND_PRODUCTBYID = "Product.FindProductById";
	}

	/**
	 * Serial Version UID.
	 */
	private static final long serialVersionUID = 0L;

	/**
	 * Primary key (will be auto generated).
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	protected Long idProduct;

	/**
	 * Name of the product.
	 */
	private String nameProduct = null;

	/**
	 * Description of the product.
	 */
	@Column(length=10000)
	private String descriptionProduct = null;

	/**
	 * Price of the product.
	 */
	private Double priceProduct = null;

	/**
	 * Quantity of the product.
	 */
	private Integer quantityProduct = null;

	/**
	 * Discount of the product.
	 */
	private Integer discountProduct = null;

	/**
	 * Url Image of the product.
	 */
	private String urlImageProduct = null;

	/**
	 * Url Video of the product.
	 */
	private String urlVideoProduct = null;

	/**
	 * Date release of the product.
	 */
	@Temporal(javax.persistence.TemporalType.DATE)
	private Date releaseDateProduct = null;

	/**
	 * My Platform
	 */
	@ManyToOne
	@JoinColumn(name = "idPlatform", referencedColumnName = "idPlatform")
    protected Platform platform= null;	
	/**
	 * My ListOrders
	 */
	@OneToMany(mappedBy="product", cascade = CascadeType.REFRESH)
	protected Collection<ProductOrderList> orderList = new ArrayList<ProductOrderList>();
	
	@ManyToMany(cascade = CascadeType.REFRESH)
    @JoinTable(name = "ProductFavoriteCustomer",joinColumns = @JoinColumn(name = "idProduct", referencedColumnName = "idProduct"), inverseJoinColumns = @JoinColumn(name = "idCustomer", referencedColumnName = "idCustomer"))		
	protected Collection<Customer> favoritedCustomerList = new ArrayList<Customer>();
	
    @OneToMany(mappedBy="product", cascade=CascadeType.ALL)
    protected Collection<Comment> commentList = new ArrayList<Comment>();	
	
	private String ratingProduct = "0|0";

	/**
	 * Default constructor.
	 */
	public Product() {

	}	

    public Product(final String nameProduct) {    	
    	this.setNameProduct(nameProduct);               
    }
    
    public Product(final String nameProduct, String descriptionProduct) {    	
    	this.setNameProduct(nameProduct);  
    	this.setDescriptionProduct(descriptionProduct);               
    }

	/**
	 * @return an id for this object (incremented automatically)
	 */
	public Long getIdProduct() {
		return this.idProduct;
	}

	/**
	 * Sets the id of this author object.
	 * 
	 * @param id
	 *            the given id of this author
	 */
	public void setIdProduct(final Long id) {
		this.idProduct = id;
	}

	/**
	 * @return name of the product
	 */
	public String getNameProduct() {
		return nameProduct;
	}

	/**
	 * Sets the name of the author.
	 * 
	 * @param name
	 *            - the name of this author
	 */
	public void setNameProduct(final String name) {
		this.nameProduct = name;
	}

	public void setDescriptionProduct(String descriptionProduct) {
		this.descriptionProduct = descriptionProduct;
	}

	public String getDescriptionProduct() {
		return descriptionProduct;
	}

	/**
	 * @param priceProduct
	 *            the priceProduct to set
	 */
	public void setPriceProduct(Double priceProduct) {
		this.priceProduct = priceProduct;
	}

	/**
	 * @return the priceProduct
	 */
	public Double getPriceProduct() {
		return priceProduct;
	}

	/**
	 * @param quantityProduct
	 *            the quantityProduct to set
	 */
	public void setQuantityProduct(Integer quantityProduct) {
		this.quantityProduct = quantityProduct;
	}

	/**
	 * @return the quantityProduct
	 */
	public Integer getQuantityProduct() {
		return quantityProduct;
	}

	

	/**
	 * @param discountProduct
	 *            the discountProduct to set
	 */
	public void setDiscountProduct(Integer discountProduct) {
		this.discountProduct = discountProduct;
	}

	/**
	 * @return the discountProduct
	 */
	public Integer getDiscountProduct() {
		return discountProduct;
	}

	/**
	 * @param urlImageProduct
	 *            the urlImageProduct to set
	 */
	public void setUrlImageProduct(String urlImageProduct) {
		this.urlImageProduct = urlImageProduct;
	}

	/**
	 * @return the urlImageProduct
	 */
	public String getUrlImageProduct() {
		return urlImageProduct;
	}

	/**
	 * @param urlVideoProduct
	 *            the urlVideoProduct to set
	 */
	public void setUrlVideoProduct(String urlVideoProduct) {
		this.urlVideoProduct = urlVideoProduct;
	}

	/**
	 * @return the urlVideoProduct
	 */
	public String getUrlVideoProduct() {
		return urlVideoProduct;
	}

	/**
	 * @param releaseDateProduct
	 *            the releaseDateProduct to set
	 */
	public void setReleaseDateProduct(Date releaseDateProduct) {
		this.releaseDateProduct = releaseDateProduct;
	}

	/**
	 * @return the releaseDateProduct
	 */
	public Date getReleaseDateProduct() {
		return releaseDateProduct;
	}

	public void GetInfos() {

	}

	/**
	 * @param orderlist the orderlist to set
	 */
	public void setOrderlist(Collection<ProductOrderList> orderList) {
		this.orderList = orderList;
	}

	/**
	 * @return the orderlist
	 */
	@OneToMany(mappedBy="product")
	public Collection<ProductOrderList> getOrderlist() {
		return this.orderList;
	}

	/**
	 * @param platform the platform to set
	 */
	public void setPlatform(Platform platform) {
		this.platform = platform;
	}

	/**
	 * @return the platform
	 */
	public Platform getPlatform() {
		return platform;
	}
	
	
	/**
	 * @param ratingProduct the ratingProduct to set
	 */
	public void setRatingProduct(String ratingProduct) {
		this.ratingProduct = ratingProduct;
	}

	/**
	 * @return the ratingProduct
	 */
	public String getRatingProduct() {
		return ratingProduct;
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
	
	public String toString() {
		return "idProduct= " + idProduct + " nameProduct= " + nameProduct + " = "
				+ descriptionProduct;

	}

}

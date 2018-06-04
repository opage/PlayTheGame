/**
 * 
 */
package ecom.jonas.entity;

import java.io.Serializable;
import java.util.Collection;

import static ecom.jonas.entity.Platform.QN.ALL_PLATEFORMS;
import static ecom.jonas.entity.Platform.QN.FIND_PLATEFORMBYID;
import static ecom.jonas.entity.Platform.QN.FIND_PLATEFORMBYNAME;
import static javax.persistence.CascadeType.ALL;
import static javax.persistence.FetchType.EAGER;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.AUTO;

/**
 * @author Olivier Page
 *
 */
@Entity
@NamedQueries({@NamedQuery(name = ALL_PLATEFORMS, query="SELECT p FROM Platform p"),
@NamedQuery(name = FIND_PLATEFORMBYID, query = "SELECT p from Platform p where p.idPlatform=:param"),
@NamedQuery(name=FIND_PLATEFORMBYNAME, query="SELECT OBJECT(p) FROM Platform p WHERE p.namePlatform = :NAMEPL")})

public class Platform  implements Serializable {	

	public static interface QN {
        /**
         * Search all platforms.
         */
        String ALL_PLATEFORMS = "Plateform.allPlateforms";

        /**
         * Search a named platform.
         */
        String FIND_PLATEFORMBYNAME = "Plateform.findPlateformByName";
        
        /**
         * Search a id of platform.
         */
        String FIND_PLATEFORMBYID = "Plateform.findPlateformById";
    }
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4359802902190516556L;
	
	/**
     * Primary key (will be auto generated).
     */
    @Id
    @GeneratedValue(strategy = AUTO)
	private Long idPlatform;
    
    /**
     * Name of the platform.
     */
    private String namePlatform = null;   
    
    /**
     * Description of the descriptionPlatform.
     */
    private String descriptionPlatform = null;    
    
    /**
     * Set of my products
     */
    @OneToMany(mappedBy = "platform", fetch = EAGER, cascade = CascadeType.REMOVE)
    private Collection<Product> products = null;   
    
    /**
	 * Default constructor.
	 */
	public Platform() {

	}
	
	public Platform(final String NamePlatform) {
		this.setNamePlatform(NamePlatform);		
	}
	
	public Platform(final String NamePlatform, final String DescriptionPlatform) {
		this.setNamePlatform(NamePlatform);
		this.setDescriptionPlatform(DescriptionPlatform);
	}

	/**
	 * @param idPlatform the idPlatform to set
	 */
	public void setIdPlatform(Long idPlatform) {
		this.idPlatform = idPlatform;
	}

	/**
	 * @return the idPlatform
	 */
	public Long getIdPlatform() {
		return idPlatform;
	}

	/**
	 * @param namePlatform the namePlatform to set
	 */
	public void setNamePlatform(String namePlatform) {
		this.namePlatform = namePlatform;
	}

	/**
	 * @return the namePlatform
	 */
	public String getNamePlatform() {
		return this.namePlatform;
	}

	/**
	 * @param descriptionPlatform the descriptionPlatform to set
	 */
	public void setDescriptionPlatform(String descriptionPlatform) {
		this.descriptionPlatform = descriptionPlatform;
	}

	/**
	 * @return the descriptionPlatform
	 */
	public String getDescriptionPlatform() {
		return descriptionPlatform;
	}

	/**
	 * @param products the products to set
	 */
	
	public void setProducts(Collection<Product> products) {
		this.products = products;
	}

	/**
	 * @return the products
	 */
	
	@OneToMany(mappedBy="platform")	
	public Collection<Product> getProducts() {
		return products;
	}
	
	
    
    
}

/**
 * 
 */
package ecom.jonas.entity;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

import static ecom.jonas.entity.Type.QN.ALL_TYPES;
import static ecom.jonas.entity.Type.QN.FIND_TYPE;
import static javax.persistence.GenerationType.AUTO;

/**
 * @author Olivier Page
 *
*/
@Entity
@NamedQueries({@NamedQuery(name = ALL_TYPES, query = "SELECT t FROM Type t"),
@NamedQuery(name = FIND_TYPE, query = "SELECT t FROM Type t WHERE t.nameType = :NAMET")})
public class Type implements Serializable {

	public static interface QN {
		/**
		 * Search all types.
		 */
		String ALL_TYPES = "Type.allTypes";

		/**
		 * Search a named type.
		 */
		String FIND_TYPE = "Type.findType";
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = -237734385774986773L;

	/**
	 * Primary key (will be auto generated).
	 */
	@Id
	@GeneratedValue(strategy = AUTO)
	private Long idType;

	/**
	 * Name of the type.
	 */
	private String nameType = null;

	/**
	 * Description of the type.
	 */
	private String descriptionType = null;

	/**
	 * Games of this type.
	 */
	@ManyToMany(mappedBy = "types")
	private Collection<Game> games;

	/**
	 * Default constructor.
	 */
	public Type() {

	}

	public Type(final String NameType, final String DescriptionType) {
		this.setNameType(NameType);
		this.setDescriptionType(DescriptionType);
	}

	/**
	 * @param idType
	 *            the idType to set
	 */
	public void setIdType(Long idType) {
		this.idType = idType;
	}

	/**
	 * @return the idType
	 */
	public Long getIdType() {
		return idType;
	}

	/**
	 * @param nameType
	 *            the nameType to set
	 */
	public void setNameType(String nameType) {
		this.nameType = nameType;
	}

	/**
	 * @return the nameType
	 */
	public String getNameType() {
		return nameType;
	}

	/**
	 * @param descriptionType
	 *            the descriptionType to set
	 */
	public void setDescriptionType(String descriptionType) {
		this.descriptionType = descriptionType;
	}

	/**
	 * @return the descriptionType
	 */
	public String getDescriptionType() {
		return descriptionType;
	}

	/**
	 * @param games
	 *            the games to set
	 */

	public void setGames(Collection<Game> games) {
		this.games = games;
	}

	/**
	 * @return the games
	 */

	public Collection<Game> getGames() {
		return games;
	}
	
	
@Override
    public String  toString(){
    	
    	StringBuilder sb = new StringBuilder(this.getClass().getName());
        sb.append("[idType=");
        sb.append(idType);
        sb.append(", nameType");
        sb.append(nameType);
        sb.append("]");
        return sb.toString();
    	   
       }
}

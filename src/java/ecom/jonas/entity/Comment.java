/**
 * 
 */
package ecom.jonas.entity;

import static ecom.jonas.entity.Comment.QN.ALL_COMMENTS;
import static ecom.jonas.entity.Comment.QN.FIND_COMMENT;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Temporal;

import ecom.jonas.entity.Customer;
import ecom.jonas.entity.Product;

/**
 * @author Olivier Page
 *
 */
@Entity
@NamedQueries({@NamedQuery(name=ALL_COMMENTS, query="SELECT c FROM Comment c"),
@NamedQuery(name=FIND_COMMENT, query="SELECT c FROM Comment c WHERE c.idComment = :IDCOMMENT")})
public class Comment implements Serializable {
	
	public static interface QN {
        /**
         * Search all authors.
         */
        String ALL_COMMENTS = "Comment.allComments";

        /**
         * Search a named author.
         */
        String FIND_COMMENT = "Comment.findCommentById";
    }

	/**
	 * 
	 */
	private static final long serialVersionUID = -4631196016532180134L;
	
	/**
     * Primary key (will be auto generated).
     */
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long idComment;
    
    /**
     * Content of the comment.
     */
    private String contentComment = null;
    
    /**
     * Date of the comment.
     */
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dateComment = null;
    
    /**
     * My customer
     */
    @ManyToOne
    @JoinColumn(name="idCustomer")
    private Customer customer;    
    
    @ManyToOne
    @JoinColumn(name="idProduct")
    private Product product;
    
    public Comment() {        
               
    }    
    
    public Comment(String contentComment) {        
        this.setContentComment(contentComment);
    }    

	/**
	 * @param idComment the idComment to set
	 */
	public void setIdComment(Long idComment) {
		this.idComment = idComment;
	}

	/**
	 * @return the idComment
	 */
	public Long getIdComment() {
		return idComment;
	}

	/**
	 * @param contentComment the contentComment to set
	 */
	public void setContentComment(String contentComment) {
		this.contentComment = contentComment;
	}

	/**
	 * @return the contentComment
	 */
	public String getContentComment() {
		return contentComment;
	}

	/**
	 * @param dateComment the dateComment to set
	 */
	public void setDateComment(Date dateComment) {
		this.dateComment = dateComment;
	}

	/**
	 * @return the dateComment
	 */
	public Date getDateComment() {
		return dateComment;
	}
	
	/**
     * @return String representation of this entity object.
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(this.getClass().getName());
        sb.append("[id=");
        sb.append(getIdComment());
        sb.append(", ContentComment=");
        sb.append(getContentComment());
        sb.append("]");
        return sb.toString();
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
	 * @param product the product to set
	 */
	public void setProduct(Product product) {
		this.product = product;
	}

	/**
	 * @return the product
	 */
	public Product getProduct() {
		return product;
	}

}

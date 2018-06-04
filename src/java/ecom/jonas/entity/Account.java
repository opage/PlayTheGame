/**
 * 
 */
package ecom.jonas.entity;

import static ecom.jonas.entity.Account.QN.ALL_ACCOUNTS;
import static ecom.jonas.entity.Account.QN.FIND_ACCOUNTBYID;
import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;

import ecom.jonas.entity.Customer;
/**
 * @author Olivier Page
 *
 */
@Entity
@NamedQueries({@NamedQuery(name=ALL_ACCOUNTS, query="SELECT ac FROM Account ac"),
@NamedQuery(name=FIND_ACCOUNTBYID, query="SELECT ac FROM Account ac WHERE ac.idAccount = :IDACCOUNT")})
public class Account implements Serializable {

	public static interface QN {
        /**
         * Search all accounts.
         */
        String ALL_ACCOUNTS = "Account.allAccounts";
        /**
         * Search an account.
         */
        String FIND_ACCOUNTBYID = "Account.findAccountById";        
    }
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5282513568696090677L;
	
	/**
     * Primary key (will be auto generated).
     */
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long idAccount;
    
    /**
     * Balance of the account.
     */
    private double balanceAccount = 0;
    
    /**
     * The customer of account
     */
    @OneToOne(cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    private Customer customer = null;
    
    public Account() {

    }

	/**
	 * @param idAccount the idAccount to set
	 */
	public void setIdAccount(Long idAccount) {
		this.idAccount = idAccount;
	}

	/**
	 * @return the idAccount
	 */
	public Long getIdAccount() {
		return idAccount;
	}

	/**
	 * @param balanceAccount the balanceAccount to set
	 */
	public void setBalanceAccount(double balanceAccount) {
		this.balanceAccount = balanceAccount;
	}

	/**
	 * @return the balanceAccount
	 */
	public double getBalanceAccount() {
		return balanceAccount;
	} 
	
	/**
	 * @param set the customer of account
	 *            
	 */
	public void setCustomer(Customer customer, boolean firstTime) {
		this.customer = customer;

		if (firstTime) {
			customer.setAccount(this);
		}
	}
	
	public void setCustomer(Customer customer) {
		this.setCustomer(customer, true);
	}

	/**
	 * @return the Customer
	 */
	@OneToOne
	public Customer getCustomer() {
		return customer;
	}
    
    

}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ecom.jonas.entity;

import static ecom.jonas.entity.Administrator.QN.ALL_ADMINISTRATORS;
import static ecom.jonas.entity.Administrator.QN.FIND_ADMINSTRATORBYLOGIN;

import java.io.Serializable;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

/**
 *
 * @author TienLuong
 */
@Entity
@NamedQueries({@NamedQuery(name=ALL_ADMINISTRATORS, query="SELECT ad FROM Administrator ad"),
@NamedQuery(name=FIND_ADMINSTRATORBYLOGIN, query="SELECT ad FROM Administrator ad WHERE ad.loginAdmin = :LOGINADMIN")})
public class Administrator implements Serializable {
	
	public static interface QN {
        /**
         * Search all authors.
         */
        String ALL_ADMINISTRATORS = "Administrator.allAdministrators";

        /**
         * Search a named author.
         */
        String FIND_ADMINSTRATORBYLOGIN = "Administrator.findAdministratorByLogin";
    }
	
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idAdmin;
    
    private String loginAdmin;
    private String passwordAdmin;

    public Administrator() {
        
    }
    
    public Administrator(final String loginAdmin, final String password) {
        setLoginAdmin(loginAdmin);
        setPasswordAdmin(password);        
    }

    public Long getIdAdmin() {
        return idAdmin;
    }

    public void setIdAdmin(Long id) {
        this.idAdmin = id;
    }

    public String getLoginAdmin() {
        return loginAdmin;
    }

    public void setLoginAdmin(String loginAdmin) {
        this.loginAdmin = loginAdmin;
    }

    public String getPasswordAdmin() {
        return passwordAdmin;
    }

    public void setPasswordAdmin(String passwordAdmin) {
        this.passwordAdmin = passwordAdmin;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Administrator other = (Administrator) obj;
        if (this.idAdmin != other.idAdmin && (this.idAdmin == null || !this.idAdmin.equals(other.idAdmin))) {
            return false;
        }
        if ((this.loginAdmin == null) ? (other.loginAdmin != null) : !this.loginAdmin.equals(other.loginAdmin)) {
            return false;
        }
        if ((this.passwordAdmin == null) ? (other.passwordAdmin != null) : !this.passwordAdmin.equals(other.passwordAdmin)) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 97 * hash + (this.idAdmin != null ? this.idAdmin.hashCode() : 0);
        hash = 97 * hash + (this.loginAdmin != null ? this.loginAdmin.hashCode() : 0);
        hash = 97 * hash + (this.passwordAdmin != null ? this.passwordAdmin.hashCode() : 0);
        return hash;
    }

    @Override
    public String toString() {
        return "entity.Administrator[id=" + idAdmin + "]";
    }

}

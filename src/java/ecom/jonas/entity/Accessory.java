/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ecom.jonas.entity;

import static ecom.jonas.entity.Accessory.QN.ALL_ACCESSORIES;
import static ecom.jonas.entity.Accessory.QN.FIND_ACCESSORY;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
/**
 *
 * @author TienLuong
 */
@Entity
@NamedQueries({@NamedQuery(name=ALL_ACCESSORIES, query="SELECT acc FROM Accessory acc"),
@NamedQuery(name=FIND_ACCESSORY, query="SELECT acc FROM Accessory acc WHERE acc.nameProduct = :NAMEPRODUCT")})
public class Accessory extends Product implements Serializable {
	
	public static interface QN {
        /**
         * Search all accessories.
         */
        String ALL_ACCESSORIES = "Accessory.allAccessories";

        /**
         * Search a named accessory.
         */
        String FIND_ACCESSORY = "Accessory.findAccessoryByName";
    }
	
    private static final long serialVersionUID = 1L;
    public Long getidProduct() {
		return super.idProduct;
	}
	public String getName() {
		return super.getNameProduct();
	}
	public String getDescriptionProduct() {
		return super.getDescriptionProduct();
	}
	public Double getPriceProduct() {
		return super.getPriceProduct();
	}
	public Integer getQuantityProduct() {
		return super.getQuantityProduct();
	}
	public Integer getDiscountProduct() {
		return super.getDiscountProduct();
	}
	public String getUrlImageProduct() {
		return super.getUrlImageProduct();
	}
	public String getUrlVideoProduct() {
		return super.getUrlVideoProduct();
	}
	public Date getReleaseDateProduct() {
		return super.getReleaseDateProduct();
	} 
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idProduct != null ? idProduct.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Accessory)) {
            return false;
        }
        Accessory other = (Accessory) object;
        if ((this.idProduct == null && other.idProduct != null) || (this.idProduct != null && !this.idProduct.equals(other.idProduct))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Accessory[id=" + idProduct + "]";
    }

}

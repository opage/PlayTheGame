/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ecom.jonas.entity;

import static ecom.jonas.entity.Console.QN.ALL_CONSOLES;
import static ecom.jonas.entity.Console.QN.FIND_CONSOLE;

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
@NamedQueries({@NamedQuery(name=ALL_CONSOLES, query="SELECT c FROM Console c"),
@NamedQuery(name=FIND_CONSOLE, query="SELECT c FROM Console c WHERE c.nameProduct = :NAMEP")})
public class Console extends Product implements Serializable {
	
	public static interface QN {
        /**
         * Search all OrderCustomer.
         */
        String ALL_CONSOLES = "Console.allConsoles";

        /**
         * Search a named OrderCustomer.
         */
        String FIND_CONSOLE = "Console.findConsole";
    }	
	
    private static final long serialVersionUID = 1L;
    
    private String hardDriveConsole;
    private double weightConsole;
    
    public Console() {
    	super();
    }
    
    public Console(final String nameConsole) {
    	super(nameConsole);
    }
    
    public Console(final String nameConsole, final String HardDriveConsole, final String WeightConsole) {
    	super(nameConsole);
    	this.setHardDriveConsole(HardDriveConsole);
    	this.setWeightConsole(weightConsole);
    }

    public String getHardDriveConsole() {
        return hardDriveConsole;
    }

    public void setHardDriveConsole(String hardDriveConsole) {
        this.hardDriveConsole = hardDriveConsole;
    }

    public double getWeightConsole() {
        return weightConsole;
    }

    public void setWeightConsole(double weightConsole) {
        this.weightConsole = weightConsole;
    }
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
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Console other = (Console) obj;
        if (this.idProduct != other.idProduct && (this.idProduct == null || !this.idProduct.equals(other.idProduct))) {
            return false;
        }
        if ((this.hardDriveConsole == null) ? (other.hardDriveConsole != null) : !this.hardDriveConsole.equals(other.hardDriveConsole)) {
            return false;
        }
        if (this.weightConsole != other.weightConsole) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 79 * hash + (this.idProduct != null ? this.idProduct.hashCode() : 0);
        hash = 79 * hash + (this.hardDriveConsole != null ? this.hardDriveConsole.hashCode() : 0);
        hash = 79 * hash + (int) (Double.doubleToLongBits(this.weightConsole) ^ (Double.doubleToLongBits(this.weightConsole) >>> 32));
        return hash;
    }

    @Override
    public String toString() {
        return "entity.Console[idProduct=" + idProduct + "]";
    }

}

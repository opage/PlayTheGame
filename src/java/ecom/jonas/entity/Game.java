/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ecom.jonas.entity;

import static ecom.jonas.entity.Game.QN.ALL_GAMES;
import static ecom.jonas.entity.Game.QN.FIND_GAME;
import static javax.persistence.CascadeType.ALL;
import static javax.persistence.FetchType.LAZY;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

/**
 *
 * @author TienLuong
 */
@Entity
@NamedQueries({@NamedQuery(name=ALL_GAMES, query="select g FROM Game g"),
@NamedQuery(name=FIND_GAME, query="select g FROM Game g WHERE g.nameProduct = :NAMEP")})
public class Game extends Product implements Serializable {
	public static interface QN {
        /**
         * Search all OrderCustomer.
         */
        String ALL_GAMES = "Games.allGames";

        /**
         * Search a named OrderCustomer.
         */
        String FIND_GAME = "Games.findGame";
    }	
	
    private static final long serialVersionUID = 1L;    
    private String developerProduct=null;
    private String editorProduct=null;
    
    /**
     * Different types of this game.
     */  
    @ManyToMany(fetch = LAZY, cascade =CascadeType.REFRESH)
    @JoinTable(name = "Game_Type",joinColumns = @JoinColumn(name = "idProduct", referencedColumnName = "idProduct"), inverseJoinColumns = @JoinColumn(name = "idType", referencedColumnName = "idType"))		
	private Collection<Type> types;
    
    public Game() {
    	super();
    }
    
    public Game(final String nameGame) {
    	super(nameGame);
    }
    
    public Game(final String nameProduct, String descriptionProduct) {    
    	super(nameProduct, descriptionProduct);    	               
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
        if (!(object instanceof Game)) {
            return false;
        }
        Game other = (Game) object;
        if ((this.idProduct == null && other.idProduct != null) || (this.idProduct != null && !this.idProduct.equals(other.idProduct))) {
            return false;
        }
        return true;
    }

    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(this.getClass().getName());
        sb.append("[idProduct=");
        sb.append(super.idProduct);
        sb.append(", priceProduct=");
        sb.append(super.getPriceProduct());
        sb.append(" , quantityProduct=");
        sb.append(super.getQuantityProduct());
        sb.append(" , developerProduct=");
        sb.append(developerProduct);
        sb.append(" , editorProduct=");
        sb.append(editorProduct);
        sb.append("]");
        return sb.toString();
    }

	/**
	 * @param developerProduct the developerProduct to set
	 */
	public void setDeveloperProduct(String developerProduct) {
		this.developerProduct = developerProduct;
	}

	/**
	 * @return the developerProduct
	 */
	public String getDeveloperProduct() {
		return developerProduct;
	}

	/**
	 * @param editorProduct the editorProduct to set
	 */
	public void setEditorProduct(String editorProduct) {
		this.editorProduct = editorProduct;
	}

	/**
	 * @return the editorProduct
	 */
	public String getEditorProduct() {
		return editorProduct;
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
	/**
	 * @param types the types to set
	 */	
	public void setTypes(Collection<Type> types) {
		this.types = types;
	}

	/**
	 * @return the types
	 */	
	public Collection<Type> getTypes() {
		return types;
	}

}

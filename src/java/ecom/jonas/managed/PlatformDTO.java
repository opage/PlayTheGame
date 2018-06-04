package ecom.jonas.managed;

import java.io.Serializable;

/**
* @author Nabil Bensalem
*
*/
public class PlatformDTO implements Serializable{
	private Long idPlatform;
	private String namePlatform = null;   
	private String descriptionPlatform = null;
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
		return namePlatform;
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
}

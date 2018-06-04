package ecom.jonas.managed;

import java.util.List;


import javax.ejb.EJB;

import ecom.jonas.entity.Platform;

import ecom.jonas.session.AdminRemote;

import javax.faces.component.html.HtmlDataTable;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

/**
*
* @author Nabil Bensalem
*/

public class PlatformManaged {

	@EJB
	private AdminRemote admin;
	private HtmlDataTable dataTable;
	private Platform selectedPlatform= new Platform();
	private List<Platform> platforms;
	private String idPlatform="";
	private String namePlatform="";
	private String descriptionPlatform="";
	private String namePlatformAdded="";
	private String descriptionPlatformAdded="";
	private String parmaRecherche="";

	public PlatformManaged() {
	
		Connecction();
	}
	// too methodes for recover the HtmlDataTable*******************
	//**************************************************************
	//**************************************************************
	/**
	 * @param platforms
	 *            the platforms to set
	 */
	public void setPlatforms(List<Platform> platforms) {
		this.platforms = platforms;
	}

	/**
	 * @return the platforms
	 */
	public List<Platform> getPlatforms() {
		return (List<Platform>) admin.getPlatformByName(parmaRecherche.trim());
	}

	/**
	 * @param dataTable
	 *            the dataTable to set
	 */
	public void setDataTable(HtmlDataTable dataTable) {
		this.dataTable = dataTable;
	}

	/**
	 * @return the dataTable
	 */
	public HtmlDataTable getDataTable() {
		return dataTable;
	}
	// too methodes set and get for recovring the Row Selected <Platform>*******************
	//**************************************************************
	//**************************************************************
	/**
	 * @param selectedPlatform
	 *            the selectedPlatform to set
	 */
	public void setSelectedPlatform(Platform selectedPlatform) {
		this.selectedPlatform = selectedPlatform;
	}

	/**
	 * @return the selectedPlatform
	 */
	public Platform getSelectedPlatform() {
		return (Platform) dataTable.getRowData();
	}
	//  methodes CRUD "communication with AdminBeans"SessienBean ****
	//**************************************************************
	//**************************************************************	
	public String creatPlatform()
    {   
  		admin.createPlatform(namePlatformAdded,descriptionPlatformAdded);
		closePopupAdd();
       return null;
    }
	public String removePlatform() {
		selectedPlatform = (Platform) dataTable.getRowData();
	    admin.removePlatform(selectedPlatform.getIdPlatform());
		return null;
	}
	public String updatePlatform() {
		selectedPlatform.setIdPlatform(Long.parseLong(idPlatform));
		selectedPlatform.setNamePlatform(namePlatform);
		selectedPlatform.setDescriptionPlatform(descriptionPlatform);
		admin.updatePlatform(selectedPlatform);
		closePopupEdit();
		return null;
	}
	public String editPlatform()
    {
        selectedPlatform = (Platform) dataTable.getRowData();
        namePlatform=selectedPlatform.getNamePlatform();
        descriptionPlatform=selectedPlatform.getDescriptionPlatform();
        idPlatform=selectedPlatform.getIdPlatform().toString();
        openPopupEdit();
       return null;
    }
	
	
	
	

	private void Connecction() {

		Context initialContext = null;
		System.setProperty(Context.INITIAL_CONTEXT_FACTORY,
				"org.objectweb.carol.jndi.spi.MultiOrbInitialContextFactory");
		try {
			initialContext = new InitialContext();

		} catch (Exception e) {
			System.err.println("Cannot get initial context : " + e);
			System.exit(2);
		}

		try {

			admin = (AdminRemote) initialContext.lookup("AdminBean");
			System.out.println("I can get AdminBean ");
		} catch (NamingException e) {
			// TODO Auto-generated catch blockCannot
			System.out.println("cannot get AdminBean");

		}

	}
	public String getIdPlatform() {
		return idPlatform;
	}

	public void setNamePlatform(String namePlatform) {
		this.namePlatform = namePlatform;
	}

	public String getNamePlatform() {
		return namePlatform;
	}

	public void setDescriptionPlatform(String descriptionPlatform) {
		this.descriptionPlatform = descriptionPlatform;
	}

	public String getDescriptionPlatform() {
		return descriptionPlatform;
	}
	public void setParmaRecherche(String parmaRecherche) {
		this.parmaRecherche = parmaRecherche;
	}
	
	public String getParmaRecherche() {
		return parmaRecherche;
	}

	//Popup Managed************************************************************************************
	private boolean visibleEdit = false;
    private boolean visibleAdd = false;
    public boolean isVisibleEdit() { return visibleEdit; }
	 public void setVisibleEdit(boolean visible) { this.visibleEdit = visible; }
	    
	    public String closePopupEdit() {
	    	visibleEdit = false;
	    	return null;
	    }
	    
	    public String openPopupEdit() {
	    	visibleEdit = true;
	    	return null;
	    }
	public boolean isVisibleAdd() { return visibleAdd; }
	    
	    public void setVisibleAdd(boolean visible) { this.visibleAdd = visible; }
	    
	    public String  closePopupAdd() {
	    	visibleAdd = false;
	    	return null;
	    }
	    
	    public String openPopupAdd() {
	    	namePlatformAdded=null;
	    	descriptionPlatformAdded=null;
	    	visibleAdd = true;
	    	return null;
	    }

		/**
		 * @param idPlatform the idPlatform to set
		 */
		public void setIdPlatform(String idPlatform) {
			this.idPlatform = idPlatform;
		}
		/**
		 * @param namePlatformAdded the namePlatformAdded to set
		 */
		public void setNamePlatformAdded(String namePlatformAdded) {
			this.namePlatformAdded = namePlatformAdded;
		}
		/**
		 * @return the namePlatformAdded
		 */
		public String getNamePlatformAdded() {
			return namePlatformAdded;
		}
		/**
		 * @param descriptionPlatformAddes the descriptionPlatformAddes to set
		 */
		public void setDescriptionPlatformAdded(String descriptionPlatformAdde) {
			this.descriptionPlatformAdded = descriptionPlatformAdde;
		}
		/**
		 * @return the descriptionPlatformAddes
		 */
		public String getDescriptionPlatformAdded() {
			return descriptionPlatformAdded;
		}
		
		

		
		
		
}

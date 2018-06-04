package ecom.jonas.managed;
import java.util.List;


import javax.ejb.EJB;

import ecom.jonas.entity.Type;

import ecom.jonas.session.AdminRemote;

import javax.faces.component.html.HtmlDataTable;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

/**
*
* @author Nabil Bensalem
*/

public class TypeManaged {

	@EJB
	private AdminRemote admin;
	private HtmlDataTable dataTable;
	private Type selectedType= new Type();
	private List<Type> types;
	private String idType="";
	private String nameType="";
	private String descriptionType="";
	private String nameTypeAdded="";
	private String descriptionTypeAdded="";
	private String parmaRecherche="";

	public TypeManaged() {
	
		Connecction();
	}
	// too methodes for recover the HtmlDataTable*******************
	//**************************************************************
	//**************************************************************
	/**
	 * @param types
	 *            the types to set
	 */
	public void setTypes(List<Type> types) {
		this.types = types;
	}

	/**
	 * @return the types
	 */
	public List<Type> getTypes() {
		return (List<Type>) admin.getTypeByName(parmaRecherche.trim());
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
	// too methodes set and get for recovring the Row Selected <Type>*******************
	//**************************************************************
	//**************************************************************
	/**
	 * @param selectedType
	 *            the selectedType to set
	 */
	public void setSelectedType(Type selectedType) {
		this.selectedType = selectedType;
	}

	/**
	 * @return the selectedType
	 */
	public Type getSelectedType() {
		return (Type) dataTable.getRowData();
	}
	//  methodes CRUD "communication with AdminBeans"SessienBean ****
	//**************************************************************
	//**************************************************************	
	public String creatType()
    {   
  		admin.createType(nameTypeAdded,descriptionTypeAdded);
		closePopupAdd();
       return null;
    }
	public String removeType() {
		selectedType = (Type) dataTable.getRowData();
	    admin.removeType(selectedType.getIdType());
		return null;
	}
	public String updateType() {
		selectedType.setIdType(Long.parseLong(idType));
		selectedType.setNameType(nameType);
		selectedType.setDescriptionType(descriptionType);
		admin.updateType(selectedType);
		closePopupEdit();
		return null;
	}
	public String editType()
    {
        selectedType = (Type) dataTable.getRowData();
        nameType=selectedType.getNameType();
        descriptionType=selectedType.getDescriptionType();
        idType=selectedType.getIdType().toString();
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
	public String getIdType() {
		return idType;
	}

	public void setNameType(String nameType) {
		this.nameType = nameType;
	}

	public String getNameType() {
		return nameType;
	}

	public void setDescriptionType(String descriptionType) {
		this.descriptionType = descriptionType;
	}

	public String getDescriptionType() {
		return descriptionType;
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
	    	nameTypeAdded=null;
	    	descriptionTypeAdded=null;
	    	visibleAdd = true;
	    	return null;
	    }

		/**
		 * @param idType the idType to set
		 */
		public void setIdType(String idType) {
			this.idType = idType;
		}
		/**
		 * @param nameTypeAdded the nameTypeAdded to set
		 */
		public void setNameTypeAdded(String nameTypeAdded) {
			this.nameTypeAdded = nameTypeAdded;
		}
		/**
		 * @return the nameTypeAdded
		 */
		public String getNameTypeAdded() {
			return nameTypeAdded;
		}
		/**
		 * @param descriptionTypeAddes the descriptionTypeAddes to set
		 */
		public void setDescriptionTypeAdded(String descriptionTypeAdde) {
			this.descriptionTypeAdded = descriptionTypeAdde;
		}
		/**
		 * @return the descriptionTypeAddes
		 */
		public String getDescriptionTypeAdded() {
			return descriptionTypeAdded;
		}
		
		

		
		
		
}

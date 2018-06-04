package ecom.jonas.managed;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import javax.ejb.EJB;
import javax.faces.component.html.HtmlDataTable;
import javax.faces.context.FacesContext;

import javax.faces.event.ActionEvent;
import javax.faces.event.ValueChangeEvent;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletContext;

import jxl.write.Boolean;
import java.io.FileInputStream;
import com.icesoft.faces.component.inputfile.InputFile;
import com.icesoft.faces.component.inputfile.FileInfo;
import com.icesoft.util.Properties;

import ecom.jonas.entity.Game;

import ecom.jonas.entity.Platform;
import ecom.jonas.entity.Type;
import ecom.jonas.session.AdminRemote;

/**
 * 
 * @author Nabil
 */

public class gameAddManaged {
	@EJB
	private AdminRemote admin;
	
	
	private Map<String, Object> platformAddItem = null;
	private Platform platformAdd = null;
	private List<String> listIdTypesAdd;
	private long idPlatformAdd=1;
	private String priceProduct;
	private Date realiseDateAdd = new Date();
    
	private Map<String, String> typeAddItem = null;
	
	
	private String nameGame = "";
	
	private Game gameAdd = new Game();
	public gameAddManaged() {
		Connecction();
		
		

	}

	

	

	
	public void setListIdTypesAdd(List<String> listTypesAdd) {
		this.listIdTypesAdd = listTypesAdd;
	}

	/**
	 * @return the idType
	 */
	public List<String> getListIdTypesAdd() {
		return listIdTypesAdd;
	}


	/**
	 * @return the nameGame
	 */
	public String getNameGame() {
		return nameGame;
	}

	public void setNameGame(String nameGame) {
		this.nameGame = nameGame;
	}


	// ******************************************************************
	// Fin des Methodes de recherche par citeres et le table de list de games
	// *******************************************************************

	// ******************************************************************
	// les Methodes de Ajouter un game
	// *******************************************************************

	

	public void platformAddChanged(ValueChangeEvent event) {
		idPlatformAdd=Long.parseLong(event.getNewValue().toString());


	}

	
	/**
	 * @return the platformAddItem
	 */
	public Map<String, Object> getPlatformAddItem() {
		List<Platform> ps = (List<Platform>) admin.getAllPlatforms();
		platformAddItem = new LinkedHashMap<String, Object>();
		for (Platform p : ps)
			platformAddItem.put(p.getNamePlatform(), p.getIdPlatform());
		return platformAddItem;
	}

	/**
	 * @param typeAddItem
	 *            the typeAddItem to set
	 */
	public void setTypeAddItem(Map<String, String> typeAddItem) {
		this.typeAddItem = typeAddItem;
	}

	/**
	 * @return the typeAddItem
	 */
	public Map<String, String> getTypeAddItem() {
		List<Type> ts = (List<Type>) admin.getAllTypes();
		typeAddItem = new LinkedHashMap<String, String>();
		for (Type t : ts)
			typeAddItem.put(t.getNameType(), t.getIdType().toString());
		return typeAddItem;
	}

	/**
	 * @param gameAdd
	 *            the gameAdd to set
	 */
	public void setGameAdd(Game gameAdd) {
		this.gameAdd = gameAdd;
	}

	/**
	 * @param realiseDateAdd
	 *            the realiseDateAdd to set
	 */
	public void setRealiseDateAdd(Date realiseDateAdd) {
		this.realiseDateAdd = realiseDateAdd;
	}

	/**
	 * @return the realiseDateAdd
	 */
	public Date getRealiseDateAdd() {
		return realiseDateAdd;
	}

	/**
	 * @return the gameAdd
	 */
	public Game getGameAdd() {
		return gameAdd;
	}

	// ******************************************************************
	// Fin des Methodes d Ajouter un game
	// *******************************************************************

	// ******************************************************************
	// les Methodes Qui communique avec AdminBean
	// *******************************************************************

	public String creatGame() {
		ArrayList<Type>listTypesAdd=new ArrayList<Type>();
		for(int i=0;i<listIdTypesAdd.size();i++)
		listTypesAdd.add(admin.getTypeById(Integer.parseInt(listIdTypesAdd.get(i))));
		gameAdd.setReleaseDateProduct(realiseDateAdd);
		gameAdd.setPlatform(admin.getPlatformById((int) idPlatformAdd));
		gameAdd.setTypes(listTypesAdd);
		gameAdd.setUrlImageProduct(getSelectedImageRow());
		admin.createGame(gameAdd);
		
		return "toAdminGame";
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

	// ******************************************************************
	// Fin Methodes Qui communique avec AdminBean
	// *******************************************************************
	// ******************************************************************
	// les qui définir URLImage et UrlVidéo
	// *******************************************************************

	// latest file uploaded by client
	private InputFileData currentFile;
	
	private List<String> listImageUrl = new ArrayList<String>();
	private HtmlDataTable imageDataTable;
	private String selectedImageRow ; 
	private String disabilityTableImage ="true";
	
	public void uploadActionListener(ActionEvent actionEvent) {
		InputFile inputFile = (InputFile) actionEvent.getSource();
		FileInfo fileInfo = inputFile.getFileInfo();
		// file has been saved
		
		if (fileInfo.isSaved()) {
			
			listImageUrl.add("/upload/"+fileInfo.getFileName());

		}
	}

	/**
	 * <p>
	 * Allows a user to remove a file from a list of uploaded files. This
	 * methods assumes that a request param "fileName" has been set to a valid
	 * file name that the user wishes to remove or delete
	 * </p>
	 * 
	 * @param event
	 *            jsf action event
	 */

	public String removeUploadedFile() {
		
		String fileName= (String) imageDataTable.getRowData();
		 synchronized (listImageUrl) {
	            String  nameF;
	            for (int i = 0; i < listImageUrl.size(); i++) {
	            	nameF = (String)listImageUrl.get(i);
	                // remove our file
	                if (nameF.equals(fileName)) {
	           	        listImageUrl.remove(i);
	           	     FacesContext fc = FacesContext.getCurrentInstance();
	         		ServletContext sc = (ServletContext) fc.getExternalContext().getContext();
	         		System.out.println();

	           	       String urlRelative=sc.getRealPath("").replace('\\', '/');
	           	       System.out.println("urlRelative="+urlRelative);
	           	     new java.io.File(urlRelative+fileName).delete();
	                    break;
	                }
	            }
	        }
		return null;
	}
	
	
	public String getuUrlImageString(){
		String url="";
		for(int i=0;i<listImageUrl.size();i++)
			url=url+listImageUrl.get(i)+""	;
		return url;
	}

	public void setListImageUrl(List<String> listImageUrl) {
		this.listImageUrl = listImageUrl;

	}

	public List<String> getListImageUrl() {
		return listImageUrl;

	}

	public InputFileData getCurrentFile() {
		return currentFile;
	}

	/**
	 * @param imageDataTable
	 *            the imageDataTable to set
	 */
	public void setImageDataTable(HtmlDataTable imageDataTable) {
		this.imageDataTable = imageDataTable;
	}

	/**
	 * @return the imageDataTable
	 */
	public HtmlDataTable getImageDataTable() {
		return imageDataTable;
	}

	/**
	 * @return the disabilityTableImage
	 */
	public String getDisabilityTableImage() {
		return  (listImageUrl.size()>0)+"";
	}
	//Popup Managed************************************************************************************
	private boolean visibleEdit = false;
   
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

		/**
		 * @param selectedImageRow the selectedImageRow to set
		 */
		public void setSelectedImageRow(String selectedImageRow) {
			this.selectedImageRow = getuUrlImageString();
		}


		/**
		 * @return the selectedImageRow
		 */
		public String getSelectedImageRow() {
			this.selectedImageRow = getuUrlImageString();
			return selectedImageRow;
		}
	
}
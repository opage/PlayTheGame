package ecom.jonas.managed;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.faces.component.html.HtmlDataTable;
import javax.faces.context.FacesContext;

import javax.faces.event.ActionEvent;
import javax.faces.event.ValueChangeEvent;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import com.icesoft.faces.component.inputfile.InputFile;
import com.icesoft.faces.component.inputfile.FileInfo;
import javax.ejb.EJB;
import ecom.jonas.entity.Game;

import ecom.jonas.entity.Platform;
import ecom.jonas.entity.Type;
import ecom.jonas.session.AdminRemote;

/**
 * 
 * @author Nabil
 */

public class gameManaged {
	@EJB
	private AdminRemote admin;
	
	private Map<String, Object> typeItem = null;
	private Map<String, Object> platformItem = null;
	
	private Game selectedGame= new Game();
	
	private String priceProduct;
	
	private long idProduct=-1;
	
	private HtmlDataTable dataTable;
	private List<Game> games;
	private String nameGame = "";
	private long idPlatform = -1;
	private long idType = -1;
	
	public gameManaged() {
		Connecction();
	}

	// ******************************************************************
	// les Methodes de recherche par citeres et le table de list de games
	// *******************************************************************

	public Map<String, Object> getPlatformItem() {

		List<Platform> ps = (List<Platform>) admin.getAllPlatforms();
		platformItem = new LinkedHashMap<String, Object>();
		platformItem.put("Tous", new Long(-1));
		for (Platform p : ps)
			platformItem.put(p.getNamePlatform(), p.getIdPlatform());
		return platformItem;
	}

	/**
	 * @return the typeItem
	 */
	public Map<String, Object> getTypeItem() {
		List<Type> ts = (List<Type>) admin.getAllTypes();
		typeItem = new LinkedHashMap<String, Object>();
		typeItem.put("Tous", new Long(-1));
		for (Type t : ts)
			typeItem.put(t.getNameType(), t.getIdType());

		return typeItem;
	}

	public void platformChanged(ValueChangeEvent event) {
		idPlatform = Long.parseLong(event.getNewValue().toString());
	}

	public void typeChanged(ValueChangeEvent event) {
		idType= Long.parseLong(event.getNewValue().toString());
	}

	/**
	 * @param idType
	 *            the idType to set
	 */
	public void setIdType(long idType) {
		this.idType = idType;
	}

	/**
	 * @return the idType
	 */
	public long getIdType() {
		return idType;
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

	/**
	 * @return the nameGame
	 */
	public String getNameGame() {
		return nameGame;
	}

	public void setNameGame(String nameGame) {
		this.nameGame = nameGame;
	}

	public List<Game> getGames() {
		games = (List<Game>) admin.getGamesByCriteria(nameGame, idPlatform,
				idType);
		return games;
	}

	// ******************************************************************
	// Fin des Methodes de recherche par citeres et le table de list de games
	// *******************************************************************

	
	

	
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
	public String removeProduct() {
	
		admin.removeProduct(idProduct);
		idProduct=-1;
	    visibleDel = false;
		return null;
	}
	/**
	 * @param selectedGame the selectedGame to set
	 */
	public void setSelectedGame(Game selectedGame) {
		this.selectedGame = selectedGame;
	}

	/**
	 * @return the selectedGame
	 */
	public Game getSelectedGame() {
		return (Game) dataTable.getRowData();
	}
	
	private boolean visibleEdit = false;
	private String selectedImageUrl  =""; 
    public boolean isVisibleEdit() { return visibleEdit; }
	 public void setVisibleEdit(boolean visible) { this.visibleEdit = visible; }
	    
	    public String closePopupEdit() {
	    	selectedImageUrl="";
	    	visibleEdit = false;
	    	return null;
	    }
	    
	    public String openPopupEdit() {
	    	selectedImageUrl=getSelectedGame().getUrlImageProduct();
	    	visibleEdit = true;
	    	return null;
	    }
	    private boolean visibleDel = false;
	    
		public boolean isVisibleDel() { return visibleDel; }
		    
		    public void setVisibleDel(boolean visible) { this.visibleDel = visible; }
		    
		    public String  closePopupDel() {
		    	idProduct=-1;
		    	visibleDel = false;
		    	return null;
		    }
		    
		    public String openPopupDel() {
		    	idProduct=Long.parseLong(FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("idProduct"));
		    	System.out.println("I can get idProduct ="+idProduct);
		    	visibleDel = true;
		    	return null;
		    }
		/**
		 * @param selectedImageUrl the selectedImageUrl to set
		 */
		public void setSelectedImageUrl(String selectedImageUrl) {
			this.selectedImageUrl = getSelectedGame().getUrlImageProduct();
		}

		/**
		 * @return the selectedImageUrl
		 */
		public String getSelectedImageUrl() {
			
			return selectedImageUrl;
		}
	

	

}
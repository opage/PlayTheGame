package ecom.jonas.managed;

import java.util.ArrayList;

import javax.ejb.EJB;
import javax.faces.component.html.HtmlDataTable;
import javax.faces.component.html.HtmlInputText;
import javax.faces.context.FacesContext;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import ecom.jonas.entity.Accessory;
import ecom.jonas.entity.Console;
import ecom.jonas.entity.Game;
import ecom.jonas.entity.Platform;
import ecom.jonas.entity.Product;
import ecom.jonas.session.SearchEngineRemote;

public class ListProduct
{
	@EJB
	SearchEngineRemote searchEngine;
	
	HtmlDataTable dataTable;
	private ArrayList<Platform> platforms;
	HtmlInputText inputTextKeyword; 
	String keyWords = "";
	private ArrayList<Product> listproducts;
	private long idPlatform=-1;
	private	String typeProduct="Product";
	private	String paramPrice=" ";
	
	// Home View:
	// 1 : List View
	// 2: Product View
	boolean listView = true;
	public  ListProduct()
	{   
		Connecction();
		listproducts= searchEngine.getProductsByOptions("");
	}
	public boolean getProductView()
	{
		return !this.listView;
	}
	
	public String changeToListView()
	{
		listView = true;
		return "homeClient";
	}
	
	public String changeToProductView()
	{
		listView = false;
		return "homeClient";
	}
	
	public ArrayList<Product> getAllProducts()
	{
		return searchEngine.getAllProduits();
	}

	public String getProductsByName()
	{   listproducts= searchEngine.getProductsByOptions((String)inputTextKeyword.getValue());
		return "";
	}
	public String getProductsByPlatform()	
	{   
		String param=FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("idPlatform");
		String paramTypeProduct=FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("TypeProduct");
		String paramPriceProduct=FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("paramPrice");
		
		System.out.println("idPlatform="+param);
		if(!(param.equals("")||param.equals(null))){
			idPlatform = Long.parseLong(param);
		}	
		if(!(paramTypeProduct.equals("")||paramTypeProduct.equals(null))){
			typeProduct = paramTypeProduct;
		}	
		if(!(paramPriceProduct.equals("")||paramPriceProduct.equals(null))){
			paramPrice = paramPriceProduct;
		}	
		listproducts= searchEngine.getProductsByCriterias((String)inputTextKeyword.getValue(),idPlatform,typeProduct,paramPrice);
		return "";
	}
	
	public ArrayList<Game> getGamesByOptions()
	{
		return searchEngine.getGamesByOptions((String)inputTextKeyword.getValue());
	}
	
	public ArrayList<Console> getConsolesByOptions()
	{
		return searchEngine.getConsolesByOptions((String)inputTextKeyword.getValue());
	}
	
	public ArrayList<Accessory> getAccessoriesByOptions()
	{
		return searchEngine.getAccessoriesByOptions((String)inputTextKeyword.getValue());
	}
	
	public String doOKKeyword()
	{
		keyWords = (String) inputTextKeyword.getValue();
		return "homeClient";
	}
	
	public HtmlDataTable getDataTable() {
		return dataTable;
	}

	public void setDataTable(HtmlDataTable dataTable) {
		this.dataTable = dataTable;
	}

	public HtmlInputText getInputTextKeyword() {
		return inputTextKeyword;
	}

	public void setInputTextKeyword(HtmlInputText inputTextKeyword) {
		this.inputTextKeyword = inputTextKeyword;
	}

	public String getKeyWords() {
		return keyWords;
	}

	public void setKeyWords(String keyWords) {
		this.keyWords = keyWords;
	}

	public boolean getListView() {
		return listView;
	}

	public void setListView(boolean listView) {
		this.listView = listView;
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

			searchEngine = (SearchEngineRemote) initialContext.lookup("SearchEngineBean");
			System.out.println("I can get AdminBean ");
		} catch (NamingException e) {
			// TODO Auto-generated catch blockCannot
			System.out.println("cannot get AdminBean");
		}
	}

	/**
	 * @param platforms the platforms to set
	 */
	public void setPlatforms(ArrayList<Platform> platforms) {
		this.platforms = platforms;
	}

	/**
	 * @return the platforms
	 */
	public ArrayList<Platform> getPlatforms() {
		return searchEngine.getAllPlatforms();
	}

	/**
	 * @param listproducts the listproducts to set
	 */
	public void setListproducts(ArrayList<Product> listproducts) {
		this.listproducts = listproducts;
	}

	/**
	 * @return the listproducts
	 */
	public ArrayList<Product> getListproducts() {
		return listproducts;
	}
}
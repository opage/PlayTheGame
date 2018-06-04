package ecom.jonas.session;

import java.util.ArrayList;

import javax.ejb.Remote;


import ecom.jonas.entity.Platform;

import ecom.jonas.entity.Accessory;
import ecom.jonas.entity.Console;
import ecom.jonas.entity.Game;
import ecom.jonas.entity.Type;

import ecom.jonas.entity.Product;

@Remote
public interface SearchEngineRemote {
	
	public ArrayList<Product> getAllProduits();
	public ArrayList<Platform> getAllPlatforms();
	public ArrayList<Product> getProductsByOptions(String keyWords);

	public ArrayList<Product> getGamesByCriterias(String keyWords,
			Long idPlatform,Long idType,String paramPrice);
	public ArrayList<Product> getConsolesByCriterias(String keyWords,Long idPlatform,String paramPrice);
	
	public ArrayList<Game> getGamesByOptions(String keyWords);
	
	public ArrayList<Console> getConsolesByOptions(String keyWords);
	
	public ArrayList<Accessory> getAccessoriesByOptions(String keyWords);
	public ArrayList<Type> getAllTypes();
	public ArrayList<Product> getProductsByCriterias(String keyWords, Long idPlatform,String TypeProduct,String paramPrice);
	public ArrayList<Product> getAllConsoles();
	public ArrayList<Product> getAllGames();
	public ArrayList<Product> getAllAccessorys();
	public ArrayList<Product> getAccessorysByCriterias(String keyWords,
			Long idPlatform, String paramPrice);	
	public boolean isExist(String emailClient);
	

}

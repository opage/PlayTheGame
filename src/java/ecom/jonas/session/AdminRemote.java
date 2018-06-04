package ecom.jonas.session;

import java.util.Collection;
import java.util.Date;
import java.util.Vector;

import javax.ejb.Remote;

import ecom.jonas.entity.Accessory;
import ecom.jonas.entity.Administrator;
import ecom.jonas.entity.Console;
import ecom.jonas.entity.Customer;
import ecom.jonas.entity.Game;
import ecom.jonas.entity.Platform;
import ecom.jonas.entity.Product;
import ecom.jonas.entity.Type;
//import ecom.managed.PlatformDTO;

@Remote
public interface AdminRemote {
	// *********************Methods Of
	// Product************************************
	public void createGame(
			// Attributs de Product (superClass)
			String nameProduct, String descriptionProduct, Double priceProduct,
			Integer quantityProduct, Integer discountProduct,
			String urlImageProduct, String urlVideoProduct,
			Date releaseDateProduct,
			// Attributs de Game (sousClass)
			String ratingProduct, String developerProduct,
			String editorProduct,
			// Platform
			Platform platform,
			// Collections Types
			Collection<Type> types);
	public void createConsole(String nameProduct, String descriptionProduct,
			Double priceProduct, Integer quantityProduct,
			Integer discountProduct, String urlImageProduct,
			String urlVideoProduct, Date releaseDateProduct,
			String hardDriveConsole, Double weightConsole, Platform platform);
	void createAccessory(String nameProduct, String descriptionProduct,
			Double priceProduct, Integer quantityProduct,
			Integer discountProduct, String urlImageProduct,
			String urlVideoProduct, Date releaseDateProduct, Platform platform);
	public boolean updateGame(Game game);
	boolean updateConsole(Console ConsoleEdit);
	boolean updateAccessory(Accessory accessoryEdit);
	public void createGame(Game game);
	public void createAccessory(Accessory accessory);
	void createConsole(Console console);
	public boolean removeProduct(Long idProduct);
	public Game getGameById(int idProduct);
	public Console getConsoleById(int idProduct);
	public Accessory getAccessoryById(int idProduct);
	public Product getProductById(int idProduct);
	public Collection<Product> getProductByType(double price,long idType);
	public Collection<Game> getGamesByCriteria (String nameGame,Long idPlatorm,Long idType);
	public Collection<Console> getConsolesByCriteria(String nameGame, Long idPlatorm);
	public Collection<Accessory> getAccessoryByCriteria(String nameGame, Long idPlatorm);
	// ********************* Methods Of
	// Platform************************************
	public void createPlatform(String namePlatform, String descriptionPlatform);
	public void createPlatform(Platform platform);
	public boolean removePlatform(Long idPlatform);

	public Platform getPlatformById(int idPlatform);

	public Collection<Platform> getAllPlatforms();
	public boolean updatePlatform(Platform platform);
	Collection<Platform> getPlatformByName(String namePlatform);
	//public Vector<PlatformDTO>getAllPlatformsToVector();

	// ********************* Methods Of
	// TypeGames************************************
	public void createType(String nameType, String descriptionType);
	public boolean updateType(Type type);
	public boolean removeType(Long idType);
	Collection<Type> getTypeByName(String nameType);
	public Type getTypeById(int idType);

	public Collection<Type> getAllTypes();
	// ********************* Methods Of Admin************************************
	public void creatAdmin(String loginAdmin,String passwordAdmin);
	public void removeAllAdmins();
	public Administrator getAdminByLP(String loginAdmin,String passwordAdmin);
	Collection<Customer> getCustomersByName(String nameCustomer);
	void Crediter(long idAccount, double balanceAccount);
	void Debiter(long idAccount, double balanceAccount);
	boolean removeCustomer(Long idCustomer);
	
	
	
	
	
	
	
	
	
	


	
}
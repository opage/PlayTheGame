package ecom.jonas.session;

import java.util.Collection;
import java.util.Date;
import java.util.Vector;

import javax.ejb.Remote;
import javax.ejb.Stateful;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import ecom.jonas.entity.Accessory;
import ecom.jonas.entity.Account;
import ecom.jonas.entity.Administrator;
import ecom.jonas.entity.Console;
import ecom.jonas.entity.Customer;
import ecom.jonas.entity.Game;
import ecom.jonas.entity.Platform;
import ecom.jonas.entity.Product;
import ecom.jonas.entity.Type;

//import ecom.managed.PlatformDTO;

/**
 * Session Bean implementation class AdminBean
 */
@Stateful(mappedName = "AdminBean")
@Remote(AdminRemote.class)
public class AdminBean implements AdminRemote {
	@PersistenceContext
	private EntityManager em;

	/**
	 * Default constructor.
	 */
	public AdminBean() {
		// TODO Auto-generated constructor stub
	}

	// *********************Methods Of
	// Product************************************
	@Override
	public void createGame(String nameProduct, String descriptionProduct,
			Double priceProduct, Integer quantityProduct,
			Integer discountProduct, String urlImageProduct,
			String urlVideoProduct, Date releaseDateProduct,
			String ratingProduct, String developerProduct,
			String editorProduct, Platform platform, Collection<Type> types) {
		// TODO Auto-generated method stub
		Game game = new Game();
		game.setNameProduct(nameProduct);
		game.setDescriptionProduct(descriptionProduct);
		game.setPriceProduct(priceProduct);
		game.setQuantityProduct(quantityProduct);
		game.setDiscountProduct(discountProduct);
		game.setUrlImageProduct(urlImageProduct);
		game.setUrlVideoProduct(urlVideoProduct);
		game.setReleaseDateProduct(releaseDateProduct);
		game.setRatingProduct(ratingProduct);
		game.setDeveloperProduct(developerProduct);
		game.setEditorProduct(editorProduct);
		game.setPlatform(platform);
		game.setTypes(types);

		// **********************persister l'objet*******************
		game = em.merge(game);
		em.persist(game);

	}
	@Override
	public void createGame(Game game)
	{  game = em.merge(game);
		em.persist(game);
	}
	@Override
	public void createAccessory(String nameProduct, String descriptionProduct,
			Double priceProduct, Integer quantityProduct,
			Integer discountProduct, String urlImageProduct,
			String urlVideoProduct, Date releaseDateProduct, Platform platform) {
		// TODO Auto-generated method stub
		Accessory accessory = new Accessory();
		accessory.setNameProduct(nameProduct);
		accessory.setDescriptionProduct(descriptionProduct);
		accessory.setPriceProduct(priceProduct);
		accessory.setQuantityProduct(quantityProduct);
		accessory.setDiscountProduct(discountProduct);
		accessory.setUrlImageProduct(urlImageProduct);
		accessory.setUrlVideoProduct(urlVideoProduct);
		accessory.setReleaseDateProduct(releaseDateProduct);
		accessory.setPlatform(platform);
		
		accessory = em.merge(accessory);
		em.persist(accessory);		
	}

	@Override
	public void createAccessory(Accessory accessory) {
		// TODO Auto-generated method stub
		accessory = em.merge(accessory);
		em.persist(accessory);		
	}

	@Override
	public void createConsole(String nameProduct, String descriptionProduct,
			Double priceProduct, Integer quantityProduct,
			Integer discountProduct, String urlImageProduct,
			String urlVideoProduct, Date releaseDateProduct,
			String hardDriveConsole, Double weightConsole, Platform platform) {
		 
		//TODO Auto-generated method stub
		Console console = new Console();
		console.setNameProduct(nameProduct);
		console.setDescriptionProduct(descriptionProduct);
		console.setPriceProduct(priceProduct);
		console.setQuantityProduct(quantityProduct);
		console.setDiscountProduct(discountProduct);
		console.setUrlImageProduct(urlImageProduct);
		console.setUrlVideoProduct(urlVideoProduct);
		console.setReleaseDateProduct(releaseDateProduct);
		console.setHardDriveConsole(hardDriveConsole);
		console.setWeightConsole(weightConsole);	
		console.setPlatform(platform);
		
		console = em.merge(console);
		em.persist(console);
	}

	@Override
	public void createConsole(Console console) {
		// TODO Auto-generated method stub
		console = em.merge(console);
		em.persist(console);
	}
	@Override
	
	public boolean removeProduct(Long idProduct) {
		
		Product product = (Product) em.find(Product.class, idProduct);
		em.remove(product);
		return true;
			
			

	}
	
	@Override
	public boolean updateGame(Game gameEdit) {
	
		gameEdit = em.merge(gameEdit);
		em.persist(gameEdit);
        return true;
	}
	@Override
	public boolean updateConsole(Console ConsoleEdit) {
		// TODO Auto-generated method stub
		ConsoleEdit = em.merge(ConsoleEdit);
		em.persist(ConsoleEdit);
        return true;
	
	}
	@Override
	public boolean updateAccessory(Accessory accessoryEdit) {
		accessoryEdit = em.merge(accessoryEdit);
		em.persist(accessoryEdit);
        return true;
	}
	
	@Override
	public Product getProductById(int idProduct) {
		Query query = em.createQuery("Product.FindProductByID");
		query.setParameter("param", new Long(idProduct));
		try {
			Product productResult = (Product) query.getSingleResult();
			em.clear();
			return productResult;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			em.clear();
			return null;
			
		}
	
	}
@TransactionAttribute(value=TransactionAttributeType.SUPPORTS)
	@Override
	public Game getGameById(int idProduct){
		Query query = em.createQuery("SELECT OBJECT(g) FROM Game g WHERE g.idProduct = :param");

		query.setParameter("param", new Long(idProduct));
		try {
			Game productResult = (Game) query.getSingleResult();
			em.clear();
			return productResult;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			em.clear();
			return null;
		}
		
	}
@TransactionAttribute(value=TransactionAttributeType.SUPPORTS)
@Override
public Console getConsoleById(int idProduct){
	Query query = em.createQuery("SELECT OBJECT(g) FROM Console g WHERE g.idProduct = :param");

	query.setParameter("param", new Long(idProduct));
	try {
		Console productResult = (Console) query.getSingleResult();
		em.clear();
		return productResult;
	} catch (Exception e) {
		// TODO Auto-generated catch block
		em.clear();
		return null;
	}
	
}
@TransactionAttribute(value=TransactionAttributeType.SUPPORTS)
@Override
public Accessory getAccessoryById(int idProduct){
	Query query = em.createQuery("SELECT OBJECT(g) FROM Accessory g WHERE g.idProduct = :param");

	query.setParameter("param", new Long(idProduct));
	try {
		Accessory productResult = (Accessory) query.getSingleResult();
		em.clear();
		return productResult;
	} catch (Exception e) {
		// TODO Auto-generated catch block
		em.clear();
		return null;
	}
	
}
	@SuppressWarnings("unchecked")
	@Override
	public Collection<Game> getGamesByCriteria (String nameGame,Long idPlatorm,Long idType){
		
		String queryString ="",coreSqlPlatform=" ",paramSqlPlatform=" ",coreSqlType=" ",paramSqlType=" ";
		
		// if pltform =ALL or is identifyed
		if(idPlatorm<0 ){//Criteria is  allPlatfomes
			coreSqlPlatform=" ";
			paramSqlPlatform=" ";
		}
			else {//	Criteria Platform identify exp Platform =PS3
				coreSqlPlatform=" JOIN g.platform p ";	
				paramSqlPlatform=" and p.idPlatform=:param2 ";
			}
		// if Type =ALLType or is identifyed
		if(idType<0 ){//Criteria is  allTypes
			coreSqlType=" ";
			paramSqlType=" ";
		}
			else {//	Criteria Type identify exp Type =Sport
				coreSqlType=" JOIN g.types t ";	
				paramSqlType=" and t.idType=:param3 ";
			}	
		queryString="SELECT OBJECT(g) FROM Game g "+coreSqlPlatform+ coreSqlType+
		            "  where g.nameProduct like :param1 "+ paramSqlPlatform+ paramSqlType;
		System.out.println(queryString +" param1="+nameGame+"  param2="+idPlatorm+" param3="+idType);
		Query query = em.createQuery(queryString);
		query.setParameter("param1", "%"+nameGame+ "%");
		if(idPlatorm>=0 )//Criteria Platform identify exp Platform =PS3
			query.setParameter("param2", idPlatorm);	
		if(idType>=0 )//Criteria Type identify exp Type =Course
			query.setParameter("param3", idType);	
		
		try {
			return (Collection<Game>) query.getResultList();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			return null;
		}
	}
	@SuppressWarnings("unchecked")
	@Override
	public Collection<Console> getConsolesByCriteria (String nameGame,Long idPlatorm){
		
		String queryString ="",coreSqlPlatform=" ",paramSqlPlatform=" ",coreSqlType=" ",paramSqlType=" ";
		
		// if pltform =ALL or is identifyed
		if(idPlatorm<0 ){//Criteria is  allPlatfomes
			coreSqlPlatform=" ";
			paramSqlPlatform=" ";
		}
			else {//	Criteria Platform identify exp Platform =PS3
				coreSqlPlatform=" JOIN g.platform p ";	
				paramSqlPlatform=" and p.idPlatform=:param2 ";
			}
		// if Type =ALLType or is identifyed
		
			
		queryString="SELECT OBJECT(g) FROM Console g "+coreSqlPlatform+ coreSqlType+
		            "  where g.nameProduct like :param1 "+ paramSqlPlatform+ paramSqlType;
		System.out.println(queryString +" param1="+nameGame+"  param2="+idPlatorm);
		Query query = em.createQuery(queryString);
		query.setParameter("param1", "%"+nameGame+ "%");
		if(idPlatorm>=0 )//Criteria Platform identify exp Platform =PS3
			query.setParameter("param2", idPlatorm);	
		
		
		try {
			return (Collection<Console>) query.getResultList();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			return null;
		}
	}
	@SuppressWarnings("unchecked")
	@Override
	public Collection<Accessory> getAccessoryByCriteria (String nameGame,Long idPlatorm){
		
		String queryString ="",coreSqlPlatform=" ",paramSqlPlatform=" ",coreSqlType=" ",paramSqlType=" ";
		
		// if pltform =ALL or is identifyed
		if(idPlatorm<0 ){//Criteria is  allPlatfomes
			coreSqlPlatform=" ";
			paramSqlPlatform=" ";
		}
			else {//	Criteria Platform identify exp Platform =PS3
				coreSqlPlatform=" JOIN g.platform p ";	
				paramSqlPlatform=" and p.idPlatform=:param2 ";
			}
		// if Type =ALLType or is identifyed
		
			
		queryString="SELECT OBJECT(g) FROM Accessory g "+coreSqlPlatform+ coreSqlType+
		            "  where g.nameProduct like :param1 "+ paramSqlPlatform+ paramSqlType;
		System.out.println(queryString +" param1="+nameGame+"  param2="+idPlatorm);
		Query query = em.createQuery(queryString);
		query.setParameter("param1", "%"+nameGame+ "%");
		if(idPlatorm>=0 )//Criteria Platform identify exp Platform =PS3
			query.setParameter("param2", idPlatorm);	
		
		
		try {
			return (Collection<Accessory>) query.getResultList();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			return null;
		}
	}
	@SuppressWarnings("unchecked")
	@Override
	public Collection<Product> getProductByType(double price, long idType) {
		// Query query =
		// em.createQuery("SELECT OBJECT(p) FROM Game p WHERE p.priceProduct <= :param1 ,in (p.Type) AS t WHERE t.idType =:param2"
		// );
		Query query = em
				.createQuery("SELECT OBJECT(p) FROM Game p JOIN p.types t WHERE  p.priceProduct <= :param1  and t.idType =:param2");
		query.setParameter("param1", price);
		query.setParameter("param2", idType);

		try {
			Collection<Product> productResult = query.getResultList();
			return productResult;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			return null;
		}
	}

	// ********************* Methods Of
	// Platform************************************
	@Override
	public void createPlatform(String namePlatform, String descriptionPlatform) {
		// TODO Auto-generated method stub
		Platform platform = new Platform();

		platform.setNamePlatform(namePlatform);
		platform.setDescriptionPlatform(descriptionPlatform);
		em.persist(platform);

	}

	@Override
	public void createPlatform(Platform platform) {
		// TODO Auto-generated method stub
		em.persist(platform);
	}

	@Override
	public boolean removePlatform(Long idPlatform) {
		Platform platform = (Platform) em.find(Platform.class, idPlatform);
		if (platform != null) {
			em.remove(platform);
			return true; // success
		} else {
			return false; // failure
		}

	}

	@Override
	public Platform getPlatformById(int idPlatform) {
		// TODO Auto-generated method stub
		Query query = em
				.createQuery("SELECT OBJECT(p) FROM Platform p WHERE p.idPlatform = :param");

		query.setParameter("param", new Long(idPlatform));
		try {
			Platform platformResult = (Platform) query.getSingleResult();

			return platformResult;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public Collection<Platform> getPlatformByName(String namePlatform) {
		// TODO Auto-generated method stub
		Query query = em
				.createQuery("SELECT OBJECT(p) FROM Platform p WHERE p.namePlatform like :param");

		query.setParameter("param", namePlatform + "%");
		try {
			Collection<Platform> results = query.getResultList();

			return results;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public Collection<Platform> getAllPlatforms() {
		Query query = em.createQuery("SELECT OBJECT(p) FROM Platform p ");

		Collection<Platform> platforms = query.getResultList();
		return platforms;

	}

	@Override
	public boolean updatePlatform(Platform platform) {
		// TODO Auto-generated method stub
		Platform p = (Platform) em.find(Platform.class, platform
				.getIdPlatform());

		p.setNamePlatform(platform.getNamePlatform());
		p.setDescriptionPlatform(platform.getDescriptionPlatform());

		return true;
	}

	/*
	 * @SuppressWarnings("unchecked")
	 * 
	 * @Override public Vector<PlatformDTO> getAllPlatformsToVector() { Query
	 * query = em.createQuery("SELECT OBJECT(p) FROM Platform p ");
	 * 
	 * Collection<Platform> platforms = query.getResultList();
	 * Vector<PlatformDTO> result = new Vector<PlatformDTO>(); for (Platform
	 * platform : platforms) { PlatformDTO platformDTO = new PlatformDTO();
	 * platformDTO.setIdPlatform(platform.getIdPlatform());
	 * platformDTO.setNamePlatform(platform.getNamePlatform());
	 * platformDTO.setDescriptionPlatform(platform .getDescriptionPlatform());
	 * 
	 * result.add(platformDTO); } return result;
	 * 
	 * }
	 */
	// ********************* Methods Of TypeGames****************************

	@Override
	public void createType(String nameType, String descriptionType) {
		// TODO Auto-generated method stub
		Type type = new Type();

		type.setNameType(nameType);
		type.setDescriptionType(descriptionType);
		em.persist(type);
	}

	@SuppressWarnings("unchecked")
	@Override
	public Collection<Type> getAllTypes() {
		Query query = em.createQuery("SELECT OBJECT(t) FROM Type t");
		Collection<Type> types = query.getResultList();
		return types;
	}

	@Override
	public Type getTypeById(int idType) {
		// TODO Auto-generated method stub
		Query query = em
				.createQuery("SELECT OBJECT(t) FROM Type t WHERE t.idType=:param");
		query.setParameter("param", new Long(idType));
		Type type = (Type) query.getSingleResult();
		return type;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Collection<Type> getTypeByName(String nameType) {
		Query query = em
				.createQuery("SELECT OBJECT(p) FROM Type p WHERE p.nameType like :param");

		query.setParameter("param", nameType + "%");
		try {
			Collection<Type> results = query.getResultList();

			return results;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			return null;
		}
	}

	@Override
	public boolean removeType(Long idType) {
		Type type = (Type) em.find(Type.class, idType);
		if (type != null) {
			em.remove(type);
			return true; // success
		} else {
			return false; // failure
		}
	}

	@Override
	public boolean updateType(Type type) {
		Type t = (Type) em.find(Type.class, type.getIdType());
		t.setNameType(type.getNameType());
		t.setDescriptionType(type.getDescriptionType());

		return true;
	}

	// *********************Methods Of Admin********************************
	@Override
	public void creatAdmin(String loginAdmin, String passwordAdmin) {
		// TODO Auto-generated method stub
		Administrator admin = new Administrator();
		admin.setLoginAdmin(loginAdmin);
		admin.setPasswordAdmin(passwordAdmin);
		em.persist(admin);

	}

	@Override
	public Administrator getAdminByLP(String loginAdmin, String passwordAdmin) {
		// TODO Auto-generated method stub
		Query query = em
				.createQuery("SELECT OBJECT(a) FROM Administrator a WHERE a.loginAdmin=:param1 and a.passwordAdmin=:param2");
		query.setParameter("param1", loginAdmin);
		query.setParameter("param2", passwordAdmin);
		Administrator admin;
		try {
			admin = (Administrator) query.getSingleResult();
		} catch (Exception e) {
			System.out
					.println("No Admin with this params.The method is 'getAdminByLP(String loginAdmin, String passwordAdmin)'");
			return null;
		}
		return admin;
	}

	@SuppressWarnings("unchecked")
	@Override
	public void removeAllAdmins() {
		// TODO Auto-generated method stub
		Query query = em.createQuery("SELECT OBJECT(a) FROM Administrator a");
		Collection<Administrator> admins = query.getResultList();
		for (Administrator a : admins)
			em.remove(a);
	}
	@SuppressWarnings("unchecked")
	@Override
	public Collection<Customer> getCustomersByName(String nameCustomer) {
		Query query = em
				.createQuery("SELECT OBJECT(cus) FROM Customer cus WHERE cus.lastnameCustomer like :param");

		query.setParameter("param", nameCustomer + "%");
		try {
			Collection<Customer> results = query.getResultList();

			return results;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			return null;
		}
	}
	
	@Override
	public void Crediter(long  idAccount ,double balanceAccount ) {
		Account account = (Account) em.find(Account.class, idAccount);
		account.setBalanceAccount(account.getBalanceAccount()+balanceAccount);
		em.persist(account);
	}
	@Override
	public void Debiter(long  idAccount ,double balanceAccount ) {
		Account account = (Account) em.find(Account.class, idAccount);
		account.setBalanceAccount(account.getBalanceAccount()-balanceAccount);
		em.persist(account);
	}
@Override
	
	public boolean removeCustomer(Long idCustomer) {
	Customer customer = (Customer) em.find(Customer.class, idCustomer);
		em.remove(customer);
		return true;
	}

}

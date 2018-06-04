package ecom.jonas.session;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import ecom.jonas.entity.Account;
import ecom.jonas.entity.Administrator;
import ecom.jonas.entity.Comment;
import ecom.jonas.entity.Customer;
import ecom.jonas.entity.Platform;
import ecom.jonas.entity.Product;
import ecom.jonas.entity.ProductOrderList;
import ecom.jonas.entity.Type;


@Stateless(mappedName = "InitBean")
@Remote(InitRemote.class)
public class InitBean implements InitRemote {

	@PersistenceContext
	private EntityManager em;
	
	@Override
	public void init() {
		// TODO Auto-generated method stub
	
		Collection<Platform> platforms =  new ArrayList<Platform>();
		Platform plat1 =  new Platform("PS3","a 3rth playstation for hardgamers");
		platforms.add(plat1);
		Platform plat2 = new Platform("XBOX","a 3rth playstation for hardgamers");
		platforms.add(plat2);
		Platform plat3 = new Platform("PS2","a 2rth playstation for hardgamers");
		platforms.add(plat3);
		Platform plat4 = new Platform("Nintendo","a 1rth playstation for hardgamers");
		platforms.add(plat4);
		Platform plat5 = new Platform("PSOne","a 1rth playstation for hardgamers");
		platforms.add(plat5);			
		for (Platform platform : platforms)
		{
			em.persist(platform);
		}
		
		Collection<Type> types = new ArrayList<Type>();
		Type typ1 = new Type("Course","");
		types.add(typ1);
		Type typ2 = new Type("Action","");
		types.add(typ2);
		Type typ3 = new Type("War","");
		types.add(typ3);
		Type typ4 = new Type("Aventure","");
		types.add(typ4);
		for (Type type : types)
		{
			em.persist(type);
		}		
		
		//Game
//		Collection<Game> games = new ArrayList<Game>();
//		Game p1 = new Game("War craft III","Third theme of Strategy's Game");
//		p1.setPlatform(plat1);
//		Collection<Type> col1 = new ArrayList<Type>();
//		col1.add(typ2);
//		col1.add(typ3);
//		p1.setTypes(col1);
//		games.add(p1);
//		Game p2 = new Game("Super mario Bros","A game very famous");		
//		p2.setPlatform(plat2);
//		Collection<Type> col2 = new ArrayList<Type>();
//		col2.add(typ4);
//		p2.setTypes(col2);		
//		games.add(p2);
//		Game p3 = new Game("Need for speed II","A course's game");		
//		p3.setPlatform(plat3);
//		Collection<Type> col3 = new ArrayList<Type>();
//		col3.add(typ1);
//		p3.setTypes(col3);
//		games.add(p3);
//		Game p4 = new Game("Prince of persia","A adventure's game");	
//		p4.setPlatform(plat4);
//		Collection<Type> col4 = new ArrayList<Type>();
//		col4.add(typ4);
//		p4.setTypes(col4);
//		games.add(p4);		
//		for (Game Game : games)
//		{
//			em.persist(Game);
//		}
		
		//Product prod = new Product();		
		
		Collection<Product> products = new ArrayList<Product>();
		Product p1 = new Product("War craft III","Third theme of Strategy's Game");
		p1.setPlatform(plat1);
		products.add(p1);
		Product p2 = new Product("Super mario Bros","A game very famous");		
		p2.setPlatform(plat2);
		products.add(p2);
		Product p3 = new Product("Need for speed II","A course's game");		
		p3.setPlatform(plat3);
		products.add(p3);
		Product p4 = new Product("Prince of persia","A adventure's game");	
		p4.setPlatform(plat4);
		products.add(p4);		
		for (Product product : products)
		{
			em.persist(product);
		}
		
		Collection<Account> accounts = new ArrayList<Account>();
		Account acc1 = new Account();
		accounts.add(acc1);
		Account acc2 = new Account();
		accounts.add(acc2);
		Account acc3 = new Account();
		accounts.add(acc3);
		Account acc4 = new Account();
		accounts.add(acc4);
		Account acc5 = new Account();
		accounts.add(acc5);
		Account acc6 = new Account();
		accounts.add(acc6);
		for (Account account : accounts)
		{
			em.persist(account);
		}		
		
		Collection<Customer> customers = new ArrayList<Customer>();
		Customer c1 = new Customer("page","page");
		c1.setAccount(acc1, true);
		Collection<Product> colFav1 = new ArrayList<Product>();
		colFav1.add(p1);
		colFav1.add(p2);
		c1.setProductFavoriteList(colFav1);		
		customers.add(c1);		
		Customer c2 = new Customer("florent","florent");
		c2.setAccount(acc2, true);
		Collection<Product> colFav2 = new ArrayList<Product>();
		colFav2.add(p3);
		c2.setProductFavoriteList(colFav2);		
		customers.add(c2);
		Customer c3 = new Customer("aicha","aicha");
		c3.setAccount(acc3, true);
		Collection<Product> colFav3 = new ArrayList<Product>();
		colFav3.add(p2);
		colFav3.add(p4);
		c3.setProductFavoriteList(colFav3);	
		customers.add(c3);
		Customer c4 = new Customer("nyugen","nyugen");
		c4.setAccount(acc4, true);
		Collection<Product> colFav4 = new ArrayList<Product>();
		colFav4.add(p4);
		c4.setProductFavoriteList(colFav4);		
		customers.add(c4);
		Customer c5 = new Customer("nabil","nabil");
		c5.setAccount(acc5, true);
		Collection<Product> colFav5 = new ArrayList<Product>();
		colFav5.add(p3);
		c5.setProductFavoriteList(colFav5);		
		customers.add(c5);
		Customer c6 = new Customer("guest","guest");
		c6.setAccount(acc6, true);		
		customers.add(c6);
		for (Customer customer : customers)
		{
			em.persist(customer);
		}
		
		Collection<Comment> comments = new ArrayList<Comment>();
		Comment co1 = new Comment("Good game");
		co1.setCustomer(c2);
		co1.setProduct(p1);
		co1.setDateComment(new Date());
		comments.add(co1);
		Comment co2 = new Comment("Excellent game olivier :)");
		co2.setCustomer(c1);
		co2.setProduct(p2);
		co2.setDateComment(new Date());
		comments.add(co2);
		Comment co3 = new Comment("it's the game");
		co3.setCustomer(c5);
		co3.setProduct(p3);
		co3.setDateComment(new Date());
		comments.add(co3);	
		for (Comment comment : comments)
		{
			em.persist(comment);
		}
		
		Collection <ProductOrderList> productorderlists = new ArrayList<ProductOrderList>();
		ProductOrderList pol1 = new ProductOrderList(null, p1, 4);
		productorderlists.add(pol1);
		ProductOrderList pol2 = new ProductOrderList(null, p2, 10);
		productorderlists.add(pol2);
		ProductOrderList pol3 = new ProductOrderList(null, p3, 9);
		productorderlists.add(pol3);
		ProductOrderList pol4 = new ProductOrderList(null, p4, 2);
		productorderlists.add(pol4);
		for (ProductOrderList productorderlist : productorderlists)
		{
			em.persist(productorderlist);
		}
		
		Administrator admin = new Administrator("admin","admin");
		em.persist(admin);
	}
}

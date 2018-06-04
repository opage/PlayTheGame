package ecom.jonas.session;

import java.util.ArrayList;
import java.util.Collection;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;


import ecom.jonas.entity.Game;
import ecom.jonas.entity.Platform;

import ecom.jonas.entity.Accessory;
import ecom.jonas.entity.Console;
import ecom.jonas.entity.Game;
import ecom.jonas.entity.Type;

import ecom.jonas.entity.Product;

@Stateless(mappedName = "SearchEngineBean")
@Remote(SearchEngineRemote.class)
public class SearchEngineBean implements SearchEngineRemote{
	
	@PersistenceContext
	private EntityManager em;

	@SuppressWarnings("unchecked")
	@Override
	public ArrayList<Product> getAllProduits() {
		Query query = em.createQuery("select p from Product p");
		return (ArrayList<Product>) query.getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public ArrayList<Product> getProductsByOptions(String keyWords) {
		String queryString = "select p from Product p";		
		if (!keyWords.equals(""))
		{
			queryString += " where p.nameProduct like \'%" + keyWords + "%\'";
		}		
		Query query = em.createQuery(queryString);
		return (ArrayList<Product>) query.getResultList();
	}
	@SuppressWarnings("unchecked")
	@Override
	// get product pour l'interface Home
	public ArrayList<Product> getProductsByCriterias(String keyWords,Long idPlatform,String TypeProduct,String paramPrice) {
		String queryString = "SELECT OBJECT(p)FROM "+TypeProduct+"  p JOIN p.platform g ";
		String paramSqlPlatform=" ";
		
			queryString += "where p.nameProduct like :param1 ";
		
		if(idPlatform>=0)
		{
			paramSqlPlatform=" and g.idPlatform =:param2 ";
		}
		System.out.println("SQL="+queryString+paramSqlPlatform+" "+paramPrice);
		Query query = em.createQuery(queryString+paramSqlPlatform+" "+paramPrice );
		query.setParameter("param1",keyWords+ "%");
		if(idPlatform>=0)
		query.setParameter("param2", idPlatform);
		try {
			return (ArrayList<Product>) query.getResultList();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("Exception="+e);
			return null;
		}
		 
	}
	@SuppressWarnings("unchecked")
	@Override
	// get product pour l'interface Console
	public ArrayList<Product> getConsolesByCriterias(String keyWords,Long idPlatform,String paramPrice) {
		String queryString = "SELECT OBJECT(p)FROM Console  p JOIN p.platform g ";
		String paramSqlPlatform=" ";
		
			queryString += "where p.nameProduct like :param1 ";
		
		if(idPlatform>=0)
		{
			paramSqlPlatform=" and g.idPlatform =:param2 ";
		}
		System.out.println("SQL="+queryString+paramSqlPlatform+" "+paramPrice);
		Query query = em.createQuery(queryString+paramSqlPlatform+" "+paramPrice );
		query.setParameter("param1",keyWords+ "%");
		if(idPlatform>=0)
		query.setParameter("param2", idPlatform);
		try {
			return (ArrayList<Product>) query.getResultList();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("Exception="+e);
			return null;
		}
		 
	}
	@SuppressWarnings("unchecked")
	@Override
	// get product pour l'interface Console
	public ArrayList<Product> getAccessorysByCriterias(String keyWords,Long idPlatform,String paramPrice) {
		String queryString = "SELECT OBJECT(p)FROM Accessory  p JOIN p.platform g ";
		String paramSqlPlatform=" ";
		
			queryString += "where p.nameProduct like :param1 ";
		
		if(idPlatform>=0)
		{
			paramSqlPlatform=" and g.idPlatform =:param2 ";
		}
		System.out.println("SQL="+queryString+paramSqlPlatform+" "+paramPrice);
		Query query = em.createQuery(queryString+paramSqlPlatform+" "+paramPrice );
		query.setParameter("param1",keyWords+ "%");
		if(idPlatform>=0)
		query.setParameter("param2", idPlatform);
		try {
			return (ArrayList<Product>) query.getResultList();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("Exception="+e);
			return null;
		}
		 
	}
	@SuppressWarnings("unchecked")
	@Override
	public ArrayList<Product> getGamesByCriterias(String keyWords,Long idPlatform,Long idType,String paramPrice) {
         String queryString ="",coreSqlPlatform=" ",paramSqlPlatform=" ",coreSqlType=" ",paramSqlType=" ";
		
		// if pltform =ALL or is identifyed
		if(idPlatform<0 ){//Criteria is  allPlatfomes
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
		System.out.println(queryString +" param1="+keyWords+"  param2="+idPlatform+" param3="+idType);
		
		
		try {
			Query query = em.createQuery(queryString +" "+paramPrice);
			query.setParameter("param1", keyWords+ "%");
			if(idPlatform>=0 )//Criteria Platform identify exp Platform =PS3
				query.setParameter("param2", idPlatform);	
			if(idType>=0 )//Criteria Type identify exp Type =Course
				query.setParameter("param3", idType);	
			return (ArrayList<Product>) query.getResultList();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("Exception e="+ e);
			return null;
		}
		 
	}
	@SuppressWarnings("unchecked")
	@Override
	public ArrayList<Platform> getAllPlatforms() {
		Query query = em.createQuery("SELECT OBJECT(p) FROM Platform p ");

		 
		return  (ArrayList<Platform>) query.getResultList();

	}
	@SuppressWarnings("unchecked")
	@Override
	public ArrayList<Type> getAllTypes() {
		Query query = em.createQuery("SELECT OBJECT(t) FROM Type t ");

		 
		return  (ArrayList<Type>) query.getResultList();

	}
	@SuppressWarnings("unchecked")
	@Override
	public ArrayList<Game> getGamesByOptions(String keyWords) {		
		// TODO Auto-generated method stub
		String queryString = "select p from Game p";		
		if (!keyWords.equals(""))
		{
			queryString += " where p.nameProduct like \'%" + keyWords + "%\'";
		}		
		Query query = em.createQuery(queryString);
		return (ArrayList<Game>) query.getResultList();
	}
	
	@SuppressWarnings("unchecked")
	public ArrayList<Console> getConsolesByOptions(String keyWords) {		
		// TODO Auto-generated method stub
		String queryString = "select p from Console p";		
		if (!keyWords.equals(""))
		{
			queryString += " where p.nameProduct like \'%" + keyWords + "%\'";
		}		
		Query query = em.createQuery(queryString);
		return (ArrayList<Console>) query.getResultList();
	}
	// get all console pour l'interface Console first setup
	@SuppressWarnings("unchecked")
	@Override
	public ArrayList<Product> getAllConsoles() {		
		// TODO Auto-generated method stub
		String queryString = "select p from Console p";		
		
		Query query = em.createQuery(queryString);
		return (ArrayList<Product>) query.getResultList();
	}
	// get all Accessorys pour l'interface Accessorys first setup
	@SuppressWarnings("unchecked")
	@Override
	public ArrayList<Product> getAllAccessorys() {		
		// TODO Auto-generated method stub
		String queryString = "select p from Accessory p";		
		
		Query query = em.createQuery(queryString);
		return (ArrayList<Product>) query.getResultList();
	}
	// get all games pour l'interface jeux video first setup
	@SuppressWarnings("unchecked")
	@Override
	public ArrayList<Product> getAllGames() {		
		// TODO Auto-generated method stub
		String queryString = "select p from Game p";		
		
		Query query = em.createQuery(queryString);
		return (ArrayList<Product>) query.getResultList();
	}
	@SuppressWarnings("unchecked")
	public ArrayList<Accessory> getAccessoriesByOptions(String keyWords) {		
		// TODO Auto-generated method stub
		String queryString = "select p from Accessory p";		
		if (!keyWords.equals(""))
		{
			queryString += " where p.nameProduct like \'%" + keyWords + "%\'";
		}		
		Query query = em.createQuery(queryString);
		return (ArrayList<Accessory>) query.getResultList();
	}

	@Override
	public boolean isExist(String emailClient) {
		
		String queryString = "from Customer as cus where cus.emailCustomer = \'" + emailClient + "\'";
		Query query = em.createQuery(queryString);
		
		return !query.getResultList().isEmpty();
	}

}

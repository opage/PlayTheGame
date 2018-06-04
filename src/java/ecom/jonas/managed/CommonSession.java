package ecom.jonas.managed;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;

import javax.ejb.EJB;
import javax.faces.component.html.HtmlDataTable;

import com.icesoft.faces.component.ext.HtmlInputSecret;
import com.icesoft.faces.component.ext.HtmlInputText;
import com.icesoft.faces.component.ext.HtmlInputTextarea;
import com.icesoft.faces.component.ext.HtmlOutputText;
import com.icesoft.faces.component.ext.HtmlPanelGroup;
import com.icesoft.faces.component.paneltabset.PanelTabSet;

import ecom.jonas.entity.Account;
import ecom.jonas.entity.Address;
import ecom.jonas.entity.Customer;
import ecom.jonas.entity.Product;
import ecom.jonas.entity.ProductOrderList;
import ecom.jonas.session.AdminRemote;
import ecom.jonas.session.CartRemote;
import ecom.jonas.session.CustomerRemote;
import ecom.jonas.session.SearchEngineRemote;

public class CommonSession
{
//	@EJB
//	static CustomerRemote customerBean;

	@EJB
	CustomerRemote customerBean;

	
	@EJB
	AdminRemote admin;
	
	@EJB
	SearchEngineRemote searchEngine;
	
	HtmlPanelGroup groupComment;
	
	// Home View
	// 1 : list of products
	// 2 : inscription
	// 3 : login view
	// 4 : signup success
	// 5 : login accepted
	int homeView = 1; // list of product by defaut
	
	// Data Customer Input
	HtmlInputText emailNewCus;
	HtmlInputSecret passwordNewCus;
	HtmlInputSecret confirmPasswordNewCus;
	HtmlInputText firstnameNewCus;
	HtmlInputText lastnameNewCus;
	HtmlInputText birthdayNewCus;
	HtmlInputText homePhoneNewCus;
	HtmlInputText mobilePhoneNewCus;
	
	String megError = "";
	
	// Data Login Customer
	HtmlInputText emailLoginCus;
	HtmlInputSecret passworLoginCus;
	
	HtmlOutputText loginMessageError;
	
	boolean isLoggedSuccess = false;
		
	public String login()
	{
		panelTabSet.setSelectedIndex(0);
		
		emailLoginCus.setValue("");
		passworLoginCus.setValue("");
		
		homeView = 3;
		return "homeClient";
	}
		
	public String loginValidate()
	{
		loginMessageError.setValue("");
		boolean loggedin = customerBean.loginCustomer((String) emailLoginCus.getValue(), (String) passworLoginCus.getValue() );
		
		if (loggedin)
		{
			isLoggedSuccess = true;
			homeView = 5;
		}
		else
		{
			loginMessageError.setValue("Sorry, something happens, plz try again ..");
		}
		return "homeClient";
	}
	
	public String logout()
	{

		customerBean.logoutCustomer();
		return "homeClient";
	}
	
	public String signup()
	{
		// Inscription
		panelTabSet.setSelectedIndex(0);
		
		emailNewCus.setValue("");
		passwordNewCus.setValue("");
		confirmPasswordNewCus.setValue("");
		firstnameNewCus.setValue("");
		lastnameNewCus.setValue("");
		birthdayNewCus.setValue("");
		homePhoneNewCus.setValue("");
		mobilePhoneNewCus.setValue("");
		
		homeView = 2;
		return "homeClient";
	}
	
	public String signupValidate() throws ParseException
	{
		megError = "";
		
		if ( searchEngine.isExist((String) emailNewCus.getValue()) )
		{
			megError = "Le mail existe deja dans la base";
			return "homeClient";
		}
		
		Customer client = new Customer();
		client.setEmailCustomer((String) emailNewCus.getValue());
		client.setPasswordCustomer((String) passwordNewCus.getValue());
		client.setFirstnameCustomer((String) firstnameNewCus.getValue());
		client.setLastnameCustomer((String) lastnameNewCus.getValue());
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/mm/yyyy");
		client.setBirthdayCustomer(dateFormat.parse((String) birthdayNewCus.getValue()));
		
		client.setTelFixCustomer((String) homePhoneNewCus.getValue());
		client.setTelMobilCustomer((String) mobilePhoneNewCus.getValue());
		
		Account acc = new Account();
		acc.setBalanceAccount(0);
		
		client.setAccount(acc, true);
		
		customerBean.addCustomer(client);

		// Inscription
		homeView = 4;
		return "homeClient";
	}
	
	public boolean isConnected()
	{
		return customerBean.isConnected();
	}
	
	public void cartSelected()
	{
		selectedIndex = panelTabSet.getSelectedIndex();
		System.out.printf(" ====== index = " + selectedIndex);
		panelTabSet.setSelectedIndex(selectedIndex);
	}
	
	public boolean isSignup() {
		// true if signup and Acceuil est chosit
		return (homeView == 2) && (panelTabSet.getSelectedIndex() == 0);
	}
	
	public boolean isSignupValidate() {
		// true if signupValidate and Acceuil est chosit
		return (homeView == 4) && (panelTabSet.getSelectedIndex() == 0);
	}
	
	public boolean isProductListView() {
		// List of product
		return homeView == 1;
	}
	
	public boolean isLoginClientView() {
		// List of product
		return homeView == 3;
	}
	
	public boolean isLoggedView() {
		// List of product
		return homeView == 5;
	}
		
	public String changeToHomeView()
	{
		homeView = 1;
		return "homeClient";
	}

	public HtmlInputText getEmailNewCus() {
		return emailNewCus;
	}

	public void setEmailNewCus(HtmlInputText emailNewCus) {
		this.emailNewCus = emailNewCus;
	}

	public HtmlInputSecret getPasswordNewCus() {
		return passwordNewCus;
	}

	public void setPasswordNewCus(HtmlInputSecret passwordNewCus) {
		this.passwordNewCus = passwordNewCus;
	}

	public HtmlInputSecret getConfirmPasswordNewCus() {
		return confirmPasswordNewCus;
	}

	public void setConfirmPasswordNewCus(HtmlInputSecret confirmPasswordNewCus) {
		this.confirmPasswordNewCus = confirmPasswordNewCus;
	}

	public HtmlInputText getFirstnameNewCus() {
		return firstnameNewCus;
	}

	public void setFirstnameNewCus(HtmlInputText firstnameNewCus) {
		this.firstnameNewCus = firstnameNewCus;
	}

	public HtmlInputText getLastnameNewCus() {
		return lastnameNewCus;
	}

	public void setLastnameNewCus(HtmlInputText lastnameNewCus) {
		this.lastnameNewCus = lastnameNewCus;
	}

	public HtmlInputText getBirthdayNewCus() {
		return birthdayNewCus;
	}

	public void setBirthdayNewCus(HtmlInputText birthdayNewCus) {
		this.birthdayNewCus = birthdayNewCus;
	}

	public HtmlInputText getHomePhoneNewCus() {
		return homePhoneNewCus;
	}

	public void setHomePhoneNewCus(HtmlInputText homePhoneNewCus) {
		this.homePhoneNewCus = homePhoneNewCus;
	}

	public HtmlInputText getMobilePhoneNewCus() {
		return mobilePhoneNewCus;
	}

	public void setMobilePhoneNewCus(HtmlInputText mobilePhoneNewCus) {
		this.mobilePhoneNewCus = mobilePhoneNewCus;
	}
	
	public HtmlInputText getEmailLoginCus() {
		return emailLoginCus;
	}

	public void setEmailLoginCus(HtmlInputText emailLoginCus) {
		this.emailLoginCus = emailLoginCus;
	}

	public HtmlInputSecret getPassworLoginCus() {
		return passworLoginCus;
	}

	public void setPassworLoginCus(HtmlInputSecret passworLoginCus) {
		this.passworLoginCus = passworLoginCus;
	}
	
	public HtmlOutputText getLoginMessageError() {
		return loginMessageError;
	}
	public void setLoginMessageError(HtmlOutputText loginMessageError) {
		this.loginMessageError = loginMessageError;
	}

	public String getMegError() {
		return megError;
	}

	public void setMegError(String megError) {
		this.megError = megError;
	}
	

	
	
	HtmlInputTextarea comment;

	public HtmlInputTextarea getComment() {
		return comment;
	}

	public void setComment(HtmlInputTextarea comment) {
		this.comment = comment;
	}

	HtmlInputTextarea commentZone;
	
	public HtmlInputTextarea getCommentZone() {
		return commentZone;
	}

	public void setCommentZone(HtmlInputTextarea commentZone) {
		this.commentZone = commentZone;
	}

	
	public HtmlPanelGroup getGroupComment() {
		return groupComment;
	}

	public void setGroupComment(HtmlPanelGroup groupComment) {
		this.groupComment = groupComment;
	}

	public String getNameCustomer()
	{
		Customer client = customerBean.getCustomer();
		
		if (client != null)
		{
			return client.getLastnameCustomer() + " " + client.getFirstnameCustomer();
		}
		else
		{
			return "";
		}
	}
	
	public double getSolde()
	{
		return customerBean.getCustomer().getAccount().getBalanceAccount();
	}
	
	public Customer getCustomer()
	{
		return customerBean.getCustomer();
	}
	
	@EJB
	CartRemote cart;
	
	public Product productChosen = null;
	HtmlDataTable dataTable;
	public PanelTabSet panelTabSet;
	
	public int selectedIndex = 0;
	
	// Cart View
	// 1 : show cart
	// 2 : payement
	// 3 : Thanks
	int cartView = 1;
	
	public ArrayList<ProductOrderList> getItems()
	{
		return cart.getItems();
	}
	
	public boolean isCartSelected()
	{
		return panelTabSet.getSelectedIndex() == 4;
	}
	
	public boolean isCartEmpty()
	{
		ArrayList<ProductOrderList> products = cart.getItems();
		return products.isEmpty();
	}
	
	public void addToCart()
	{
		ArrayList<ProductOrderList> products = cart.getItems();
		Iterator<ProductOrderList> it = products.iterator();
		
		while (it.hasNext())
		{
			ProductOrderList product = it.next();
			if (product.getProduct().equals(productChosen))
			{
				// Si il existe dans panier, on incrémente la quantité
				cart.modifyProductQuantity(productChosen, product.getQuantity()+1);
				return;
			}
		}
		// S'il n'existe pas encore dans le panier
		// On ajoute le produit avec la quantité 1
		cart.addProduct(productChosen, 1);
	}
	
	public int getNbrArticles()
	{
		return this.getItems().size();
	}
	
	public void removeFromCart()
	{
		cart.removeProduct(productChosen);
	}
	
	public boolean isCartValid()
	{
		Customer cus = getCustomer();
		double amountAcc = 0;
		if (cus != null)
		{
			amountAcc = cus.getAccount().getBalanceAccount();
		}
		boolean prixTotalValid = getPriceTotal() <= amountAcc;
		
		return cart.validate() && prixTotalValid ;
	}
	
	public boolean isAmountNotAccepted()
	{
		Customer cus = getCustomer();
		double amountAcc = 0;
		
		if (cus !=null)
		{
			amountAcc = cus.getAccount().getBalanceAccount();
		}
		boolean prixTotalValid = getPriceTotal() > amountAcc;
		return prixTotalValid;
	}
	
	String messageCart;
	
	public String refreshCart()
	{
		messageCart = "";
		
		boolean cartValid = cart.validate();
		
		if (!cartValid)
		{
			messageCart = "Votre panier n'est pas valid";
		}
		
		selectedIndex = panelTabSet.getSelectedIndex();
		return "homeClient";
	}
	
	public String purchaseCart()
	{
		
		if ( isCartValid() && !isAmountNotAccepted() )
		{
			cartView = 2;
		}
		else
		{
			cartView = 1;
		}
		
		return "homeClient";
	}

	public String backToCart()
	{
		cartView = 1;
		return "homeClient";
	}
	
	public String purchaseDone()
	{
		cartView = 1;
		panelTabSet.setSelectedIndex(0);
		return "homeClient";
	}
	
	public String confirmPurchaseCart()
	{
		
		Address ad = new Address();
		ad.setCityAddress(villeL);
		ad.setCountryAddress(paysL);
		ad.setPostalCodeAddress(Integer.parseInt(codePL));
		ad.setStreetAddress(rueL);
		
		cart.purchase(getCustomer(), ad);
		
		cartView = 3;
		return "homeClient";
	}
	
	public String getMessageCart() {
		return messageCart;
	}

	public void setMessageCart(String messageCart) {
		this.messageCart = messageCart;
	}
	
	public String cleanCart()
	{
		cart.cleanAll();
		return "homeClient";
	}
	
	public String cleanCartHome()
	{
		cart.cleanAll();
		return "homeClient";
	}
	
	public String showCart()
	{
		panelTabSet.setSelectedIndex(4);
		return "homeClient";
	}

	public double getPriceTotal()
	{
		return cart.priceTotal();
	}
	
	public Product getProductChosen() {
		return productChosen;
	}

	public void setProductChosen(Product productChosen) {
		this.productChosen = productChosen;
	}

	public HtmlDataTable getDataTable() {
		return dataTable;
	}

	public void setDataTable(HtmlDataTable dataTable) {
		this.dataTable = dataTable;
	}
	
	public PanelTabSet getPanelTabSet() {
		return panelTabSet;
	}

	public void setPanelTabSet(PanelTabSet panelTabSet) {
		this.panelTabSet = panelTabSet;
	}

	public int getSelectedIndex() {
		return selectedIndex;
	}

	public void setSelectedIndex(int selectedIndex) {
		this.selectedIndex = selectedIndex;
	}
	
	public boolean isShowCartView()
	{
		return cartView == 1;
	}
	
	public boolean isPurchaseView()
	{
		return cartView == 2;
	}

	public boolean isThanksView()
	{
		return cartView == 3;
	}
	
	// Livraison
	String rueL;
	String villeL;
	String codePL;
	String paysL;

	public String getRueL() {
		return rueL;
	}

	public void setRueL(String rueL) {
		this.rueL = rueL;
	}

	public String getVilleL() {
		return villeL;
	}

	public void setVilleL(String villeL) {
		this.villeL = villeL;
	}

	public String getCodePL() {
		return codePL;
	}

	public void setCodePL(String codePL) {
		this.codePL = codePL;
	}

	public String getPaysL() {
		return paysL;
	}

	public void setPaysL(String paysL) {
		this.paysL = paysL;
	}
	
}
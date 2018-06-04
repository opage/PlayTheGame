package ecom.jonas.client;

import java.util.LinkedList;
import java.util.List;

import javax.ejb.EJB;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import ecom.jonas.entity.Platform;
import ecom.jonas.entity.Product;
import ecom.jonas.entity.Type;
import ecom.jonas.session.AdminRemote;

public class Client {

	@EJB
	private static AdminRemote admin;

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Connection();
		// if (Login())
		InitDatabase();
	}

	private static boolean Login() {
		
		// Admin*********************************************************
	
		
		JTextField userField = new JTextField();
		JPasswordField passField = new JPasswordField();
		String message = "Please enter your user name and password.";
		Object[] options = { message, userField, passField };

		int result = JOptionPane.showOptionDialog(null, options, "Login",
				JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE,
				null, null, null);

		String loginAdmin = null;
		String passwordAdmin = null;
		// clic ok
		if (result == JOptionPane.OK_OPTION) {
			loginAdmin = userField.getText().trim();
			passwordAdmin = new String(passField.getPassword());
		} else
			// clic CANCEL
			return false;

		if (admin.getAdminByLP(loginAdmin, passwordAdmin) == null) {
			System.err.println("Your login attempt was not successful");
			return false;
		}
		else
			return true;
	}

	private static void Connection() {
		
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

	private static void InitDatabase() {
		try {
			// Type*********************************************************			
			admin.createType( "Action", "Action Description");			
			admin.createType( "Aventure", "Aventure Description");		
			admin.createType( "Combat", "Combat Description");			
			admin.createType( "Complilation", "Complilation Description");			
			admin.createType( "Course", "Course Description");		
			admin.createType( "Jeu de role", "Jeu de role Description");			
			admin.createType( "MMO", "MMO Description");		
			admin.createType( "Plate-forme", "Plate-forme Description");			
			admin.createType( "Sport", "Sport Description");		
			admin.createType( "Strategie", "Strategie Description");
			
			// Platofrm
			// *********************************************************
			long idPlatform = 1;
			admin.createPlatform("PS3","PS3 Description");			
			admin.createPlatform("WII","La Wii Description");			
			admin.createPlatform("Xbox 360","La Xbox 360 Description");			
			admin.createPlatform("PS2","La PlayStation 2 (PS2) Description");			
			admin.createPlatform("PSP","PSP Description");			
			admin.createPlatform("DS","La Nintendo DS Description");			
			admin.createPlatform("PC","PC Description");
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("Exception is " + e);
		}
		// get Platform and all Types
		Platform platform = null;
		List<Type> types = null;
		try {
			platform = admin.getPlatformById(4);
			types = (List<Type>) admin.getAllTypes();
			System.out.println("TypesSise is" + types.size());
			System.out.println("PlatformsSise id =3 is:" + admin.getPlatformById(4).getNamePlatform());
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			System.out.println("Exception getPlatformById is " + e1);
		}
		try {
			for (Type t : types)
				System.out.println(t.toString());
			List<Type> typesN = new LinkedList<Type>();
			typesN.add(types.get(8));			
			
			/** Game **/
			/** Platform Action   **/
			platform = admin.getPlatformById(1);
			typesN.removeAll(types);
			typesN.add(types.get(0));//Action
			typesN.add(types.get(1));//Aventure
			
			String url="/upload/";
			String des="Après le livre, il y aura donc le film et le jeu vidéo. Max et les Maximonstres débarque dans nos salons et aussi dans les salles obscures. Dans le jeu, vous incarnez Max, un jeune garçon espiègle. Vous voici parti pour l’exploration de l’île mystérieuse des Maximonstres. Certes effrayants, mais en réalité adorables.";
			
			admin.createGame("AVATAR Collector", translate(des), 79.99, 30, 0,url+"39978_jaqr_avatarPS3_129x171.jpg", 
					null, new java.util.Date(03,12,2009),"0|0","Ubisoft", "UBISOFT", platform, typesN);
			des="Après le livre, il y aura donc le film et le jeu vidéo. Max et les Maximonstres débarque dans nos salons et aussi dans les salles obscures. Dans le jeu, vous incarnez Max, un jeune garçon espiègle. Vous voici parti pour l’exploration de l’île mystérieuse des Maximonstres. Certes effrayants, mais en réalité adorables.";
			admin.createGame("MAX ET LES MAXIMONSTRES", translate(des), 49.99, 20, 0,url+"40359_jaqr_maxetlesmaxiPS3_129x171.jpg", 
					null, new java.util.Date(03,12,2009),"0|0","Amaze Entertainment", "WARNER BROS", platform, typesN);
			des="LEGO Indiana Jones 2 suit les aventures du Dr Jones, de la jungle sud-américaine aux ruines péruviennes, et jusqu’au Royaume du Crâne de Cristal !";
				admin.createGame("LEGO INDIANA JONES 2, L'Aventure Continue", translate(des), 59.99, 15, 0,url+"39648_jaqr_jaquette2_129x171.jpg", 
						null, new java.util.Date(20,11,2009),"0|0","Traveller’s Tales", "ACTIVISION", platform, typesN);
			des="Rejoins la joyeuse équipe d’animaux préhistoriques pour vivre l’aventure la plus rocambolesque qu’ils aient jamais connues.";
			admin.createGame("L'AGE DE GLACE 3", translate(des), 59.99, 20, 0,url+"38539_jaqr_agedeglace3PS3_129x171.jpg", 
						null, new java.util.Date(26,06,2009),"0|0","Eurocom", "ACTIVISION", platform, typesN);
			typesN.removeAll(types);
			typesN.add(types.get(2));
			des="Première apparition d'un opus original sur PS3 et XBOX 360, TEKKEN 6 est à coup sur un véritable évènement dans le monde du jeu vidéo. Développé depuis maintenant 5 ans dans les studios japonais de Namco, la franchise multimillionnaire compte reprendre sa place de n°1 des jeux de combat";
			admin.createGame("TEKKEN 6", translate(des), 69.99, 20, 0,url+"39313_jaqr_jaquette-1_129x171.jpg", 
						null, new java.util.Date(26,10,2009),"0|0","Namco Bandai", " BANDAI NAMCO PARTNERS", platform, typesN);
			des="La référence du catch signe un retour en fanfare avec son panel toujours plus complet de superstars";
			admin.createGame("WWE SMACKDOWN VS RAW 2010", translate(des), 69.99, 20, 30,url+"39359_jaqr_jaquette-1_129x171.jpg", 
						null, new java.util.Date(26,07,2009),"0|0"," n.c", " THQ", platform, typesN);
			typesN.add(types.get(4));//Course
			des="Après Ferrari Challenge, System 3 élargit ses horizons. On retrouve donc le gameplay ajustable (arcade ou simulation) du précédent titre mais un panel de véhicule plus larges";
			admin.createGame("Supercar Challenge", translate(des), 64.99, 20, 0,url+"39163_jaqr_supercarchallengePS3_129x171.jpg", 
						null, new java.util.Date(11,9,2009),"0|0","System 3", "BANDAI NAMCO PARTNERS", platform, typesN);
			des="La référence du catch signe un retour en fanfare avec son panel toujours plus complet de superstars";
			admin.createGame("RIDGE RACER 7 Platinum", translate(des), 60.00, 20, 20,url+"30281_jaqr_RR7_129x171.jpg", 
						null, new java.util.Date(19,07,2009),"0|0","Namco", "SONY", platform, typesN);
			typesN.add(types.get(5));//Jeux de Role
			des="Sorti fin 2008 sur PC, Sacred 2 Fallen Angel débarque sur PS3. 2000 ans avant les événements narrés dans le premier jeu, les Hauts-Elfes ont en garde la puissante Energie T. Mais leur avidité face à cette source de pouvoir ne tarde pas à plonger le monde dans le chaos.";
			admin.createGame("SACRED 2 Fallen Angels Collector", translate(des), 60.00,40, 30,url+"38206_jaqr_sacred2PS3_129x171.jpg", 
						null, new java.util.Date(17,9,2008),"0|0","Ascaron", "KOCH MEDIA", platform, typesN);
			typesN.add(types.get(7));//Platform
			des="Sorti fin 2008 sur PC, Sacred 2 Fallen Angel débarque sur PS3. 2000 ans avant les événements narrés dans le premier jeu, les Hauts-Elfes ont en garde la puissante Energie T. Mais leur avidité face à cette source de pouvoir ne tarde pas à plonger le monde dans le chaos.";
			admin.createGame("RATCHET & CLANK, A Crack in Time", translate(des), 60.00,10, 0,url+"39146_jaqr_ratchetnclankacrackintime_129x171.jpg", 
						null, new java.util.Date(17,5,2008),"0|0","Insomniac Games", "SONY", platform, typesN);
			typesN.add(types.get(8));//Sport
			des="Le premier véritable système de dribbles à 360 degrés intégré à un jeu de football vous permet, pour la première fois, de mieux maîtriser les espaces entre les défenseurs.";
			admin.createGame("FIFA 10", translate(des), 66.00,22, 0,url+"39519_jaqr_fifa10PS3_129x171.jpg", 
						null, new java.util.Date(17,5,2008),"0|0","EA Sports", "ELECTRONIC ARTS", platform, typesN);
			des="La franchise phare de simulation de basketball signe son retour pour sa dixième saison consécutive. NBA 2K9 se veut être un jeu réaliste, proposant un riche contenu officiel et de nombreuses fonctionnalités. Graphismes soignés et jeu en ligne sont aussi de rigueur.";
			admin.createGame("NBA 2K9", translate(des), 66.00,22, 0,url+"36035_jaqr_nba2k9ps3_129x171.jpg", 
						null, new java.util.Date(12,5,2008),"0|0","EA Sports", "ELECTRONIC ARTS", platform, typesN);
		
			
			/** Platform WII   **/
			platform = admin.getPlatformById(2);
			typesN.removeAll(types);
			typesN.add(types.get(0));//Action
			typesN.add(types.get(1));//Aventure
			
			des="Après le livre, il y aura donc le film et le jeu vidéo. Max et les Maximonstres débarque dans nos salons et aussi dans les salles obscures. Dans le jeu, vous incarnez Max, un jeune garçon espiègle. Vous voici parti pour l’exploration de l’île mystérieuse des Maximonstres. Certes effrayants, mais en réalité adorables.";
			admin.createGame("AVATAR Collector", translate(des), 79.99, 30, 0,url+"139978_jaqr_avatarPS3_129x171.jpg", 
					null, new java.util.Date(03,12,2009),"0|0","Nintendo", "Nintendo", platform, typesN);
			des="Après le livre, il y aura donc le film et le jeu vidéo. Max et les Maximonstres débarque dans nos salons et aussi dans les salles obscures. Dans le jeu, vous incarnez Max, un jeune garçon espiègle. Vous voici parti pour l’exploration de l’île mystérieuse des Maximonstres. Certes effrayants, mais en réalité adorables.";
			admin.createGame("MAX ET LES MAXIMONSTRES", translate(des), 49.99, 20, 0,url+"140359_jaqr_maxetlesmaxiPS3_129x171.jpg", 
					null, new java.util.Date(03,12,2009),"0|0","Nintendo", "Nintendo", platform, typesN);
			
			des="De 1 à 4 joueurs, faites du stop et montez à bord de véhicules divers (camionnette, bus, voiture de sport, etc). Conduisez votre équipe le plus vite possible à travers la route des Dragons, du Vietnam à l’Indonésie.";
			admin.createGame("PEKIN EXPRESS", translate(des), 29.99, 20, 0,url+"40520_jaqr_pekinexpressWii_129x171.jpg", 
						null, new java.util.Date(26,9,2009),"0|0","n.c", "MINDSCAPE", platform, typesN);
			typesN.removeAll(types);
			typesN.add(types.get(3));//compilation
			des="Plongez ou replongez dans l’armure du chasseur de prime le plus redoutable de la galaxie : la trilogie Metroid Prime se joue désormais intégralement à la télécommande Wii pour une immersion accrue.";
			admin.createGame("Metroid Prime, Trilogy", translate(des), 39.99, 20, 0,url+"39155_jaqr_Metroidprimetrilogy_129x171.jpg", 
						null, new java.util.Date(4,10,2009),"0|0","Nintendo", "Nintendo", platform, typesN);
			
			typesN.add(types.get(4));//Course
			des="Seul comme à plusieurs, imposez votre suprématie sur des pistes délirantes et réalisez des cascades spectaculaires. Prenez à fond les sauts vertigineux et les parois verticales, dépassez vos adversaires sur la ligne d’arrivée pour une bonne dose d’adrénaline.";
			admin.createGame("MONSTER 4X4, Stunt Racer", translate(des), 24.99, 20, 0,url+"40376_jaqr_monster4x4Wii_129x171.jpg", 
						null, new java.util.Date(11,9,2009),"0|0","Maxis", "Maxis", platform, typesN);
			des="Soyez le premier à franchir la ligne d'arrivée avec MySims Racing ! Vous ferez la course contre les MySims sur de nombreuses pistes, toutes plus exotiques les unes que les autres, et vous pourrez affronter jusqu'à trois amis en multijoueur";
			admin.createGame("MYSIMS RACING", translate(des), 20.00, 20, 20,url+"38852_jaqr_jaquette_129x171.jpg", 
						null, new java.util.Date(11,07,2009),"0|0","Maxis", "Maxis", platform, typesN);
			
			typesN.add(types.get(7));//Platform
			des="Sorti fin 2008 sur PC, Sacred 2 Fallen Angel débarque sur PS3. 2000 ans avant les événements narrés dans le premier jeu, les Hauts-Elfes ont en garde la puissante Energie T. Mais leur avidité face à cette source de pouvoir ne tarde pas à plonger le monde dans le chaos.";
			admin.createGame("PITFALL, La Grande Aventure", translate(des), 30.00,10, 10,url+"37519_jaqr_pitfalllagrandeaventureWii_129x171.jpg", 
						null, new java.util.Date(17,11,2008),"0|0","ACTIVISION", "ACTIVISION", platform, typesN);
			typesN.add(types.get(8));//Sport
			des="L'hiver arrive, il est temps de se lancer dans les sports de glisse avec Winter Sports 2010. Au programme : patinage, descente, saut à ski, bobsleigh, snowboard et bien d'autres activités de saison. Le joueur participera à un total de 16 compétitions contre quatre équipes nationales. En mode carrière, vous pourrez gagner des points à utiliser pour améliorer vos talents et votre équipement.";
			admin.createGame("WINTER SPORTS 2010", translate(des), 46.00,22, 0,url+"39801_jaqr_wintersports2010Wii_129x171.jpg", 
						null, new java.util.Date(17,5,2008),"0|0","RTL Games", "RTL Games", platform, typesN);
			des="La franchise phare de simulation de basketball signe son retour pour sa dixième saison consécutive. NBA 2K9 se veut être un jeu réaliste, proposant un riche contenu officiel et de nombreuses fonctionnalités. Graphismes soignés et jeu en ligne sont aussi de rigueur.";
			admin.createGame("ACADEMY OF CHAMPIONS", translate(des), 60.00,22, 50,url+"39055_jaqr_academyofchampionsWii_129x171.jpg", 
						null, new java.util.Date(12,5,2008),"0|0","UBISOFT", "UBISOFT", platform, typesN);
			
			
			
			/** Platform XBOX   **/
			platform = admin.getPlatformById(3);
			typesN.removeAll(types);
			typesN.add(types.get(0));//Action
			typesN.add(types.get(1));//Aventure
			
			 des="Après le livre, il y aura donc le film et le jeu vidéo. Max et les Maximonstres débarque dans nos salons et aussi dans les salles obscures. Dans le jeu, vous incarnez Max, un jeune garçon espiègle. Vous voici parti pour l’exploration de l’île mystérieuse des Maximonstres. Certes effrayants, mais en réalité adorables.";
			admin.createGame("AVATAR Collector", translate(des), 69.99, 30, 0,url+"239978_jaqr_avatarPS3_129x171.jpg", 
					null, new java.util.Date(03,12,2009),"0|0","Nintendo", "Nintendo", platform, typesN);
			des="Après le livre, il y aura donc le film et le jeu vidéo. Max et les Maximonstres débarque dans nos salons et aussi dans les salles obscures. Dans le jeu, vous incarnez Max, un jeune garçon espiègle. Vous voici parti pour l’exploration de l’île mystérieuse des Maximonstres. Certes effrayants, mais en réalité adorables.";
			admin.createGame("MAX ET LES MAXIMONSTRES", translate(des), 59.99, 20, 0,url+"240359_jaqr_maxetlesmaxiPS3_129x171.jpg", 
					null, new java.util.Date(03,12,2009),"0|0","Nintendo", "Nintendo", platform, typesN);
			
			des="De 1 à 4 joueurs, faites du stop et montez à bord de véhicules divers (camionnette, bus, voiture de sport, etc). Conduisez votre équipe le plus vite possible à travers la route des Dragons, du Vietnam à l’Indonésie.";
			admin.createGame("LES EXPERTS, Préméditation", translate(des), 49.99, 20, 0,url+"39375_jaqr_lesexpertsPremeditationx360_129x171.jpg", 
						null, new java.util.Date(26,9,2009),"0|0","n.c", "UBISOFT", platform, typesN);
			typesN.removeAll(types);
			
			
			typesN.add(types.get(4));//Course
			des="Vous rêvez de conduire un jour une Audi R8 ou une Porsche 911 ? Vous êtes plutôt branché « muscle », Ford GT ou Corvette Z06 reste votre fantasme roulant ? Quelque soit la voiture de vos rêves, elle sera forcément présente dans la nouvelle référence de simulation automobile sur Xbox 360.";
			admin.createGame("FORZA MOTORSPORT 3", translate(des), 44.99, 20, 0,url+"39543_jaqr_forza3_129x171.jpg", 
						null, new java.util.Date(11,9,2009),"0|0","MICROSOFT", "MICROSOFT", platform, typesN);
			des="Les joueurs rencontreront 12 personnages jouables encore jamais présentés. Direction les rues, les plages ou encore le désert de Baja pour des défis de pilotage inédits. Découvrez notamment la nouvelle capacité de drift de McQueen.";
			admin.createGame("CARS, Race O Rama", translate(des), 60.00, 20, 20,url+"39341_jaqr_carsraceoramaXbox360_129x171.jpg", 
						null, new java.util.Date(11,07,2009),"0|0","MICROSOFT", "MICROSOFT", platform, typesN);
			
			typesN.add(types.get(9));//Stratigie
			des="Les joueurs rencontreront 12 personnages jouables encore jamais présentés. Direction les rues, les plages ou encore le désert de Baja pour des défis de pilotage inédits. Découvrez notamment la nouvelle capacité de drift de McQueen.";
			admin.createGame("CALL OF DUTY Modern Warfare 2", translate(des), 80.00, 20, 20,url+"38868_jaqr_codmw2x360_129x171.jpg", 
						null, new java.util.Date(11,10,2009),"0|0","MICROSOFT", "MICROSOFT", platform, typesN);
			
		
			/** Platform sp2   **/
			platform = admin.getPlatformById(4);
			typesN.add(types.get(2));//COMBAT
			
			
			 des="Basé sur l'anime Naruto Shippuden, le jeu plonge le joueur dans une aventure passionnante.";
			admin.createGame("NARUTO ULTIMATE NINJA 5, Shippuden", translate(des), 29.99, 30, 0,url+"39830_jaqr_jaquette-1_129x171.jpg", 
					null, new java.util.Date(03,12,2009),"0|0","CyberConnect", "CyberConnect", platform, typesN);
			des=" Le nouveau mode « Dragon Mission » vous propose un son subtil mélange de minijeux originaux, de séquences cinématiques fidèles au dessin animé et de combats mythiques.";
			admin.createGame("DRAGON BALL Z", translate(des), 59.99, 20, 0,url+"36813_jaqr_DBZInfiniteWorld_129x171.jpg", 
					null, new java.util.Date(03,12,2009),"0|0","Nintendo", "Nintendo", platform, typesN);
			
			typesN.removeAll(types);
		
			typesN.add(types.get(4));//Course
			des="Le festival de courses off-road frénétiques Motorstorm propose un tout nouveau défi sur PS2 : les températures glaciales de l’Alaska.";
			admin.createGame("Motorstorm, Arctic Edge", translate(des), 34.99, 20, 0,url+"39504_jaqr_motorstormarticadgePS2_87x103.jpg", 
						null, new java.util.Date(11,9,2009),"0|0","SONY", "SONY", platform, typesN);
			
			
			typesN.add(types.get(9));//Stratigie
			des="Les joueurs rencontreront 12 personnages jouables encore jamais présentés. Direction les rues, les plages ou encore le désert de Baja pour des défis de pilotage inédits. Découvrez notamment la nouvelle capacité de drift de McQueen.";
			admin.createGame("BAKUGAN, Battle Brawlers", translate(des), 40.00, 20, 20,url+"40019_jaqr_jaquette_129x171.jpg", 
						null, new java.util.Date(11,10,2009),"0|0","ACTIVISION", "ACTIVISION", platform, typesN);
			
			/** Platform psp   **/
			platform = admin.getPlatformById(5);
			typesN.removeAll(types);
			typesN.add(types.get(0));//Action
			typesN.add(types.get(1));//Aventure
			typesN.add(types.get(3));//COMPILATION
			
			
			 des="Toute l'émotion du film - Harry, Ron et Hermione ont été modélisés à partir des interprètes cinéma. L'histoire suit le scénario et utilise la musique.";
			admin.createGame("HARRY POTTER COLLECTION", translate(des), 29.99, 30, 0,url+"38918_jaqr_jaquette_129x171.jpg", 
					null, new java.util.Date(03,12,2009),"0|0","EA Games", "EA Games", platform, typesN);
			typesN.removeAll(types);
			
			typesN.add(types.get(3));//COMPILATION
			typesN.add(types.get(8));//Sport
			des=" Entrez sur le terrain et jouez comme un pro avec FIFA 09. Découvrez la simulation de football la plus réaliste jamais créée par EA SPORTS sur le système PSP (PlayStation Portable) et réalisez votre rêve d'incarner un grand joueur professionnel.";
			admin.createGame("THE FOOTBALL COLLECTION 2009", translate(des), 39.99, 20, 0,url+"38916_jaqr_jaquette_129x171.jpg", 
					null, new java.util.Date(03,12,2009),"0|0","EA Games", "EA Games", platform, typesN);
			
			typesN.removeAll(types);
			typesN.add(types.get(3));//COMPILATION
			typesN.add(types.get(4));//Course
			des="Battez-vous sur des nouveaux circuits conçus pour tous les types de course. Dérapez le long de grands virages sur les artères urbaines ouvertes et faites la course en contre sens dans des petites rues étroites de la ville.";
			admin.createGame("THE STREET RACING COLLECTION 2009", translate(des), 29.99, 20, 0,url+"38920_jaqr_jaquette_129x171.jpg", 
						null, new java.util.Date(11,9,2009),"0|0","EA Games", "EA Games", platform, typesN);
			
			/** Platform ds   **/
			platform = admin.getPlatformById(6);
			typesN.removeAll(types);
			
			typesN.add(types.get(8));//Platform
			
			
			 des="Un énorme cyclone a dévasté la planète, projetant tous les poissons hors des océans.Sans votre intervention, cette catastrophe aura des répercussions irréversibles sur tout l'écosystème !.";
			admin.createGame("AQUA PANIC, Sauvez-les Tous", translate(des), 19.99, 30, 0,url+"38182_jaqr_aquapanic_129x171.jpg", 
					null, new java.util.Date(03,12,2009),"0|0","MICRO APPLICATION", "MICRO APPLICATION", platform, typesN);
			typesN.removeAll(types);
			
			
			typesN.add(types.get(4));//course
			des=" Du Royaume-Uni à la France, des quartiers chics aux quartiers chauds, astique ta caisse et sors-là dans la rue.";
			admin.createGame("PIMP MY RIDE, Street Racing", translate(des), 29.99, 20, 0,url+"37984_jaqr_pimpmyrideeuroDS_129x171.jpg", 
					null, new java.util.Date(03,4,2009),"0|0","ACTIVISION", "ACTIVISION", platform, typesN);
			
			typesN.removeAll(types);
			
			typesN.add(types.get(9));//sTRATIGIE
			des="Stratégie quand tu nous tiens. Inspirés des différents thèmes LEGO, ce titre exclusif à la nomade de Nintendo fonctionne par niveau. Destiné aux plus petits, il propose au joueur d’évoluer d’une mission à une autre en remplissant un ou plusieurs objectifs bien définis.";
			admin.createGame("LEGO BATTLES", translate(des), 30.00, 20, 10,url+"38831_jaqr_jaquette_129x171.jpg", 
						null, new java.util.Date(11,9,2009),"0|0","Hellbent Games", "Hellbent Games", platform, typesN);
			
			/** Platform PC   **/
			platform = admin.getPlatformById(7);
			typesN.removeAll(types);
			
			typesN.add(types.get(6));//MmO
			
			
			 des="Elu depuis 3 ans meilleur jeu online en développement, Age of Conan est un jeu en ligne massivement multijoueur développé par Funcom. Avec des graphismes éblouissants, Conan plonge les joueurs au coeur d'un univers barbare et incroyablement riche.";
			admin.createGame("AGE OF CONAN, Hyborian Adventures", translate(des), 5.99, 30, 0,url+"34634_jaqr_3D_AOC_classique_129x171.jpg", 
					null, new java.util.Date(03,12,2009),"0|0","Funcom", "Funcom", platform, typesN);
			typesN.removeAll(types);
			 des="Située en plein coeur des Terres foudroyées, le Portail des ténèbres renferme de terribles secrets, souvenirs effroyables du passage de la Legion ardente en terres d’Azeroth.";
				admin.createGame("WORLD OF WARCRAFT Expansion", translate(des), 14.99, 30, 0,url+"29128_jaqr_burningcrusade_129x171.jpg", 
						null, new java.util.Date(03,12,2009),"0|0","ACTIVISION", "ACTIVISION", platform, typesN);
				typesN.removeAll(types);
			
			
			typesN.add(types.get(2));//COMBAT
			des=" Street Fighter est le maître incontesté du jeu de combat, sur consoles de jeux vidéo et en arcade, depuis près de 20 ans ! Peu de séries de jeux vidéo peuvent en tout cas prétendre avoir réussi à marquer autant de générations, et suscitent encore, à chaque nouvel épisode, un tel engouement.";
			admin.createGame("STREET FIGHTER", translate(des), 40.00, 20, 20,url+"37415_jaqr_micromania_129x171.jpg", 
					null, new java.util.Date(03,4,2009),"0|0","CAPCOM", "CAPCOM", platform, typesN);
			
			typesN.removeAll(types);
			
			typesN.add(types.get(9));//sTRATIGIE
			typesN.add(types.get(0));//sTRATIGIE
			typesN.add(types.get(1));//sTRATIGIE
			des=" ";
			admin.createGame("ORDER OF WAR", translate(des), 50.00, 20, 10,url+"39123_jaqr_orderofwarPC_129x171.jpg", 
						null, new java.util.Date(11,9,2009),"0|0","SQUARE", "SQUARE", platform, typesN);
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			admin.createGame("MARVEL SUPER HERO SQUAD (Wii)", "Nazih", 55.0, 44,0, "THQ","THQ", new java.util.Date(), "0|0", "GroupI", "Sony", platform, typesN);
			typesN.removeAll(types);
			typesN.add(types.get(0));			
			admin.createGame("WATCHMEN (Xbox 360)", "Nazih", 60.0, 20, 4, "java", "Video", new java.util.Date(), "0|0", "Deadline Games","Sony", platform, typesN);
			admin.createGame("Call of Duty", "Nazih", 90.0, 20, 4, "java", "Video", new java.util.Date(),"0|0", "Deadline Games",	"Sony", platform, typesN);
			
			/** Consoles **/
			admin.createConsole("Nintendo DS", "Console de jeux", 376.78, 10, 5, "Image", "Video", new java.util.Date(), "32GB", 3.5, platform);
			admin.createConsole("PS3", "Console de jeux", 506.48, 15, 8, "Image", "Video", new java.util.Date(), "320GB", 5.5, platform);
			admin.createConsole("Xbox 360", "Console de jeux", 276.78, 16, 3, "Image", "Video", new java.util.Date(), "50GB", 8.5, platform);
			
			/** Accessories **/
			admin.createAccessory("GAMEware Wii Wheel", "Volant", 30.5, 3, 20, "Image", "Video", new java.util.Date(), admin.getPlatformById(2));
			admin.createAccessory("Pedale Wii", "Pedale", 54.5, 6, 34, "Image", "Video", new java.util.Date(), admin.getPlatformById(2));
			admin.createAccessory("Velo Wii Wheel", "Velo", 89.5, 5, 11, "Image", "Video", new java.util.Date(), admin.getPlatformById(2));
			

			System.out.println("Products List with price<= 60 and type ='Action");		
			
			List<Product> products = (List<Product>) admin.getProductByType(60,	new Long(1));
			for (Product p : products)
				System.out.println(p.toString());

		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("Exception createProduit is " + e);
		}
	}
	public static String translate(String src)
	{
	 StringBuffer result = new StringBuffer();
	 if(src!=null && src.length()!=0)
	 {
	  int index = -1;
	  char c = (char)0;
	  String chars= "àâäéèêëîïôöùûüç";
	  String replace= "aaaeeeeiioouuuc";
	  for(int i=0; i<src.length(); i++)
	  {
	   c = src.charAt(i);
	   if( (index=chars.indexOf(c))!=-1 )
	    result.append(replace.charAt(index));
	   else
	    result.append(c);
	  }
	 };
	 return result.toString();
	}
}

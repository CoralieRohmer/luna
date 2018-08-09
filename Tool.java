/**
 * Tool.java
 */

import java.io.IOException;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.BufferedReader;
import java.net.URL;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.net.MalformedURLException;
import java.net.URLConnection;
import java.lang.StringBuilder;
import java.io.InputStream;
import java.io.File;
import java.awt.Color;
import java.util.HashMap;
import java.io.IOException;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
import java.util.List;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import java.lang.String;


/**
 * <b>Tool contient toutes les fonctions utiles au programme.</b>
 * 
 * @author Coralie Rohmer && Marine Aglave
 */
 
public class Tool{
	//------------------------------------------------------------------
	//				Variables globales
	//------------------------------------------------------------------
	/**
     * Couleur de fond de la fenetre.
     * @see Color
     * @see GUI
     */
	public static final Color background = Color.LIGHT_GRAY;
    
    /**
     * Couleur de fond de la zone de titre de la barre Metabolisme 
     * "Affichage Metabolisme" et "Information Reaction".
     * @see Color
     * @see GUI
     */
	public static final Color colorMeta = new Color(132,156,182);
    
     /**
     * Couleur de fond de la zone de titre de la barre Genome 
     * "Affichage Genome" et "Information Gene"
     * @see Color.
     * @see GUI
     */
	public static final Color colorGenome = new Color(180,107,144);
    
    /**
     * Couleur des bordures internes des composants de la fenetre
     * @see Color
     * @see PathwayBrowser
     * @see GenomeBrowser
     */
	public static final Color border = new Color(131,131,131);
    
    /**
     * Titres affiches pour le choix de langue anglais.
     */
	private static final String englishPackage[] = {"Species","Search","Image","Genome Browser","Pathway browser","Gene ID"," Map ID","Gene information",
													"Reaction information","Involved in reactions(s)","Involves gene(s)","Do you want to save your session before leaving?",
													"Quit Metagene?","Error","You must first click on a metabolic pattern reaction.","The organism or identifier is incorrect for metabolism.",
													"The organism or identifier is incorrect for genome.","This gene is not involved in any reaction.",
													"This reaction does not exist in this organism or no gene\nhas yet been identified."};
    
    /**
     * Titres affiches pour le choix de langue francais.
     */
	private static final String frenchPackage[] = {"Espèce","Recherche","Image","Affichage Génome","Affichage Métabolisme", "ID Gène","ID Carte", "Information Gène",
													"Information Reaction", "Réaction(s) Associée(s)","Gène(s) Associé(s)","Voulez-vous vous sauvegarder votre session\navant de quitter?",
													"Quitter Metagene?","Erreur","Vous devez d'abord cliquer sur une réaction du schéma métabolique.","L'organisme ou l'identifiant est incorrecte pour le métabolisme.",
													"L'organisme ou l'identifiant est incorrecte pour le génome.","Ce gène n'intervient dans aucune réaction.",
													"Cette réaction n'existe pas dans cet organisme ou \naucun gène n'a encore été identifié."};
    
    /**
     * Le pack de la langue active (anglais par defaut)
     */
	private static String languagePackage[] = englishPackage;
    
    /**
     * Le choix de la langue (anglais par defaut)
     */
	private static char activeLanguage = 'e';
    
    /**
     * Taille des titres
     * @see Bar
     */
	public static int titleSize = 190;
    
    /**
     * Taille du bouton "Recherche"
     * @see Bar
     */
	public static int buttonSearchSize = 110;
    
    /**
     * Definition de taille minimum de la fenetre
     * @see GUI
     */
	public static int minimumSize = 1100;
	
    /**
     * Valeur dans le package de langue
     */
	public static String display(int i){
		return languagePackage[i];
	}

	//------------------------------------------------------------------
	//				Methodes
	//------------------------------------------------------------------
    /**
     * Changement de la langue des textes
     * @see WelcomeWindow
     */
	public static void switchActiveLanguage(char newLanguage){
		activeLanguage = newLanguage;
	}
    
    /**
     * Lecture de la nouvelle langue afin d'adapter des parametres
     * @see WelcomeWindow
     */
	public static void initiateActiveLanguage(){
		if (activeLanguage == 'f'){
			languagePackage = frenchPackage;
			titleSize = 250;
			buttonSearchSize = 135;
			minimumSize = 1195;
		}
	}
	
	/**
     * Recuperation des images du metabolisme. Si l'image n'existe pas 
     * en local (presente sur l'ordinateur), elle est telechargee.
     * 
     * @param nom concatenation de m'organisme et du metabolisme.
     * @param cheminFichier chemin du dossier qui stocke les images.
     * @see UpdatePathway#start
     */
	public static void extractImage(String nom, String cheminFichier){
    	BufferedImage image = null;

		File nomfichier = new File(cheminFichier +"/"+ nom + ".png");
		//Si l'image n'existe pas en local, on la télécharge et on l'enregistre
		if (!nomfichier.exists()){
				try {
					URL url = new URL("http://rest.kegg.jp/get/" + nom + "/image");
					image = ImageIO.read(url);
					ImageIO.write( image, "PNG", nomfichier);//ou JPG
					
				} catch (IOException e) {

					ImageIcon img = new ImageIcon("Resources/erreur.png");
					JOptionPane.showMessageDialog(null, Tool.display(15), Tool.display(13), JOptionPane.ERROR_MESSAGE, img);
					//e.printStackTrace();
				}
		}
	}
    
	/**
     * Recuperation du chemin de la localisation des images du
     * metabolisme (dossier "Metabolisme/image" qui contient les images
     * deja telechargees).
     */
	public static String imageMetPath(){
    	String path =new File("").getAbsolutePath();
    	path = path +"/Metabolisme";
    	File dir =new File(path);
    	dir.mkdir();
    	path = path +"/Images";
    	dir =new File(path);
    	dir.mkdir();
    	return path;
	}
    
	/**
     * Recuperation des fichiers conf des organismes. Si le fichier conf
     * n'existe pas en local (present sur l'ordinateur), il est telecharge.
     * @param orgamet concatenation de l'organisme et du metabolisme.
     * @see UpdatePathway#start
     * @see UpdateGenome#start
     */
	public static void extractOrgaConf(String orgamet){
    	String pathOrgaConf = orgaconfPath();
		File nomfichier = new File(pathOrgaConf +"/"+orgamet+ ".txt");
		//Si le texte n'existe pas en local, on le télécharge et on l'enregistre
		if (!nomfichier.exists()){
			try {	
				URL url = new URL("http://rest.kegg.jp/get/" + orgamet + "/conf");
				Tool.downloadTxtFile (url, orgamet, pathOrgaConf);
			}catch(IOException ex){
				ex.printStackTrace();
			}
		}
	}

    /**
     * Recuperation du chemin de la localisation des fichiers conf de
     * l'organisme (dossier "orgaconf" qui contient les fichiers conf 
     * deja telecharges).
     */
	public static String orgaconfPath(){
    	String pathOrgaConf =new File("").getAbsolutePath();
    	pathOrgaConf = pathOrgaConf +"/Metabolisme";
    	File dir =new File(pathOrgaConf);
    	dir.mkdir();
    	pathOrgaConf = pathOrgaConf +"/OrgaConf";
    	dir =new File(pathOrgaConf);
    	dir.mkdir();
    	return pathOrgaConf;
	}
	
    /**
     * Recuperation des fichiers conf des map. Si le fichier conf
     * n'existe pas en local (present sur l'ordinateur), il est telecharge.
     * @param id du metabolisme.
     * @see UpdatePathway#start
     * @see UpdateGenome#start
     */
	public static void extractMapConf(String met){
    	String pathMapConf = mapconfPath();
		File nomfichier = new File(pathMapConf +"/"+ met + ".txt");
		//Si le texte n'existe pas en local, on le télécharge et on l'enregistre
		if (!nomfichier.exists()){
			try {
				//Création de l'url
				URL url = new URL("http://rest.kegg.jp/get/map" + met + "/conf");
				Tool.downloadTxtFile (url, met, pathMapConf);
			}catch(IOException ex){
				//ex.printStackTrace();
			}
		}
	}

    /**
     * Recuperation du chemin de la localisation des fichiers conf des
     * map (dossier "mapconf" qui contient les fichiers conf deja
     * telecharges).
     */
	public static String mapconfPath(){
    	String pathMapConf =new File("").getAbsolutePath();
    	pathMapConf = pathMapConf +"/Metabolisme";
    	File dir =new File(pathMapConf);
    	dir.mkdir();
    	pathMapConf = pathMapConf +"/MapConf";
    	dir =new File(pathMapConf);
    	dir.mkdir();
    	return pathMapConf;
	}

    /**
     * Recuperation des informations sur les gènes (descriptions).
     * Si le fichier de description n'existe pas en local (present sur
     * l'ordinateur), il est telecharge.
     * 
     * @param inforgagene concatenation de l'organisme et du gene.
     * @see UpdateGenome#start
     */
	public static void extractInfoGene(String inforgagene){
    	String pathGene = genePath();

		File nomfichier = new File(pathGene +"/"+inforgagene+ ".txt");
		//Si le texte n'existe pas en local, on le télécharge et on l'enregistre
		if (!nomfichier.exists()){
			try {
				//Création de l'url
				URL url = new URL("http://rest.kegg.jp/get/" + inforgagene);
                Tool.downloadTxtFile (url, inforgagene, pathGene);
            }catch(IOException ex){
				//ex.printStackTrace();
			}  
		}
	}

    /**
     * Recuperation du chemin de la localisation des fichiers de 
     * description des genes (dossier "InfoGenes" qui contient les infos
     * des gènes deja telechargees).
     */
	public static String genePath(){
    	String pathGene =new File("").getAbsolutePath();
    	pathGene = pathGene +"/InfoGenes";
    	File dir =new File(pathGene);
    	dir.mkdir();
    	return pathGene;
	}
	
    /**
     * Recuperation des informations sur les reactions.
     * Si le fichier de description n'existe pas en local (present sur
     * l'ordinateur), il est telecharge.
     * 
     * @param ID ID de la reaction
     * @see UpdatePathway#start
     */
	public static void extractReaction(String ID){
    	String pathReaction = reactionPath();

		File nomfichier = new File(pathReaction +"/"+ID+ ".txt");
		//Si le texte n'existe pas dans en local, on le télécharge et on l'enregistre
		if (!nomfichier.exists()){
			try {
				//Création de l'url
				URL url = new URL("http://rest.kegg.jp/get/rn:" + ID);
                Tool.downloadTxtFile (url, ID, pathReaction);
            }catch(IOException ex){
				//ex.printStackTrace();
			}  
		}
	}
	
    /**
     * Recuperation du chemin de la localisation des fichiers de 
     * description des reactions (dossier "reaction" qui contient les
     * infos des reactions deja telechargees)
     */
	public static String reactionPath(){
    	String pathReaction =new File("").getAbsolutePath();
    	pathReaction = pathReaction +"/Metabolisme";
    	File dir =new File(pathReaction);
    	dir.mkdir();
    	pathReaction = pathReaction +"/Reactions";
    	dir =new File(pathReaction);
    	dir.mkdir();
    	return pathReaction;
	}
    
    /**
     * Recuperation et stockage des informations textes de l'API Kegg,
     * par la lecture des informations textes sur le site de l'url et
     * enregistrement de ces donnees dans un nouveau fichier texte, dans
     * le dossier specifie par le chemin.
     * @param url l'url de l'information a recuperer
     * @param typetxt orgamet/orgagene
     * @param path chemin ou stocker le fichier
     */
	public static void downloadTxtFile (URL url, String typetxt, String path){
		try {
				//connection au site internet
				URLConnection uc = url.openConnection();
				//création des objets de stockage du flux de données
				InputStream in = uc.getInputStream();
				StringBuilder build = new StringBuilder();
				//Lecture de la page internet
				int c = in.read();
				while (c != -1) {
					build.append((char) c); //Stockage de l'information dans l'objet
					c = in.read(); 
				}
				String txt = build.toString();
				  //Enregistrement de l'objet dans un fichier txt
				  try{
						FileWriter file = new FileWriter(path +"/"+ typetxt+".txt");
						file.write(txt);
						file.close();
				  }catch(IOException ex){
						ex.printStackTrace();
				  }
		  } catch (MalformedURLException e) {
				e.printStackTrace();
		  } catch (IOException e) {
                if (path.endsWith("/InfoGenes")){
                    ImageIcon img = new ImageIcon("Resources/erreur.png");
					JOptionPane.showMessageDialog(null, Tool.display(16), Tool.display(13), JOptionPane.ERROR_MESSAGE, img);
                }
                //e.printStackTrace();
		  }
	}
	
    /**
     * Parseur des fichiers conf de l'organisme: Lecture des informations
     * des fichiers conf de l'organisme, puis enregistrement de ces 
     * donnees dans un hash (clef:valeur) ou la clef correspond a la liste
     * des coordonnees des carres des genes, et la valeur a la liste des
     * genes correspondants.
     * 
     * @param orgamet concatenation de l'organisme et du metabolisme.
     * @see UpdateGenome#start
     * @see UpdatePathway#start
     */
	//Crée le hash pour le .conf de l'organisme
	public static HashMap< List<Integer>, List<String> > parserConfOrga(String orgamet){
		//Crée le hash qui contiendra le .conf
		HashMap< List<Integer>, List<String> > hashConfOrga =  new HashMap< List<Integer>, List<String> >();
		List<Integer> key;
		try (BufferedReader buffer = new BufferedReader(new FileReader(Tool.orgaconfPath()+"/"+orgamet+ ".txt"))) {
			String line=null;
			Pattern pattern1 = Pattern.compile("\\([0-9]+,[0-9]+\\) \\([0-9]+,[0-9]+\\)"); //Fabrication de l'expression régulière à trouver
			while((line=buffer.readLine())!=null){ //Lecture du fichier ligne par ligne
				Matcher matcher1 = pattern1.matcher(line); 
				while (matcher1.find()){ //Si correspondance avec l'expression régulière trouvée
					String group1 = matcher1.group(); //Récupération de l'expression régulière
					String str1[]=group1.split("[(,)]"); //split dans un tableau qui contiendra 7 cases dt 3 vides ([0][3][6])
					key = new ArrayList<Integer>();
					key.add(Integer.parseInt(str1[1]));
					key.add(Integer.parseInt(str1[2]));
					key.add(Integer.parseInt(str1[4]));
					key.add(Integer.parseInt(str1[5]));
					//Récupération de la liste des gènes impliqués
					Pattern pattern2 = Pattern.compile("([a-z]{2,4}:[a-z]{0,1}[0-9]+\\+{0,1})+"); //Fabrication de l'expression régulière à trouver
					Matcher matcher2 = pattern2.matcher(line);
					while (matcher2.find()){ //Si correspondance avec l'expression régulière trouvée
						String group2 = matcher2.group(); //ex: group2 <=> "eco:b1393+eco:b1000"
						String str2[]=group2.split("[:+]");
						List<String> listGenes = new ArrayList<String>();
						for(int j = 1; j <= str2.length; j+=2){ //La liste des gènes est mise dans une liste
								listGenes.add(str2[j]);
						}
						//Enregistre les coordonnées du carré et la liste des gènes dans le hash
						hashConfOrga.put(key,listGenes);
					} //Fin while
				} //Fin while
				
			} //Fin while Lecture fichier
		}catch (IOException ex) {
			//ex.printStackTrace();
		}
		return hashConfOrga;
	}
	
	/**
     * Parseur des fichiers conf de la map: Lecture des informations
     * des fichiers conf de la map, puis enregistrement de ces 
     * donnees dans un hash (clef:valeur) ou la clef correspond a la liste
     * des coordonnees des carres des reaction, et la valeur a la liste des
     * reactions correspondantes.
     * 
     * @param ID id du metabolisme.
     * @see UpdateGenome#start
     * @see UpdatePathway#start
     */
	//Crée le hash qui contiendra le .conf pour la map
	public static HashMap< List<Integer>, String >  parserConfMap(String ID){	
		HashMap< List<Integer>, String > hashConfMap =  new HashMap< List<Integer>, String >();
		List<Integer> key;
		String reaction;
		try (BufferedReader buffer = new BufferedReader(new FileReader(Tool.mapconfPath()+"/"+ ID + ".txt"))) {
			String line=null;
			Pattern pattern1 = Pattern.compile("\\([0-9]+,[0-9]+\\) \\([0-9]+,[0-9]+\\)"); //Fabrication de l'expression régulière à trouver
			//~ StringBuilder build = new StringBuilder();
			while(((line=buffer.readLine())!=null)){
				Matcher matcher1 = pattern1.matcher(line); 
				while (matcher1.find()){ //Si correspondance avec l'expression régulière trouvée
					String group1 = matcher1.group(); //Récupération de l'expression régulière
					String str1[]=group1.split("[(,)]"); //split dans un tableau qui contiendra 7 cases dt 3 vides ([0][3][6])
					key = new ArrayList<Integer>();
					key.add(Integer.parseInt(str1[1]));
					key.add(Integer.parseInt(str1[2]));
					key.add(Integer.parseInt(str1[4]));
					key.add(Integer.parseInt(str1[5]));
					//Récupération de la réaction impliqués
					Pattern pattern2 = Pattern.compile(" R[0-9]+"); //Fabrication de l'expression régulière à trouver
					Matcher matcher2 = pattern2.matcher(line);
					while (matcher2.find()){ //Si correspondance avec l'expression régulière trouvée
						reaction = matcher2.group(); //ex: reaction <=> " R00947"
						reaction=reaction.substring(1); //Enlève le premier caractère de la chaine (ici " ")
						//enregistre les coordonnées du carré et le nom de la réaction
						hashConfMap.put(key,reaction);
					} //Fin while
				} //Fin while

			} //Fin while Lecture fichier
		}catch (IOException ex) {
			//ex.printStackTrace();
		}
		return hashConfMap;
	}
	
	/**
	 * Renvoie la liste des organismes present dans le repertoire image
	 * des metabolismes
	 * 
	 * @return Une liste des organismes present dans le repertoire
	 * 
	 * @see Bar
	 */
	public static List<String> recoverListOrgaMet(){
		File repertoire = new File(imageMetPath());
		String [] fileList;
		List<String>  listOrga = new ArrayList<String>();
		if (repertoire.exists() && repertoire.isDirectory()){
			fileList=repertoire.list();
			Pattern pattern = Pattern.compile("^[a-zA-Z]{3}");
			for(String orga : fileList){
				Matcher matcher = pattern.matcher(orga);
				if(matcher.find()){
					if(!listOrga.contains(matcher.group())){
						listOrga.add(matcher.group());
					}
				}
			}
		}
		return listOrga;
	}
	
	/**
	 * Renvoie la liste des organismes present dans le repertoire infosGene
	 * pour le genome
	 * 
	 * @return Une liste des organismes present dans le repertoire
	 * 
	 * @see Bar
	 */
	public static List<String> recoverListOrgaGene(){
		File repertoire = new File(genePath());
		String [] fileList;
		List<String>  listOrga = new ArrayList<String>();
		if (repertoire.exists() && repertoire.isDirectory()){
			fileList=repertoire.list();
			Pattern pattern = Pattern.compile("^[a-zA-Z]{3}");
			for(String orga : fileList){
				Matcher matcher = pattern.matcher(orga);
				if(matcher.find()){
					if(!listOrga.contains(matcher.group())){
						listOrga.add(matcher.group());
					}
				}
			}
		}
		return listOrga;
	}
	
	/**
	 * Renvoie la liste des identifiants present dans le repertoire image
	 * des metabolismes
	 * 
	 * @return Une liste des identifiants present dans le repertoire
	 * 
	 * @see Bar
	 */
	public static List<String> recoverListIDMet(){
		File repertoire = new File(imageMetPath());
		String [] fileList;
		List<String>  listID = new ArrayList<String>();
		if (repertoire.exists() && repertoire.isDirectory()){
			fileList=repertoire.list();
			Pattern pattern = Pattern.compile("[0-9]{5}");
			for(String ID : fileList){
				Matcher matcher = pattern.matcher(ID);
				if(matcher.find()){
					if(!listID.contains(matcher.group())){
						listID.add(matcher.group());
					}
				}
			}
		}
		return listID;
	}
	/**
	 * Renvoie la liste des organismes present dans le repertoire infosGene
	 * pour le genome
	 * 
	 * @return Une liste des organismes present dans le repertoire
	 * 
	 * @see Bar
	 */
	public static List<String> recoverListIDGene(){
		File repertoire = new File(genePath());
		String [] fileList;
		List<String>  listID = new ArrayList<String>();
		if (repertoire.exists() && repertoire.isDirectory()){
			fileList=repertoire.list();
			Pattern pattern = Pattern.compile("[a-zA-Z0-9]{1}[0-9]{4}");
			for(String ID : fileList){
				Matcher matcher = pattern.matcher(ID);
				if(matcher.find()){
					if(!listID.contains(matcher.group())){
						listID.add(matcher.group());
					}
				}
			}
		}
		return listID;
	}
}


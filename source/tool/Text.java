/**
 * Tool.java
 */
package tool;

/**
 * <b>Tool contient toutes les fonctions utiles au programme.</b>
 * 
 * @author Coralie Rohmer && Marine Aglave
 */
 
public class Text{
    /**
     * Titres affiches pour le choix de langue anglais.
     */
	private static final String englishPackage[] = {};
    
    /**
     * Titres affiches pour le choix de langue francais.
     */
	private static final String frenchPackage[] = {};
    
    /**
     * Le pack de la langue active (Français par defaut)
     */
	private static String languagePackage[] = frenchPackage;
    
    /**
     * Le choix de la langue (anglais par defaut)
     */
	private static char activeLanguage = 'f';

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
		if (activeLanguage == 'e'){
			languagePackage = englishPackage;
		}
	}
}

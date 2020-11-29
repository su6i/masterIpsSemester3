package com.masterips.javaeeproject.service;

import java.util.List;

import com.masterips.javaeeproject.entities.Lieu;
import com.masterips.javaeeproject.entities.Monument;
import com.masterips.javaeeproject.entities.Celebrite;
import com.masterips.javaeeproject.entities.Departement;

public interface AppService {
	
//	— pouvoir consulter la base de données au sujet des communes, de leurs monuments ou de leurs célébrités (prévoyez un jeu d’au moins 10 requètes à cet effet)
//	— pouvoir mettre à jour la base de données au travers d’opérations de: 
//																		modification, 
//																		suppression et 
//																		insertion (prévoyez au moins un exemple de chaque)

//	— pouvoir lancer des traitements sur les données : par exemple 
//															pouvoir connaître la distance entre deux monuments cibles dans la même commune ou 
//															dans deux communes différentes
//	
//	— pouvoir se connecter au système au travers de différents rôles et donc de différents droits en matière d’accès en lecture/écriture sur les données. Vous pouvez par exemple 
//		définir les rôles 
//						”Administrateur”, 
//						”Voyagiste”, 
//						”Touriste” qui donneront lieu à des vues externes différentes du système.
	
//	— ...
//	L’originalité sera une plus-value à votre travail et les aspects relatifs à l'orgonomie et à la restitution visuelle seront également considérés.

	
	public void addDepartement(Departement departement);
	public Departement getDepartement(String numDep);
	
	public void addLieu(Lieu lieu);
	
	public void addMonument(Monument monument);
	public void addMonumentToLieu(Long codeM, String codeInsee);
	public float getDistanceBetweenMonuments(String nomMonA,String nomMonB);
	public List<Monument> getListMonumentsByDep(String nomDep);
	public List<Monument> getListMonumentsByLieu(String nomCom);
	
	public Celebrite addCelebrite(Celebrite celebrite);
	

	//public AssocieA addAssociation(AssocieA associeA);

	
}

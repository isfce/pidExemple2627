package org.isfce.pid.controller;

import org.isfce.pid.model.Professeur;
import org.isfce.pid.service.ProfesseurServices;
import org.isfce.pid.service.exception.NotExistException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

/**
 * 
 * @author Didier
 *
 */
@Slf4j
@Controller
@RequestMapping("/professeur")
public class ProfesseurController {

	ProfesseurServices serviceProfesseurs;

	/**
	 * Construction avec autowired des services et de l'encodeur
	 * 
	 * @param serviceProfesseurs
	 */
	public ProfesseurController(ProfesseurServices serviceProfesseurs) {
		this.serviceProfesseurs = serviceProfesseurs;
	}

	/**
	 * Liste des professeurs
	 * 
	 * @param model
	 * @return vue
	 */
	@GetMapping("/liste")
	public String getProfesseurs(Model model) {
		model.addAttribute("professeurList", serviceProfesseurs.findAll());
		return "professeur/listeProfesseur";
	}

	/**
	 * Détail d'un prof à partir de son id via une 'path' variable
	 * 
	 * @param username
	 * @param model
	 * @param auth
	 * @return la vue du professeur
	 */
	@GetMapping("/{id}")
	public String getProfesseur(@PathVariable("id") String usernameIn, Model model) {
		String username=usernameIn.toUpperCase();
		log.info("Professeur par id: " + username);
		// Verifie si pas dans la Map suite à d'une redirection avec FlashAttribut
		if (!model.containsAttribute("professeur")) { // Récupère le professeur sinon déclenche une exception
			Professeur prof = serviceProfesseurs.getById(username).orElseThrow(() -> new NotExistException(username));
			model.addAttribute("professeur", prof);
		}
		return "professeur/professeur";
	}
	
	@GetMapping("/")
	public String getProfesseur2(@RequestParam("username") String username, Model model) {
		//String username=usernameIn.toUpperCase();
		log.info("Professeur par id2: " + username);
		// Verifie si pas dans la Map suite à d'une redirection avec FlashAttribut
		if (!model.containsAttribute("professeur")) { // Récupère le professeur sinon déclenche une exception
			Professeur prof = serviceProfesseurs.getById(username).orElseThrow(() -> new NotExistException(username));
			model.addAttribute("professeur", prof);
		}
		return "professeur/professeur";
	}

	/**
	 * Méthode Get pour ajouter un Professeur
	 * 
	 * @param prof
	 * @param model
	 * @return
	 */

	@GetMapping("/add")
	public String addProfesseurGet(@ModelAttribute("professeur") Professeur prof, Model model) {
		log.debug("affiche la vue pour ajouter un professeur ");
		return "professeur/addProfesseur";
	}

	/**
	 * Post pour Ajout d'un professeur Ici on fait tous les tests d'erreur avant
	 * l'ajout ==> plusieurs accès à la bd mais en présence de plusieurs types
	 * d'erreur, on revient à la vue avec toutes celle-ci (ça évite de revenir
	 * plusieurs fois sur une même vue avec des erreurs)
	 * 
	 * @param profDto
	 * @param errors
	 * @return
	 */
	@PostMapping("/add")
	public String addProfesseurPost(@Valid @ModelAttribute(name = "professeur") Professeur prof,
			BindingResult errors) {
		log.debug(" Post Professeur");

		// Gestion de la validation avec toutes les erreurs de données possibles
		if (errors.hasErrors()) {
			log.debug("Erreurs dans les données:" + prof.getUsername());
			return "professeur/addProfesseur";
		}

		try {
			// Sauvegarde
			prof = serviceProfesseurs.save(prof);
		} catch (Exception e) {
			log.debug("Erreur Lors de la sauvagarde: ", e.getMessage());
			// problème technique
			errors.reject("error.persistance", "Exception de persistance");
			return "professeur/addProfesseur";
		}

		return "redirect:/professeur/liste";
	}

}

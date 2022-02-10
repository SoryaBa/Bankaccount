package bankaccount.exaltit.service;

import bankaccount.exaltit.data.model.Compte;

public interface CompteService {

	/**
	 * Enregistre le compte passe en parametre
	 */
	Compte save(Compte compte);

	/**
	 * Trouver le compte correspondant à l'identifiant passé en parametre
	 */
	Compte findById(Long id);

}

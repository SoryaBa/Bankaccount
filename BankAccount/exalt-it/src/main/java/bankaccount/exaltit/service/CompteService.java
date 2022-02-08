package bankaccount.exaltit.service;

import bankaccount.exaltit.data.dto.CompteDTO;
import bankaccount.exaltit.data.model.Compte;

public interface CompteService {
	
	Compte getCompteById(Long id);

	/**
	 * Enregistre le compte passe en parametre
	 */
	Compte save(Compte compte);

}

package bankaccount.exaltit.service.serviceImpl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bankaccount.exaltit.controller.ClientController;
import bankaccount.exaltit.data.model.Compte;
import bankaccount.exaltit.data.repository.CompteRepository;
import bankaccount.exaltit.service.CompteService;

@Service
public class CompteServiceImpl implements CompteService {
	
	Logger log = LoggerFactory.getLogger(ClientController.class);

	@Autowired
	CompteRepository compteRepository;

	// Constructeur utile pour les tests
	public CompteServiceImpl(CompteRepository compteRepository) {
		this.compteRepository = compteRepository;
	}

	/**
	 * Trouver le compte correspondant à l'identifiant passé en parametre
	 */
	@Override
	public Compte findById(Long id) {
		return compteRepository.findById(id).get();
	}

	/**
	 * Enregistrer le compte passé en parametre
	 */
	@Override
	public Compte save(Compte compte) {
		log.info("sauvegarde du compte " + compte.getLibelle());
		return compteRepository.save(compte);
	}

}
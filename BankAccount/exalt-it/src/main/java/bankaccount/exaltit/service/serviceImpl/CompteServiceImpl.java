package bankaccount.exaltit.service.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import bankaccount.exaltit.data.model.Compte;
import bankaccount.exaltit.data.repository.CompteRepository;
import bankaccount.exaltit.service.CompteService;

@Service
public class CompteServiceImpl implements CompteService {
	
	@Autowired
	CompteRepository compteRepository;

	// Constructeur utile pour les tests
	public CompteServiceImpl(CompteRepository compteRepository) {
		this.compteRepository = compteRepository;
	}

	/**
	 * Trouve le compte correspondant a l identifiant passe en parametre
	 */
	@Override
	public Compte getCompteById(Long id) {
		return null;
	}

	/**
	 * Enregistre le compte passe en parametre
	 */
	@Override
	public Compte save(Compte compte) {
		return null;
	}

}
package bankaccount.exaltit.service.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import bankaccount.exaltit.data.model.Client;
import bankaccount.exaltit.data.model.Compte;
import bankaccount.exaltit.data.model.Operation;
import bankaccount.exaltit.data.repository.OperationRepository;
import bankaccount.exaltit.service.OperationService;

@Service
public class OperationServiceImpl implements OperationService {
	
	@Autowired
	private OperationRepository operationRepository;

	// Constructeur utile pour les tests
	public OperationServiceImpl(OperationRepository operationRepository) {
		this.operationRepository = operationRepository;
	}

	@Override
	public Operation getOperationById(Long id) {
		return null;
	}

	/**
	 * Trouve les operations du compte passé en parametre
	 */
	@Override
	public List<Operation> getOperationByCompte(Compte compte) {
		return null;
	}

	/**
	 * Trouve les operations du client passé en parametre
	 */
	@Override
	public List<Operation> getOperationByClient(Client Client) {
		return null;
	}

	/**
	 * Trouve les operations du client passé en parametre sur le compte passé en
	 * parametre
	 */
	@Override
	public List<Operation> getOperationByClientAndCompte(Long clientID, Long compteID) {
		return null;
	}

	/**
	 * Effectue l'enregistrement d'une operation passé en parametre
	 */
	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public Operation save(Operation operation) {
		return null;
	}

}

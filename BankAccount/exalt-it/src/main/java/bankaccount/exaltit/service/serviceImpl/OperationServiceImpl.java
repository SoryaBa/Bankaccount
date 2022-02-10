package bankaccount.exaltit.service.serviceImpl;

import java.sql.Timestamp;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import bankaccount.exaltit.controller.ClientController;
import bankaccount.exaltit.data.model.Client;
import bankaccount.exaltit.data.model.Compte;
import bankaccount.exaltit.data.model.Operation;
import bankaccount.exaltit.data.repository.OperationRepository;
import bankaccount.exaltit.exception.ExceptionIllegalOperation;
import bankaccount.exaltit.exception.ExceptionNotEnough;
import bankaccount.exaltit.service.ClientService;
import bankaccount.exaltit.service.CompteService;
import bankaccount.exaltit.service.OperationService;

@Service
public class OperationServiceImpl implements OperationService {

	Logger log = LoggerFactory.getLogger(ClientController.class);

	@Autowired
	private OperationRepository operationRepository;
	@Autowired
	private ClientService clientService;
	@Autowired
	private CompteService compteService;

	// Constructeur utile pour les tests
	public OperationServiceImpl(OperationRepository operationRepository, ClientService clientService,
			CompteService compteService) {
		this.operationRepository = operationRepository;
		this.clientService = clientService;
		this.compteService = compteService;
	}

	@Override
	public Operation findById(Long id) {
		return operationRepository.findById(id).get();
	}

	/**
	 * Trouve les operations du compte passé en parametre
	 */
	@Override
	public List<Operation> getOperationByCompte(Long compteID) {
		Compte compte = compteService.findById(compteID);
		return operationRepository.findByCompte(compte);
	}

	/**
	 * Trouve les operations du client passé en parametre
	 */
	@Override
	public List<Operation> getOperationByClient(Long clientID) {
		Client client = clientService.findById(clientID);
		return operationRepository.findByClient(client);
	}

	/**
	 * Trouve les operations du client passé en parametre sur le compte passé en
	 * parametre
	 */
	@Override
	public List<Operation> getOperationByClientAndCompte(Long clientID, Long compteID) {
		Client client = clientService.findById(clientID);
		Compte compte = compteService.findById(compteID);

		return operationRepository.findByClientAndCompte(client, compte);
	}

	/**
	 * Effectue l'enregistrement d'une operation passé en parametre <br>
	 * Pour faire un depot dans le compte, utiliser le credit de l'operation <br>
	 * Pour faire un retrait dans le compte, utiliser le debit de l'operation <br>
	 * "Une operation ne peut pas contenir un nombre négatif. Une operation ne doit
	 * contenir qu'un seul nombre possitif soit au debit ou au credit"
	 * 
	 * @throws ExceptionIllegalOperation
	 * @throws ExceptionNotEnough
	 */
	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public Operation save(Operation operation, Long clientID, Long compteID)
			throws ExceptionIllegalOperation, ExceptionNotEnough {

		if (operation.getCredit() < 0 || operation.getDebit() < 0
				|| (operation.getCredit() > 0 && operation.getDebit() > 0)) {

			log.debug("Tentative d'enregistrement d'une operation invalide");
			log.error("Tentative d'enregistrement d'une operation invalide");
			throw new ExceptionIllegalOperation(
					"Une operation ne peut pas contenir un nombre négatif. Une operation ne doit contenir qu'un seul nombre positif soit au debit ou au credit");
		}

		Compte compte = compteService.findById(compteID);
		double newSolde = compte.getSolde();
		newSolde += operation.getCredit() > 0 ? operation.getCredit() : -operation.getDebit();
		if (newSolde < 0) {
			log.debug("Tentative d'enregistrement d'une operation sans provision suffisante");
			log.error("Tentative d'enregistrement d'une operation sans provision suffisante");
			throw new ExceptionNotEnough(
					"Cette operation n'est pas possible vous n'avez pas assez d'avoir pour cette operation");
		}

		Client client = clientService.findById(clientID);
		compte.setSolde(newSolde);
		operation.setDateCreation(new Timestamp(System.currentTimeMillis()));
		operation.setClient(client);
		operation.setCompte(compte);
		compteService.save(compte);

		return operationRepository.save(operation);
	}

}

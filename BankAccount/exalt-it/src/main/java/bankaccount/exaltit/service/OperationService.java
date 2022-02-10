package bankaccount.exaltit.service;

import java.util.List;

import bankaccount.exaltit.data.model.Operation;
import bankaccount.exaltit.exception.ExceptionIllegalOperation;
import bankaccount.exaltit.exception.ExceptionNotEnough;

public interface OperationService {

	Operation findById(Long id);

	List<Operation> getOperationByClientAndCompte(Long clientID, Long compteID);

	/**
	 * Trouve les operations du client passé en parametre
	 */
	List<Operation> getOperationByClient(Long clientID);

	/**
	 * Trouve les operations du compte passé en parametre
	 */
	List<Operation> getOperationByCompte(Long compteID);

	/**
	 * Effectue l'enregistrement d'une operation passé en parametre
	 * @throws ExceptionNotEnough  Si vous n'avez pas assez d'avoir pour effectuer cette operation
	 * @throws ExceptionIllegalOperation Si votre operation n'est pas valide : credit > 0 && debit > 0 || credit < 0 || debit < 0
	 */
	Operation save(Operation operation, Long clientID, Long compteID) throws ExceptionIllegalOperation, ExceptionNotEnough;

}

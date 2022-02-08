package bankaccount.exaltit.service;

import java.util.List;

import bankaccount.exaltit.data.model.Client;
import bankaccount.exaltit.data.model.Compte;
import bankaccount.exaltit.data.model.Operation;

public interface OperationService {

	Operation getOperationById(Long id);

	List<Operation> getOperationByCompte(Compte compte);

	List<Operation> getOperationByClient(Client Client);

	List<Operation> getOperationByClientAndCompte(Long clientID, Long compteID);

	Operation save(Operation operation);

}

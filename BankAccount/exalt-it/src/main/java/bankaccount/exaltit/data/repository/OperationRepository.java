package bankaccount.exaltit.data.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import bankaccount.exaltit.data.model.Client;
import bankaccount.exaltit.data.model.Compte;
import bankaccount.exaltit.data.model.Operation;

@Repository
public interface OperationRepository extends CrudRepository<Operation, Long> {

	List<Operation> findByCompte(Compte compte);
	
	List<Operation> findByClient(Client client);
	
	Optional<Operation> findById(Long id);
	
	List<Operation> findByClientAndCompte(Client client, Compte compte);
}
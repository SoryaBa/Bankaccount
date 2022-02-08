package bankaccount.exaltit.data.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import bankaccount.exaltit.data.model.Client;

@Repository
public interface ClientRepository extends CrudRepository<Client, Long> {

	Optional<Client> findByEmail(String email);
	
	Optional<Client> findById(Long id);	
	
	Iterable<Client> findAll();
	
}

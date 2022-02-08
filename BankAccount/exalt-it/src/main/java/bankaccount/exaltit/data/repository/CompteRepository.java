package bankaccount.exaltit.data.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import bankaccount.exaltit.data.model.Compte;

@Repository
public interface CompteRepository extends CrudRepository<Compte, Long>{

	Optional<Compte> findById(Long id);
	
}

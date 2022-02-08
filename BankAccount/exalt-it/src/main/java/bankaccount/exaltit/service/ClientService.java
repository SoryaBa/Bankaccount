package bankaccount.exaltit.service;

import java.util.List;

import bankaccount.exaltit.data.model.Client;

public interface ClientService {
	
	Client findByEmail(String email);
	
	Client findById(Long id);
	
	Client save(Client client);
	

	/**
	 * Retourne tous les clients
	 */
	List<Client> getAll();
	
}

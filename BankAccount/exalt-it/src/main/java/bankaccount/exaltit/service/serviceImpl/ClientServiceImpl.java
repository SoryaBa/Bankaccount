package bankaccount.exaltit.service.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bankaccount.exaltit.data.model.Client;
import bankaccount.exaltit.data.repository.ClientRepository;
import bankaccount.exaltit.service.ClientService;

@Service
public class ClientServiceImpl implements ClientService {

	@Autowired
	private ClientRepository clientRepository;

	// Constructeur utile pour les tests
	public ClientServiceImpl(ClientRepository clientRepository) {
		this.clientRepository = clientRepository;
	}

	/**
	 * Trouve le client possedant cet email
	 */
	@Override
	public Client findByEmail(String email) {
		return null;
	}

	/**
	 * Trouve le client correspondant a cet idenfitiant
	 */
	@Override
	public Client findById(Long id) {
		return null;
	}

	/**
	 * Enregistre le client passe en parametre
	 */
	@Override
	public Client save(Client client) {
		return null;
	}

	/**
	 * Retourne tous les clients
	 */
	@Override
	public List<Client> getAll() {
		return null;
	}
	
}

package bankaccount.exaltit.service.serviceImpl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bankaccount.exaltit.controller.ClientController;
import bankaccount.exaltit.data.model.Client;
import bankaccount.exaltit.data.repository.ClientRepository;
import bankaccount.exaltit.service.ClientService;

@Service
public class ClientServiceImpl implements ClientService {
	Logger log = LoggerFactory.getLogger(ClientController.class);

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
		return clientRepository.findByEmail(email).get();
	}

	/**
	 * Trouve le client correspondant a cet idenfitiant
	 */
	@Override
	public Client findById(Long id) {
		return clientRepository.findById(id).get();
	}

	/**
	 * Enregistre le client passe en parametre
	 */
	@Override
	public Client save(Client client) {
		log.info("sauvegarde du client " + client.getEmail());
		return clientRepository.save(client);
	}

	/**
	 * Retourne tous les clients
	 */
	@Override
	public List<Client> getAll() {
		return (List<Client>) clientRepository.findAll();
	}

}

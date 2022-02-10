package bankaccount.exaltit.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import bankaccount.exaltit.data.dto.AdresseDTO;
import bankaccount.exaltit.data.dto.ClientDTO;
import bankaccount.exaltit.data.dto.CompteDTO;
import bankaccount.exaltit.data.model.Adresse;
import bankaccount.exaltit.data.model.Client;
import bankaccount.exaltit.data.model.Compte;
import bankaccount.exaltit.service.ClientService;

@RestController
public class ClientController {

	@Autowired
	private ClientService clientService;

	@Autowired
	private ModelMapper modelMapper;
	
	Logger logger = LoggerFactory.getLogger(ClientController.class);

	/**
	 * Trouver le client par son email
	 * 
	 * @param email
	 * @return
	 */
	@GetMapping("/client")
	public ClientDTO getClientByEmail(@RequestParam(name = "email") String email) {
		logger.info("demande du client "+ email);
		return convertToDto(clientService.findByEmail(email));
	}

	/**
	 * Tous les clients de la banque
	 * 
	 * @return
	 */
	@GetMapping("/clients")
	public List<ClientDTO> getAllClient() {
		return convertToDto(clientService.getAll());
	}

	/**
	 * Enregistrer un nouveau client
	 * @param client
	 * @return
	 */
	@PostMapping("/client")
	public ClientDTO addClient(@RequestBody ClientDTO client) {
		return convertToDto(clientService.save(convertToEntity(client)));
	}

	@PutMapping("/client")
	public ClientDTO updateClient(@RequestBody ClientDTO client) {
		logger.info("demande mis à jour du client "+ client.getEmail());
		return convertToDto(clientService.save(convertToEntity(client)));
	}

	private ClientDTO convertToDto(Client client) {
		AdresseDTO adresseDTO = modelMapper.map(client.getAdresse(), AdresseDTO.class);
		ClientDTO clientDTO = modelMapper.map(client, ClientDTO.class);
		List<Compte> lesComptes = client.getComptes();
		lesComptes = lesComptes == null ? new ArrayList<Compte>() : lesComptes;
		List<CompteDTO> lesCompteDTO = lesComptes.stream()
				.map(elem -> modelMapper.map(elem, CompteDTO.class))
				.collect(Collectors.toList());
		clientDTO.setAdresse(adresseDTO);
		clientDTO.setComptes(lesCompteDTO);
		return clientDTO;
	}

	private List<ClientDTO> convertToDto(List<Client> clients) {
		clients = clients == null ? new ArrayList<Client>() : clients;
		return clients.stream()
				.map(this::convertToDto)
				.collect(Collectors.toList());
	}

	private Client convertToEntity(ClientDTO clientDTO) {
		Adresse adresse = modelMapper.map(clientDTO.getAdresse(), Adresse.class);
		Client client = modelMapper.map(clientDTO, Client.class);
		List<CompteDTO> lesCompteDTO = clientDTO.getComptes();
		lesCompteDTO = lesCompteDTO == null ? new ArrayList<CompteDTO>() : lesCompteDTO;
		List<Compte> lesCompte = lesCompteDTO.stream()
				.map(elem -> modelMapper.map(elem, Compte.class))
				.collect(Collectors.toList());
		client.setAdresse(adresse);
		client.setComptes(lesCompte);

		return client;
	}

}

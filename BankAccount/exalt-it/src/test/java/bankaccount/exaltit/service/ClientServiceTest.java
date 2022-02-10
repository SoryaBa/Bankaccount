package bankaccount.exaltit.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import bankaccount.exaltit.data.model.Adresse;
import bankaccount.exaltit.data.model.Client;
import bankaccount.exaltit.data.repository.ClientRepository;
import bankaccount.exaltit.service.serviceImpl.ClientServiceImpl;


@ExtendWith(MockitoExtension.class)
public class ClientServiceTest {
	
	private static String NOM = "nom";
	private static String PRENOM = "prenom";
	private static String NUMERO_VOIE = "N°1";
	private static String VOIE = "voie";
	private static String VILLE = "ville";
	private static String PAYS = "pays";
	private static String CODEPOSTALE_750XX = "750";
	private static String EMAIL = "nom.prenom@serveurmail.com";
	
	private static Client client0, client1, client2;
	private static Adresse adresse0;
	
	@Mock 
	private ClientRepository clientRepository;
	private ClientService clientService;
	
	@BeforeEach
	void setUp() {
		adresse0 = new Adresse(NUMERO_VOIE,VOIE, VILLE, CODEPOSTALE_750XX, PAYS);
		client0 = new Client(NOM, PRENOM, EMAIL, adresse0, null);
		client1 = new Client(NOM+"1", PRENOM+"1", EMAIL+"1", adresse0, null);
		client2 = new Client(NOM+"2", PRENOM+"2", EMAIL+"2", adresse0, null);
		clientService = new ClientServiceImpl(clientRepository);
	}
	
	@Test
	void testerRechercheParEmail() {

		ArgumentCaptor<String> clientArgumentCaptor = ArgumentCaptor.forClass(String.class);
		when(clientRepository.findByEmail(EMAIL)).thenReturn(Optional.of(client0));
		Client resultat = clientService.findByEmail(EMAIL);
		
		//verify(clientRepository).findByEmail(EMAIL);
		verify(clientRepository).findByEmail(clientArgumentCaptor.capture());

		assertEquals(EMAIL, clientArgumentCaptor.getValue(), "XXX");
		assertEquals(client0, resultat, "XXX");		
	}
	
	@Test
	void testerRechercheParId() {
		Long id = 1L;
		ArgumentCaptor<Long> clientArgumentCaptor = ArgumentCaptor.forClass(Long.class);
		when(clientRepository.findById(id)).thenReturn(Optional.of(client0));
		Client resultat = clientService.findById(id);
		
		//verify(clientRepository).findById(id);
		verify(clientRepository).findById(clientArgumentCaptor.capture());
		
		assertEquals(id, clientArgumentCaptor.getValue());
		assertEquals(client0, resultat, "Le client trouvé doit etre égal au client cherché");		
	}
	
	@Test
	void testerRechercheTousLesClients() {
		List<Client> lesclients = new ArrayList<Client>();
		lesclients.add(client0);
		lesclients.add(client1);
		lesclients.add(client2);		
		when(clientRepository.findAll()).thenReturn(lesclients);		
		List<Client> resultat = clientService.getAll();
		
		verify(clientRepository).findAll();

		assertEquals(lesclients, resultat, "Les clients trouvés doivent etre tous ceux qui sont dans la bd");		
	}
	
	@Test
	void testerSauvegardeClient() {
		ArgumentCaptor<Client> clientArgumentCaptor = ArgumentCaptor.forClass(Client.class);
		when(clientRepository.save(client0)).thenReturn(client0);		
		Client resultat = clientService.save(client0);
		
		//verify(clientRepository).save(client0);
		verify(clientRepository).save(clientArgumentCaptor.capture());
		
		assertEquals(client0, clientArgumentCaptor.getValue());
		assertEquals(client0, resultat,"Le client sauvegardé doit etre le meme que client enregistré dans la bd");		
	}	
	
}

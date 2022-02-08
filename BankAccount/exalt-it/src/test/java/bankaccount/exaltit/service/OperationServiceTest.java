package bankaccount.exaltit.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;

import bankaccount.exaltit.data.model.Adresse;
import bankaccount.exaltit.data.model.Client;
import bankaccount.exaltit.data.model.Compte;
import bankaccount.exaltit.data.model.Operation;
import bankaccount.exaltit.data.repository.OperationRepository;
import bankaccount.exaltit.service.serviceImpl.OperationServiceImpl;

public class OperationServiceTest {

	@Mock
	private OperationRepository operationRepository;
	private OperationService operationService;

	private static String NOM = "nom";
	private static String PRENOM = "prenom";
	private static String NUMERO_VOIE = "N°1";
	private static String VOIE = "voie";
	private static String VILLE = "ville";
	private static String PAYS = "pays";
	private static String CODEPOSTALE_750XX = "750";
	private static String EMAIL = "nom.prenom@serveurmail.com";

	private static String DESCRIPTION = "Description";
	private static String LIBELLE = "Libelle";

	private static Client client0;
	private static Adresse adresse0;
	private static Compte compte0;
	private static Operation operation0;

	@BeforeEach
	void setUp() {
		this.operationService = new OperationServiceImpl(operationRepository);
		compte0 = new Compte(DESCRIPTION, LIBELLE, 0);
		adresse0 = new Adresse(NUMERO_VOIE, VOIE, VILLE, CODEPOSTALE_750XX, PAYS);
		client0 = new Client(NOM, PRENOM, EMAIL, adresse0, null);
		Timestamp nowTimestamp = new Timestamp(System.currentTimeMillis());
		operation0 = new Operation(nowTimestamp, "paie ExaltIT", 0, 300, client0, compte0);
	}

	@Test
	void testerRechercheOperationsParId() {
		Long id = 1L;
		ArgumentCaptor<Long> operationArgumentCaptor = ArgumentCaptor.forClass(Long.class);
		when(operationRepository.findById(id)).thenReturn(Optional.of(operation0));
		Operation resultat = operationService.getOperationById(id);

		// verify(clientRepository).findById(id);
		verify(operationRepository).findById(operationArgumentCaptor.capture());

		assertEquals(operationArgumentCaptor.getValue(), resultat.getId(),
				"L'identifiant de l'operation trouvée doit etre égale à celle de l'operation cherchée");
	}

	@Test
	void testerRechercheOperationsParCompte() {
		List<Operation> lesOperationsDuCompte = new ArrayList<Operation>();
		lesOperationsDuCompte.add(operation0);

		ArgumentCaptor<Compte> operationArgumentCaptor = ArgumentCaptor.forClass(Compte.class);
		when(operationRepository.findByCompte(compte0)).thenReturn(lesOperationsDuCompte);
		List<Operation> resultat = operationService.getOperationByCompte(compte0);

		// verify(operationRepository).findByCompte(compte0);
		verify(operationRepository).findByCompte(operationArgumentCaptor.capture());

		assertEquals(compte0, operationArgumentCaptor.getValue());
		assertIterableEquals(lesOperationsDuCompte, resultat, "Les operations trouvées doivent appartenir au compte0");
	}

	@Test
	void testerRechercheOperationsParClient() {
		List<Operation> lesOperationsDuClient = new ArrayList<Operation>();
		lesOperationsDuClient.add(operation0);

		ArgumentCaptor<Client> operationArgumentCaptor = ArgumentCaptor.forClass(Client.class);
		when(operationRepository.findByCompte(compte0)).thenReturn(lesOperationsDuClient);
		List<Operation> resultat = operationService.getOperationByClient(client0);

		// verify(operationRepository).findByClient(client0);
		verify(operationRepository).findByClient(operationArgumentCaptor.capture());

		assertEquals(client0, operationArgumentCaptor.getValue());
		assertIterableEquals(lesOperationsDuClient, resultat, "Les operations trouvées doivent appartenir au client0");
	}

	@Test
	void testerRechercheOperationsParClientEtCompte() {
		Long id_Client0 = 1L;
		Long id_Compte0 = 1L;
		List<Operation> lesOperationsDuClient = new ArrayList<Operation>();
		lesOperationsDuClient.add(operation0);

		when(operationRepository.findByClientAndCompte(client0, compte0)).thenReturn(lesOperationsDuClient);
		List<Operation> resultat = operationService.getOperationByClientAndCompte(id_Client0, id_Compte0);

		verify(operationRepository).findByClientAndCompte(client0, compte0);

		assertIterableEquals(lesOperationsDuClient, resultat,
				"Les operations trouvées doivent appartenir au client0 et au compte0");
	}

	@Test
	void testerSauvegardeOperation() {
		ArgumentCaptor<Operation> operationArgumentCaptor = ArgumentCaptor.forClass(Operation.class);
		when(operationRepository.save(operation0)).thenReturn(operation0);
		Operation resultat = operationService.save(operation0);

		verify(operationService).save(operationArgumentCaptor.capture());

		assertEquals(operation0, operationArgumentCaptor.getValue());
		assertEquals(operation0, resultat,
				"L'operation sauvegardée doit etre la meme que operation enregistrée dans la bd");
	}

}

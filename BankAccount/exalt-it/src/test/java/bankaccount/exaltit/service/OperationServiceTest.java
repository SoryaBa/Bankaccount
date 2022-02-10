package bankaccount.exaltit.service;

import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;
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
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import bankaccount.exaltit.data.model.Adresse;
import bankaccount.exaltit.data.model.Client;
import bankaccount.exaltit.data.model.Compte;
import bankaccount.exaltit.data.model.Operation;
import bankaccount.exaltit.data.repository.OperationRepository;
import bankaccount.exaltit.exception.ExceptionIllegalOperation;
import bankaccount.exaltit.exception.ExceptionNotEnough;
import bankaccount.exaltit.service.serviceImpl.OperationServiceImpl;

@ExtendWith(MockitoExtension.class)
public class OperationServiceTest {

	@Mock
	private OperationRepository operationRepository;
	@Mock
	private CompteService compteService;
	@Mock
	private ClientService clientService;

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
		this.operationService = new OperationServiceImpl(operationRepository, clientService, compteService);
		compte0 = new Compte(DESCRIPTION, LIBELLE, 0);
		compte0.setId(111L);
		adresse0 = new Adresse(NUMERO_VOIE, VOIE, VILLE, CODEPOSTALE_750XX, PAYS);
		client0 = new Client(NOM, PRENOM, EMAIL, adresse0, null);
		client0.setId(777L);
		Timestamp nowTimestamp = new Timestamp(System.currentTimeMillis());
		operation0 = new Operation(nowTimestamp, "paie ExaltIT", 0, 300, client0, compte0);
		operation0.setId(555L);
	}

	@Test
	void testerRechercheOperationsParId() {
		ArgumentCaptor<Long> operationArgumentCaptor = ArgumentCaptor.forClass(Long.class);
		when(operationRepository.findById(operation0.getId())).thenReturn(Optional.of(operation0));
		Operation resultat = operationService.findById(operation0.getId());

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
		when(compteService.findById(compte0.getId())).thenReturn(compte0);
		List<Operation> resultat = operationService.getOperationByCompte(compte0.getId());

		// verify(operationRepository).findByCompte(compte0);
		verify(operationRepository).findByCompte(operationArgumentCaptor.capture());
		verify(compteService).findById(compte0.getId());

		assertEquals(compte0, operationArgumentCaptor.getValue());
		assertIterableEquals(lesOperationsDuCompte, resultat, "Les operations trouvées doivent appartenir au compte0");
	}

	@Test
	void testerRechercheOperationsParClient() {
		List<Operation> lesOperationsDuClient = new ArrayList<Operation>();
		lesOperationsDuClient.add(operation0);

		ArgumentCaptor<Client> operationArgumentCaptor = ArgumentCaptor.forClass(Client.class);
		when(operationRepository.findByClient(client0)).thenReturn(lesOperationsDuClient);
		when(clientService.findById(client0.getId())).thenReturn(client0);
		List<Operation> resultat = operationService.getOperationByClient(client0.getId());

		// verify(operationRepository).findByClient(client0);
		verify(operationRepository).findByClient(operationArgumentCaptor.capture());
		verify(clientService).findById(client0.getId());

		assertEquals(client0, operationArgumentCaptor.getValue());
		assertIterableEquals(lesOperationsDuClient, resultat, "Les operations trouvées doivent appartenir au client0");
	}

	@Test
	void testerRechercheOperationsParClientEtCompte() {
		List<Operation> lesOperationsDuClient = new ArrayList<Operation>();
		lesOperationsDuClient.add(operation0);

		when(operationRepository.findByClientAndCompte(client0, compte0)).thenReturn(lesOperationsDuClient);
		when(clientService.findById(client0.getId())).thenReturn(client0);
		when(compteService.findById(compte0.getId())).thenReturn(compte0);
		List<Operation> resultat = operationService.getOperationByClientAndCompte(client0.getId(), compte0.getId());

		verify(operationRepository).findByClientAndCompte(client0, compte0);
		verify(clientService).findById(client0.getId());
		verify(compteService).findById(compte0.getId());

		assertIterableEquals(lesOperationsDuClient, resultat,
				"Les operations trouvées doivent appartenir au client0 et au compte0");
	}

	@Test
	void testerSauvegardeOperationCredit() throws ExceptionIllegalOperation, ExceptionNotEnough {
		// Operation d'un credit de 4444
		operation0.setDebit(0);
		operation0.setCredit(4444);
		double lastSolde = compte0.getSolde();
		when(operationRepository.save(operation0)).thenReturn(operation0);
		when(clientService.findById(client0.getId())).thenReturn(client0);
		when(compteService.findById(compte0.getId())).thenReturn(compte0);
		Operation resultat = operationService.save(operation0, client0.getId(), compte0.getId());

		verify(clientService).findById(client0.getId());
		verify(compteService).findById(compte0.getId());
		verify(operationRepository).save(operation0);

		double valeurOp = operation0.getCredit() > 0 ? operation0.getCredit() : -operation0.getDebit();

		assertEquals(lastSolde + valeurOp, compte0.getSolde(),
				"Le solde du compte avant operation + la valeur de l'operation sauvegardée doit egal au solde du compte après operation");
		assertEquals(operation0, resultat,
				"L'operation sauvegardée doit etre la meme que operation enregistrée dans la bd");
	}

	@Test
	void testerSauvegardeOperationDebit() throws ExceptionIllegalOperation, ExceptionNotEnough {
		// Operation d'un debit de 5555
		compte0.setSolde(5555);
		operation0.setDebit(5555);
		operation0.setCredit(0);
		double lastSolde = compte0.getSolde();
		when(operationRepository.save(operation0)).thenReturn(operation0);
		when(clientService.findById(client0.getId())).thenReturn(client0);
		when(compteService.findById(compte0.getId())).thenReturn(compte0);
		Operation resultat = operationService.save(operation0, client0.getId(), compte0.getId());

		verify(clientService).findById(client0.getId());
		verify(compteService).findById(compte0.getId());
		verify(operationRepository).save(operation0);

		double valeurOp = operation0.getCredit() > 0 ? operation0.getCredit() : -operation0.getDebit();

		assertEquals(lastSolde + valeurOp, compte0.getSolde(),
				"Le solde du compte avant operation + la valeur de l'operation sauvegardée doit egal au solde du compte après operation");
		assertEquals(operation0, resultat,
				"L'operation sauvegardée doit etre la meme que operation enregistrée dans la bd");
	}

	@Test
	void testerSauvegardeOperationNonValide() {
		// Operation d'un debit de 4444 et d'un credit de 5555
		operation0.setDebit(4444);
		operation0.setCredit(5555);

		ExceptionIllegalOperation exception2nombrePositifs = assertThrows(ExceptionIllegalOperation.class, () -> {
			operationService.save(operation0, client0.getId(), compte0.getId());
		});

		// Operation d'un debit de 4444 et d'un credit de 5555
		operation0.setDebit(-4444);
		operation0.setCredit(0);

		ExceptionIllegalOperation exceptionDebitNegatif = assertThrows(ExceptionIllegalOperation.class, () -> {
			operationService.save(operation0, client0.getId(), compte0.getId());
		});

		// Operation d'un credit négatif -5
		operation0.setDebit(0);
		operation0.setCredit(-5);

		ExceptionIllegalOperation exceptionCreditNegatif = assertThrows(ExceptionIllegalOperation.class, () -> {
			operationService.save(operation0, client0.getId(), compte0.getId());
		});

		String messageAttendu = "Une operation ne peut pas contenir un nombre négatif. Une operation ne doit contenir qu'un seul nombre positif soit au debit ou au credit";

		assertTrue("L'exception levée doit contenir le message attendu",
				exception2nombrePositifs.getMessage().contains(messageAttendu));
		assertTrue("L'exception levée doit contenir le message attendu",
				exceptionDebitNegatif.getMessage().contains(messageAttendu));
		assertTrue("L'exception levée doit contenir le message attendu",
				exceptionCreditNegatif.getMessage().contains(messageAttendu));

	}

	// * Pour faire un depot dans le compte, utiliser credit de l'operation <br>
	// * Pour faire un retrait dans le compte, utiliser debit de l'operation <br>
	@Test
	void testerSauvegardeOperationSansAssezProvision() {
		// Operation d'un debit de 4444 avec un solde 1010
		operation0.setCredit(0);
		operation0.setDebit(4444);
		compte0.setSolde(1010);

		when(clientService.findById(client0.getId())).thenReturn(client0);
		when(compteService.findById(compte0.getId())).thenReturn(compte0);

		ExceptionNotEnough exceptionPasAssez = assertThrows(ExceptionNotEnough.class, () -> {
			operationService.save(operation0, client0.getId(), compte0.getId());
		});

		String messageAttendu = "Cette operation n'est pas possible vous n'avez pas assez d'avoir pour cette operation";

		assertTrue("L'exception levée doit contenir le message attendu",
				exceptionPasAssez.getMessage().contains(messageAttendu));

	}

}

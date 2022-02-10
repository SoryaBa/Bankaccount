package bankaccount.exaltit.data.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import bankaccount.exaltit.data.model.Adresse;
import bankaccount.exaltit.data.model.Client;
import bankaccount.exaltit.data.model.Compte;
import bankaccount.exaltit.data.model.Operation;

@DataJpaTest
public class OperationRepositoryTest {

	@Autowired
	private ClientRepository clientRepository;
	@Autowired
	private CompteRepository compteRepository;
	@Autowired
	private OperationRepository operationRepository;

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

	@Test
	public void testerSauvergardeOperation() {

		// Compte
		Compte compte = new Compte(DESCRIPTION, LIBELLE, 0);
		compteRepository.save(compte);
		// Client
		Adresse adresse = new Adresse(NUMERO_VOIE, VOIE, VILLE, CODEPOSTALE_750XX, PAYS);
		Client client = new Client(NOM, PRENOM, EMAIL, adresse, null);
		client.add(compte);
		clientRepository.save(client);
		// Operation
		Timestamp nowTimestamp = new Timestamp(System.currentTimeMillis());
		Operation operation = new Operation(nowTimestamp, "paie ExaltIT", 0, 300, client, compte);

		operationRepository.save(operation);
		Optional<Operation> clientOperationResultat = operationRepository.findById(operation.getId());

		assertTrue(clientOperationResultat.isPresent(), "Il existe une operation enregistree en bd avec cet id");
		assertEquals(operation, clientOperationResultat.get(),
				"L'operation trouvee correspond bien a celle sauvegardee");
	}

	@Test
	public void trouverOperationsParClient() {

		// Compte
		Compte compte = new Compte(DESCRIPTION, LIBELLE, 0);
		// Client
		Adresse adresse = new Adresse(NUMERO_VOIE, VOIE, VILLE, CODEPOSTALE_750XX, PAYS);
		Client client = new Client(NOM, PRENOM, EMAIL, adresse, null);
		client.add(compte);
		clientRepository.save(client);
		// Operation
		Timestamp nowTimestamp = new Timestamp(System.currentTimeMillis());
		Operation operation = new Operation(nowTimestamp, "paie ExaltIT", 0, 300, client, compte);
		ArrayList<Operation> operationsAttendus = new ArrayList<Operation>(Arrays.asList(operation));

		operationRepository.save(operation);
		List<Operation> clientOperationResultat = operationRepository.findByClient(client);

		assertEquals(operationsAttendus, clientOperationResultat,
				"Les operations trouvees correspondent bien a celles sauvegardees ");

	}

	@Test
	public void trouverOperationsParCompte() {
		
		// Compte
		Compte compte = new Compte(DESCRIPTION, LIBELLE, 0);
		// Client
		Adresse adresse = new Adresse(NUMERO_VOIE, VOIE, VILLE, CODEPOSTALE_750XX, PAYS);
		Client client = new Client(NOM, PRENOM, EMAIL, adresse, null);
		client.add(compte);
		clientRepository.save(client);
		// Operation
		Timestamp nowTimestamp = new Timestamp(System.currentTimeMillis());
		Operation operation = new Operation(nowTimestamp, "paie ExaltIT", 0, 300, client, compte);
		ArrayList<Operation> operationsAttendus = new ArrayList<Operation>(Arrays.asList(operation));

		operationRepository.save(operation);
		List<Operation> clientOperationsResultat = operationRepository.findByClient(client);

		assertIterableEquals(operationsAttendus, clientOperationsResultat,
				"Les operations trouvees correspondent bien a celles sauvegardees ");
	}
	
	@Test
	public void trouverOperationsParClientEtCompte() {
		
		// Compte
		Compte compte = new Compte(DESCRIPTION, LIBELLE, 0);
		// Client
		Adresse adresse = new Adresse(NUMERO_VOIE, VOIE, VILLE, CODEPOSTALE_750XX, PAYS);
		Client client = new Client(NOM, PRENOM, EMAIL, adresse, null);
		client.add(compte);
		clientRepository.save(client);
		// Operation
		Timestamp nowTimestamp = new Timestamp(System.currentTimeMillis());
		Operation operation = new Operation(nowTimestamp, "paie ExaltIT", 0, 300, client, compte);
		ArrayList<Operation> operationsAttendus = new ArrayList<Operation>(Arrays.asList(operation));

		operationRepository.save(operation);
		List<Operation> clientOperationsResultat = operationRepository.findByClientAndCompte(client, compte);

		assertIterableEquals(operationsAttendus, clientOperationsResultat,
				"Les operations trouvees correspondent bien a celles sauvegardees ");
	}
}
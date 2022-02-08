package bankaccount.exaltit.data.repository;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import bankaccount.exaltit.data.model.Adresse;
import bankaccount.exaltit.data.model.Client;
import bankaccount.exaltit.data.model.Compte;

@DataJpaTest
public class ClientRepositoryTest {

	@Autowired
	private ClientRepository clientRepository;
	@Autowired
	private CompteRepository compteRepository;

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
	void testTrouverClientParEmail() {

		// given
		String emailExistant = "A" + EMAIL;
		String emailNonExistant = EMAIL;
		Adresse adresse = new Adresse(NUMERO_VOIE, VOIE, VILLE, CODEPOSTALE_750XX + "08", PAYS);
		Client client = new Client("A_" + NOM, "A_" + PRENOM, emailExistant, adresse, null);

		clientRepository.save(client);

		// when
		Optional<Client> optionalClient = clientRepository.findByEmail(emailExistant);
		boolean resultatEmailExistant = optionalClient.isPresent();
		boolean resultatEmailNonExistant = clientRepository.findByEmail(emailNonExistant).isPresent();

		// then
		assertTrue("Trouver un client avec son email : ", resultatEmailExistant);
		assertFalse("Ne pas trouver un client avec un email qui n'existe dans la BD : ", resultatEmailNonExistant);
		assertEquals(client, optionalClient.get(), "Le client trouver par son email doit correspondre au client cherche");
	}
	
	@Test
	void testSauvegardeClient() {

		// given
		Adresse adresse = new Adresse(NUMERO_VOIE, VOIE, VILLE, CODEPOSTALE_750XX + "08", PAYS);
		Client client = new Client("A_" + NOM, "A_" + PRENOM, "A" + EMAIL, adresse, null);
		

		// when
		clientRepository.save(client);
		Long idDuClientSauvegarder = client.getId();
		Optional<Client> optionalClient = clientRepository.findById(idDuClientSauvegarder);
		
		// then
		assertEquals(client, optionalClient.get(), "Le client Enregistrer doit etre le meme que celui trouver par son Id");
	}

	/**
	 * Ce test doit verifier que nous parvenons a retrouver un client par son email
	 * dans une bd ayant plusieurs lignes. Doit verifier que nous parvenons a
	 * sauvegarder/affecter un compte existant (deja sauvegarder) a un client:
	 * nouvelle ligne dans table client. Doit verifier que nous parvenons a
	 * sauvegarder le client et tous ses comptes : nouvelle ligne dans table client,
	 * dans la table relation client compte (linkedcpt-client) et si necessaire dans
	 * la table compte
	 * 
	 */
	@Test
	void testRelationClientCompte() {

		// given

		// client A
		String emailClientA = "A" + EMAIL;
		Adresse adresseA = new Adresse(NUMERO_VOIE, VOIE, VILLE, CODEPOSTALE_750XX + "01", VILLE + "1");
		Client clientA = new Client("A" + NOM, "A" + PRENOM, emailClientA, adresseA, null);

		// Creation et sauvegarde avant affectation au compte A1 du client A
		Compte compteA1SauvegardeAvantAffectationAuCientA = new Compte(DESCRIPTION + " cpt:A1", LIBELLE + " cpt:A1", 0);
		compteRepository.save(compteA1SauvegardeAvantAffectationAuCientA);
		clientA.add(compteA1SauvegardeAvantAffectationAuCientA);

		// Creation sans sauvegarde avant affectation au compte A2 du client A
		Compte compteA2SansSauvegardeAvantAffectationAuCientA = new Compte(DESCRIPTION + " cpt:A2", LIBELLE + " cpt:A2",
				7);
		clientA.add(compteA2SansSauvegardeAvantAffectationAuCientA);

		// Creation et sauvegarde du compte A3 avant affectation au client A
		Compte compteA3SauvegardeAvantAffectationAuCientA = new Compte(DESCRIPTION + " cpt:A3", LIBELLE + " cpt:A3", 0);
		compteRepository.save(compteA3SauvegardeAvantAffectationAuCientA);
		clientA.add(compteA3SauvegardeAvantAffectationAuCientA);

		// Creation sans sauvegarde du compte A4 avant affectation au client A
		Compte compteA4SansSauvegardeAvantAffectationAuCientA = new Compte(DESCRIPTION + " cpt:A4", LIBELLE + " cpt:A4",
				0);
		compteRepository.save(compteA4SansSauvegardeAvantAffectationAuCientA);
		clientA.add(compteA4SansSauvegardeAvantAffectationAuCientA);

		// Client B
		Adresse adresseB = new Adresse(NUMERO_VOIE, VOIE, VILLE, CODEPOSTALE_750XX + "02", VILLE + "2");
		Client clientB = new Client("B" + NOM, "B" + PRENOM, "B" + EMAIL, adresseB, null);

		// Creation sans sauvegarde du compte B1 avant affectation au client B
		Compte compteB1SansSauvegardeAvantAffectationAuCientB = new Compte(DESCRIPTION + " cpt:B1", LIBELLE + " cpt:B1",
				0);
		compteRepository.save(compteB1SansSauvegardeAvantAffectationAuCientB);
		clientB.add(compteB1SansSauvegardeAvantAffectationAuCientB);

		// client voisin et homonyme de A
		String emailClientVoisinA = "Avoisin" + EMAIL;
		Client clientVoisinA = new Client("A" + NOM, "A" + PRENOM, emailClientVoisinA, adresseA, null);

		// Creation et sauvegarde du compte A3 avant affectation au client A
		Compte compteA1SauvegardeAvantAffectationAuCientVoisinA = new Compte(DESCRIPTION + " cpt:A1",
				LIBELLE + " cpt:A1", 0);
		compteRepository.save(compteA1SauvegardeAvantAffectationAuCientVoisinA);
		clientVoisinA.add(compteA1SauvegardeAvantAffectationAuCientVoisinA);

		// when

		// Sauvegarde du client A
		clientRepository.save(clientA);
		// Sauvegarde du client B
		clientRepository.save(clientB);
		// Sauvegarde du voisin et homonyme de A
		clientRepository.save(clientVoisinA);

		Client clientResultat = clientRepository.findByEmail(emailClientA).get();

		// then

		assertEquals(clientA, clientResultat,
				"Le client trouver dans la base contenant plusieurs lignes doit correspondre au client cherche");
		assertEquals(clientA, clientResultat,
				"Le client trouver dans la base contenant plusieurs lignes doit correspondre au client cherche");
		assertIterableEquals(clientA.getComptes(), clientResultat.getComptes(),
				"On parvient a retrouver seulemnt et tous les comptes affectes a un client");
	}
}

package bankaccount.exaltit.data.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import bankaccount.exaltit.data.model.Compte;

@DataJpaTest
public class CompteRepositoryTest {

	private static String DESCRIPTION = "Description";
	private static String LIBELLE = "Libelle";

	@Autowired
	private CompteRepository compteRepository;

	@Test
	public void testerSauvegardeCompte() {

		// given
		Compte compte = new Compte(DESCRIPTION, LIBELLE, 0);

		// when
		compteRepository.save(compte);
		Long idDuCompteSauvegarder = compte.getId();
		Optional<Compte> optionalClient = compteRepository.findById(idDuCompteSauvegarder);

		// then
		assertTrue(optionalClient.isPresent(), "Il doit exister un compte correspondant a l'Id ");
		assertEquals(compte, optionalClient.get(),
				"Le compte enregistrer doit etre le meme que celui trouver par son Id");
	}

	@Test
	public void testerTrouverCompteParId() {
		// given
		Compte compte = new Compte(DESCRIPTION, LIBELLE, 0);
		compteRepository.save(compte);
		Long idDuCompteSauvegarder = compte.getId();

		// when
		Optional<Compte> optionalCompte = compteRepository.findById(idDuCompteSauvegarder);

		// then
		assertEquals(compte, optionalCompte.get(), "Le compte trouver par son Id doit correspondre au client cherche");
	}

}
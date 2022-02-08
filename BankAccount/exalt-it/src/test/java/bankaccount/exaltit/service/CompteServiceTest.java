package bankaccount.exaltit.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import bankaccount.exaltit.data.model.Compte;
import bankaccount.exaltit.data.repository.CompteRepository;
import bankaccount.exaltit.service.serviceImpl.CompteServiceImpl;

@ExtendWith(MockitoExtension.class)
public class CompteServiceTest {
	
	@Mock
	private CompteRepository compteRepository;
	private CompteService compteService;

	private static String DESCRIPTION = "Description";
	private static String LIBELLE = "Libelle";

	private static Compte compte0;

	@BeforeEach
	void setUp() {
		this.compteService = new CompteServiceImpl(compteRepository);
		compte0 = new Compte(DESCRIPTION, LIBELLE, 0);
	}

	@Test
	void testerRechercheCompteParId() {
		Long id = 1L;
		ArgumentCaptor<Long> compteArgumentCaptor = ArgumentCaptor.forClass(Long.class);
		when(compteRepository.findById(id)).thenReturn(Optional.of(compte0));
		Compte resultat = compteService.getCompteById(id);
		
		verify(compteRepository).findById(compteArgumentCaptor.capture());

		assertEquals(id, compteArgumentCaptor.getValue(), "le compte trouvé doit avoir un id égal à l'id passé en parametre");
		assertEquals(compte0, resultat, "Le compte trouver doit etre le meme que compte cherché");
	}

	@Test
	void testerSauvegardeCompte() {
		ArgumentCaptor<Compte> compteArgumentCaptor = ArgumentCaptor.forClass(Compte.class);
		when(compteRepository.save(compte0)).thenReturn(compte0);
		Compte resultat = compteService.save(compte0);

		verify(compteService).save(compteArgumentCaptor.capture());

		assertEquals(compte0, compteArgumentCaptor.getValue());
		assertEquals(compte0, resultat, "Le compte sauvegardé doit etre le meme que compte enregistré dans la bd");
	}
}

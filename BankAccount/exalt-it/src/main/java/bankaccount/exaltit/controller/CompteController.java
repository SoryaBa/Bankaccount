package bankaccount.exaltit.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import bankaccount.exaltit.data.model.Compte;

@RestController
@RequestMapping(path = "/banque")
public class CompteController {

	@GetMapping("/compte/{compteID}")
	public Compte getCompteByID(@PathVariable(name = "compteID") Long clientID) {

		return null;
	}

	
	@PutMapping("/compte")
	public Compte updateCompte(@RequestBody Compte compte) {
		return null;
	}

}

package bankaccount.exaltit.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import bankaccount.exaltit.data.dto.OperationDTO;
import bankaccount.exaltit.data.model.Operation;

@RestController
public class OperationController {

	@GetMapping("/historique/client/{clientID}")
	public List<Operation> getOperationsByClient(@PathVariable(name = "clientID") Long clientID) {

		return null;
	}
	
	@GetMapping("/historique/compte/{compteID}")
	public List<Operation> getOperationsByCompte(@PathVariable(name = "compteID") Long compteID) {

		return null;
	}

	@GetMapping("/historique/{clientID}/{compteID}")
	public List<Operation> getOperationByClientAndCompte(@PathVariable(name = "clientID") Long clientID, @PathVariable(name = "compteID") Long compteID) {

		return null;
	}
	
	@PostMapping("/operation")
	public List<Operation> createOperation(@RequestBody OperationDTO operationDTO) {

		return null;
	}
	

}

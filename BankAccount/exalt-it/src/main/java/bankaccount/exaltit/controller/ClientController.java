package bankaccount.exaltit.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import bankaccount.exaltit.data.dto.ClientDTO;
import bankaccount.exaltit.data.model.Client;

@RestController
public class ClientController {

	@Autowired
	private ClientController clientController;
	
	@GetMapping("/client")
	public ClientDTO getClientByEmail(@RequestParam(name = "email") String email) {
		
		return null;
	}
	
	@GetMapping("/clients")
	public List<ClientDTO> getAllClient() {
		
		return null;
	}
	
	@PostMapping("/client")
	public void addClient(@RequestBody Client client) {
		
	}
	
	@PutMapping("/client")
	public void updateClient(@RequestBody Client client) {
		
	}
	
}

package bankaccount.exaltit.controller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import bankaccount.exaltit.data.dto.CompteDTO;
import bankaccount.exaltit.data.model.Compte;
import bankaccount.exaltit.service.CompteService;

@RestController
public class CompteController {
	
	@Autowired
    private ModelMapper modelMapper;
	@Autowired
	private CompteService compteService;

	@GetMapping("/compte/{compteID}")
	public CompteDTO getCompteByID(@PathVariable(name = "compteID") Long clientID) {
		return convertToDto(compteService.findById(clientID));
	}

	/** 
	 *  Sauvegarde d'un compte <br>
	 * /!\ Il est préferable de Créer le compte en affectant à un client ie: client.add(compte)
	 * @param compteDTO
	 * @return
	 */
	@PostMapping("/compte")
	public CompteDTO updateCompte(@RequestBody CompteDTO compteDTO) {
		return convertToDto(compteService.save(convertToEntity(compteDTO)));
	}
	
	/**
	 * Modification d'un compte <br>
	 * /!\ Il est preferable de ne pas modifier le solde du compte, le solde ne devrait etre modifié que par une operation
	 * @param compteDTO
	 * @return
	 */
	@PutMapping("/compte")
	public CompteDTO addCompte(@RequestBody CompteDTO compteDTO) {
		return convertToDto(compteService.save(convertToEntity(compteDTO)));
	}	
	
	private CompteDTO convertToDto(Compte compte) {
		return modelMapper.map(compte, CompteDTO.class);
	}
	private Compte convertToEntity(CompteDTO compteDTO) {
		return modelMapper.map(compteDTO, Compte.class);
	}

}

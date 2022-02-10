package bankaccount.exaltit.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import bankaccount.exaltit.data.dto.AdresseDTO;
import bankaccount.exaltit.data.dto.ClientDTO;
import bankaccount.exaltit.data.dto.CompteDTO;
import bankaccount.exaltit.data.dto.OperationDTO;
import bankaccount.exaltit.data.model.Operation;
import bankaccount.exaltit.exception.ExceptionIllegalOperation;
import bankaccount.exaltit.exception.ExceptionNotEnough;
import bankaccount.exaltit.service.OperationService;

@RestController
public class OperationController {

	@Autowired
	private ModelMapper modelMapper;
	@Autowired
	private OperationService operationService;

	/**
	 * Trouve les operations d'un client
	 * 
	 * @param clientID
	 * @return
	 */
	@GetMapping("/historique/client/{clientID}")
	public List<OperationDTO> getOperationsByClient(@PathVariable(name = "clientID") Long clientID) {
		return convertToDto(operationService.getOperationByClient(clientID));
	}

	/**
	 * Trouve les operations sur un compte
	 * 
	 * @param compteID
	 * @return
	 */
	@GetMapping("/historique/compte/{compteID}")
	public List<OperationDTO> getOperationsByCompte(@PathVariable(name = "compteID") Long compteID) {
		return convertToDto(operationService.getOperationByCompte(compteID));
	}

	@GetMapping("/historique/{clientID}/{compteID}")
	public List<OperationDTO> getOperationByClientAndCompte(@PathVariable(name = "clientID") Long clientID,
			@PathVariable(name = "compteID") Long compteID) {

		return convertToDto(operationService.getOperationByClientAndCompte(clientID, compteID));
	}

	/**
	 * Enregistre une opertion
	 * 
	 * @param clientID
	 * @param compteID
	 * @param operationDTO
	 * @return
	 * @throws ExceptionNotEnough 
	 * @throws ExceptionIllegalOperation 
	 */
	@PostMapping("/operation/{clientID}/{compteID}")
	public OperationDTO createOperation(@PathVariable(name = "clientID") Long clientID,
			@PathVariable(name = "compteID") Long compteID, @RequestBody OperationDTO operationDTO) throws ExceptionIllegalOperation, ExceptionNotEnough {
		return convertToDto(operationService.save(convertToEmtity(operationDTO), clientID, compteID));
	}

	private OperationDTO convertToDto(Operation operation) {
		OperationDTO operationDTO = modelMapper.map(operation, OperationDTO.class);
		AdresseDTO adresseDTO = modelMapper.map(operation.getClient().getAdresse(), AdresseDTO.class);
		ClientDTO clientDTO = modelMapper.map(operation.getClient(), ClientDTO.class);
		CompteDTO compteDTO = modelMapper.map(operation.getCompte(), CompteDTO.class);
		clientDTO.setAdresse(adresseDTO);
		operationDTO.setClient(clientDTO);
		operationDTO.setCompte(compteDTO);
		
		//Pour la date de l'operation en String
		operationDTO.setDateOperation(operation.getDateCreation().toString());

		return operationDTO;
	}

	private List<OperationDTO> convertToDto(List<Operation> operations) {
		operations = operations == null ? new ArrayList<Operation>() : operations;
		return operations.stream().map(this::convertToDto).collect(Collectors.toList());
	}

	private Operation convertToEmtity(OperationDTO operationDTO) {
		return modelMapper.map(operationDTO, Operation.class);
	}

}

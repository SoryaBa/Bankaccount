package bankaccount.exaltit;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import bankaccount.exaltit.data.model.Adresse;
import bankaccount.exaltit.data.model.Client;
import bankaccount.exaltit.data.model.Compte;
import bankaccount.exaltit.data.model.Operation;
import bankaccount.exaltit.data.repository.ClientRepository;
import bankaccount.exaltit.data.repository.CompteRepository;
import bankaccount.exaltit.data.repository.OperationRepository;

@SpringBootApplication
public class ExaltItApplication {

	@Autowired
	private ClientRepository clientRepository;
	
	@Autowired
	private CompteRepository compteRepository;
	
	@Autowired
	private OperationRepository operationRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(ExaltItApplication.class, args);
	}
	
	    public void run(String... args) {
	        
		 	// Netoyer la table client
	      //  clientRepository.deleteAll();
	        
	     // Netoyer la table transaction
	     //   operationRepository.deleteAll();
	        
	     // Netoyer la table compte
	     ///   compteRepository.deleteAll();
	        
	        Compte compte1 = new Compte("compte pricipale","Compte courant personel", 0);
	        compteRepository.save(compte1);
	        
	        // Insertion d'un nouveau client dans la db_banque
	        Adresse adresse = new Adresse("45", "ChampsElysés", "Paris", "75008", "France");
	        List<Compte> listComptes = new ArrayList<Compte>();
	        listComptes.add(compte1);
	        Client client = new Client("BAH", "Aliou", "aliousorya@gmail.com", adresse, listComptes);
	        
	        clientRepository.save(client);
	        
	        Operation operation = new Operation(new Timestamp(System.currentTimeMillis()), "paie ExaltIT", 0, 300, client, compte1);
			operationRepository.save(operation);
			
			
			
	    }
	    
	   }

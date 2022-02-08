package bankaccount.exaltit.data.dto;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import bankaccount.exaltit.data.model.Adresse;
import bankaccount.exaltit.data.model.Compte;

public class ClientDTO {
	
		Long id;		
		String nom;
		String prenom;
		String email;		
		Adresse adresse;
		List<Compte> comptes;

		public ClientDTO() {
		}

		public ClientDTO(String nom, String prenom, String email, Adresse adresse, List<Compte> mesComptes) {
			this.nom = nom;
			this.prenom = prenom;
			this.email = email;
			this.adresse = adresse;
			this.comptes = mesComptes == null ? new ArrayList<Compte>() : comptes;
		}

		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		public String getNom() {
			return nom;
		}

		public void setNom(String nom) {
			this.nom = nom;
		}

		public String getPrenom() {
			return prenom;
		}

		public void setPrenom(String prenom) {
			this.prenom = prenom;
		}

		public String getEmail() {
			return email;
		}

		public void setEmail(String email) {
			this.email = email;
		}

		public Adresse getAdresse() {
			return adresse;
		}

		public void setAdresse(Adresse adresse) {
			this.adresse = adresse;
		}

		public List<Compte> getComptes() {
			return comptes;
		}

		public void setComptes(List<Compte> comptes) {
			this.comptes = comptes;
		}
		
		public boolean add(Compte compte) {
			return comptes.add(compte);
		}

		@Override
		public int hashCode() {
			return Objects.hash(email, id, nom, prenom);
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			ClientDTO other = (ClientDTO) obj;
			return Objects.equals(email, other.email) && Objects.equals(id, other.id) && Objects.equals(nom, other.nom)
					&& Objects.equals(prenom, other.prenom);
		}
		
		

}

package bankaccount.exaltit.data.dto;

import java.util.Objects;

public class CompteDTO {
	
		Long id;
		String description;
		String libelle;
		double solde;
		
		
		public CompteDTO() {
		}
		
		public CompteDTO(String description, String libelle, double solde) {
			this.description = description;
			this.libelle = libelle;
			this.solde = solde;
		}
		
		public Long getId() {
			return id;
		}
		public void setId(Long id) {
			this.id = id;
		}
		public String getDescription() {
			return description;
		}
		public void setDescription(String description) {
			this.description = description;
		}
		public String getLibelle() {
			return libelle;
		}
		public void setLibelle(String libelle) {
			this.libelle = libelle;
		}
		public double getSolde() {
			return solde;
		}
		public void setSolde(double solde) {
			this.solde = solde;
		}

		@Override
		public int hashCode() {
			return Objects.hash(description, id, libelle, solde);
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			CompteDTO other = (CompteDTO) obj;
			return Objects.equals(description, other.description) && Objects.equals(id, other.id)
					&& Objects.equals(libelle, other.libelle)
					&& Double.doubleToLongBits(solde) == Double.doubleToLongBits(other.solde);
		}
		
}

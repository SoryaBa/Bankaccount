package bankaccount.exaltit.data.dto;

import java.util.Objects;

public class AdresseDTO {	
		
		String numero;
		String voie;
		String ville;
		String codePostal;
		String pays;
		
		public AdresseDTO(String numero, String voie, String ville, String codePostal, String pays) {
			this.numero = numero;
			this.voie = voie;
			this.codePostal = codePostal;
			this.ville = ville;
			this.pays = pays;
		}
		
		public AdresseDTO() {
		}

		public String getNumero() {
			return numero;
		}

		public void setNumero(String numero) {
			this.numero = numero;
		}

		public String getVoie() {
			return voie;
		}

		public void setVoie(String voie) {
			this.voie = voie;
		}

		public String getVille() {
			return ville;
		}

		public void setVille(String ville) {
			this.ville = ville;
		}

		public String getCodePostal() {
			return codePostal;
		}

		public void setCodePostal(String codePostal) {
			this.codePostal = codePostal;
		}

		public String getPays() {
			return pays;
		}

		public void setPays(String pays) {
			this.pays = pays;
		}

		@Override
		public int hashCode() {
			return Objects.hash(codePostal, numero, pays, ville, voie);
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			AdresseDTO other = (AdresseDTO) obj;
			return Objects.equals(codePostal, other.codePostal) && Objects.equals(numero, other.numero)
					&& Objects.equals(pays, other.pays) && Objects.equals(ville, other.ville)
					&& Objects.equals(voie, other.voie);
		}		
		
}

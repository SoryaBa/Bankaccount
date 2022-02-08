package bankaccount.exaltit.data.model;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class Adresse {
	
	@Column(name = "numero_voie", table = "client")
	String numero;
	
	@Column(name = "nom_voie", table = "client")
	String voie;
		
	@Column(name = "ville", table = "client")
	String ville;
	
	@Column(name = "codepostal", table = "client")
	String codePostal;
	
	@Column(name = "pays", table = "client")
	String pays;
	
	public Adresse(String numero, String voie, String ville, String codePostal, String pays) {
		this.numero = numero;
		this.voie = voie;
		this.codePostal = codePostal;
		this.ville = ville;
		this.pays = pays;
	}
	
	public Adresse() {
		super();
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
		Adresse other = (Adresse) obj;
		return Objects.equals(codePostal, other.codePostal) && Objects.equals(numero, other.numero)
				&& Objects.equals(pays, other.pays) && Objects.equals(ville, other.ville)
				&& Objects.equals(voie, other.voie);
	}
	
}

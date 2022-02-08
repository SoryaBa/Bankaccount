package bankaccount.exaltit.data.model;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Compte {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	Long id;
	String description;
	String libelle;
	double solde;
	
	
	public Compte() {
		super();
	}
	
	public Compte(String description, String libelle, double solde) {
		super();
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
		Compte other = (Compte) obj;
		return Objects.equals(description, other.description) && Objects.equals(id, other.id)
				&& Objects.equals(libelle, other.libelle)
				&& Double.doubleToLongBits(solde) == Double.doubleToLongBits(other.solde);
	}
	
}

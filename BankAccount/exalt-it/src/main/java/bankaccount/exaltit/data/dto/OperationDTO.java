package bankaccount.exaltit.data.dto;

import java.sql.Timestamp;
import java.util.Objects;

public class OperationDTO {

	Long id;
	transient Timestamp dateCreation;
	String dateOperation;
	String message;
	double debut;
	double credit;
	ClientDTO client;
	CompteDTO compte;

	public OperationDTO(Timestamp dateCreation, String message, double debut, double credit, ClientDTO client,
			CompteDTO compte) {
		this.dateCreation = dateCreation;
		this.message = message;
		this.debut = debut;
		this.credit = credit;
		this.client = client;
		this.compte = compte;
	}

	public ClientDTO getClient() {
		return client;
	}

	public void setClient(ClientDTO client) {
		this.client = client;
	}

	public CompteDTO getCompte() {
		return compte;
	}

	public void setCompte(CompteDTO compte) {
		this.compte = compte;
	}

	public OperationDTO() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Timestamp getDateCreation() {
		return dateCreation;
	}

	public void setDateCreation(Timestamp dateCreation) {
		this.dateCreation = dateCreation;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public double getDebut() {
		return debut;
	}

	public void setDebut(double debut) {
		this.debut = debut;
	}

	public double getCredit() {
		return credit;
	}

	public void setCredit(double credit) {
		this.credit = credit;
	}

	public String getDateOperation() {
		return dateOperation;
	}

	public void setDateOperation(String dateOperation) {
		this.dateOperation = dateOperation;
	}

	@Override
	public int hashCode() {
		return Objects.hash(client, compte, credit, dateCreation, debut, id, message);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		OperationDTO other = (OperationDTO) obj;
		return Objects.equals(client, other.client) && Objects.equals(compte, other.compte)
				&& Double.doubleToLongBits(credit) == Double.doubleToLongBits(other.credit)
				&& Objects.equals(dateCreation, other.dateCreation)
				&& Double.doubleToLongBits(debut) == Double.doubleToLongBits(other.debut)
				&& Objects.equals(id, other.id) && Objects.equals(message, other.message);
	}

}
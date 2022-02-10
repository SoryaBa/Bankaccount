package bankaccount.exaltit.data.dto;

import java.sql.Timestamp;
import java.util.Objects;

public class OperationDTO {

	Long id;
	Timestamp dateCreation;
	String message;
	double debit;
	double credit;
	ClientDTO client;
	CompteDTO compte;
	
	
	public OperationDTO(Long id, Timestamp dateCreation, String message, double debit, double credit, ClientDTO client,
			CompteDTO compte) {
		super();
		this.id = id;
		this.dateCreation = dateCreation;
		this.message = message;
		this.debit = debit;
		this.credit = credit;
		this.client = client;
		this.compte = compte;
	}
	
	public OperationDTO() {
		super();
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

	public double getDebit() {
		return debit;
	}

	public void setDebit(double debit) {
		this.debit = debit;
	}

	public double getCredit() {
		return credit;
	}

	public void setCredit(double credit) {
		this.credit = credit;
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

	@Override
	public int hashCode() {
		return Objects.hash(client, compte, credit, dateCreation, debit, id, message);
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
				&& Double.doubleToLongBits(debit) == Double.doubleToLongBits(other.debit)
				&& Objects.equals(id, other.id) && Objects.equals(message, other.message);
	}
}
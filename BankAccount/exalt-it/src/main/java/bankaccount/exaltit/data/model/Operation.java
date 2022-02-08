package bankaccount.exaltit.data.model;

import java.sql.Timestamp;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "operation")
public class Operation {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	Long id;
	
	@Column(name = "datecreation")
	Timestamp dateCreation;
	String message ;
	double debut;
	double credit;
	
	@OneToOne(cascade = {
			CascadeType.PERSIST,
			CascadeType.MERGE})
    @JoinColumn(name = "id_client", referencedColumnName = "id")
	Client client;
	
	@OneToOne(cascade = {
			CascadeType.PERSIST,
	        CascadeType.MERGE})
    @JoinColumn(name = "id_compte", referencedColumnName = "id")
	Compte compte;

	public Operation(Timestamp dateCreation, String message, double debut, double credit, Client client,
			Compte compte) {
		super();
		this.dateCreation = dateCreation;
		this.message = message;
		this.debut = debut;
		this.credit = credit;		
		this.client = client;		
		this.compte = compte;
	}	
	
	public Operation() {
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
	
	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public Compte getCompte() {
		return compte;
	}

	public void setCompte(Compte compte) {
		this.compte = compte;
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
		Operation other = (Operation) obj;
		return Objects.equals(client, other.client) && Objects.equals(compte, other.compte)
				&& Double.doubleToLongBits(credit) == Double.doubleToLongBits(other.credit)
				&& Objects.equals(dateCreation, other.dateCreation)
				&& Double.doubleToLongBits(debut) == Double.doubleToLongBits(other.debut)
				&& Objects.equals(id, other.id) && Objects.equals(message, other.message);
	}	
	
}

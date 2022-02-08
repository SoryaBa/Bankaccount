package bankaccount.exaltit.data.dto;

import java.sql.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Objects;
import java.util.TimeZone;

import bankaccount.exaltit.data.model.Client;
import bankaccount.exaltit.data.model.Compte;

public class OperationDTO {
	
		Long id;
		Timestamp dateCreation;
		String message ;
		double debut;
		double credit;
		Client client;
		Compte compte;
		

		public OperationDTO(Timestamp dateCreation, String message, double debut, double credit, Client client,
				Compte compte) {
			this.dateCreation = dateCreation;
			this.message = message;
			this.debut = debut;
			this.credit = credit;		
			this.client = client;		
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
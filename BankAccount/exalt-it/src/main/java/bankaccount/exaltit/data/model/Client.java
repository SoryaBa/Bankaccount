package bankaccount.exaltit.data.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name="client")
public class Client {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	Long id;
	
	String nom;
	String prenom;
	String email;
	
	Adresse adresse;
	
	@ManyToMany(cascade = {
	        CascadeType.PERSIST,
	        CascadeType.MERGE,
	        CascadeType.REFRESH
	    })
	    @JoinTable(name = "linked_cpt_client",
	        joinColumns = @JoinColumn(name = "id_client"),
	        inverseJoinColumns = @JoinColumn(name = "id_compte")
	    )
	List<Compte> comptes;

	
	public Client() {
		super();
	}

	public Client(String nom, String prenom, String email, Adresse adresse, List<Compte> mesComptes) {
		this.nom = nom;
		this.prenom = prenom;
		this.email = email;
		this.adresse = adresse;
		this.comptes = mesComptes == null ? new ArrayList<Compte>() : mesComptes;
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
		Client other = (Client) obj;
		return Objects.equals(email, other.email) && Objects.equals(id, other.id) && Objects.equals(nom, other.nom)
				&& Objects.equals(prenom, other.prenom);
	}
	
}

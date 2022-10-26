package tp.appliSpring.core.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="CLIENT")
@NamedQuery(name = "Client.findClientByIdWithComptes",
            query = "SELECT c FROM Client c LEFT JOIN FETCH c.comptes WHERE c.numero = ?1")
@Getter @Setter
@NoArgsConstructor 
public class Client {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long numero;
	
	private String prenom;
	
	private String nom;
	//...
	
	@ManyToMany(/*fetch = FetchType.LAZY */) //Spring-Data impose LAZY et ignore EAGER
	@JoinTable(name = "CLIENT_COMPTE" ,
	           joinColumns = { @JoinColumn(name="numClient") } ,
	           inverseJoinColumns = { @JoinColumn(name="numCompte") } )
	private List<Compte> comptes; /* = new ArrayList<>();*/ //ou bien new dans methode addCompte() 
	
	public void addCompte(Compte compte) {
		if(this.comptes == null) this.comptes =new ArrayList<>();
		this.comptes.add(compte);
	}
	
	
	
	
	public Client(Long numero, String prenom, String nom) {
		super();
		this.numero = numero;
		this.prenom = prenom;
		this.nom = nom;
	}




	@Override
	public String toString() {
		return "Client [numero=" + numero + ", prenom=" + prenom + ", nom=" + nom + "]";
	}
	
	
}

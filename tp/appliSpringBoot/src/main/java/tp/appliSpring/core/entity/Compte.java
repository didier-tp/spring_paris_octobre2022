package tp.appliSpring.core.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name="COMPTE")
@NamedQueries({
   @NamedQuery(name="Compte.findBySoldeMin", query="SELECT c FROM Compte c WHERE c.solde >= ?1"),
   @NamedQuery(name="Compte.findCompteByIdWithOperations", query="SELECT c FROM Compte c LEFT JOIN FETCH c.operations WHERE c.numero = ?1")
})
@Getter @Setter
@NoArgsConstructor 
public class Compte {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long numero;
    
    private String label;
    
    private Double solde;
    
    //@JsonIgnore est techniquement possible ici mais c'est mieux sur un DTO
    @OneToMany(mappedBy="compte")
    private List<Operation> operations = new ArrayList<>(); //avec get/set
    
  //+get/set , constructeur , toString()
    
	

	public Compte(Long numero, String label, Double solde) {
		super();
		this.numero = numero;
		this.label = label;
		this.solde = solde;
	}

	@Override
	public String toString() {
		return "Compte [numero=" + numero + ", label=" + label + ", solde=" + solde + "]";
	}

	



    //+get/set , constructeur , toString()


	
}
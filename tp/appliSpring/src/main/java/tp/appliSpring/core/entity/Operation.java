package tp.appliSpring.core.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="OPERATION")
public class Operation {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long numOp;
	private Double montant; // -30 si achat , +2000 si reception d'un salaire
	private String label; // "achat TV" , "reception salaire"
	
	@Temporal(TemporalType.DATE)
	private Date dateOp; //dans la colonne de la table "2022-08-02"
	
	@ManyToOne
	@JoinColumn(name="numCompte")
	private Compte compte;

	public Operation() {
	}
	

	public Operation(Long numOp, Double montant, String label, Date dateOp, Compte compte) {
		super();
		this.numOp = numOp;
		this.montant = montant;
		this.label = label;
		this.dateOp = dateOp;
		this.compte = compte;
	}
	
	



	@Override
	public String toString() {
		return "Operation [numOp=" + numOp + ", montant=" + montant + ", label=" + label + ", dateOp=" + dateOp
				+ ", compte=" + compte + "]";
	}


	public Long getNumOp() {
		return numOp;
	}

	public void setNumOp(Long numOp) {
		this.numOp = numOp;
	}

	public Double getMontant() {
		return montant;
	}

	public void setMontant(Double montant) {
		this.montant = montant;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public Date getDateOp() {
		return dateOp;
	}

	public void setDateOp(Date dateOp) {
		this.dateOp = dateOp;
	}

	public Compte getCompte() {
		return compte;
	}

	public void setCompte(Compte compte) {
		this.compte = compte;
	}
	
	

}
